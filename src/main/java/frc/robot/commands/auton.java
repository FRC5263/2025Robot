// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.auton.playAuton;
import frc.robot.subsystems.mecDrive;

/* You should consider using the more terse Command factories API instead https://docs.wpilib.org/en/stable/docs/software/commandbased/organizing-command-based.html#defining-commands */
public class auton extends Command {
  /** Creates a new auton. */
  boolean isAutonomous = true;
  public auton() {}
    mecDrive mecdrive;
    public auton(mecDrive subsystem){
        mecdrive = subsystem;
    }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
            playAuton player = null;
        try {
            player = new playAuton();
        } catch (Exception e) {
            e.printStackTrace();
        }
        while(isAutonomous){
        if(player != null){
            player.play();
        }
    }
    if(player != null){
        player.end();
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
