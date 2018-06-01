var express = require('express');
var router = express.Router();

/* GET users listing. */
router.get('/', function(req, res, next) {
  res.render('users', { title: 'Users', users : [
    {firstName : "Andrew", lastName : "Yates"},
    {firstName : "Vincent", lastName : "Costa"}
  ]});
});

module.exports = router;
