package shenkar.phidgets;

import com.phidgets.*;
import processing.core.PApplet;
import java.lang.Math.*;

public class LED extends Phid4P5 {
	LEDPhidget ph;

	/**
	 * @example LED_Example
	 * @param theParent
	 */

	public LED(PApplet theParent) {
		myParent = theParent;
		myParent.registerMethod("dispose", this);
		try {
			ph = new LEDPhidget();
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

	public LED(PApplet theParent, int ser) {
		myParent = theParent;
		myParent.registerMethod("dispose", this);
		try {
			ph = new LEDPhidget();
		} catch (PhidgetException ex) {
			System.out.println(ex);
		}
		setPhid(ph);
		phSetup(ser);
	}

	/**
	 * Get brightness of an LED
	 * 
	 * @param led
	 * 				index of LED (for 64-LED board, 0..63)
	 * @return float 
	 * 				brightness (0..100)
	 */

	public float getBrightness(int led) {
		try {
			return (float)ph.getBrightness(led);
		} catch (PhidgetException ex) {
			System.out.println(ex);
			return -9999;
		}
	}

	/**
	 * Set brightness for an LED.
	 * 
	 * @param led
	 * 				index of LED (for 64-LED board, 0..63)
	 * @param brt 
	 * 				brightness (0..100)
	 */

	public void setBrightness(int led, float brt) {
		try {
			if (brt<0) brt=0;
			if (brt>100) brt=100;
			ph.setBrightness(led, brt);
		} catch (PhidgetException ex) {
			System.out.println(ex);
		}
	}

	/**
	 * Set brightness for an LED. This function changes the given brightness value and runs a function to change it so the scale
	 * 	of 0..100 represents a better brightness for the human eye.
	 * 
	 * @param led
	 * 				index of LED (for 64-LED board, 0..63)
	 * @param brt 
	 * 				brightness (0..100)
	 */

	public void setLinearBrightness(int led, float brt) {
		try {
			if (brt<0) brt=0;
			if (brt>100) brt=100;
			brt = (float)((Math.exp(brt/100.0*Math.log(100.0))-1.0)/99.0*100.0);
			ph.setBrightness(led, brt);
		} catch (PhidgetException ex) {
			System.out.println(ex);
		}
	}

	/**
	 * Set current limit for a specific LED. This is a possibly more efficient way for controlling brightness, but for some LEDs
	 *  might cause instability.
	 *  In addition, note that each type of LED has its own current limit. If driven with higher current, the LED might be
	 *  damaged.
	 *  If specification of the LEDs are not known, it is best to leave to default, and control brightness with "setBrightness"
	 * 
	 * @param led
	 * 				index of LED (for 64-LED board, 0..63)
	 * @param lmt 
	 * 				current limit in milliAmperes (0..80)
	 */

	public void setCurrentLimit(int led, float lmt) {
		try {
			if (lmt<0) lmt=0;
			if (lmt>80) lmt=80;
			ph.setCurrentLimit(led, lmt);
		} catch (PhidgetException ex) {
			System.out.println(ex);
		}
	}

	/**
	 * Get current limit for a specific LED.
	 * 
	 * @param led
	 * 				index of LED (for 64-LED board, 0..63)
	 * @return float 
	 * 				current limit in milliAmperes (20..80)
	 */

	public float getCurrentLimit(int led) {
		try {
			return (float)ph.getCurrentLimit(led);
		} catch (PhidgetException ex) {
			System.out.println(ex);
			return -9999;
		}
	}

	/**
	 * Set voltage for all LEDs. This should be matched to the LEDs being used. If you don't know the LEDs specifications,
	 * 	you can set voltage to 1.7V, see if the LEDs light well, and if not, raise to 2.75V and so on. Setting to highest
	 * 	possible voltage (5V) when not needed will result in more power loss and lower stability (possible over-heat shutdowns)
	 * 
	 * @param vol 
	 * 				voltage (1.7, 2.75, 3.9 or 5)
	 */

	public void setVotage(float vol) {
		try {
			if (vol<2.2025) {
				ph.setVoltage(LEDPhidget.PHIDGET_LED_VOLTAGE_1_7V);
			}
			else if (vol<3.325) {
				ph.setVoltage(LEDPhidget.PHIDGET_LED_VOLTAGE_2_75V);
			}
			else if (vol<4.45) {
				ph.setVoltage(LEDPhidget.PHIDGET_LED_VOLTAGE_3_9V);
			}
			else {
				ph.setVoltage(LEDPhidget.PHIDGET_LED_VOLTAGE_5_0V);
			}
		} catch (PhidgetException ex) {
			System.out.println(ex);
		}
	}

	/**
	 * Get voltage for all LEDs. 
	 * 
	 * @return float 
	 * 				voltage (1.7, 2.75, 3.9 or 5)
	 */

	public float getVotage() {
		try {
			switch (ph.getVoltage()) {
				case LEDPhidget.PHIDGET_LED_VOLTAGE_1_7V:
					return 1.7f;
				case LEDPhidget.PHIDGET_LED_VOLTAGE_2_75V:
					return 2.75f;
				case LEDPhidget.PHIDGET_LED_VOLTAGE_3_9V:
					return 3.9f;
				case LEDPhidget.PHIDGET_LED_VOLTAGE_5_0V:
					return 5;
				default:
					return -9999;
			}
		} catch (PhidgetException ex) {
			System.out.println(ex);
			return -9999;
		}
	}
}
