package org.usfirst.frc.team3015.robot.subsystems;

import org.usfirst.frc.team3015.robot.commands.HopperRetract;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Hopper extends Subsystem {
	private DoubleSolenoid extension;
	
	public Hopper(){
		extension = new DoubleSolenoid(6, 7);
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	this.setDefaultCommand(new HopperRetract());
    }
    
    public void extend(){
    	extension.set(DoubleSolenoid.Value.kForward);
    }
    
    public void retract(){
    	extension.set(DoubleSolenoid.Value.kReverse);
    }
}

