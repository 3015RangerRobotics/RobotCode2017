package org.usfirst.frc.team3015.robot.commands;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDController.AbsoluteTolerance;
import edu.wpi.first.wpilibj.PIDOutput;

/**
 *
 */
public class DriveStraightWithPID extends CommandBase {
	private double distance;
	private double output = 0;
	private PIDController driveControl;
	private double p = 0.15;
	private double i = 0;
	private double d = 0.15;
	private double onTargetCount = 0;
	private double startAngle = 0;
	
    public DriveStraightWithPID(double distance) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(drive);
    	this.distance = distance;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	driveControl = new PIDController(p, i, d, drive.leftEncoder, new PIDOutput(){
			@Override
			public void pidWrite(double output) {}
    	});
    	driveControl.setSetpoint(distance);
    	driveControl.setAbsoluteTolerance(1);
    	drive.zeroDriveEncoder();
    	driveControl.enable();
    	startAngle = drive.getAngle();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double turnValue = 0;
    	System.out.println(drive.leftEncoder.getDistance() + " - " + driveControl.onTarget() + " - " + driveControl.getError());
    	double currentAngle = drive.getAngle();
    	if (currentAngle > startAngle + 2){
    		turnValue = 0.3;
    		
    	}
    	else if (currentAngle > startAngle + 0.5){
    		turnValue = 0.2;
    	}
    	else if (currentAngle < startAngle - 0.5){
    		turnValue = -0.2;
    	}
    	else if (currentAngle < startAngle - 2){
    		turnValue = -0.3;
    	}
    	else{
    		turnValue = 0;
    	}
    	drive.arcadeDrive(driveControl.get(), turnValue, false);
    	if(driveControl.onTarget()){
    		onTargetCount++;
    	}else{
    		onTargetCount = 0;
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
//        return Math.abs(Math.abs(distance) - Math.abs(drive.leftEncoder.getDistance())) < 1;
    	return onTargetCount >= 10;
    }

    // Called once after isFinished returns true
    protected void end() {
    	drive.arcadeDrive(0, 0, false);
    	driveControl.disable();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
