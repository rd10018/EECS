const port =54000; // put 8000 on workstation, 0 on red
const home = '/eecs/home/rd110018'; // put your username
//const DB_PATH =home + '/4413/pkg/sqlite/Models_R_US.db';
const DB_PATH = "C:\Users\risha\Desktop\angular\project1\Models_R_US.db"

const net = require('net');
const https = require('https');
const express = require('express');
//const cookieParser = require('cookie-parser');
const session = require('express-session');
//const io = require("socket.io")(tcp);
var app = express();
var sqlite3 = require('sqlite3').verbose();
//var db = new sqlite3.Database(DB_PATH);
var db = new sqlite3.Database('./Models_R_US.db');
app.enable('trust proxy');


app.use(function(req, res, next) {
  res.header("Access-Control-Allow-Origin", "*"); 
  next();
});

//app.use(cookieParser());
app.use(session(
  {
    secret: "mine",
    proxy: true,
    resave: true,
    saveUninitialized: true
  }));

app.use('/Test', function(req, res)
{
   res.writeHead(200, { 'Content-Type': 'text/html'});
   res.write("Hello ... this is EECS4413/Fall19 Tester! ");
   res.end("You sent me: " + req.query.x);
});

app.use("/list", (req, res) => 
{
  res.writeHead(200, { 'Content-Type': 'text/json'});
  let id = req.query.id;
  // sql injection vulnerability:
  //let query = "select id, name from product where catid = " + id;
  //db.all(query, [], (err, rows) =>
  // prepared statement:
  let query = "select id, name from product where catid = ?";
  db.all(query, [id], (err, rows) =>

  {
      if (err == null)
      {
          res.write(JSON.stringify(rows));
          res.end();
      }
      else
      {
          res.end("Error " + err);
      }
  });
 });


 //global cart variable for testing purposes
var session_cart = [];
//  app.use("/cart", (req,res) =>
//  {
//   console.log("it reached the cart"); 
//   res.writeHead(200, { 'Content-Type': 'text/plain'});
//   // client sends item as {"id":"S50_1514", "qty":"1", "price":58.58}
//   let id = req.query.item;
//   var repeat = false;
//   if (!req.session.cart)  // req.getSession().getAttribute("cart")
//   {
//       req.session.cart = [];
//   }
//   if (id )
//   {
//     if( req.session.cart.length > 0)
//    { 
//     var current_obj = JSON.parse(id);
//     for(var i= 0;i<req.session.cart.length;i++)
//     {
//       var obj = req.session.cart[i]; 
//       if((obj).id == current_obj.id)
//       {
//         obj.qty = obj.qty + current_obj.qty;
//         repeat  = true;
//       }
//     }
//     } 
//      if(!repeat)
//      {
//        req.session.cart.push(JSON.parse(id));
//      }
//   } 
//   // res.write("Session id = " + req.session.id);
//   //
//   // use forEach to remove items with qty=0
  
 


//   res.end(JSON.stringify(req.session.cart));
//  });  



app.use("/cart", (req,res) =>
 {
  console.log("it reached the cart"); 
  res.writeHead(200, { 'Content-Type': 'text/plain'});
  let id = req.query.item;
  var repeat = false;
  if (id )
  {
   
      if(session_cart.length > 0)
      { 
        var current_obj = JSON.parse(id);
        for(var i= 0;i<session_cart.length;i++)
        {
          var obj = session_cart[i]; 
          if((obj).id == current_obj.id)
          {
            obj.qty = obj.qty + current_obj.qty;
            obj.price = obj.price + (obj.price * current_obj.qty);
            repeat  = true;
          }
        }
      } 
      
     if(!repeat)
     {
       session_cart.push(JSON.parse(id));
     }
  } 
  // use forEach to remove items with qty=0
  res.end(JSON.stringify(session_cart));
 });  




 app.use("/updateCart", (req,res) =>
 {
  console.log("it reached the update cart"); 
  res.writeHead(200, { 'Content-Type': 'text/plain'});
  // client sends item as {"id":"S50_1514", "qty":"1", "price":58.58}
  let id = req.query.item;
  if (id )
  {

        var current_obj = JSON.parse(id);
        for(var i= 0;i<session_cart.length;i++)
        {
          var obj = session_cart[i]; 
          if((obj).id == current_obj.id)
          {
            obj.qty =  current_obj.qty;
            obj.price =  current_obj.price;
            
          }
        }
  } 

  res.end(JSON.stringify(session_cart));
 });  




 app.use("/deleteCart", (req,res) =>
 {
  console.log("this method invocation means deleting a entry from the cart"); 
  res.writeHead(200, { 'Content-Type': 'text/plain'});
  // client sends item as {"id":"S50_1514", "qty":"1", "price":58.58}
  let id = req.query.item;

  if (id )
  {
   
      
        var current_obj = JSON.parse(id);
        for(var i= 0;i<session_cart.length;i++)
        {
          var obj = session_cart[i]; 
          if((obj).id == current_obj.id)
          {
            session_cart.pop(obj);
          }
        }
       
      
     
  } 
  // use forEach to remove items with qty=0
  res.end(JSON.stringify(session_cart));
 });  


  

