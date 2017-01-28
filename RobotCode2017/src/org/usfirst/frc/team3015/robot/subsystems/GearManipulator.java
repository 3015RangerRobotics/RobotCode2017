package org.usfirst.frc.team3015.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;

/**
 *
 */
public class GearManipulator extends Subsystem {
	DoubleSolenoid clawSolenoid;
	DigitalInput gearDetector;
	DigitalInput test;
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	public GearManipulator(){
//		clawSolenoid = new DoubleSolenoid();
//		gearDetector = new DigitalInput();
		test = new DigitalInput(0);
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    /**
     * this opens the claw
     */
    public void openClaw() {
    	clawSolenoid.set(DoubleSolenoid.Value.kForward);
    }
    /**
     * this closes the claw
     */
    public void closeClaw() {
    	clawSolenoid.set(DoubleSolenoid.Value.kReverse);
    }
    
    public boolean getTest(){
    	return test.get();
    }
}

