// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.mecDrive;

import static edu.wpi.first.units.Units.Value;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Joystick;
import frc.robot.subsystems.Pneumatics;
/* You should consider using the more terse Command factories API instead https://docs.wpilib.org/en/stable/docs/software/commandbased/organizing-command-based.html#defining-commands */
public class TeleOp extends Command {
  /** Creates a new TeleOp. */
  public Joystick stick1 = new Joystick(0);
  Joystick stick2 = new Joystick(1);
  mecDrive m_mecDrive;
  recordOp m_recordOp;
  Pneumatics m_pneumatics;
  
  public TeleOp(Joystick stick1, Joystick stick2, mecDrive m_mecDrive, recordOp m_recordOp, Pneumatics m_pneumatics) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.stick1 = stick1;
    this.stick2 = stick2;
    this.m_mecDrive = m_mecDrive;
    this.m_recordOp = m_recordOp;
    this.m_pneumatics = m_pneumatics;
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

    if(stick2.getRawButton(/*currently undecided*/2)){
      //checks if solenoid is already extended
      if(Pneumatics.TOP_PUMP.get() == DoubleSolenoid.Value.kForward){
        //"if pressed again"
        Pneumatics.TOP_PUMP.set(DoubleSolenoid.Value.kReverse);
    }
    else{
      Pneumatics.TOP_PUMP.set(DoubleSolenoid.Value.kForward);
    }
   }
   
   if(stick2.getRawButton(/*currently undecided*/3)){
    if(Pneumatics.BOTTOM_PUMP.get() == DoubleSolenoid.Value.kForward){
      Pneumatics.BOTTOM_PUMP.set(DoubleSolenoid.Value.kReverse);
    }
    else{
      Pneumatics.BOTTOM_PUMP.set(DoubleSolenoid.Value.kForward);
    }
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
