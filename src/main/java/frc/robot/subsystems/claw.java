package frc.robot.subsystems;


import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class claw extends SubsystemBase {
    public static SparkMax Wrist;
    public static SparkMax intake1;
    public static SparkMax intake2;
    
    public claw(){
    //mota
        Wrist = new SparkMax(Constants.WristID, MotorType.kBrushless);
        intake1 = new SparkMax(Constants.intake1_ID, MotorType.kBrushless);
        intake2 = new SparkMax(Constants.intake2_ID, MotorType.kBrushless);

    }
}