app.use('/Catalog', function(req, res)
{
  res.writeHead(200, { 'Content-Type': 'text/json'});
let query1 = "select * from category";

  db.all(query1, (err, rows) =>

  {
    if (err == null)
    {
      res.write(JSON.stringify(rows));
      res.end();
    }
    else
    {
      res.end("Error " + err);
    }
  });
}); 




app.use('/Category', function(req, res)
{
  res.writeHead(200, { 'Content-Type': 'text/json'});
let query1 = "select name from product where catId = (select id from category where name = ? )";

  db.all(query1, req.query.name, (err, rows) =>

  {
    if (err == null)
    {
      res.write(JSON.stringify(rows));
      res.end();
    }
    else
    {
      res.end("Error " + err);
    }
  });
}); 





app.use('/Product', function(req, res)
{
  res.writeHead(200, { 'Content-Type': 'text/json'});
let query1 = "select * from product where name = ? ; )";

  db.all(query1, req.query.name, (err, rows) =>

  {
    if (err == null)
    {
      res.write(JSON.stringify(rows));
      res.end();
    }
    else
    {
      res.end("Error " + err);
    }
  });
}); 


app.use('/GeoNode', function(req, res)
  {
     res.writeHead(200, { 'Content-Type': 'text/html'});
     const lat = req.query.lat;
     const lon = req.query.lon;
     if (!req.session.lat1 & !req.session.lon1) // req.getSession().getAttribute("coordinates")
     {
      req.session.lat1 = lat;
      req.session.lon1 = lon;
      res.end("RECEIVED");
      //res.write("session coordinates" + req.session.lat1 + req.session.lon1);

     }
     else
     {
       console.log("it entered the else part");
      //res.write(req.session.lat1  + "  "  + req.session.lon1 +  "  " + lat +  "  " + lon ); 	
       // var opt = {host:'130.63.96.250', port:34051};
        var client = new net.Socket();
        client.connect('33988','130.63.96.250', () => {
          console.log("Connected to the Geo Service ");
        });
        client.end(req.session.lat1 + " " + req.session.lon1 + " " + lat + " " +lon);
        //console.log("this step done!!");
        let s = " ";
        client.on('data', (message) => {
          s += message;
          res.end(s);
      });
         client.on('error', (err) => {res.end("Error " + err);});	
    }    	
 
  }); 

  app.use('/CatalogC', function(req, res)
  {
    let id = req.query.id;
	// sql injection vulnerability:
	//let query = "select id, name from product where catid = " + id;
	//db.all(query, [], (err, rows) =>
	// prepared statement:
  let query1 = "select id, name from category where id = ?";
  let query2 = "select * from category;";
  if(id)
  {
    db.all(query1, [id], (err, rows) =>

    {
      if (err == null)
      {
        res.write(JSON.stringify(rows));
        res.end();
      }
      else
      {
        res.end("Error " + err);
      }
    });
  }  
  else{
    db.all(query2, (err, rows) =>

    {
      if (err == null)
      {
        res.write(JSON.stringify(rows));
        res.end();
      }
      else
      {
        res.end("Error " + err);
      }
    });
  }
}	);


  app.use('/Trip', function(req, res)
  {
      res.writeHead(200, { 'Content-Type': 'text/html'});
      
      const start = req.query.start;
      const end = req.query.end;
      const url ="https://maps.googleapis.com/maps/api/distancematrix/json?origins=" + start + "&destinations="+end+"&key=AIzaSyDZ13d14cXk3VnljJtKLnMsJaut4JMElu0" + "&departure_time=now";
      
      https.get(url, (resp) =>
      {
      let data = '';
      resp.on('data', (x) => {data += x;} );
      resp.on('end', () => {
        var jsonObj =  JSON.parse(data);
      
      res.write(jsonObj.rows[0].elements[0].distance.text + "  ");
      res.write(jsonObj.rows[0].elements[0].duration.text); 
      res.end();
      })
      }).on("error", (err) => { res.end(err); });
      

    });



