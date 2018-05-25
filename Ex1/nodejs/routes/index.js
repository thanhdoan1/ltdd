var express = require('express');
var router = express.Router();
const haversine = require('haversine')

var googleMapsClient = require('@google/maps').createClient({
    key: 'AIzaSyC7ekvjIL1uPSebizIYWXJzGeKZ_nVLfIs'
});


router.get('/maps', function(req,res){
    googleMapsClient.reverseGeocode({
        latlng: [request.query.lat, request.query.long],
    }, function(err, response) {
        if (!err) {
            res.json((response.json.results[0].formatted_address));
            res.end()
        }else {
            console.log(err)
        }
    });
});

router.get('/latlng',function (req,res) {
    const start = {
        latitude: req.query.lat1,
        longitude: req.query.long1
    }

    const end = {
        latitude: req.query.lat2,
        longitude: req.query.long2
    }

    var a = haversine(start, end, {unit: 'km'});
    res.write(a.toString())
    res.end()
})


module.exports = router;
