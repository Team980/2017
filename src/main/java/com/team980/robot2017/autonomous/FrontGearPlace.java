package com.team980.robot2017.autonomous;

import com.ctre.PigeonImu;
import com.team980.robot2017.CustomDrive;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class FrontGearPlace extends CommandGroup {

    //Schedules subcommands
    public FrontGearPlace(CustomDrive drive, PigeonImu imu) {
        super("FrontGearPlace");

        //addSequential(); TODO

        //robot centered

        //move forward 75 inches
        //wait 3 seconds
        //move backward 6 inches
    }
}
