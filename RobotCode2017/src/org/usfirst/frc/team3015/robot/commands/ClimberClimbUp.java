package org.usfirst.frc.team3015.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *Command to climb up
 */
public class ClimberClimbUp extends CommandBase {

    public ClimberClimbUp() {
    	requires(climber);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	climber.climbUp();
    	System.out.println("Climber Current:" + climber.getCurrent());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Math.abs(climber.getCurrent()) > 500;
    }

    // Called once after isFinished returns true
    protected void end() {
    	climber.stopClimb();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
