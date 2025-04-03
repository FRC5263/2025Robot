// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

//import frc.robot.auton.recordAuton;
import frc.robot.commands.driveCommand;
import frc.robot.commands.intakeCommand;
import frc.robot.commands.climberCommand;
import frc.robot.subsystems.climberSubsystem;
import frc.robot.subsystems.intakeSubsystem;
import frc.robot.subsystems.mecDrive;
import frc.robot.subsystems.clawSubsystem;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandJoystick;
import frc.robot.auton.autonBad;

public class RobotContainer {

  public static final int autoNumber = 1;

  private final mecDrive m_MecDrive = new mecDrive();


  private final climberSubsystem climber = new climberSubsystem();
  private final intakeSubsystem intake = new intakeSubsystem();

  private final autonBad m_autonBad = new autonBad(m_MecDrive);

  private final CommandJoystick stick3 = new CommandJoystick(2);
  private final CommandJoystick stick1 = new CommandJoystick(0);
  private final CommandJoystick stick2 = new CommandJoystick(1);

  public RobotContainer() {
    m_MecDrive.setDefaultCommand(new driveCommand(m_MecDrive, 
      () -> Math.pow(stick1.getRawAxis(1) * .9, 3),       // Translation (Y)
      () -> -Math.pow(stick1.getRawAxis(0) * .9, 3),      // Strafe (X)
      () -> -Math.pow((stick2.getRawAxis(0) * .9), 3)));  // Rotation (Z)

    intake.setDefaultCommand(new intakeCommand(intake, () -> -Math.pow((stick3.getY() * .5), 3)));

    configureBindings();
  }

  private void configureBindings() {
    stick3.button(6).whileTrue(new climberCommand(climber, -0.1));
  }

  public Command getAutonomousCommand() {
    return m_autonBad;
  }
}