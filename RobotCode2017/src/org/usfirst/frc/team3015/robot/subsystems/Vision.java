package org.usfirst.frc.team3015.robot.subsystems;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

import org.spectrum3847.RIOdroid.RIOdroid;
import org.usfirst.frc.team3015.robot.Robot;
import org.usfirst.frc.team3015.robot.commands.VisionEnableComms;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Subsystem;
/**
 * Vision subsystem for communicating with the vision tracking phone
 * @author Michael
 *
 */
public class Vision extends Subsystem {
	public static volatile double xAngleToTarget = 0;
	public static volatile double distance = 0;
	public static volatile boolean isSpoinger = false;
	public static volatile boolean isCompMode = false;
	private volatile boolean shouldRun = false;
	private volatile boolean shouldSpeak = false;
	private volatile boolean shouldSeeTarget = false;
	private volatile String message = "";
	
	public Vision(){
		
	}

    public void initDefaultCommand() {
        setDefaultCommand(new VisionEnableComms());
    }
    /**
     * Method to start the thread that uses socket servers to communicate with the android phone
     */
    public void runComms(){
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
					
					while(shouldRun){
						String messageIn = in.readLine();
						if(messageIn != null){
//							System.out.println(messageIn);
							Vision.xAngleToTarget = Double.parseDouble(messageIn.substring(0, messageIn.indexOf(',')));
							Vision.distance = Double.parseDouble(messageIn.substring(messageIn.lastIndexOf(',')));
						}
						
						if(shouldSeeTarget){
							out.println("target");
						}else if(shouldSpeak){
							out.println("say:" + message);
							message = "";
							shouldSpeak = false;
						}else if(isCompMode){
							out.println("comp");
							isCompMode = false;
						}else if(DriverStation.getInstance().isEnabled()){
							out.println("enabled");
						}else if(!DriverStation.getInstance().isEnabled()){
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
     * Method to change the shouldRun boolean to true to allow the comms thread to run
     */
    public void enableComms(){
    	shouldRun = true;
    }
    /**
     * Method to change the shouldRun boolean to false to stop the comms thread from running
     */
    public void disableComms(){
    	shouldRun = false;
    }
    /**
     * Returns whether the comms thread is/should be running
     * @return shouldRun
     */
    public boolean shouldRun(){
    	return shouldRun;
    }
    /**
     * Executes a command on the RoboRIO, mainly used for adb commands
     * @param command the command to execute
     */
    public void executeCommand(String command){
    	System.out.println("Running command: " + command + "\n" + RIOdroid.executeCommand(command));
    }
    /**
     * Sends a message to the phone for the text-to-speech to say
     * @param text what the phone should say
     */
    public void speakAddMessage(String text){
//    	shouldSpeak = true;
    	message += text + " ";
    }
    
    public void speak(){
    	shouldSpeak = true;
    }
    /**
     * Tells the phone whether a vision target should be seen (ex. know we are lined up in auto)
     * @param bool should the phone see the target
     */
    public void shouldSeeTarget(boolean bool){
    	shouldSeeTarget = bool;
    }
}

