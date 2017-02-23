package org.usfirst.frc.team3015.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.DigitalInput;

/**
 *
 */
public class GearManipulator extends Subsystem {
	private Solenoid grabber;
	private Solenoid grabber2;
	private VictorSP gearIntake;
	private VictorSP tilt;
	private DigitalInput upLimit;
	private DigitalInput downLimit;

	private final double INTAKE_SPEED = 0.8;
	
	public GearManipulator(){
		grabber = new Solenoid(4);
		grabber2 = new Solenoid(5);
		gearIntake = new VictorSP(5);
        tilt = new VictorSP(7);
        upLimit = new DigitalInput(6);
        downLimit = new DigitalInput(7);
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void tiltDown(){
    	SmartDashboard.putBoolean("isGearUp", false);
    	if(downLimit.get()){
    		tilt.set(-0.5);
    		System.out.println("down");
    	}else{
    		tilt.set(0.0);
    		System.out.println("stay");
    	}
    	System.out.println(downLimit.get());
    }
    
    public void tiltUp(){
    	SmartDashboard.putBoolean("isGearUp", true);
    	if(upLimit.get()){
    		tilt.set(1.0);
    		System.out.println("up");
    	}else{
    		tilt.set(0);
    		System.out.println("stay");
    	}
    	System.out.println(upLimit.get());
    }
    
    public void tiltStop(){
    	tilt.set(0);
    }
    
    /**
     * Opens the claw
     */
    public void openClaw() {
    	SmartDashboard.putBoolean("isClawOpen", true);
    	grabber.set(false);
    }
    
    public void openClaw2() {
    	grabber2.set(false);
    }
    /**
     * Closes the claw
     */
    public void closeClaw() {
    	SmartDashboard.putBoolean("isClawOpen", false);
    	grabber.set(true);
    }
    
    public void closeClaw2() {
    	grabber2.set(true);
    }
    /**
     * Run intake
     */
    public void intake() {
    	SmartDashboard.putBoolean("isGearIntakeRunning", true);
    	gearIntake.set(-1*INTAKE_SPEED);
    }
    
    /**
     * Reverse intake
     */
    public void outtake() {
    	SmartDashboard.putBoolean("isGearIntakeRunning", true);
    	gearIntake.set(INTAKE_SPEED);
    }
    
    /**
     * Stop intake
     */
    public void intakeStop() {
    	SmartDashboard.putBoolean("isIntakeRunning", false);
    	gearIntake.set(0);
    }
    
    public boolean isClawOpen() {
    	return grabber.get();
    }
}

