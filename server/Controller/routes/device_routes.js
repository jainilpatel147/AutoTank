var express= require('express');
var device = express.Router();
var fs = require('fs');
var DeviceFunc = require("../lib/DeviceFunc");
const cors = require('cors');
device.use(cors());
const bodyParser = require('body-parser');
device.use(bodyParser.json());
device.use(bodyParser.urlencoded({extended:true}));

function log(req,res,next){
    var date = new Date();
    var log = "\nUrl:- "+ req.originalUrl + ", Time:- "+ date.getHours() +":"+date.getMinutes()+":"+ date.getSeconds();
    fs.appendFileSync("./logs/api_log_device.txt",log);
    next();
}

device.use(log);
device
    .route("/me")
    .get(async function(req,res) {
        var result = await DeviceFunc.getDeviceAll();
        console.log(result);
        res.json(result);
    })
    .put((req,res)=>{
        const newUser = req.body;
        var result = DeviceFunc.updateUser();
        if(result)
            res.json({"result":true});
        else
            res.json({"result":false});
    })

//Register the new user
device.post('/register',async function(req,res){
    var devicedetails = req.body;
    var result = await DeviceFunc.createDevice(devicedetails.name,devicedetails.height,userid);
    if(result)
        res.json({"result":true});
    else
        res.json({"result":false});
    
});

device.post('/login',async function(req,res){
    const userReq = req.body;
    var result = await DeviceFunc.validateDevice(userReq.name,userReq.password);
    console.log(result);
    if(result)
        res.json({"result":true});
    else
        res.json({"result":false});

});
device.delete('/:id',async function(req,res){
    const userid= req.params.id;
    var result = await DeviceFunc.deleteUser(userid);
    if(result)
        res.json({"result":true});
    else
        res.json({"result":false});
})

module.exports= device;