import shenkar.phidgets.*;

InterfaceKit myInterfaceKit;

void setup() {
  size(300,300);
  myInterfaceKit = new InterfaceKit(this);
  background(0);
}

void draw() {
}

void digitalInputChange (InterfaceKit callingInterfaceKit) {
  if (callingInterfaceKit.input == 0) {
    if (callingInterfaceKit.state == true) {
      noStroke();
      fill(150,0,0);
      ellipse (mouseX, mouseY, 20,20);
    }
  }
}

/*

 Quick help:
 
 analogRead(pos)   // read a sensor attached to position "pos" 
 digitalRead(pos)  // read a digital input (e.g. switch) connected between G and position "pos" of digital inputs
 digitalWrite(pos, state)  // change the state (of/off) of a digital output (e.g LED) connected to position "pos" of digital outputs
 digitalInputChange(InterfaceKit callingInterfaceKit)  // an event function called when a digital input is changed
 

 Full help:
 
 shenkar.phidgets.* - The library which contains all the classes and functions that allows easy conrol of th Phidgets boards.
   Shenkar is a college for design, engineering and art in Israel.
   
 InterfaceKit   - The class which represents a Phidgets interface kit board in Processing.
 myInterfaceKit - A name to represent the board that is connected to the computer.
 
 *** For connecting more than one board of the same type (e.g interface kit) to a computer, see example "Connect_Multiple_InterfaceKits".
 
 new InterfaceKit(this) - A command to create an object and connect it to the connected board.

 void digitalInputChange (InterfaceKit callingInterfaceKit) - An event function. see full explanation bellow
 
 callingInterfaceKit.input - position of digital input that caused the event (works only inside the "digitalInputChange" function)

 callingInterfaceKit.state - new state of digital input that caused the event (works only inside the "digitalInputChange" function)

 
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
   
 void digitalInputChange (InterfaceKit callingInterfaceKit)
   When defined, this function is automatically called whenever a digital input changes stae (e.g a switch is closed or opened).
   Inside the function, it is possible to use callingInterfaceKit.input and callingInterfaceKit.state variables for getting the position of digital input that
   caused the event, and its new state.
   This is useful for wiritng input methods in the same manner they are written for kewyboard or mouse (e.g keyPressed and mousePressed).
   NOTE: the rate of calls for this function is limited by processing's frame rate (60 frames per second, by default).
   
 
*/
