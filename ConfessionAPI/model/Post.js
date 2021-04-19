const mongoose=require('mongoose');
const url=require('../util/db');
mongoose.connect(url,{useNewUrlParser: true });
let schema=mongoose.Schema({
    title: String,
    content: String,
    //memberid: String,
    memberid: {type: mongoose.Schema.Types.ObjectId, ref:'Member'},
    isArrove: Boolean,
    approveAt: Date,
    createAt: Date,
    updateAt: Date,
    isDelete: Boolean,
    comments: [{type: mongoose.Schema.Types.ObjectId, ref:'Comment'}],
    reactions: [{type: mongoose.Schema.Types.ObjectId, ref:'Member'}]
})
module.exports=mongoose.model('Post',schema,'Post');