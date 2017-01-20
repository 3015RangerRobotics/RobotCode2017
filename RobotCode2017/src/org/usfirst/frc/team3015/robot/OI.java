package org.usfirst.frc.team3015.robot;

import edu.wpi.first.wpilibj.buttons.Button;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
import org.usfirst.frc.team3015.robot.commands.*;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	Joystick driver = new Joystick(0);
	Button driverA1 = new JoystickButton(driver, 1);
	Button driverB2 = new JoystickButton(driver, 2);
	Button driverX3 = new JoystickButton(driver, 3);
	Button driverY4 = new JoystickButton(driver, 4);
	Button driverLB5 = new JoystickButton(driver, 5);
	Button driverRB6 = new JoystickButton(driver, 6);
	Button driverSEL7 = new JoystickButton(driver, 7);
	Button driverSTART8 = new JoystickButton(driver, 8);
	Button driverLS9 = new JoystickButton(driver, 9);
	Button driverRS10 = new JoystickButton(driver, 10);
	Button driverDLeft = new DPad(driver, DPad.Value.kDPadLeft);
	Button driverDUp = new DPad(driver, DPad.Value.kDPadUp);
	Button driverDDown = new DPad(driver, DPad.Value.kDPadDown);
	Button driverDRight = new DPad(driver, DPad.Value.kDPadRight);
	Button driverLTrig = new JoystickTrigger(driver,JoystickTrigger.Trigger.kLeftTrigger,.05);
	Button driverRTrig = new JoystickTrigger(driver,JoystickTrigger.Trigger.kRightTrigger,.05);
	
	Joystick coDriver = new Joystick(1);
	Button coDriverA1 = new JoystickButton(coDriver, 1);
	Button coDriverB2 = new JoystickButton(coDriver, 2);
	Button coDriverX3 = new JoystickButton(coDriver, 3);
	Button coDriverY4 = new JoystickButton(coDriver, 4);
	Button coDriverLB5 = new JoystickButton(coDriver, 5);
	Button coDriverRB6 = new JoystickButton(coDriver, 6);
	Button coDriverSEL7 = new JoystickButton(coDriver, 7);
	Button coDriverSTART8 = new JoystickButton(coDriver, 8);
	Button coDriverLS9 = new JoystickButton(coDriver, 9);
	Button coDriverRS10 = new JoystickButton(coDriver, 10);
	Button coDriverDLeft = new DPad(coDriver, DPad.Value.kDPadLeft);
	Button coDriverDUp = new DPad(coDriver, DPad.Value.kDPadUp);
	Button coDriverDDown = new DPad(coDriver, DPad.Value.kDPadDown);
	Button coDriverDRight = new DPad(coDriver, DPad.Value.kDPadRight);
	Button coDriverLTrig = new JoystickTrigger(coDriver,JoystickTrigger.Trigger.kLeftTrigger); 
	Button coDriverRTrig = new JoystickTrigger(coDriver,JoystickTrigger.Trigger.kRightTrigger);
	public OI() {
		driverA1.whenPressed(new DriveTurnToTarget()); 
		//Do not remove, this code is essential to the roboRio. If it is removed, the roboRio will be bricked
		driverB2.whenPressed(new VisionSpeak("Jen is a bully exclamation point exclamation point exclamation point."));
		driverX3.whenPressed(new VisionSpeak("According to all known laws of aviation, there is no way a bee should be able to fly. Its wings are too small to get its fat little body off the ground. The bee, of course, flies anyway because bees dont care what humans think is impossible. Yellow, black. Yellow, black. Yellow, black. Yellow, black. Ooh, black and yellow! Lets shake it up a little. Barry! Breakfast is ready! Coming! Hang on a second. Hello? Barry? Adam? Can you believe this is happening? I cant. Ill pick you up. Looking sharp. Use the stairs. Your father paid good money for those. Sorry. Im excited. Heres the graduate. We are very proud of you, son. A perfect report card, all Bees. Very proud. Ma! I got a thing going here. You got lint on your fuzz. Ow! Thats me! Wave to us! We will be in row 118,000. Bye! Barry, I told you, stop flying in the house! Hey, Adam. Hey, Barry. Is that fuzz gel? A little. Special day, graduation. Never thought Id make it. Three days grade school, three days high school. Those were awkward. Three days college. Im glad I took a day and hitchhiked around the hive. You did come back different."));
		driverY4.whenPressed(new VisionSpeak("The FitnessGram Pacer Test is a multistage aerobic capacity test that progressively gets more difficult as it continues. The 20 meter pacer test will begin in 30 seconds. Line up at the start. The running speed starts slowly, but gets faster each minute after you hear this signal... beep... A single lap should be completed each time you hear this sound... ding... Remember to run in a straight line, and run as long as possible. The second time you fail to complete a lap before the sound, your test is over. The test will begin on the word start. On your mark, get ready, start."));
		driverSTART8.whenPressed(new CG_SystemsCheck());
		driverLTrig.whenPressed(new DriveStrafeWithGamepad());
		driverRTrig.whenPressed(new DriveStrafeWithGamepad());
	}
	
	public double getDriverLeftX(){
		return driver.getRawAxis(0);
	}
	
	public double getDriverLeftY(){
		return driver.getRawAxis(1) * -1;
	}
	
	public double getDriverSumTriggers(){
		return driver.getRawAxis(3)-driver.getRawAxis(2);
	}
}
