// Example for addressing 2 interface kits:

import shenkar.phidgets.*;

InterfaceKit interfaceKit1;
InterfaceKit interfaceKit2;

void setup() {
  size(800,600);
  interfaceKit1 = new InterfaceKit(this, 411059);    // 411059 is the Serial number of the first interface kit
  interfaceKit2 = new InterfaceKit(this, 407880);    // 407880 is the Serial number of the second interface kit
}

void draw() {
  println (interfaceKit1.analogRead(0));
  println (interfaceKit2.analogRead(0));
}

// Detecting an event and which one of the connected interface kits enitiated it:

void digitalInputChange(InterfaceKit callingInterfaceKit) {
  if (callingInterfaceKit.getSer() == 407880) {
    println("Input change on second interface kit board");
  }
}

/*
 This example demonstrate the connection of several Interface Kit boards, but the same functions and procedures can be used for
  any Phidgets board. Just replace the "ik" with the name of the boards in their example (e.g. "stepper") and the "InterfaceKit"
  with the type of boards in their example (e.g. "Stepper").
  
 When connecting more than one board of the same type, you should give them different names (e.g. ik1, ik2 etc.).
 Each board is referred to by its serial number, which is printed on a label attached to its bottom (e.g. "S/N 322072").
 If the label is missing or corrupt, refer to the notes at the end of this example for getting a board's serial number. 
  
 When using event functions (e.g "digitalInputChange") , it might be helpful to know which Phidget board initiated the event.
  You can use "printSer" to print a serial number of a specific board, and then, inside the event, check if the calling board has
  the same serial number using "getSer".


 Getting a board's serial number, in case of missing or corrupt label:
 1. Connect only one board to the computer.
 2. Copy the code below to a new sketch.
 3. If using a different board from Interface Kit, replace the type "InterfaceKit" with the adequate type (which can be found
     in the example of the board used).
 4. Run the sketch. The board's serial number is printed to the console (bottom of the Processing editor), and can be marked
     with the mouse and copied with Ctrl-C.


// example for getting an Interface Kit's serial number:
 
import shenkar.phidgets.*;

InterfaceKit myInterfaceKit;

void setup() {
  myInterfaceKit = new InterfaceKit(this);
  myInterfaceKit.printSer();
}

void draw() {
}
  
 
 
*/
 
