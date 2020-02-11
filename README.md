# AD_MongoDB


show databases
show collections

use baseexemplo
db.persoas.insert( {"nome":"luis", numeros:[21,56,89] } )

db.persoas.find()

db.persoas.remove({ nome:"luis"})

db.persoas.insert( {"nome":"ana","edade":31, numeros:[54,36,12,75], "enderezo":{"rua":"urzaiz", "numero":24, "piso":3, "porta":"a"} } )
db.persoas.insert( {nome:"felix", edade:40, enderezo:{"rua":"faisan", numero:14, piso:1 } } )


-- busqueda anidada

db.persoas.find({"enderezo.rua":"faisan"})

Operadores

$eq ==
db.persoas.find({nome:{$eq:"luis"}})

$ne != (no cumple condic o no tiene el campo de busqueda)
db.persoas.find({edade:{$ne:31}})

$in In
db.persoas.find({nome:{$in:["ana", "luis"]}})
$nin Not In
db.persoas.find({nome:{$nin:["ana", "luis"]}})
db.persoas.find({edade:{$nin:[35,40]}})

$gt
db.persoas.find({edade:{$gt:30}})

>>OPERADORES

$eq --> equal
db.persoas.find({"enderezo.rua":{$eq:"faisan"}})

$ne --> no son iguales, nonequal, lista lso que no cumplan la condicion o que no exista ese campo
db.persoas.find({"edade":{$ne:21}})

$in --> Lista los documentos cuyo nombre se encuentre en ese conjunto
db.persoas.find({"nome":{$in:["ana","luis"]}})

$nin --> Lista los documentos cuyo nombre no se encuentre en ese conjunto
db.persoas.find({"nome":{$nin:["ana","luis"]}})

$gt --> greater than, y no salen losnull, solo los que tienen campo edade
db.persoas.find({"edade":{$gt:30}})

$lt --> less than, salen los que son menores y no los que no tienen campo edad.
db.persoas.find({"edade":{$lt:40}})

$gte -->greater than equal, mayor o igual, y no salen losnull, solo los que tienen campo edade
db.persoas.find({"edade":{$gte:21}})

$lte --> less than equal, salen los que son menores y no los que no tienen campo edad.
db.persoas.find({"edade":{$lte:40}})

$or --> Se cumple una u otra condición
db.persoas.find({$or:[{"edade":{$gt:21}},{"nome":{$eq:"ana"}}]})

$and --> Se cumplen las dos condiciones a la vez
db.persoas.find({$and:[{"edade":{$gt:21}},{"nome":{$eq:"ana"}}]})

$not --> 
db.persoas.find({"edade":{$not:{$gt:30}}})

$nor --> Lista los que no cumplen alguna de estas condiciones


$exists --> 
db.persoas.find({$nor:[{"edade":30},{"nome":"ana"}]})
db.persoas.find({$nor:[{"edade":30},{"edade":{$exists:false}},{"nome":"ana"},{"nome":{exists:false}}]})


--> Poniendo exists quitamos los null que no tienen campo edad, 1=true,0=false
db.persoas.find({"edade":{$exists:true,$nin:[30,40]}})

Ref: https://docs.mongodb.com/manual/reference/operator/query/show databases
use training
db.scores.find()

db.scores.find({score:{$lt:20}})

it + enter -> baja los resultados de una consulta por el terminal de forma homogenea

db.scores.find({$or:[{kind:'exam'},{kind:'quiz'}]})

db.scores.insert({_id:1,kind:'taller',score:15,student:5})
db.scores.insert({_id:2,kind:'taller',score:25,student:4})

db.scores.find({$and:[{kind:'taller'},{student:{$lt:5}}]})

db.scores.find().limit(10).sort({student:-1})

db.stuff.insert({_id:123,hello:'world'})
db.stuff.find()
db.stuff.remove({}) -> vacia la collection entera

db.stuff.update({_id:123},{$set:{hello:'si'}})

db.stuff.insert({_id:124,by:'no'})

db.stuff.update({_id:123},{helo:'xa'})
db.stuff.update({_id:123},{$set:{hello:'si'}})
Comparison
Name	Description
$eq	Matches values that are equal to a specified value.
$gt	Matches values that are greater than a specified value.
$gte	Matches values that are greater than or equal to a specified value.
$in	Matches any of the values specified in an array.
$lt	Matches values that are less than a specified value.
$lte	Matches values that are less than or equal to a specified value.
$ne	Matches all values that are not equal to a specified value.
$nin	Matches none of the values specified in an array.

