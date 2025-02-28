package frc.robot.auton;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import frc.robot.RobotContainer;
import frc.robot.subsystems.mecDrive;

public class playAuton {
    Scanner scanner;
    long startTime;

    boolean onTime = true;

    double nextDouble;

    public playAuton() throws FileNotFoundException{
            scanner = new Scanner(new File(RobotContainer.autoFile));
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
                mecDrive.frontRight.set(scanner.nextDouble());
                mecDrive.rearRight.set(scanner.nextDouble());
                mecDrive.rearLeft.set(scanner.nextDouble());
                mecDrive.frontLeft.set(scanner.nextDouble());
                onTime = true;
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
        mecDrive.frontRight.set(0.0);
        mecDrive.rearRight.set(0.0);
        mecDrive.rearLeft.set(0.0);
        mecDrive.frontLeft.set(0.0);

        if(scanner != null){
            scanner.close();
        }
    }
}
