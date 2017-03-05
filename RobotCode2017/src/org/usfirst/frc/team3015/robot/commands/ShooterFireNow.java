package org.usfirst.frc.team3015.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 *
 */
public class ShooterFireNow extends CommandBase {
	boolean isReversing = false;
	Timer timer = new Timer();

    public ShooterFireNow() {
    	requires(hopper);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	timer.reset();
    	timer.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
//    	hopper.startFeeder();
//		hopper.rotate();
//    	System.out.println("hopper current: " + Math.abs(hopper.getCurrent()));
//    	System.out.println("Hopper speed: " + Math.abs(hopper.getSpeed()));
//		if(Math.abs(hopper.getCurrent()) >= 6.5 && !isReversing){
//		if(Math.abs(hopper.getSpeed()) <= 5 && !isReversing){
//    		hopper.reverse();
//    		timer.start();
//    		timer.reset();
//    		isReversing = true;
//    	}else if(timer.get() <= 0.5 && isReversing){
//    		hopper.reverse();
//    	}else{
//    	if(timer.get() <= 1.75){
    		hopper.rotate();
    		hopper.startFeeder();
//    	}else if(timer.get() <= 2.25){
//    		hopper.stop();
//    		hopper.stopFeeder();
//    	}else{
//    		timer.reset();
//    		timer.start();
//    	}
//    		hopper.rotate();
    		isReversing = false;
//    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	hopper.stopFeeder();
    	hopper.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}