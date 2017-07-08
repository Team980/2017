package com.team980.robot2017.autonomous.subcommands;

import com.team980.robot2017.CustomDrive;
import edu.wpi.first.wpilibj.command.Command;

public class Wait extends Command {

    private CustomDrive drive;
    private long startTime;
    private long waitTime;

    private boolean isFinished = false;

    //Handles parameters
    public Wait(CustomDrive drive, long waitTime) {
        super("Wait");
        this.drive = drive;
        this.waitTime = waitTime;
    }

    //Called immediately before start, initialize things here
    @Override
    protected void initialize() {
        startTime = System.currentTimeMillis();
        drive.setLeftRightMotorSetpoints(0,0);

        System.out.println("Wait command, time = " + waitTime);
    }

    //Called periodically (20ms) - do work here
    @Override
    protected void execute() {
        if(System.currentTimeMillis()-startTime >= waitTime)
        {
            isFinished = true;
        }
    }

    // This should return true when the command is finished
    @Override
    protected boolean isFinished() {
        return isFinished;
    }

}
