package frc.robot.auton;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import frc.robot.Robot;
import frc.robot.subsystems.mecDrive;

public class playAuton {
    Scanner scanner;
    long startTime;

    boolean onTime = true;

    double nextDouble;

    public playAuton() throws FileNotFoundException{
        scanner = new Scanner(new File(Robot.autoFile));
        scanner.useDelimiter(",\\n");

        startTime = System.currentTimeMillis();
    }

    public void play(){
        if((scanner != null) && (scanner.hasNextDouble())){
            // Change in timing across replay to stop rushing/dragging in play
            double t_delta;

            if(onTime = true){
                nextDouble = scanner.nextDouble();
            }   
            t_delta = nextDouble - (System.currentTimeMillis() - startTime);

            if(t_delta <= 0){
                /*
                mecDrive.FRD.set(scanner.nextDouble());
                mecDrive.BRD.set(scanner.nextDouble());
                mecDrive.BLD.set(scanner.nextDouble());
                mecDrive.FLD.set(scanner.nextDouble());
                onTime = true;*/
            }
            else{ onTime = false; }
        }
        else{
            if(scanner != null){
                scanner.close();
                scanner = null;
            }
        }
    }
    public void end(){
        /*mecDrive.FRD.set(0.0);
        mecDrive.BRD.set(0.0);
        mecDrive.BLD.set(0.0);
        mecDrive.FLD.set(0.0);*/

        if(scanner != null){
            scanner.close();
        }
    }
}
