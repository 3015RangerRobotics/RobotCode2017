package org.usfirst.frc.team3015.robot.subsystems;

import org.usfirst.frc.team3015.robot.commands.HopperStop;

import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Hopper for feeding our wheel shooter 1 ball at a time.
 * Contains a anti-jamming system.
 */
public class Hopper extends Subsystem {

    private CANTalon rotation;
    private double rotationSpeed = 0.5;
    
    /**
     * Constructs hardware
     */
    public Hopper() {
//    	rotation = new VictorSP();
//    	deJammer = new Encoder();
    }
    
    public void initDefaultCommand() {
//       this.setDefaultCommand(new HopperStop());
    }
    
    public void rotate() {
    	rotation.set(rotationSpeed);
    }
    
    public void reverse() {
    	rotationSpeed *= -1;
    }
    
    public void stop() {
    	rotation.set(0);
    }
    
    public double getCurrent(){
    	return rotation.getOutputCurrent();
    }
    
    
}