Logical
Name	Description
$and	Joins query clauses with a logical AND returns all documents that match the conditions of both clauses.
$not	Inverts the effect of a query expression and returns documents that do not match the query expression.
$nor	Joins query clauses with a logical NOR returns all documents that fail to match both clauses.
$or	Joins query clauses with a logical OR returns all documents that match the conditions of either clause.



Element
Name	Description
$exists	Matches documents that have the specified field.
$type	Selects documents if a field is of the specified type.


Evaluation
Name	Description
$expr	Allows use of aggregation expressions within the query language.
$jsonSchema	Validate documents against the given JSON Schema.
$mod	Performs a modulo operation on the value of a field and selects documents with a specified result.
$regex	Selects documents where values match a specified regular expression.
$text	Performs text search.
$where	Matches documents that satisfy a JavaScript expression.


Geospatial
Name	Description
$geoIntersects	Selects geometries that intersect with a GeoJSON geometry. The 2dsphere index supports $geoIntersects.
$geoWithin	Selects geometries within a bounding GeoJSON geometry. The 2dsphere and 2d indexes support $geoWithin.
$near	Returns geospatial objects in proximity to a point. Requires a geospatial index. The 2dsphere and 2d indexes support $near.
$nearSphere	Returns geospatial objects in proximity to a point on a sphere. Requires a geospatial index. The 2dsphere and 2d indexes support $nearSphere.


Array
Name	Description
$all	Matches arrays that contain all elements specified in the query.
$elemMatch	Selects documents if element in the array field matches all the specified $elemMatch conditions.
$size	Selects documents if the array field is a specified size.


Bitwise
Name	Description
$bitsAllClear	Matches numeric or binary values in which a set of bit positions all have a value of 0.
$bitsAllSet	Matches numeric or binary values in which a set of bit positions all have a value of 1.
$bitsAnyClear	Matches numeric or binary values in which any bit from a set of bit positions has a value of 0.
$bitsAnySet	Matches numeric or binary values in which any bit from a set of bit positions has a value of 1.


Comments
Name	Description
$comment	Adds a comment to a query predicate.


Projection Operators
Name	Description
$	Projects the first element in an array that matches the query condition.
$elemMatch	Projects the first element in an array that matches the specified $elemMatch condition.
$meta	Projects the document’s score assigned during $text operation.
$slice	Limits the number of elements projected from an array. Supports skip and limit slices.



db.persoas.find({edade:{$exists:true, $nin:[20,30]}})

db.persoas.find({edade:{$gt:30}}, {nome:0, edade:1})

db.persoas.find({edade:{$gt:30}}, {_id:0,nome:1, edade:1})


db.persoas.find({$or:[{nome:'ana'}, {edade:{$gt:31}}]}, {nome:0})





show databases
use training
db.scores.find()

db.scores.find({score:{$lt:20}})

it + enter -> baja los resultados de una consulta por el terminal de forma homogenea

db.scores.find({$or:[{kind:'exam'},{kind:'quiz'}]})

db.scores.insert({_id:1,kind:'taller',score:15,student:5})
db.scores.insert({_id:2,kind:'taller',score:25,student:4})

db.scores.find({$and:[{kind:'taller'},{student:{$lt:5}}]})

db.scores.find().limit(10).sort({student:-1})

db.stuff.insert({_id:123,hello:'world'})
db.stuff.find()
db.stuff.remove({}) -> vacia la collection entera

db.stuff.update({_id:123},{$set:{hello:'si'}})

db.stuff.insert({_id:124,by:'no'})

db.stuff.update({_id:123},{helo:'xa'}, {upsert:true})
db.stuff.update({_id:123},{$set:{hello:'si'}})

db.stuff.update({_id:125},{helo:'xa'}, {upsert:true})

db.stuff.update({helo:'xa'), {$set:{telf:11111}})

db.stuff.update({helo:'xa'}, {$set:{telf:222222}}, {multi:true})




db.books.update({_id:1},
		{
		 $inc: {stock:3},
		 $set: {
			item:'abc',
			'info.publisher':'2222',
			tags:['software'],
			'ratings.1':{by:'xyz',rating:3}
		       }
		}
)






















