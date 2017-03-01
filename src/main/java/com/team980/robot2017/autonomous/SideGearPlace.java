package com.team980.robot2017.autonomous;

import com.team980.robot2017.CustomDrive;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class SideGearPlace extends CommandGroup {

    private CustomDrive drive;

    public SideGearPlace(CustomDrive drive, Position position) {
        super("SideGearPlace at" + position);

        this.drive = drive;

        switch (position) {
            case RED_ALLIANCE_LEFT:
                break;
            case RED_ALLIANCE_RIGHT:
                break;
            case BLUE_ALLIANCE_LEFT:
                break;
            case BLUE_ALLIANCE_RIGHT:
                break;
        }
    }

    private void redAllianceLeft() {
        //center of right wheel at inner edge of tape

        //move forward 84.5 inches
        //turn -60 degrees
        //move forward 41.5 inches
        //wait 3 seconds
        //move backward 6 inches
    }

    private void redAllianceRight() {
        //center of left wheel at inner edge of tape

        //move forward 94.5 inches
        //turn 60 degrees
        //move forward 15.5 inches
        //wait 3 seconds
        //move backward 6 inches
    }

    private void blueAllianceLeft() {
        //center of right wheel at inner edge of tape

        //move forward 94.5 inches
        //turn -60 degrees
        //move forward 15.5 inches
        //wait 3 seconds
        //move backward 6 inches
    }

    private void blueAllianceRight() {
        //center of left wheel at inner edge of tape

        //move forward 84.5 inches
        //turn 60 degrees
        //move forward 41.5 inches
        //wait 3 seconds
        //move backward 6 inches
    }


    public enum Position {
        RED_ALLIANCE_LEFT,
        RED_ALLIANCE_RIGHT,
        BLUE_ALLIANCE_LEFT,
        BLUE_ALLIANCE_RIGHT;
    }
}
