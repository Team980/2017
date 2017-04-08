package com.team980.robot2017.autonomous;

import com.team980.robot2017.CustomDrive;
import com.team980.robot2017.Parameters;
import com.team980.robot2017.autonomous.subcommands.Move;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**Do not use in place of FrontGearPlace*/
public class SimpleBaselineCross extends CommandGroup {

    //Handles parameters
    public SimpleBaselineCross(CustomDrive drive) {
        super("SimpleBaselineCross");

        addSequential(new Move(drive, Parameters.AUTO_DISTANCE)); //in feet, the baseline is 9'4" from the alliance station
    }
}
