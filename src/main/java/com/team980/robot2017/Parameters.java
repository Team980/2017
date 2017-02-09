package com.team980.robot2017;

class Parameters {

    //TODO PORTED FROM 2016 - NEEDS REORGANIZING

    //motor channels
    public static int leftMotorCh = 0;
    public static int rightMotorCh = 1;

    //drive parameters
    public static double maxSpeed = 10.0; // in feet per second
    public static double maxCommand = 1.0;
    public static double turnGain = 1.0;

    //joystick channels
    public static int driveJs = 0;
    public static int driveWheel = 1;

    //encoder channels
    public static int leftEncChA = 3;
    public static int leftEncChB = 4;
    public static int rightEncChA = 1;
    public static int rightEncChB = 2;

    //encoder stuff
    public static boolean rightEncInvert = true;
    public static boolean leftEncInvert = false;

    //drive PID values
    public static double leftPIDp = 0.025;
    public static double leftPIDi = 0.0;
    public static double leftPIDd = 0.0;
    public static double rightPIDp = 0.025;
    public static double rightPIDi = 0.0;
    public static double rightPIDd = 0.0;

    public static double pidPercentTolerance = 0.0;


    public static double wheelRadius = 4.0;
    public static double encoderCounts = 1024.0;

    public static final int SHIFT_SOLENOID_CHANNEL_A = 4; //reverse
    public static final int SHIFT_SOLENOID_CHANNEL_B = 5; //forward

    public static final int PCM_CAN_ID = 1;
}
