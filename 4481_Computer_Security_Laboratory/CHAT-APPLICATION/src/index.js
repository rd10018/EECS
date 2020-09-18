const path = require('path')
const http = require('http')
const express = require('express')
const session = require('express-session');
const socketio = require('socket.io')
const upload = require("express-fileupload");
const Filter = require('bad-words')
const { generateMessage, generateLocationMessage } = require('./utils/messages')
const { addUser, removeUser, getUser, getUsersInRoom , getUserAll , roomsAvailble} = require('./utils/users')
const MongoClient = require('mongodb').MongoClient;
const app = express()
const server = http.createServer(app)
const io = socketio(server)
const bodyparser = require('body-parser');
const port = process.env.PORT || 80
const publicDirectoryPath = path.join(__dirname, '../public')
app.use(bodyparser.urlencoded({extended: true}));
app.use(express.static(publicDirectoryPath))
app.use(upload())
app.use(session({ secret: 'ssshhhhh' }));
var sess;

MongoClient.connect('mongodb+srv://rd110018:RIShab95!@cluster0-ajjrr.mongodb.net/test?retryWrites=true&w=majority', (err, client)=> {
    // .. Start the server
    if (err)
    {
        return console.log(err);
    }

        console.log("the database connection is successful!!");
         db = client.db('helpdeskdb');
        

    app.post("/uploadFile", function(req,res)
    {
        if(req.files)
        {
            var file = req.files.fileToUpload;
            console.log(file);
            //var filename = req.files.filename.name;
            console.log(file);
            file.mv("./"+ file.name, function(err)
            {
                if(err)
                {
                    console.log(err);
                    res.send("error occurred");
                }
                else
                {
                    res.send("Done!!");
                }
            })
         }
    } )     
    
    app.post('/joinForm', (req,res) => {
        console.log("joinForm is called!!");
        sess = req.session;
        //console.log("this is the session for the user " + JSON.stringify(sess));
        console.log("this is the sessionID --> " + req.sessionID);
        console.log(req.body);
        if(req.body.category === "Client Login")
        {
            return res.redirect('/clientform.html');
        }
        if(req.body.category === "Helpdesk Dashboard")
        {
            return res.redirect('/helpdeskform.html')
        }
        if(req.body.category === "Admin Login")
        {
            return res.redirect('/adminform.html')
        }
       
    })



    app.post('/clientData', (req,res) => {
        console.log("clientform is called!!");
        console.log(req.body); 
        return res.redirect('/chat.html');
    })


    var foundAdmin = false;
    app.post('/adminLogin', (req,res) => {
        console.log("admin login page for credentials!!");
        console.log("These are the credentials requested by the admin --> " ,req.body);
        var cursor = db.collection('credentials').find();
        
        
        cursor.toArray(function(err, results) 
        {
            for(let i=0;i< results.length ; i++)
            {
               // console.log(" --- > " , results[i]);
                if(req.body.username === results[i].username && req.body.password === results[i].password && req.body.room === results[i].room)
                {
                    foundAdmin = true;
                    console.log("this is the value of the found now --> " , foundAdmin);
                    console.log("the admin is fully authenticated!!");
                    return res.redirect('/chat.html?username=' + `${req.body.username}&room=${req.body.room}` );                
                }
                
            }
        })
        // if(!foundAdmin)
        // {
        //     ;
        // }
        // else
        // {
        //     console.log("this is the value of the found asdasda --> " , foundAdmin);
        // }

    })



    app.post('/credentials', (req,res) => {
        console.log("helpdesk user awaits for credentials!!");
        console.log("These are the credentials requested by the user --> " ,req.body);
        var cursor = db.collection('credentials').find();
        var found = false;
        
        cursor.toArray(function(err, results) 
        {
            for(let i=0;i< results.length ; i++)
            {
                console.log(" --- > " , results[i]);
                if(req.body.username === results[i].username)
                { 
                    found = true;
                    console.log("this is a valid user");
                    return res.redirect('/helpdesk.html');                
                }
                
            }
        })
        // if(!found)
        // {
        //     return res.redirect('/unauthenticated.html');
        // }
    })
     
    

        
})
io.on('connection', (socket) => {
    console.log('New WebSocket connection')
    //socket.emit('message', "Welcome from the server!!");
    
    socket.emit('clientData', {
        users: getUserAll()
    });


    socket.on('join', (options, callback) => {
        const { error, user } = addUser({ id: socket.id, ...options })

        if (error) {
            return callback(error)
        }

        socket.join(user.room)

        
         socket.emit('message', generateMessage('', 'Welcome everyone!'))
        
        socket.broadcast.to(user.room).emit('message', generateMessage('Admin', `${user.username} has joined!`))
        io.to(user.room).emit('roomData', {
            room: user.room,
            users: getUsersInRoom(user.room)
        })

        callback()
    })


    socket.on('sendMessage', (message, callback) => {
        const user = getUser(socket.id)
        const filter = new Filter()

        if (filter.isProfane(message)) {
            return callback('Profanity is not allowed!')
        }

        io.to(user.room).emit('message', generateMessage(user.username, message))
        callback()
    })

    var roomObj = {"rooms" : roomsAvailble()}
    socket.emit('roomsAvailable', roomObj );



    socket.on('sendLocation', (coords, callback) => {
        const user = getUser(socket.id)
        io.to(user.room).emit('locationMessage', generateLocationMessage(user.username, `https://google.com/maps?q=${coords.latitude},${coords.longitude}`))
        callback()
    })

    socket.on('disconnect', () => {
        const user = removeUser(socket.id)

        if (user) {
            io.to(user.room).emit('message', generateMessage('Admin', `${user.username} has left!`))
            io.to(user.room).emit('roomData', {
                room: user.room,
                users: getUsersInRoom(user.room)
            })
        }
    })
})

server.listen(3000, () => {
    console.log(`Listening on localhost:3000`);})
