// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.mecDrive;
import edu.wpi.first.wpilibj.Joystick;
import java.lang.Math;


public class driveCommand extends Command{
  public void initialize(){}
  Joystick stick1 = new Joystick(0);
  private mecDrive m_drive;

  public driveCommand(mecDrive driveSubsystem){
    m_drive = driveSubsystem;
    addRequirements(m_drive);
  }

 /* public void execute(){
    double angle = Math.atan2(stick1.getRawAxis(1), stick1.getRawAxis(0));
    double magnitude = Math.hypot(stick1.getRawAxis(0), stick1.getRawAxis(1));
    double twist = stick1.getRawAxis(2);

    angle -= mecDrive.gyro.getAngle();

    mecDrive.setSpeed(angle, magnitude, twist);
  }*/

  public boolean isFinished(){
    return false;
  }

  public void end(){
    mecDrive.setSpeed(0.0, 0.0, 0.0);
  }

  public void interrupted(){
    end();
  }
}
