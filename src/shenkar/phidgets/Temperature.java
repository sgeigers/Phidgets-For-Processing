package shenkar.phidgets;

import processing.core.PApplet;
import com.phidgets.*;

public class Temperature extends Phid4P5 {
	TemperatureSensorPhidget ph;

	/**
	 * @example Temperature_Example
	 * @param theParent
	 */

	public Temperature(PApplet theParent) {
		myParent = theParent;
		myParent.registerMethod("dispose", this);
		try {
			ph = new TemperatureSensorPhidget();
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

	public Temperature(PApplet theParent, int ser) {
		myParent = theParent;
		myParent.registerMethod("dispose", this);
		try {
			ph = new TemperatureSensorPhidget();
		} catch (PhidgetException ex) {
			System.out.println(ex);
		}
		setPhid(ph);
		phSetup(ser);
	}

	/**
	 * Get temperature from sensor.
	 * 
	 * @param pos
	 * 				index of sensor (usually 0, unless there are more than one sensor on the board)
	 * @return float 
	 * 				measured temperature (for PhidgetTemperatureSensor IR: -70..380 Celsius)
	 */

	public float getTemp(int pos) {
		try {
			return (float)ph.getTemperature(pos);
		} catch (PhidgetException ex) {
			System.out.println(ex);
			return -9999;
		}
	}

	/**
	 * Get temperature from sensor at position 0 (for boards with only 1 sensor).
	 * 
	 * @return float 
	 * 				measured temperature (for PhidgetTemperatureSensor IR: -70..380 Celsius)
	 */

	public float getTemp() {
		try {
			return (float)ph.getTemperature(0);
		} catch (PhidgetException ex) {
			System.out.println(ex);
			return -9999;
		}
	}

	/**
	 * Get ambient temperature.
	 * 
	 * @return float 
	 * 				ambient temperature (-40..125 Celsius)
	 */

	public float getAmbient() {
		try {
			return (float)ph.getAmbientTemperature();
		} catch (PhidgetException ex) {
			System.out.println(ex);
			return -9999;
		}
	}
}
