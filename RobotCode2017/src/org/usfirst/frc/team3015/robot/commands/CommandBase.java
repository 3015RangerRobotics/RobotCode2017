package org.usfirst.frc.team3015.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;



import org.usfirst.frc.team3015.robot.OI;
import org.usfirst.frc.team3015.robot.subsystems.*;
/**
 * Where subsystem's commands are organized
 */
public abstract class CommandBase extends Command {
	public static DriveTrain drive;
	public static ShooterWheel shooter;
	public static Climber climber;
	public static Vision vision;
	public static Harvester harvester;
	public static Singulator singulator;
	public static OurCompressor ourCompressor;
	public static GearManipulator gearManipulator;
	public static CameraStream cameraStream;
	public static Hopper hopper;
	public static OI oi;
	
	public static void init(){
		drive = new DriveTrain();
		shooter = new ShooterWheel();
		vision = new Vision();
		climber = new Climber();
		harvester = new Harvester();
		singulator = new Singulator();
		ourCompressor = new OurCompressor();
		gearManipulator = new GearManipulator();
		cameraStream = new CameraStream();
		hopper = new Hopper();
		oi = new OI();
		
		SmartDashboard.putData(climber);
		SmartDashboard.putData(drive);
		SmartDashboard.putData(harvester);
		SmartDashboard.putData(singulator);
		SmartDashboard.putData(vision);
		SmartDashboard.putData(shooter);
		SmartDashboard.putData(ourCompressor);
		SmartDashboard.putData(gearManipulator);
		SmartDashboard.putData(hopper);
	}
}
