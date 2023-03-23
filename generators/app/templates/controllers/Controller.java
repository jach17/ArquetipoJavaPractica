package com.[%= companylower %].[%= namelower %].controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.[%= companylower %].[%= namelower %].commons.aspectj.JsonResponseInterceptor;
import com.[%= companylower %].[%= namelower %].commons.dto.[%= namecamel %]Dto;
import com.[%= companylower %].[%= namelower %].commons.request.PaginatedRequestDto;
import com.[%= companylower %].[%= namelower %].commons.response.GenericResponseDto;
import com.[%= companylower %].[%= namelower %].commons.response.PaginatedResponseDto;
import com.[%= companylower %].[%= namelower %].facade.[%= namecamel %]Facade;
[% if (redis) { %]import com.[%= companylower %].[%= namelower %].persistence.StringRedisRepository;[% } %]
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;

/**
 * [%= namecamel %] controller class
 * 
 * @author [%= username %]
 *
 */
@RestController
@RequestMapping("[%= apiName %]")
@CrossOrigin
@Slf4j
public class [%= namecamel %]Controller
{
  @Autowired
  private [%= namecamel %]Facade [%= entityLower %]Facade;

  [% if (redis) { %]@Autowired
  private StringRedisRepository redis;[% } %]

  /***
   * Method to get [%= namecamel %]
   * 
   * @param limit The limit
   * @param offset The offset
   * @return A paginated result of [%= namecamel %]
   */
  @JsonResponseInterceptor
  @GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
  @Operation(tags = "[%= namecamel %]s", summary = "Method to get [%= namecamel %]s paginated")
  public ResponseEntity<PaginatedResponseDto<[%= namecamel %]Dto>> find[%= namecamel %]s(
      @RequestParam(name = "limit", defaultValue = "50", required = false)
      int limit, @RequestParam(name = "offset", defaultValue = "0", required = false)
      int offset )
  {
    var result = this.[%= entityLower %]Facade.find[%= namecamel %]s( new PaginatedRequestDto( limit, offset ) );
    return ResponseEntity.ok( result );
  }

  /**
   * Method to get [%= namecamel %] by id
   * 
   * @param id The [%= entityLower %] Id
   * @return An registry of [%= entityLower %] or 204
   */
  @JsonResponseInterceptor
  @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  @Operation(tags = "[%= namecamel %]s", summary = "Method to get [%= namecamel %] by id")
  public ResponseEntity<GenericResponseDto<[%= namecamel %]Dto>> find[%= namecamel %]( @PathVariable("id")
  Integer id )
  {
    [% if (redis) { %]
    String key = this.get[%= namecamel %]Key( id );

    Gson gson = new GsonBuilder().create();
    GenericResponseDto<[%= namecamel %]Dto> result = null;
    if( redis.hasKey( key ) )
    {
      var json = this.redis.get( key );

      result = gson.fromJson( json, new TypeToken<GenericResponseDto<[%= namecamel %]Dto>>()
      {
      }.getType() );
    }
    else
    {
      result = this.[%= entityLower %]Facade.find( id );

      String json = gson.toJson( result );
      this.redis.add( key, json );
    }
    [% } else { %]var result = this.[%= entityLower %]Facade.find( id );[% } %]

    HttpStatus status = HttpStatus.OK;
    if( result == null )
    {
      status = HttpStatus.NO_CONTENT;
    }
    return new ResponseEntity<>( result, status );
  }

  private String get[%= namecamel %]Key( Integer id )
  {
    String key = new StringBuilder().append( "[%= namecamel %].by[%= namecamel %]Id:" ).append( id ).toString();
    return key;
  }

  /**
   * Method to create a [%= namecamel %]
   * 
   * @param dto The [%= namecamel %] object
   * @return
   */
  @JsonResponseInterceptor
  @PostMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  @Operation(tags = "[%= namecamel %]s", summary = "Method to create a [%= namecamel %]")
  public ResponseEntity<GenericResponseDto<[%= namecamel %]Dto>> create( @RequestBody [%= namecamel %]Dto dto )
  {
    var result = this.[%= entityLower %]Facade.create( dto );
    return new ResponseEntity<>( result, HttpStatus.CREATED );
  }

  /**
   * Method to update a [%= namecamel %]
   * 
   * @param id The [%= namecamel %] Id
   * @param dto The [%= namecamel %] object
   * @return
   */
  @JsonResponseInterceptor
  @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  @Operation(tags = "[%= namecamel %]s", summary = "Method to update a [%= namecamel %]")
  public ResponseEntity<GenericResponseDto<Boolean>> update( @PathVariable("id") Integer id, @RequestBody [%= namecamel %]Dto dto )
  {
    dto.setId( id );
    var result = this.[%= entityLower %]Facade.update( dto );

    [% if (redis) { %]if( result.getBody() )
    {
      this.redis.delete( this.get[%= namecamel %]Key( id ) );
    }[% } %]

    return ResponseEntity.ok( result );
  }

  /**
   * Method to delete a [%= namecamel %]
   * 
   * @param id The [%= namecamel %] Id
   * @return
   */
  @JsonResponseInterceptor
  @DeleteMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  @Operation(tags = "[%= namecamel %]s", summary = "Method to delete a [%= namecamel %]")
  public ResponseEntity<GenericResponseDto<Boolean>> delete( @PathVariable("id") Integer id )
  {
    var result = this.[%= entityLower %]Facade.delete( id );

    [% if (redis) { %]if( result.getBody() )
    {
      this.redis.delete( this.get[%= namecamel %]Key( id ) );
    }[% } %]
    return ResponseEntity.ok( result );
  }

  /**
   * Ping
   * 
   * @return Pong
   */
  @JsonResponseInterceptor
  @GetMapping(path = "ping", produces = MediaType.APPLICATION_JSON_VALUE)
  @Operation(tags = "[%= namecamel %]s", summary = "Ping")
  public ResponseEntity<GenericResponseDto<String>> ping( )
  {
    return ResponseEntity.ok( new GenericResponseDto<>("pong") );
  }
}
