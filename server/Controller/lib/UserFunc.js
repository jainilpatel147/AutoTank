var Model = require("../../Model/model");

async function createUser(name,email,password){
    try{
        const user = await Model.User.create({"name":name,"email":email,"password":password});
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
async function validateUser(name,password){
    try{
        const checkUser = await Model.User.find({"name" : name , "password" : password});
        console.log(checkUser);
        if(checkUser.length > 0){
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
async function updateUser(name,updateField,UpdatedValue){
    try{
        const user = await Model.User.where("name").equals(name)
        user[0].updateField = UpdatedValue;
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
async function getUser(name,password){
    try{
        const checkUser = await Model.User.findOne({"name" : name , "password" : password});
        if(checkUser.length>0){
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
async function getUsersAll(){
    try{
        const checkUser = await Model.User.find();
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
async function deleteUser(name,password){
    try{
        const checkUser = await Model.User.findOne({"name" : name , "password" : password});
        if(checkUser.length>0){
            checkUser[0].is_del = true;
            checkUser[0].save();
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
module.exports= {createUser,validateUser,updateUser,getUser,getUsersAll,deleteUser};