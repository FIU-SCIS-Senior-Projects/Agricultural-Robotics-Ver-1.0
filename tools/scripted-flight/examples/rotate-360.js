var arDrone = require('..');
var http    = require('http');



//var pngStream = arDrone.createClient().getPngStream();
var client = arDrone.createClient();

client.config('general:navdata_demo', 'FALSE'); // Send all navdata
//client.config('general:navdata_options', 777060865); // Turn on GPS

client.disableEmergency();

// Log all GPS data
client.on('navdata', function(navdata) {
  console.log(navdata);
});

/*
client.takeoff();
client.stop();
client

  .after(5000, function() {
    this.clockwise(1);
  })
  .after(5000, function() {
    this.land();
  });
*/
