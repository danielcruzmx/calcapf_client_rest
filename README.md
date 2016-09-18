# calcapf_client_rest
Cliente Java Rest

Explota un servicio REST de calculo de nomina elaborado en Django-REST.

El ejemplo conecta con una base de datos Oracle, obtiene informacion para generar un objeto JSon con las caracteristicas que requiere el servicio REST Django.

EL servicio REST recibe al objeto y de acuerdo a sus atributos realiza el calculo que es devuelto en el mismo objeto a la aplicacion cliente.

El ejemplo hace uso de las librerias:

- JPA para conectar con la base de datos y recuperar la informacion a traves de consultas SQL.
- Dozer que mapea el objeto de datos al objeto que sera enviado al servicio REST.
- Jackson que convierte objetos al formato JSon y viceversa.
- HttpClient que conecta con el servicio REST, envia y recibe datos.

Integrado con Spring y armado con la ayuda de MAVEN.




