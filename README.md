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

10. Ingresar a la carpeta target y ejecutar el siguiente comando para ejecutar el proyecto

```
java -jar spring-dna-mutation-api-0.0.1-SNAPSHOT
```
