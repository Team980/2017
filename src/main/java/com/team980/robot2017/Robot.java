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

        driveStick = new Joystick(Parameters.DRIVE_JOYSTICK_ID);
        driveWheel = new Joystick(Parameters.DRIVE_WHEEL_ID);
    }

    @Override
    public void autonomousInit() {
        drive.setHighGear(false);
    }

    @Override
    public void autonomousPeriodic() {

    }

    @Override
    public void teleopInit() {
        drive.setHighGear(false);
    }

    @Override
    public void teleopPeriodic() {
        drive.drive(driveStick, driveWheel);
    }

    @Override
    public void disabledInit() {

    }
}
