const express=require('express');
const router=express.Router();
const userController=require('../controller/user');
router.get('/',userController.index); //
router.post('/register',userController.register); //

router.post('/login',userController.login);
router.get('/profile',userController.getProfile);
router.post('/profile',userController.updateProfile);

router.get('/joinedconf',userController.getJoinedConfession);
module.exports=router;