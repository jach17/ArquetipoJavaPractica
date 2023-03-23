# Generator Jdk11 + Spring Boot microservicios Yeoman

Proyecto generado para la configuración automática de microservicios con OpenJdk 11 + Spring Boot 2.7.1

## Requisitos

* [Instalar NodeJs LTS](https://nodejs.org/es/)
* Instalar Yeoman
    `npm install -g yo`
* Ejecutar dentro de jdk11-springboot/generator/
    `npm link`

## Generar proyecto

Ejecutar el comando: `yo jdk11-springboot`  

Se emplearán las configuraciones en los archivos

* [generators/app/config.json](generators/app/config.json)
* [generators/app/templates/sql/schema.sql](generators/app/templates/sql/schema.sql)
* [generators/app/templates/sql/data.sql](generators/app/templates/sql/data.sql)

## Configuración de arquetipo

La configuración se realiza en el archivo [generators/app/config.json](generators/app/config.json)

Se indica:
* El nombre de la compañía.
* El nombre del servicio. 
* La versión de maven del proyecto.
* El puerto sobre el que se levanta el microservicio.
* El nombre del usuario (autor).
* La bandera lombok para emplear las anotaciones @Getter/@Setter en los DTOs y entidades.
* La bandera de redis para indicar si se habilita el servicio de cache de redis.
* La configuración de 1 o más controladores (microservicios).

```json
{
    "company": "Axity",
    "name": "Office",
    "version": "1.0.0",
    "port": 9090,
    "username": "username@axity.com",
    "lombok": true,
    "redis": false,
    "controllers": [ ... ]
}
```

Los controladores es un arreglo de json que configura cada microservicio, su DTO y entity correspondiente.

* El nombre lógico de la entidad (en PascalCase).
* El nombre o ruta de la api.
* El nombre de la entidad (en PascalCase).
* El nombre de la entidad (en PascalCase).
* El nombre físico de la tabla.
* Las propiedades (atributos) de la entidad.
```json
{
    "name": "Office",
    "apiName": "/api/offices",
    "nameCamel": "Office",
    "entity": "Office",
    "entityLower": "office",
    "table" : "TBL_Office",
    "properties" : [...]
}
```

Las propiedades o atributos es un arreglo de json que configura cada DTO y entidad.

* El nombre del atributo.
* El nombre del atributo en camel case.
* El tipo del atributo, se deben emplear primitivos, wrappers, String, Date, Entidades o List<Entidad>.
* El tipo del atributo del DTO, se deben emplear primitivos, wrappers, String, Date, Entidades o List<Dto>.
* La descripción para swagger.
* El nombre físico de la columna, puede ser null en el caso de mapeos de @OneToMany, @ManyToOne, @ManyToMany. 
* Bandera **primaryKey**, indica si se empleará la anotación @Id.
* Bandera **manyToOne**, indica si se empleará la anotación @ManyToOne. En caso de que sea true es requerido indicar la configuración **manyToOneConfig**.
* Bandera **oneToMany**, indica si se empleará la anotación @OneToMany. En caso de que sea true es requerido indicar la configuración **oneToManyConfig**.
* Bandera **manyToMany**, indica si se empleará la anotación @ManyToMany. En caso de que sea true es requerido indicar la configuración **manyToManyConfig**.
* Bandera **timestamp**, indica si se empleará la anotación @Temporal(TemporalType.TIMESTAMP).
* Bandera **date**, indica si se empleará la anotación @Temporal(TemporalType.DATE).
* Bandera **time**, indica si se empleará la anotación @Temporal(TemporalType.TIME).
* Bandera **update**, indica si llamará el atributo a actualizar en el método Update del microservicio. Importante, sólo puede usarse en primitivos, wrapper o String.
* Bandera **transient**, indica si se omite la serialización del atributo para la librería Gson. Importante, esto debe activarse en las llamadas bidireccionales entre las entidades para evitar anidación cíclica.
* Bandera **jsonIgnore**,indica si se omite la serialización del atributo para la librería Jackson. Importante, esto debe activarse en las llamadas bidireccionales entre las entidades para evitar anidación cíclica.

```json
{
    "name" : "Id",
    "nameCamel" : "id",
    "type" : "[int|Integer|String|Date|EntityDO]",
    "typeDto" : "[int|Integer|String|Date|EntityDto]",
    "description": "The id",
    "column": "cd_id" | null,
    "primaryKey": true,
    "manyToOne": false,
    "manyToOneConfig" : null | {...},
    "oneToMany": false,
    "oneToManyConfig": null  | {...},
    "manyToMany" : false,
    "manyToManyConfig": null  | {...},
    "timestamp": false,
    "date": false,
    "time": false,
    "update": false,
    "transient": false,
    "jsonIgnore" : false
}
```

La configuración de @ManyToOne, requiere indicar:
* la columna de la tabla de la entidad (**joinColumn**) y 
* la columna de la tabla de la entidad referenciada (**joinReferenceColumn**)
```json
{
    "manyToOneConfig":{
        "joinColumn": "cd_country",
        "joinReferenceColumn": "cd_id"
    }
}
```   

La configuración @OneToMany, requiere indicar:
* el nombre lógico de la entidad.
```json
{
    "oneToManyConfig": {
        "mappedBy" : "country"
    }
}
``` 

La configuración @ManyToMany permite emplear una tabla relacional MxN, hay dos posibles configuraciones:

* Indicando que la anotación está *mapeada* en la relación @ManyToMany de la otra entidad.
* Requiere indicar el nombre de la entidad *mapeada*.
```json
{
    "manyToManyConfig": {
        "mapped": true,
        "mappedBy": "roles"
    }
}
``` 
La otra configuración posible es indicando:
* El nombre de la tabla de relación (jointTable).
* El nombre de la columna la tabla de relación (joinColumn) y su nombre físico en la tabla (joinReferenceColumn).
* El nombre de la columna en la tabla de relación hacia la otra entidad (inverseJoinColumn) y su nombre físico en la tabla relacionada (inverseJoinReferenceColumn).
* La bandera mapped debe ser falsa.
* El atributo mappedBy no es requerido, puede ser nulo.
```json
{
"manyToManyConfig": {
                    "joinTable": "TRL_UserRole",
                    "joinColumn": "cd_user",
                    "joinReferenceColumn": "cd_id",
                    "inverseJoinColumn": "cd_role",
                    "inverseJoinReferenceColumn": "cd_id",
                    "mapped": false,
                    "mappedBy": null
                }
}
``` 

## Contributors

Javier Rodríguez [francisco.rodriguez@axity.com]  

Hugo Meraz [hugo.meraz@axity.com]

Guillermo Segura Olivera [guillermo.segura@axity.com]  

## License

[MIT](https://opensource.org/licenses/MIT)