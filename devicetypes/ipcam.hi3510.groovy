/**
* hi3510 ip camera device type
*
* This implementation supports snapshots (low resolution), pan/tilt, and IR LED control.
*
* Known supported cameras:
*     	Tenvis IPROBOT3 (2014)
*     	Foscam 9821 / 8608W
*		Foscam 8608W
*		InStar (unknown models)
*		Wansview NCH536MW
* and probably several others.
*
* To test if your camera is supported, open a web browser and go to:
* 		http://(camera ip address):(camera port)/cgi-bin/hi3510/param.cgi?cmd=getinfrared
* Type in your camera's username/password. The response will be similar to:
*		var infraredstat= ...
*
* Installation:
* 1) Go to "My Device Types" in your ST developer account (https://graph.api.smartthings.com/ide/devices)
* 2) Click "New Device Type"
* 3) Click "From Code"
* 4) Paste this entire file into the space provided and click "Create"
* 5) CLick "Publish" then "For Me"
* 6) Click "My Devices"
* 7) Click "New Device"
* 8) Fill in Name, Device Network Id
* 9) For "Type", choose "HI3510 Camera Device" (near bottom)
* 10) For "Version", "choose "Published"
* 11) Click "Create"
* 12) Go into the ST app
* 13) Touch the "Marketplace" tab (furthest right)
* 14) Touch "Not Yet Configured" and choose your new device
* 15) Fill in the required information and touch "Next"
* 16) Optionally set up a SmartApp and touch "Done"
*
* Copyright 2015 uncleskippy
*
* This implementation is based on the Foscam Universal Device by skp19:
*				https://github.com/skp19/st_foscam_universal
*
*  Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
*  in compliance with the License. You may obtain a copy of the License at:
*
*      http://www.apache.org/licenses/LICENSE-2.0
*
*  Unless required by applicable law or agreed to in writing, software distributed under the License is distributed
*  on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License
*  for the specific language governing permissions and limitations under the License.
*
*/

