var express = require('express');
var routes = require('./routes');

var app = express();
app.use('/', express.static('./'));
app.use('/js', express.static('./public/js/'));
app.use(routes);

app.listen(3000);