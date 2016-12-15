/*
 NOTICE: Easiest way to use the board:
 - Get tag ID by printing it (connect the board, run this sketch, bring tag close to board).
 - Marking the ID in the console (bottom of Processing editor window) using the mouse.
 - Copy ID (Press Ctrl-C).
 - Paste ID in this example instead the ID in "tag" or one of the IDs in "tags" - depending on your application.
 */

import shenkar.phidgets.*;

RFID myRFID;

String tag = "3f00608fe8";

String tags[] = {
  "4d004ae490", 
  "0a00ec6c2f", 
  "4d004a6351", 
};

void setup() {
  size(800, 600);
  myRFID = new RFID(this);
}

void draw() {
  background(0);

  if (myRFID.isTag(tag)) {
    noStroke();
    fill(150, 0, 180);
    rect(0, 550, 800, 50);
  }

  switch (myRFID.whichTag(tags)) {
    case 0:
      noStroke();
      fill(200, 50, 0);
      triangle(100, 100, 300, 100, 200, 240);
      break;
  
    case 1:
      stroke(0, 150, 150);
      strokeWeight(5);
      noFill();
      rect(300, 300, 200, 200);
      break;
  
    case 2:
      noStroke();
      fill(0, 50, 200);
      ellipse(500, 400, 200, 200);
      break;
  }
}


void tagDetected(RFID callingRFID) {
  callingRFID.printTag();
}


/*

 Quick help:
 
 anyTag()                      // return true if the board "sees" a tag (any tag) in its range
 isTag(tag)                    // return true if the board sees a specific tag. "tag" is a String (i.e. "6800888721")
 whichTag(tags)                // return the index of seen tag, from a String array (see example below). if no tag seen - return -1
 printTag()                    // prints to console the ID of the current seen tag (or last seen tag, if no tag is currently seen)
 tagDetected(RFID callingRFID) // an event function called when the board sees a tag (see example below)
 tagLost(RFID callingRFID)     // an event function called when the board stops seeing a tag
 digitalWrite(pos, state)      // change the state (of/off) of a digital output (e.g LED) connected to position "pos" of digital outputs


 Full help:
 
 shenkar.phidgets.* - The library which contains all the classes and functions that allows easy conrol of the Phidgets boards.
 Shenkar is a college for design, engineering and art in Israel.
 
 RFID   - The class which represents a PhidgetRFID board in Processing.
 myRFID - A name to represent the board that is connected to the computer.
 
 *** For connecting more than one board of the same type (e.g PhidgetRFID) to a computer, see example "Connect_Multiple_Phidgets".
 
 new RFID(this) - A command to create an object and connect it to the connected board.
 
 String tag = "3f00608fe8" - definition of the String "tag" to be used with the function "isTag" (see below).
 
 String tags[] = {...} - definition of a String rray to be used with the function "whichTag" (see below) .
 
 myRFID.isTag(tag) - check if the board currently sees the tag whose ID is equal to "tag" (3f00608fe8).
 
 myRFID.whichTag(tags) - check if the board currently sees any of the tag whose IDs are listed in "tags" String array.
   If so - return index of tag in the array (for example, if tag whith ID 4d004a6351 is seen, return 2).
   else - return -1.
 
 void tagDetected(RFID callingRFID) - this function is automatically called every time the board sees a tag.
 
 callingRFID.printTag() - print the currently seen tag (or last tag seen, if none is seen now). 
   Line begins with "callingRFID", and not "myRFID" because it refers to the specific object which created the event. This has no practical meaning, unless there are more than 1 RFID boards connected to the computer.
 
 
 All functions:
 
 boolean anyTag()
   Return true if the board "sees" a tag (any tag) in its range. This is usefull for really basic detection demonstrations.
   
 boolean isTag(String tag)
   Return true if the board sees a specific tag. "tag" is a String (i.e. "6800888721")
   To obtain a specific tag's ID, use function "printTag" (or just run this sketch. see NOTICE above).
   
 int whichTag(String[] tags)
   Return the index of seen tag, from a String array.
   if no tag are currently seen - the function returns -1
   
 void printTag()
   Prints to console the ID of the current seen tag (or last seen tag, if no tag is currently seen).
   Mostly useful inside "tagDetected" event function.
   
 void tagDetected(RFID callingRFID)
   An event function called when the board sees a tag.
   When using multiple PhidgetRFID boards, the board which evoked the call can be addressed inside the function as "rf". Then its serial number can be read using the "getSer" function (see Connect_Multiple_Phisgets example)
   
 void tagLost(RFID callingRFID)
   An event function called when the board stops seeing a tag.
   
 void digitalWrite(int pos, boolean state)  
   Change the state (of/off) of a digital output (e.g LED) connected to position "pos" of digital outputs
   pos - Position of connected digital output (0/1).
   state - "true" to turn on the digital output (set output to 5 Volts).
           "false" to turn off the digital output (set output to ground).
 
 */
