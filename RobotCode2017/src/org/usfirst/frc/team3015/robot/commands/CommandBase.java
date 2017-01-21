package org.usfirst.frc.team3015.robot.commands;

import edu.wpi.first.wpilibj.command.Command;



import org.usfirst.frc.team3015.robot.OI;
import org.usfirst.frc.team3015.robot.subsystems.*;
/**
 *
 */
public abstract class CommandBase extends Command {
	public static DriveTrain drive;
	public static Shooter shooter;
	public static Climber climber;
	public static Vision vision;
	public static OI oi;
	
	public static void init(){
		drive = new DriveTrain();
		shooter = new Shooter();
		vision = new Vision();
		climber = new Climber();
		oi = new OI();
	}
}
