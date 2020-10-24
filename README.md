# ADNMutation-SpringBoot
Aplicación Spring Boot para la gestión y comprobación de mutación ADN

## Requisitos para ejecución de aplicación
- MySQL 8
- JDK 8
- IDE (eclipse, netbeans, etc)

## Pasos para ejecución en local
1. Descargar repositorio
2. Descomprimir archivo
3. Abrir proyecto en IDE de preferencia
4. Abrir la clase del paquete com.springboot.mutation.api.auth
5. Ingresa una clave robusta en la variable llamada KEY_SECRET
6. Generar una un certificado RSA y obtener las llaves privada y publica

```
Se pueden generar con la herramienta openssl mediante los siguientes comandos

// Genera el certificado ingresandolo en un archivo llamado jwt.pem
openssl genrsa -out jwt.pem

// Imprime la llave privada del archivo jwt.pem
openssl rsa -in jwt.pem

// Imprime la llave privada del archivo jwt.pem
openssl rsa -in jwt.pem -pubout
```
  
7. Ingresar las llaves respectivamente en las variables RSA_PRIVATE y RSA_PUBLIC

8. Ingresar a la carpeta contenedora del proyecto por terminal

9. Ejecutar el siguiente comando para limpiar despliegues anteriores del proyecto y empaquetar el mismo

```
./mvnw clean package
```

10. Ingresar a la carpeta target y ejecutar el siguiente comando para ejecutar el proyecto mediante el archivo jar

```
java -jar spring-dna-mutation-api-0.0.1-SNAPSHOT.jar
```

## Despliegue en la nube
Para el despliegue en la nube se utilizó el servicio de Heroku siguiendo los pasos que se describen a continuación

1.	Ingresar al método para la obtención del Bean **corsConfigurationSource** localizado en la clase **ResourceServerConfig** del paquete **auth** y especificar la dirección de la aplicación cliente en la instrucción **setAllowedOrigins**
2.	Ingresar en el tag **@CrossOrigin** de la clase **HumanController** del paquete controller la dirección de la aplicación cliente
3.	Ingresar a heroku, autenticarse y crear una aplicación seleccionando el buildpack para java
4.	Ingresar al archivo **application.properties** e ingresar la siguiente propiedad para especificar el puerto en el que correrá la aplicación en Heroku

```
server.port=${PORT:8080}
```

Para los siguientes puntos es necesario instalar la herramienta “Heroku CLI” depediendo del sistema operativo de su equipo, tomando como referencia la siguiente documentación:

https://devcenter.heroku.com/articles/heroku-cli

Tambien es necesario estar autenticado en heroku con la herramienta instalada en el punto anterior, esto se puede realizar mediante el comando `heroku login`

5.	Si no se ha inicializado un repositorio **git** local, es necesario ingresar a la ruta base del proyecto por terminal y ejecutar el comando `git init`
6.	Ingresar el siguiente comando para asociar el repositorio local con el repositorio remoto de la aplicación Heroku

```
git:remote -a {nombre de la aplicación creada en Heroku}
```

7.	Mediante el siguiente comando se instalará el plugin de java en el proyecto heroku

```
heroku plugins:install java
```

8.	Mediante el siguiente comando se creará la base de datos en MySQL mediante un add-on disponible en Heroku llamado **JawsDB MySQL**

```
heroku addons:create jawsdb
```

9.	Ingresando el comando `heroku config:get JAWSDB_URL` obtendremos la url de conexión a la base de datos creada en el punto anterior, teniendo la siguiente estructura:


```
mysql://{USUARIO}:{USUARIO_CONTRASEÑA}@{URL_BASE_DE_DATOS}:{PUERTO}/{NOMBRE_BASE_DE_DATOS}
```

10.	Ingresar los datos de la base de datos obtenidos en el punto anterior en las variables correspondientes del archivo **application.properties**
11.	Ejecutar el siguiente comando para limpiar despliegues anteriores del proyecto y empaquetar el mismo y obtener el archivo jar

```
./mvnw clean package
```

12.	Realizar el despliegue del proyecto en Heroku ejecutando el siguiente comando

```
heroku jar:deploy ./target/{NOMBRE_DE_ARCHIVO}.jar
```

Este último paso retornara la url del proyecto desplegado, por ejemplo:
https://{nombre_del_proyecto}.herokuapp.com
