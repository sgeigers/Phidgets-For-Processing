# Phidgets For Processing
A Library by Shachar Geiger for the [Processing](http://www.processing.org) programming environment.
Last update, 21/07/2016. 

Allows easy control over Phidget boards. 
Only for Windows based systems currently.
Examples based documentation - everything you need to know in order to use this library is inside the examples - just open the example that fits the Phidget board you'd like to use.
Mostly suitable for beginners, but more advanced users will also enjoy the ease and directness of use. Supports most common Phidgets boards. For details on Phidgets, goto [Phidgets website](http://www.phidgets.com).

## Installation
Open the Processing editor, choose Sketch -> Import Library -> Add Library, type "phidgets" in the search bar, choose PhidgetsForProcessing and press "Install" below. After install is finished, exit the editor and restart it.

## Troubleshooting
If, when running an example, you get an error similar to "ClassNotFoundException: com.phidgets.PhidgetException": 
<ol>
<li> Make sure Phidgets' latest driver is properly installed on your system (go to Phidgets Drivers and install the driver). 
<li>Make sure the board is connected to the computer. 
<li>Open "Phidgets Control Panel" (installed with the driver), and make sure the connected board is shown. You can test the board by double-clicking on it, but make sure to close the panel when you finish, or Processing won't be able to find it.
<li>If all is well up to here, you probably need to update Java's support for Phidgets in the library, so:
<li>Download [phidgets21jar.zip](http://www.phidgets.com/downloads/libraries/phidget21jar.zip), and unzip it.
<li>Replace phidget21.jar in library folder (typically "c:\My Documents\Processing\libraries\PhidgetsForProcessing\library") with the new file you just downloaded.
<li>Restart Processing.
</ol>

## Reference
Beginners are encouraged to open the examples from within the Processing environment (File -> Examples -> Contributed Libraries -> Phidgets For Processing).<br>
More advanced programmers can have a look at the javadoc reference. A copy of the reference is included in the .zip as well.
