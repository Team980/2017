package com.team980.robot2017;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {

    private Joystick driveStick;
    private Joystick driveWheel;
    private CustomDrive drive;

    private SendableChooser<Command> autoChooser;

    @Override
    public void robotInit() {
        drive = new CustomDrive();

        driveStick = new Joystick(Parameters.DRIVE_JOYSTICK_ID);
        driveWheel = new Joystick(Parameters.DRIVE_WHEEL_ID);

        autoChooser = new SendableChooser<>();
        autoChooser.addObject("None", null);
        SmartDashboard.putData("Autonomous mode chooser", autoChooser);
    }

    @Override
    public void autonomousInit() {
        drive.setHighGear(false);
        Command autoCommand = autoChooser.getSelected();
        if (autoCommand !=  null) {
            System.out.println("Starting autonomous " + autoCommand.getName());
            autoCommand.start();
        } else {
            System.out.println("No autonomous selected, idling");
        }
    }

    @Override
    public void autonomousPeriodic() {
        //REQUIRED to run scheduled auto command
        Scheduler.getInstance().run();
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
