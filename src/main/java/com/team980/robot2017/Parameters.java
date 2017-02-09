package com.team980.robot2017;

public class Parameters {

    //TODO Change values to match robot

    // JOYSTICKS
    public static int DRIVE_JOYSTICK_ID = 0;
    public static int DRIVE_WHEEL_ID = 1;

    // DRIVE SYSTEM
    public static int LEFT_DRIVE_PWM_CHANNEL = 0;
    public static int RIGHT_DRIVE_PWM_CHANNEL = 1;

    public static final double MAX_SPEED = 10.0; // in feet per second
    public static final double TURN_GAIN = 1.0;

    public static final double SHIFT_THRESHOLD = 200;

    // ENCODERS
    public static final int LEFT_ENCODER_CHANNEL_A = 3;
    public static final int LEFT_ENCODER_CHANNEL_B = 4;
    public static final boolean INVERT_LEFT_ENCODER = false;

    public static final int RIGHT_ENCODER_CHANNEL_A = 1;
    public static final int RIGHT_ENCODER_CHANNEL_B = 2;
    public static final boolean INVERT_RIGHT_ENCODER = true;

    // PID
    public static final double LEFT_PID_P = 0.025;
    public static final double LEFT_PID_I = 0.0;
    public static final double LEFT_PID_D = 0.0;

    public static final double RIGHT_PID_P = 0.025;
    public static final double RIGHT_PID_I = 0.0;
    public static final double RIGHT_PID_D = 0.0;

    public static final double PID_PERCENT_TOLERANCE = 0.0;

    // PNEUMATICS

    public static final int PCM_CAN_ID = 1;

    public static final int SHIFT_SOLENOID_CHANNEL_A = 4;
    public static final int SHIFT_SOLENOID_CHANNEL_B = 5;
}
