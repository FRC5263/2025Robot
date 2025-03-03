package frc.robot.auton;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.InputMismatchException;
import java.util.Scanner;

import frc.robot.subsystems.mecDrive;



public class playAuton{
    public Scanner scanner;
    long startTime;
    boolean onTime = true;
    double nextDouble;

    public playAuton() throws FileNotFoundException{
        scanner = new Scanner(new FileReader("C:\\Users\\Robotics\\Documents\\2025Robot\\src\\main\\java\\frc\\robot\\auton\\auton1.csv"));
        scanner.useDelimiter(",");
        startTime = System.currentTimeMillis();
    }
    public void play() throws InputMismatchException{
        if(scanner != null && scanner.hasNextDouble()){
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
                onTime = true;
            }
            else{onTime = false;}
        }
        else{
            if(scanner != null){
                scanner.close();
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