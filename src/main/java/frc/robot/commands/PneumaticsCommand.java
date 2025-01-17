package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Pneumatics;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.XboxController;



public class PneumaticsCommand extends Command{
    
        XboxController controller2 = new XboxController(1);
            private Pneumatics m_pneumatics;
            public PneumaticsCommand(Pneumatics pneumaticsSubsystem){
                m_pneumatics = pneumaticsSubsystem;
                addRequirements(m_pneumatics);
            }
             public boolean isFinished(){
                return false;
             }
              public void end(){
                Pneumatics.TOP_PUMP.set(DoubleSolenoid.Value.kOff);
                Pneumatics.BOTTOM_PUMP.set(DoubleSolenoid.Value.kOff);
                Pneumatics.COMPRESSOR.disable();
              }  
            public void interrupted(){
                end();
            }

            }