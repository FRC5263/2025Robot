// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.auton;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.mecDrive;

public class autonBad extends Command {
  mecDrive m_drive;
  Timer m_timer;
  long startTime;
  long elapsedTime;
  public autonBad(mecDrive drive) {
    this.m_drive = drive;
    m_timer = new Timer();
    addRequirements(this.m_drive);
  }

  @Override
  public void initialize() {
    m_timer.reset();
    m_timer.start();
  }

  @Override
  public void execute() {
    mecDrive.drive(1, 0, 0);
  }

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return m_timer.get() >= 2;
  }
}
