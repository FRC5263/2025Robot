// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.auton.recordAuton;
import frc.robot.subsystems.mecDrive;
import edu.wpi.first.wpilibj.drive.MecanumDrive;

import java.io.IOException;

import edu.wpi.first.wpilibj.Joystick;

public class TeleOp extends Command {
  public static Joystick stick1 = new Joystick(0);
  public static Joystick stick2 = new Joystick(1);
  mecDrive m_mecDrive;
  MecanumDrive m_drive;
  public boolean isRecording = false;
  public boolean isOperatorControl  = true;
  recordAuton recorder = null;


  public TeleOp(Joystick stick1, Joystick stick2, mecDrive m_mecDrive) {
    // Use addRequirements() here to declare subsystem dependencies.
    TeleOp.stick1 = stick1;
    TeleOp.stick2 = stick2;
    this.m_mecDrive = m_mecDrive;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // TODO: wrap the stick axis in their own vars so I don't have to change this logic everywhere and I don't have to debug THIS (and it'll look a bit prettier)
    // Deadzones for a given direction
    if(Math.pow((Math.atan2(stick1.getRawAxis(1), stick1.getRawAxis(0)) * .9), 3) < .301 && Math.pow((Math.atan2(stick1.getRawAxis(1), stick1.getRawAxis(0)) * .9), 3) > -.301){
        m_drive.driveCartesian(0, Math.pow((Math.hypot(stick1.getRawAxis(0), stick1.getRawAxis(1) * .9)), 3), -stick2.getRawAxis(0));     // X deadzones
        m_drive.feed();
    }
    if(Math.pow((Math.hypot(stick1.getRawAxis(0), stick1.getRawAxis(1)) * .9), 3) < .301 && Math.pow((Math.hypot(stick1.getRawAxis(0), stick1.getRawAxis(1)) * .9), 3) > -.301){
      m_drive.driveCartesian(-Math.pow((Math.atan2(stick1.getRawAxis(1), stick1.getRawAxis(0)) * .9), 3), 0, -stick2.getRawAxis(0));      // Y deadzones
      m_drive.feed();
      
    }
    if(stick2.getRawAxis(0) < .301 && stick2.getRawAxis(0) > -.301){
      m_drive.driveCartesian(-Math.pow((Math.atan2(stick1.getRawAxis(1), stick1.getRawAxis(0)) * .9), 3), Math.pow((Math.hypot(stick1.getRawAxis(0), stick1.getRawAxis(1) * .9)), 3), 0);     // Z deadzones
      m_drive.feed();
    }
    m_drive.driveCartesian(-Math.pow((Math.atan2(stick1.getRawAxis(1), stick1.getRawAxis(0)) * .9), 3), Math.pow((Math.hypot(stick1.getRawAxis(0), stick1.getRawAxis(1) * .9)), 3), -stick2.getRawAxis(0));
    m_drive.feed();

    if(stick1.getRawButton(4)){
      isRecording = true;
    }
    if(stick1.getRawButton(5)){
      isRecording = false;
    }

    // Moved all of recordOp
    try {
      recorder = new recordAuton();
    } catch (Exception e) {
      e.printStackTrace();
    }
    while(isOperatorControl){
      isRecording = !isRecording;
    }
    if(isRecording){
      try {
        recorder.record();
      } catch (Exception e) { e.printStackTrace(); }
    }
  
    try{
      if(recorder != null){
    			recorder.end();
    		}
		} catch(IOException e){ e.printStackTrace(); }
  }



  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return false;
  }
}
