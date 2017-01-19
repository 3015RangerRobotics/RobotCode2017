package org.usfirst.frc.team3015.robot.commands;
import edu.wpi.first.wpilibj.DoubleSolenoid;


/**
 *
 */
public class DriveStrafeWithGamepad extends CommandBase {

    public DriveStrafeWithGamepad() {
        // Use requires() here to declare subsystem dependencies
          requires(drive);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	drive.setHWheelSolenoid(DoubleSolenoid.Value.kForward);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	drive.hDrive(oi.getDriverSumTriggers());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (oi.getDriverSumTriggers() < .01) && (oi.getDriverSumTriggers() > -.01);
    }

    // Called once after isFinished returns true
    protected void end() {
    	drive.setHWheelSolenoid(DoubleSolenoid.Value.kReverse);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
