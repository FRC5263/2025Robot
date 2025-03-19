package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import frc.robot.Constants;

public class clawSubsystem extends SubsystemBase {
    public static SparkMax claw;
    private boolean clawOn = false;
        public clawSubsystem(){
            claw = new SparkMax(Constants.clawID, MotorType.kBrushless);
        }
        public void clawSpeed(double power){
            claw.set(power);
          }
          @Override
          public void periodic() {
            // This method will be called once per scheduler run
          }
        
          public void toggleClaw(){
            clawOn = !clawOn;
          }
          public boolean getClawOn(){
            return clawOn;
          }
}
