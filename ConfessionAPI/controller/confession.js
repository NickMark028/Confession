const mongoose = require('mongoose');
const Token = require('./token');
const Retriver = require('./retriver');
const Error=require('./error');
const tool=require('./tool');
const User = require("../model/Confession");
const Member = require('../model/Member');
const Role = require('../model/Role');
const Confession = require('../model/Confession');
const PreMember = require('../model/PreMember');
const { exists } = require('../model/Confession');

class UserController
{
    UserController()
    {

    }

    index(req, res) //
    {
        
        res.send('Confession API');
    }

    newConfession(req, res) //
    {
        let token = req.body.token;
        if(Token.isValid(token))
        {
            let admin = Token.parseToken(token);

            let memberdata = 
            {
                userid: admin._id,
                createAt: new Date(),
                updateAt: new Date(),
                isDelete: false 
            }

            let member = new Member(memberdata);
            member.save(err=>{
                if(!err)
                {
                    let memberid = member._id
                    let admindata=
                    {
                        memberid: memberid,
                        createAt: new Date(),
                        updateAt: new Date(),
                        isDelete: false 
                    }
                    let admin = new Role(admindata);
                    admin.save(err=>{
                        let adminid = admin._id;

                        let confessiondata={
                            shortname: req.body.shortname,
                            groupname: req.body.groupname, 
                            avatar: req.body.avatar,
                            posts: [],
                            members: [memberid],
                            admins: [adminid],
                            users: [],
                            topPin: false,
                            createAt: new Date(),
                            updateAt: new Date(),
                            isDelete: false,   
                        }

                        Confession.find({shortname: confessiondata.shortname},(err,items)=>{
                            if(items.length>0)
                            {   
                                let error=Error.report(403,req.originalUrl,'Confession duplicate');
                                res.json(error);
                            }
                            else
                            {
                                let confession = new Confession(confessiondata);
                                confession.save(err =>{
                                    res.json(confession);
                                })
                            }
                        })  
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

    joinRequest(req, res) //
    {
        let token = req.body.token;
        let confession = req.body.confession;
        if(Token.isValid(token))
        {
            Confession.find({_id:confession},(err,items)=>{
                if(!err)
                {
                    let conf_mem = items[0]['users'];
                    let user = Token.parseToken(token);
                    PreMember.find({userid:user._id},(err,items)=>{
                        let exists = false;
                        for(let i=0; i<items.length;i++)
                        {
                            let member_row = items[i];
                            let member_row_id = member_row._id;
                            //res.json(member_row_id);
                            exists = conf_mem.includes(member_row_id);
                        }
                        if(!exists)
                        {
                            let prememdata={
                                userid: user._id,
                                createAt: new Date(),
                                updateAt: new Date(),
                                isDelete: false
                            }
                            let premem = new PreMember(prememdata);
                            premem.save(err=>{
                                if(!err)
                                {
                                    Confession.findOneAndUpdate({_id: confession},{$push:{users: premem._id}},(err,data)=>{
                                        if(!err)
                                        {
                                            res.json(premem);
                                        }
                                    })
                                    
                                }
                            })
                        }
                        else
                        {
                            let error=Error.report(403,req.originalUrl,'already_request');
                            res.json(error);
                        }
                    })
                }
                else
                {
                    let error=Error.report(403,req.originalUrl,'invalid_confession_id');
                    res.json(error);
                }
            })
        }
        else
        {
            let error=Error.report(403,req.originalUrl,'invalid_token');
            res.json(error);
        }
    }

    addMember(req, res) //
    {
        let token = req.body.token;
        let confession = req.body.confession;
        let premem = req.body.premem;
        if(Token.isValid(token))
        {
            Confession.find({_id: confession}).populate(
                {
                    path: 'admins',
                    populate: {
                        path: 'memberid',
                        populate: {
                            path: 'userid'
                        }
                    }
                }
            ).populate(
                {
                    path: 'users',
                    populate: {
                        path: 'userid'
                    }
                }
            ).exec((err,items)=>{
                // Check Confession Exists //
                let userreq =  Token.parseToken(token);
                let isNotAdmin = true;
                let admins = items[0].admins;
                let premems = items[0].users; // waiting list
                for(let i=0;i<admins.length;i++)
                {
                    let userid = admins[i].memberid.userid._id;
                    if(userreq._id==userid)
                    {
                        isNotAdmin = false;
                        break;
                    }
                }

                if(!isNotAdmin)
                {
                    let inPreMemList = false;
                    let curpremem;
                    for(let i=0;i<premems.length;i++)
                    {
                        curpremem = premems[i];
                        if(curpremem._id == premem)
                        {
                            inPreMemList=true;
                            break;
                        }
                    }
                    
                    if(inPreMemList)
                    {
                        PreMember.findOneAndUpdate({_id:premem},{isDelete: true},(err,items)=>{
                            if(!err)
                            {
                                let member = {
                                    userid: curpremem.userid._id,
                                    createAt: new Date(),
                                    updateAt: new Date(),
                                    isDelete: false
                                }
                                let newmem = new Member(member);
                                newmem.save(err=>{
                                    Confession.findOneAndUpdate({_id:confession},{$push:{members: newmem._id}},(err,data)=>{
                                        if(!err) res.json(member);
                                    })
                                })
                            }
                        })
                    }
                    else
                    {
                        let error=Error.report(403,req.originalUrl,'is_not_waiting_member');
                        res.json(error);
                    }

                }
                else
                {
                    let error=Error.report(403,req.originalUrl,'is_not_admin');
                    res.json(error);
                }

            })
        }
        else
        {
            let error=Error.report(403,req.originalUrl,'invalid_token');
            res.json(error);
        }
    }

    rejectPremember(req,res) // Chua check admin //
    {
        let token = req.body.token; // admin token
        let groupid = req.body.groupid;
        let userid = req.body.userid;
        if(Token.isValid(token))
        {
            let info = Token.parseToken(token);
            Retriver.getOwnConfession(token).then(data=>{    
                for(let i=0;i<data.length;i++)
                {
                    if(data[i]._id==groupid)
                    {
                        //res.json(data[i]);
                        for(let j=0;j<data[i]['users'].length;j++)
                        {
                            if(data[i]['users'][j]['userid']!==null&&data[i]['users'][j]['userid']['_id']==userid)
                            {
                                //res.json(data[i]['users'][j]._id);
                                PreMember.findOneAndUpdate({_id:mongoose.Types.ObjectId(data[i]['users'][j]['_id'])},{isDelete:true},(err,data)=>{
                                    res.json({'rejectPremember':true});
                                })   
                            }
                        }
                    }
                }
                res.json({'rejectPremember':false});
            })
        }
        else
        {
            let error=Error.report(403,req.originalUrl,'invalid_token');
            res.json(error);
        }
    }

    getConfessionByID(req, res) //
    {
        let confession = req.query.conf;
        Confession.find(
            {_id: confession}
        ).populate(
            {
                path: 'posts',
                populate: {
                    path: 'memberid',
                    match: { isDelete: false },
                    populate:{
                        path: 'userid',
                        select: "-password -email -phone",
                        match: { isDelete: false }
                    },
                },
                match: { isDelete: false }
            }
        ).populate(
            {
                path: 'members',
                match: { isDelete: false },
                populate:{
                    path: 'userid',
                    select: "-password -email -phone",
                    match: { isDelete: false }
                }
            }
        ).populate(
            {
                path: 'admins',
                match: { isDelete: false },
                populate:{
                    path: 'memberid',
                    match: { isDelete: false },
                    populate:{
                        path:'userid',
                        select: "-password -email -phone",
                        match: { isDelete: false }
                    }
                }
            }
        ).populate(
            {
                path: 'users',
                match: { isDelete: false },
                populate:{
                    path:'userid',
                    select: "-password -email -phone",
                    match: { isDelete: false }
                }
            }
        ).populate(
            {
                path: 'posts',
                populate: {
                    path: 'comments',
                    select: '-postid',
                    match: { isDelete: false },
                    populate:{
                        path: 'memberid',
                        select: "-password -email -phone",
                        match: { isDelete: false },
                        populate:{
                            path:'userid',
                            select: "-password -email -phone",
                            match: { isDelete: false }
                        }
                    },
                },
                match: { isDelete: false }
            }
        )
        .exec((err,items)=>{
            if(!err)
            {
                res.json(items[0]);
            }
            else
            {
                let error=Error.report(403,req.originalUrl,'invalid_confession_id');
                res.json(error);
            }
        })
    }


    getAllConfession(req, res) //
    {
        //res.json(id);
        //res.send( Tool.isMember('60505ccec951fc27083de447','6059827d051ad535c0f70861'));
        
        let confession = req.query.conf;
        Confession.find().populate(
            {
                path: 'posts',
                populate: {
                    path: 'memberid',
                    match: { isDelete: false },
                    populate:{
                        path: 'userid',
                        select: "-password -email -phone",
                        match: { isDelete: false }
                    }
                },
                match: { isDelete: false }
            }
        ).populate(
            {
                path: 'members',
                match: { isDelete: false },
                populate:{
                    path: 'userid',
                    select: "-password -email -phone",
                    match: { isDelete: false }
                }
            }
        ).populate(
            {
                path: 'admins',
                match: { isDelete: false },
                populate:{
                    path: 'memberid',
                    match: { isDelete: false },
                    populate:{
                        path:'userid',
                        select: "-password -email -phone",
                        match: { isDelete: false }
                    }
                }
            }
        ).populate(
            {
                path: 'users',
                match: { isDelete: false },
                populate:{
                    path:'userid',
                    select: "-password -email -phone",
                    match: { isDelete: false }
                }
            }
        ).exec((err,items)=>{
            if(!err)
            {
                res.json(items);
            }
            else
            {
                let error=Error.report(403,req.originalUrl,'invalid_confession_id');
                res.json(error);
            }
        })
    }

    search(req,res) //
    {
        let keyword = req.query.keyword;
        Confession.find({shortname:{$regex: keyword, $options: "i"}},(err,data)=>{
            if(!err)
            {
                res.json(data);
            }
            else
            {
                let error=Error.report(403,req.originalUrl,'search_fail');
                res.json(error);
            }
        })
    }

    getPendingUsers(req,res) //
    {
        let token = req.query.token;
        let groupid = req.query.groupid;
        let infor = Token.parseToken(token);
        Retriver.getJoinedConfession(token).then(data=>{
            let users=[];
            let isAdmin = false;
            for(let con=0;con<data.length;con++)
            {
                if(groupid==data[con]['_id'])
                {
                    
                    for(let admin=0;admin<data[con]['admins'].length;admin++)
                    {
                        //console.log(data[con]['admins'][admin]['memberid']['userid']['_id']);
                        //console.log(infor._id);
                        if(data[con]['admins'][admin]['memberid']['userid']['_id']==infor._id)
                        {
                            isAdmin=true;
                            break;
                        }
                    }
                    console.log(data[con]['users']);
                    if(isAdmin) users=data[con]['users'];
                    break;
                }
            }
            res.json(users);
        })
    }

    getMembers(req,res) //
    {
        let token = req.query.token;
        let groupid = req.query.groupid;
        let infor = Token.parseToken(token);
        Retriver.getJoinedConfession(token).then(data=>{
            let members=[];
            let isAdmin = false;
            for(let con=0;con<data.length;con++)
            {
                if(groupid==data[con]['_id'])
                {
                    
                    for(let admin=0;admin<data[con]['admins'].length;admin++)
                    {
                        //console.log(data[con]['admins'][admin]['memberid']['userid']['_id']);
                        //console.log(infor._id);
                        if(data[con]['admins'][admin]['memberid']['userid']['_id']==infor._id)
                        {
                            isAdmin=true;
                            break;
                        }
                    }
                    if(isAdmin) members=data[con]['members'];
                    break;
                }
            }
            res.json(members);
        })
    }

    getAdmins(req,res)
    {
        let token = req.query.token;
        let groupid = req.query.groupid;
        let infor = Token.parseToken(token);
        Retriver.getJoinedConfession(token).then(data=>{
            let managers=[];
            let isAdmin = false;
            for(let con=0;con<data.length;con++)
            {
                if(groupid==data[con]['_id'])
                {
                    
                    for(let admin=0;admin<data[con]['admins'].length;admin++)
                    {
                        //console.log(data[con]['admins'][admin]['memberid']['userid']['_id']);
                        //console.log(infor._id);
                        if(data[con]['admins'][admin]['memberid']['userid']['_id']==infor._id)
                        {
                            isAdmin=true;
                            break;
                        }
                    }
                
                    if(isAdmin) managers=data[con]['admins'];
                    break;
                }
            }
            res.json(managers);
        })
    }
}
module.exports=new UserController;