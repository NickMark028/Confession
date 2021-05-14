const mongoose = require('mongoose');
const User = require("../model/Confession");
const Member = require('../model/Member');
const Confession = require('../model/Confession');

class Tool
{
    async isMember(groupid, userid) //
    { 
        let result;
        let group_members,user_members;

        group_members =await Confession.find({_id:mongoose.Types.ObjectId(groupid)}).select("members");
        
        user_members =await Member.find({userid:mongoose.Types.ObjectId(userid)});
       
        for(let i=0;i<user_members.length;i++)
        {
        
            if(group_members[0]['members'].includes(user_members[i]['_id']))
            {   
                result=user_members[i]['_id'];
                
                break;        
            }
        }

        return result;
    }
}
module.exports=new Tool();