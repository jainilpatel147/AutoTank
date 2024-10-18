var express = require('express');
var app = express();

var api = require("./routes/api");
app.use('/api',api);

const cors = require('cors');
app.use(cors());

app.get('/',function(req,res){
    // res.send("got request at /");
    var data = [{"id":1,"title":"Scary Movie 5 (Scary MoVie)","description":"adwerwersdfs","raised":"$8.47","image":"http://dummyimage.com/149x100.png/ff4444/ffffff"},
        {"id":2,"title":"Battle of the Bulge","description":"adwerwersdfs","raised":"$2.31","image":"http://dummyimage.com/116x100.png/ff4444/ffffff"},
        {"id":3,"title":"Girls Against Boys ","description":"adwerwersdfs","raised":"$3.83","image":"http://dummyimage.com/180x100.png/cc0000/ffffff"},
        {"id":4,"title":"All Is Forgiven (Tout est pardonné)","description":"adwerwersdfs","raised":"$6.47","image":"http://dummyimage.com/177x100.png/cc0000/ffffff"},
        {"id":5,"title":"Days of Heaven","description":"adwerwersdfs","raised":"$8.38","image":"http://dummyimage.com/241x100.png/cc0000/ffffff"},
        {"id":6,"title":"Platform (Zhantai)","description":"adwerwersdfs","raised":"$4.68","image":"http://dummyimage.com/160x100.png/dddddd/000000"},
        {"id":7,"title":"Weddings and Babies ","description":"adwerwersdfs","raised":"$7.49","image":"http://dummyimage.com/109x100.png/ff4444/ffffff"},
        {"id":8,"title":"My Wife's Relations","description":"adwerwersdfs","raised":"$8.22","image":"http://dummyimage.com/112x100.png/5fa2dd/ffffff"},
        {"id":9,"title":"Mountain, The","description":"adwerwersdfs","raised":"$6.89","image":"http://dummyimage.com/216x100.png/5fa2dd/ffffff"},
        {"id":10,"title":"Big Knife, The","description":"adwerwersdfs","raised":"$0.43","image":"http://dummyimage.com/219x100.png/cc0000/ffffff"}];
    res.json(data);
});

app.listen(5000);