var fs = require('fs');
var Promise = require("bluebird");
var url = require('url');
var express = require('express');
var app = express();
var bodyParser = require('body-parser');
var _ = require('lodash');

var defaultCharset = 'utf8';
var readFile  = Promise.promisify(fs.readFile)

app.set('port', process.env.PORT || 8989);
app.use(express.static(__dirname + '/public'));
app.use(bodyParser.urlencoded({
    extended: true
}));


app.get('/', function(req, res){
    manifest = {
        format: "conf-json-v1",
        data_files: [
                "/blocks.json",
                "/rooms.json",
                "/speakers.json",
                "/tags.json",
                "/sessions.json",
                "/videos.json"
            ]
    }

    returnJson(Promise.resolve(manifest), res);
});

app.get('/blocks.json', function(req, res){
    readFile('data/blocks.json').then(function(data){
        returnJson(data, res);
    }).catch(function(error){
        res.status(500);
    });
});

app.get('/rooms.json', function(req, res){
    readFile('data/rooms.json').then(function(data){
            returnJson(data, res);
        }).catch(function(error){
            res.status(500);
        });
});

app.get('/speakers.json', function(req, res){
    readFile('data/speakers.json').then(function(data){
            returnJson(data, res);
        }).catch(function(error){
            res.status(500);
        });
});

app.get('/tags.json', function(req, res){
    readFile('data/tags.json').then(function(data){
            returnJson(data, res);
        }).catch(function(error){
            res.status(500);
        });
});

app.get('/sessions.json', function(req, res){
    readFile('data/sessions.json').then(function(data){
            returnJson(data, res);
        }).catch(function(error){
            res.status(500);
        });
});

app.get('/videos.json', function(req, res){
    readFile('data/videos.json').then(function(data){
            returnJson(data, res);
        }).catch(function(error){
            res.status(500);
        });
});

app.get('/full', function(req, res){
    /*
    readFile('data/data.json').then(function(data){
            var fullData = JSON.parse(data);
            returnJson(fullData, res);
        }).catch(function(error){
            res.status(500);
        });
        */

        Promise.delay(0).then(function() {
           return [readFile("data/blocks.json"),
                   readFile("data/rooms.json"),
                   readFile("data/tags.json"),
                   readFile("data/speakers.json"),
                   readFile("data/sessions.json"),
                   readFile("data/videos.json")] ;
        }).spread(function(blocks, rooms, tags, speakers, sessions, videos) {

            var fullData = {
                blocks: JSON.parse(blocks),
                rooms: JSON.parse(rooms),
                tags: JSON.parse(tags),
                speakers: JSON.parse(speakers),
                sessions: JSON.parse(sessions),
                videos: JSON.parse(videos)
            }
            returnJson(fullData, res);
        });
});

function returnJson(data, res){
console.log(data)
    res.type('application/json; charset=' + defaultCharset);
    res.status(200).send(data);
}

app.listen(app.get('port'), function () {
    console.log('Express server listening on port ' + app.get('port'));
});