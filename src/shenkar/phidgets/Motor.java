package shenkar.phidgets;

/*
 * ToDo:
 * - Add backEMF functions.
 * 
 */
import com.phidgets.*;
import processing.core.PApplet;

public class Motor extends Phid4P5{

	MotorControlPhidget ph;

	/**
	 * @example Motor_Example
	 * @param theParent
	 */

	public Motor(PApplet theParent) {
		myParent = theParent;
		myParent.registerMethod("dispose", this);
		try {
			ph = new MotorControlPhidget();
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

	public Motor(PApplet theParent, int ser) {
		myParent = theParent;
		myParent.registerMethod("dispose", this);
		try {
			ph = new MotorControlPhidget();
		} catch (PhidgetException ex) {
			System.out.println(ex);
		}
		setPhid(ph);
		phSetup(ser);
	}

	/**
	 * set motor speed
	 * 
	 * @param mot
	 * 				index of the motor
	 * 
	 * @param spd
	 * 				speed
	 */

	public void setSpeed(int mot, float spd) {
		try {
			ph.setVelocity(mot, spd);
		} catch (PhidgetException ex) {
			System.out.println(ex);
		}
	}

	/**
	 * set motor acceleration (how fast the motor will change speed)
	 * 
	 * @param mot
	 * 				index of the motor
	 * 
	 * @param acc
	 * 				acceleration
	 */

	public void setAcceleration(int mot, float acc) {
		try {
			ph.setAcceleration(mot, acc);
		} catch (PhidgetException ex) {
			System.out.println(ex);
		}
	}

	/**
	 * get position of encoder. The position can be reset with setEncoderPosition
	 * 
	 * @return int 
	 * 				position of the encoder
	 */

	public int getEncoderPosition() {
		try {
			return ph.getEncoderPosition(0);
		} catch (PhidgetException ex) {
			System.out.println(ex);
			return 0;
		}
	}

	/**
	 * sets position of encoder.
	 * 
	 * @param pos
	 * 				new position
	 * 
	 */

	public void setEncoderPosition(int pos) {
		try {
			ph.setEncoderPosition(0, pos);
		} catch (PhidgetException ex) {
			System.out.println(ex);
		}
	}

	/**
	 * return motor usage of current
	 * 
	 * @param mot
	 *            	index of motor
	 * 
	 * @return float 
	 * 				true if input is high
	 */

	public float current(int mot) {
		try {
			return (float)ph.getCurrent(mot);
		} catch (PhidgetException ex) {
			System.out.println(ex);
			return 0;
		}
	}

	/**
	 * sets braking force to be activated when speed is 0.
	 * 
	 * @param mot
	 * 				index of the encoder
	 * @param brk
	 * 				breaking force (0..100)
	 * 
	 */

	public void setBraking(int mot, float brk) {
		try {
			ph.setBraking(mot, brk);
		} catch (PhidgetException ex) {
			System.out.println(ex);
		}
	}

	/**
	 * get value of sensor attached to analog position pos of interface kit
	 * 
	 * @param pos
	 * 				the position where the sensor is attached
	 * 
	 * @return int 
	 * 				value read by sensor (1..1000)
	 */

	public int analogRead(int pos) {
		try {
			return ph.getSensorValue(pos);
		} catch (PhidgetException ex) {
			System.out.println(ex);
			return 0;
		}
	}

	/**
	 * return true if input attached to digital input position pos is high.
	 * otherwise - return false
	 * 
	 * @param pos
	 *            	the position where the input is attached
	 * 
	 * @return boolean 
	 * 				true if input is high
	 */

	public boolean digitalRead(int pos) {
		try {
			return ph.getInputState(pos);
		} catch (PhidgetException ex) {
			System.out.println(ex);
			return false;
		}
	}

	/**
	 *  automatically called when program exists.
	 *  stops all the motors.
	 *  
	 */
	public void dispose() {
		try {
			for (int i=0; i<ph.getMotorCount(); i++) {
				ph.setVelocity(i, 0);
			}
		} catch (PhidgetException ex) {
			System.out.println(ex);
		}
		
	}
	
}
