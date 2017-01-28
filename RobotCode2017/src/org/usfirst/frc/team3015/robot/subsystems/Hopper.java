package org.usfirst.frc.team3015.robot.subsystems;

import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Hopper for feeding our wheel shooter 1 ball at a time.
 * Contains a anti-jamming system.
 */
public class Hopper extends Subsystem {

    private CANTalon rotation;
    private final double ROTATION_SPEED = 1;
    
    /**
     * Constructs hardware
     */
    public Hopper() {
//    	rotation = new CANTalon();
//    	deJammer = new Encoder();
    }
    
    public void initDefaultCommand() {
//       this.setDefaultCommand(new HopperStop());
    }
    
    public void rotate() {
    	rotation.set(ROTATION_SPEED);
    }
    
    public void reverse() {
    	rotation.set(ROTATION_SPEED * -1);
    }
    
    public void stop() {
    	rotation.set(0);
    }
    
    public double getCurrent(){
    	return rotation.getOutputCurrent();
    }
    
    
}

