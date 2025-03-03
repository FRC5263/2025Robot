// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.io.IOException;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.auton.recordAuton;

/*
 * Last step in the autonomus before everything is wrapped up and handed over to the container
 * Entire thing runs on an if statment that determines wether or not isOperatorControl is true, which sets the mode
 * If false, record
 * If true, run back into teleOp
 * 
 * Also note: even though this a command, this acts more like a subsytem than anything. The command part is simply boilerplate so that TeleOp will take the argument
 */


public class recordOp extends Command {
  public boolean isOperatorControl = true;
  public boolean isRecording;
  public recordOp() {}

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
