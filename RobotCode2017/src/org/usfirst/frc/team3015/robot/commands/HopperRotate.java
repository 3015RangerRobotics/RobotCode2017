package org.usfirst.frc.team3015.robot.commands;
import edu.wpi.first.wpilibj.Timer;

/**
 * Rotates the hopper
 */
public class HopperRotate extends CommandBase {
	Timer timer = new Timer();
	boolean isReversing;
	
    public HopperRotate() {
        requires(hopper);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	hopper.rotate();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(Math.abs(hopper.getCurrent()) >= 24 && !isReversing){
    		hopper.reverse();
    		timer.start();
    		timer.reset();
    		isReversing = true;
    	}else if(timer.get() <= 0.5 && isReversing){
    		hopper.reverse();
    	}else{
    		hopper.rotate();
    		isReversing = false;
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	hopper.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }


}
