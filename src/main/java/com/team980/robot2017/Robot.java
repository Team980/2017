package com.team980.robot2017;

import com.ctre.CANTalon;
import com.team980.robot2017.autonomous.SimpleBaselineCross;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Solenoid;
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

    private Solenoid climberSolenoid;
    private CANTalon intakeMotor;
    private CANTalon outputMotor;

    //private PigeonImu imu;

    private SendableChooser<Command> autoChooser;

    @Override
    public void robotInit() {
        drive = new CustomDrive();

        climberSolenoid = new Solenoid(Parameters.PCM_CAN_ID, Parameters.CLIMBER_SOLENOID_CHANNEL);
        climberSolenoid.set(false);

        intakeMotor = new CANTalon(Parameters.INTAKE_MOTOR_CAN_ID);
        outputMotor = new CANTalon(Parameters.OUTPUT_MOTOR_CAN_ID);

        //imu = new PigeonImu(Parameters.IMU_CAN_ID);

        driveStick = new Joystick(Parameters.DRIVE_JOYSTICK_ID);
        driveWheel = new Joystick(Parameters.DRIVE_WHEEL_ID);
        operatorBox = new Joystick(Parameters.OPERATOR_BOX_ID);

        /*
        autoChooser = new SendableChooser<>();
        autoChooser.addObject("None", null);
        autoChooser.addObject("Simple Baseline Cross", new SimpleBaselineCross(drive));
        autoChooser.addObject("Front Gear Place", new FrontGearPlace(drive));
        autoChooser.addObject("Side Gear Place - Red Alliance Left",
                new SideGearPlace(drive, SideGearPlace.Position.RED_ALLIANCE_LEFT));
        autoChooser.addObject("Side Gear Place - Red Alliance Right",
                new SideGearPlace(drive, SideGearPlace.Position.RED_ALLIANCE_RIGHT));
        autoChooser.addObject("Side Gear Place - Blue Alliance Left",
                new SideGearPlace(drive, SideGearPlace.Position.BLUE_ALLIANCE_LEFT));
        autoChooser.addObject("Side Gear Place - Blue Alliance Right",
                new SideGearPlace(drive, SideGearPlace.Position.BLUE_ALLIANCE_RIGHT));
        SmartDashboard.putData("Autonomous mode chooser", autoChooser);
        */

        UsbCamera videoSource0 = CameraServer.getInstance().startAutomaticCapture(0);
        videoSource0.setExposureManual(50);
    }

    @Override
    public void autonomousInit() {
        drive.setHighGear(false);

        drive.getLeftDriveEncoder().reset();
        drive.getRightDriveEncoder().reset();

        Command autoCommand = new SimpleBaselineCross(drive);
        if (autoCommand != null) {
            System.out.println("Starting au5onomous " + autoCommand.getName());
            autoCommand.start();
        } else {
            System.out.println("No autonomous selected, idling");
        }
    }

    @Override
    public void autonomousPeriodic() {
        //REQUIRED to run scheduled auto command
        Scheduler.getInstance().run();

        printToNetworkTables();
    }

    @Override
    public void teleopInit() {
        drive.setHighGear(false);

        climberSolenoid.set(false);

        drive.getLeftDriveEncoder().reset();
        drive.getRightDriveEncoder().reset();
    }

    @Override
    public void teleopPeriodic() {

        //DRIVE
        drive.drive(driveStick, driveWheel);

        //FUEL PICKUP
        if (operatorBox.getRawButton(5)) { //INTAKE FORWARD
            intakeMotor.set(-Parameters.INTAKE_MOTOR_SPEED);
        } else if (operatorBox.getRawButton(6)) { //INTAKE REVERSE
            intakeMotor.set(-Parameters.INTAKE_MOTOR_SPEED);
        } else {
            intakeMotor.set(0.0);
        }

        if (operatorBox.getRawButton(2)) { //OUTPUT FORWARD
            if (operatorBox.getRawButton(4)) { //SLOW OUTPUT
                outputMotor.set(Parameters.OUTPUT_SLOW_MULTIPLIER * Parameters.OUTPUT_MOTOR_SPEED);
            } else {
                outputMotor.set(Parameters.OUTPUT_MOTOR_SPEED);
            }
        } else if (operatorBox.getRawButton(3)) { //OUTPUT REVERSE
            if (operatorBox.getRawButton(4)) { //SLOW OUTPUT
                outputMotor.set(Parameters.OUTPUT_SLOW_MULTIPLIER * -Parameters.OUTPUT_MOTOR_SPEED);
            } else {
                outputMotor.set(-Parameters.OUTPUT_MOTOR_SPEED);
            }
        } else {
            outputMotor.set(0.0);
        }

        //CLIMBER
        //TODO: Uncomment when ready to use climber
        if (operatorBox.getRawButton(1)) { //Big red switch set to ON position
            climberSolenoid.set(true); //Trigger the solenoid used for the climber
        }
        else{
            climberSolenoid.set(false);
        }


        printToNetworkTables();
    }

    @Override
    public void disabledInit() {

    }

    private void printToNetworkTables() {
        NetworkTable table = NetworkTable.getTable("Encoders");

        //NETWORK TABLES
        table.putNumber("leftCounts", drive.getLeftDriveEncoder().get());
        table.putNumber("rightCounts", drive.getRightDriveEncoder().get());

        table.putNumber("leftRate", drive.getLeftDriveEncoder().getRate());
        table.putNumber("rightRate", drive.getRightDriveEncoder().getRate());

        table.putNumber("leftDistance", drive.getLeftDriveEncoder().getDistance());
        table.putNumber("rightDistance", drive.getRightDriveEncoder().getDistance());
    }
}
