package com.team980.robot2017.autonomous;

import com.team980.robot2017.CustomDrive;
import com.team980.robot2017.autonomous.subcommands.Move;
import com.team980.robot2017.autonomous.subcommands.Turn;
import com.team980.robot2017.autonomous.subcommands.Wait;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class SideGearPlace extends CommandGroup {

    private CustomDrive drive;
    private SpeedController leftSpeedController;
    private SpeedController rightSpeedController;

    public SideGearPlace(CustomDrive drive, Position position) {
        super("SideGearPlace at" + position);
        leftSpeedController = drive.getLeftSpeedController();
        rightSpeedController = drive.getRightSpeedController();
        System.out.println("SideGearPlace");
        this.drive = drive;

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
        System.out.println("redAllianceLeft");
        //move forward 84.5 inches
        driveForwardByDistance(66.5/12.0);
        //wait 0.5 seconds
        waitTimeInMillis(500);
        //turn 60 degrees
        turnByDegrees(60.0);
        //wait 0.5 seconds
        waitTimeInMillis(500);
        //Drive forward
        driveForwardByDistance((84.5/12.0)*0.6);



    }

    private void redAllianceRight() {
        //cen9ter of left wheel at inner edge of tape
        System.out.println("redAllianceRight");
        //move forward 94.5 inches
        driveForwardByDistance(94.5/12.0);
        //wait 0.5 seconds
        waitTimeInMillis(500);
        //turn -60 degrees
        turnByDegrees(-60);
        //wait 0.5 seconds
        waitTimeInMillis(500);
        //Drive forward
        driveForwardByDistance((94.5/12.0)*0.4);


    }

    private void blueAllianceLeft() {
        //center of right wheel at inner edge of tape
        System.out.println("BlueAllianceLeft");
        //move forward 94.5 inches
        driveForwardByDistance(94.5/12.0);
        //wait 0.5 seconds
        waitTimeInMillis(500);
        //turn 60 degrees
        turnByDegrees(60.0);
        //wait 0.5 seconds
        waitTimeInMillis(500);
        //Drive forward
        driveForwardByDistance((94.5/12.0)*0.4);


    }

    private void blueAllianceRight() {
        //center of left wheel at inner edge of tape
        System.out.println("BlueAllianceRight");
        //move forward 84.5 inches
        driveForwardByDistance(84.5/12.0);
        //wait 0.5 seconds
        waitTimeInMillis(500);
        //turn 60 degrees
        turnByDegrees(-60.0);
        //wait 0.5 seconds
        waitTimeInMillis(500);
        //Drive forward
        driveForwardByDistance((84.5/12.0)*0.4);


    }


    public enum Position {
        RED_ALLIANCE_LEFT,
        RED_ALLIANCE_RIGHT,
        BLUE_ALLIANCE_LEFT,
        BLUE_ALLIANCE_RIGHT;
    }

    private void turnByDegrees(double value){
        //reset encoders for distance measurement
        addSequential(new Turn(drive, value));
    }

    private void driveForwardByDistance(double value) {
        addSequential(new Move(drive, value));
    }

    private void waitTimeInMillis(long value){
        addSequential(new Wait(drive, value));
    }
}
