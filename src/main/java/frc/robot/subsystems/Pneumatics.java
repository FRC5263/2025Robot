package frc.robot.subsystems;

import frc.robot.commands.TeleOp;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;


public class Pneumatics extends SubsystemBase {
    //both double solinoids (tubes)
     private final static DoubleSolenoid m_doubleSolenoidBottomL =
           new DoubleSolenoid(PneumaticsModuleType.REVPH, 1, 2);
           private final DoubleSolenoid m_doubleSolenoidTopL =
           new DoubleSolenoid(PneumaticsModuleType.REVPH, 3, 4);
               private final Compressor m_compressor = new Compressor(PneumaticsModuleType.REVPH);
}
