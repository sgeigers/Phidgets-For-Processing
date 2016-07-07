package shenkar.phidgets;

/*
 * ToDo:
 *  - add automatic disengage, like DC motor controller.
 */
import com.phidgets.*;
import com.phidgets.event.AttachEvent;
import com.phidgets.event.AttachListener;
import com.phidgets.event.DetachEvent;
import com.phidgets.event.DetachListener;
import com.phidgets.event.ErrorEvent;
import com.phidgets.event.ErrorListener;

import processing.core.PApplet;

public class Servo extends Phid4P5 {

	ServoPhidget ph;
	AdvancedServoPhidget pha;
	
	public static int DEFAULT = com.phidgets.AdvancedServoPhidget.PHIDGET_SERVO_DEFAULT;
	public static int FIRGELLI_L12_30_50_06_R = com.phidgets.AdvancedServoPhidget.PHIDGET_SERVO_FIRGELLI_L12_30_50_06_R;
	public static int FIRGELLI_L12_50_100_06_R = com.phidgets.AdvancedServoPhidget.PHIDGET_SERVO_FIRGELLI_L12_50_100_06_R;
	public static int FIRGELLI_L12_50_210_06_R = com.phidgets.AdvancedServoPhidget.PHIDGET_SERVO_FIRGELLI_L12_50_210_06_R;
	public static int FIRGELLI_L12_100_50_06_R = com.phidgets.AdvancedServoPhidget.PHIDGET_SERVO_FIRGELLI_L12_100_50_06_R;
	public static int FIRGELLI_L12_100_100_06_R = com.phidgets.AdvancedServoPhidget.PHIDGET_SERVO_FIRGELLI_L12_100_100_06_R;
	public static int HITEC_805BB = com.phidgets.AdvancedServoPhidget.PHIDGET_SERVO_HITEC_805BB;
	public static int HITEC_815BB = com.phidgets.AdvancedServoPhidget.PHIDGET_SERVO_HITEC_815BB;
	public static int HITEC_HS322HD = com.phidgets.AdvancedServoPhidget.PHIDGET_SERVO_HITEC_HS322HD;
	public static int HITEC_HS422 = com.phidgets.AdvancedServoPhidget.PHIDGET_SERVO_HITEC_HS422;
	public static int HITEC_HS485HB = com.phidgets.AdvancedServoPhidget.PHIDGET_SERVO_HITEC_HS485HB;
	public static int HITEC_HS5245MG = com.phidgets.AdvancedServoPhidget.PHIDGET_SERVO_HITEC_HS5245MG;
	public static int HITEC_HS645MG = com.phidgets.AdvancedServoPhidget.PHIDGET_SERVO_HITEC_HS645MG;
	public static int HITEC_HS785HB = com.phidgets.AdvancedServoPhidget.PHIDGET_SERVO_HITEC_HS785HB;
	public static int HITEC_HSR1425CR = com.phidgets.AdvancedServoPhidget.PHIDGET_SERVO_HITEC_HSR1425CR;
	public static int SPRINGRC_SM_S2313M = com.phidgets.AdvancedServoPhidget.PHIDGET_SERVO_SPRINGRC_SM_S2313M;
	public static int SPRINGRC_SM_S3317M = com.phidgets.AdvancedServoPhidget.PHIDGET_SERVO_SPRINGRC_SM_S3317M;
	public static int SPRINGRC_SM_S3317SR = com.phidgets.AdvancedServoPhidget.PHIDGET_SERVO_SPRINGRC_SM_S3317SR;
	public static int SPRINGRC_SM_S4303R = com.phidgets.AdvancedServoPhidget.PHIDGET_SERVO_SPRINGRC_SM_S4303R;
	public static int SPRINGRC_SM_S4315M = com.phidgets.AdvancedServoPhidget.PHIDGET_SERVO_SPRINGRC_SM_S4315M;
	public static int SPRINGRC_SM_S4315R = com.phidgets.AdvancedServoPhidget.PHIDGET_SERVO_SPRINGRC_SM_S4315R;
	public static int SPRINGRC_SM_S4505B = com.phidgets.AdvancedServoPhidget.PHIDGET_SERVO_SPRINGRC_SM_S4505B;
	public static int TOWERPRO_MG90 = com.phidgets.AdvancedServoPhidget.PHIDGET_SERVO_TOWERPRO_MG90;
	public static int RAW_US_MODE = com.phidgets.AdvancedServoPhidget.PHIDGET_SERVO_RAW_us_MODE;
	public static int USER_DEFINED = com.phidgets.AdvancedServoPhidget.PHIDGET_SERVO_USER_DEFINED;
	
