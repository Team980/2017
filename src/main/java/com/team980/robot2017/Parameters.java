package com.team980.robot2017;

public class Parameters {

    //TODO Change values to match robot

    // JOYSTICKS
    public static final int DRIVE_JOYSTICK_ID = 0;
    public static final int DRIVE_WHEEL_ID = 1;
    public static final int OPERATOR_BOX_ID = 2;

    // DRIVE SYSTEM
    public static final int LEFT_DRIVE_PWM_CHANNEL = 0;
    public static final int RIGHT_DRIVE_PWM_CHANNEL = 1;

    public static final double MAX_SPEED = 16.0; // in feet per second
    public static final double TURN_GAIN = 1.0;

    public static final double SHIFT_THRESHOLD = 5; //TODO calculate with Mark

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

    // PNEUMATICS
    public static final int PCM_CAN_ID = 1;

    public static final int SHIFT_SOLENOID_CHANNEL = 4;
    public static final int CLIMBER_SOLENOID_CHANNEL = 5;

    // IMU
    public static final int IMU_CAN_ID = 4;

    // AUTONOMOUS
    public static final int AUTO_SPEED = 5; //All setpoints are scaled in feet per second

    //Mode-specific parameters moved to their respective commands
}
