// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.auton.recordAuton;
import frc.robot.commands.TeleOp;
import frc.robot.commands.driveCommand;
import frc.robot.commands.intakeControl;
import frc.robot.commands.clawCommand;
import frc.robot.commands.auton;
import frc.robot.subsystems.intakeSubsystem;
import frc.robot.subsystems.mecDrive;
import frc.robot.subsystems.clawSubsystem;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandJoystick;

/**
 * This class is where the bulk of the robot should be declared. Very little robot logic should actually be handled here
 * Instead, the structure of the robot (including subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // Every used object declared here
  
  public static final int autoNumber = 1;


  public final mecDrive m_MecDrive = new mecDrive();

  public final intakeSubsystem intakeMotor = new intakeSubsystem();

  public final clawSubsystem clawMotor = new clawSubsystem();
  
  private final auton m_auton = new auton(m_MecDrive);
  private final recordAuton m_record = new recordAuton();


  private final Command m_TeleOp;


  private final Joystick stick1 = new Joystick(0);
  private final Joystick stick2 = new Joystick(1);
  private final CommandJoystick CommandBasedStick1 = new CommandJoystick(0);
  private final CommandJoystick CommandBasedStick2 = new CommandJoystick(1);
  
    public RobotContainer() {
      // This is the absolute worst code I've ever written
      m_MecDrive.setDefaultCommand(new driveCommand(m_MecDrive, () -> Math.pow(stick1.getRawAxis(1) * .9, 3), () -> -Math.pow(stick1.getRawAxis(0) * .9, 3), () -> -Math.pow((stick2.getRawAxis(0) * .9), 3), false));
      m_TeleOp = new TeleOp(stick1, stick2, m_MecDrive, intakeMotor);
    
      configureBindings();
  
    }
  // this is important. don delete this under any circumstances
    private void configureBindings() {
      CommandBasedStick1.button(1).whileTrue(new intakeControl(intakeMotor, 1.0));
      CommandBasedStick2.button(2).whileTrue(new clawCommand(1.0,clawMotor));
      //CommandBasedStick1.button(2).whileTrue(new recordAuton());

        CommandBasedStick1.button(2).whileTrue(m_record);
    }
  
    public Command getTeleOpCommand(){
      return m_TeleOp;
  
    }
  
    public Command getAutonomousCommand() {
      return m_auton;
      
      // return Commands.run(()->mecDrive.drive(1, 0, 0)).andThen(Commands.waitSeconds(2.5).andThen(()->mecDrive.drive(0, 0, 0)));
}
}