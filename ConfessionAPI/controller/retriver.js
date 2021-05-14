const jwt = require('jsonwebtoken');
const Error=require('./error');
const User = require("../model/User");
const Confession = require('../model/Confession');
const Token = require('./token');
const post = require('./post');
class Retriver
{
    getAllGroup()
    {        
        console.log('Retriver...');
        let mypromise = new Promise(function(resolve,reject){
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
                    resolve(items);
                }
                else
                {
                    let error=Error.report(403,req.originalUrl,'invalid_confession_id');
                    res.json(error);
                }
            })
        });
        return mypromise;
    }

    getJoinedConfession(token)
    {
        if(Token.isValid(token))
        {
            let infor = Token.parseToken(token);
            let mypromise=new Promise(function(resolve,reject){
                Confession.find().select("-topPin").
            populate(
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
                    populate: {
                        path:'comments reactions',
                        match: { isDelete: false },
                        populate:{
                            path:'memberid userid',
                            match: { isDelete: false },
                            populate:{
                                path:'userid',
                                select: "-password -email -phone",
                                match: { isDelete: false }
                            }
                        }
                    },
                    
                    match: { isDelete: false }
                }
            ).
            populate(
                {
                    path: 'members',
                    match: {isDelete: false, userid: {$ne: null}},
                    populate: {
                        path: 'userid',
                        match: {isDelete: false}
                    }
                }
            ).
            populate(
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
            ).
            populate(
                {
                    path: 'users',
                    match: { isDelete: false },
                    populate:{
                        path: 'userid',
                        select: "-password -email -phone",
                        match: { isDelete: false },
                    }
                }
            ).
            exec((err,items)=>{
                resolve(items);
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
                })
            })
            return mypromise;
        }
    }


    getOwnConfession(token)
    {
        if(Token.isValid(token))
        {
            let infor = Token.parseToken(token);
            let mypromise=new Promise(function(resolve,reject){
                Confession.find().select("-topPin").
            populate(
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
                    populate: {
                        path:'comments reactions',
                        match: { isDelete: false },
                        populate:{
                            path:'memberid userid',
                            match: { isDelete: false },
                            populate:{
                                path:'userid',
                                select: "-password -email -phone",
                                match: { isDelete: false }
                            }
                        }
                    },
                    
                    match: { isDelete: false }
                }
            ).
            populate(
                {
                    path: 'members',
                    match: {isDelete: false, userid: {$ne: null}},
                    populate: {
                        path: 'userid',
                        match: {isDelete: false}
                    }
                }
            ).
            populate(//
                {
                    path: 'users',
                    match: {isDelete: false, userid: {$ne: null}},
                    populate: {
                        path: 'userid',
                        match: {isDelete: false}
                    }
                }
            ).
            populate(
                {
                    path: 'admins',
                    match: { isDelete: false },
                    populate:{
                        path: 'memberid',
                        match: { isDelete: false },
                        populate:{
                            path:'userid',
                            select: "-password -email -phone",
                            match: {isDelete: false }
                        }
                    }
                }
            ).
            exec((err,items)=>{
                if(!err) resolve(items);
                else reject();
                })
            })
            return mypromise;
        }
    }

}
module.exports=new Retriver();