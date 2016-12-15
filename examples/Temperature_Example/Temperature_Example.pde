// NOTE: This class and example were written to be used mainly with PhidgetTemperatureSensor IR board.
//       for using other PhidgetTemperatureSensor boards (with thermocouples) further development is needed.

import shenkar.phidgets.*;

Temperature myTemp;

PFont font;

void setup() {
  size(200,600);
  
  myTemp = new Temperature(this);
  
  font = createFont("Tahoma", 24);
  textFont(font);
  textAlign(CENTER);
}

void draw() {
  drawThermometer (myTemp.getTemp());
}
  
void drawThermometer(float t) {
  background(0);
  noStroke();
  fill(constrain((int)t, 0, 255), 0, constrain(255-(int)t, 0, 255));
  rect(50, 500-t, 100, t); 
  noFill();
  stroke(200,0,0);
  rect(50,100,100,400);
  fill(200);
  text(t, 100,525);
}

/*

 Quick help:
 
 getTemp()     // read the temperature sensed by the board.
 getTemp(pos)  // read the temperature sensed by the board at sensor "pos" (for boards with more than 1 sensor).
 getAmbient()  // read ambient temperature from board.


 Full help: 
 
 shenkar.phidgets.* - The library which contains all the classes and functions that allows easy conrol of the Phidgets boards.
   Shenkar is a college for design, engineering and art in Israel.
   
 Temperature - The class which represents a PhidgetTemperatureSensor board in Processing.
 myTemp - A name to represent the board that is connected to the computer.
 
 *** For connecting more than one board of the same type (e.g interface kit) to a computer, see example "Connect_Multiple_Phidgets".
 
 new Temperature(this) - A command to create an object and connect it to the connected board.
 
 myTemp.getTemp() - get the temperature read by the board.
 
 
 All functions:
 
 float getTemp()
   Return the temperature sensed by the main sensor of the board.
   For boards with more than one sensor positions, this will return the value of sensor connected to position 0.
   
 float getTemp(itn pos)
   Return the temperature sensed by the sensor connected to position "pos".
 
 getAmbient()
   Return ambient temperature from board.

*/
