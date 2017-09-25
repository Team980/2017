package com.team980.robot2017;

import com.ctre.MotorControl.CANTalon;
import com.team980.robot2017.autonomous.FrontGearPlace;
import com.team980.robot2017.autonomous.SideGearPlace;
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

import static com.team980.robot2017.autonomous.SideGearPlace.Position.RED_ALLIANCE_LEFT;

public class Robot extends IterativeRobot {

    private Joystick driveStick;
    private Joystick driveWheel;
    private Joystick operatorBox;

    private CustomDrive drive;

    private Solenoid climberSolenoid;
    private Solenoid gearTiltSolenoid;

    private CANTalon intakeMotor;
    private CANTalon outputMotor;

    //private PigeonImu imu;

    private SendableChooser<Command> autoChooser;

    @Override
    public void robotInit() {
        drive = new CustomDrive();

        climberSolenoid = new Solenoid(Parameters.PCM_CAN_ID, Parameters.CLIMBER_SOLENOID_CHANNEL);
        climberSolenoid.set(false);

        gearTiltSolenoid = new Solenoid(Parameters.PCM_CAN_ID, Parameters.GEAR_SOLENOID_CHANNEL);
        gearTiltSolenoid.set(false);

        intakeMotor = new CANTalon(Parameters.INTAKE_MOTOR_CAN_ID);
        outputMotor = new CANTalon(Parameters.OUTPUT_MOTOR_CAN_ID);

        //imu = new PigeonImu(Parameters.IMU_CAN_ID);

        driveStick = new Joystick(Parameters.DRIVE_JOYSTICK_ID);
        driveWheel = new Joystick(Parameters.DRIVE_WHEEL_ID);
        operatorBox = new Joystick(Parameters.OPERATOR_BOX_ID);

        autoChooser = new SendableChooser<>(); //TODO don't instantiate CommandGroups until runtime
        autoChooser.addObject("0 - None", null);
        autoChooser.addObject("1 - Simple Baseline Cross", new SimpleBaselineCross(drive));
        autoChooser.addObject("2 - Front Gear Place", new FrontGearPlace(drive));
        autoChooser.addObject("3 - Side Gear Place - Red Alliance Left",
                new SideGearPlace(drive, SideGearPlace.Position.RED_ALLIANCE_LEFT));
        autoChooser.addObject("4 - Side Gear Place - Red Alliance Right",
                new SideGearPlace(drive, SideGearPlace.Position.RED_ALLIANCE_RIGHT));
        autoChooser.addObject("5 - Side Gear Place - Blue Alliance Left",
                new SideGearPlace(drive, SideGearPlace.Position.BLUE_ALLIANCE_LEFT));
        autoChooser.addObject("6 - Side Gear Place - Blue Alliance Right",
                new SideGearPlace(drive, SideGearPlace.Position.BLUE_ALLIANCE_RIGHT));
        SmartDashboard.putData("AutoChooser", autoChooser);


        UsbCamera videoSource0 = CameraServer.getInstance().startAutomaticCapture(0);
        videoSource0.setExposureManual(50);
    }

    @Override
    public void autonomousInit() {
        drive.setHighGear(false);

        drive.getLeftDriveEncoder().reset();
        drive.getRightDriveEncoder().reset();

        climberSolenoid.set(false);
        gearTiltSolenoid.set(false);

        SideGearPlace.Position position = RED_ALLIANCE_LEFT;
        //RED_ALLIANCE_RIGHT;
        //BLUE_ALLIANCE_LEFT;
        //BLUE_ALLIANCE_RIGHT;

        Command autoCommand;
        //autoCommand = new SimpleBaselineCross(drive);
        //autoCommand = new SideGearPlace(drive, position);
        autoCommand = autoChooser.getSelected();

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

        printToNetworkTables();
    }

    @Override
    public void teleopInit() {
        drive.setHighGear(false);

        climberSolenoid.set(false);
        gearTiltSolenoid.set(false);

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
        if (operatorBox.getRawButton(1)) { //Big red switch set to ON position
            climberSolenoid.set(true); //Trigger the solenoid used for the climber
        } else {
            climberSolenoid.set(false);
        }

        //GEAR TILT AND LATCH
        if (operatorBox.getRawButton(7)) {
            gearTiltSolenoid.set(true); //Tilt the gear holder and close the latch
        } else {
            gearTiltSolenoid.set(false); //Retract gear holder and open the latch
        }

        printToNetworkTables();
    }

    @Override
    public void disabledInit() {
        drive.setLeftRightMotorSetpoints(0, 0); //Stop driving
        Scheduler.getInstance().removeAll();
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
