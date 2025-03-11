package frc.robot.subsystems;

import frc.robot.Constants;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.SparkMax;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class intake extends SubsystemBase {
    public static SparkMax Intake;
    private SparkMaxConfig invertConfig;
    private SparkMaxConfig notinvertConfig;
    
    public intake(){
    
        Intake = new SparkMax(Constants.WristID,MotorType.kBrushless);
        invertConfig = new SparkMaxConfig();
        notinvertConfig = new SparkMaxConfig();
 

   
        notinvertConfig.inverted(false);
        
 

    }

   //this method is terrible but i kinda have to since i hate clutter specificly in TeleOp
    public void invert(){ Intake.configure(invertConfig, null, null);};
    public void falseinvert(){ Intake.configure(notinvertConfig, null, null);}
   

}