	boolean advanced = false;

	/**
	 * @example Servo_Example
	 * @param theParent
	 */

	public Servo(PApplet theParent) {
		myParent = theParent;
		myParent.registerMethod("dispose", this);
		try {
			ph = new ServoPhidget();
			ph.addAttachListener(new AttachListener() {
				public void attached(AttachEvent ae) {
					// System.out.println("attachment of " + ae);
				}
			});
			ph.addDetachListener(new DetachListener() {
				public void detached(DetachEvent de) {
					System.out.println("detachment of " + de);
				}
			});
			ph.addErrorListener(new ErrorListener() {
				public void error(ErrorEvent ee) {
					System.out.println("error event for " + ee);
				}
			});
			
			ph.openAny();
			setPhid(ph);
			ph.waitForAttachment(ATTACHMENT_TIMEOUT);
		}
		catch (PhidgetException ex) 
		{
			if (ex.getErrorNumber() == PhidgetException.EPHIDGET_TIMEOUT) {
				// probably advanced servo board attached
//				System.out.println("Advanced");
				advanced = true;
				closePh();
				try {
					pha = new AdvancedServoPhidget();
				}
				catch (PhidgetException exep) {
					System.out.println(exep);
				}
				setPhid(pha);
				phSetup(0);				
			}
			else {
				System.out.println(ex);
			}
		}
	}

	/**
	 * @param theParent
	 * @param ser
	 * 			Serial number of the phidget, acquired by calling printSer when only one of them is connected.
	 */

	public Servo(PApplet theParent, int ser) {
		myParent = theParent;
		myParent.registerMethod("dispose", this);
		try {
			ph = new ServoPhidget();
			ph.addAttachListener(new AttachListener() {
				public void attached(AttachEvent ae) {
					// System.out.println("attachment of " + ae);
				}
			});
			ph.addDetachListener(new DetachListener() {
				public void detached(DetachEvent de) {
					System.out.println("detachment of " + de);
				}
			});
			ph.addErrorListener(new ErrorListener() {
				public void error(ErrorEvent ee) {
					System.out.println("error event for " + ee);
				}
			});
			
			ph.open(ser);
			setPhid(ph);
			ph.waitForAttachment(ATTACHMENT_TIMEOUT);
		}
		catch (PhidgetException ex) 
		{
			if (ex.getErrorNumber() == PhidgetException.EPHIDGET_TIMEOUT) {
				// probably advanced servo board attached
				advanced = true;
				closePh();
				try {
					pha = new AdvancedServoPhidget();
				}
				catch (PhidgetException exep) {
					System.out.println(exep);
				}
				setPhid(pha);
				phSetup(ser);				
			}
			else {
				System.out.println(ex);
			}
		}
	}

	/**
	 * turn servo off
	 * 
	 * @param mot
	 * 				index of the motor
	 * 
	 */
	
	public void turnOff(int mot) {
		try {
			if (advanced) {
				pha.setEngaged(mot, false);
			}
			else {
				ph.setEngaged(mot, false);			
			}
		} catch (PhidgetException ex) {
			System.out.println(ex);
		}
	}

	/**
	 * sets servo position. If the board is "simple", the servo will get to that position as fast as it can.
	 * 	If the board is "advanced", it is possible to set acceleration and velocity using the proper functions.
	 * 
	 * @param mot
	 * 				index of the motor
	 * @param pos
	 * 				desired position
	 * 
	 */

