// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import frc.robot.Constants;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class intakeSubsystem extends SubsystemBase {
  /** Creates a new intakeSubsystem. */
  private boolean intakeOn = false;
  private SparkMax intake;
  public intakeSubsystem() {
    intake = new SparkMax(Constants.intake1_ID, MotorType.kBrushless);
  }

  public void ySpeed(double power){
    intake.set(power);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void toggleIntake(){
    intakeOn = !intakeOn;
  }
  public boolean getIntakeOn(){
    return intakeOn;
  }
}
