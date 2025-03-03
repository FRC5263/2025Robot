package frc.robot.auton;
import java.io.FileWriter;
import java.io.IOException;

import frc.robot.subsystems.mecDrive;

/* Auton begins here
 * 
 * This creates a file write system called writer and appends a CSV file with the double values in order
 */

public class recordAuton{
    FileWriter writer;
    long startTime;

    public recordAuton() {
        startTime = System.currentTimeMillis();
        try{
            // TODO: automate file names in constants file
            writer = new FileWriter("auton1.csv", true);
        }
        catch(IOException e){
            System.out.print("Shit's fucked: " + e.getMessage());
        }
    }
    public void record() throws IOException{
        if(writer != null){
            writer.append("" + (System.currentTimeMillis() - startTime));
        }
        writer.append("," + mecDrive.frontRight.get());
        writer.append("," + mecDrive.rearRight.get());
        writer.append("," + mecDrive.rearLeft.get());
        writer.append("," + mecDrive.frontLeft.get());
    }
    public void end() throws IOException{
        if(writer != null){
            writer.flush();
            writer.close();
        }
    }
}
