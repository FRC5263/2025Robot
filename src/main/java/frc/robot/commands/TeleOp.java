// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.mecDrive;
import edu.wpi.first.wpilibj.drive.MecanumDrive;


import edu.wpi.first.wpilibj.Joystick;

public class TeleOp extends Command {
  public Joystick stick1 = new Joystick(0);
  public Joystick stick2 = new Joystick(1);
  mecDrive m_mecDrive;
  recordOp m_recordOp;
  MecanumDrive m_drive;
  double x = Math.pow((Math.atan2(stick1.getRawAxis(1), stick1.getRawAxis(0)) * .9), 3);
  double y = Math.pow((Math.hypot(stick1.getRawAxis(0), stick1.getRawAxis(1)) * .9), 3);
  double z = Math.pow((stick2.getRawAxis(0) * .9), 3);
  
  public TeleOp(Joystick stick1, Joystick stick2, mecDrive m_mecDrive, recordOp m_recordOp) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.stick1 = stick1;
    this.stick2 = stick2;
    this.m_mecDrive = m_mecDrive;
    this.m_recordOp = m_recordOp;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(stick1.getRawButton(1)){
      m_recordOp.operatorControl();
    }
      m_drive.driveCartesian(x, y, z);
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
