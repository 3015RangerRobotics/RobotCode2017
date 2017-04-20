
package org.usfirst.frc.team3015.robot;

import org.spectrum3847.RIOdroid.RIOadb;
import org.spectrum3847.RIOdroid.RIOdroid;
import org.usfirst.frc.team3015.robot.commands.*;
import org.usfirst.frc.team3015.robot.subsystems.Vision;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	Command autonomousCommand;
	SendableChooser<Command> chooser = new SendableChooser<>();

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
//		System.exit(0);
		//Construct Subsystems BEFORE any Commands are constructed
		//init stuff
		
//		System.out.println("RIOdroid init...");
//		RIOdroid.initUSB();
//		System.out.println("Clearing network ports...");
//		RIOadb.clearNetworkPorts();
//		Timer.delay(1);
//		System.out.println("Forwarding...");
//		RIOdroid.executeCommand("adb forward tcp:3800 tcp:3015");
//		Timer.delay(1);
//		RIOdroid.executeCommandThread("socat TCP4-LISTEN:3015,fork TCP4:127.0.0.1:3800");
//		System.out.println("Launching app...");
//		RIOdroid.executeCommand("adb shell am force-stop com.rangerrobot.rangervision");
//		Timer.delay(0.5);
//		RIOdroid.executeCommand("adb shell am start -n com.rangerrobot.rangervision/com.rangerrobot.rangervision.RangerVision");
//		Timer.delay(2);
//		RIOdroid.executeCommand("adb shell input tap 1200 1000");
//		Timer.delay(1);
		
		CommandBase.init();
		SmartDashboard.putBoolean("isRed", DriverStation.getInstance().getAlliance() == Alliance.Red);
		chooser.addObject("Red Gear and Hopper Shot", new AutonomousGearAndHopperShotRed());
		chooser.addObject("Blue Gear and Hopper Shot", new AutonomousGearAndHopperShotBlue());
		chooser.addObject("Red Hopper Shot", new AutonomousHopperShotRed());
		chooser.addObject("Blue Hopper Shot", new AutonomousHopperShotBlue());
		chooser.addObject("Cross Base Line", new AutonomousCrossBaseLine());
		chooser.addObject("No Auto", null);
		SmartDashboard.putData("autonomous",chooser);
		System.out.println("FINISHED ROBOT INIT");
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	//bla
	@Override
	public void disabledInit() {
		
	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
//		autonomousCommand = chooser.getSelected();
		if(DriverStation.getInstance().getAlliance() == Alliance.Red){
			autonomousCommand = new AutonomousHopperShotRed();
		}else if(DriverStation.getInstance().getAlliance() == Alliance.Blue){
			autonomousCommand = new AutonomousHopperShotBlue();
		}else{
			autonomousCommand = null;
		}
		Vision.isCompMode = true;
		
//		RIOdroid.executeCommandThread("adb shell screenrecord /sdcard/Movies/" + (int) (Math.random() * 1000) + ".mp4");
		
		if (autonomousCommand != null)
			autonomousCommand.start();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (autonomousCommand != null)
			autonomousCommand.cancel();
//		Command test = new DriveTurnToTarget(true);
//		test.start();
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}
}
