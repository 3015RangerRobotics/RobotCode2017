package org.usfirst.frc.team3015.robot.commands;

import edu.wpi.first.wpilibj.command.Command;



import org.usfirst.frc.team3015.robot.OI;
import org.usfirst.frc.team3015.robot.subsystems.*;
/**
 *
 */
public class CommandBase extends Command {
	public static DriveTrain drive;
	public static OI oi;
	
	public CommandBase() {
		drive = new DriveTrain();
		oi = new OI();	
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
	}
}
