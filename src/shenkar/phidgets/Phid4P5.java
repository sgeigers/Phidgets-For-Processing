package shenkar.phidgets;

import processing.core.PApplet;
import com.phidgets.*;
import com.phidgets.event.*;

/*
 * ToDo:
 * Frequency Counter Phidget
 * IR Phidget
 * check global current limit for LEDphidget board. Documentation doesn't mention it - only API
 * implement automatic detection of servo board type (advanced or regular). for now - only advanced is supported
 */

/**
 * @example interfaceKit
 */

public class Phid4P5 {

	PApplet myParent;
	
	protected Phidget ph;

	public final static String VERSION = "0.3";

	public final static int ATTACHMENT_TIMEOUT = 500;
	
	protected void setPhid(Phidget p) {
		ph = p;
	}
	
	public void phSetup(int ser) {
		try {
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
			
			if (ser==0) {
				ph.openAny();
			}
			else {
				ph.open(ser);
			}
			// System.out.println("waiting for InterfaceKit attachment...");
			ph.waitForAttachment(ATTACHMENT_TIMEOUT);

			// System.out.println(ph.getDeviceName());
		} catch (PhidgetException ex) {
			if (ex.getErrorNumber() == PhidgetException.EPHIDGET_TIMEOUT) {
				if (ser==0) {
					System.out.println("Error: no phidget board attached");
				}
				else {
					System.out.println("Error: no phidget board with serial " + ser +" attached");
				}
			}
			else {
				System.out.println(ex);
			}
		}
	}

	/**
	 * Closes the phidget, if attached. Called automatically.
	 * 
	 */
	
	public void closePh() {
		try {
			// System.out.println("closing...");
			ph.close();
			ph = null;
			// System.out.println(" ok");
			// if (false) {
			// println("wait for finalization...");
			// System.gc();
			// }
		} catch (PhidgetException ex) {
			System.out.println(ex);
		}
	}

	/**
	 * closes everything. called automatically when the application is closed.
	 */

	public void dispose() {
		closePh();
	}

	/**
	 * if any number of phidgets need to be connected at the same time, connect each one of them 
	 * in turn, and run the "printSer" function to get specified IDs. Then use this ID number when
	 * initializing the boards. See example Connect_Multiple 
	 */
	
	public void printSer() {
		try {
			System.out.println("Connected device id is: " + ph.getSerialNumber());
		} catch (PhidgetException ex) {
			System.out.println(ex);
		}
	}

	/**
	 * When using events, it might be helpful to know which Phidget board initiated the event. You
	 * can use "printSer" to print a serial number of a specific board, and then, inside the event, check if the calling board has
	 * the same serial number using "getSer".
	 */
	
	public int getSer() {
		try {
			return ph.getSerialNumber();
		} catch (PhidgetException ex) {
			System.out.println(ex);
			return 0;
		}
	}	
	
	/**
	 * return the version of the Library.
	 * 
	 * @return String
	 */
	
	public static String version() {
		return VERSION;
	}

}
