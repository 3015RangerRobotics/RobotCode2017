package org.usfirst.frc.team3015.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DigitalInput;

/**
 *
 */
public class GearManipulator extends Subsystem {
	DoubleSolenoid clawSolenoid;
	DigitalInput gearDetector;
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    /**
     * this opens the claw
     */
    public void openClaw() {
    	
    }
    /**
     * this closes the claw
     */
    public void closeClaw() {
    	
    }
    
    
}

