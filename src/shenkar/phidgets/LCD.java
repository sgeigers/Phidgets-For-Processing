package shenkar.phidgets;

import processing.core.PApplet;
import com.phidgets.*;

public class LCD extends Phid4P5 {
	TextLCDPhidget  ph;

	/**
	 * @example LCD_Example
	 * @param theParent
	 */

	public LCD(PApplet theParent) {
		myParent = theParent;
		myParent.registerMethod("dispose", this);
		try {
			ph = new TextLCDPhidget ();
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

	public LCD(PApplet theParent, int ser) {
		myParent = theParent;
		myParent.registerMethod("dispose", this);
		try {
			ph = new TextLCDPhidget ();
		} catch (PhidgetException ex) {
			System.out.println(ex);
		}
		setPhid(ph);
		phSetup(ser);
	}

	/**
	 * Clear the screen.
	 * 
	 */

	public void clear() {
		try {
			for (int i=0; i<ph.getRowCount(); i++) {
				ph.setDisplayString(i, "");
			}
		} catch (PhidgetException ex) {
			System.out.println(ex);
		}
	}
	
	/**
	 * Turn backlight on.
	 * 
	 */

	public void lightOn() {
		try {
			 ph.setBacklight(true);
		} catch (PhidgetException ex) {
			System.out.println(ex);
		}
	}

	/**
	 * Turn backlight off.
	 * 
	 */

	public void lightOff() {
		try {
			 ph.setBacklight(false);
		} catch (PhidgetException ex) {
			System.out.println(ex);
		}
	}

	/**
	 * Set screen brightness.
	 * 
	 * @param brt
	 * 			desired brightness (0..255)
	 */

	public void brightness(int brt) {
		try {
			 ph.setBrightness(brt);
		} catch (PhidgetException ex) {
			System.out.println(ex);
		}
	}

	/**
	 * Set screen contrast.
	 * 
	 * @param cnt
	 * 			desired contrast (0..255)
	 */

	public void contrast(int cnt) {
		try {
			 ph.setContrast(cnt);
		} catch (PhidgetException ex) {
			System.out.println(ex);
		}
	}

	/**
	 * Show cursor line.
	 * 
	 */

	public void showCursorLine() {
		try {
			 ph.setCursor(true);
		} catch (PhidgetException ex) {
			System.out.println(ex);
		}
	}

	/**
	 * Hide cursor line.
	 * 
	 */

	public void hideCursorLine() {
		try {
			 ph.setCursor(false);
		} catch (PhidgetException ex) {
			System.out.println(ex);
		}
	}

	/**
	 * Show cursor blinking box.
	 * 
	 */

	public void showCursorBlink() {
		try {
			 ph.setCursorBlink(true);
		} catch (PhidgetException ex) {
			System.out.println(ex);
		}
	}

	/**
	 * Hide cursor blinking box.
	 * 
	 */

	public void hideCursorBlink() {
		try {
			 ph.setCursorBlink(false);
		} catch (PhidgetException ex) {
			System.out.println(ex);
		}
	}
	
	/**
	 * Write a specific character at a specific location on the LCD.
	 * 
	 * @param row
	 * 			Row (0..1)
	 * @param col
	 * 			Column (0..19)
	 * @param c
	 * 			Character (for example: 'a')
	 * 
	 */

	public void displayChar(int row, int col, char c) {
		try {
			 ph.setDisplayCharacter(row, col, c);
		} catch (PhidgetException ex) {
			System.out.println(ex);
		}
	}
	
	/**
	 * Write a string at a specific row of the LCD.
	 * 
	 * @param row
	 * 			Row (0..1)
	 * @param str
	 * 			String to display (for example: "Hello")
	 * 
	 */

	public void displayString(int row, String str) {
		try {
			 ph.setDisplayString(row, str);
		} catch (PhidgetException ex) {
			System.out.println(ex);
		}
	}
	
	/**
	 * Define a custom character to be used in the LCD.
	 * 8 custom characters can be defined (indexed 8 to 15) , and will stay stored in the LCD until it's powered off, or
	 * 		until re-defined.
	 * 
	 * To "draw" a character:
	 *  - Connect the Phidgets TextLCD board to the computer.
	 *  - Open the Phidget Control Panel and double-click the "Phidget TextLCD" line in the Locally attached devices list.
	 *  - Choose "Custom Characters" tab.
	 *  - Choose any "memory Location".
	 *  - Draw your character. When finished - press the "Store" button.
	 *  - Copy the values of "Val1" and "Val2" and paste them in the respective parameters in the "defineCustomChar" function
	 *  	in your sketch.
	 *  - Close the "TextLCD-full" window for allowing the board to be operated through Processing sketch.
	 * 
	 * To display the custom characters on the LCD, use "(char)" and the index number of the character (8..15) with
	 * 		the "displayString" or "displayChar" functions.
	 * 
	 * for example:
	 * lcd.displayChar (0, 4, (char)8);
	 * lcd.displayString (1, "Logo: " + (char)9 + ". Nice?");
	 *  
	 * @param index
	 * 			Index of special character (8..15)
	 * @param val1, val2
	 * 			Values defining the shape of the character - see explanations above
	 * 
	 */

	public void defineCustomChar(int index, int val1, int val2) {
		try {
			 ph.setCustomCharacter(index, val1, val2);
		} catch (PhidgetException ex) {
			System.out.println(ex);
		}
	}
	
	/**
	 * Get number of columns per line of the LCD.
	 * 
	 * @return int
	 * 			Number of columns per line
	 */

	public int getColumns() {
		try {
			 return ph.getColumnCount();			 
		} catch (PhidgetException ex) {
			System.out.println(ex);
			return 0;
		}
	}
	
	/**
	 * Get number of rows of the LCD.
	 * 
	 * @return int
	 * 			Number of rows
	 */

	public int getRows() {
		try {
			 return ph.getRowCount();			 
		} catch (PhidgetException ex) {
			System.out.println(ex);
			return 0;
		}
	}
	
	/**
	 * Get index of active LCD screen (for TextLCD Adapter, which has 2 screen connectors).
	 * 
	 * @return int
	 * 			Index of active screen
	 */

	public int getScreen() {
		try {
			 return ph.getScreen();			 
		} catch (PhidgetException ex) {
			System.out.println(ex);
			return 0;
		}
	}

	/**
	 * Set active screen (for TextLCD Adapter, which has 2 screen connectors)..
	 * 
	 * @param scr
	 * 			Screen index (0/1) 
	 * 
	 */

	public void setScreen(int scr) {
		try {
			 ph.setScreen(scr);
		} catch (PhidgetException ex) {
			System.out.println(ex);
		}
	}
	
}
