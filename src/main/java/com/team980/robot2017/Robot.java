package com.team980.robot2017;

import com.team980.robot2017.autonomous.SimpleBaselineCross;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {

    private Joystick driveStick;
    private Joystick driveWheel;
    private Joystick operatorBox;

    private CustomDrive drive;

    private SendableChooser<Command> autoChooser;

    private NetworkTable table;

    @Override
    public void robotInit() {
        drive = new CustomDrive();

        driveStick = new Joystick(Parameters.DRIVE_JOYSTICK_ID);
        driveWheel = new Joystick(Parameters.DRIVE_WHEEL_ID);
        operatorBox = new Joystick(Parameters.OPERATOR_BOX_ID);

        autoChooser = new SendableChooser<>();
        autoChooser.addObject("None", null);
        autoChooser.addObject("Simple Baseline Cross", new SimpleBaselineCross(drive));
        //autoChooser.addObject("Front Gear Placement", new FrontGearPlace(drive));
        //autoChooser.addObject("Left Gear Placement", new SideGearPlace(drive, SideGearPlace.AirshipSide.LEFT));
        //autoChooser.addObject("Right Gear Placement", new SideGearPlace(drive, SideGearPlace.AirshipSide.RIGHT));

        SmartDashboard.putData("Autonomous mode chooser", autoChooser);
    }

    @Override
    public void autonomousInit() {
        drive.setHighGear(false);

        Command autoCommand = autoChooser.getSelected();
        if (autoCommand != null) {
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

        drive.getLeftDriveEncoder().reset();
        drive.getRightDriveEncoder().reset();

        table = NetworkTable.getTable("Encoders");
    }

    @Override
    public void teleopPeriodic() {
        drive.drive(driveStick, driveWheel);

        table.putNumber("leftScaled", drive.getLeftDriveEncoder().getRate());
        table.putNumber("rightScaled", drive.getRightDriveEncoder().getRate());

        table.putNumber("leftRaw", drive.getLeftDriveEncoder().getRaw());
        table.putNumber("rightRaw", drive.getRightDriveEncoder().getRaw());

    }

    @Override
    public void disabledInit() {

    }
}
