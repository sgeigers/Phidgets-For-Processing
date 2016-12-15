// NOTICE: When using PhidgetsTextLCD Integrated PhidgetsInterfaceKit 8/8/8, for using the interface kit fuctions, 
//         it is required to add an InterfaceKit object. See elaboration below.

import shenkar.phidgets.*;

LCD myLCD;

void setup() {
  size(200,200);
  
  myLCD = new LCD(this);
  
  myLCD.lightOn();
  myLCD.defineCustomChar(9, 1015950, 462879);
  myLCD.displayString (0, "Logo: " + (char)9 + "  Nice?");
  myLCD.hideCursorBlink();
  myLCD.displayString (1, "MousePress to change");
}

void draw() {
}

void mousePressed() {
  myLCD.defineCustomChar(9, 1037444, 135871);
  myLCD.displayChar(1, 5, 'p');
}

void mouseReleased() {
  myLCD.defineCustomChar(9, 1015950, 462879);
  myLCD.displayChar(1, 5, 'P');
}


  
/*
 Quick help:
 
 clear()                   // erase all text currently displayed on the LCD 
 lightOn()                 // turn on backlight of the LCD 
 lightOff()                // turn off backlight of the LCD 
 brightness(brt)           // change intensity of backlight (0..255) 
 contrast(cnt)             // change contrast of LCD display (0..255)
 showCursorLine()          // show a horizontal line in current location of cursor
 hideCursorLine()          // hide the horizontal line in current location of cursor
 showCursorBlink()         // show a blinking rectangle in current location of cursor
 hideCursorBlink()         // hide the blinking rectangle in current location of cursor
 displayChar(row, col, c)  // write a char in a specific location on the LCD
 displayString(row, str)   // write a string in the selected row of LCD
 getColumns()              // get number of columns per row for active LCD
 getRows()                 // get number of rows of active LCD
 getScreen()               // get index of active LCD screen (for TextLCD Adapter, which has 2 screen connectors).
 setScreen(scr)            // set active LCD screen (for TextLCD Adapter, which has 2 screen connectors).
 defineCustomChar(index, val1, val2) // define a custom character to be used in the LCD. See explanations bellow.

 
 Full help:
 
 shenkar.phidgets.* - The library which contains all the classes and functions that allows easy conrol of the Phidgets boards.
   Shenkar is a college for design, engineering and art in Israel.
   
 LCD   - The class which represents a PhidgetsTextLCD board in Processing.
 myLCD - A name to represent the board that is connected to the computer.
 
 *** For connecting more than one board of the same type (e.g LCD) to a computer, see example "Connect_Multiple_Phidgets".
 
 new LCD(this) - A command to create an object and connect it to the connected board.
 
 myLCD.lightOn() - turn on backlight. In most environments and screens, the text can hardly be seen when light is off. 
 
 myLCD.defineCustomChar(9, 1015950, 462879) - define a special character in slot 9 - logo for an imaginary company. See below for explanations on how to create and use special characters.
 
 myLCD.displayString (0, "Logo: " + (char)9 + "  Nice?") - display a string on row 0, which includes the special character we just defined.

 myLCD.displayString (1, "MousePress to change") - display a string on row 1.

 myLCD.defineCustomChar(9, 1037444, 135871) - change the shape of special character in slot 9 (effect immediately. can be used for animations and interactions).
 
 myLCD.displayChar(1, 5, 'p') - display the character 'p' (lowercase 'p') at a specific location of LCD.
 
 
 All functions:
 
 void clear()
   Erase all text currently displayed on the LCD.
   
 void lightOn()
   Turn on backlight of the LCD.

 void lightOff()
   Turn off backlight of the LCD.
   
 void brightness(int brt)
   Change intensity of backlight to "brt" (0..255).
   
 void contrast(int cnt)
   Change contrast of LCD display to "cnt" (0..255).
   
 void showCursorLine()
   Show a horizontal line in current location of cursor.
   
 void hideCursorLine()
   Hide the horizontal line in current location of cursor.
   
 void showCursorBlink()
   Show a blinking rectangle in current location of cursor.
   
 void hideCursorBlink()
   Hide the blinking rectangle in current location of cursor.
   
 void displayChar(int row, int col, char c)
   Write the char "c" in row "row" and column "col".
   Sets cursor location to the character immediatly after the 1 just written.
   
 void displayString(int row, String str)
   Write the string "str" in row "row" of LCD.
   Sets cursor location in the end of the written string.
   
 int getColumns()
   Returns number of columns per row for active LCD.
   
 int getRows()
   Returns number of rows of active LCD
   
 int getScreen()
   Returns index of active LCD screen (for TextLCD Adapter, which has 2 screen connectors).
   Returned number can be 0 or 1.
   
 void setScreen(int scr)
   Set active LCD screen (for TextLCD Adapter, which has 2 screen connectors).
   "scr" should be 0 or 1.
   
 void defineCustomChar(int index, int val1, int val2)
   Define a custom character to be used in the LCD.
   8 custom characters can be defined (indexed 8 to 15) , and will stay stored in the LCD until it's powered off, or until re-defined.
   
   To "draw" a character:
    - Connect the Phidgets TextLCD board to the computer.
    - Open the Phidget Control Panel and double-click the "Phidget TextLCD" line in the Locally attached devices list.
    - Choose "Custom Characters" tab.
    - Choose any "memory Location".
    - Draw your character. When finished - press the "Store" button.
    - Copy the values of "Val1" and "Val2" and paste them in the respective parameters in the "defineCustomChar" function
      in your sketch.
    - Close the "TextLCD-full" window for allowing the board to be operated through Processing sketch. 
   
   To display the custom characters on the LCD, use "(char)" and the index number of the character (8..15) with
       the "displayString" or "displayChar" functions.
   
   for example:
   lcd.displayChar (0, 4, (char)8);
   lcd.displayString (1, "Logo: " + (char)9 + ". Nice?");
    
    
  USING PhidgetsTextLCD Integrated PhidgetsInterfaceKit 8/8/8:
  ------------------------------------------------------------
  
  If you need to also use the InterfaceKit abilities of the board (i.e. read sensors etc.), you need to integrate commands from the InterfaceKit_Example into you sketch:
  - Add the line "InterfaceKit ik;" right after "LCD lcd;".
  - inside function "setup", add the line "ik = new InterfaceKit(this);" right after "lcd = new LCD(this);".
  - Open the example "InterfaceKit_Example" and use any needed functions from the example itself or the explanations. 
  
*/
