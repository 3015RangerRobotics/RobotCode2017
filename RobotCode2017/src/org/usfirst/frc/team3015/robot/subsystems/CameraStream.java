package org.usfirst.frc.team3015.robot.subsystems;

import org.usfirst.frc.team3015.robot.commands.StreamFrontCamera;

import edu.wpi.cscore.MjpegServer;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class CameraStream extends Subsystem {
	UsbCamera frontCamera;
	UsbCamera backCamera;
	MjpegServer server;
	
	public CameraStream(){
//		frontCamera = new UsbCamera("USB Camera 0", 0);
//		backCamera = new UsbCamera("Back", 1);
//		server = new MjpegServer("Server", 1180);
//		server.setSource(frontCamera);
//		frontCamera = CameraServer.getInstance().startAutomaticCapture(0);
//		frontCamera.setResolution(854, 480);
//		backCamera = CameraServer.getInstance().startAutomaticCapture(1);
//		backCamera.setResolution(854, 480);
//		server = CameraServer.getInstance().addServer("cameraStream", 1180);
//		server.setSource(frontCamera);
//		SmartDashboard.putBoolean("isFront", true);
	}

    public void initDefaultCommand() {
//    	this.setDefaultCommand(new StreamFrontCamera());
    }
    
    public void streamFront(){
//    	frontCamera = CameraServer.getInstance().startAutomaticCapture(0);
//		frontCamera.setResolution(854, 480);
//		server = CameraServer.getInstance().addServer("cameraStream", 1180);
    	server.setSource(frontCamera);
    	SmartDashboard.putBoolean("refreshStream", true);
    	SmartDashboard.putBoolean("isFront", true);
    }
    
    public void streamBack(){
//    	backCamera = CameraServer.getInstance().startAutomaticCapture(1);
//		backCamera.setResolution(854, 480);
//		server = CameraServer.getInstance().addServer("cameraStream", 1180);
    	server.setSource(backCamera);
    	SmartDashboard.putBoolean("refreshStream", true);
    	SmartDashboard.putBoolean("isFront", false);
    }
}

