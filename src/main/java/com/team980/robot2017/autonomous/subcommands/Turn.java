package com.team980.robot2017.autonomous.subcommands;

import com.ctre.PigeonImu;
import com.team980.robot2017.CustomDrive;
import com.team980.robot2017.Parameters;
import edu.wpi.first.wpilibj.command.Command;

/**
 * Created by Team 980 on 3/20/2017.
 */
public class Turn extends Command {

    //TODO: figure out distance from degrees
    private CustomDrive drive;

    private double turnDegree;
    private double distance;

    private boolean isFinished = false;

    //Handles parameters
    public Turn(CustomDrive drive, double degrees) {
        super("Turn");
        distance = 0.78;
        this.drive = drive;
        turnDegree = degrees;
    }

    //Called immediately before start, initialize things here
    @Override
    protected void initialize() {
        drive.getLeftDriveEncoder().reset();
        drive.getRightDriveEncoder().reset();
    }

    //Called periodically (20ms) - do work here
    @Override
    protected void execute() {
        double multiplier = -1.0;
        if(turnDegree>0){
            multiplier = 1.0;
        }
        if(distance>0){
            if(Math.abs(drive.getLeftDriveEncoder().getDistance())>distance && Math.abs(drive.getRightDriveEncoder().getDistance())>distance){
                drive.setLeftRightMotorSetpoints(0,0);
                isFinished = true;
            }
            else{
                drive.getLeftSpeedController().set(Parameters.AUTO_SPEED*multiplier);
                drive.getRightSpeedController().set(Parameters.AUTO_SPEED*multiplier*-1.0);
            }
        }

    }

    // This should return true when the command is finished
    @Override
    protected boolean isFinished() {
        return isFinished;
    }

}
