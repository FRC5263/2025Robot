package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.clawSubsystem;
public class clawCommand extends Command {
    public double clawSpeed;
    public clawSubsystem claw;
    public clawCommand(double clawSpeed, clawSubsystem claw){
        this.claw = claw;
        this.clawSpeed = clawSpeed;
        addRequirements(this.claw);
    }

    @Override
    public void execute(){
        claw.clawSpeed(-1);
        claw.toggleClaw();
    }
    

    @Override
     public void end(boolean interrupted){
       claw.clawSpeed(0);
    }
        

public boolean isFinished(){
    return false;
}
}
