package frc.robot.auton;
import java.io.FileWriter;
import java.io.IOException;

import frc.robot.Robot;
import frc.robot.subsystems.mecDrive;


public class recordAuton {
    FileWriter recorder;

    

    long startTime;

    public recordAuton() throws IOException{
        startTime = System.currentTimeMillis();

        recorder = new FileWriter(Robot.autoFile);
    }

    public void record() throws IOException{
        // Begin recording frames
        if(recorder != null){
            recorder.append("" + (System.currentTimeMillis() - startTime));
        }

        recorder.append("," + mecDrive.FRD.get());
        recorder.append("," + mecDrive.BRD.get());
        recorder.append("," + mecDrive.BLD.get());
        recorder.append("," + mecDrive.FLD.get());
    }

    public void end() throws IOException{
        if(recorder != null){
            recorder.flush();
            recorder.close();
        }
    }
}
