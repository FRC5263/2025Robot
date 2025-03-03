package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.claw;
public class clawCommand extends Command {
    private claw m_claw;
        public clawCommand(claw clawSubsystem){
            m_claw = clawSubsystem;
            addRequirements(m_claw);
        }

        @Override
        public void initialize() {}

        @Override
        public void execute(){      
        }

        
  @Override
  public void end(boolean interrupted) {
    claw.Wrist.set(0);
    claw.intake1.set(0);
    claw.intake2.set(0);
  }

  @Override
  public boolean isFinished() {
    return false;
  }


}
