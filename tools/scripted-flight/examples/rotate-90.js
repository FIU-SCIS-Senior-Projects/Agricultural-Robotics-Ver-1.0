var arDrone = require('..');
var http    = require('http');



//var pngStream = arDrone.createClient().getPngStream();
var client = arDrone.createClient();

client.config('general:navdata_demo', 'FALSE'); // Send all navdata
client.config('general:navdata_options', 777060865); // Turn on GPS

client.disableEmergency();

// Log all GPS data
client.on('navdata', function(navdata) {
  if(navdata.gps && navdata.gps.longitude && navdata.gps.latitude){
    console.log(navdata.gps.longitude + ', ' + navdata.gps.latitude);
  }
});

client.takeoff();

client
  .after(5000, function() {
    this.clockwise(0.5);
  })
  .after(5000, function() {
    this.stop();
    this.land();
  });
