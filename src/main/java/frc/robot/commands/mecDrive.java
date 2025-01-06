package frc.robot.commands;

import java.util.function.DoubleConsumer;

import edu.wpi.first.hal.HAL;
import edu.wpi.first.hal.FRCNetComm.tInstances;
import edu.wpi.first.hal.FRCNetComm.tResourceType;
import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.util.sendable.Sendable;
import edu.wpi.first.util.sendable.SendableBuilder;
import frc.robot.Constants;

import com.revrobotics.spark.SparkMax;

import edu.wpi.first.wpilibj.drive.RobotDriveBase;

/* shamlessly taken from WPI docs, I'm simply too lazy
 *
 * wheel config is FINAL (and also stolen from WPI), builders MUST do it like this (or execution):
 * 
 *      F
 * \\_______//
 * \\ |   | //
 *    |   |
 * //_|___|_\\
 * //       \\
 *      B
 * 
 * 
 * I barely understand how this works so we're keeping it such that I can kind of grasp it
 */

public class mecDrive extends RobotDriveBase implements Sendable, AutoCloseable {

    // motorReal !!!
    private final DoubleConsumer m_frontRightMotor;
    private final DoubleConsumer m_backRightMotor;
    private final DoubleConsumer m_backLeftMotor;
    private final DoubleConsumer m_frontLeftMotor;

    // realMotorReal !!!!!!!!
    public static SparkMax frontRightMotor = new SparkMax(Constants.frontRightID, com.revrobotics.spark.SparkLowLevel.MotorType.kBrushless);
    public static SparkMax backRightMotor = new SparkMax(Constants.backRightID, com.revrobotics.spark.SparkLowLevel.MotorType.kBrushless);
    public static SparkMax backLefttMotor = new SparkMax(Constants.frontLeftID, com.revrobotics.spark.SparkLowLevel.MotorType.kBrushless);
    public static SparkMax frontLeftMotor = new SparkMax(Constants.frontLeftID, com.revrobotics.spark.SparkLowLevel.MotorType.kBrushless);

    // Used to send out to the DoubleConsumer
    private double m_frontRightOutput;
    private double m_backRightOutput;
    
    private double m_backLeftOutput;
    private double m_frontLeftOutput;

    private boolean m_isOutputSent; // You're never gonna guess what this does


    //-----I can't remove this without the interpreter screaming so they must stay unfortunatley-----//
    @Override
    public void initSendable(SendableBuilder builder) {
        throw new UnsupportedOperationException("Unimplemented method 'initSendable'");
    }

    @Override
    public String getDescription() {
        throw new UnsupportedOperationException("Unimplemented method 'getDescription'");
    }
    //-----------------------------------------------------------------------------------------------//

    // Wheel speeds
    @SuppressWarnings("MemberName")
    public static class WheelSpeeds{
        public double frontRight;
        public double backRight;
        public double backLeft;
        public double frontLeft;
        public WheelSpeeds(){}

        public WheelSpeeds(double frontRight, double backRight, double backLeft, double frontLeft){
                this.frontRight = frontRight;
                this.backRight = backRight;
                this.backLeft = backLeft;
                this.frontLeft = frontLeft;
        }
    }

        public mecDrive(
            DoubleConsumer frontRightMotor,
            DoubleConsumer backRightMotor,
            DoubleConsumer backLeftMotor,
            DoubleConsumer frontLeftMotor){
            m_frontRightMotor = frontRightMotor;
            m_backRightMotor = backRightMotor;
            m_backLeftMotor = backLeftMotor;
            m_frontLeftMotor = frontLeftMotor;
        }
        @Override
        public void close(){}
        // Drive method
        // I don't know if this HAL report does anything actually or if it's for JVM but the code doesn't work without it
        public void driveCartesian(double xSpeed, double ySpeed, double zRoation){
            driveCartesian(xSpeed, ySpeed, zRoation, Rotation2d.kZero);
        }
        public void driveCartesian(double xSpeed, double ySpeed, double zRoation, Rotation2d gyroAngle){
            if(!m_isOutputSent){
                HAL.report(tResourceType.kResourceType_RobotDrive, tInstances.kRobotDrive2_MecanumCartesian, 4);
                m_isOutputSent = true;
            }
            xSpeed = MathUtil.applyDeadband(ySpeed, m_deadband);
            ySpeed = MathUtil.applyDeadband(ySpeed, m_deadband);

            var speeds = driveCartesianIK(xSpeed, ySpeed, zRoation, gyroAngle);

            m_frontRightOutput = speeds.frontRight * m_maxOutput;
            m_backRightOutput = speeds.backRight * m_maxOutput;
            m_backLeftOutput = speeds.backLeft * m_maxOutput;
            m_frontLeftOutput = speeds.frontLeft * m_maxOutput;

            m_frontRightMotor.accept(m_frontRightOutput);
            m_backRightMotor.accept(m_backRightOutput);
            m_backLeftMotor.accept(m_backLeftOutput);
            m_frontLeftMotor.accept(m_frontLeftOutput);
        }

        public void drivePolar(double magnitude, Rotation2d angle, double zRoation){
            if(!m_isOutputSent){
                HAL.report(tResourceType.kResourceType_RobotDrive, tInstances.kRobotDrive2_MecanumPolar, 4);
                m_isOutputSent = true;
            }
            driveCartesian(
                magnitude * angle.getCos(), magnitude * angle.getSin(), zRoation, Rotation2d.kZero
            );
        }

        public static WheelSpeeds driveCartesianIK(double xSpeed, double ySpeed, double zRoation){
            return driveCartesianIK(xSpeed, ySpeed, zRoation, Rotation2d.kZero);
        }
        // Cartesian inverse kinematics for Mecanum platform (whatever that means)
        // Set up for gyro
    public static WheelSpeeds driveCartesianIK(
        double xSpeed, double ySpeed, double zRoation, Rotation2d gyroAngle){
            xSpeed = MathUtil.clamp(xSpeed, -1.0, 1.0);
            ySpeed = MathUtil.clamp(ySpeed, -1.0, 1.0);

            var input = new Translation2d(xSpeed, ySpeed).rotateBy(gyroAngle.unaryMinus());
            
            double[] wheelSpeeds = new double[4];
            wheelSpeeds[MotorType.kFrontRight.value] = input.getX() - input.getY() - zRoation;
            wheelSpeeds[MotorType.kRearRight.value] = input.getX() + input.getY() - zRoation;
            wheelSpeeds[MotorType.kRearLeft.value] = input.getX() - input.getY() + zRoation;
            wheelSpeeds[MotorType.kFrontLeft.value] = input.getX() + input.getY() + zRoation;

            normalize(wheelSpeeds);

            return new WheelSpeeds(
                wheelSpeeds[MotorType.kFrontRight.value],
                wheelSpeeds[MotorType.kRearRight.value],
                wheelSpeeds[MotorType.kRearLeft.value],
                wheelSpeeds[MotorType.kFrontLeft.value]);
        }

        @Override
        public void stopMotor(){
            m_frontRightOutput = 0.0;
            m_backRightOutput = 0.0;
            m_backLeftOutput = 0.0;
            m_frontLeftOutput = 0.0;

            m_frontRightMotor.accept(0.0);
            m_backRightMotor.accept(0.0);
            m_backLeftMotor.accept(0.0);
            m_frontLeftMotor.accept(0.0);
        }
}
