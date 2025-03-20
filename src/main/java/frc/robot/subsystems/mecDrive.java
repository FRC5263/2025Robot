package frc.robot.subsystems;

import frc.robot.Constants;

import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class mecDrive extends SubsystemBase{
    public static SparkMax frontLeft;
    public static SparkMax frontRight;
    public static SparkMax rearLeft;
    public static SparkMax rearRight;
    private static MecanumDrive mecanumDrive;
    private static double speedY;
    private static double speedX;
    private static double speedZ;
    
    
        public mecDrive(){
            // Init spark max
    
            // Just ONE more print statement to make sure this works............
    
            frontRight = new SparkMax(Constants.frontRightID, MotorType.kBrushless);
            System.out.print("Front right motor initalized with ID 2\n");
            rearRight = new SparkMax(Constants.backRightID, MotorType.kBrushless);
            System.out.print("Back right motor initalized with ID 3\n");
            rearLeft = new SparkMax(Constants.backLeftID, MotorType.kBrushless);
            System.out.print("Back left motor initalized with ID 4\n");
            frontLeft = new SparkMax(Constants.frontLeftID, MotorType.kBrushless);
            System.out.print("Front left motor initalized with ID 5\n");
            System.out.print("Motor configuration set\n");
            
            // Init
            mecanumDrive = new MecanumDrive(frontLeft, rearLeft, frontRight, rearRight);
            System.out.print("Drive cartesian initalized\n");
            mecanumDrive.setSafetyEnabled(false);
        }
    
        public static void drive(double ySpeed, double xSpeed, double zRotation) {
            mecanumDrive.driveCartesian(ySpeed, xSpeed, zRotation);
            speedY = ySpeed;
            speedX = xSpeed;
            speedZ = zRotation;
    }
    @Override
    public void periodic(){
        SmartDashboard.putNumber("Forward(ySpeed)", getYspeed());
        SmartDashboard.putNumber("Strafe(xSpeed)", getXspeed());
        SmartDashboard.putNumber("Rotation(zRotation)", getZspeed());
    }
    public static double getYspeed(){
        return speedY;
    }
    public static double getXspeed(){
        return speedX;
    }
    public static double getZspeed(){
        return speedZ;
    }
}