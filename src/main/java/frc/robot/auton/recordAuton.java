package frc.robot.auton;
import java.io.FileWriter;
import java.io.IOException;

import frc.robot.subsystems.mecDrive;


public class recordAuton{
    FileWriter writer;
    long startTime;

    public recordAuton() {
        startTime = System.currentTimeMillis();
        try{
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
