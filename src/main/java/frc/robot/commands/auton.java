// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.auton.playAuton;
import frc.robot.subsystems.mecDrive;

/*
 * Step 3: 
 * This just wraps the auton play method into a command runnable by the recordOp file
 */


public class auton extends Command {
  playAuton player = null;
  mecDrive mecdrive;
    public auton(mecDrive m_MecDrive, playAuton m_auton){
        mecdrive = m_MecDrive;
        player = m_auton;
    }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
        try {
            player = new playAuton();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(player != null){
            player.play();
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    if(player != null){
      player.end();
  }
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
