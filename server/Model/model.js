var mongoose=require('mongoose');
mongoose.connect('mongodb://localhost/AutoTank');
const User = require("./User");
const Device = require("./Device");
const Measurement = require("./Measurement");

module.exports= {Device,Measurement,User};