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

import edu.wpi.first.wpilibj.command.Subsystem;

public class Vision extends Subsystem {
	public static volatile double xAngleToTarget = 0;
	private volatile boolean shouldRun = false;
	private volatile boolean shouldSpeak = false;
	private volatile boolean shouldSeeTarget = false;
	private volatile String message = "";
	
	public Vision(){
		
	}

    public void initDefaultCommand() {
        setDefaultCommand(new VisionEnableComms());
    }
    
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
							System.out.println(messageIn);
							Vision.xAngleToTarget = Double.parseDouble(messageIn.substring(0, messageIn.indexOf(',')));
						}
						
						if(shouldSeeTarget){
							out.println("target");
						}else if(shouldSpeak){
							out.println("say:" + message);
							message = "";
							shouldSpeak = false;
						}else if(Robot.isEnabled){
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
    
    public void enableComms(){
    	shouldRun = true;
    }
    
    public void disableComms(){
    	shouldRun = false;
    }
    
    public boolean shouldRun(){
    	return shouldRun;
    }
    
    public void executeCommand(String command){
    	System.out.println("Running command: " + command + "\n" + RIOdroid.executeCommand(command));
    }
    
    public void speak(String text){
    	shouldSpeak = true;
    	message = text;
    }
    
    public void shouldSeeTarget(boolean bool){
    	shouldSeeTarget = bool;
    }
}

