const port=3000|process.env.port;
const express=require('express');
const app=express();
const bodyParser = require('body-parser');
var jwt = require('jsonwebtoken');
const router=require('./route/router');

app.use(bodyParser.urlencoded({ extended: true }));
router.route(app);

app.listen(port,(req,res)=>{
    console.log('Listen in port 3000')
});