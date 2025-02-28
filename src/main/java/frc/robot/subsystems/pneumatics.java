// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;

import static edu.wpi.first.units.Units.Value;

import edu.wpi.first.wpilibj.Compressor;

/*
*  Good work Mallek; this code is taken almost directly from your previous commits with minor changes.
*  You had some good ideas as to what was going on (worlds better than when I started,) and your problem 
*  seemed to be actually linking the parts Don't sweat it though, I struggled with that on the drivetrain.
*  OOJ programming's a bitch but when you come to understand it it works pretty damn well
*/

public class pneumatics extends SubsystemBase {
  /** Creates a new pneumatics. */
  public static DoubleSolenoid topSolenoid;
  public static DoubleSolenoid bottomSolenoid;
  public static Compressor compressor;
  public boolean switchPressure;

  public pneumatics() {
    topSolenoid = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, 0, 1);
    bottomSolenoid = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, 2, 3);
    compressor = new Compressor(PneumaticsModuleType.CTREPCM);
  }

  @Override
  public void periodic() {
    switchPressure = compressor.getPressureSwitchValue();   // Why does this return a boolean
    if(switchPressure == true){
      compressor.disable();
    }
    else{
      compressorOn();
    }
  }
  public void compressorUpTop(){
    topSolenoid.set(DoubleSolenoid.Value.kForward);
  }
  public void compressorUpBottom(){
    bottomSolenoid.set(DoubleSolenoid.Value.kForward);
  }
  public void compressorDownTop(){
    topSolenoid.set(DoubleSolenoid.Value.kReverse);
  }
  public void compressorDownBottom(){
    bottomSolenoid.set(DoubleSolenoid.Value.kReverse);
  }
  public void compressorOff(){
    compressor.disable();
  }
  public void compressorOn(){
    compressor.enableDigital();
  }
}
