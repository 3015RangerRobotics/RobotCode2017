package org.usfirst.frc.team3015.robot.commands;


/**
 *
 */
public class DriveWithGamepad extends CommandBase {

    public DriveWithGamepad() {
        requires(drive);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	drive.arcadeDrive(-oi.getDriverLeftY(), oi.getDriverLeftX(), true);
//    	if(!drive.isCalibrating()){
//    		System.out.println("IMU Angle: " + drive.getAngle());
//    		System.out.println("Is calibrating: " + drive.isCalibrating());
//    		System.out.println("Magnetic Disturbance: " + drive.isMagneticDisturbance());
//    		System.out.println("IMU Magnetic Disturbance: " + drive.isMagneticDisturbance());
//    	}
    	System.out.println(drive.getLeftEncoder() + ", " + drive.getRightEncoder());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
