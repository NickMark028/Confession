const mongoose=require('mongoose');
const url=require('../util/db');
mongoose.connect(url,{useNewUrlParser: true });
let schema=mongoose.Schema({
    //userid: String,
    userid: {type: mongoose.Schema.Types.ObjectId, ref:'User'},
    createAt: Date,
    updateAt: Date,
    isDelete: Boolean
})
module.exports=mongoose.model('Member',schema,'Member');