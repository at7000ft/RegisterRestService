# Registration Web Service
####This is a Springboot/H2/Java Registration Web Service code challenge implementing four API


###Build an executable jar using Maven:
>cd RegisterRestService

>mvn clean package

###Run from executable jar (requires Java8):
>cd RegisterRestService/target

>java -jar RegisterRestService-1.0-SNAPSHOT.jar

###Open H2 console to view all Registration records in browser (connect to jdbc:h2:mem:testdb):
>http://localhost:8080/console

###Get All Registrations:
>curl http://localhost:8080

###Get User Registration:
>curl http://localhost:8080/joe

###Delete User Registration:
>curl -X "DELETE" http://localhost:8080/joe

###Add User Registration:
>curl -H "Content-Type: application/json" -X POST -d '{"userName":"xyz","email":"xyz@gmail.com"}' http://localhost:8080/add