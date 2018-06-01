var database = new (require('../Modules/Database/module'))();


database.add({name:"Your mom"});
database.add({name:"Your mom2"});

console.log(database.getElementIndex({name:"Your mom"}));