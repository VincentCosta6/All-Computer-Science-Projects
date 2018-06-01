var http = require("http");
function requestHandler(req, res) {
	console.log("Request: "+req.url);

	if(req.url === "/")
	{
		res.end("Home page");
	}
	else if(req.url === "/about")
	{
		res.end("About page");
	}
	else if(req.url === "/uganda")
	{
		res.end("Hello my brudda");
	}
	else
	{
		res.end("404");
	}
}
var server = http.createServer(requestHandler);
server.listen(3000);