var mongoose=require('mongoose');

const measurementSchema = new mongoose.Schema({
    waterlevel: {
        type:Number,
        default:0,
    },
    time: {
        type : Date,
        default : () => Date.now(),
    },
    device: {
        type: mongoose.Schema.Types.ObjectId,
        ref: 'Device',
        required: true,
    },
    // UID:{
    //     type:String,
    //     required:true,
    // }
},{collection:'measurements'});

const measurement = mongoose.model('measurement', measurementSchema);

module.exports = measurement;