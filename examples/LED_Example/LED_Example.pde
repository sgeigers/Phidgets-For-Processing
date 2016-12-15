// NOTICE: see explanations below for setting voltage and current limits

import shenkar.phidgets.*;

LED myLED;

void setup() {
  size(500,500);
  myLED = new LED(this);
}

void draw() {
  myLED.setBrightness(0, mouseX/5);
  myLED.setLinearBrightness(1, mouseX/5);
}


/*

 Quick help:

 setBrightness(led, brt)        // set brighness "brt" (0..100) for LED connected to position "led" (0..63 on "PhidgetLED-64 Advanced" board)
 setLinearBrightness(led, brt)  // same as above, but the level is set for better linear brightness perception of human eye. Best for "breathing" and similar effects.
 getBrightness(led)             // get brighness set for LED connected to position "led" (returns 0..100)
 setCurrentLimit(led, lmt)      // sets current limit for a specific LED
 getCurrentLimit(led)           // get current limit set for a specific LED
 setVoltage(vol)                // set voltage for all LEDs
 
 
 Full help:
 
 shenkar.phidgets.* - The library which contains all the classes and functions that allows easy conrol of the Phidgets boards.
   Shenkar is a college for design, engineering and art in Israel.
   
 LED   - The class which represents a PhidgetLED board in Processing.
 myLED - A name to represent the board that is connected to the computer.
 
 *** For connecting more than one board of the same type (e.g PhidgetLED) to a computer, see example "Connect_Multiple_Phidgets".
 
 new LED(this) - A command to create an object and connect it to the connected board.
 
 myLED.setBrightness(0, mouseX/5) - Set brightness of LED connected to position 0 of the board to value set by mouse x position
 
 myLED.setLinearBrightness(1, mouseX/5) - Same as above, but for LED connected to position 1 and with linear brightness 9connect LEDs to position 0 and 1 for comparison)
 
 All functions:
 
 void setBrightness(int led, float brt)
   Set brighness "brt" (0..100) for LED connected to position "led" (0..63 on "PhidgetLED-64 Advanced" board)
   
 void setLinearBrightness(int led, float brt)
   Same as above, but the level is set for better linear brightness perception of human eye. Best for "breathing" and similar effects.
   
 float getBrightness(int led)
   Return the brighness set for LED connected to position "led" (returns 0..100).
   Value is in the direct bightness scale - not the linear.
 
 void setCurrentLimit(int led, float lmt)
   Set current limit for a specific LED. This is a possibly more efficient way for controlling brightness (than setBrightness), but for some LEDs, it might cause instability.
   In addition, note that each type of LED has its own current limit. If driven with higher current, the LED might be damaged.
   If specification of the LEDs are not known, it is best to leave to default, and control brightness with "setBrightness".
   lmt - current limit in milliAmperes - 0..80

 float getCurrentLimit(int led)
   Return the current limit set for a specific LED
   
 void setVoltage(float vol)
   Set voltage for all LEDs. This should be matched to the LEDs being used. If you don't know the LEDs specifications, you can set voltage to 1.7V, see if the LEDs light well,
    and if not, raise to 2.75V and so on.
   Setting to highest possible voltage (5V) when not needed will result in more power loss and lower stability (possible over-heat shutdowns of the board).
   vol - voltage for board - 1.7, 2.75, 3.9 or 5

 float getVoltage()
   Return the voltage setting of the board (see explanation above).
   
*/
