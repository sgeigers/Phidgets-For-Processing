package shenkar.phidgets;

import com.phidgets.*;
import processing.core.PApplet;

public class GPS extends Phid4P5 {
	GPSPhidget ph;

	/**
	 * @example GPS_Example
	 * @param theParent
	 */

	public GPS(PApplet theParent) {
		myParent = theParent;
		myParent.registerMethod("dispose", this);
		try {
			ph = new GPSPhidget();
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

	public GPS(PApplet theParent, int ser) {
		myParent = theParent;
		myParent.registerMethod("dispose", this);
		try {
			ph = new GPSPhidget();
		} catch (PhidgetException ex) {
			System.out.println(ex);
		}
		setPhid(ph);
		phSetup(ser);
	}

	/**
	 * Get current altitude, in meters
	 * 
	 * @return float 
	 * 				altitude (meters)
	 */

	public float altitude() {
		if (positionFix()) {
			try {
				return (float)ph.getAltitude();
			} catch (PhidgetException ex) {
				System.out.println(ex);
				return -9999;
			}
		}
		else {
			return -9999;
		}
	}
	
	/**
	 * Get current heading, in degrees
	 * 
	 * @return float 
	 * 				heading (degrees)
	 */

	public float heading() {
		if (positionFix()) {
			try {
				return (float)ph.getHeading();
			} catch (PhidgetException ex) {
				System.out.println(ex);
				return -9999;
			}
		}
		else {
			return -9999;
		}
	}
	/**
	 * Get current latitude, in signed degrees
	 * 
	 * @return float 
	 * 				altitude (degrees)
	 */

	public float latitude() {
		if (positionFix()) {
			try {
				return (float)ph.getLatitude();
			} catch (PhidgetException ex) {
				System.out.println(ex);
				return -9999;
			}
		}
		else {
			return -9999;
		}
	}
	
	/**
	 * Get current longitude, in signed degrees
	 * 
	 * @return float 
	 * 				longitude (degrees)
	 */

	public float longitude() {
		if (positionFix()) {
			try {
				return (float)ph.getLongitude();
			} catch (PhidgetException ex) {
				System.out.println(ex);
				return -9999;
			}
		}
		else {
			return -9999;
		}
	}
	
	/**
	 * Get current velocity (speed of motion), in Km/h
	 * 
	 * @return float 
	 * 				velocity (Km/h)
	 */

	public float velocity() {
		if (positionFix()) {
			try {
				return (float)ph.getVelocity();
			} catch (PhidgetException ex) {
				System.out.println(ex);
				return -9999;
			}
		}
		else {
			return -9999;
		}
	}
	
	/**
	 * Return true if a GPS got position fix (information can be read from it).
	 * 
	 * @return boolean 
	 * 				true = position fix 
	 */

	public boolean positionFix() {
		try {
			return ph.getPositionFixStatus();
		} catch (PhidgetException ex) {
			System.out.println(ex);
			return false;
		}
	}
}
