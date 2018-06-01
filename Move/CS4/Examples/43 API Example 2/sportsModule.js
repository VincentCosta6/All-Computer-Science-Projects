var MAX_PLAYERS = 10;
var Client = require('node-rest-client').Client;
var season = "2015-2016-regular";
var client = new Client();

var args = {
    port: '443',
    headers: { "User-Agent": "node " + process.version }
};
client.registerMethod("getActivePlayers", `https://www.mysportsfeeds.com/api/feed/pull/nhl/${season}/active_players.json`, "GET");

client.registerMethod("getPlayerStats", `https://api.mysportsfeeds.com/v1.2/pull/nhl/${season}/cumulative_player_stats.json`, "GET");

module.exports = function (username, password) {
    args.headers["Authorization"] = "Basic " + Buffer.from(username + ':' + password).toString('base64');
}

///////////////////////////////////////////////

module.exports["NBA"] = {
    getActivePlayers: function ( fn) {
    client.methods.getActivePlayers(args, function (data, response) {
            if (response.statusCode !== 200) return fn(response.statusCode);
        var obj = data.activeplayers.playerentry;
        obj.length = MAX_PLAYERS;
            fn(false, obj);
        });
    },
    getPlayerStats: function ( data,fn ) {
      var args = {
          port: '443',
          headers: { "User-Agent": "node " + process.version },
          player: data.playername
      };
      client.methods.getPlayerStats(args, function (data, response) {
        if (response.statusCode !== 200) return fn(response.statusCode);
        var obj = data.cumulativeplayerstats.playerstatsentry.stats;
        fn(false, obj);
      });
    }
}
