package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.intake;
public class IntakeCommand extends Command {
    private intake m_Intake;
        public IntakeCommand(intake intakeSubsystem){
            m_Intake = intakeSubsystem;
            addRequirements(m_Intake);
        }

        @Override
        public void initialize() {}

        @Override
        public void execute(){      
        }

        
  @Override
  public void end(boolean interrupted) {
    intake.Intake.set(0);
  }

  @Override
  public boolean isFinished() {
    return false;
  }


}
