const mongoose=require('mongoose');
const url=require('../util/db');
mongoose.connect(url,{useNewUrlParser: true });
let schema=mongoose.Schema({
    postid: {type: mongoose.Schema.Types.ObjectId, ref:'Post'},
    memberid: {type: mongoose.Schema.Types.ObjectId, ref:'Member'},
    content: String,
    createAt: Date,
    updateAt: Date,
    isDelete: Boolean
})
module.exports=mongoose.model('Comment',schema,'Comment');