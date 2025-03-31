// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.intakeSubsystem;

public class intakeCommand extends Command {
  private intakeSubsystem intake;
  private DoubleSupplier ySpeed;
  public intakeCommand(intakeSubsystem intakeSub, DoubleSupplier ySpeed) {
    intake = intakeSub;
    this.ySpeed = ySpeed;

    addRequirements(intake);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    intakeSubsystem.go(ySpeed.getAsDouble());
  }

  @Override
  public boolean isFinished() {
    return false;
  }
  
  @Override
  public void end(boolean interrupted) {
    intakeSubsystem.go(0.0);
  }

}
