package org.usfirst.frc.team3015.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.DigitalInput;

/**
 *
 */
public class GearManipulator extends Subsystem {
	private DoubleSolenoid clawActuator;
	private DoubleSolenoid tiltActuator;
	private VictorSP gearIntake;
	private DigitalInput gearDetector;

	private final double INTAKE_SPEED = .8;
	private final double INTAKE_REVERSE_SPEED = -.5;
	
	public GearManipulator(){
//		clawActuator = new DoubleSolenoid();
//		tiltActuator = new DoubleSolenoid();
//		gearMotor = VictorSP();
//		gearDetector = new DigitalInput();
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    /**
     * Opens the claw
     */
    public void openClaw() {
    	clawActuator.set(DoubleSolenoid.Value.kForward);
    }
    
    /**
     * Closes the claw
     */
    public void closeClaw() {
    	clawActuator.set(DoubleSolenoid.Value.kReverse);
    }
    
    /**
     * Send manipulator outside the frame
     */
    public void tiltDown() {
    	tiltActuator.set(DoubleSolenoid.Value.kReverse);
    }
    
    /**
     * Send manipulator inside the frame
     */
    public void tiltUp() {
    	tiltActuator.set(DoubleSolenoid.Value.kForward);
    }
    
    /**
     * Run intake
     */
    public void intakeForward() {
    	gearIntake.set(INTAKE_SPEED);
    }
    
    /**
     * Reverse intake
     */
    public void intakeReverse() {
    	gearIntake.set(INTAKE_REVERSE_SPEED);
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
}

