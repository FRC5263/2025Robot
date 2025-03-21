// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.climberSubsystem;

public class climberCommand extends Command {
  climberSubsystem climber;
  double ySpeed;

  public climberCommand(climberSubsystem climber, double ySpeed) {
    this.climber = climber;
    this.ySpeed = ySpeed;
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    climber.climb(1);
    climber.toggleClimber();
  }

  @Override
  public void end(boolean interrupted) {
    climber.climb(0);
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
