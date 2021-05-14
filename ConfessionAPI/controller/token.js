const jwt = require('jsonwebtoken');
const key=require('../util/hash');
class TokenController
{
    createToken(object, expireTime) //
    {
        let token=jwt.sign(object, key, { expiresIn: expireTime });
        return token;
    }

    parseToken(token) //
    {
        if(this.isValid(token))
        {
            let content = jwt.verify(token, key);
            return content;
        }
    }

    isValid(token)
    {
        try 
        {
            jwt.verify(token, key);
            return true;
        } catch(err)
        {
            return false;
        }
    }
}
module.exports=new TokenController();