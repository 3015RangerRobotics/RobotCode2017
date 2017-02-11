
package org.usfirst.frc.team3015.robot;

import org.usfirst.frc.team3015.robot.commands.AutonomousCrossBaseLine;
import org.usfirst.frc.team3015.robot.commands.AutonomousHopperShot;
import org.usfirst.frc.team3015.robot.commands.CommandBase;

import edu.wpi.first.wpilibj.IterativeRobot;
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
	public static boolean isEnabled = false;
	public static volatile double xAngle = 0;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		chooser.addDefault("Hopper Shot", new AutonomousHopperShot());
		chooser.addObject("Cross Base Line", new AutonomousCrossBaseLine());
		SmartDashboard.putData("Auto mode", chooser);
		//init stuff
//		RIOdroid.initUSB();
//		System.out.println(RIOadb.clearNetworkPorts());
//		RIOdroid.init();
//		//forward the ports used for comms
//		Timer.delay(1);
//		System.out.println("FOWARD ADB: " + RIOadb.ForwardAdb(3800,3015));
//		Timer.delay(1);
//		System.out.println("FOWARD SOCAT: " + RIOadb.forwardToLocal(3015,3800));
//		//run adb commands on the phone to close the app if it is running, and re-open it
//		Timer.delay(1);
////		RIOdroid.executeCommand("adb shell input KEYCODE_WAKEUP");
////		Timer.delay(0.5);
////		RIOdroid.executeCommand("adb shell input text 3015 && adb shell input keyevent 66");
////		Timer.delay(0.5);
//		RIOdroid.executeCommand("adb shell am force-stop com.rangerrobot.rangervision");
//		Timer.delay(0.5);
//		RIOdroid.executeCommand("adb shell am start -n com.rangerrobot.rangervision/com.rangerrobot.rangervision.RangerVision");
//		Timer.delay(2);
//		RIOdroid.executeCommand("adb shell input tap 1200 1000");
//		Timer.delay(1);
		//init command base
		CommandBase.init();
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
		isEnabled = false;
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
		isEnabled = true;
		autonomousCommand = chooser.getSelected();

		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }
		 */

		// schedule the autonomous command (example)
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
		isEnabled = true;
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
