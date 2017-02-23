KINETECHNIK MOTORSPORTS DATA ACQUISITION AND ANALYSIS TOOL README
==================================================================

System Requirements
-------------------------
Windows 10 64-bit (will NOT work on a 32-bit machine)
4GM of RAM minimum, 8GB+ recommended

**THE BELOW IS OUTDATED. WILL UPDATE README AT LATER DATE**
Development Revision 0.3
-------------------------
-GUI improvements all around

-Data Analysis implementation has begun. Can now draw V/t traces and TT/t traces for individual laps for a selected driver.

-Load XML data to the program has been started, but for now loading doesn't work.

Development Revision 0.2
-------------------------
-Program now displays event data on an "event dashboard"

-Program now maintains a live leaderboard of all drivers per event. It automatically places drivers in 1st, 2nd, and 3rd place based on fastest lap times. The leaderboard also displays a driver's fastest lap time, their last laptime, and the +/- difference between their previous lap and their last lap. 

-Program can now save data to XML as data is entered and laps are added. XML file names are generated based on the event name field in the "Create New Event" window. XML files are persistent throughout the lifetime of the event - in the event of program failure, the XML will still be there, so all of the event data will not be lost. 

-The program can 'close out' an event by asking for the BTC/D from the user and appending them to the XML. The program automatically calculates and saves percent BTC/D as well as the raw BTC/D values in the XML for future reference. 

-After a lap is added for a driver, the program draws a V/t trace that will behave one of two ways: If the latest lap for the driver was their fastest lap, then the V/t will only display a trace of their fastest lap. If, however, the driver's latest lap was slower than their overall fastest lap, then the program will draw an overlaid trace of the driver's fastest lap and last lap so thay they can make comparisons.

Development Revision 0.1
-------------------------
The program can: 
- Make a new event
- Log current car suspension setup on per-event basis
- Add up 1-3 drivers to event
- Record data on a per-lap, per-driver basis. Each driver maintains own set of laps, and each lap maintains own set of lap-specific recorded data. The program keeps track of how many laps an individual driver has driven throughout the course of an event.
- Program will generate V/t (NOT V/d) trace and Tire Temp/t trace for all four tires after a lap has been completed (Neat feature - if you click anywhere on the plot, a vertical and horizontal line will be drawn where you clicked. It will also return X and Y values of plot on the bottom left hand corner of the window.)

Known Issues
-------------------------
- Input validation is practically nonexistant. Please take care to input ints and doubles where needed, otherwise the program WILL crash. I will take care of this issue at a later date. 

- But Matt, you wrote this in Java - why Windows 10 only? Windows 10 is the only tested platform. It will most likely work fine on Win7, 8/8.1, but I haven't tested it. I can't speak for Linux, but I think handling serial comms in Linux *might* be different? I'm not 100% sure. For now, Windows 10 is a sure bet. 

- Why 64-bit only? The development machine is 64-bit, so I have to use the 64-bit RXTX library for serial communication. This shouldn't be an issue nowadays, as most machines ship with 64-bit OSs anyways, but on the off-chance the host machine runs a 32-bit OS, the program will crash on startup. I'll maintain this as a low-priority issue for now, because it's 2016 and you should be running a 64-bit OS. No excuses.

-The comm port is hardcoded into the program for now. I have it set at COMM3, which is what my Arduino defaults to. If your hardware is sitting at another comm port, the program will still run initially, but you won't be able to add laps and record data, just make events and change suspension setup settings. Plus, you'll get some nasty console errors.

Self-Imposed Limitations
-------------------------
- Global program tic rate is 100ms, to be in line with the hardware update rate of 100ms. Preliminary testing shows that this is fine - we'll take a look at it again once we start actually pulling values from hardware.

- You can only record 90 seconds of data per lap. Why? To safeguard against someone forgetting to stop recording data once a lap is over. And also to ensure we don't have runaway data eating too much memory.

TO-DO List
-------------------------
- Make Event Dashboard that reports basic event details DONE
- Write-to-XML IN PROGRESS
- Implement method to capture track surface and ambient air temp, either by manual entry or via hardware 
- Make data plot window size dynamic and not hardcoded DONE
- Implement input validation IN PROGRESS
- Begin Test Day Suite 
- Begin Data Analysis Suite
