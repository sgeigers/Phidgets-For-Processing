import shenkar.phidgets.*;

Encoder myEncoder;

void setup() {
  size(300,300);
  
  myEncoder = new Encoder(this);
  
  myEncoder.setPosition(0);
}


void draw() {
  background(0);
  drawCircs(myEncoder.getPosition());
}

void drawCircs(long p) {
  noFill();
  stroke(255);
  translate (150,150);
  for (int i=0; i<40; i++) {
    rotate((float)p/1440*TWO_PI);
    ellipse(0,0,245-i*5,255-i*5);
  }
}

/*

 Quick help:
 
 For boards with only 1 encoder:
 
 getPosition()          // get position of encoder
 setPosition(pos)       // set position of encoder to "pos"
 getIndexPosition()     // get indexed position. for encoders with index, this will keep the last position when the index was reached.
 
 For boards with multiple encoder connectors:
 
 getPosition(enc)       // get position of encoder connected to station "enc" of the board
 setPosition(enc, pos)  // set position of encoder connected to station "enc" of the board to "pos"
 getIndexPosition(enc)  // get indexed position of encoder connected to station "enc" of the board. for encoders with index, this will keep the last position when the index was reached.
 
 for boards with digital input connector:
 
 digitalRead(sta)       // read a digital input (e.g. limit switch) connected between G and station "sta" of digital inputs.

 
 Full help:
 
 shenkar.phidgets.* - The library which contains all the classes and functions that allows easy conrol of the Phidgets boards.
   Shenkar is a college for design, engineering and art in Israel.
   
 Encoder   - The class which represents a PhidgetEncoder board in Processing.
 myEncoder - A name to represent the board that is connected to the computer.
 
 *** For connecting more than one board of the same type (e.g PhidgetEncoder) to a computer, see example "Connect_Multiple_Phidgets".
 
 new Encoder(this) - A command to create an object and connect it to the connected board.
 
 myEncoder.setPosition(0) - reset encoder position to 0. Encoder position is kept between runs of the program, as long as the board is powered, so it is recommended
   to reset its position in beginning of sketch.

 myEncoder.getPosition() - get position of encoder.

 
 All functions:

 long getPosition()
 long getPosition(int enc)
   Return position of encoder "enc". When "enc" is ommited, encoder "0" is being read.
   
 void setPosition(long pos)
 void setPosition(int enc, long pos)
   Set position of encoder "enc" to "pos". When "enc" is ommited. sets position to encoder "0".
   
 long getIndexPosition()
 long getIndexPosition(int enc)
   Return index position of encoder "enc". For encoders with index, this will keep the last position when the index was reached (and can be used for calculating absolute position).
   When "enc" is ommited, encoder "0" is being read.
   
 boolean digitalRead(int sta)  
   Read a digital input (e.g. limit switch) connected between G and station "sta" of digital inputs.
   sta - Station of connected digital input (for an 4 digital encoder board, range 0..3).
   The function returns true if digital input is connected to ground (switch closed) or false otherwise.
   
*/
