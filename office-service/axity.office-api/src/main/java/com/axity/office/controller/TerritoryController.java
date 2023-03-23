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
import com.axity.office.commons.dto.TerritoryDto;
import com.axity.office.commons.request.PaginatedRequestDto;
import com.axity.office.commons.response.GenericResponseDto;
import com.axity.office.commons.response.PaginatedResponseDto;
import com.axity.office.facade.TerritoryFacade;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;

/**
 * Territory controller class
 * 
 * @author username@axity.com
 *
 */
@RestController
@RequestMapping("/api/territories")
@CrossOrigin
@Slf4j
public class TerritoryController
{
  @Autowired
  private TerritoryFacade territoryFacade;

  

  /***
   * Method to get Territory
   * 
   * @param limit The limit
   * @param offset The offset
   * @return A paginated result of Territory
   */
  @JsonResponseInterceptor
  @GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
  @Operation(tags = "Territorys", summary = "Method to get Territorys paginated")
  public ResponseEntity<PaginatedResponseDto<TerritoryDto>> findTerritorys(
      @RequestParam(name = "limit", defaultValue = "50", required = false)
      int limit, @RequestParam(name = "offset", defaultValue = "0", required = false)
      int offset )
  {
    var result = this.territoryFacade.findTerritorys( new PaginatedRequestDto( limit, offset ) );
    return ResponseEntity.ok( result );
  }

  /**
   * Method to get Territory by id
   * 
   * @param id The territory Id
   * @return An registry of territory or 204
   */
  @JsonResponseInterceptor
  @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  @Operation(tags = "Territorys", summary = "Method to get Territory by id")
  public ResponseEntity<GenericResponseDto<TerritoryDto>> findTerritory( @PathVariable("id")
  Integer id )
  {
    var result = this.territoryFacade.find( id );

    HttpStatus status = HttpStatus.OK;
    if( result == null )
    {
      status = HttpStatus.NO_CONTENT;
    }
    return new ResponseEntity<>( result, status );
  }

  private String getTerritoryKey( Integer id )
  {
    String key = new StringBuilder().append( "Territory.byTerritoryId:" ).append( id ).toString();
    return key;
  }

  /**
   * Method to create a Territory
   * 
   * @param dto The Territory object
   * @return
   */
  @JsonResponseInterceptor
  @PostMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  @Operation(tags = "Territorys", summary = "Method to create a Territory")
  public ResponseEntity<GenericResponseDto<TerritoryDto>> create( @RequestBody TerritoryDto dto )
  {
    var result = this.territoryFacade.create( dto );
    return new ResponseEntity<>( result, HttpStatus.CREATED );
  }

  /**
   * Method to update a Territory
   * 
   * @param id The Territory Id
   * @param dto The Territory object
   * @return
   */
  @JsonResponseInterceptor
  @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  @Operation(tags = "Territorys", summary = "Method to update a Territory")
  public ResponseEntity<GenericResponseDto<Boolean>> update( @PathVariable("id") Integer id, @RequestBody TerritoryDto dto )
  {
    dto.setId( id );
    var result = this.territoryFacade.update( dto );

    

    return ResponseEntity.ok( result );
  }

  /**
   * Method to delete a Territory
   * 
   * @param id The Territory Id
   * @return
   */
  @JsonResponseInterceptor
  @DeleteMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  @Operation(tags = "Territorys", summary = "Method to delete a Territory")
  public ResponseEntity<GenericResponseDto<Boolean>> delete( @PathVariable("id") Integer id )
  {
    var result = this.territoryFacade.delete( id );

    
    return ResponseEntity.ok( result );
  }

  /**
   * Ping
   * 
   * @return Pong
   */
  @JsonResponseInterceptor
  @GetMapping(path = "ping", produces = MediaType.APPLICATION_JSON_VALUE)
  @Operation(tags = "Territorys", summary = "Ping")
  public ResponseEntity<GenericResponseDto<String>> ping( )
  {
    return ResponseEntity.ok( new GenericResponseDto<>("pong") );
  }
}
