// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class intakeSubsystem extends SubsystemBase {
  /** Creates a new intakeSubsystem. */
  private Boolean intakeOn = false;
  private static SparkMax intakeMax;
    public intakeSubsystem() {
      intakeMax = new SparkMax(Constants.intake1_ID, MotorType.kBrushless);
    }
  
    public static void go(double ySpeed){
      intakeMax.set(ySpeed);
  }

  @Override
  public void periodic() {
  }
}
