// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class intakeSubsystem extends SubsystemBase {
  private boolean intakeOn = false;
  private SparkMax intake;
  private String key = "intake going out";
  private String keyIn = "intake going in";
  public intakeSubsystem() {
    intake = new SparkMax(Constants.intake1_ID, MotorType.kBrushless);
  }

  public void ySpeed(double power){
    intake.set(power);
  }

  @Override
  public void periodic() {
    if(intake.get() > 0.0){
    SmartDashboard.putNumber(key, intake.get());
    } else{
      if(intake.get() < 0.0){
        SmartDashboard.putNumber(keyIn, intake.get());
      }
      else{
        SmartDashboard.putNumber("intake", 0);
      }
    }
  }

  public void toggleIntake(){
    intakeOn = !intakeOn;
  }
  public boolean getIntakeOn(){
    return intakeOn;
  }
}
