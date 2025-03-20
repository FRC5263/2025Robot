// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class climberSubsystem extends SubsystemBase {
  private boolean ClimberOn = false;
  private SparkMax climber;
  public climberSubsystem() {
    climber = new SparkMax(Constants.climberID, MotorType.kBrushless);
  }

  public void climb(double power){
    climber.set(power);
  }

  @Override
  public void periodic() {
    SmartDashboard.putNumber("Climber", climber.get());
  }

  public void toggleClimber(){
    ClimberOn = !ClimberOn;
  }
  
  public boolean getClimberOn(){
    return ClimberOn;
  }
}