metadata {
	definition (name: "HI3510 IP Camera Device", namespace: "uncleskippy", author: "uncleskippy") {
			capability "Polling"
			capability "Image Capture"

			attribute "ledStatus", "string"

			command "moveLeft"
			command "moveRight"
			command "moveUp"
			command "moveDown"            
			command "zoomIn"            
			command "zoomOut"            

			command "moveToPreset1"
			command "moveToPreset2"
			command "moveToPreset3"
			command "moveToPreset4"

			command "ledOn"
			command "ledOff"
			command "ledAuto"            
	}

	preferences {
		input("ip", "string", title:"Camera IP Address", description: "Camera IP Address", required: true, displayDuringSetup: true)
		input("port", "string", title:"Camera Port", description: "Camera Port", defaultValue: 80 , required: true, displayDuringSetup: true)
		input("username", "string", title:"Camera Username", description: "Camera Username", defaultValue: "admin", required: true, displayDuringSetup: true)
		input("password", "password", title:"Camera Password", description: "Camera Password", defaultValue: "admin", required: true, displayDuringSetup: true)
		input("mirror", "bool", title:"Mirror?", description: "Camera Mirrored?", defaultValue: false)
		input("flip", "bool", title:"Flip?", description: "Camera Flipped?", defaultValue: false)
	}

	tiles(scale: 2) {
		standardTile("camera", "device.image", width: 1, height: 1, canChangeIcon: true, inactiveLabel: true, canChangeBackground: false) {
			state "default", label: "", action: "", icon: "st.camera.camera", backgroundColor: "#FFFFFF"
		}

		carouselTile("cameraDetails", "device.image", width: 6, height: 4) { }

		standardTile("take", "device.image", width: 2, height: 2, canChangeIcon: false, inactiveLabel: true, canChangeBackground: false) {
			state "take", label: "Take", action: "Image Capture.take", icon: "st.camera.camera", backgroundColor: "#FFFFFF", nextState:"taking"
			state "taking", label:'Taking', action: "", icon: "st.camera.take-photo", backgroundColor: "#53a7c0"
			state "image", label: "Take", action: "Image Capture.take", icon: "st.camera.camera", backgroundColor: "#FFFFFF", nextState:"taking"
		}

		valueTile("left", "device.image", width: 1, height: 2, decoration: "flat") {
			state "blank", label: "<", action: "moveLeft", icon: ""
		}
		valueTile("right", "device.image", width: 1, height: 2, decoration: "flat") {
			state "blank", label: ">", action: "moveRight", icon: ""
		}

		standardTile("up", "device.button", width: 2, height: 1, canChangeIcon: false, canChangeBackground: false, decoration: "flat") {
			state "up", label: "", action: "moveUp", icon: "st.thermostat.thermostat-up"
		}

		standardTile("down", "device.button", width: 2, height: 1, canChangeIcon: false, canChangeBackground: false, decoration: "flat") {
			state "down", label: "", action: "moveDown", icon: "st.thermostat.thermostat-down"
		}

		standardTile("zoomin", "device.button", width: 1, height: 1, canChangeIcon: false, canChangeBackground: false, decoration: "flat") {
			state "down", label: "Zoom\nin", action: "zoomIn", backgroundColor: "#FFFFFF"
		}

		standardTile("zoomout", "device.button", width: 1, height: 1, canChangeIcon: false, canChangeBackground: false, decoration: "flat") {
			state "down", label: "Zoom\nout", action: "zoomOut", backgroundColor: "#FFFFFF"
		}

		standardTile("ledAuto", "device.ledStatus", width: 1, height: 1, canChangeIcon: false, inactiveLabel: true, canChangeBackground: false) {
			state "auto", label: "auto", action: "ledAuto", icon: "st.Lighting.light11", backgroundColor: "#53A7C0"
			state "off", label: "auto", action: "ledAuto", icon: "st.Lighting.light13", backgroundColor: "#FFFFFF"
			state "on", label: "auto", action: "ledAuto", icon: "st.Lighting.light13", backgroundColor: "#FFFFFF"
		}

		standardTile("ledOn", "device.ledStatus", width: 1, height: 1, canChangeIcon: false, inactiveLabel: true, canChangeBackground: false) {
			state "auto", label: "on", action: "ledOn", icon: "st.Lighting.light11", backgroundColor: "#FFFFFF"
			state "off", label: "on", action: "ledOn", icon: "st.Lighting.light11", backgroundColor: "#FFFFFF"
			state "on", label: "on", action: "ledOn", icon: "st.Lighting.light11", backgroundColor: "#FFFF00"
		}

		standardTile("ledOff", "device.ledStatus", width: 1, height: 1, canChangeIcon: false, inactiveLabel: true, canChangeBackground: false) {
			state "auto", label: "off", action: "ledOff", icon: "st.Lighting.light13", backgroundColor: "#FFFFFF"
			state "off", label: "off", action: "ledOff", icon: "st.Lighting.light13", backgroundColor: "#53A7C0"
			state "on", label: "off", action: "ledOff", icon: "st.Lighting.light13", backgroundColor: "#FFFFFF"
		}

		standardTile("blank11", "device.image", width: 1, height: 1) {
			state "blank", label: " ", backgroundColor: "#FFFFFF"
		}
        
		valueTile("ledLabel", "device.image", width: 1, height: 1, decoration: "flat") {
			state "blank", label: "LED\nMode", backgroundColor: "#FFFFFF"
		}
        
		valueTile("preset1", "device.image", width: 1, height: 1, decoration: "flat") {
			state "blank", label: "move\n1", action: "moveToPreset1", backgroundColor: "#53a7c0"
		}
        
		valueTile("preset2", "device.image", width: 1, height: 1, decoration: "flat") {
			state "blank", label: "move\n2", action: "moveToPreset2", backgroundColor: "#53a7c0"
		}
        
		valueTile("preset3", "device.image", width: 1, height: 1, decoration: "flat") {
			state "blank", label: "move\n3", action: "moveToPreset3", backgroundColor: "#53a7c0"
		}
        
		valueTile("preset4", "device.image", width: 1, height: 1, decoration: "flat") {
			state "blank", label: "move\n4", action: "moveToPreset4", backgroundColor: "#53a7c0"
		}

		main "camera"
		details(["cameraDetails", "ledLabel", "blank11", "preset1", "up", "preset2",
									"ledAuto", "zoomin", "left", "take", "right",
									"ledOn", "zoomout",
									"ledOff", "blank11", "preset3", "down", "preset4"])
	}
}

//TAKE PICTURE
def take() {
	hubGet("/web/tmpfs/auto.jpg", true)
}

def ledOn() {
	log.debug("LED changed to: on")
	sendEvent(name: "ledStatus", value: "on");
	hubGet("/web/cgi-bin/hi3510/param.cgi?cmd=setinfrared&-infraredstat=open", false)
}

def ledOff() {
	log.debug("LED changed to: off")
	sendEvent(name: "ledStatus", value: "off");
	hubGet("/web/cgi-bin/hi3510/param.cgi?cmd=setinfrared&-infraredstat=close", false)
}

def ledAuto() {
	log.debug("LED changed to: auto")
	sendEvent(name: "ledStatus", value: "auto");
	hubGet("/web/cgi-bin/hi3510/param.cgi?cmd=setinfrared&-infraredstat=auto", false)
}

def moveToPreset1() {
	log.debug("preset1")
   	hubGet("/web/cgi-bin/hi3510/preset.cgi?-act=goto&-number=0", false);
}

