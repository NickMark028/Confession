const mongoose=require('mongoose');
const url=require('../util/db');
mongoose.connect(url,{useNewUrlParser: true });
let schema=mongoose.Schema({
    shortname: String,
    groupname: String,
    avatar: String,
    posts: [{type: mongoose.Schema.Types.ObjectId, ref:'Post'}],
    members: [{type: mongoose.Schema.Types.ObjectId, ref:'Member'}],
    admins: [{type: mongoose.Schema.Types.ObjectId, ref:'Role'}],
    users: [{type: mongoose.Schema.Types.ObjectId, ref:'PreMember'}],
    topPin: false,
    createAt: Date,
    updateAt: Date,
    isDelete: Boolean
})
module.exports=mongoose.model('Confession',schema,'Confession');