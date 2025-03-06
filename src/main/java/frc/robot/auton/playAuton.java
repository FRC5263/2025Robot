package frc.robot.auton;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.InputMismatchException;
import java.util.Scanner;

import edu.wpi.first.wpilibj.drive.MecanumDrive;
import frc.robot.subsystems.mecDrive;


/* 2nd step in auton method
 * 
 * This opens up the CSV file with a scanner and sets the motor values (bulk of logic goes here)
 * 
 */


public class playAuton{
    public Scanner scanner;
    long startTime;
    boolean onTime = true;
    MecanumDrive m_drive;
    double nextDouble;

    public playAuton() throws FileNotFoundException{
        scanner = new Scanner(new FileReader("auton1.csv"));
        scanner.useDelimiter(",");
        startTime = System.currentTimeMillis();
    }
    public void play() throws InputMismatchException{
        if(scanner != null && scanner.hasNextDouble()){

            /* 
             * Delta time is important here; it subtracts the time from the start of the auton recording and the current system time
             * If the measured time is above a millisecond it'll start lagging
             * On case of not being on time, the system self-desructs (closes scanner and ends)
             */

            double deltaTime;

            if(onTime = true){
                nextDouble = scanner.nextDouble();
            }
            deltaTime = nextDouble - (System.currentTimeMillis() - startTime);
            if(deltaTime <= 0){
                mecDrive.frontRight.set(scanner.nextDouble());
                mecDrive.rearRight.set(scanner.nextDouble());
                mecDrive.rearLeft.set(scanner.nextDouble());
                mecDrive.frontLeft.set(scanner.nextDouble());
                m_drive.feed();
                onTime = true;
            }
            else{onTime = false;}
        }
        else{
            if(scanner != null){
                scanner.close();
                end();
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