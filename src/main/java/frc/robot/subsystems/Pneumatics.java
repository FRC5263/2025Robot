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
        TOP_PUMP = new DoubleSolenoid(0, PneumaticsModuleType.CTREPCM, 0, 1);
        BOTTOM_PUMP = new DoubleSolenoid(PneumaticsModuleType.CTREPCM,2,3);
        COMPRESSOR = new Compressor(PneumaticsModuleType.CTREPCM);
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
         
            

            

    
}
