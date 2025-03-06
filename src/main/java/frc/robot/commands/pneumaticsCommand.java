// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.pneumatics;

// Honestly I have no clue how to do this
// Mallek if you can hear us Mallek please save us Mallek
// Please save us Mallek


/*  all thats REALLY needed in execute is to enable the compressor (which i set to hybrid for now)
 * If the pressure switch continues to kill itself(as always) the swich type can be changed
*/

public class pneumaticsCommand extends Command {
  private pneumatics m_Pneumatics;
  public pneumaticsCommand(pneumatics pneumaticsSubsystem) {
    m_Pneumatics = pneumaticsSubsystem;
    addRequirements(m_Pneumatics);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    
    m_Pneumatics.compressorOn();
  }

  @Override
  public void end(boolean interrupted) {
    m_Pneumatics.compressorOff();
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
