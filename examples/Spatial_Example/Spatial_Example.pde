// NOTE: for using the compass data (e.g. bearing of the board - the "getYaw" function), it is needed to first calibrate it.
//       See step-by-step instructions below.

import shenkar.phidgets.*;

Spatial mySpatial;

void setup() {
  size(500,500);
  
  mySpatial = new Spatial(this);
}


void draw() {
  background(0);
  translate(width/2,height/2);
  rotate(-mySpatial.getYaw()*PI/180);
  drawArrow();
}

void drawArrow() {
  stroke(200,50,50);
  strokeWeight(10);
  line(0,-200,0,200);
  line(0,-200,-40,-145);
  line(0,-200, 40,-145);
}

/*

 Quick help:
 
 getPitch()              // get pitch angle of the board (angle of axis 0 above / below horizon). Units: degrees
 getRoll()               // get roll angle of the board (angle of rotation around axis 0, from flat position). Units: degrees
 getYaw()                // get yaw angle of board (bearing of axis 0 relative to magnetic north of earth). Units: degrees
 getAcceleration(axis)   // get acceleration for "axis" (0, 1, or 2). Units: g (earth gravity)
 getRotationRate(axis)   // get rotation speed (gyro data) for "axis" (0, 1 or 2). Units: degrees pre second
 getMagneticField(axis)  // get magnetic field strength for "axis" (0, 1 or 2). Units: Gauss
 initGyro()              // initialize gyro sensor. Board must remain stationary while initializing (1-2 seconds)
 setDataRate(rate)       // sets time between sensor reads, in milliseconds. Default = 8ms
 spatialUpdate(Spatial callingSpatial)  // an event function called every time the board has a new reading. See Spatial_Event_Example


 Full help: 
 
 For using the compass, it is needed to calibarte it locally:
  - Navigate to the Phidgets installation folder on your computer, Open the 'examples' folder and run the Compass Calibrator program. 
  - Go to: http://www.ngdc.noaa.gov/geomag-web/#igrfwmm
  - Select your country and city. Click 'Get Location' and then 'Compute'. A table of values will show up in an overlaid window.
     The value you will need is the 'Total Field' value in nT.
  - Enter the magnetic field value into the calibration program.
  - Click "Start", and rotate the compass around, such that the red dots being generated on screen outline as much of a full sphere as possible. 
  - Once done, click stop.
 For more information, read http://www.phidgets.com/docs/Compass_Primer#Calibration

 shenkar.phidgets.* - The library which contains all the classes and functions that allows easy conrol of the Phidgets boards.
   Shenkar is a college for design, engineering and art in Israel.
   
 Spatial   - The class which represents a PhidgetSpatial board in Processing.
 mySpatial - A name to represent the board that is connected to the computer.
 
 *** For connecting more than one board of the same type (e.g PhidgetSpatial) to a computer, see example "Connect_Multiple_Phidgets".
 
 new Spatial(this) - A command to create an object and connect it to the connected board.

 rotate(-mySpatial.getYaw()*PI/180) - read current bearing of the board, convert result to radians and use it to rotate the drawing canvas in an inverted angle.

 
 All functions:

 float getPitch()
   Read pitch angle of the board (angle of axis 0 above / below horizon = rotation around axis 1).
   This is calculated from accelerometer data.
   This value's update rate is up to 250 times a second.
   Value returned in degrees.
   
 float getRoll()
   Read roll angle of the board (angle of rotation around axis 0, from flat position).
   This is calculated from accelerometer data.
   This value's update rate is up to 250 times a second.
   Value returned in degrees.
   
 float getYaw()
   Read yaw angle of board (bearing of axis 0 relative to magnetic north of earth).
   This is calculated from accelerometer and magnetometer data.
   This value's update rate is up to 125 times a second.
   Value returned in degrees.
   
 float getAcceleration(int axis)
   Read raw acceleration data of "axis" (0, 1, or 2).
   This value's update rate is up to 250 times a second.
   Value returned in g's (earth's gravity =~ 9.8m/(sec*sec)).

 float getRotationRate(int axis)
   Read raw rotation speed from gyro for "axis" (0, 1 or 2).
   This value's update rate is up to 250 times a second.
   Value returned in degrees per second.
   
 float getMagneticField(int axis)
   Read raw magnetic field strength for "axis" (0, 1 or 2).
   This value's update rate is up to 250 times a second.
   Value returned in Gauss.
   
 void initGyro()
   Initialize gyro sensor. Board must remain stationary while initializing (1-2 seconds).

 void setDataRate(int rate)
   Sets time between sensor reads, in milliseconds. Default = 8ms.
   Supported data rates are different for each board:
     for PhidgetSpatial 3/3/3 board: 4, 8, 12, 16, 20 etc. up to 1000
     for PhidgetSpatial 0/0/3 board: 1, 2, 4, 8, 16, 24, 32 etc. up to 1000
   
 void spatialUpdate(Spatial callingSpatial)
  An event function called every time the board has a new reading. See Spatial_Event_Example
   When defined, this function is automatically called every time the board has a new reading (i.e. every 8ms or new rate set by "setDataRate" function).
   This is useful for reading the sensors at steady rate, without losing readings, and without interrupting to other procedures (like drawing to the screen).
   Inside the function, it is possible to use following variables for getting the information about the new reading:
     
     callingSpatial.acceleration - float array. holds values of acceleration by axis (e.g. acceleration[0]).
     callingSpatial.rotationRate - float array. holds values of rotation rate (gyro readings) by axis.
     callingSpatial.magneticField - float array. holds values of magnetic field.
     
     callingSpatial.newAcceleration - boolean. "true" if new acceleration read. otherwise - the values in "spt.acceleration" are from last reading.
     callingSpatial.newRotationRate - boolean. same logics...
     callingSpatial.newMagneticField - boolean. same here.
     
     callingSpatial.timeStamp - exact time at which the reading has been taken (measured roughly from program start).
     callingSpatial.serNum - the serial number of the board which enitiated the event (see example "Connect_Multiple_Phidgets").

   NOTE: it is not possible to draw to the screen inside this function. Doing this might cause the sketch to crash.
*/
