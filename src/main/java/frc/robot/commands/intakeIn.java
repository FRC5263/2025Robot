// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.intakeSubsystem;

public class intakeIn extends Command {
  public double ySpeed;
  intakeSubsystem intake;
  public intakeIn(intakeSubsystem intake, double ySpeed) {
    this.ySpeed = ySpeed;
    this.intake = intake;
    addRequirements(this.intake);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    intake.ySpeed(1);
    intake.toggleIntake();
  }

  @Override
  public void end(boolean interrupted) {
    intake.ySpeed(0);
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
