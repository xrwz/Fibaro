
Smartthings-nuki
nuki smart lock controller for Smartthings steps to install the device handler:

Copy the code and login into the smartthings console https://graph.api.smartthings.com (EU Site https://graph-eu01-euwest1.api.smartthings.com)

At the top of the console click on "My Device Handlers"
Click on "Create New Device Handler"
Next on the top select "From Code"
Paste the code into the page and click on "Create" at the bottom of the page
After you get the "Device type published successfully" - click on "Publish" and select "For me"
Click on "My Devices" at the top
Click on "New Device"
In the "Name" fill -> Nuki (or anything else this will be the name of the Device)
In the "Device Network Id" fill -> Nuki (or anything else - must be unique).
In the "Type" select "Simulated Lock" (this is the device we have created in the steps before)
In the "Version" select "Self-Published"
In "Location" select your location
In "Hub" select the hub you want to control the nuki
Click on "Create"
DONT close the console open a new page and go to https://web.nuki.io
Login to your account click on the setting at the top and select API
Click on "Generate API token"
In the "API token name" -> select a name for the token for example NukiSmartThings (can be anything else).
ALL the rights should be enabled otherwise it will not work
Click on "Generate" and copy the code - Dont close the nuki webpage
Go the smartthings console under "My Devices" Click on the Device you have create before
Next to "Preferences" click on "edit" in the "API token" paste the token.
Go back to nuki web and go to https://web.nuki.io/en/#/admin/smartlocks/
Click on the lock you want to control - now the tricky part after click on it - click on the browser web address the url will look like this https://web.nuki.io/en/#/admin/smartlocks/XXXXXX where the XXXXXX - is the lock ID copy the number and return to the smartthings console.
In the nukiId paste the number.
Click on "Save"
Open the smartthings app and you are ready to control the Nuki Lock