//this service stores cart per user in the backend
//var sessionShippingList = {};
var sessionCartList = [];
var sessionPresent = false;
var sessionList = [];
app.use('/sessionCart', function (req, res) {
  var sessionCartShipping = { "sessionId": "", "cart": Object , "shipping" : Object }; 
  res.writeHead(200, { 'Content-Type': 'text/json' });
  var s = req.query.sessionId;
  if(s === "")
  {
    s = req.sessionID;
  }
  var cart = req.query.cart;
  console.log("this is the cart that came with the request " , cart);
  var shipping = req.query.shipping;
  for (let i = 0; i < sessionList.length; i++) {
    if (sessionList[i]["sessionId"] === s) {
      sessionPresent = true;
     
      sessionList[i]["cart"] = cart;
      sessionList[i]["shipping"] = shipping;
    }
  }
  if (sessionPresent == false) {
    sessionCartShipping["sessionId"] = s;
    sessionCartShipping["cart"] = cart;
    sessionCartShipping["shipping"] = shipping;
    sessionList.push(sessionCartShipping);
  }
  sessionPresent = false;

  console.log("this is the complete list " + JSON.stringify(sessionList));
  res.end(s);

}); 
  

//this service prints all the session-ID and cart combinations to the front-end
app.use('/sessionCartPrint', function (req, res) {
  var s = req.query.sessionId;
  var jsonObj;
  res.writeHead(200, { 'Content-Type': 'text/json' });
  console.log(JSON.stringify(sessionList));

  // for (let i = 0; i < sessionList.length; i++) {
  //   if (sessionList[i]["sessionId"] === s) {
  //     jsonObj = sessionList[i];
  //     console.log("-----------------------------------------");
  //     console.log("this is the json obj " + JSON.stringify(jsonObj["cart"]));
  //   }
  // }
  res.end(JSON.stringify(sessionList));
});



app.use('/sessionPrint', function (req, res) {
  res.writeHead(200, { 'Content-Type': 'text/html' });
  let id = req.query.sessionId;
  if (!id)
    {
      id = req.sessionID;
    }
    else
    {
      console.log("this is the session id received from the frontend" , id);
    }
  //console.log(JSON.stringify(req.sessionID));

  res.end(JSON.stringify(id));
});





//this service gets a value from the user and adds it to the user's total

var idPresent = false;
var sessionScoreList = [];

app.use('/sessionScore', function (req, res) {
  var sessionScore = { "sessionId": "", "totalScore": 0 };
  let s = req.query.sessionId;
  if (s === "") {
    s = req.sessionID;
  }
  console.log("this is the session id" , s);      
  var score = req.query.score;
 
  for (let i = 0; i < sessionScoreList.length; i++) {
    //console.log("the length is at least 1");
    if (sessionScoreList[i]["sessionId"] === s) {
      idPresent = true;
      sessionScoreList[i]["totalScore"] += parseInt(score);
    } 
  }
  if (idPresent == false) {
    
    sessionScore["sessionId"] = s;
    sessionScore["totalScore"] = sessionScore["totalScore"] + parseInt(score);
    sessionScoreList.push(sessionScore);
    
  }
  idPresent = false;
  res.writeHead(200, { 'Content-Type': 'text/json' });
  console.log(JSON.stringify(sessionScoreList));
  
  // this loop is to get the specific session json of that specific user with the total score and return it to the user
  for (let i = 0; i < sessionScoreList.length; i++) {
    if (sessionScoreList[i]["sessionId"] === s) {
      sessionScore["sessionId"] = s;
      sessionScore["totalScore"] = sessionScoreList[i]["totalScore"];
      
    }
  }
  res.end(JSON.stringify(sessionScore)); 
});

app.use('/sessionScoreListPrint', function (req, res) {
  res.writeHead(200, { 'Content-Type': 'text/html' });
  
  res.end(JSON.stringify(sessionScoreList));
});





// --------------------------------------SERVER
var server = app.listen(port, function()
{
  var host = server.address().address;
  var port = server.address().port;
  console.log('Listening at http://%192.168.0.16:%d', port);
  //console.log('Listening at http://%s:%d', host, port);
});

