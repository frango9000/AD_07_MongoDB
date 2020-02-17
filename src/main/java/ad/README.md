// abrir terminal mongo

mongo

//unha vez lanzado o cliente  mongo podo copiar e pegar o que se atopa a partir de aqui:

// creo base tenda

use tenda

// borrar coleccion pedidos 

db.pedidos.drop()

// creo colecion pedidos

var pedidos=

[
{
_id:"p1",
codcli:"c1",
codpro :"pro1",
cantidade :2,
data:"02/02/2011"
},

{
_id:"p2",
codcli:"c2",
codpro :"pro2",
cantidade :3,
data:"03/03/2011"
},

{
_id:"p3",
codcli:"c1",
codpro :"pro2",
cantidade :4,
data:"04/04/2011"
}
]

db.pedidos.insert(pedidos)

1) inserir este  documento : "p4",c1","pro3",5,"02/02/2019"

2) actualizar un par clave valor por clave do documento : exemplo actualizar o campo codpro do pedido  p3 a o valor pro4

3) incrementar un par clave valor por clave. exemplo: aumentar en 7 a cantidade correspondente ao pedido p2 .

4) amosar o documento correspondente ao pedido p3

5) amosar o codigo do cliente, o codigo do producto e a cantidade correspondentes ao pedido p1

6) amosar  o codigo do cliente e  o codigo do producto correspondentes aos pedidos que teñan no campo cantidade un valor superior a 2

7) amosar  o codigo do cliente e  o codigo do producto correspondentes aos pedidos que teñan no campo cantidade un valor superior a 2 e inferior a 5

8) amosar   o codigo do cliente e  o codigo do producto correspondentes a todos os pedidos.

9) aumentar  no seu dobre a cantidade correspondente ao pedido p4 .

