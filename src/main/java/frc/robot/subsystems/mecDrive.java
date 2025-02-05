package frc.robot.subsystems;

import frc.robot.Constants;
import com.revrobotics.spark.SparkMax;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;

/*
 * How does this work?
 * I don't know but this *should* be the correct explanation, assuming it works immediatley
 */

public class mecDrive extends SubsystemBase{

    // Auton main points here for FRC doc reasons (this is the only way I've seen it done)
    // I ended up leaving it empty, autonMain is being linked from its own file for conviniences sake
    
    //-----I don't think this block does anything-----//
        // public static boolean isAutonomous = false;

        // public static final int autoNumber = 1;

        // static String autoFile = new String("auton" + autoNumber + ".csv");
    //------------------------------------------------//

    public static MotorController FRD;      //= new SparkMax(Constants.frontRightID, com.revrobotics.spark.SparkLowLevel.MotorType.kBrushless);
    public static MotorController BRD;      //= new SparkMax(Constants.backRightID, com.revrobotics.spark.SparkLowLevel.MotorType.kBrushless);
    public static MotorController BLD;      // = new SparkMax(Constants.backLeftID, com.revrobotics.spark.SparkLowLevel.MotorType.kBrushless);
    public static MotorController FLD;      // = new SparkMax(Constants.frontLeftID, com.revrobotics.spark.SparkLowLevel.MotorType.kBrushless);
    public static ADXRS450_Gyro gyro;

    public mecDrive(){
        FRD = new SparkMax(Constants.frontRightID, com.revrobotics.spark.SparkLowLevel.MotorType.kBrushless);
        BRD = new SparkMax(Constants.backRightID, com.revrobotics.spark.SparkLowLevel.MotorType.kBrushless);
        BLD = new SparkMax(Constants.backLeftID, com.revrobotics.spark.SparkLowLevel.MotorType.kBrushless);
        FLD = new SparkMax(Constants.frontLeftID, com.revrobotics.spark.SparkLowLevel.MotorType.kBrushless);
        gyro = new ADXRS450_Gyro();
    }

    public void shadowAuton(){}

    public void mecanumDrive(double FR, double BR, double BL, double FL){
        FRD.set(FR);
        BRD.set(BR);
        BLD.set(BL);
        FLD.set(FL);
    }

    public static void setSpeed(double translationAngle, double translationPower, double turnPower){
        // Motor power math, shamelessly stolen from 6624's implamentation because I don't know circles
        double FLBRPower = translationPower * Math.sqrt(2) * 0.5 * (Math.sin(translationAngle) + Math.cos(translationAngle));
        double FRBLPower = translationPower * Math.sqrt(2) * 0.5 * (Math.sin(translationAngle) - Math.cos(translationAngle));

        // Make power consistent on turn, check to see if turning angle interferes with math
        double turnScale = Math.max(Math.abs(FLBRPower + turnPower), Math.abs(FLBRPower - turnPower));
        turnScale = Math.max(turnScale, Math.max(Math.abs(FRBLPower + turnPower), Math.abs(FLBRPower - turnPower)));

        // Scale
        if(Math.abs(turnScale) < 1.0){
            turnScale = 1.0;
        }


        // Set values
        FRD.set(FRBLPower);
        BLD.set(FRBLPower);
        FLD.set(FLBRPower);
        BRD.set(FLBRPower);
    }
}


























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
 *

public class mecDrive extends RobotDriveBase implements Sendable, AutoCloseable {

    // motorReal !!!
    private final DoubleConsumer m_frontRightMotor;
    private final DoubleConsumer m_backRightMotor;
    private final DoubleConsumer m_backLeftMotor;
    private final DoubleConsumer m_frontLeftMotor;

    // realMotorReal !!!!!!!!
    public static SparkMax frontRightMotor = new SparkMax(Constants.frontRightID, com.revrobotics.spark.SparkLowLevel.MotorType.kBrushless);
    public static SparkMax backRightMotor = new SparkMax(Constants.backRightID, com.revrobotics.spark.SparkLowLevel.MotorType.kBrushless);
    public static SparkMax backLeftMotor = new SparkMax(Constants.frontLeftID, com.revrobotics.spark.SparkLowLevel.MotorType.kBrushless);
    public static SparkMax frontLeftMotor = new SparkMax(Constants.frontLeftID, com.revrobotics.spark.SparkLowLevel.MotorType.kBrushless);

    // Used to send out to the DoubleConsumer
    private double m_frontRightOutput;
    private double m_backRightOutput;
    private double m_backLeftOutput;
    private double m_frontLeftOutput;

    double speed;

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

        public void setMotor(){
            m_frontRightOutput = speed;
            m_backRightOutput = speed;
            m_backLeftOutput = speed;
            m_frontLeftOutput = speed;

            m_frontRightMotor.accept(speed);
            m_backRightMotor.accept(speed);
            m_backLeftMotor.accept(speed);
            m_frontLeftMotor.accept(speed);

            frontRightMotor.set(speed);
            backRightMotor.set(speed);
            backLeftMotor.set(speed);
            frontLeftMotor.set(speed);
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

            frontRightMotor.set(0.0);
            backRightMotor.set(0.0);
            backLeftMotor.set(0.0);
            frontLeftMotor.set(0.0);
        }
}
*/