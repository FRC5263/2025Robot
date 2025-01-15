package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Pneumatics;
import edu.wpi.first.wpilibj.XboxController;


public class PneumaticsCommand extends Command{
        XboxController controller2 = new XboxController(1);

            public PneumaticsCommand(Pneumatics pneumatics){
                

            }  
    
}
