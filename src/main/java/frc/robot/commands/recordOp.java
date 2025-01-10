// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.io.IOException;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.auton.recordAuton;

/* You should consider using the more terse Command factories API instead https://docs.wpilib.org/en/stable/docs/software/commandbased/organizing-command-based.html#defining-commands */
public class recordOp extends Command {
  /** Creates a new recordOp. */
  public boolean isOperatorControl = true;
  public boolean isRecording;
  public recordOp() {
    // Use addRequirements() here to declare subsystem dependencies.
  }

  public void operatorControl(){
    recordAuton recorder = null;
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

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
