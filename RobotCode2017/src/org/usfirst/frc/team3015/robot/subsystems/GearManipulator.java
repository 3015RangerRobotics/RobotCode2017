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
	private VictorSP gearIntake;
	private VictorSP tilt;
	private DigitalInput gearDetector;

	private final double INTAKE_SPEED = .8;
	
	public GearManipulator(){
//		grabber = new Solenoid();
//		gearMotor = VictorSP();
//      tilt = new VictorSP();
//		gearDetector = new DigitalInput();
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void tiltDown(){
    	SmartDashboard.putBoolean("isGearUp", false);
    	tilt.set(-0.8);
    }
    
    public void tiltUp(){
    	SmartDashboard.putBoolean("isGearUp", true);
    	tilt.set(0.8);
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
    
    /**
     * Closes the claw
     */
    public void closeClaw() {
    	SmartDashboard.putBoolean("isClawOpen", false);
    	grabber.set(true);
    }
    
    /**
     * Run intake
     */
    public void intake() {
    	SmartDashboard.putBoolean("isIntakeRunning", true);
    	gearIntake.set(INTAKE_SPEED);
    }
    
    /**
     * Reverse intake
     */
    public void outtake() {
    	SmartDashboard.putBoolean("isIntakeRunning", true);
    	gearIntake.set(-INTAKE_SPEED);
    }
    
    /**
     * Stop intake
     */
    public void intakeStop() {
    	SmartDashboard.putBoolean("isIntakeRunning", false);
    	gearIntake.set(0);
    }
    
    /**
     * Checks to see if gear is present
     * @return true/false if gear is present
     */
    public boolean getIsGearPresent() {
    	return gearDetector.get();
    }
    
    public boolean isClawOpen() {
    	return grabber.get();
    }
}

