function Input(Text)
{
	var rl = require('readline');
	var prompts = rl.createInterface(process.stdin, process.stdout);
	prompts.question(Text, function(response) {
		prompts.close();
		return response;
	});
}
module.exports = Input;