var createError = require('http-errors');
var express = require('express');
var path = require('path');
var cookieParser = require('cookie-parser');

var mongoose = require("mongoose");
var info = require("./models/info");
var users = require("./models/users");

var loginRouter = require('./routes/login');
var sessionRouter = require('./routes/session');
var passport = require("passport");
var LocalStrategy = require('passport-local').Strategy;
var app = express();
var bodyParser = require('body-parser');

mongoose.connect("mongodb://localhost/testdb");
const ObjectID = require('mongodb').ObjectID;
let db = mongoose.connection;

// view engine setup

app.use(bodyParser.json());
app.use(express.urlencoded({ extended: false }));
app.use(cookieParser());
app.use(express.static(path.join(__dirname, 'public')));
app.use(require('express-session')({ secret: 'keyboard cat', resave: true, saveUninitialized: true }));
app.use(passport.initialize());
app.use(passport.session());

passport.use(new LocalStrategy(
  function(username, password, done) {
    users.findOne({ username: username }, function (err, user) {
      if (err) { return done(err); }
      if (!user) { return done(null, false); }
      if (!user.password === password) { return done(null, false); }
      return done(null, user);
    });
  }
));
passport.serializeUser(function(user, done) {
  done(null, user.id);
});
passport.deserializeUser(function(id, done) {
  users.findById(id, function (err, user) {
    done(err, user);
  });
});

app.get("/myName", (req, res) => {
  res.json({name : req.user.username});
});
app.get("/", sessionDecider);
app.get("/login", sessionDecider);
app.get("/session", sessionDecider);
app.get("/signup", (req, res)=>{
  res.sendFile(__dirname + "/signup.html");
});
function sessionDecider(req, res)
{
  if(!req.user)
    res.sendFile(__dirname + "/login.html");
  else {
    res.sendFile(__dirname + "/session.html");
  }
}

app.post("/logout", (req, res) => {
  req.session.destroy(function (err) {
    res.redirect('http://localhost:3000/');
  });
});
app.post("/insert", (req, res) => {
  if(!req.user)
    res.redirect("http://localhost:3000/");
    info.findOne({ident:req.body.id}, (err, info) => {
      if(err) throw err;
      if(!info)
      {
        var object = {
          _id : new ObjectID(),
          ident: req.body.id,
          name : req.body.name
        };
        db.collection('info').insert(object);
        res.json({status:"Successful add"});
      }
      else {
        res.json({status:"item with Ident already exists"});
      }
    });

});


app.post('/login',
  passport.authenticate('local', { failureRedirect: '/login' }),
  function(req, res) {
    res.redirect('/session');
  }
);
app.post('/signup', (req, res) => {
  var newUser = {
    _id : new ObjectID(),
    username : req.body.username,
    password : req.body.password
  };
  db.collection('users').insert(newUser);
  res.redirect("http://localhost:3000/login");
});

/*app.post('/login', passport.authenticate('local', { successRedirect: '/',
                                                    failureRedirect: '/login' }));*/
app.listen(3000);
