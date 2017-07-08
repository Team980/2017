package com.team980.robot2017.autonomous;

import com.team980.robot2017.CustomDrive;
import com.team980.robot2017.autonomous.subcommands.Move;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class FrontGearPlace extends CommandGroup {

    //Schedules subcommands
    public FrontGearPlace(CustomDrive drive) {
        super("FrontGearPlace");

        addSequential(new Move(drive, 6.25)); //distance in feet to center peg

        //robot shouldn't move while human players take gear out
        //we've already crossed the baseline, so don't bother to do anything else
    }
}
