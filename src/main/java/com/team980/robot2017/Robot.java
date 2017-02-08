package com.team980.robot2017;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;

public class Robot extends IterativeRobot {

    private Joystick driveStick;
    private Joystick driveWheel;
    private CustomDrive drive;

    @Override
    public void robotInit() {
        drive = new CustomDrive();

        driveStick = new Joystick(Parameters.driveJs);
        driveWheel = new Joystick(Parameters.driveWheel);
    }

    @Override
    public void autonomousInit() {

    }

    @Override
    public void autonomousPeriodic() {

    }

    @Override
    public void teleopInit() {

    }

    @Override
    public void teleopPeriodic() {
        drive.drive(driveStick, driveWheel);
    }

    @Override
    public void disabledInit() {

    }
}
