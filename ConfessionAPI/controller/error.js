class Error
{
    report(code,path,message)
    {
        
        let error={
            code: code,
            path: path,
            error: message
        }
        
        return error;
    }
}
module.exports=new Error;