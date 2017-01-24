package org.usfirst.frc.team3015.robot.subsystems;

import org.usfirst.frc.team3015.robot.commands.ClimberClimbUp;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Climber extends Subsystem {
	private VictorSP leftClimbMotor;
	private VictorSP rightClimbMotor;
	private Encoder climbEncoder;
	
	public Climber() {
//		leftClimbMotor = new VictorSP(3); //temp SP numbers
//		rightClimbMotor = new VictorSP(4);
	}
	
	@Override
	protected void initDefaultCommand() {
//		setDefaultCommand(new ClimbUp());
	}
	
	public void stopClimb(){
		leftClimbMotor.set(0);
		rightClimbMotor.set(0);
	}
	
	public void climbUp() {
//		leftClimbMotor.set(.8);
//		rightClimbMotor.set(.8);
	}
	
	
	public void climbDown() {
//		leftClimbMotor.set(-.8);
//		rightClimbMotor.set(-.8);
	}
	

}
