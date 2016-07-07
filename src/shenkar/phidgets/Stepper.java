package shenkar.phidgets;

import com.phidgets.*;
import processing.core.PApplet;

public class Stepper extends Phid4P5 {
	StepperPhidget ph;

	/**
	 * WARNING:
	 * Make sure the power supply is unplugged before attaching or removing wires from the terminal blocks.
	 * Failure to do so could cause permanent damage to the PhidgetStepper board. 
	 * 
	 * @example Stepper_Example
	 * @param theParent
	 */

	public Stepper(PApplet theParent) {
		myParent = theParent;
		myParent.registerMethod("dispose", this);
		try {
			ph = new StepperPhidget();
		} catch (PhidgetException ex) {
			System.out.println(ex);
		}
		setPhid(ph);
		phSetup(0);
	}

	/**
	 * @param theParent
	 * @param ser
	 * 			Serial number of the phidget, acquired by calling printSer when only one of them is connected.
	 */

	public Stepper(PApplet theParent, int ser) {
		myParent = theParent;
		myParent.registerMethod("dispose", this);
		try {
			ph = new StepperPhidget();
		} catch (PhidgetException ex) {
			System.out.println(ex);
		}
		setPhid(ph);
		phSetup(ser);
	}

	/**
	 * set motor maximum speed
	 * It's advised to test best speed in the boards control panel (in Phidget Control Panel)
	 * 
	 * @param mot
	 * 				index of the motor
	 * 
	 * @param spd
	 * 				max speed in micro-steps per second (0..250000)
	 */

	public void setSpeed(int mot, float spd) {
		try {
			ph.setVelocityLimit(mot, spd);
		} catch (PhidgetException ex) {
			System.out.println(ex);
		}
	}

	/**
	 * Set motor acceleration (how fast the motor will change speed).
	 * It's advised to test best acceleration in the boards control panel (in Phidget Control Panel)
	 * 
	 * @param mot
	 * 				index of the motor
	 * 
	 * @param acc
	 * 				acceleration in micro-step per second square (0..10000000)
	 */

	public void setAcceleration(int mot, float acc) {
		try {
			ph.setAcceleration(mot, acc);
		} catch (PhidgetException ex) {
			System.out.println(ex);
		}
	}

	/**
	 * Set current limit.
	 * Current limit sets how strong the motor can act, but setting it too high might damage the motor or the board.
	 * Best if set by the specification of the motor. Otherwise - you can test with different values when your motor is 
	 *  connected to your system and setup with high physical load. If the motor or board get too hot to touch - set
	 *  the current limit to lower value. If it's too weak, you can try to put it higher, or lower the acceleration or
	 *  maxSpeed. 
	 * 
	 * @param mot
	 * 				index of the motor
	 * 
	 * @param crnt
	 * 				max current in amperes (0..4)
	 */

	public void setCurrentLimit(int mot, float crnt) {
		try {
			ph.setCurrentLimit(mot, crnt);
		} catch (PhidgetException ex) {
			System.out.println(ex);
		}
	}

	/**
	 * Set target position. If engaged, The motor will rotate from current position to target position, using acceleration,
	 * 	speed and current limit set using proper functions.
	 * 
	 * @param mot
	 * 				index of the motor
	 * 
	 * @param trgt
	 * 				acceleration in micro-step per second square (0..10000000)
	 */

	public void setTarget(int mot,long trgt) {
		try {
			ph.setTargetPosition(mot, trgt);
		} catch (PhidgetException ex) {
			System.out.println(ex);
		}
	}

	/**
	 * Get current position of the motor
	 * 
	 * @param mot
	 * 				index of the motor
	 * 
	 * @return long
	 * 				position
	 */

	public long getPosition(int mot) {
		try {
			return ph.getCurrentPosition(mot);
		} catch (PhidgetException ex) {
			System.out.println(ex);
			return 0;
		}
	}

	/**
	 * Set current position of the motor. If needed to reset it or preset it to any value.
	 * 
	 * @param mot
	 * 				index of the motor
	 * 
	 * @param pos
	 * 				desired position
	 */

	public void setPosition(int mot, long pos) {
		try {
			ph.setCurrentPosition(mot, pos);
		} catch (PhidgetException ex) {
			System.out.println(ex);
		}
	}

	/**
	 * Engage a motor (must be called once before running it).
	 * 
	 * @param mot
	 * 				index of the motor
	 * 
	 */

	public void engage(int mot) {
		try {
			ph.setEngaged(mot, true);
		} catch (PhidgetException ex) {
			System.out.println(ex);
		}
	}

	/**
	 * Disengage a motor (for example, to let it free-rotate).
	 * 
	 * @param mot
	 * 				index of the motor
	 * 
	 */

	public void disEngage(int mot) {
		try {
			ph.setEngaged(mot, false);
		} catch (PhidgetException ex) {
			System.out.println(ex);
		}
	}

	/**
	 * Return true if the motor is stopped
	 * 
	 * @param mot
	 * 				index of the motor
	 * 
	 * @return boolean
	 * 				true if motor is stopped
	 */

	public boolean stopped(int mot) {
		try {
			return ph.getStopped(mot);
		} catch (PhidgetException ex) {
			System.out.println(ex);
			return false;
		}
	}

	/**
	 *  automatically called when program exists.
	 *  disengages all the motors.
	 *  
	 */
	public void dispose() {
		try {
			for (int i=0; i<ph.getMotorCount(); i++) {
				ph.setEngaged(i, false);
			}
		} catch (PhidgetException ex) {
			System.out.println(ex);
		}
		
	}

}
