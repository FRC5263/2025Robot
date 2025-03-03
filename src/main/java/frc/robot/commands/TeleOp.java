// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.auton.recordAuton;
import frc.robot.subsystems.mecDrive;
import frc.robot.subsystems.pneumatics;
import edu.wpi.first.wpilibj.drive.MecanumDrive;


import edu.wpi.first.wpilibj.Joystick;

public class TeleOp extends Command {
  public static Joystick stick1 = new Joystick(0);
  public static Joystick stick2 = new Joystick(1);
  mecDrive m_mecDrive;
  recordOp m_recordOp;
  MecanumDrive m_drive;
  pneumatics m_Pneumatics;


  public TeleOp(Joystick stick1, Joystick stick2, mecDrive m_mecDrive, recordOp m_recordOp, pneumatics m_Pneumatics) {
    // Use addRequirements() here to declare subsystem dependencies.
    TeleOp.stick1 = stick1;
    TeleOp.stick2 = stick2;
    this.m_mecDrive = m_mecDrive;
    this.m_recordOp = m_recordOp;
    this.m_Pneumatics = m_Pneumatics;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(Math.pow((Math.atan2(stick1.getRawAxis(1), stick1.getRawAxis(0)) * .9), 3) < .301 && Math.pow((Math.atan2(stick1.getRawAxis(1), stick1.getRawAxis(0)) * .9), 3) > -.301){
        m_drive.driveCartesian(0, Math.pow((Math.hypot(stick1.getRawAxis(0), stick1.getRawAxis(1) * .9)), 3), -stick2.getRawAxis(0));
        m_drive.feed();
    }
    if(Math.pow((Math.hypot(stick1.getRawAxis(0), stick1.getRawAxis(1)) * .9), 3) < .301 && Math.pow((Math.hypot(stick1.getRawAxis(0), stick1.getRawAxis(1)) * .9), 3) > -.301){
      m_drive.driveCartesian(-Math.pow((Math.atan2(stick1.getRawAxis(1), stick1.getRawAxis(0)) * .9), 3), 0, -stick2.getRawAxis(0));
      m_drive.feed();
    }
    if(stick2.getRawAxis(0) < .301 && stick2.getRawAxis(0) > -.301){
      m_drive.driveCartesian(-Math.pow((Math.atan2(stick1.getRawAxis(1), stick1.getRawAxis(0)) * .9), 3), Math.pow((Math.hypot(stick1.getRawAxis(0), stick1.getRawAxis(1) * .9)), 3), 0);
      m_drive.feed();
    }
    m_drive.driveCartesian(-Math.pow((Math.atan2(stick1.getRawAxis(1), stick1.getRawAxis(0)) * .9), 3), Math.pow((Math.hypot(stick1.getRawAxis(0), stick1.getRawAxis(1) * .9)), 3), -stick2.getRawAxis(0));
    m_drive.feed();

    // I do not know what this does
    if(stick1.getRawButton(1)){
      m_Pneumatics.compressorUpTop();
    }
    if(stick1.getRawButton(2)){
      m_Pneumatics.compressorDownTop();
    }
    if(stick1.getRawButton(4)){
      m_recordOp.operatorControl();
    }
    if(stick1.getRawButton(5)){
      m_recordOp.isRecording = false;
    }
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
