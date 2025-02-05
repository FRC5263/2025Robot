// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.commands.TeleOp;
import frc.robot.commands.PneumaticsCommand;
import frc.robot.commands.driveCommand;
import frc.robot.commands.recordOp;
import frc.robot.commands.auton;
import frc.robot.subsystems.mecDrive;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Pneumatics;

/**
 * This class is where the bulk of the robot should be declared. Very little robot logic should actually be handled here
 * Instead, the structure of the robot (including subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // Every used object declared here
  
  private final mecDrive m_MecDrive = new mecDrive();
  private final Pneumatics m_Pneumatics = new Pneumatics();
  private final recordOp m_RecordOp = new recordOp();
  private final auton m_auton = new auton(m_MecDrive);


  private final Command m_TeleOp;

  private final Joystick stick1 = new Joystick(0);
  private final Joystick stick2 = new Joystick(1);


  public RobotContainer() {
    configureBindings();
    m_MecDrive.setDefaultCommand(new driveCommand(m_MecDrive));
    m_Pneumatics.setDefaultCommand(new PneumaticsCommand(m_Pneumatics));
    m_TeleOp = new TeleOp(stick1, stick2, m_MecDrive, m_RecordOp, m_Pneumatics);

  }

  private void configureBindings() {}

  public Command getTeleOpCommand(){
    return m_TeleOp;
  }

  public Command getAutonomousCommand() { // Why is there so much extra commenting just look at the *FILKINS* method name
    return m_auton;
  }
}
