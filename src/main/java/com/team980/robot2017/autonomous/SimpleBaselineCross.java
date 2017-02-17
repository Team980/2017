package com.team980.robot2017.autonomous;

import com.team980.robot2017.CustomDrive;
import com.team980.robot2017.Parameters;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Command;

public class SimpleBaselineCross extends Command {

    CustomDrive drive;
    Encoder leftDriveEncoder;
    Encoder rightDriveEncoder;

    boolean isFinished = false;

    //Handles parameters
    public SimpleBaselineCross(CustomDrive drive) {
        super("SimpleBaselineCross");

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
    protected void execute() {

        if (leftDriveEncoder.getDistance() > Parameters.AUTO_DISTANCE_BASELINE_CROSS
                && rightDriveEncoder.getDistance() > Parameters.AUTO_DISTANCE_BASELINE_CROSS) {
            drive.setLeftRightMotorSetpoints(0, 0); //Stop driving
            isFinished = true;
        } else {
            drive.setLeftRightMotorSetpoints(Parameters.AUTO_LEFT_SETPOINT, Parameters.AUTO_RIGHT_SETPOINT);
        }
    }

    // This should return true when the command is finished
    @Override
    protected boolean isFinished() {
        return isFinished;
    }
}
