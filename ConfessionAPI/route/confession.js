const express=require('express');
const router=express.Router();
const confessionController=require('../controller/confession'); //
router.get('/',confessionController.getAllConfession); //
router.get('/id',confessionController.getConfessionByID); //
router.get('/search',confessionController.search); //
router.post('/new',confessionController.newConfession); //
router.post('/join',confessionController.joinRequest); //
router.post('/addmember',confessionController.addMember); //
module.exports=router; 