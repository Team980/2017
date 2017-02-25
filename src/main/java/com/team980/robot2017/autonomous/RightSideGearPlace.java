package com.team980.robot2017.autonomous;

import com.ctre.PigeonImu;
import com.team980.robot2017.CustomDrive;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class RightSideGearPlace extends CommandGroup {

    //Schedules subcommands
    public RightSideGearPlace(CustomDrive drive, PigeonImu imu) {
        super("RightSideGearPlace");

        //addSequential(); TODO

        //if RedAlliance

        //center of left wheel at inner edge of tape

        //move forward 94.5 inches
        //turn 60 degrees
        //move forward 15.5 inches
        //wait 3 seconds
        //move backward 6 inches


        //if BlueAlliance

        //move forward 84.5 inches
        //turn 60 degrees
        //move forward 41.5 inches
        //wait 3 seconds
        //move backward 6 inches
    }
}
