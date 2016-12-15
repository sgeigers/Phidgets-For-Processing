/**
 * WARNING:
 * Make sure the power supply is unplugged before attaching or removing wires from the terminal blocks.
 * Failure to do so could cause permanent damage to the PhidgetStepper board. 
**/

import shenkar.phidgets.*;

Stepper myStepper;

void setup() {
  size(300,300);
  
  myStepper = new Stepper(this);
  
  myStepper.setSpeed(0, 5000);
  myStepper.setAcceleration(0, 100000);
  myStepper.setPosition(0, 0);
  myStepper.setCurrentLimit(0, 1.3);
  myStepper.engage(0);
}

void draw() {
  myStepper.setTarget(0, mouseX*10);
}

/*

 Quick help:
 
 setSpeed(mot, spd)          // set maximum speed "spd" for motor connected as station "mot" of board. range: 0..250000 (1/16th steps pre second)
 setAcceleration(mot, acc)   // set acceleration (and deceleration) for motor connected as atation "mot" of board. range: 0..10000000 (units of speed per second)
 setCurrentLimit(mot, crnt)  // set current limit for motor. see below for explanations.
 setTarget(mot, trgt)        // set target position for motor (in 1/16th steps).
 getPosition(mot)            // get current position of motor (in 1/16th steps).
 setPosition(mot, pos)       // set current position of motor (in 1/16th steps).
 engage(mot)                 // enable control of the motor connected to station "mot". must be called once for running the motor.
 disEngage(mot)              // disable control of motor by the board. can be called for allowing the motor to be passively turned.
 stopped(mot)                // return true if the motor is stopped.


 Full help: 
 
 shenkar.phidgets.* - The library which contains all the classes and functions that allows easy conrol of the Phidgets boards.
   Shenkar is a college for design, engineering and art in Israel.
   
 Stepper   - The class which represents a PhidgetStepper board in Processing.
 myStepper - A name to represent the board that is connected to the computer.
 
 *** For connecting more than one board of the same type (e.g PhidgetStepper) to a computer, see example "Connect_Multiple_Phidgets".
 
 new Stepper(this) - A command to create an object and connect it to the connected board.
 
 myStepper.setSpeed(0, 5000) - set maximum speed for the motor.

 myStepper.setAcceleration(0, 100000) - set acceleration and deceleration for the motor.
 
 myStepper.setPosition(0, 0) - reset motor's position (in case it has been changed, e.g when using Phidgets Control Panel for testing the board).
 
 myStepper.setCurrentLimit(0, 1.3) - set maximum current for motor. this needs to be changed to fit current motor used. see explanation below.
 
 myStepper.engage(0) - enable opertion of the motor.
 
 myStepper.setTarget(0, mouseX*10) - rotate motor according to mouse's x position.

 
 All functions:

 void setSpeed(int mot, float spd)
   Set maximum speed "spd" (0..250000) for motor connected as station "mot" of board.
   This is maximum speed and not actual speed, because the speed is controlled with a "ramp" set by acceleration and magnitude of position change. Low acceleration or small change
     in position might bring to a motion in which the maximum speed is never reached.
   The units of speed are units of position per second, which means 1/16th of motor's step per second.
   
 void setAcceleration(int mot, float acc)
   Set acceleration (and deceleration) for motor connected as atation "mot" of board.
   This is the pace of change in speed of the motor.
   Units are units of speed per second (amount of speed change per second).
   Usually best values for speed and acceleration can only be set by trial and error. Too high values can bring to motor stall and missed steps.
   
 void setCurrentLimit(int mot, float crnt)
   Set current limit for motor.
   Current limit sets how strong the motor can act, but setting it too high might damage the motor or the board, or bring to lower performances of the motor.
   Best if set by the specification of the motor (datasheet). Otherwise - you can test with different values when your motor is connected to your system and setup with high physical
     load. If the motor or board get too hot to touch - set the current limit to lower value. If it's too weak, you can try to put it higher, or lower the acceleration or maxSpeed. 
   
 void setTarget(int mot, long trgt)
   Set target position for motor (in 1/16th steps).
   If continues rotation is desired, one way to achhieve this is by setting target to maximum positive or negative values (1E+15, which is 1,000,000,000,000,000).
   Units are 1/16th of motor's step. This means, for example, for a typical stepper motor with 200 steps per revolution, setting target position of 3200 (when moved fro position 0)
     will result in exactly 1 turn of the motor.
   
 long getPosition(int mot)
   Get current position of motor (in 1/16th steps).
   
 void setPosition(int mot, long pos)
   Set current position of motor (in 1/16th steps).
   Usually used for resetting initial position of mottor, like the example above.
   
 void engage(int mot)
   Enable control of the motor connected to station "mot".
   This connects the power source to the motor through the controlling electronics, which means once this function is called, the motor is powered.
   If target is different than current position when this cfunction is called, the motor starts moving immediately (with acceleration and max speed set by above functions).
   This function must be called once for running the motor.
   
 void disEngage(int mot)
   Disable control of motor by the board. 
   Can be called for allowing the motor to be passively turned.
   This function is automatically called for all motors once the program ends.
   
 boolean stopped(int mot)                // return true if the motor is stopped.
   Returns "true" if the servo is stopped.
   NOTE: almost any command sent to the motor will temporarily set "stopped" response to "false" (including setting acceleratiion or speed for a stopped motor). In order to
     properly use this function, make sure no other commands was sent to board for roughly 100ms before calling it. 
   
*/
