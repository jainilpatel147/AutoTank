var Model = require("../../Model/model");

async function createDevice(name,height,userid){
    try{
        console.log('hello from function');
        const user = await Model.Device.create({"name":name,"height":height,"user":userid});
        await user.save();
        if(user)
            return true;
        else
            return false;
    }
    catch(e){
        return false;
    }
}
async function validateDevice(name,userid){
    try{
        const checkUser = await Model.User.find({"name" : name ,"user":userid});
        if(checkUser != null){
            return true; 
        }
        else{
            return false;
        }
    }
    catch(e){
        return false;
    }
}
async function getDevice(name,userid){
    try{
        const checkUser = await Model.User.find({"name" : name , "user" : userid, "is_del": false});
        if(checkUser != null){
            return checkUser; 
        }
        else{
            return false;
        }
    }
    catch(e){
        return false;
    }
}
async function getDeviceAll(){
    try{
        const checkUser = await Model.Device.find();
        if(checkUser != null){
            return checkUser; 
        }
        else{
            return false;
        }
    }
    catch(e){
        return false;
    }
}
async function getDeviceByName(name){
    try{
        const checkUser = await Model.Device.find({"name": name});
        console.log(checkUser);
        if(checkUser.length>0){
            return checkUser; 
        }
        else{
            return false;
        }
    }
    catch(e){
        return false;
    }
}
async function getDeviceById(user){
    try{
        const checkUser = await Model.Device.find({"user": user});
        console.log(checkUser);
        if(checkUser.length>0){
            return checkUser; 
        }
        else{
            return false;
        }
    }
    catch(e){
        return false;
    }
}
async function deleteDevice(name){
    try{
        const user = await Model.Device.find({"name" : name});
        user[0].is_del = true;
        user[0].save();
        if(user != null){
            return true; 
        }
        else{
            return false;
        }
    }
    catch(e){
        return false;
    }
}
module.exports= {createDevice,getDeviceById,getDeviceByName,validateDevice,getDevice,getDeviceAll,deleteDevice};