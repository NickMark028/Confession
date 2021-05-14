const Post = require("../model/Post");
const User = require("../model/Confession");
const Member = require('../model/Member');
const Role = require('../model/Role');
const Confession = require('../model/Confession');
const Comment = require('../model/Comment');
const Token = require('./token');
const Retriver = require('./retriver')
const Error=require('./error');
const { post, use } = require("../route/user");
const mongoose = require("mongoose");
const tool=require("./tool");


class PostController
{
    index(req,res)
    {
        res.send('Post API');
    }

    new(req, res)
    {
        let token = req.body.token;
        if(Token.isValid(token))
        {
            let infor = Token.parseToken(token);
            let userid = infor._id; //
            let groupid = req.body.confessionid; //
            let memberid = req.body.memberid;
            console.log(userid);
            console.log(memberid);
            console.log(groupid);
            console.log(req.body.title);
            console.log(req.body.content);
            //tool.isMember(groupid,userid).then(memberid=>
            //{
                let postdata=
                {
                    title: req.body.title,
                    content: req.body.content,
                    memberid: memberid,
                    isArrove: false,
                    approveAt: new Date(),
                    createAt: new Date(),
                    updateAt: new Date(),
                    isDelete: false,
                    comments: [],
                    reactions:[]
                }
                let post = new Post(postdata);
                post.save(err=>{
                    Confession.find({_id: mongoose.Types.ObjectId(req.body.confessionid)},(err,items)=>{
                        let posts = items[0].posts;
                        posts.push(post._id);
                        Confession.findOneAndUpdate({shortname: items[0].shortname},{posts: posts},(err,itemss)=>{
                            res.json(post);
                        })
                    })
                })
            //})            

        }
        else
        {
            let error=Error.report(403,req.originalUrl,'invalid_token');
            res.json(error);
        }
    }   
    
    
    newComment(req,res)
    {
        let token = req.body.token;
        if(Token.isValid(token))
        {
            let postid = req.body.postid;
            let memberid = req.body.memberid;
            let content = req.body.content;
            
            let commentData = 
            {
                postid: mongoose.Types.ObjectId(postid),
                memberid: mongoose.Types.ObjectId(memberid),
                content: content,
                createAt: new Date(),
                updateAt: new Date(),
                isDelete: false
            };
            let comment = new Comment(commentData);
            comment.save(err=>{
                if(!err)
                {
                    Post.findOneAndUpdate({_id:commentData.postid},{$push:{comments: comment._id}},(err,data)=>{
                        if(!err)
                        {
                            res.json(comment);
                        }        
                    })
                }
            })
        }   
        else
        {
            let error=Error.report(403,req.originalUrl,'invalid_token');
            res.json(error);
        }
    }

    react(req,res)
    {
        let token = req.body.token;
        if(Token.isValid(token))
        {
            let postid = req.body.postid;
            let memberid = req.body.memberid;
            Post.findOneAndUpdate({_id:mongoose.Types.ObjectId(postid)},{$push:{reactions:memberid}},(err,data)=>{
                if(!err)
                {
                    res.json(data);
                }
            })
        }
        else
        {
            let error=Error.report(403,req.originalUrl,'invalid_token');
            res.json(error);
        }
    }


    getComments(req,res)
    {
        let token=req.query.token;
        let postid = req.query.postid;
    
        if(Token.isValid(token))
        {
            let info = Token.parseToken(token);
            Retriver.getJoinedConfession(token).then(data=>{
                let comments=[];
                for(let i=0;i<data.length;i++)
                {
                    let posts=[];
                    posts = data[i]['posts'];
                    for(let j=0;j<posts.length;j++)
                    {
                        if(posts[j]['_id']==postid)
                        {
                            comments = posts[j]['comments'];
                            break;
                        }
                    }   
                }
                res.json(comments);
            })
        }
        else
        {
            let error=Error.report(403,req.originalUrl,'invalid_token');
            res.json(error);
        }
    }

    getReactions(req,res)
    {
        let token=req.query.token;
        let postid = req.query.postid;
    
        if(Token.isValid(token))
        {
            let info = Token.parseToken(token);
            Retriver.getJoinedConfession(token).then(data=>{
                let reactions=[];
                for(let i=0;i<data.length;i++)
                {
                    let posts=[];
                    posts = data[i]['posts'];
                    for(let j=0;j<posts.length;j++)
                    {
                        if(posts[j]['_id']==postid)
                        {
                            reactions = posts[j]['reactions'];
                            break;
                        }
                    }   
                }
                res.json(reactions);
            })
        }
        else
        {
            let error=Error.report(403,req.originalUrl,'invalid_token');
            res.json(error);
        }
    }


    removePost(req,res)
    {
        let token = req.body.token;
        let postid = req.body.postid;
        if(Token.isValid(token))
        {
            let info = Token.parseToken(token);
            /*Retriver.getJoinedConfession(token).then(data=>{
                res.json(data);
                for(let i=0;i<data.length;i++)
                {
                    posts.concat(data[i]['posts']);
                }
                Retriver.getOwnConfession(token).then(data=>{
                    for(let i=0;i<data.length;i++)
                    {
                        posts.concat(data[i]['posts']);
                    }
                    res.json(posts);
                })
            })*/
        }
        else
        {
            let error=Error.report(403,req.originalUrl,'invalid_token');
            res.json(error);
        }
    }

    isReact(req, res)
    {
        let token=req.query.token;
        let memberid = req.query.memberid;
        let postid = req.query.postid;
        if(Token.isValid(token))
        {
            let info = Token.parseToken(token);
            Retriver.getJoinedConfession(token).then(data=>{
                let reactions=[];
                for(let i=0;i<data.length;i++)
                {
                    let posts=[];
                    posts = data[i]['posts'];
                    for(let j=0;j<posts.length;j++)
                    {
                        if(posts[j]['_id']==postid)
                        {
                            reactionslen = posts[j]['reactions'].length;
                            for(let r=0;r<reactionslen;r++)
                            {
                                if(posts[j]['reactions'][r]==memberid)
                                {
                                    res.json({'isreact':true});
                                    break;
                                }
                            }
                            //reactions = posts[j]['reactions'];
                           
                        }
                    }   
                }
                res.json({'isreact':false});
            })
        }
        else
        {
            let error=Error.report(403,req.originalUrl,'invalid_token');
            res.json(error);
        }
    }

}

module.exports=new PostController;