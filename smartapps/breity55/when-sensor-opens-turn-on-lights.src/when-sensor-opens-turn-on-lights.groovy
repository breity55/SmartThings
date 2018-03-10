definition(
	name: "When Sensor Opens Turn On Lights",
	namespace: "breity55",
	author: "Alex Breitenstein",
	description: "When a sensor is opened between sunset and sunrise then turn on selected lights.",
	category: "Lights",
    iconUrl: "https://s3.amazonaws.com/smartapp-icons/Meta/light_presence-outlet.png",
    iconX2Url: "https://s3.amazonaws.com/smartapp-icons/Meta/light_presence-outlet@2x.png"
)

preferences {
	section("When Sensor Opens...") {
		input "sensorInput", "capability.contactSensor", title: "Which Sensor?", required: true
	}
	section("Turn On Lights...") {
		input "lightInputs", "capability.switch", title: "Which Light(s)?", required: true, multiple: true
	}
}

def installed() {
   	subscribeEvents()
}

def updated() {
	unsubscribe()
	subscribeEvents()
}

def subscribeEvents() {
	subscribe(sensorInput, "contactSensor.open", handleEvent)
}

def handleEvent(event) {
	def currentDate = new Date()
	def sunTimes = getSunriseAndSunset()
	def sunrise = sunTime.sunrise
	def sunset = sunTime.sunset

	log.debug "sunset: $sunset"
	log.debug "sunrise: $sunrise"
}