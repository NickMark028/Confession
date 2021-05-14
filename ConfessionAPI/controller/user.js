const mongoose = require('mongoose');
const Token = require('./token');
const Retriver = require('./retriver')
const User = require("../model/User");
const Error=require('./error');
const Confession = require('../model/Confession');
const { Mongoose } = require('mongoose');
const { populate } = require('../model/Confession');
const Member = require('../model/Member');

class UserController
{
    index(req,res)
    {
        res.send('User API');
    }

    register(req,res) // 
    {
        //console.log(req.body);
        let data={
            username: req.body.username,
            password: req.body.password, 
            fullname: req.body.fullname,
            email: req.body.email, 
            phone: req.body.phone,
            createAt: new Date(),
            updateAt: new Date(),
            isDelete: false
        }
       
        User.find({username:data.username,email:data.email},(err,items)=>{
            if(items.length>0)
            {   
                let error=Error.report(403,req.originalUrl,'User duplicate');
                res.json(error);
            }
            else
            {
                let user=new User(data);
                user.save(err=>{
                    if(err) console.log(err);
                    else
                    {
                        res.send(JSON.stringify(user));
                    }
                })
            }
        })
    }

    login(req,res) //
    {
        //console.log(req.body);
        let data={
            username: req.body.username,
            password: req.body.password
        }
        console.log(data.username);
        console.log(data.password);
       
        User.find({username:data.username,password:data.password},(err,items)=>{
            if(err) 
            {
                let error=Error.report(403,req.originalUrl,'unknown_error');
                res.json(error);  
            }
            if(items.length==0)
            {   
                let error=Error.report(403,req.originalUrl,'login_unsuccessfully');
                res.json(error);
            }
            else
            {
                let item = JSON.stringify(items[0]);
                item = JSON.parse(item);
                let token=Token.createToken(item,60*60*24)// expire in 1 hour
                item['token']=token;
                res.send(JSON.stringify(item));       
            }
        })
    }

    isLoginSession(req,res) // 
    {
        let obj;
        let token = req.query.token;
        if(Token.isValid(token))
        {
            let info = Token.parseToken(token);
            res.json(info);
            /*obj['_id'] = info['_id'];
            obj['fullname'] = info['fullname'];
            obj['token'] = token;
            obj['email'] = info['email'];
            obj['phone']=info['phone'];
            res.json(obj);*/
        }
        else
        {
            let error=Error.report(403,req.originalUrl,'invalid_token');
            res.json(error);
        }
    }

    isLogin(token) // 
    {   
        if(Token.isValid(token))
        {
           return true;
        }
        else
        {
            return false;
        }
    }

    getProfile(req,res) //
    {
        let token = req.query.token;
        if(Token.isValid(token))
        {
            let user = Token.parseToken(token);
            User.find({username:user.username},(err,items)=>{
                if(items.length==0)
                {   
                    let error=Error.report(403,req.originalUrl,'profile_not_found');
                    res.json(error);
                }
                else
                {
                    let item = JSON.stringify(items[0]);
                    res.send(item);     
                }
            })
        }
        else
        {
            let error=Error.report(403,req.originalUrl,'invalid_token');
            res.json(error);
        }
    }

