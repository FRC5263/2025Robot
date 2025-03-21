// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

//import frc.robot.auton.recordAuton;
import frc.robot.commands.driveCommand;
import frc.robot.commands.intakeIn;
import frc.robot.commands.intakeOut;
import frc.robot.commands.climberCommand;
import frc.robot.subsystems.climberSubsystem;
import frc.robot.subsystems.intakeSubsystem;
import frc.robot.subsystems.mecDrive;

import edu.wpi.first.wpilibj2.command.Command;
//import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandJoystick;
import frc.robot.auton.autonBad;
import frc.robot.auton.playAuton;
import frc.robot.auton.recordAuton;

public class RobotContainer {

  public static final int autoNumber = 1;

  private final mecDrive m_MecDrive = new mecDrive();

  private final intakeSubsystem intakeMotor = new intakeSubsystem();

  private final climberSubsystem climber = new climberSubsystem();

  private final autonBad m_autonBad = new autonBad(m_MecDrive);
  private final playAuton m_auton = new playAuton(m_MecDrive);

  private final CommandJoystick stick3 = new CommandJoystick(2);
  private final CommandJoystick stick1 = new CommandJoystick(0);
  private final CommandJoystick stick2 = new CommandJoystick(1);

  public RobotContainer() {
    // This is the absolute worst code I've ever written
    m_MecDrive.setDefaultCommand(new driveCommand(m_MecDrive, () -> Math.pow(stick1.getRawAxis(1) * .9, 3),
        () -> -Math.pow(stick1.getRawAxis(0) * .9, 3), () -> -Math.pow((stick2.getRawAxis(0) * .9), 3)));

    configureBindings();
  }

  private void configureBindings() {
    stick1.button(1).whileTrue(new intakeOut(intakeMotor, 1.0));
    stick1.button(2).whileTrue(new intakeIn(intakeMotor, -1.0));

    stick1.button(3).whileTrue(new climberCommand(climber, 1));

    stick1.button(4).whileTrue(new recordAuton(m_MecDrive));
  }

  public Command getAutonomousCommand() {
    return m_auton;

    // return Commands.run(()->mecDrive.drive(1, 0,
    // 0)).andThen(Commands.waitSeconds(2.5).andThen(()->mecDrive.drive(0, 0, 0)));
  }
}