/**
 * WARNING:
 * Never leave the '-' terminal unconnected. If not in use (case of most sensors), connect it to GND terminal.
**/

import shenkar.phidgets.*;

FrequencyCounter myFreqCnt;

void setup() {
  size(300,300);
  
  myFreqCnt = new FrequencyCounter(this);
  
}


void draw() {
  background(0);
  float RPM = myFreqCnt.frequency(0);
  RPM /= 60;
  drawDial(RPM);
}

void drawDial(float RPM) {
  translate (150,200);
  pushMatrix();
  strokeWeight(2);
  stroke(150);
  for (int i=0; i<19; i++) {
    line (-100,0,-80,0);
    rotate(PI/18);
  }
  popMatrix();
  float maxRPM = 4;
  RPM = min(RPM, maxRPM);
  rotate(RPM/maxRPM*PI);
  strokeWeight(3);
  stroke(220);
  line (-78,0,0,0);
}

/*

 Quick help:
 
 frequency(pos)              // get last calculated frequency of sensor connected to position "pos"
 count(pos)                  // get total number of pulses since program run or reset command
 micros(pos)                 // get total time (in microseconds) since program run or a reset command for this position
 reset(pos)                  // resets count and micros for position "pos"
 setTimeout(pos)             // set timeout value (in microseconds) until returning 0 Hz if no pulse is detected
 setLogicSensor(pos)         // tells the board that the sensor connected to position "pos" outputs logic level voltages (3.3V or 5V)
 setZeroCrossingSensor(pos)  // tells the board that the sensor connected to position "pos" outputs voltages around 0V
 setUnknownSensor(pos)       // tells the board that the sensor connected to position "pos" outputs unknown voltages
  
 
 Full help:
 
 shenkar.phidgets.* - The library which contains all the classes and functions that allows easy conrol of the Phidgets boards.
   Shenkar is a college for design, engineering and art in Israel.
    
 FrequencyCounter   - The class which represents a PhidgetFrequencyCounter board in Processing.
 myFreqCnt - A name to represent the board that is connected to the computer.
 
 *** For connecting more than one board of the same type (e.g FrequencyCounter) to a computer, see example "Connect_Multiple_Phidgets".
 
 new FrequencyCounter(this) - A command to create an object and connect it to the connected board.
 
 myEncoder.setPosition(0) - reset encoder position to 0. Encoder position is kept between runs of the program, as long as the board is powered, so it is recommended
   to reset its position in beginning of sketch.

 myFreqCnt.frequency(0) - read the frequency of sensor connected to position 0.
 
 RPM /= 60 - divide value read from sensor by 60 in order to convert to RPM (revolutions per minute). The value read from the board
   is in Hz (cycles per seconds), so in order to convert to RPM, this value is divided by 60. 
 
 All functions:

 float frequency(int pos)              
   Return last calculated frequency of sensor connected to position "pos", in Hz. This function will return 0 if no pulses are detected
   for a amount of time set by "setTimeout" function. Frequency is recalculated up to 31.25 times a second, depending on the pulse rate.
   
 long count(int pos)
   Return total number of pulses since program run or reset command issued for the chosed position.
   
 long micros(int pos)
   Return total time (in microseconds) since program run or a reset command issued for the chosen position.
  
 void reset(int pos)
   Reset count and micros for position "pos".
   
 setTimeout(pos)
   Set the timeout value, in microseconds. This value is used to set the time to wait without detecting a signal before reporting 0 Hz.
   The valid range is 0.1 - 100 seconds (100,000 - 100,000,000 microseconds).
   This value represents the lowest frequency that will be measurable.
   
 setLogicSensor(pos)
 setZeroCrossingSensor(pos)
 setUnknownSensor(pos)
   These functions sets the detecting method for the signals from the sensors connected. Most sensors use logic level voltages (3.3v or 5V),
   thus match the default method (no need to call any function),
   Some sensors (like tachometers) use values around zero voltage (positive and negative voltages). If using such a sensor, call the
   "setZeroCrossingSensor" function in "setup", after creating the myFrqCnt object ("myFrqCnt = new ...").
 
*/