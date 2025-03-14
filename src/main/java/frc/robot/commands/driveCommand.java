// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.mecDrive;
import edu.wpi.first.wpilibj.Joystick;
import java.util.function.DoubleSupplier;


/* Hello all continuous years in FRC5263
 * Use this as a good command example
 * This is good code so until the structre of the command system itself changes, use this as a base for the drive command
*/
public class driveCommand extends Command{
  public void initialize(){}
  Joystick stick1 = new Joystick(0);
  private mecDrive m_drive;
  private DoubleSupplier x;
  private DoubleSupplier y;
  private DoubleSupplier z;

  public driveCommand(mecDrive driveSubsystem, DoubleSupplier x, DoubleSupplier y, DoubleSupplier z){
    m_drive = driveSubsystem;
    this.z = z;
    this.y = y;
    this.x = x;
    addRequirements(m_drive);
  }

  public void execute(){
    mecDrive.drive(-x.getAsDouble(), -y.getAsDouble(), -z.getAsDouble());
  }

  public boolean isFinished(){
    return false;
  }

  public void end(){
    mecDrive.drive(0.0, 0.0, 0.0);
  }

  public void interrupted(){
    end();
  }
}
