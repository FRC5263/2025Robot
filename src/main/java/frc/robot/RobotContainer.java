// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.auton.recordAuton;
import frc.robot.commands.driveCommand;
import frc.robot.commands.intakeIn;
import frc.robot.commands.intakeOut;
import frc.robot.commands.climberCommand;
import frc.robot.subsystems.climberSubsystem;
import frc.robot.subsystems.intakeSubsystem;
import frc.robot.subsystems.mecDrive;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandJoystick;
import frc.robot.auton.autonBad;

/**
 * This class is where the bulk of the robot should be declared. Very little robot logic should actually be handled here
 * Instead, the structure of the robot (including subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  
  public static final int autoNumber = 1;


  private final mecDrive m_MecDrive = new mecDrive();

  private final intakeSubsystem intakeMotor = new intakeSubsystem();

  private final climberSubsystem climber = new climberSubsystem();

  
  private final autonBad m_auton = new autonBad(m_MecDrive);
  private final recordAuton m_record = new recordAuton();



  private final Joystick stick1 = new Joystick(0);
  private final Joystick stick2 = new Joystick(1);
  private final CommandJoystick CommandBasedStick1 = new CommandJoystick(0);
  
    public RobotContainer() {
      // This is the absolute worst code I've ever written
      m_MecDrive.setDefaultCommand(new driveCommand(m_MecDrive, () -> Math.pow(stick1.getRawAxis(1) * .9, 3), () -> -Math.pow(stick1.getRawAxis(0) * .9, 3), () -> -Math.pow((stick2.getRawAxis(0) * .9), 3), false));

      configureBindings();
    }
    
    private void configureBindings() {
      CommandBasedStick1.button(1).whileTrue(new intakeOut(intakeMotor, 1.0));
      CommandBasedStick1.button(2).whileTrue(new intakeIn(intakeMotor, -1.0));

      CommandBasedStick1.button(3).whileTrue(new climberCommand(climber, 1));

      CommandBasedStick1.button(4).whileTrue(m_record);
    }
  
    public Command getAutonomousCommand() {
      return m_auton;
      
      //return Commands.run(()->mecDrive.drive(1, 0, 0)).andThen(Commands.waitSeconds(2.5).andThen(()->mecDrive.drive(0, 0, 0)));
}
}