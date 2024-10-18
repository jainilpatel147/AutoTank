var express= require('express');
var user = express.Router();
var userFunc = require("../lib/UserFunc");
var fs = require('fs');

const bodyParser = require('body-parser');
user.use(bodyParser.json());
user.use(bodyParser.urlencoded({extended:true}));

const cors = require('cors');
user.use(cors());

function log(req,res,next){
    var date = new Date();
    var log = "\nUrl:- "+ req.originalUrl + ", Time:- "+ date.getHours() +":"+date.getMinutes()+":"+ date.getSeconds();
    fs.appendFileSync("./logs/api_log_user.txt",log);
    next();
}

user.use(log);
user
    .route("/me")
    .get(async function(req,res) {
        var result = await userFunc.getUsersAll();
        console.log(result);
        res.json(result);
    })
    .put((req,res)=>{
        const newUser = req.body;
        var result = userFunc.updateUser();
        if(result)
            res.json({"result":true});
        else
            res.json({"result":false});
    })

//Register the new user
user.post('/register',async function(req,res){
    var userdetails = req.body;
    var result = await userFunc.createUser(userdetails.name,userdetails.email,userdetails.password);
    if(result)
        res.json({"result":true});
    else
        res.json({"result":false});
    
});

user.post('/login',async function(req,res){
    const userReq = req.body;
    var result = await userFunc.validateUser(userReq.name,userReq.password);
    console.log(result);
    if(result)
        res.json({"result":true});
    else
        res.json({"result":false});

});
user.delete('/:id',async function(req,res){
    const userid= req.params.id;
    var result = await userFunc.deleteUser(userid);
    if(result)
        res.json({"result":true});
    else
        res.json({"result":false});
})

module.exports= user;