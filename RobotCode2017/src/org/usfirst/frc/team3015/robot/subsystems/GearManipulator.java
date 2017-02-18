package org.usfirst.frc.team3015.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
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
    	tilt.set(-0.8);
    }
    
    public void tiltUp(){
    	tilt.set(0.8);
    }
    
    public void tiltStop(){
    	tilt.set(0);
    }
    
    /**
     * Opens the claw
     */
    public void openClaw() {
    	grabber.set(false);
    }
    
    /**
     * Closes the claw
     */
    public void closeClaw() {
    	grabber.set(true);
    }
    
    /**
     * Run intake
     */
    public void intake() {
    	gearIntake.set(INTAKE_SPEED);
    }
    
    /**
     * Reverse intake
     */
    public void outtake() {
    	gearIntake.set(-INTAKE_SPEED);
    }
    
    /**
     * Stop intake
     */
    public void intakeStop() {
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

