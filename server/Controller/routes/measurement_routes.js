var express= require('express');
var measurement = express.Router();
var MesFunc = require("../lib/MeasurementFunc");
var fs = require('fs');
const cors = require('cors');
measurement.use(cors());

const bodyParser = require('body-parser');
measurement.use(bodyParser.json());
measurement.use(bodyParser.urlencoded({extended:true}));

function log(req,res,next){
    var date = new Date();
    var log = "\nUrl:- "+ req.originalUrl + ", Time:- "+ date.getHours() +":"+date.getMinutes()+":"+ date.getSeconds();
    fs.appendFileSync("./logs/api_log_measurement.txt",log);
    next();
}

measurement.use(log);
measurement
    .route("/me")
    .get(async function(req,res) {
        var result = await MesFunc.getMesAll();
        console.log(result);
        res.json(result);
    })
measurement.post('/rec', function(req,res){
    var waterlevel = req.body;
    console.log(waterlevel);
})
measurement.get('/:devname',async function(req,res){
    const devid = req.params.devid;
    var result = await MesFunc.getMes(dev);
    if(result)
        res.json(result);
    else
        res.json({"result":false});
    
});
//Register the new user
measurement.post('/insert',async function(req,res){
    var devicedetails = req.body;
    var result = await MesFunc.createMes(devicedetails.waterlevel,devicedetails.deviceid);
    if(result)
        res.json({"result":true});
    else
        res.json({"result":false});
    
});


module.exports= measurement;
