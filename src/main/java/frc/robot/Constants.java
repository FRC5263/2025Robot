// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

public final class Constants {
  /*
   * Motor order:
   * First 4 ALWAYS drivetrain
   * More to come
   */
  public static final int CANID[] = {0, 1, 2, 3};
  public static final int frontRightID = CANID[0];
  public static final int backRightID = CANID[1];
  public static final int backLeftID = CANID[2];
  public static final int frontLeftID = CANID[3];
}
