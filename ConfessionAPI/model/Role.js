const mongoose=require('mongoose');
const url=require('../util/db');
mongoose.connect(url,{useNewUrlParser: true });
let schema=mongoose.Schema({
    //memberid: String,
    memberid: {type: mongoose.Schema.Types.ObjectId, ref:'Member'},
    createAt: Date,
    updateAt: Date,
    isDelete: Boolean
})
module.exports=mongoose.model('Role',schema,'Role');