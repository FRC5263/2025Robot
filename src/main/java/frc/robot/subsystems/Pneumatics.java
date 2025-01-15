//Important to the change of the code: https://docs.wpilib.org/en/stable/docs/hardware/hardware-basics/wiring-pneumatics-ph.html (mostly for the pressure sensor.)
package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;


public class Pneumatics extends SubsystemBase {
    public static DoubleSolenoid TOP_PUMP;
    public static DoubleSolenoid BOTTOM_PUMP;
    public static Compressor COMPRESSOR;
    public boolean pressureSwitch;
      public Pneumatics(){
        TOP_PUMP = new DoubleSolenoid(PneumaticsModuleType.REVPH, 0, 0);
        BOTTOM_PUMP = new DoubleSolenoid(PneumaticsModuleType.REVPH, 0, 0);
        COMPRESSOR = new Compressor(PneumaticsModuleType.REVPH);
      }
        
            public void periodic(){
                pressureSwitch = COMPRESSOR.getPressureSwitchValue();
                    if(pressureSwitch) {
                        COMPRESSOR.disable();


                    }
                    else {
                        //this MAY be changed later depending on which we want to
                        COMPRESSOR.enableDigital();

                    }
            }
            public void initDefaultCommand(){}
            //Random stuff we might not need:
    public void pitchUpTop(){
        TOP_PUMP.set(DoubleSolenoid.Value.kForward);
    }
    public void pitchUpBottom(){
        BOTTOM_PUMP.set(DoubleSolenoid.Value.kForward);
    }
    public void pitchDownTop(){
        TOP_PUMP.set(DoubleSolenoid.Value.kReverse);
    }
    public void pitchDownBottom(){
        BOTTOM_PUMP.set(DoubleSolenoid.Value.kReverse);
    }
    public void CompressorOff(){
        COMPRESSOR.disable();
    }
    public void CompressorOn(){
        COMPRESSOR.enableDigital();
    }


            

    
}
