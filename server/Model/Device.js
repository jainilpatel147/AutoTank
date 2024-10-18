var mongoose=require('mongoose');


const DeviceSchema = new mongoose.Schema({
    name: {
        type: String,
        required: true,
    },
    height:{
        type:Number,
        required: true,
    },
    user: {
        type: mongoose.Schema.Types.ObjectId,
        ref: 'User',
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
   
},{collection:'Devices'});

const Device = mongoose.model('Device', DeviceSchema);

module.exports = Device;