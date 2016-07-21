package shenkar.phidgets;

import com.phidgets.*;
import processing.core.PApplet;

public class Encoder extends Phid4P5 {

	EncoderPhidget ph;

	/**
	 * @example Encoder_Example
	 * @param theParent
	 */

	public Encoder(PApplet theParent) {
		myParent = theParent;
		myParent.registerMethod("dispose", this);
		try {
			ph = new EncoderPhidget();
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

	public Encoder(PApplet theParent, int ser) {
		myParent = theParent;
		myParent.registerMethod("dispose", this);
		try {
			ph = new EncoderPhidget();
		} catch (PhidgetException ex) {
			System.out.println(ex);
		}
		setPhid(ph);
		phSetup(ser);
	}

	/**
	 * Get position of encoder. The position can be reset with setPosition
	 * This function is for boards with only 1 encoder. For larger boards, use getPosition(int enc)
	 *
	 * 
	 * @return int 
	 * 				position of the encoder
	 */

	public int getPosition() {
		try {
			if (!ph.getEnabled(0)) {
				ph.setEnabled(0, true);
			}
			return ph.getPosition(0);
		} catch (PhidgetException ex) {
			System.out.println(ex);
			return 0;
		}
	}

	/**
	 * get position of encoder. The position can be reset with setPosition
	 * 
	 * @param enc
	 * 				index of the encoder
	 * 
	 * @return int 
	 * 				position of the encoder
	 */

	public int getPosition(int enc) {
		try {
			if (!ph.getEnabled(enc)) {
				ph.setEnabled(enc, true);
			}
			return ph.getPosition(enc);
		} catch (PhidgetException ex) {
			System.out.println(ex);
			return 0;
		}
	}

	/**
	 * sets position of encoder.
	 * This function is for boards with only 1 encoder. For larger boards, use setPosition(int enc, int pos)
	 * 
	 * @param pos
	 * 				new position
	 */

	public void setPosition(int pos) {
		try {
			if (!ph.getEnabled(0)) {
				ph.setEnabled(0, true);
			}
			ph.setPosition(0, pos);
		} catch (PhidgetException ex) {
			System.out.println(ex);
		}
	}

	/**
	 * sets position of encoder.
	 * 
	 * @param enc
	 * 				index of the encoder
	 * @param pos
	 * 				new position
	 */

	public void setPosition(int enc, int pos) {
		try {
			if (!ph.getEnabled(enc)) {
				ph.setEnabled(enc, true);
			}
			ph.setPosition(enc, pos);
		} catch (PhidgetException ex) {
			System.out.println(ex);
		}
	}
	
	/**
	 * Get index position of encoder. For encoders with index, this will keep the last position when
	 *  the index was reached (and can be used for calculating absolute position).
	 * This function is for boards with only 1 encoder. For larger boards, use getIndexPosition(int enc)
	 *
	 * 
	 * @return int 
	 * 				position of the encoder
	 */

	public int getIndexPosition() {
		try {
			if (!ph.getEnabled(0)) {
				ph.setEnabled(0, true);
			}
			return ph.getIndexPosition(0);
		} catch (PhidgetException ex) {
			System.out.println(ex);
			return 0;
		}
	}

	/**
	 * Get index position of encoder. For encoders with index, this will keep the last position when
	 *  the index was reached (and can be used for calculating absolute position).
	 *
	 * 
	 * @param enc
	 * 				index of the encoder
	 * @return int 
	 * 				position of the encoder
	 */

	public int getIndexPosition(int enc) {
		try {
			if (!ph.getEnabled(enc)) {
				ph.setEnabled(enc, true);
			}
			return ph.getIndexPosition(enc);
		} catch (PhidgetException ex) {
			System.out.println(ex);
			return 0;
		}
	}
	
	/**
	 * Some encoder boards also have digital input terminals.
	 * This function returns true if input attached to digital input position pos is high.
	 * otherwise - returns false
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
	
}
