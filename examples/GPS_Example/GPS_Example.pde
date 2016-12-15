import shenkar.phidgets.*;

GPS myGPS;

void setup() {
  size(200,200);
  
  myGPS = new GPS(this);
  background(0);
}

void draw() {
  boolean gotPos = myGPS.positionFix();
  if(gotPos) {
    float longi = myGPS.longitude();
    float lati = myGPS.latitude();
    float howFar = dist(longi, lati, 30.73422, 32.14355);
    if (howFar < 0.0005) {
      background(255);
    }
    else {
      background(0);
    }
  }
}


/*

 Quick help:
  
 positionFix() // returns true if GPS acquired a position (might take a few minutes after connection) 
 latitude()    // get latitude of current position. If position not acquired yet, returns -9999
 longitude()   // get longitude of current position
 altitude()    // get altitude of current position
 velocity()    // get velocity (speed) of movement
 heading()     // get direction of movement (heading)


 Full help:
 
 shenkar.phidgets.* - The library which contains all the classes and functions that allows easy conrol of the Phidgets boards.
   Shenkar is a college for design, engineering and art in Israel.
   
 GPS   - The class which represents a PhidgetsGPS board in Processing.
 myGPS - A name to represent the board that is connected to the computer.
 
 *** For connecting more than one board of the same type (e.g GPS) to a computer, see example "Connect_Multiple_Phidgets".
 
 new GPS(this) - A command to create an object and connect it to the connected board.
 
 myGPS.positionFix() - true if the GPS finished its setup and succeeded in getting a position fix from satellites.
 
 println(myGPS.longitude(), myGPS.latitude()) - print current position to console.
 
 
 All functions:
 
 int analogRead(int pos)
   Read a sensor attached to position "pos".
   pos should be an integer between 0 and the number of analog inputs that connected board has (usually 8) minus 1.
     That is - for an 8 digital inputs board, 0..7
   The function returns an integer ranged 0..1000
   
 boolean positionFix()
   Checks if a satellite fix has been acquired. If you are inside a building, you might have to go outside to get a satellite fix.
   When and while the fix is acquired, this function returns "true". otherwise, it returns "false".
   
 float latitude()
   Returns the latitude of current position in signed decimal degree format.
   If position fix is false, this function will return -9999.
   
 float longitude()   // get longitude of current position
   Returns the longitude of current position in signed decimal degree format.
   If position fix is false, this function will return -9999.
   
 float altitude()    // get altitude of current position
   Returns the altitude of current position from mean sea level in meters.
   If position fix is false, this function will return -9999.
   
 float velocity()    // get velocity (speed) of movement
   Returns the velocity (speed over ground) of the attached antenna in km/h.
   If position fix is false, this function will return -9999.
   
 float heading()     // get direction of movement (heading)
   Returns the direction of movement of attached antenna, in degrees (0 - 359.9).
   0 = True North
   180 = True South
   If position fix is false, this function will return -9999.
   
*/
