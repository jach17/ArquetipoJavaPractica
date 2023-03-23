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
import com.axity.office.commons.dto.OfficeDto;
import com.axity.office.commons.request.PaginatedRequestDto;
import com.axity.office.commons.response.GenericResponseDto;
import com.axity.office.commons.response.PaginatedResponseDto;
import com.axity.office.facade.OfficeFacade;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;

/**
 * Office controller class
 * 
 * @author username@axity.com
 *
 */
@RestController
@RequestMapping("/api/offices")
@CrossOrigin
@Slf4j
public class OfficeController
{
  @Autowired
  private OfficeFacade officeFacade;

  

  /***
   * Method to get Office
   * 
   * @param limit The limit
   * @param offset The offset
   * @return A paginated result of Office
   */
  @JsonResponseInterceptor
  @GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
  @Operation(tags = "Offices", summary = "Method to get Offices paginated")
  public ResponseEntity<PaginatedResponseDto<OfficeDto>> findOffices(
      @RequestParam(name = "limit", defaultValue = "50", required = false)
      int limit, @RequestParam(name = "offset", defaultValue = "0", required = false)
      int offset )
  {
    var result = this.officeFacade.findOffices( new PaginatedRequestDto( limit, offset ) );
    return ResponseEntity.ok( result );
  }

  /**
   * Method to get Office by id
   * 
   * @param id The office Id
   * @return An registry of office or 204
   */
  @JsonResponseInterceptor
  @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  @Operation(tags = "Offices", summary = "Method to get Office by id")
  public ResponseEntity<GenericResponseDto<OfficeDto>> findOffice( @PathVariable("id")
  Integer id )
  {
    var result = this.officeFacade.find( id );

    HttpStatus status = HttpStatus.OK;
    if( result == null )
    {
      status = HttpStatus.NO_CONTENT;
    }
    return new ResponseEntity<>( result, status );
  }

  private String getOfficeKey( Integer id )
  {
    String key = new StringBuilder().append( "Office.byOfficeId:" ).append( id ).toString();
    return key;
  }

  /**
   * Method to create a Office
   * 
   * @param dto The Office object
   * @return
   */
  @JsonResponseInterceptor
  @PostMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  @Operation(tags = "Offices", summary = "Method to create a Office")
  public ResponseEntity<GenericResponseDto<OfficeDto>> create( @RequestBody OfficeDto dto )
  {
    var result = this.officeFacade.create( dto );
    return new ResponseEntity<>( result, HttpStatus.CREATED );
  }

  /**
   * Method to update a Office
   * 
   * @param id The Office Id
   * @param dto The Office object
   * @return
   */
  @JsonResponseInterceptor
  @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  @Operation(tags = "Offices", summary = "Method to update a Office")
  public ResponseEntity<GenericResponseDto<Boolean>> update( @PathVariable("id") Integer id, @RequestBody OfficeDto dto )
  {
    dto.setId( id );
    var result = this.officeFacade.update( dto );

    

    return ResponseEntity.ok( result );
  }

  /**
   * Method to delete a Office
   * 
   * @param id The Office Id
   * @return
   */
  @JsonResponseInterceptor
  @DeleteMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  @Operation(tags = "Offices", summary = "Method to delete a Office")
  public ResponseEntity<GenericResponseDto<Boolean>> delete( @PathVariable("id") Integer id )
  {
    var result = this.officeFacade.delete( id );

    
    return ResponseEntity.ok( result );
  }

  /**
   * Ping
   * 
   * @return Pong
   */
  @JsonResponseInterceptor
  @GetMapping(path = "ping", produces = MediaType.APPLICATION_JSON_VALUE)
  @Operation(tags = "Offices", summary = "Ping")
  public ResponseEntity<GenericResponseDto<String>> ping( )
  {
    return ResponseEntity.ok( new GenericResponseDto<>("pong") );
  }
}