	public void setPosition(int mot, float pos) {
		try {
			if (advanced) {
				if (!pha.getEngaged(mot)) {
					pha.setEngaged(mot,  true);
				}
				pha.setPosition(mot, pos);
			}
			else {
				if (!ph.getEngaged(mot)) {
					ph.setEngaged(mot,  true);
				}
				ph.setPosition(mot, pos);			
			}
		} catch (PhidgetException ex) {
			System.out.println(ex);
		}
	}

	/**
	 * returns the position of the servo.
	 * NOTES:
	 * 		1. If the position hasn't been set yet, the value has no meaning (it is not possible to read the actual position
	 * 			of the servo - the value returned is the value set using "setPosition" function).
	 * 		2. If the servo is turned off, or not turned on yet (by setting a position with "setPosition", the function will
	 * 			return -1.
	 * 
	 * @param mot
	 * 				index of the motor
	 * @return float
	 * 				known position
	 * 
	 */

	public float getPosition(int mot) {
		try {
			if (advanced) {
				if (!pha.getEngaged(mot)) {
					return -1;
				}
				return (float)pha.getPosition(mot);
			}
			else {
				if (!ph.getEngaged(mot)) {
					return -1;
				}
				return (float)ph.getPosition(mot);				
			}
		} catch (PhidgetException ex) {
			System.out.println(ex);
			return -1;
		}
	}

	/**
	 * Sets type of servo. This can help for more precise control of the motor attached.
	 * Possible values for "type" are:
	 * 		DEFAULT - Default - This is what the servo API been historically used, originally based on the Futaba FP-S148.
	 * 		FIRGELLI_L12_30_50_06_R - Firgelli L12 Linear Actuator 100mm 100:1.
	 * 		FIRGELLI_L12_50_100_06_R - Firgelli L12 Linear Actuator 100mm 50:1.
	 * 		FIRGELLI_L12_50_210_06_R - Firgelli L12 Linear Actuator 30mm 50:1.
	 * 		FIRGELLI_L12_100_50_06_R - Firgelli L12 Linear Actuator 50mm 100:1.
	 * 		FIRGELLI_L12_100_100_06_R -  Firgelli L12 Linear Actuator 50mm 210:1.
	 * 		HITEC_805BB - HiTec HS-805BB Mega Quarter Scale Servo.
	 * 		HITEC_815BB -  HiTec HS-815BB Mega Sail Servo.
	 * 		HITEC_HS322HD - HiTec HS-322HD Standard Servo.
	 * 		HITEC_HS422 - HiTec HS-422 Standard Servo.
	 * 		HITEC_HS485HB - HiTec HS-485HB Deluxe Servo.
	 * 		HITEC_HS5245MG - HiTec HS-5245MG Digital Mini Servo.
	 * 		HITEC_HS645MG - HiTec HS-645MG Ultra Torque Servo.
	 * 		HITEC_HS785HB - HiTec HS-785HB Sail Winch Servo.
	 * 		HITEC_HSR1425CR - HiTec HSR-1425CR Continuous Rotation Servo.
	 * 		SPRINGRC_SM_S2313M - SpringRC SM-S2313M Micro Servo.
	 * 		SPRINGRC_SM_S3317M - SpringRC SM-S3317M Small Servo.
	 * 		SPRINGRC_SM_S3317SR - SpringRC SM-S3317SR Small Continuous Rotation Servo.
	 * 		SPRINGRC_SM_S4303R - SpringRC SM-S4303R Standard Continuous Rotation Servo.
	 * 		SPRINGRC_SM_S4315M - SpringRC SM-S4315M High Torque Servo.
	 * 		SPRINGRC_SM_S4315R - SpringRC SM-S4315R High Torque Continuous Rotation Servo.
	 * 		SPRINGRC_SM_S4505B - SpringRC SM-S4505B Standard Servo.
	 * 		TOWERPRO_MG90 - Tower Pro MG90 Micro Servo.
	 * 		 
	 * In addition, two special "types" are possible:
	 * 		RAW_US_MODE - use microseconds instead of degrees when setting motor position. Experimentally, this
	 * 								can lead for exact control of other motor types.
	 * 		USER_DEFINED - can be used for defining specific parameters for a motor with the function "setParameters"
	 * 
	 * 
	 * @param mot
	 * 				index of the motor
	 * @param type
	 * 				type of motor attached
	 * 
	 */

