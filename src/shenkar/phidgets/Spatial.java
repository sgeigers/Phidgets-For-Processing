package shenkar.phidgets;

import com.phidgets.*;
import processing.core.PApplet;

public class Spatial extends Phid4P5 {

	private double[] gravity;
	private double[] magField;
	private double rollAngle;
	private double pitchAngle;
	private double yawAngle;
	private long lastCalcTime;
		
	SpatialPhidget ph;

	/**
	 * @example Encoder_Example
	 * @param theParent
	 */

	public Spatial(PApplet theParent) {
		myParent = theParent;
		myParent.registerMethod("dispose", this);
		try {
			ph = new SpatialPhidget();
		} catch (PhidgetException ex) {
			System.out.println(ex);
		}
		gravity = new double[3];
		magField = new double[3];
		lastCalcTime = 0;
		setPhid(ph);
		phSetup(0);
	}

	/**
	 * @param theParent
	 * @param ser
	 * 			Serial number of the phidget, acquired by calling printSer when only one of them is connected.
	 */

	public Spatial(PApplet theParent, int ser) {
		myParent = theParent;
		myParent.registerMethod("dispose", this);
		try {
			ph = new SpatialPhidget();
		} catch (PhidgetException ex) {
			System.out.println(ex);
		}
		gravity = new double[3];
		magField = new double[3];
		lastCalcTime = 0;
		setPhid(ph);
		phSetup(ser);
	}

	/**
	 * Get acceleration in a specific axis
	 *
	 * @param axis
	 * 				axis to read (0, 1 or 2)
	 * 
	 * @return float 
	 * 				acceleration magnitude (g)
	 */

	public float getAcceleration(int axis) {
		try {
			if (ph.getAccelerationAxisCount()>axis) {
				return (float)ph.getAcceleration(axis);
			}
			else {
				System.out.println("Connected board doesn't have accelerometer axis number "+axis);
				return 0;
			}
		} catch (PhidgetException ex) {
			System.out.println(ex);
			return 0;
		}
	}

	/**
	 * Get rotation rate (gyro) in a specific axis
	 *
	 * @param axis
	 * 				axis to read (0, 1 or 2)
	 * 
	 * @return float 
	 * 				rotation magnitude (deg/sec)
	 */

	public float getRotationRate(int axis) {
		try {
			if (ph.getGyroAxisCount()>axis) {
				return (float)ph.getAngularRate(axis);
			}
			else {
				System.out.println("Connected board doesn't have gyro axis number "+axis);
				return 0;
			}
		} catch (PhidgetException ex) {
			System.out.println(ex);
			return 0;
		}
	}
	
	/**
	 * Get magnetic field in a specific axis
	 *
	 * @param axis
	 * 				axis to read (0, 1 or 2)
	 * 
	 * @return float 
	 * 				magnetic field (G)
	 */

	public float getMagneticField(int axis) {
		try {
			if (ph.getCompassAxisCount()>axis) {
				return (float)ph.getMagneticField(axis);
			}
			else {
				System.out.println("Connected board doesn't have compass axis number "+axis);
				return 0;
			}
		} catch (PhidgetException ex) {
			System.out.println(ex);
			return 0;
		}
	}

	private void calcHeading() {
		try {
			if (System.currentTimeMillis() - lastCalcTime >= 4) {
				if ((ph.getCompassAxisCount()==3) && (ph.getAccelerationAxisCount()==3)) {
					gravity[0] = ph.getAcceleration(0);
					gravity[1] = ph.getAcceleration(1);
					gravity[2] = ph.getAcceleration(2);
					
					magField[0] = ph.getMagneticField(0);
					magField[1] = ph.getMagneticField(1);
					magField[2] = ph.getMagneticField(2);
					
					rollAngle = Math.atan2(gravity[1], gravity[2]);
					
					pitchAngle = Math.atan(-gravity[0] / ((gravity[1] * Math.sin(rollAngle)) + (gravity[2] * Math.cos(rollAngle))));
					
					yawAngle = Math.atan2((magField[2] * Math.sin(rollAngle)) - (magField[1] * Math.cos(rollAngle)),
							   (magField[0] * Math.cos(pitchAngle)) + (magField[1] * Math.sin(pitchAngle) * Math.sin(rollAngle)) + (magField[2] * Math.sin(pitchAngle) * Math.cos(rollAngle)));

					lastCalcTime = System.currentTimeMillis();
				}
			}
		} catch (PhidgetException ex) {
			System.out.println(ex);
		}
	}
	
	/**
	 * Get pitch about axis 1 - calculated from accelerometer data
	 * 
	 * @return float 
	 * 				pitch (deg)
	 */

	public float getPitch() {
		calcHeading();
		return (float)(pitchAngle*180.0/Math.PI);
	}
	
	/**
	 * Get roll about axis 0 - calculated from accelerometer data
	 * 
	 * @return float 
	 * 				roll (deg)
	 */

	public float getRoll() {
		calcHeading();
		return (float)(rollAngle*180.0/Math.PI);
	}
	
	/**
	 * Get yaw about axis 2 - calculated from magnetometer data, taking into affect the acceleration data
	 * 
	 * @return float 
	 * 				pitch (deg)
	 */

	public float getYaw() {
		calcHeading();
		return (float)(yawAngle*180.0/Math.PI);
	}
	
	/**
	 * Initialize gyro. The board need to be stand still for 2 seconds.
	 * 
	 */

	public void initGyro() {
		try {
			ph.zeroGyro();
		} catch (PhidgetException ex) {
			System.out.println(ex);
		}
	}
	
}
