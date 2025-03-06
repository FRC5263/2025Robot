package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.claw;
public class clawCommand extends Command {
    private claw m_Claw;
        public clawCommand(claw clawSubsystem){
            m_Claw = clawSubsystem;
            addRequirements(m_Claw);
        }

        @Override
        public void initialize() {}

        @Override
        public void execute(){      
        }

        
  @Override
  public void end(boolean interrupted) {
    claw.Wrist.set(0);
    claw.intake_coral.set(0);
    claw.intake_alg.set(0);
  }

  @Override
  public boolean isFinished() {
    return false;
  }


}
