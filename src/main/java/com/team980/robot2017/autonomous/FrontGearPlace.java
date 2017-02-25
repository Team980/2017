package com.team980.robot2017.autonomous;

import com.team980.robot2017.CustomDrive;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Command;

public class FrontGearPlace extends Command {

    CustomDrive drive;
    Encoder leftDriveEncoder;
    Encoder rightDriveEncoder;

    boolean isFinished = false;

    //Handles parameters
    public FrontGearPlace(CustomDrive drive) {
        super("FrontGearPlace");

        this.drive = drive;
        leftDriveEncoder = drive.getLeftDriveEncoder();
        rightDriveEncoder = drive.getRightDriveEncoder();
    }

    //Called immediately before start, initialize things here
    @Override
    protected void initialize() {
        leftDriveEncoder.reset();
        rightDriveEncoder.reset();
    }

    //Called periodically (20ms) - do work here
    @Override
    protected void execute() { //TODO

        /*if (leftDriveEncoder.getDistance() > Parameters.AUTO_DISTANCE_BASELINE_CROSS
                && rightDriveEncoder.getDistance() > Parameters.AUTO_DISTANCE_BASELINE_CROSS) {
            drive.setLeftRightMotorSetpoints(0, 0); //Stop driving
            isFinished = true;
        } else {
            drive.setLeftRightMotorSetpoints(Parameters.AUTO_SETPOINT, Parameters.AUTO_SETPOINT);
        }*/
    }

    // This should return true when the command is finished
    @Override
    protected boolean isFinished() {
        return isFinished;
    }
}
