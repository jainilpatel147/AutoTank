var mongoose=require('mongoose');

const userSchema = new mongoose.Schema({
    name: {
        type: String,
        required: true,
        unique : true,
    },
    email: {
        type: String,
        required: true,
        unique: true,
        lowercase : true,
    },
    password: {
        type: String,
        required: true,
    },
    is_del:{
        type:Boolean,
        default : false,
    },
    createdAt :{
        type : Date,
        default : () => Date.now(),
        immutable : true,
    },
    updatedAt :{
        type : Date,
        default : () => Date.now(),
    }
    
}, {collection:'Users'});

const User = mongoose.model('User', userSchema);
module.exports = User;