var Model = require("../../Model/model");

async function createMes(waterlevel,deviceid){
    try{
        const user = await Model.Measurement.create({waterlevel:waterlevel,device:deviceid});
        await user.save();
        return true;
    }
    catch(e){
        return false;
    }
}
async function getMes(DeviceId){
    try{
        const checkMes = await Model.Measurement.find({"device" : DeviceId});
        if(checkMes != null){
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
async function getMesAll(){
    try{
        const checkUser = await Model.Measurement.find();
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
module.exports= {createMes,getMes,getMesAll};
// async function validateUser(name,email,password){
//     try{
//         const checkUser = await Model.User.findOne({"name" : name , "password" : password, "email": email});
//         if(checkUser != null){
//             return true; 
//         }
//         else{
//             return false;
//         }
//     }
//     catch(e){
//         return false;
//     }
// }
// async function updateUser(oldUserName,updateField,UpdatedValue){
//     try{
//         const user = await Model.User.where("name").equals(oldUserName)
//         user[0].updateField = UpdatedValue;
//         user[0].save();
//         return true;
//         // if(checkUser != null){
//         //     return true; 
//         // }
//         // else{
//         //     return false;
//         // }
//     }
//     catch(e){
//         return false;
//     }
// }
// async function deleteUser(name,password){
//     try{
//         const checkUser = await Model.User.findOne({"name" : name , "password" : password});
//         if(checkUser != null){
//             return true; 
//         }
//         else{
//             return false;
//         }
//     }
//     catch(e){
//         return false;
//     }
// }
