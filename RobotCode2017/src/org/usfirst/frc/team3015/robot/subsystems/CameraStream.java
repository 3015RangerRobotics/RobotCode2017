package org.usfirst.frc.team3015.robot.subsystems;

import edu.wpi.cscore.MjpegServer;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class CameraStream extends Subsystem {
	UsbCamera frontCamera;
	UsbCamera backCamera;
	MjpegServer server;
	
	public CameraStream(){
		frontCamera = new UsbCamera("Front", 0);
		backCamera = new UsbCamera("Back", 1);
		server = new MjpegServer("Server", 1181);
	}

    public void initDefaultCommand() {
    	
    }
    
    public void streamFront(){
    	server.setSource(frontCamera);
    }
    
    public void streamBack(){
    	server.setSource(backCamera);
    }
}

