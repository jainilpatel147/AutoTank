var express= require('express');
var api = express.Router();
// var UserData = require("../../Model/model");
var fs = require('fs');

function log(req,res,next){
    var date = new Date();
    var log = "\nUrl:- "+ req.originalUrl + ", Time:- "+ date.getHours() +":"+date.getMinutes()+":"+ date.getSeconds();
    fs.appendFileSync("./logs/api_log.txt",log);
    next();
}

api.use(log);

//included user routes
var users = require("./user_routes");
api.use('/users',users);

//included campaign routes
var device = require("./device_routes");
api.use('/devices',device);

//include contributions routes
var Measurement = require("./measurement_routes");
api.use("/measurements",Measurement);

module.exports= api;