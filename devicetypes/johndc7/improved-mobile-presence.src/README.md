
https://community.smartthings.com/t/release-improved-mobile-presence-android-only/102492


Improved Mobile Presence
This project is a mobile presence system with additional features and improved speed / reliability. I started working on this after having many frustrations with the built in mobile presence system and battery issues with the arrival sensor. I am open to any ideas or suggestions and check this thread often so let me know what you think. :slight_smile:

Setup Instructions
Step 1: Install Presence Sensor app
Accept any prompts to ignore optimizations (If you do not do this the app will be blocked from communicating with the server so it will not work)

You will need the device ID listed at the top of the app
Get it on Google Play

If you are using Android 8.1 you need to install the patch AND the app from Google Play
Android 8.1 Patch 

Step 2: Setup GitHub integration (Written by @bobbles)
SmartThings IDE can be accessed at https://account.smartthings.com 6

Once you have github integration you do the following.
Go to the code in github.
You will then see an icon that says ‘Fork’. Top right.
image
Click on it.
Then in the ide go to device handlers.
Click on settings.
image
Click on add a new repository and fill in the fields with the following.
image
Click on Save.
Now in the device handlers, for the Presence DH click on the 3 lines on the left.
image
Now click on the Dropdown for Github Repository and select the repository that you just set up.
image
Now click on update.
It should now change to black italics and look like this.
Step 3: Install SmartApp and Device Handler
Click “My SmartApps”
Click “Update from Repo” then “Smartthings (Master)”
Install “Presence Sensor”
Check “Publish” and click “Execute Update”
Click “My Device Handlers”
Click “Update from Repo” then “Smartthings (Master)”
Install “Improved Mobile Presence”
Check “Publish” and click “Execute Update”
Step 4: Create Devices
Perform this step for every device you wish to create

Click “My Devices” then “New Device”
Enter a name and device network ID (Can be anything)
Set the type to “Improved Mobile Presence”
Select the correct location and hub
Click “Create”
Click “Edit” under the preferences section
setid.PNG600x516 10.2 KB
Enter the device ID from the Presence Sensor app (Step 1)
Set “Presence Timeout” to 0
Click “Save”
Everything should be working now but your response time will be limited to one minute
If you authorize Presence Sensor using the following steps, the response time should go down to less than 5 seconds

Step 5: Enable OAuth
Click “My SmartApps”
Click the edit icon next to “Presence Sensor”
Click on the OAuth Section
Make sure OAuth is enabled and you have a “Client ID” and “Client Secret”
Keep this tab open as you will need it for the next step
Step 6: Authorize Presence Sensor
This is a relatively new feature that I have been testing so please let me know if you have any issues

Go to https://st.callahtech.com/link 9
Enter your Device ID and Client Secret from Step 5 and click “Next”
Sign in if prompted and check all devices created in Step 4 and click “Allow”
Step 7: Create Locations in Presence Sensor app
Open Presence Sensor app
Click on a location to edit or click the add button to create a new one
a. Name: Location name
b. Home location: If checked, you will show as present when at this location
c. Networks: Connecting to any of the checked networks will cause you to show that you are at this location
Everything should be working now
If you have any questions about this process or any other issues let me know.

WebCore
More information about WebCore can be found at https://community.webcore.co/

This project works with WebCore and has the following attributes

currentLocation: The location where you currently are
previousLocation: The last location you were
presence: “present” if at a home location or “not present” if anywhere else
For example, To set the thermostat to 70 degrees when you leave work you could do this:
CaptureCapture.PNG780x743 15.6 KB
Download Links
Must have app from Google Play and device handler installed
If you are using Android 8.1, you need to install the Android 8.1 Patch

Device Handler

SmartApp

Android 8.1 Patch

Presence Sensor App
Get it on Google Play
