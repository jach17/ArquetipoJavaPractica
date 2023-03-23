const config = require("./config.json");
var Generator = require('yeoman-generator');

module.exports = class extends Generator {
    
    initializing() {
        this.props = {
            company: config.company,
            name: config.name,
            port: config.port,
            version: config.version,
            username: config.username,
            lombok: config.lombok,
            redis: config.redis,
            controllers: config.controllers
        };
        this.log('Company:', config.company);
        this.log('Name:', config.name);
        this.log('Port:', config.port);
        this.log('Version:', config.version);
        this.log('Username:', config.username);
        this.log('Lombok:', config.lombok);
        this.log('Redis:', config.redis);
    }

    writing() {
        var done = this.async();
        this.fs.copyTpl(
            this.templatePath('api-service/**'),
            this.destinationPath(`${this.props.name.toLowerCase()}-service/`), {
                name: this.props.name,
                namelower: this.props.name.toLowerCase(),
                nameupper: this.props.name.toUpperCase(),
                namecamel: this.props.name.substring(0,1).toUpperCase() + this.props.name.substring(1).toLowerCase(),
                port: this.props.port,
                company: this.props.company,
                companylower: this.props.company.toLowerCase(),
                username: this.props.username,
                version: this.props.version,
                lombok: this.props.lombok,
                redis: this.props.redis,
                controllers: config.controllers
            },
            {
                openDelimiter: '[',
                closeDelimiter: ']'
            }
        );
        this.fs.copyTpl(
            this.templatePath('api-service/*/**/*'),
            this.destinationPath(`${this.props.name.toLowerCase()}-service/`), {
                name: this.props.name,
                namelower: this.props.name.toLowerCase(),
                nameupper: this.props.name.toUpperCase(),
                namecamel: this.props.name.substring(0,1).toUpperCase() + this.props.name.substring(1).toLowerCase(),
                port: this.props.port,
                company: this.props.company,
                companylower: this.props.company.toLowerCase(),
                username: this.props.username,
                version: this.props.version,
                lombok: this.props.lombok,
                redis: this.props.redis,
                controllers: config.controllers
            },
            {
                openDelimiter: '[',
                closeDelimiter: ']'
            }
        );
        this.fs.copyTpl(
            this.templatePath('.gitignore'),
            this.destinationPath(`${this.props.name.toLowerCase()}-service/.gitignore`)
          );

        this.fs.copyTpl(
            this.templatePath('sql/**'),
            this.destinationPath(`${this.props.name.toLowerCase()}-service/${this.props.company.toLowerCase()}.${this.props.name.toLowerCase()}-api/src/main/resources`),
          );

        this.fs.copyTpl(
            this.templatePath('sql/**'),
            this.destinationPath(`${this.props.name.toLowerCase()}-service/${this.props.company.toLowerCase()}.${this.props.name.toLowerCase()}-service/src/test/resources`),
          );
        
        this.props.controllers.forEach(controller => {
            this.log('Creando Controller:', controller.name);

            /* Controller */
            this.fs.copyTpl(
                this.templatePath('controllers/Controller.java'),
                this.destinationPath(`${this.props.name.toLowerCase()}-service/${this.props.company.toLowerCase()}.${this.props.name.toLowerCase()}-api/src/main/java/com/${this.props.company.toLowerCase()}/${this.props.name.toLowerCase()}/controller/${controller.name}Controller.java`),
                {
                    name: controller.name,
                    apiName: controller.apiName,
                    namelower: this.props.name.toLowerCase(),
                    nameupper: this.props.name.toUpperCase(),
                    namecamel: controller.nameCamel,
                    entityLower: controller.entityLower,
                    port: this.props.port,
                    company: this.props.company,
                    companylower: this.props.company.toLowerCase(),
                    lombok: this.props.lombok,
                    redis: this.props.redis,
                    username: this.props.username,
                    version: this.props.version,
                },
                {
                    openDelimiter: '[',
                    closeDelimiter: ']'
                });
            this.fs.copyTpl(
                    this.templatePath('controllers/ControllerTest.java'),
                    this.destinationPath(`${this.props.name.toLowerCase()}-service/${this.props.company.toLowerCase()}.${this.props.name.toLowerCase()}-api/src/test/java/com/${this.props.company.toLowerCase()}/${this.props.name.toLowerCase()}/controller/${controller.name}ControllerTest.java`),
                    {
                        name: controller.name,
                        apiName: controller.apiName,
                        namelower: this.props.name.toLowerCase(),
                        nameupper: this.props.name.toUpperCase(),
                        namecamel: controller.nameCamel,
                        entityLower: controller.entityLower,
                        port: this.props.port,
                        company: this.props.company,
                        companylower: this.props.company.toLowerCase(),
                        lombok: this.props.lombok,
                        redis: this.props.redis,
                        username: this.props.username,
                        version: this.props.version,
                    },
                    {
                        openDelimiter: '[',
                        closeDelimiter: ']'
                    });
            if (this.props.redis) {
                this.fs.copyTpl(
                    this.templatePath('controllers/RedisConfiguration.java'),
                    this.destinationPath(`${this.props.name.toLowerCase()}-service/${this.props.company.toLowerCase()}.${this.props.name.toLowerCase()}-api/src/main/java/com/${this.props.company.toLowerCase()}/${this.props.name.toLowerCase()}/config/RedisConfiguration.java`),
                    {
                        name: controller.name,
                        apiName: controller.apiName,
                        namelower: this.props.name.toLowerCase(),
                        nameupper: this.props.name.toUpperCase(),
                        namecamel: controller.nameCamel,
                        entityLower: controller.entityLower,
                        port: this.props.port,
                        company: this.props.company,
                        companylower: this.props.company.toLowerCase(),
                        lombok: this.props.lombok,
                        redis: this.props.redis,
                        username: this.props.username,
                        version: this.props.version,
                    },
                    {
                        openDelimiter: '[',
                        closeDelimiter: ']'
                    });
            }

            /* Facade */
            this.fs.copyTpl(
                    this.templatePath('facade/Facade.java'),
                    this.destinationPath(`${this.props.name.toLowerCase()}-service/${this.props.company.toLowerCase()}.${this.props.name.toLowerCase()}-facade/src/main/java/com/${this.props.company.toLowerCase()}/${this.props.name.toLowerCase()}/facade/${controller.name}Facade.java`),
                    {
                        name: controller.name,
                        namelower: this.props.name.toLowerCase(),
                        nameupper: this.props.name.toUpperCase(),
                        namecamel: controller.nameCamel,
                        entityLower: controller.entityLower,
                        port: this.props.port,
                        company: this.props.company,
                        companylower: this.props.company.toLowerCase(),
                        username: this.props.username,
                        version: this.props.version,
                    },
                    {
                        openDelimiter: '[',
                        closeDelimiter: ']'
                    });
            this.fs.copyTpl(
                        this.templatePath('facade/FacadeImpl.java'),
                        this.destinationPath(`${this.props.name.toLowerCase()}-service/${this.props.company.toLowerCase()}.${this.props.name.toLowerCase()}-facade/src/main/java/com/${this.props.company.toLowerCase()}/${this.props.name.toLowerCase()}/facade/impl/${controller.name}FacadeImpl.java`),
                        {
                            name: controller.name,
                            namelower: this.props.name.toLowerCase(),
                            nameupper: this.props.name.toUpperCase(),
                            namecamel: controller.nameCamel,
                            entityLower: controller.entityLower,
                            port: this.props.port,
                            company: this.props.company,
                            companylower: this.props.company.toLowerCase(),
                            username: this.props.username,
                            version: this.props.version,
                        },
                        {
                            openDelimiter: '[',
                            closeDelimiter: ']'
                        });
            this.fs.copyTpl(
                            this.templatePath('facade/FacadeTest.java'),
                            this.destinationPath(`${this.props.name.toLowerCase()}-service/${this.props.company.toLowerCase()}.${this.props.name.toLowerCase()}-facade/src/test/java/com/${this.props.company.toLowerCase()}/${this.props.name.toLowerCase()}/facade/${controller.name}FacadeTest.java`),
                            {
                                name: controller.name,
                                namelower: this.props.name.toLowerCase(),
                                nameupper: this.props.name.toUpperCase(),
                                namecamel: controller.nameCamel,
                                entityLower: controller.entityLower,
                                port: this.props.port,
                                company: this.props.company,
                                companylower: this.props.company.toLowerCase(),
                                username: this.props.username,
                                version: this.props.version,
                            },
                            {
                                openDelimiter: '[',
                                closeDelimiter: ']'
                            });
            /** Service */
            this.fs.copyTpl(
                this.templatePath('service/Service.java'),
                this.destinationPath(`${this.props.name.toLowerCase()}-service/${this.props.company.toLowerCase()}.${this.props.name.toLowerCase()}-service/src/main/java/com/${this.props.company.toLowerCase()}/${this.props.name.toLowerCase()}/service/${controller.name}Service.java`),
                {
                    name: controller.name,
                    namelower: this.props.name.toLowerCase(),
                    nameupper: this.props.name.toUpperCase(),
                    namecamel: controller.nameCamel,
                    port: this.props.port,
                    company: this.props.company,
                    companylower: this.props.company.toLowerCase(),
                    username: this.props.username,
                    version: this.props.version,
                },
                {
                    openDelimiter: '[',
                    closeDelimiter: ']'
                });
            this.fs.copyTpl(
                    this.templatePath('service/ServiceImpl.java'),
                    this.destinationPath(`${this.props.name.toLowerCase()}-service/${this.props.company.toLowerCase()}.${this.props.name.toLowerCase()}-service/src/main/java/com/${this.props.company.toLowerCase()}/${this.props.name.toLowerCase()}/service/impl/${controller.name}ServiceImpl.java`),
                    {
                        name: controller.name,
                        namelower: this.props.name.toLowerCase(),
                        nameupper: this.props.name.toUpperCase(),
                        namecamel: controller.nameCamel,
                        entityLower: controller.entityLower,
                        port: this.props.port,
                        company: this.props.company,
                        companylower: this.props.company.toLowerCase(),
                        username: this.props.username,
                        version: this.props.version,
                        properties : controller.properties,
                    },
                    {
                        openDelimiter: '[',
                        closeDelimiter: ']'
                    });
            this.fs.copyTpl(
                        this.templatePath('service/ServiceTest.java'),
                        this.destinationPath(`${this.props.name.toLowerCase()}-service/${this.props.company.toLowerCase()}.${this.props.name.toLowerCase()}-service/src/test/java/com/${this.props.company.toLowerCase()}/${this.props.name.toLowerCase()}/service/${controller.name}ServiceTest.java`),
                        {
                            name: controller.name,
                            namelower: this.props.name.toLowerCase(),
                            nameupper: this.props.name.toUpperCase(),
                            namecamel: controller.nameCamel,
                            entityLower: controller.entityLower,
                            port: this.props.port,
                            company: this.props.company,
                            companylower: this.props.company.toLowerCase(),
                            username: this.props.username,
                            version: this.props.version,
                        },
                        {
                            openDelimiter: '[',
                            closeDelimiter: ']'
                        });
            /** Persistence */
            this.fs.copyTpl(
                this.templatePath('persistence/Persistence.java'),
                this.destinationPath(`${this.props.name.toLowerCase()}-service/${this.props.company.toLowerCase()}.${this.props.name.toLowerCase()}-persistence/src/main/java/com/${this.props.company.toLowerCase()}/${this.props.name.toLowerCase()}/persistence/${controller.name}Persistence.java`),
                {
                    name: controller.name,
                    namelower: this.props.name.toLowerCase(),
                    nameupper: this.props.name.toUpperCase(),
                    namecamel: controller.nameCamel,
                    port: this.props.port,
                    company: this.props.company,
                    companylower: this.props.company.toLowerCase(),
                    username: this.props.username,
                    version: this.props.version,
                },
                {
                    openDelimiter: '[',
                    closeDelimiter: ']'
                });

            if (this.props.redis) {
                this.fs.copyTpl(
                    this.templatePath('persistence/StringRedisRepository.java'),
                    this.destinationPath(`${this.props.name.toLowerCase()}-service/${this.props.company.toLowerCase()}.${this.props.name.toLowerCase()}-persistence/src/main/java/com/${this.props.company.toLowerCase()}/${this.props.name.toLowerCase()}/persistence/StringRedisRepository.java`),
                    {
                        name: controller.name,
                        apiName: controller.apiName,
                        namelower: this.props.name.toLowerCase(),
                        nameupper: this.props.name.toUpperCase(),
                        namecamel: controller.nameCamel,
                        entityLower: controller.entityLower,
                        port: this.props.port,
                        company: this.props.company,
                        companylower: this.props.company.toLowerCase(),
                        lombok: this.props.lombok,
                        redis: this.props.redis,
                        username: this.props.username,
                        version: this.props.version,
                    },
                    {
                        openDelimiter: '[',
                        closeDelimiter: ']'
                    });
            }
        /** Model */

        var temporal = false;
        var manyToOne = false;
        var oneToMany = false;
        var manyToMany = false;
        var jsonIgnore = false;

        controller.properties.forEach(p => {
            temporal |= p.timestamp || p.date || p.time;
            manyToOne |= p.manyToOne;
            oneToMany |= p.oneToMany;
            manyToMany |= p.manyToMany;
            jsonIgnore |= p.jsonIgnore;
        });

        this.fs.copyTpl(
            this.templatePath('model/EntityDO.java'),
            this.destinationPath(`${this.props.name.toLowerCase()}-service/${this.props.company.toLowerCase()}.${this.props.name.toLowerCase()}-model/src/main/java/com/${this.props.company.toLowerCase()}/${this.props.name.toLowerCase()}/model/${controller.entity}DO.java`),
            {
                name: controller.entity,
                namelower: this.props.name.toLowerCase(),
                nameupper: this.props.name.toUpperCase(),
                namecamel: controller.nameCamel,
                port: this.props.port,
                company: this.props.company,
                companylower: this.props.company.toLowerCase(),
                username: this.props.username,
                version: this.props.version,
                properties : controller.properties,
                tablename: controller.table,
                lombok: this.props.lombok,
                temporal: temporal,
                manyToOne: manyToOne,
                oneToMany: oneToMany,
                manyToMany: manyToMany,
            },
            {
                openDelimiter: '[',
                closeDelimiter: ']'
            });

        /** DTOs */
        this.fs.copyTpl(
            this.templatePath('dto/Dto.java'),
            this.destinationPath(`${this.props.name.toLowerCase()}-service/${this.props.company.toLowerCase()}.${this.props.name.toLowerCase()}-commons/src/main/java/com/${this.props.company.toLowerCase()}/${this.props.name.toLowerCase()}/commons/dto/${controller.entity}Dto.java`),
            {
                name: controller.entity,
                namelower: this.props.name.toLowerCase(),
                nameupper: this.props.name.toUpperCase(),
                namecamel: controller.nameCamel,
                port: this.props.port,
                company: this.props.company,
                companylower: this.props.company.toLowerCase(),
                username: this.props.username,
                version: this.props.version,
                properties : controller.properties,
                tablename: controller.table,
                lombok: this.props.lombok,
                temporal: temporal,
                manyToOne: manyToOne,
                oneToMany: oneToMany,
                manyToMany: manyToMany,
                jsonIgnore: jsonIgnore
            },
            {
                openDelimiter: '[',
                closeDelimiter: ']'
            });

        });

        done();
    }

    install() {
        var done = this.async();
        this.spawnCommand('mvn',['clean', 'package'], {'cwd':`${this.props.name.toLowerCase()}-service/`})
        .on('error', function(err) {
            done(err);
        })
    }

};