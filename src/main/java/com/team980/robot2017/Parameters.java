package com.team980.robot2017;

public class Parameters {
    // JOYSTICKS
    public static final int DRIVE_JOYSTICK_ID = 0;
    public static final int DRIVE_WHEEL_ID = 1;
    public static final int OPERATOR_BOX_ID = 2;

    // DRIVE SYSTEM
    public static final int LEFT_DRIVE_PWM_CHANNEL = 0;
    public static final int RIGHT_DRIVE_PWM_CHANNEL = 1;

    public static final double MAX_SPEED = 16.0; // in feet per second
    public static final double TURN_DAMPEN = 1.0; // adjust this to dampen the steering: [0.0, 1.0]
    public static final double TURN_GAIN = 1.0;  // do not adjust this

    public static final double SHIFT_THRESHOLD = 4; // in feet per second
    public static final double SHIFT_THRESHOLD_DELTA = 0.5; // in feed per second

    // ENCODERS
    public static final int LEFT_ENCODER_DIO_CHANNEL_A = 1;
    public static final int LEFT_ENCODER_DIO_CHANNEL_B = 2;
    public static final boolean INVERT_LEFT_ENCODER = false;

    public static final int RIGHT_ENCODER_DIO_CHANNEL_A = 4;
    public static final int RIGHT_ENCODER_DIO_CHANNEL_B = 5;
    public static final boolean INVERT_RIGHT_ENCODER = true;

    // PID
    public static final double LEFT_PID_P = 0.025; //TODO tune the PID
    public static final double LEFT_PID_I = 0.0;
    public static final double LEFT_PID_D = 0.0;

    public static final double RIGHT_PID_P = 0.025;
    public static final double RIGHT_PID_I = 0.0;
    public static final double RIGHT_PID_D = 0.0;

    public static final double PID_PERCENT_TOLERANCE = 0.0;

    // BALL PICKUP
    public static final int INTAKE_MOTOR_CAN_ID = 3;
    public static final int OUTPUT_MOTOR_CAN_ID = 2;

    public static final double INTAKE_MOTOR_SPEED = 1.0;
    public static final double OUTPUT_MOTOR_SPEED = 1.0;
    public static final double OUTPUT_SLOW_MULTIPLIER = 0.5;

    // PNEUMATICS
    public static final int PCM_CAN_ID = 1;

    public static final int SHIFT_SOLENOID_CHANNEL = 4;
    public static final int CLIMBER_SOLENOID_CHANNEL = 5;
    public static final int GEAR_SOLENOID_CHANNEL = 6; //TODO: determine correct channel

    // IMU
    public static final int IMU_CAN_ID = 6;

    // AUTONOMOUS
    public static final double AUTO_LEFT_SPEED = 0.62; //Until PID is back: [-1.0, 1.0]
    public static final double AUTO_RIGHT_SPEED = 0.6;
    public static final double AUTO_DISTANCE = 15.0; // in feet
}
