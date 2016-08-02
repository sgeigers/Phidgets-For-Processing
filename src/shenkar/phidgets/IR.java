package shenkar.phidgets;

import processing.core.PApplet;
import com.phidgets.*;
import com.phidgets.event.*;
import java.lang.reflect.Method;

public class IR extends Phid4P5 {
	IRPhidget ph;
	
	Method IRReceivedMethod;
	Method IRLearnedMethod;
	
	private boolean tagDetectedEvent = false;
	private boolean tagLostEvent = false;
	
	/**
	 * @example RFID_Example
	 * @param theParent
	 */
	
	public IR(PApplet theParent) {
		myParent = theParent;
		myParent.registerMethod("dispose", this);
		myParent.registerMethod("pre", this);
		try {
			ph = new IRPhidget();
		} catch (PhidgetException ex) {
			System.out.println(ex);
		}
		setPhid(ph);
		phSetup(0);
		try {
			IRReceivedMethod = myParent.getClass().getMethod("IRReceived",
                    new Class<?>[] { IR.class });
		    ph.addCodeListener(new CodeListener() {
		        public void code(CodeEvent ce) {
		        	IRReceivedMtd();
		        }
		      }
		    );
		} 
		catch (Exception e) {
			// no such method, or an error.. which is fine, just ignore
		}
		try {
			IRLearnedMethod = myParent.getClass().getMethod("IRLearned",
                    new Class<?>[] { IR.class });
		    ph.addLearnListener(new LearnListener() {
		        public void learn(LearnEvent le) {
		        	IRLearnedMtd();
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

	public IR(PApplet theParent, int ser) {
		myParent = theParent;
		myParent.registerMethod("dispose", this);
		try {
			ph = new IRPhidget();
		} catch (PhidgetException ex) {
			System.out.println(ex);
		}
		setPhid(ph);
		phSetup(ser);
		try {
			IRReceivedMethod = myParent.getClass().getMethod("IRReceived",
                    new Class<?>[] { IR.class });
		    ph.addCodeListener(new CodeListener() {
		        public void code(CodeEvent ce) {
		        	IRReceivedMtd();
		        }
		      }
		    );
		} 
		catch (Exception e) {
			// no such method, or an error.. which is fine, just ignore
		}
		try {
			IRLearnedMethod = myParent.getClass().getMethod("IRLearned",
                    new Class<?>[] { IR.class });
		    ph.addLearnListener(new LearnListener() {
		        public void learn(LearnEvent le) {
		        	IRLearnedMtd();
		        }
		      }
		    );
		} 
		catch (Exception e) {
			// no such method, or an error.. which is fine, just ignore
		}
	}

	/**
	 * Prints tag string to the console, when a tag is detected. This is usefull for "copy-pasting" in the "tags" String array for later using
	 * "whichTag" for getting a tag number.
	 * If no tags seen - prints "None".
	 *  
	 */
/*
	public void printTag() {
		try {
			if (anyTag()) {
				System.out.println(ph.getLastTag());
			}
			else {
				System.out.println("None");
			}
		} catch (PhidgetException ex) {
			System.out.println(ex);
		}
	}

	/**
	 * Return true if board sees any tag at all.
	 * 
	 * @return boolean 
	 * 				is there any tag?
	 */
/*
	public boolean anyTag() {
		try {
			return ph.getTagStatus();
		} catch (PhidgetException ex) {
			System.out.println(ex);
			return false;
		}
	}

	/**
	 * Return true if detected tag is equal to the "tag" parameter. If not equal or no tag detected - return false.
	 * 
	 * @param tag
	 * 			String of expected tag (use "printTag" to create)
	 * @return boolean 
	 * 			true if detected the tag
	 */
/*
	public boolean isTag(String tag) {
		try {
			if (ph.getTagStatus()) {
				if (tag.equals(ph.getLastTag())) {
					return true;
				}
			}
			return false;
		} catch (PhidgetException ex) {
			System.out.println(ex);
			return false;
		}
	}

	/**
	 * Gets a String array ("tags"), compares current tag to each of the tag strings and returns its index in the array (0..length of array).
	 * If no tag seen by the board, -1 is returned.
	 * 
	 * @param tags
	 * 			String array of expected tags (use "printTag" to create)
	 * @return int 
	 * 			index of detected tag in the tags array, or -1 if no tag detected, or if the tag is not in the array
	 */
/*
	public int whichTag(String[] tags) {
		try {
			int t=-1;
			if (ph.getTagStatus()) {
				for (int i=0; i<tags.length; i++) {
					if (tags[i].equals(ph.getLastTag())) {
						t=i;
					}
				}
			}
			return t;
		} catch (PhidgetException ex) {
			System.out.println(ex);
			return -1;
		}
	}

	/**
	 * handles events. Do not call.
	 * 
	 */
/*	
	public void pre() {
		if (tagDetectedEvent) {
			tagDetectedEvent=false;
			try {
				tagDetectedMethod.invoke(myParent, new Object[] { this });
			} 
			catch (Exception e) {
				System.err.println("Disabling digitalInputChange() for " + this.hashCode() +
				                         " because of an error.");
				e.printStackTrace();
				tagDetectedMethod = null;
			}
			
		}
		if (tagLostEvent) {
			tagLostEvent=false;
			try {
				tagLostMethod.invoke(myParent, new Object[] { this });
			} 
			catch (Exception e) {
				System.err.println("Disabling digitalInputChange() for " + this.hashCode() +
				                         " because of an error.");
				e.printStackTrace();
				tagLostMethod = null;
			}
			
		}
	}
*/	
	public void IRReceivedMtd() {
		if (IRReceivedMethod != null) {
			tagDetectedEvent = true;
		}
	}
	
	public void IRLearnedMtd() {
		if (IRLearnedMethod != null) {
			tagLostEvent = true;
		}
	}
	
}
