package org.usfirst.frc.team3015.robot.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ShooterFeeder extends Subsystem {
	private VictorSP ballFeeder;
	private Encoder ballFeederEncoder;

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

	public ShooterFeeder(){
//		ballFeeder = new VictorSP(3);
//    	ballFeederEncoder = new Encoder(8, 9);
		
	}
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    public void setBallFeeder(double speed) {
    	ballFeeder.set(speed);
    }
}

