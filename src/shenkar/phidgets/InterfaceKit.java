package shenkar.phidgets;

import processing.core.PApplet;
import com.phidgets.*;
import com.phidgets.event.*;
import java.lang.reflect.Method;

/**
 * @example interfaceKit
 */

public class InterfaceKit extends Phid4P5 {

	InterfaceKitPhidget ph;
	
	Method digitalInputChangeMethod;
	private boolean digitalEvent = false;
	
	public int input=0;
	public boolean state=false;

	/**
	 * @example InterfaceKit_Example
	 * @param theParent
	 */
	
	public InterfaceKit(PApplet theParent) {
		myParent = theParent;
		myParent.registerMethod("dispose", this);
		myParent.registerMethod("pre", this);
		try {
			ph = new InterfaceKitPhidget();
		} catch (PhidgetException ex) {
			System.out.println(ex);
		}
		setPhid(ph);
		phSetup(0);
		try {
			digitalInputChangeMethod = myParent.getClass().getMethod("digitalInputChange",
                    new Class<?>[] { InterfaceKit.class });
			ph.addInputChangeListener(new InputChangeListener() {
		        public void inputChanged(InputChangeEvent ie) {
		          input = ie.getIndex();
		          state = ie.getState();
		          digitalInputChangeMtd();
		        }
		      }
		    );
		} 
		catch (Exception e) {
			// no such method, or an error.. which is fine, just ignore
		}
	}

	/**
	 * @example Connect_Multiple_InterfaceKits
	 * @param theParent
	 * @param ser
	 * 			Serial number of the phidget, acquired by calling printSer when only one of them is connected.
	 */

	public InterfaceKit(PApplet theParent, int ser) {
		myParent = theParent;
		myParent.registerMethod("dispose", this);
		try {
			ph = new InterfaceKitPhidget();
		} catch (PhidgetException ex) {
			System.out.println(ex);
		}
		setPhid(ph);
		phSetup(ser);
		try {
			digitalInputChangeMethod = myParent.getClass().getMethod("digitalInputChange",
                    new Class<?>[] { InterfaceKit.class });
			    ph.addInputChangeListener(new InputChangeListener() {
		        public void inputChanged(InputChangeEvent ie) {
		          input = ie.getIndex();
		          state = ie.getState();
		          digitalInputChangeMtd();
		        }
		      }
		    );
		} 
		catch (Exception e) {
			// no such method, or an error.. which is fine, just ignore
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
	 * handles events. Do not call.
	 * 
	 */
	
	public void pre() {
		if (digitalEvent) {
			digitalEvent=false;
			try {
				digitalInputChangeMethod.invoke(myParent, new Object[] { this });
			} 
			catch (Exception e) {
				System.err.println("Disabling digitalInputChange() for " + this.hashCode() +
				                         " because of an error.");
				e.printStackTrace();
				digitalInputChangeMethod = null;
			}
			
		}
	}
	
	public void digitalInputChangeMtd() {
		if (digitalInputChangeMethod != null) {
			digitalEvent = true;
		}
	}
	
	/**
	 * set output state for digital output. true = high false = low
	 * 
	 * @param pos
	 *            the position where the output is attached
	 * 
	 * @param state
	 *            desired output state (true/false)
	 */

	public void digitalWrite(int pos, boolean state) {
		try {
			ph.setOutputState(pos, state);
		} catch (PhidgetException ex) {
			System.out.println(ex);
		}
	}	
	
	/**
	 * set data rate for sensor
	 * 
	 * @param pos
	 *            the position where the output is attached
	 * 
	 * @param rate
	 *            desired rate in milliseconds: 1,2,4,8 and on in multiples of 8 up to 1000
	 */

	public void dataRate(int pos, int rate) {
		try {
			if (rate>8) {
				rate = (rate/8+(rate%8)/5)*8;
			}
			else {
				switch (rate) {
					case 3:
						rate=2;
						break;
					case 5:
					case 6:
						rate=4;
						break;
					case 7:
						rate=7;
						break;
				}
			}
			rate = Math.max(Math.min(rate,1000),1);
			ph.setDataRate(pos, rate);
		} catch (PhidgetException ex) {
			System.out.println(ex);
		}
	}		
}
