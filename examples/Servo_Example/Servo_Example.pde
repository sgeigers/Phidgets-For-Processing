import shenkar.phidgets.*;

Servo myServo;

void setup() {
  size(800, 720);
  myServo = new Servo(this);
  myServo.setSpeed(0, 6400);
}

void draw() {
  myServo.setPosition(0, mouseX/4);
}

/*

 Quick help:
 
 setPosition(mot, pos)      // move servo attached to station "mot" to position "pos"
 getPosition(mot)           // get position of servo attached to station "pos". See limitations below.
 turnOff(mot)               // turn off servo attached to station "mot"
 controlOn(mot)             // activate advanced controls for servo attached to station "mot". These controls allow setting speed and acceleration.
 controlOff(mot)            // deactivate advanced controls for servo attached to station "mot". When off, the servo will always move to selected position as fast as possible.
 setAcceleration(mot, acc)  // set acceleration "acc" for servo in station "mot" (for PhisgetAdvancedServo boards only). range: 19.53125..320000
 setSpeed(mot, spd)         // set speed "spd" for servo in station "mot" (for PhisgetAdvancedServo boards only). range: 0..6400
 stopped(mot)               // returns "true" if the servo is stopped (for PhisgetAdvancedServo boards only).
 setType(mot, type)         // set type of servo connected to station "mot". This can help get a precise control over servo's position. See elaboration below.
 setParameters(...)         // manually sets motor parameters for precise control of a servo. See elaboration below.


 Full help: 
 
 shenkar.phidgets.* - The library which contains all the classes and functions that allows easy conrol of the Phidgets boards.
   Shenkar is a college for design, engineering and art in Israel.
   
 Servo   - The class which represents a PhidgetServo or PhisgetAdvancedServo board in Processing.
 myServo - A name to represent the board that is connected to the computer.
 
 *** For connecting more than one board of the same type (e.g PhisgetAdvancedServo) to a computer, see example "Connect_Multiple_Phidgets".
 
 new Servo(this) - A command to create an object and connect it to the connected board.
 
 myServo.setPosition(0, mouseY/4) - command the servo attached to station 0 of the board to rotate to position set by mouse y position in sketch's window.
 
 myServo.setSpeed(0, mouseX/4) - set servo's speed to value proportional to mouse's x position.
 
 All functions:

 void setPosition(int mot, float pos)
   Command servo attached to station "mot" to move to position "pos".
   If the board is "simple", or control is set to "off" (see below), the servo will get to that position as fast as it can.
   If the board is "advanced", it is possible to set acceleration and velocity using the proper functions (see below).
   
 float getPosition(int mot)
   Return position of servo attached to station "pos".
   If the position hasn't been set yet, the value has no meaning.
   If the servo is turned off, or not turned on yet (by setting a position with "setPosition"), the function will return -1.
   
 void turnOff(int mot)
   Turn off servo attached to station "mot".
   
 void controlOn(int mot)
   Activate advanced controls for servo attached to station "mot". These controls allow setting speed and acceleration.
   This function only works for boards of "Advanced" type.
   
 void controlOff(int mot)
   Deactivate advanced controls for servo attached to station "mot". When off, the servo will always move to selected position as fast as possible.
   This function only works for boards of "Advanced" type.
   
 void setAcceleration(int mot, float acc)
   Set acceleration "acc" (19.53125..320000) for servo in station "mot".
   This function only works for boards of "Advanced" type.   
   
 void setSpeed(int mot, float spd)
   Set speed "spd" (0..6400) for servo in station "mot".
   This function only works for boards of "Advanced" type.   
 
 boolean stopped(int mot)
   Returns "true" if the servo is stopped.
   This function only works for boards of "Advanced" type.
   NOTE: almost any command sent to the servo will temporarily set "stopped" response to "false". In order to properly use this function, make sure no other commands was sent to board for
     roughly 100ms before calling it. 
   
 void setType(int mot, int type)
   Set type of servo connected to station "mot". This can help get a precise control over servo's position.
   Possible values for "type" are:
       Servo.DEFAULT - Default - This is what the servo API been historically used, originally based on the Futaba FP-S148.
       Servo.FIRGELLI_L12_30_50_06_R - Firgelli L12 Linear Actuator 100mm 100:1.
       Servo.FIRGELLI_L12_50_100_06_R - Firgelli L12 Linear Actuator 100mm 50:1.
       Servo.FIRGELLI_L12_50_210_06_R - Firgelli L12 Linear Actuator 30mm 50:1.
       Servo.FIRGELLI_L12_100_50_06_R - Firgelli L12 Linear Actuator 50mm 100:1.
       Servo.FIRGELLI_L12_100_100_06_R -  Firgelli L12 Linear Actuator 50mm 210:1.
       Servo.HITEC_805BB - HiTec HS-805BB Mega Quarter Scale Servo.
       Servo.HITEC_815BB -  HiTec HS-815BB Mega Sail Servo.
       Servo.HITEC_HS322HD - HiTec HS-322HD Standard Servo.
       Servo.HITEC_HS422 - HiTec HS-422 Standard Servo.
       Servo.HITEC_HS485HB - HiTec HS-485HB Deluxe Servo.
       Servo.HITEC_HS5245MG - HiTec HS-5245MG Digital Mini Servo.
       Servo.HITEC_HS645MG - HiTec HS-645MG Ultra Torque Servo.
       Servo.HITEC_HS785HB - HiTec HS-785HB Sail Winch Servo.
       Servo.HITEC_HSR1425CR - HiTec HSR-1425CR Continuous Rotation Servo.
       Servo.SPRINGRC_SM_S2313M - SpringRC SM-S2313M Micro Servo.
       Servo.SPRINGRC_SM_S3317M - SpringRC SM-S3317M Small Servo.
       Servo.SPRINGRC_SM_S3317SR - SpringRC SM-S3317SR Small Continuous Rotation Servo.
       Servo.SPRINGRC_SM_S4303R - SpringRC SM-S4303R Standard Continuous Rotation Servo.
       Servo.SPRINGRC_SM_S4315M - SpringRC SM-S4315M High Torque Servo.
       Servo.SPRINGRC_SM_S4315R - SpringRC SM-S4315R High Torque Continuous Rotation Servo.
       Servo.SPRINGRC_SM_S4505B - SpringRC SM-S4505B Standard Servo.
       Servo.TOWERPRO_MG90 - Tower Pro MG90 Micro Servo.
   In addition, two special "types" are possible:
       Servo.RAW_US_MODE - use microseconds instead of degrees when setting motor position. Experimentally, this can lead for exact control of other motor types.
       Servo.USER_DEFINED - can be used for defining specific parameters for a motor using the function "setParameters" (see below).
   
 void setParameters(int mot, float minUs, float maxUs, float degrees)
   Manually sets motor parameters for precise control of a servo.
   mot - station of board that is connected to the servo we wish to set parameters to.
   minUs - pulse time in microseconds for minimum angle
   maxUs - pulse time in microseconds for maximum angle
   degrees - servo's degrees of rotation (e.g. 180)

*/