	public void setType(int mot, int type) {
		try {
			if (advanced) {
				pha.setServoType(mot, type);
			}
			else {
				ph.setServoType(mot, type);
			}
			} catch (PhidgetException ex) {
			System.out.println(ex);
		}
	}

	/**
	 * Manually sets motor parameters for precise control of a servo
	 * 
	 * @param mot
	 * 				index of the motor
	 * @param minUs
	 * 				pulse time in microseconds for minimum angle
	 * @param maxUs
	 * 				pulse time in microseconds for maximum angle
	 * @param degrees
	 * 				servo's degrees of rotation (e.g. 180)
	 * 
	 */

	public void setParameters(int mot, float minUs, float maxUs, float degrees) {
		try {
			if (advanced) {
				pha.setServoParameters(mot,  minUs, maxUs, degrees, pha.getVelocityMax(mot));
			}
			else {
				ph.setServoParameters(mot,  minUs, maxUs, degrees);				
			}
		} catch (PhidgetException ex) {
			System.out.println(ex);
		}
	}
	
	/**
	 * for "advanced" boards only.
	 * returns true if the motor is stopped (reached its target)
	 * 
	 * @param mot
	 * 				index of the motor
	 * @return boolean
	 * 				true if stopped
	 * 
	 */

	public boolean stopped(int mot) {
		if (advanced) {
			try {
				return pha.getStopped(mot);
			} catch (PhidgetException ex) {
				System.out.println(ex);
				return false;
			}
		}
		else {
			return false;
		}
	}

	/**
	 * for "advanced" boards only.
	 * set acceleration for motor
	 * 
	 * @param mot
	 * 				index of the motor
	 * @param acc
	 * 				desired acceleration
	 * 
	 */

	public void setAcceleration(int mot, float acc) {
		if (advanced) {
			try {
				pha.setAcceleration(mot, acc);
			} catch (PhidgetException ex) {
				System.out.println(ex);
			}
		}
	}
	
	/**
	 * for "advanced" boards only.
	 * set maximum speed for motor
	 * 
	 * @param mot
	 * 				index of the motor
	 * @param spd
	 * 				desired speed
	 * 
	 */

	public void setSpeed(int mot, float spd) {
		if (advanced) {
			try {
				pha.setVelocityLimit(mot, spd);
			} catch (PhidgetException ex) {
				System.out.println(ex);
			}
		}
	}
	
	/**
	 * for "advanced" boards only.
	 * cancel speed and acceleration control. The motor will go to desired position as fast as possible.
	 * 
	 * @param mot
	 * 				index of the motor
	 * 
	 */

	public void controlOff(int mot) {
		if (advanced) {
			try {
				pha.setSpeedRampingOn(mot, false);
			} catch (PhidgetException ex) {
				System.out.println(ex);
			}
		}
	}
	
	/**
	 * for "advanced" boards only.
	 * enable speed and acceleration control. The motor will go to desired position as fast as possible.
	 * 
	 * @param mot
	 * 				index of the motor
	 * 
	 */

	public void controlOn(int mot) {
		if (advanced) {
			try {
				pha.setSpeedRampingOn(mot, true);
			} catch (PhidgetException ex) {
				System.out.println(ex);
			}
		}
	}
	
	/**
	 *  automatically called when program exists.
	 *  turn off all the servos.
	 *  
	 */
	
	public void dispose() {
		try {
			if (advanced) {
				for (int i=0; i<pha.getMotorCount(); i++) {
					pha.setEngaged(i, false);
				}
			}
			else {
				for (int i=0; i<ph.getMotorCount(); i++) {
					ph.setEngaged(i, false);			
				}
			}
		} catch (PhidgetException ex) {
			System.out.println(ex);
		}
		
	}
	
}
