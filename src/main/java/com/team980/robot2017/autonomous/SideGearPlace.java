package com.team980.robot2017.autonomous;

import com.ctre.PigeonImu;
import com.team980.robot2017.CustomDrive;
import com.team980.robot2017.autonomous.subcommands.IMUTurn;
import com.team980.robot2017.autonomous.subcommands.Move;
import com.team980.robot2017.autonomous.subcommands.Wait;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class SideGearPlace extends CommandGroup {

    private CustomDrive drive;
    private PigeonImu imu;

    public SideGearPlace(CustomDrive drive, PigeonImu imu, Position position) {
        super("SideGearPlace at " + position);
        System.out.println("SideGearPlace at " + position);
        this.drive = drive;
        this.imu = imu;

        switch (position) {
            case RED_ALLIANCE_LEFT:
                redAllianceLeft();
                break;
            case RED_ALLIANCE_RIGHT:
                redAllianceRight();
                break;
            case BLUE_ALLIANCE_LEFT:
                blueAllianceLeft();
                break;
            case BLUE_ALLIANCE_RIGHT:
                blueAllianceRight();
                break;
        }
    }

    private void redAllianceLeft() {
        //center of right wheel at inner edge of tape

        //move forward 84.5 inches
        addSequential(new Move(drive, 84.5/12.0)); //was 66.5 for some reason
        //wait 0.5 seconds
        addSequential(new Wait(drive, 500));
        //turn 60 degrees
        addSequential(new IMUTurn(drive, imu, 60.0));
        //wait 0.5 seconds
        addSequential(new Wait(drive, 500));
        //Drive forward
        addSequential(new Move(drive, 33.7/12.0)); //Distance found via testing
    }

    private void redAllianceRight() {
        //center of left wheel at inner edge of tape

        //move forward 94.5 inches
        addSequential(new Move(drive, 94.5/12.0));
        //wait 0.5 seconds
        addSequential(new Wait(drive, 500));
        //turn -60 degrees
        addSequential(new IMUTurn(drive, imu, -60));
        //wait 0.5 seconds
        addSequential(new Wait(drive, 500));
        //Drive forward
        addSequential(new Move(drive, (94.5/12.0)*0.4));
    }

    private void blueAllianceLeft() {
        //center of right wheel at inner edge of tape

        //move forward 94.5 inches
        addSequential(new Move(drive, 94.5/12.0));
        //wait 0.5 seconds
        addSequential(new Wait(drive, 500));
        //turn 60 degrees
        addSequential(new IMUTurn(drive, imu, 60.0));
        //wait 0.5 seconds
        addSequential(new Wait(drive, 500));
        //Drive forward
        addSequential(new Move(drive, (94.5/12.0)*0.4));
    }

    private void blueAllianceRight() {
        //center of left wheel at inner edge of tape

        //move forward 84.5 inches
        addSequential(new Move(drive, 84.5/12.0));
        //wait 0.5 seconds
        addSequential(new Wait(drive, 500));
        //turn -60 degrees
        addSequential(new IMUTurn(drive, imu, -60.0));
        //wait 0.5 seconds
        addSequential(new Wait(drive, 500));
        //Drive forward
        addSequential(new Move(drive, (84.5/12.0)*0.4));
    }


    public enum Position {
        RED_ALLIANCE_LEFT,
        RED_ALLIANCE_RIGHT,
        BLUE_ALLIANCE_LEFT,
        BLUE_ALLIANCE_RIGHT;
    }
}
