
package org.usfirst.frc.team3015.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import org.spectrum3847.RIOdroid.RIOadb;
import org.spectrum3847.RIOdroid.RIOdroid;
import org.usfirst.frc.team3015.robot.commands.CommandBase;

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

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
//		chooser.addDefault("Default Auto", new CommandBase());
		// chooser.addObject("My Auto", new MyAutoCommand());
		SmartDashboard.putData("Auto mode", chooser);
		
		RIOdroid.initUSB();
		
		System.out.println(RIOadb.clearNetworkPorts());
		RIOdroid.init();
		Timer.delay(1);
		System.out.println("FOWARD ADB: " + RIOadb.ForwardAdb(3800,3015));
		Timer.delay(1);
		System.out.println("FOWARD SOCAT: " + RIOadb.forwardToLocal(3015,3800));
		System.out.println("FINISHED ROBOT INIT");
		
		CommandBase.init();
		
//		try {
//			Socket socket = new Socket("localhost", 3015);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		
		new Thread(new Runnable(){
			Socket socket;
			BufferedReader in = null;
			OutputStreamWriter osw = null;
			PrintWriter out = null;
			
			@Override
			public void run() {
				try {
					System.out.println("starting comms");
					System.out.println(socket = new Socket("localhost", 3015));
					System.out.println("1");
					in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					System.out.println("2");
					osw = new OutputStreamWriter(socket.getOutputStream());
					System.out.println("3");
					out = new PrintWriter(new BufferedWriter(osw), true);
					System.out.println("4");
					
					while(true){
						String messageIn = in.readLine();
						if(messageIn != null){
							System.out.println(messageIn);
						}
						if(Robot.isEnabled){
							out.println("enabled");
						}else{
							out.println("disabled");
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
		}).start();
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
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (autonomousCommand != null)
			autonomousCommand.cancel();
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
