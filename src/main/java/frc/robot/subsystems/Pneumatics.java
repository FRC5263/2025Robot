package frc.robot.subsystems;

import frc.robot.commands.TeleOp;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;


public class Pneumatics extends SubsystemBase {
    //Compressor
     private final Compressor m_compressor = new Compressor(PneumaticsModuleType.REVPH);
    //Solinoids
    private final DoubleSolenoid m_doubleSolenoidTop =
      new DoubleSolenoid(PneumaticsModuleType.REVPH, 1, 2);
      private final DoubleSolenoid m_doubleSolenoidBottom =
      new DoubleSolenoid(PneumaticsModuleType.REVPH, 3, 4);
      //Pressure switch
        boolean pressureSwitch;

      public Pneumatics(){

      }
        
            public void periodic(){
                pressureSwitch = m_compressor.getPressureSwitchValue();
                    if(pressureSwitch) {
                        m_compressor.disable();


                    }
                    else {
                        //this MAY be changed later depending on which we want to
                        m_compressor.enableDigital();

                    }
            }
            public void initDefaultCommand(){}
            //Random stuff we might not need:
    public void pitchUpTop(){
        m_doubleSolenoidTop.set(DoubleSolenoid.Value.kForward);
    }
    public void pitchUpBottom(){
        m_doubleSolenoidBottom.set(DoubleSolenoid.Value.kForward);
    }
    public void pitchDownTop(){
        m_doubleSolenoidTop.set(DoubleSolenoid.Value.kReverse);
    }
    public void pitchDownBottom(){
        m_doubleSolenoidBottom.set(DoubleSolenoid.Value.kReverse);
    }
    public void CompressorOff(){
        m_compressor.disable();
    }
    public void CompressorOn(){
        m_compressor.enableDigital();
    }


            

    
}