    updateProfile(req,res) //
    {
        let token = req.body.token;
        if(Token.isValid(token))
        {
            let user = Token.parseToken(token);
            
            let newinfor = {
                fullname: req.body.fullname==''?'Anonymous':req.body.fullname,
                password: req.body.password==''?user.password:req.body.password
            }

            User.findOneAndUpdate({username: user.username},newinfor,(err,data)=>{
                if(err)
                {
                    let error=Error.report(403,req.originalUrl,'update_profile_fail');
                    res.json(error);
                }
                else
                {
                    User.find({username: user.username},(err,data)=>{
                        
                        res.json(data[0]);
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

    updateProfile3(req,res) //
    {
        let token = req.body.token;
        if(Token.isValid(token))
        {
            let user = Token.parseToken(token);
            
            let newinfor = {
                fullname: req.body.fullname==''?'Anonymous':req.body.fullname,
                email: req.body.email==''?user.email:req.body.email,
                phone: req.body.phone==''?user.phone:req.body.phone
            }

            User.findOneAndUpdate({username: user.username},newinfor,(err,data)=>{
                if(err)
                {
                    let error=Error.report(403,req.originalUrl,'update_profile_fail');
                    res.json(error);
                }
                else
                {
                    User.find({username: user.username},(err,data)=>{
                        
                        res.json(data[0]);
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

    getJoinedConfession(req, res) // 
    {
        let token = req.query.token;
        if(Token.isValid(token))
        {
            let infor = Token.parseToken(token);
            console.log(infor._id);
            Confession.find().select("-users -topPin -posts").populate(
                {
                    path: 'members',
                    match: {isDelete: false, userid: {$ne: null}},
                    populate: {
                        path: 'userid',
                        match: {_id: mongoose.Types.ObjectId(infor._id), isDelete: false}
                    }
                }
            ).exec((err,items)=>{
                let result=[];
                for(let i=0;i<items.length;i++)
                {
                    let members=[];
                    for(let j=0;j<items[i].members.length;j++)
                    {
                        if(items[i].members[j].userid!=null)
                        {
                            members.push(items[i].members[j]);
                        }
                    }
                    
                    if(members.length>0)
                    {
                        items[i].members=members;
                        result.push(items[i]);
                    }
                    
                }
                res.json(result);
            })
        }
    }

    getJoinedConfessionRole(req, res)
    {
        let token = req.query.token;
        if(Token.isValid(token))
        {
            let infor = Token.parseToken(token);
            console.log(infor._id);
            Confession.find().select("-users -topPin -posts").populate(
                {
                    path: 'admins',
                    match: {isDelete: false},
                    populate: {
                        path: 'memberid',
                        populate:{
                            path:'userid',
                            match: {_id: mongoose.Types.ObjectId(infor._id), isDelete: false},
                        }
                    }
                }
            ).exec((err,items)=>{
                let result=[];
                for(let i=0;i<items.length;i++)
                {
                    let admins=[];
                    for(let j=0;j<items[i].admins.length;j++)
                    {
                        if(items[i].admins[j].memberid.userid!=null)
                        {
                            admins.push(items[i].admins[j]);
                        }
                    }
                    
                    if(admins.length>0)
                    {
                        items[i].admins=admins;
                        result.push(items[i]);
                    }
                    
                }
                res.json(result);
            })
        }
        else
        {
            let error=Error.report(403,req.originalUrl,'invalid_token');
            res.json(error);
        }
    }

    getJoinedConfessionNoRole(req, res)
    {
        let token = req.query.token;
        if(Token.isValid(token))
        {
            let infor = Token.parseToken(token);
            console.log(infor._id);
            Confession.find().select("-users -topPin -posts").
            populate(
                {
                    path: 'members',
                    match: {isDelete: false, userid: {$ne: null}},
                    populate: {
                        path: 'userid',
                        match: {_id: mongoose.Types.ObjectId(infor._id), isDelete: false}
                    }
                }
            ).
            populate(
                {
                    path: 'admins',
                    match: {isDelete: false},
                    populate: {
                        path: 'memberid',
                        populate:{
                            path:'userid'
                        }
                    }
                }
            ).exec((err,items)=>{
                //res.json(items);
                let result=[];
                for(let i=0;i<items.length;i++)
                {
                    let normalmem=[];
                    let isValid=true;
                    for(let j=0;j<items[i].admins.length;j++)
                    {
                        if(items[i].admins[j].memberid.userid._id==infor._id)
                        {
                            isValid=false;
                            break;
                        }
                    }
                    //console.log(isValid);
                    
                    if(isValid)
                    for(let j=0;j<items[i].members.length;j++)
                    {
                        isValid = false;
                        if(items[i].members[j].userid!=null)
                        if(items[i].members[j].userid._id==infor._id)
                        {
                            isValid=true;
                            break;
                        }
                    }
                    if(isValid) result.push(items[i]);
                }
                res.json(result);
            })
        }
        else
        {
            let error=Error.report(403,req.originalUrl,'invalid_token');
            res.json(error);
        }
    }

    getNewsFeed(req,res)
    {
        let token=req.query.token;
        if(Token.isValid(token))
        {
            let info = Token.parseToken(token);
            Retriver.getJoinedConfession(token).then(data=>{
                
                let groups_posts=[];
                
                for(let i=0;i<data.length;i++)
                {
                    let curmemid;
                    let ismember = false;
                    let members = data[i]['members'];
                    for(let j=0;j<members.length;j++)
                    {
                        if(members[j]['userid']['_id']==info._id)
                        {
                            curmemid = members[j]['_id'];
                            //console.log(curmemid);
                            ismember=true;
                            break;
                        }
                    }

                    if(ismember)
                    {
                        let posts=[];
                        
                        posts = JSON.parse(JSON.stringify(data[i]['posts']));
                        
                        for(let j=0;j<posts.length;j++)
                        {
                            let isreact=false;
                            //console.log(posts[j]['memberid']);
                            posts[j]['groupid'] = data[i]._id;
                            posts[j]['shortname'] = data[i]['shortname'];
                            posts[j]['groupname'] = data[i]['groupname'];
                            posts[j]['avatar'] = data[i]['avatar'];
                            posts[j]['createAt'] = Date.parse(posts[j]['createAt']); // Moi them, fix..... 12/5/21 19h

                            for(let r=0;r<posts[j]['reactions'].length;r++)
                            {
                                if(posts[j]['reactions'][r]['userid']['_id']==info._id)
                                {
                                    isreact=true;
                                    break;
                                }
                            }
                            posts[j]['isreact'] = isreact;
                            //console.log(isreact);
                        }
                        
                        groups_posts=groups_posts.concat(posts);
                    }
                }
                
                groups_posts.sort((a,b)=>{
                   //return -Date.parse(a['createAt'])+Date.parse(b['createAt']);
                   return -a['createAt']+b['createAt']; // Moi them, fix..... 12/5/21 19h
                })
                res.json(groups_posts);
            })
        }
        else
        {
            let error=Error.report(403,req.originalUrl,'invalid_token');
            res.json(error);
        }
    }

    getOwnComments(req,res)
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
                            for(let k=0;k<posts[j]['comments'].length;k++)
                            {
                                if(posts[j]['comments'][k]['memberid']['userid']['_id']==info._id)
                                {
                                    comments.push(posts[j]['comments'][k]);
                                }
                            }
                            
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


    leaveConfession(req,res)
    {
        let token = req.query.token;
        let groupid = req.query.groupid;
        if(Token.isValid(token))
        {
            let info = Token.parseToken(token);
            Retriver.getJoinedConfession(token).then(data=>{    
                for(let i=0;i<data.length;i++)
                {
                    if(data[i]._id==groupid)
                    {
                        for(let j=0;j<data[i]['members'].length;j++)
                        {
                            if(data[i]['members'][j]['userid']!==null&&data[i]['members'][j]['userid']._id==info._id)
                            {
                                let memberid = data[i]['members'][j]._id;
                                Member.findOneAndUpdate({_id:mongoose.Types.ObjectId(memberid)},{isDelete:true},(err,data)=>{
                                    if(!err) res.json(data);
                                })
                            }
                        }
                    }
                }
            })
        }
        else
        {
            let error=Error.report(403,req.originalUrl,'invalid_token');
            res.json(error);
        }
    }

    isAdmin(req,res)
    {
        let token = req.query.token;
        let groupid = req.query.groupid;
        if(Token.isValid(token))
        {
            let info = Token.parseToken(token);
            Retriver.getOwnConfession(token).then(data=>{    
                //res.json(data);
                for(let i=0;i<data.length;i++)
                {
                    if(data[i]._id==groupid)
                    {
                        for(let j=0;j<data[i]['admins'].length;j++)
                        {
                            if(data[i]['admins'][j]['memberid']['userid']!==null&&data[i]['admins'][j]['memberid']['userid']._id==info._id)
                            {
                                let adminid = data[i]['admins'][j]._id;
                                res.json({'isAdmin':true});
                            }
                        }
                    }
                }
                res.json({'isAdmin':false});
            })
        }
        else
        {
            let error=Error.report(403,req.originalUrl,'invalid_token');
            res.json(error);
        }
    }

    getStates(req, res)
    {
        let token = req.query.token;
        let groupid = req.query.groupid;
        if(Token.isValid(token))
        {
            let isAdmin = false;
            let isMember = false;
            let isPremem = false;
        
            let info = Token.parseToken(token);
            Retriver.getOwnConfession(token).then(data=>{    // get joined confession
                //res.json(data);
                for(let i=0;i<data.length;i++)
                {
                    if(data[i]._id==groupid)
                    {
                        for(let j=0;j<data[i]['admins'].length;j++)
                        {
                            if(data[i]['admins'][j]['memberid']['userid']!==null&&data[i]['admins'][j]['memberid']['userid']._id==info._id)
                            {
                                let adminid = data[i]['admins'][j]._id;
                                isAdmin = true;
                                isMember = true;
                            }
                        }

                        for(let j=0;j<data[i]['members'].length;j++)
                        {
                            if(data[i]['members'][j]['userid']!==null&&data[i]['members'][j]['userid']._id==info._id)
                            {
                                isMember = true;
                            }
                        }

                        for(let j=0;j<data[i]['users'].length;j++)
                        {
                            if(data[i]['users'][j]['userid']!==null&&data[i]['users'][j]['userid']._id==info._id)
                            {
                                isPremem = true;
                            }
                        }
                    }
                }
                res.json({'isAdmin':isAdmin,'isMember':isMember,'isPending':isPremem});
            })
        }
        else
        {
            let error=Error.report(403,req.originalUrl,'invalid_token');
            res.json(error);
        }
    }

    getMemberId(req,res)
    {
        let token = req.query.token;
        let groupid = req.query.groupid;
        console.log(groupid);
        console.log(token);
        if(Token.isValid(token))
        {
            let info = Token.parseToken(token);
            Retriver.getJoinedConfession(token).then(data=>{    
                
                for(let i=0;i<data.length;i++)
                {
                    if(data[i]._id==groupid)
                    {
                        
                        for(let j=0;j<data[i]['members'].length;j++)
                        {
                            if(data[i]['members'][j]['userid']!==null&&data[i]['members'][j]['userid']._id==info._id)
                            {
                                
                                //let adminid = data[i]['admins'][j]._id;
                                //res.json({'status':"Chay vao day roi"});
                                res.json({'memberid':data[i]['members'][j]._id});
                            }
                        }
                    }
                }
                res.json({'isAdmin':false});
            })
        }
        else
        {
            let error=Error.report(403,req.originalUrl,'invalid_token');
            res.json(error);
        }
    }

    getPreMemberId(req,res)
    {
        let userid = req.query.userid;
        let groupid = req.query.groupid;
        let token = req.query.token;
        Retriver.getJoinedConfession(token).then(data=>{    
            //res.json(data);
            for(let i=0;i<data.length;i++)
            {
                if(data[i]._id==groupid)
                {
                    for(let j=0;j<data[i]['users'].length;j++)
                    {
                        if(data[i]['users'][j]['userid']!==null&&data[i]['users'][j]['userid']._id==userid)
                        {
                            res.json({'prememid':data[i]['users'][j]._id});
                        }
                    }
                }
            }
            let error=Error.report(403,req.originalUrl,'unknown_error');
            res.json(error);
        })
    }

}
module.exports=new UserController;