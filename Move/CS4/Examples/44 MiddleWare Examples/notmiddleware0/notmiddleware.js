var http = require("http");


function requestHandler(req,res) {
  console.log(req.method + " to " + req.url);

  if (req.url === "/") {
    res.end("Welcome to the home page");
  } else if (req.url === "/stuff") {
    res.end("Welcome to the stuff page");
  }
  else {
    res.end("Error! File not found");
  }
}


var server = http.createServer(requestHandler);
server.listen(3000);



