const express=require('express');
const router=express.Router();
const postController=require('../controller/post');
router.get('/',postController.index); //
router.post('/new',postController.new); //
router.post('/comment/new',postController.newComment);
router.post('/reaction/react',postController.react);
module.exports=router;