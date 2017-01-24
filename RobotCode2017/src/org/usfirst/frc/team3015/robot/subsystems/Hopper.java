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
    	rotation.set(0.5);
    }
    
    public void checkForJam(double encoderRaw) {
    	if(rotation.get() > 0 || rotation.get() < 0) {
    		if (deJammer.getRaw() > encoderRaw) { //this is where I think i'm wrong
    			fixJam();
    		}
    	}
    }
    
    public void fixJam() {
    	rotation.set(-0.5);
    }
    
    public void stop() {
    	rotation.set(0);
    }
    
    public double getEncoderRaw() {
    	return deJammer.getRaw();
    }
}

