package frc.robot.auton;
import java.io.FileWriter;
import java.io.IOException;

import frc.robot.RobotContainer;
import frc.robot.subsystems.mecDrive;


public class recordAuton {
    FileWriter recorder;

    

    long startTime;

    public recordAuton() throws IOException{
        startTime = System.currentTimeMillis();

        recorder = new FileWriter(RobotContainer.autoFile);
    }

    public void record() throws IOException{
        // Begin recording frames
        if(recorder != null){
            recorder.append("" + (System.currentTimeMillis() - startTime));
        }

        recorder.append("," + mecDrive.frontRight.get());
        recorder.append("," + mecDrive.rearRight.get());
        recorder.append("," + mecDrive.rearLeft.get());
        recorder.append("," + mecDrive.frontLeft.get());
    }

    public void end() throws IOException{
        if(recorder != null){
            recorder.flush();
            recorder.close();
        }
    }
}
