package org.usfirst.frc.team3015.robot.commands;

import edu.wpi.first.wpilibj.Timer;

/**
 * Close claw
 */
public class GearClawClose extends CommandBase {
//	Timer timer = new Timer();

    public GearClawClose() {
//        requires(gearManipulator);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
//    	gearManipulator.closeClaw();
//    	timer.reset();
//    	timer.start();
    	gearManipulator.closeClaw();
    	gearManipulator.closeClaw2();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	gearManipulator.closeClaw();
//    	if(timer.get() > 0.25){
    		gearManipulator.closeClaw2();
//    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    	
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
