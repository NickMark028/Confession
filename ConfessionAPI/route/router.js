const userRouter = require('./user');
const confessionRouter = require('./confession');
const postRouter = require('./post');
class Router
{
    route(app)
    {
        app.get('/', (req,res)=>{
            res.send('Confession API'); // 
        })
        
        app.use('/user', userRouter); // 

        app.use('/confession', confessionRouter); //

        app.use('/post', postRouter);
    }
}

module.exports=new Router;