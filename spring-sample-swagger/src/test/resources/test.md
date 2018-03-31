### start docker swagger ui
$ docker run --rm -it -d --name myswagger -p 8080:8080 swaggerapi/swagger-ui:v2.2.9

### start spring boot app that will provide API definition(swagger.yaml)
$ java run com.bicjo.sample.SwaggerApplication

### open url to any browser and use the API_URL  
>> http://192.168.99.100:8080  
>> API_URL = http://localhost:8080/api-yml/swagger.yaml

---

### start docker swagger editor
$ docker run --rm -it -d --name myeditor -p 8081:8080 swaggerapi/swagger-editor