def moveToPreset2() {
	log.debug("preset2")
	hubGet("/web/cgi-bin/hi3510/preset.cgi?-act=goto&-number=1", false);
}

def moveToPreset3() {
	log.debug("preset3")
	hubGet("/web/cgi-bin/hi3510/preset.cgi?-act=goto&-number=2", false);
}

def moveToPreset4() {
	log.debug("preset4")
	hubGet("/web/cgi-bin/hi3510/preset.cgi?-act=goto&-number=3", false);
}

//PTZ CONTROLS
def moveLeft() {
	if(mirror == "true") {
		hubGet("/web/cgi-bin/hi3510/ptzctrl.cgi?-step=1&-act=right", false);
	} else {
		hubGet("/web/cgi-bin/hi3510/ptzctrl.cgi?-step=1&-act=left", false);
	}
}

def moveRight() {
	if(mirror == "true") {
		hubGet("/web/cgi-bin/hi3510/ptzctrl.cgi?-step=1&-act=left", false);
	} else {
		hubGet("/web/cgi-bin/hi3510/ptzctrl.cgi?-step=1&-act=right", false);
	}
}

def moveUp() {
	if(flip == "true") {
		hubGet("/web/cgi-bin/hi3510/ptzctrl.cgi?-step=1&-act=down", false);
	} else {
		hubGet("/web/cgi-bin/hi3510/ptzctrl.cgi?-step=1&-act=up", false);
	}
}

def moveDown() {
	if(flip == "true") {
		hubGet("/web/cgi-bin/hi3510/ptzctrl.cgi?-step=1&-act=up", false);
	} else {
		hubGet("/web/cgi-bin/hi3510/ptzctrl.cgi?-step=1&-act=down", false);
	}
}

def zoomIn() {
	hubGet("/web/cgi-bin/hi3510/ptzctrl.cgi?-step=0&-act=zoomin", false);
}

def zoomOut() {
	hubGet("/web/cgi-bin/hi3510/ptzctrl.cgi?-step=0&-act=zoomout", false);
}

def poll() {
	log.trace("poll");
	hubGet("/web/cgi-bin/hi3510/param.cgi?cmd=getinfrared", false);
}

def hubGet(def apiCommand, def useS3) {
	//Setting Network Device Id
	def iphex = convertIPtoHex(ip)
	def porthex = convertPortToHex(port)
	device.deviceNetworkId = "$iphex:$porthex"
	log.debug "Device Network Id set to ${iphex}:${porthex}"

	// Create headers
	def headers = [:]
	def hostAddress = "${ip}:${port}"
	headers.put("Host", hostAddress)
	def authorizationClear = "${username}:${password}"
	def authorizationEncoded = "Basic " + authorizationClear.encodeAsBase64().toString()
	headers.put("Authorization", authorizationEncoded)

	//log.debug headers
	log.debug("Getting ${apiCommand}")
	def hubAction = new physicalgraph.device.HubAction(
		method: "GET",
		path: apiCommand,
		headers: headers
    )
    
	if(useS3) {
		log.debug "Outputting to S3"
		hubAction.options = [outputMsgToS3:true]
	} else {
		log.debug "Outputting to local"
		hubAction.options = [outputMsgToS3:false]
	}
    
    log.debug hubAction
	return hubAction
}

def parse(String description) {
	log.debug "Parsing '${description}'"
	def map = stringToMap(description)
	//log.debug map
	def result = []

	if (map.tempImageKey) {
    	try {  
     		storeTemporaryImage(map.tempImageKey, getPictureName())  
		} catch(Exception e) {  
			log.error e  
		}  
	} else if (map.headers && map.body) {
		if (map.body) {
			def body = new String(map.body.decodeBase64())
			if(body.find("infraredstat=\"auto\"")) {
				log.info("Polled: LED Status Auto")
				sendEvent(name: "ledStatus", value: "auto")
			} else if(body.find("infraredstat=\"open\"")) {
				log.info("Polled: LED Status Open")
				sendEvent(name: "ledStatus", value: "on")
			} else if(body.find("infraredstat=\"close\"")) {
				log.info("Polled: LED Status Close")
				sendEvent(name: "ledStatus", value: "off")
			}
		}
	}
	result
}

private getPictureName() {
	def pictureUuid = java.util.UUID.randomUUID().toString().replaceAll('-', '')
	"image" + "_$pictureUuid" + ".jpg"
}

private String convertIPtoHex(ipAddress) { 
	String hex = ipAddress.tokenize( '.' ).collect {  String.format( '%02X', it.toInteger() ) }.join()
	return hex
}

private String convertPortToHex(port) {
	String hexport = port.toString().format( '%04X', port.toInteger() )
	return hexport
}