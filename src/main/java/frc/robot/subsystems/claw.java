package frc.robot.subsystems;

import frc.robot.Constants;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.SparkMax;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class claw extends SubsystemBase {
    public static SparkMax Wrist;
    public static SparkMax intake_coral;
    public static SparkMax intake_alg;
    private SparkMaxConfig invertConfig;
    private SparkMaxConfig notinvertConfig;
    
    public claw(){
    
        Wrist = new SparkMax(Constants.WristID, MotorType.kBrushless);
        intake_coral = new SparkMax(Constants.intake1_ID, MotorType.kBrushless);
        intake_alg = new SparkMax(Constants.intake2_ID, MotorType.kBrushless);
        invertConfig = new SparkMaxConfig();
        notinvertConfig = new SparkMaxConfig();

   
        notinvertConfig.inverted(false);
        
 

    }

   //this method is terrible but i kinda have to since i hate clutter specificly in TeleOp
    public void Wristinvert(){ Wrist.configure(invertConfig, null, null); }
    public void Alginvert(){intake_alg.configure(invertConfig, null, null);}
    public void Coralinvert(){intake_coral.configure(invertConfig, null, null);}
    public void deWristinvert(){  Wrist.configure(notinvertConfig, null, null);}
    public void deAlginvert(){intake_alg.configure(notinvertConfig, null, null);}
    public void deCoralinvert(){intake_coral.configure(notinvertConfig, null, null);}
    public void Algstop(){intake_alg.set(0);}
    public void Coralstop(){intake_coral.set(0);}

}

