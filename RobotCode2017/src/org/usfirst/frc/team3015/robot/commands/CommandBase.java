package org.usfirst.frc.team3015.robot.commands;

import edu.wpi.first.wpilibj.command.Command;



import org.usfirst.frc.team3015.robot.OI;
import org.usfirst.frc.team3015.robot.subsystems.*;
/**
 * Where subsystem's commands are organized
 */
public abstract class CommandBase extends Command {
	public static DriveTrain drive;
	public static ShooterWheel shooterWheel;
	public static ShooterFeeder shooterFeeder;
	public static Climber climber;
	public static Vision vision;
	public static Harvester harvester;
	public static OI oi;
	
	public static void init(){
		drive = new DriveTrain();
		shooterWheel = new ShooterWheel();
		shooterFeeder = new ShooterFeeder();
		vision = new Vision();
		climber = new Climber();
		harvester = new Harvester();
		oi = new OI();
	}
}
