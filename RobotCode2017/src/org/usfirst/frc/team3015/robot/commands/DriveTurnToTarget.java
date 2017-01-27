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
    	long currentTime = System.currentTimeMillis();
    	if(currentTime - lastTime == 0){
    		lastTime = currentTime;
    		return;
    	}
    	double updateRate = 1000/(currentTime-lastTime);
    	
    	if(Vision.xAngleToTarget >= 10){
    		turnRate = -15.0;
    	}else if(Vision.xAngleToTarget >= 1){
    		turnRate = -10.0;
    	}else if(Vision.xAngleToTarget >= 0.25){
    		turnRate = -5.0;
    	}else if(Vision.xAngleToTarget <= -10){
    		turnRate = 15.0;
    	}else if(Vision.xAngleToTarget <= -1){
    		turnRate = 10.0;
    	}else if(Vision.xAngleToTarget <= -0.25){
    		turnRate = 5.0;
    	}
    	else{
    		turnRate = 0;
    	}
    	
    	double neededTurnAmount = Math.abs(turnRate / updateRate);
    	double turnAmount = Math.abs(lastAngle - (usesGyro ? drive.getAngle() : Vision.xAngleToTarget));
    	if(turnAmount <= neededTurnAmount - neededTurnAmount*0.1){
    		turnSpeed += speedIncrement;
    	}else if(turnAmount >= neededTurnAmount + neededTurnAmount*0.1){
    		turnSpeed -= speedIncrement;
    	}
    	System.out.println("Update Rate: " + updateRate);
    	System.out.println("Turn Amount: " + turnAmount);
    	System.out.println("Needed turn amount: " + neededTurnAmount);
    	System.out.println("Angle: " + drive.getAngle());
    	System.out.println("Magnetic Disturbance: " + drive.isMagneticDisturbance());
    	System.out.println("Turn Speed: " + turnSpeed);
    	
    	drive.arcadeDrive(0, (turnRate < 0) ? -turnSpeed : turnSpeed, false);
    	lastAngle = usesGyro ? drive.getAngle() : Vision.xAngleToTarget;
    	lastTime = currentTime;
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
