package frc.robot.subsystems;

import frc.robot.Constants;
import com.revrobotics.spark.SparkMax;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;

public class mecDrive extends SubsystemBase{
    private SparkMax frontLeft;
    private SparkMax frontRight;
    private SparkMax rearLeft;
    private SparkMax rearRight;
    private static MecanumDrive mecanumDrive;

    public mecDrive(){
        frontLeft = new SparkMax(Constants.frontLeftID, com.revrobotics.spark.SparkLowLevel.MotorType.kBrushless);
        frontRight = new SparkMax(Constants.frontRightID, com.revrobotics.spark.SparkLowLevel.MotorType.kBrushless);
        rearLeft = new SparkMax(Constants.backLeftID, com.revrobotics.spark.SparkLowLevel.MotorType.kBrushless);
        rearRight = new SparkMax(Constants.backRightID, com.revrobotics.spark.SparkLowLevel.MotorType.kBrushless);

        // Invert motors on one side
        frontRight.setInverted(true);
        rearRight.setInverted(true);
        // Init
        mecanumDrive = new MecanumDrive(frontLeft, rearLeft, frontRight, rearRight);
    }

    public static void drive(double ySpeed, double xSpeed, double zRotation) {
       // Use MecanumDrive's drive method
        mecanumDrive.driveCartesian(ySpeed, xSpeed, zRotation);
        mecanumDrive.feed();    // I don't know what this does or why it needs this but RioLog bitches at me whenever I don't have it
    }
}