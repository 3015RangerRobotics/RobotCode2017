package org.usfirst.frc.team3015.robot.subsystems;

import org.usfirst.frc.team3015.robot.commands.HopperCheckAndResolveJam;

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
    private final double PROCESS_SPEED = 0.5;
    
    /**
     * Constructs hardware
     */
    public Hopper() {
//    	rotation = new VictorSP();
//    	deJammer = new Encoder();
    }
    
    public void initDefaultCommand() {
        setDefaultCommand(new HopperCheckAndResolveJam());
    }
    
    public void rotate() {
    	rotation.set(PROCESS_SPEED);
    }
    
    public void checkForJam() {
    	if(rotation.get() > 0 || rotation.get() < 0) {
    		if (deJammer.getRate() == 0) { 
    			fixJam();
    		}
    	}
    }
    
    public void fixJam() {
    	rotation.set(-1*rotation.get());
    }
    
    public void stop() {
    	rotation.set(0);
    }
    
    public double getEncoderRaw() {
    	return deJammer.getRaw();
    }
}

