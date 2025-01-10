// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.mecDrive;
import edu.wpi.first.wpilibj.Joystick;

/* You should consider using the more terse Command factories API instead https://docs.wpilib.org/en/stable/docs/software/commandbased/organizing-command-based.html#defining-commands */
public class TeleOp extends Command {
  /** Creates a new TeleOp. */
  public Joystick stick1 = new Joystick(0);
  Joystick stick2 = new Joystick(1);
  mecDrive m_mecDrive;
  recordOp m_recordOp;
  public TeleOp(Joystick stick1, Joystick stick2, mecDrive m_mecDrive, recordOp m_recordOp) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.stick1 = stick1;
    this.stick2 = stick2;
    this.m_mecDrive = m_mecDrive;
    this.m_recordOp = m_recordOp;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(stick1.getRawButton(1)){
      m_recordOp.operatorControl();
    }
    double angle = Math.atan2(stick1.getRawAxis(1), stick1.getRawAxis(0));
    double magnitude = Math.hypot(stick1.getRawAxis(0), stick1.getRawAxis(1));
    double twist = stick1.getRawAxis(2);

    angle -= mecDrive.gyro.getAngle();

    mecDrive.setSpeed(angle, magnitude, twist);
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
