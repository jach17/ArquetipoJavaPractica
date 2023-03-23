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
import com.axity.office.commons.dto.RoleDto;
import com.axity.office.commons.request.PaginatedRequestDto;
import com.axity.office.commons.response.GenericResponseDto;
import com.axity.office.commons.response.PaginatedResponseDto;
import com.axity.office.facade.RoleFacade;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;

/**
 * Role controller class
 * 
 * @author username@axity.com
 *
 */
@RestController
@RequestMapping("/api/roles")
@CrossOrigin
@Slf4j
public class RoleController
{
  @Autowired
  private RoleFacade roleFacade;

  

  /***
   * Method to get Role
   * 
   * @param limit The limit
   * @param offset The offset
   * @return A paginated result of Role
   */
  @JsonResponseInterceptor
  @GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
  @Operation(tags = "Roles", summary = "Method to get Roles paginated")
  public ResponseEntity<PaginatedResponseDto<RoleDto>> findRoles(
      @RequestParam(name = "limit", defaultValue = "50", required = false)
      int limit, @RequestParam(name = "offset", defaultValue = "0", required = false)
      int offset )
  {
    var result = this.roleFacade.findRoles( new PaginatedRequestDto( limit, offset ) );
    return ResponseEntity.ok( result );
  }

  /**
   * Method to get Role by id
   * 
   * @param id The role Id
   * @return An registry of role or 204
   */
  @JsonResponseInterceptor
  @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  @Operation(tags = "Roles", summary = "Method to get Role by id")
  public ResponseEntity<GenericResponseDto<RoleDto>> findRole( @PathVariable("id")
  Integer id )
  {
    var result = this.roleFacade.find( id );

    HttpStatus status = HttpStatus.OK;
    if( result == null )
    {
      status = HttpStatus.NO_CONTENT;
    }
    return new ResponseEntity<>( result, status );
  }

  private String getRoleKey( Integer id )
  {
    String key = new StringBuilder().append( "Role.byRoleId:" ).append( id ).toString();
    return key;
  }

  /**
   * Method to create a Role
   * 
   * @param dto The Role object
   * @return
   */
  @JsonResponseInterceptor
  @PostMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  @Operation(tags = "Roles", summary = "Method to create a Role")
  public ResponseEntity<GenericResponseDto<RoleDto>> create( @RequestBody RoleDto dto )
  {
    var result = this.roleFacade.create( dto );
    return new ResponseEntity<>( result, HttpStatus.CREATED );
  }

  /**
   * Method to update a Role
   * 
   * @param id The Role Id
   * @param dto The Role object
   * @return
   */
  @JsonResponseInterceptor
  @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  @Operation(tags = "Roles", summary = "Method to update a Role")
  public ResponseEntity<GenericResponseDto<Boolean>> update( @PathVariable("id") Integer id, @RequestBody RoleDto dto )
  {
    dto.setId( id );
    var result = this.roleFacade.update( dto );

    

    return ResponseEntity.ok( result );
  }

  /**
   * Method to delete a Role
   * 
   * @param id The Role Id
   * @return
   */
  @JsonResponseInterceptor
  @DeleteMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  @Operation(tags = "Roles", summary = "Method to delete a Role")
  public ResponseEntity<GenericResponseDto<Boolean>> delete( @PathVariable("id") Integer id )
  {
    var result = this.roleFacade.delete( id );

    
    return ResponseEntity.ok( result );
  }

  /**
   * Ping
   * 
   * @return Pong
   */
  @JsonResponseInterceptor
  @GetMapping(path = "ping", produces = MediaType.APPLICATION_JSON_VALUE)
  @Operation(tags = "Roles", summary = "Ping")
  public ResponseEntity<GenericResponseDto<String>> ping( )
  {
    return ResponseEntity.ok( new GenericResponseDto<>("pong") );
  }
}
