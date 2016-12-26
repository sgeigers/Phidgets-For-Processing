package shenkar.phidgets;

import com.phidgets.*;
import processing.core.PApplet;

public class FrequencyCounter extends Phid4P5 {

	FrequencyCounterPhidget ph;

	/**
	 * @example FrequenctCounter_Example
	 * @param theParent
	 */

	public FrequencyCounter(PApplet theParent) {
		myParent = theParent;
		myParent.registerMethod("dispose", this);
		try {
			ph = new FrequencyCounterPhidget();
		} catch (PhidgetException ex) {
			System.out.println(ex);
		}
		setPhid(ph);
		phSetup(0);
		// This is REALLY ugly, but after many testing, this is the only method I found to get this board to work
		try {
		ph.setEnabled(0, true);
		ph.setEnabled(1, true);
		setZeroCrossingSensor(0);
		setZeroCrossingSensor(1);
		setLogicSensor(0);
		setLogicSensor(1);
		ph.setEnabled(0, false);
		ph.setEnabled(1, false);
		} catch (PhidgetException ex) {
			System.out.println(ex);
		}
	}

	/**
	 * @param theParent
	 * @param ser
	 * 			Serial number of the phidget, acquired by calling printSer when only one of them is connected.
	 */

	public FrequencyCounter(PApplet theParent, int ser) {
		myParent = theParent;
		myParent.registerMethod("dispose", this);
		try {
			ph = new FrequencyCounterPhidget();
		} catch (PhidgetException ex) {
			System.out.println(ex);
		}
		setPhid(ph);
		phSetup(ser);
		// This is REALLY ugly, but after many testing, this is the only method I found to get this board to work
		try {
		ph.setEnabled(0, true);
		ph.setEnabled(1, true);
		setZeroCrossingSensor(0);
		setZeroCrossingSensor(1);
		setLogicSensor(0);
		setLogicSensor(1);
		ph.setEnabled(0, false);
		ph.setEnabled(1, false);
		} catch (PhidgetException ex) {
			System.out.println(ex);
		}
	}

	/**
	 * get frequency from device connected to position pos, in Hz.
	 * 
	 * @param pos
	 * 				sensor position
	 * 
	 * @return float 
	 * 				frequency in Hz (0.01 .. 1M)
	 */

	public float frequency(int pos) {
		try {
			if (!ph.getEnabled(pos)) {
				ph.setEnabled(pos, true);
			}
			return (float)ph.getFrequency(pos);
		} catch (PhidgetException ex) {
			if (ex.getErrorNumber() != PhidgetException.EPHIDGET_UNKNOWNVAL) {
				System.out.println(ex);
			}
			return 0;
		}
	}

	/**
	 * sets sensor on position "pos" to match logic level type (3.3V or 5V).
	 * NOTE: DO NOT LEAVE THE '-' TERMINAL UNCONNECTED. IF NOT IN USE, CONNECT TO GND TERMINAL
	 * this is the default.
	 * 
	 * @param pos
	 * 				sensor position
	 */

	public void setLogicSensor(int pos) {
		try {
			ph.setFilter(pos, com.phidgets.FrequencyCounterPhidget.PHIDGET_FREQUENCYCOUNTER_FILTERTYPE_LOGIC_LEVEL);
		} catch (PhidgetException ex) {
			System.out.println(ex);
		}
	}

	/**
	 * sets sensor on position "pos" to match zero crossing type.
	 * 
	 * @param pos
	 * 				sensor position
	 */

	public void setZeroCrossingSensor(int pos) {
		try {
			ph.setFilter(pos, com.phidgets.FrequencyCounterPhidget.PHIDGET_FREQUENCYCOUNTER_FILTERTYPE_ZERO_CROSSING);
		} catch (PhidgetException ex) {
			System.out.println(ex);
		}
	}

	/**
	 * sets sensor on position "pos" to an unknown type.
	 * NOTE: DO NOT LEAVE THE '-' TERMINAL UNCONNECTED. IF NOT IN USE _ CONNECT TO GND TERMINAL
	 * 
	 * @param pos
	 * 				sensor position
	 */

	public void setUnknownSensor(int pos) {
		try {
			ph.setFilter(pos, com.phidgets.FrequencyCounterPhidget.PHIDGET_FREQUENCYCOUNTER_FILTERTYPE_UNKNOWN);
		} catch (PhidgetException ex) {
			System.out.println(ex);
		}
	}

	/**
	 * read total number of pulses since program run or reset command.
	 * 
	 * @param pos
	 * 				sensor position
	 * 
	 * @return long
	 * 				total number of pulses
	 */

	public long count(int pos) {
		try {
			if (!ph.getEnabled(pos)) {
				ph.setEnabled(pos, true);
			}
			return ph.getTotalCount(pos);
		} catch (PhidgetException ex) {
			if (ex.getErrorNumber() != PhidgetException.EPHIDGET_UNKNOWNVAL) {
				System.out.println(ex);
			}
			return 0;
		}
	}
	
	/**
	 * read total time (in microseconds) since program run or reset command.
	 * 
	 * @param pos
	 * 				sensor position
	 * 
	 * @return long
	 * 				total time elapsed (microseconds)
	 */

	public long micros(int pos) {
		try {
			if (!ph.getEnabled(pos)) {
				ph.setEnabled(pos, true);
			}
			return ph.getTotalTime(pos);
		} catch (PhidgetException ex) {
			if (ex.getErrorNumber() != PhidgetException.EPHIDGET_UNKNOWNVAL) {
				System.out.println(ex);
			}
			return 0;
		}
	}
	
	/**
	 * resets time and pulse counts for a sensor.
	 * 
	 * @param pos
	 * 				sensor position
	 * 
	 */

	public void reset(int pos) {
		try {
			if (!ph.getEnabled(pos)) {
				ph.setEnabled(pos, true);
			}
			ph.reset(pos);
		} catch (PhidgetException ex) {
			System.out.println(ex);
		}
	}
	
	/**
	 * Sets the timeout value, in microseconds. Set the time to wait without detecting a signal before reporting 0 Hz.
	 * The valid range is 0.1 - 100 seconds (100,000 - 100,000,000 microseconds).
	 * timeout represents the lowest frequency that will be measurable
	 * 
	 * @param pos
	 * 				sensor position
	 * 
	 * @param tmo
	 * 				timout in microseconds (100,000 .. 100,000,000)
	 */

	public void setTimeout(int pos, int tmo) {
		try {
			if (tmo<100000) tmo=100000;
			if (tmo>100000000) tmo=100000000;
			ph.setTimeout(pos, tmo);
		} catch (PhidgetException ex) {
			System.out.println(ex);
		}
	}

	/**
	 *  automatically called when program exists.
	 *  disables all stations.
	 *  
	 */
	
	public void dispose() {
		try {
			for (int i=0; i<ph.getFrequencyInputCount(); i++) {
				ph.setEnabled(i, false);
			}
		} catch (PhidgetException ex) {
			System.out.println(ex);
		}
		
	}
}
