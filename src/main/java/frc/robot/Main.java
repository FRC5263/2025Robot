// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.RobotBase;

// Just don't touch this code EVER
// To any of the newer programmers I do not care if a tutorial has it differently, do NOT change this for ANY reason

public final class Main {
  private Main() {}
  public static void main(String... args) {
    RobotBase.startRobot(Robot::new);
  }
}
