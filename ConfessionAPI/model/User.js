const mongoose=require('mongoose');
const url=require('../util/db');
mongoose.connect(url,{useNewUrlParser: true });
let schema=mongoose.Schema({
    username: String,
    password: String,
    fullname: String,
    email: String,
    phone: String,
    createAt: Date,
    updateAt: Date,
    isDelete: Boolean
})
module.exports=mongoose.model('User',schema,'User');