package com.axity.office.controller;

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

import com.axity.office.commons.aspectj.JsonResponseInterceptor;
import com.axity.office.commons.dto.UserDto;
import com.axity.office.commons.request.PaginatedRequestDto;
import com.axity.office.commons.response.GenericResponseDto;
import com.axity.office.commons.response.PaginatedResponseDto;
import com.axity.office.facade.UserFacade;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;

/**
 * User controller class
 * 
 * @author username@axity.com
 *
 */
@RestController
@RequestMapping("/api/users")
@CrossOrigin
@Slf4j
public class UserController
{
  @Autowired
  private UserFacade userFacade;

  

  /***
   * Method to get User
   * 
   * @param limit The limit
   * @param offset The offset
   * @return A paginated result of User
   */
  @JsonResponseInterceptor
  @GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
  @Operation(tags = "Users", summary = "Method to get Users paginated")
  public ResponseEntity<PaginatedResponseDto<UserDto>> findUsers(
      @RequestParam(name = "limit", defaultValue = "50", required = false)
      int limit, @RequestParam(name = "offset", defaultValue = "0", required = false)
      int offset )
  {
    var result = this.userFacade.findUsers( new PaginatedRequestDto( limit, offset ) );
    return ResponseEntity.ok( result );
  }

  /**
   * Method to get User by id
   * 
   * @param id The user Id
   * @return An registry of user or 204
   */
  @JsonResponseInterceptor
  @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  @Operation(tags = "Users", summary = "Method to get User by id")
  public ResponseEntity<GenericResponseDto<UserDto>> findUser( @PathVariable("id")
  Integer id )
  {
    var result = this.userFacade.find( id );

    HttpStatus status = HttpStatus.OK;
    if( result == null )
    {
      status = HttpStatus.NO_CONTENT;
    }
    return new ResponseEntity<>( result, status );
  }

  private String getUserKey( Integer id )
  {
    String key = new StringBuilder().append( "User.byUserId:" ).append( id ).toString();
    return key;
  }

  /**
   * Method to create a User
   * 
   * @param dto The User object
   * @return
   */
  @JsonResponseInterceptor
  @PostMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  @Operation(tags = "Users", summary = "Method to create a User")
  public ResponseEntity<GenericResponseDto<UserDto>> create( @RequestBody UserDto dto )
  {
    var result = this.userFacade.create( dto );
    return new ResponseEntity<>( result, HttpStatus.CREATED );
  }

  /**
   * Method to update a User
   * 
   * @param id The User Id
   * @param dto The User object
   * @return
   */
  @JsonResponseInterceptor
  @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  @Operation(tags = "Users", summary = "Method to update a User")
  public ResponseEntity<GenericResponseDto<Boolean>> update( @PathVariable("id") Integer id, @RequestBody UserDto dto )
  {
    dto.setId( id );
    var result = this.userFacade.update( dto );

    

    return ResponseEntity.ok( result );
  }

  /**
   * Method to delete a User
   * 
   * @param id The User Id
   * @return
   */
  @JsonResponseInterceptor
  @DeleteMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  @Operation(tags = "Users", summary = "Method to delete a User")
  public ResponseEntity<GenericResponseDto<Boolean>> delete( @PathVariable("id") Integer id )
  {
    var result = this.userFacade.delete( id );

    
    return ResponseEntity.ok( result );
  }

  /**
   * Ping
   * 
   * @return Pong
   */
  @JsonResponseInterceptor
  @GetMapping(path = "ping", produces = MediaType.APPLICATION_JSON_VALUE)
  @Operation(tags = "Users", summary = "Ping")
  public ResponseEntity<GenericResponseDto<String>> ping( )
  {
    return ResponseEntity.ok( new GenericResponseDto<>("pong") );
  }
}
