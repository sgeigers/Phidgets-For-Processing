import shenkar.phidgets.*;

InterfaceKit myInterfaceKit;

void setup() {
  size(800,600);
  myInterfaceKit = new InterfaceKit(this);
}

void draw() {
  background(0);
  
  noFill();
  stroke(100);  
  rect(200,50,100,500);
  
  noStroke();
  fill(150,0,0);
  int sensor = myInterfaceKit.analogRead(0);
  rect(200,50,100,sensor/2);
}


/*
 Quick help:

 analogRead(pos)           // read a sensor attached to position "pos" 
 digitalRead(pos)          // read a digital input (e.g. switch) connected between G and position "pos" of digital inputs
 digitalWrite(pos, state)  // change the state (of/off) of a digital output (e.g LED) connected to position "pos" of digital outputs
 dataRate(pos, rate)       // change data rate for analog input.
 digitalInputChange(InterfaceKit callingInterfaceKit)  // an event function called when a digital input is changed (see Interface_Kit_Event_Example)
 

 Full help:
 
 shenkar.phidgets.* - The library which contains all the classes and functions that allows easy conrol of the Phidgets boards.
   Shenkar is a college for design, engineering and art in Israel.
   
 InterfaceKit   - The class which represents a Phidgets interface kit board in Processing.
 myInterfaceKit - A name to represent the board that is connected to the computer.
 
 *** For connecting more than one board of the same type (e.g interface kit) to a computer, see example "Connect_Multiple_Phidgets".
 
 new InterfaceKit(this) - A command to create an object and connect it to the connected board.
 
 myInterfaceKit.analogRead(0) - get the value read by sensor connected to position 0 of the interface kit. Range 0..1000
 
 
 All functions:
 
 int analogRead(int pos)
   Read a sensor attached to position "pos".
   pos should be an integer between 0 and the number of analog inputs that connected board has (usually 8) minus 1.
     That is - for an 8 digital inputs board, 0..7
   The function returns an integer ranged 0..1000
   
 boolean digitalRead(int pos)  
   Read a digital input (e.g. switch) connected between G and position "pos" of digital inputs.
   pos - Position of connected digital input (for an 8 digital inputs board, range 0..7).
   The function returns true if digital input is connected to ground (switch closed) or false otherwise.
   
 void digitalWrite(int pos, boolean state)  
   Change the state (of/off) of a digital output (e.g LED) connected to position "pos" of digital outputs
   pos - Position of connected digital output (for an 8 digital outputs board, range 0..7).
   state - "true" to turn on the digital output (set output to 5 Volts).
           "false" to turn off the digital output (set output to ground).

 void dataRate(int pos, int rate)
   Change data rate for analog input. This sets the intervals the sensor is read and reported.
   The "rate" represens time, in milliseconds, between readings.
   Applicable vlaues for "rate" are: ,1 2, 4, 8 and on in multiples of 8 up to 1000. other values will be rounded to closest applicable value.

 void digitalInputChange(InterfaceKit callingInterfaceKit)  
   An event function called when a digital input is changed - see Interface_Kit_Event_Example
 
*/
