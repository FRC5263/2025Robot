// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.mecDrive;
import edu.wpi.first.wpilibj.drive.MecanumDrive;


import edu.wpi.first.wpilibj.Joystick;

public class TeleOp extends Command {
  public static Joystick stick1 = new Joystick(0);
  public static Joystick stick2 = new Joystick(1);
  mecDrive m_mecDrive;
  recordOp m_recordOp;
  MecanumDrive m_drive;
  public static double y = -Math.pow((stick1.getRawAxis(1) * .5), 3);
  public static double x = Math.pow((stick1.getRawAxis(0) * .5), 3);
  public static double z = Math.pow((stick2.getRawAxis(0) * .5), 3);
  
  public TeleOp(Joystick stick1, Joystick stick2, mecDrive m_mecDrive, recordOp m_recordOp) {
    // Use addRequirements() here to declare subsystem dependencies.
    TeleOp.stick1 = stick1;
    TeleOp.stick2 = stick2;
    this.m_mecDrive = m_mecDrive;
    this.m_recordOp = m_recordOp;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    x = 0;
    y = 0;
    z = 0;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // So theoretically maybeprobably button 2 should start the record process
    if(stick1.getRawButton(2)){
      m_recordOp.isOperatorControl = false;
    }
    // And hopefully maybeprobably this will end and save it
    if(stick1.getRawButton(1)){
      m_recordOp.operatorControl();
    }
    m_drive.feed();
    if(x < .301 && y < .301 && z < .301 &&
      x > -.301 && y > -.301 && z > -.301){
      m_drive.driveCartesian(0, 0, 0);
    }
    m_drive.driveCartesian(x, y, -z);
   }



  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
