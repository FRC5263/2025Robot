// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.auton;

import java.io.FileWriter;
import java.io.IOException;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.mecDrive;

public class recordAuton extends Command {

  mecDrive m_drive;
  FileWriter writeFile;
  long startTime;

  public recordAuton(mecDrive m_drive) {
    this.m_drive = m_drive;
    addRequirements(m_drive);
  }

  @Override
  public void initialize() {
    try {
      writeFile = new FileWriter(Constants.pathName);
    } catch (IOException e) {
      e.printStackTrace();
    }
    startTime = System.currentTimeMillis();
  }

  @Override
  public void execute() {
    if (writeFile != null) {
      try {
        writeFile.append("," + m_drive.getYspeed());
        writeFile.append("," + m_drive.getXspeed());
        writeFile.append("," + m_drive.getZspeed() + "\n");
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  @Override
  public void end(boolean interrupted) {
    if (writeFile != null) {
      try {
        writeFile.flush();
        writeFile.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  public void killTask() {
    if (writeFile != null) {
      try {
        writeFile.flush();
        writeFile.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
