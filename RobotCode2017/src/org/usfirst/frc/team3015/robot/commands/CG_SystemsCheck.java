package org.usfirst.frc.team3015.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CG_SystemsCheck extends CommandGroup {

    public CG_SystemsCheck() {
        addSequential(new DriveCheckDriveTrain());
        addSequential(new ShooterCheckShooter());
        addSequential(new VisionSpeak());
    }
}
