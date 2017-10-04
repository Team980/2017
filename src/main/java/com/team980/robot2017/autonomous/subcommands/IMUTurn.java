package com.team980.robot2017.autonomous.subcommands;

import com.ctre.PigeonImu;
import com.team980.robot2017.CustomDrive;
import com.team980.robot2017.Parameters;
import edu.wpi.first.wpilibj.command.Command;

public class IMUTurn extends Command {

    private CustomDrive drive;
    private PigeonImu imu;

    private double turnDegree;

    private boolean isFinished = false;

    //Handles parameters
    public IMUTurn(CustomDrive drive, PigeonImu imu, double degrees) {
        super("IMUTurn");

        this.drive = drive;
        this.imu = imu;

        this.turnDegree = degrees;
    }

    //Called immediately before start, initialize things here
    @Override
    protected void initialize() {
        imu.SetYaw(0); //reset

        double[] ypr = new double[3];
        imu.GetYawPitchRoll(ypr);

        turnDegree += ypr[0]; //Convert relative to absolute position
    }

    //Called periodically (20ms) - do work here
    @Override
    protected void execute() {

        double[] ypr = new double[3];
        imu.GetYawPitchRoll(ypr);

        if (turnDegree > 0) { //Positive degree
            if (ypr[0] > turnDegree) {
                drive.setLeftRightMotorSetpoints(0, 0); //Stop driving
                isFinished = true;
            } else {
                drive.setLeftRightMotorSetpoints(-Parameters.AUTO_LEFT_SPEED, Parameters.AUTO_RIGHT_SPEED); //turn left (positive)
            }
        } else {  //Negative degree
            if (ypr[0] < turnDegree) {
                drive.setLeftRightMotorSetpoints(0, 0); //Stop driving
                isFinished = true;
            } else {
                drive.setLeftRightMotorSetpoints(Parameters.AUTO_LEFT_SPEED, -Parameters.AUTO_RIGHT_SPEED); //turn right (negative)
            }
        }
    }

    // This should return true when the command is finished
    @Override
    protected boolean isFinished() {
        return isFinished;
    }
}