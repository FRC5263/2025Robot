// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.auton;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.mecDrive;

public class playAuton extends Command {
  mecDrive m_drive;
  Scanner scanner;
  boolean onTime;
  long startTime;
  double deltaTime;
  double nextDouble;

  public playAuton(mecDrive m_drive) {
    this.m_drive = m_drive;
    addRequirements(m_drive);
  }

  @Override
  public void initialize() {
    try {
      scanner = new Scanner(new File(Constants.pathName));
      scanner.useDelimiter(",|\\n");
      startTime = System.currentTimeMillis();
      deltaTime = nextDouble - (System.currentTimeMillis() - startTime);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void execute() {
    if ((scanner != null) && (scanner.hasNextDouble())) {
      if (onTime) {
        nextDouble = scanner.nextDouble();
      }
      if (deltaTime <= 0) {
        mecDrive.drive(scanner.nextDouble(), scanner.nextDouble(), scanner.nextDouble());
        onTime = true;
      } else {
        onTime = false;
      }
    } else {
      this.end(isFinished());
      if (scanner != null) {
        scanner.close();
        scanner = null;
      }
    }
  }

  @Override
  public void end(boolean interrupted) {
    mecDrive.drive(0, 0, 0);
    if (scanner != null) {
      scanner.close();
    }
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
