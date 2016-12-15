import shenkar.phidgets.*;

Bridge myBridge;

PFont font;

void setup() {
  size(300,200);
  
  myBridge = new Bridge(this);
  myBridge.setGain(0, 128);
  font = createFont("Tahoma", 24);
  textFont(font);
}

void draw() {
  background(0);
  noStroke();
  fill(200);
  float strain = myBridge.getValue(0);
  text("Strain is: " + strain, 50,100);
}
 
/*

 Quick help:
 
 getValue(ch)   // read the input value at channel "ch" 
 setGain(ch, gain)  // set the gain for channel "ch". gain can be: 1/8/16/32/64/128


 Full help:
 
 shenkar.phidgets.* - The library which contains all the classes and functions that allows easy conrol of the Phidgets boards.
   Shenkar is a college for design, engineering and art in Israel.
   
 Bridge   - The class which represents a PhidgetBridge board in Processing.
 myBridge - A name to represent the board that is connected to the computer.
 
 *** For connecting more than one board of the same type (e.g PhidgetBridge) to a computer, see example "Connect_Multiple_InterfaceKits".
 
 new Bridge(this) - A command to create an object and connect it to the connected board.
 
 myBridge.setGain(0, 128) - Set maximum gain for channel 0 of the board. It is recommended to always set maximum gain possible, unless the full
   scale of the sensor is not covered.
   
 myBridge.getValue(0) - Get the value of sensor connected to position 0 of the board. The returned value is of type "double", and can be converted
   to "float" by adding "(float)" before the expression.
 
 
 All functions:
 
 float getValue(int ch)
   Get current reading from channel "ch".

 void setGain(int ch, int gain)
   Set the gain for channel "ch". gain can be: 1, 8, 16, 32, 64 or 128. Any other value will result in gain 128.
   Gain sets the sensitivity of the channel. Higher gain means higher responsiveness, but lower span. It is recommended to set each used channel
   to highest possible gain, as long as the entire wanted reading scale is reachable.
   
*/
