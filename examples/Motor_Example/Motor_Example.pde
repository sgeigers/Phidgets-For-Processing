import shenkar.phidgets.*;

Motor myMotor;

void setup() {
  size(800, 600);
  myMotor = new Motor(this);
  background(0);
  line(400,100, 400,500);
  myMotor.setAcceleration(0, 100);
}

void draw() {
  myMotor.setSpeed(0, (mouseX-400)/4);
  int encPos = myMotor.getEncoderPosition();
  println(encPos);
}


/*

 Quick help:
 
 setSpeed(mot, spd)         // set rotation speed (-100..100) for motor connected to position "mot" of the board.
 setAcceleration(mot, acc)  // set motor acceleration (how fast it gains rotation speed). range is 24.51..6250 %/sec
 current(mot)               // get motor use of current (roughly relates to how hard it is for the motor to rotate).
 setBraking(mot, brk)       // set braking power (0..100) to use when speed is set to 0. 
 getEncoderPosition()       // get position of encoder (only for boards with encoder connector).
 setEncoderPostion(pos)     // set position "pos" for encoder (usually used for resetting position to 0).
 analogRead(pos)            // read a sensor attached to position "pos" (only for boards with analog connector). 
 digitalRead(pos)           // read a digital input (e.g. switch) connected between G and position "pos" of digital inputs (only for boards with digital connector). 

 
 Full help:
 
 shenkar.phidgets.* - The library which contains all the classes and functions that allows easy conrol of the Phidgets boards.
   Shenkar is a college for design, engineering and art in Israel.
   
 Motor   - The class which represents a PhidgetMotorControl board in Processing.
 myMotor - A name to represent the board that is connected to the computer.
 
 *** For connecting more than one board of the same type (e.g PhidgetMotorControl) to a computer, see example "Connect_Multiple_Phidgets".
 
 new Motor(this) - A command to create an object and connect it to the connected board.
 
 myMotor.setAcceleration(0, 100) - set acceleration for motor 0 to value 100.
 
 myMotor.setSpeed(0, (mouseX-400)/4) - set rotation speed for motor 0 according to x position of mouse.

 println(myMotor.getEncoderPosition()) - assuming an encoder is connected, this prints its position to the console.

 
 All functions:
 
 void setSpeed(int mot, float spd)
   Set rotation speed (-100..100) for motor connected to position "mot" of the board.
   The speed is not accurate, and depends on the load put on the motor.
   -100 means full speed in reverse, and 100 means full speed forward.
   When set to 0, the motor's behaviour depends on the "setBraking" function - from free run to fastest possible brake.
   
 void setAcceleration(int mot, float acc)
   Set motor acceleration (how fast it gains rotation speed).
   Range is 24.51..6250 %/sec.
   
 float current(int mot)
   Return motor use of current - roughly relates to how hard it is for the motor to rotate.
   Can be used, for example, for detecting if the motor is stuck (stall).
   
 void setBraking(int mot, float brk)
   Set braking power (0..100) to use when speed is set to 0.
   0 means no braking - free run.
   100 means full braking, which depends on the specific motor used.
   
 int getEncoderPosition()
   Return position of encoder (only for boards with encoder connector).
   
 void setEncoderPostion(int pos)
   Set position "pos" for encoder (usually used for resetting position to 0).
   
 int analogRead(int pos)
   Read a sensor attached to connector "pos" (only for boards with analog connector).
   The function returns an integer ranged 0..1000
   
 boolean digitalRead(pos)
   Read a digital input (e.g. switch) connected between G and terminal "pos" of digital inputs (only for boards with digital connector). 
   pos - Position of connected digital input (for an 8 digital inputs board, range 0..7).
   The function returns true if digital input is connected to ground (switch closed) or false otherwise.

*/
