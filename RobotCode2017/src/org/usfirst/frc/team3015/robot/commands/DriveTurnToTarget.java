package org.usfirst.frc.team3015.robot.commands;

import org.usfirst.frc.team3015.robot.Robot;
import org.usfirst.frc.team3015.robot.subsystems.Vision;



/**
 *
 */
public class DriveTurnToTarget extends CommandBase {
	private boolean usesGyro;
	private double turnRate = 0;
	private double speedIncrement = 0.001;
	private long lastTime = 0;
	private double lastAngle = 0;
	private double startSpeed = 0.6;
	private double turnSpeed = 0;

    public DriveTurnToTarget(boolean usesGyro) {
    	requires(drive);
    	this.usesGyro = usesGyro;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	lastTime = System.currentTimeMillis();
    	lastAngle = usesGyro ? drive.getAngle() : Vision.xAngleToTarget;
    	turnSpeed = startSpeed;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (Math.abs(Vision.xAngleToTarget) < 0.5);
    }

    // Called once after isFinished returns true
    protected void end() {
    	drive.arcadeDrive(0, 0, true);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
