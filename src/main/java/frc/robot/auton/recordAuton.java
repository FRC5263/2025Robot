package frc.robot.auton;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.mecDrive;

/* Auton begins here
 * 
 * This creates a file write system called writer and appends a CSV file with the double values in order
 */

public class recordAuton extends Command{
    FileWriter writer;
    long startTime;
    static File file;

    public recordAuton() {}
    public void record() throws IOException{
        startTime = System.currentTimeMillis();
            file = new File(Constants.pathName);
            writer = new FileWriter(file, true);
        
        writer.append("" + (System.currentTimeMillis() - startTime));
        
        writer.append("," + mecDrive.getYspeed());
        writer.append("," + mecDrive.getXspeed());
        writer.append("," + mecDrive.getZspeed());
    }
    public void end() throws IOException{
        if(writer != null){
            writer.flush();
            writer.close();
        }
    }
}
