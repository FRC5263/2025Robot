package frc.robot.auton;
import java.io.File;
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
    File file;

    public recordAuton() {}
    public void record() throws IOException{
        startTime = System.currentTimeMillis();
            file = new File("C:\\Users\\Robotics\\Documents\\2025Robot\\src\\main\\java\\frc\\robot\\auton\\auton1.csv");
            // TODO: automate file names in constants file
            writer = new FileWriter("C:\\Users\\Robotics\\Documents\\2025Robot\\src\\main\\java\\frc\\robot\\auton\\auton1.csv", true);
        
        writer.append("" + (System.currentTimeMillis() - startTime));
        
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
