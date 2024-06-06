Para compilar hay que ejecutar "mvn clean install". Se puede ejecutar el jar con Java. Si hay un daemon de Docker arrancado, va a crear la imagen en Docker, lista para arrancar con las opciones deseadas.
La base de datos se creará en el directorio "/data/".
La librería usada para el mantenimiento de scripts DDL ha sido Flyway. Para realizar el mantenimiento hay que ejecutar el comando "mvn clean flyway:migrate -Dflyway.configFiles=myFlywayConfig.conf".
Las APIs están definidas con Swagger. Para ver las definiciones se puede acceder a las URL "http://localhost:8080/v3/api-docs" o "http://localhost:8080/swagger-ui/index.html". Para poder usar las APIs hay que autenticarse poniendo la cabecera "Authorization":"321654987".
El API "/api/kafka" usa un Producer para enviar un mensaje por kafka. Luego hay un Consumer que lee el mensaje y lo muestra en el log.

Tal como está, Kafka no funciona en Docker. En un entorno dedicado a los servicios, habría un servicio de Kafka en Docker o en un cluster de Docker y tanto la aplicación como el servicio de Kafka estarían en el mismo network.