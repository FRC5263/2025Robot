package frc.robot.auton;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.InputMismatchException;
import java.util.Scanner;

import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.mecDrive;


/* 2nd step in auton method
 * 
 * This opens up the CSV file with a scanner and sets the motor values (bulk of logic goes here)
 * 
 */


public class playAuton extends Command {
    public Scanner scanner;
    long startTime;
    boolean onTime = true;
    MecanumDrive m_drive;
    double nextDouble;

    public playAuton() throws FileNotFoundException, NullPointerException{
        try (Scanner scanner = new Scanner(new FileReader("/home/lvuser/frc/auton1.csv"))) {
            scanner.useDelimiter(",");
        }
        catch(Exception e){ System.out.println("shit's fucked"); e.printStackTrace(); }
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
                mecDrive.drive(scanner.nextDouble(), scanner.nextDouble(), scanner.nextDouble());
                onTime = true;
            }
            else{onTime = false;  System.out.println("Robot not on time!");   }
        }
        else{
            if(scanner != null){
                scanner.close();               
                 end();
            }
        }
    }
    public void end(){
        mecDrive.drive(0.0, 0.0, 0.0);

        if(scanner != null){
            scanner.close();
        }
    }
}