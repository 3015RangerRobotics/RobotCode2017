package org.usfirst.frc.team3015.robot.subsystems;

import org.usfirst.frc.team3015.robot.commands.HopperStop;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Hopper for feeding our wheel shooter 1 ball at a time.
 * Contains a anti-jamming system.
 */
public class Hopper extends Subsystem {

    private VictorSP rotation;
    private Encoder deJammer;
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
    
    public double getEncoderRate(){
    	return deJammer.getRate();
    }
    
    public double getEncoder() {
    	return deJammer.get();
    }
}

