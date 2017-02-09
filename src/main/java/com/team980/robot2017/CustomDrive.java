package com.team980.robot2017;

import edu.wpi.first.wpilibj.*;

class CustomDrive {

    private Encoder leftDriveEncoder;
    private Encoder rightDriveEncoder;

    private PIDController leftSidePID;
    private PIDController rightSidePID;

    private DoubleSolenoid shiftSolenoid;

    private boolean inHighGear;

    CustomDrive() {
        SpeedController leftDriveTrain = new Spark(Parameters.LEFT_DRIVE_PWM_CHANNEL);
        SpeedController rightDriveTrain = new Spark(Parameters.RIGHT_DRIVE_PWM_CHANNEL);
        rightDriveTrain.setInverted(true);

        leftDriveEncoder = new Encoder(Parameters.LEFT_ENCODER_CHANNEL_A, Parameters.LEFT_ENCODER_CHANNEL_B, Parameters.INVERT_LEFT_ENCODER, CounterBase.EncodingType.k4X);
        leftDriveEncoder.setDistancePerPulse((2 * (Constants.PI) * (Constants.wheelRadius / 12)) / (Constants.encoderCounts));
        leftDriveEncoder.setPIDSourceType(PIDSourceType.kRate);

        rightDriveEncoder = new Encoder(Parameters.RIGHT_ENCODER_CHANNEL_A, Parameters.RIGHT_ENCODER_CHANNEL_B, Parameters.INVERT_RIGHT_ENCODER, CounterBase.EncodingType.k4X);
        rightDriveEncoder.setDistancePerPulse((2 * (Constants.PI) * (Constants.wheelRadius / 12)) / (Constants.encoderCounts));
        rightDriveEncoder.setPIDSourceType(PIDSourceType.kRate);

        leftSidePID = new PIDController(Parameters.LEFT_PID_P, Parameters.LEFT_PID_I, Parameters.LEFT_PID_D, leftDriveEncoder, leftDriveTrain);
        leftSidePID.setContinuous();
        leftSidePID.setPercentTolerance(Parameters.PID_PERCENT_TOLERANCE);

        rightSidePID = new PIDController(Parameters.RIGHT_PID_P, Parameters.RIGHT_PID_I, Parameters.RIGHT_PID_D, rightDriveEncoder, rightDriveTrain);
        rightSidePID.setContinuous();
        rightSidePID.setPercentTolerance(Parameters.PID_PERCENT_TOLERANCE);

        shiftSolenoid = new DoubleSolenoid(Parameters.PCM_CAN_ID,
                Parameters.SHIFT_SOLENOID_CHANNEL_B,
                Parameters.SHIFT_SOLENOID_CHANNEL_A);

        inHighGear = false;
    }

    void drive(Joystick driveJs, Joystick driveWheel) {

        //Calculate velocity from input and set setpoints
        double turnValue = driveWheel.getAxis(Joystick.AxisType.kX);
        double throttleValue = -driveJs.getAxis(Joystick.AxisType.kY);

        if (java.lang.Math.abs(driveWheel.getAxis(Joystick.AxisType.kX)) < 0.2) {
            turnValue = 0.0;
        }
        if (java.lang.Math.abs(driveJs.getAxis(Joystick.AxisType.kY)) < 0.2) {
            throttleValue = 0.0;
        }

        if (throttleValue < -0.2) {
            turnValue = -turnValue;
        }

        double rawLeft = throttleValue + turnValue;
        double rawRight = throttleValue - turnValue;

        double leftMotorCommand = rawLeft - skimValue(rawRight) - skimValue(rawLeft);
        double rightMotorCommand = rawRight - skimValue(rawLeft) - skimValue(rawRight);

        double leftMotorVelocity = leftMotorCommand * Parameters.MAX_SPEED;
        double rightMotorVelocity = rightMotorCommand * Parameters.MAX_SPEED;

        leftSidePID.setSetpoint(leftMotorVelocity);
        rightSidePID.setSetpoint(rightMotorVelocity);

        //leftDriveTrain.set(leftMotorCommand);
        //rightDriveTrain.set(rightMotorCommand);

        //IF NOT TURNING, then check for shifting velocities and shift
        if (leftDriveEncoder.getDirection() == rightDriveEncoder.getDirection()) { //Are we not turning (encoder directions match)?
            if (Math.abs((leftDriveEncoder.getRate() + rightDriveEncoder.getRate() / 2)) > Parameters.SHIFT_THRESHOLD
                    && !inHighGear) { //Are we above the high gear threshold and not in high gear?
                setHighGear(true);
            } else if (inHighGear) { //Are we below the threshold and in high gear?
                setHighGear(false);
            }
        }
    }

    private double skimValue(double inputValue) {
        if (inputValue > 1.0)
            return ((inputValue - 1.0) * Parameters.TURN_GAIN);
        else if (inputValue < -1.0)
            return ((inputValue + 1.0) * Parameters.TURN_GAIN);
        return 0;
    }

    /**
     * Should be called in autonomousInit() and teleopInit()
     */
    public void setHighGear(boolean enable) { //TODO Make sure this does what we want it to do
        if (enable) {
            shiftSolenoid.set(DoubleSolenoid.Value.kForward);
            inHighGear = true;
        } else {
            shiftSolenoid.set(DoubleSolenoid.Value.kReverse);
            inHighGear = false;
        }
    }
}