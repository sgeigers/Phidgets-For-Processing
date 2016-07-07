package shenkar.phidgets;

import com.phidgets.*;
import processing.core.PApplet;

public class Bridge extends Phid4P5 {
	BridgePhidget ph;

	/**
	 * @example Bridge_Example
	 * @param theParent
	 */

	public Bridge(PApplet theParent) {
		myParent = theParent;
		myParent.registerMethod("dispose", this);
		try {
			ph = new BridgePhidget();
		} catch (PhidgetException ex) {
			System.out.println(ex);
		}
		setPhid(ph);
		phSetup(0);
		try {
			for (int i=0; i<ph.getInputCount(); i++) {
				ph.setEnabled(i, true);
			}
		} catch (PhidgetException ex) {
			System.out.println(ex);
		}
	}

	/**
	 * @param theParent
	 * @param ser
	 * 			Serial number of the phidget, acquired by calling printSer when only one of them is connected.
	 */

	public Bridge(PApplet theParent, int ser) {
		myParent = theParent;
		myParent.registerMethod("dispose", this);
		try {
			ph = new BridgePhidget();
		} catch (PhidgetException ex) {
			System.out.println(ex);
		}
		setPhid(ph);
		phSetup(ser);
		try {
			for (int i=0; i<ph.getInputCount(); i++) {
				ph.setEnabled(i, true);
			}
		} catch (PhidgetException ex) {
			System.out.println(ex);
		}
	}

	/**
	 * read value of sensor attached to bridge position pos
	 * 
	 * @param pos
	 * 				the position where the sensor is attached
	 * 
	 * @return float 
	 * 				value read by sensor
	 */

	public float getValue(int pos) {
		try {
			return (float)ph.getBridgeValue(pos);
		} catch (PhidgetException ex) {
			System.out.println(ex);
			return 0;
		}
	}

	/**
	 * Sets gain for position pos of the bridge.
	 * Higher gain sets larger sensitivity, but smaller limits of the read values.
	 *  
	 * The gain can get one of these values:
	 * 1, 8, 16, 32, 64, 128
	 * Other values will be regarded as 128.
	 * 
	 * @param pos
	 * 				the position to set the gain for
	 * 
	 * @param gain 
	 * 				desired gain (1, 8, 16, 32, 64 or 128)
	 */

	public void setGain(int pos, int gain) {
		try {
			int g;
			switch (gain) {
				case 1:
					g = BridgePhidget.PHIDGET_BRIDGE_GAIN_1;
					break;

				case 8:
					g = BridgePhidget.PHIDGET_BRIDGE_GAIN_8;
					break;
					
				case 16:
					g = BridgePhidget.PHIDGET_BRIDGE_GAIN_16;
					break;
					
				case 32:
					g = BridgePhidget.PHIDGET_BRIDGE_GAIN_32;
					break;
					
				case 64:
					g = BridgePhidget.PHIDGET_BRIDGE_GAIN_64;
					break;
					
				case 128:
				default:
					g = BridgePhidget.PHIDGET_BRIDGE_GAIN_128;
					break;				
					
			}
			ph.setGain(pos, g);
		} catch (PhidgetException ex) {
			System.out.println(ex);
		}
	}
}
