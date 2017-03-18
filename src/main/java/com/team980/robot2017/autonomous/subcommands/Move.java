package com.team980.robot2017.autonomous.subcommands;

import com.team980.robot2017.CustomDrive;
import com.team980.robot2017.Parameters;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Command;

public class Move extends Command {

    private CustomDrive drive;
    private Encoder leftDriveEncoder;
    private Encoder rightDriveEncoder;

    private double distance; //in feet

    private boolean isFinished = false;

    //Handles parameters
    public Move(CustomDrive drive, double distance) {
        super("Move");

        this.drive = drive;
        leftDriveEncoder = drive.getLeftDriveEncoder();
        rightDriveEncoder = drive.getRightDriveEncoder();

        this.distance = distance;
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

        if (distance > 0) { //Positive distance
            if (leftDriveEncoder.getDistance() > distance && rightDriveEncoder.getDistance() > distance) {
                drive.setLeftRightMotorSetpoints(0, 0); //Stop driving
                isFinished = true;
            } else {
                drive.setLeftRightMotorSetpoints(Parameters.AUTO_SPEED, Parameters.AUTO_SPEED);
            }
        } else { //Negative distance
            if (leftDriveEncoder.getDistance() < distance && rightDriveEncoder.getDistance() < distance) {
                drive.setLeftRightMotorSetpoints(0, 0); //Stop driving
                isFinished = true;
            } else {
                drive.setLeftRightMotorSetpoints(-Parameters.AUTO_SPEED, -Parameters.AUTO_SPEED);
            }
        }
    }

    // This should return true when the command is finished
    @Override
    protected boolean isFinished() {
        return isFinished;
    }
}
