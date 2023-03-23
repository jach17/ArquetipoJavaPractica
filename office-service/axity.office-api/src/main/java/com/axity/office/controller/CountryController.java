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
import com.axity.office.commons.dto.CountryDto;
import com.axity.office.commons.request.PaginatedRequestDto;
import com.axity.office.commons.response.GenericResponseDto;
import com.axity.office.commons.response.PaginatedResponseDto;
import com.axity.office.facade.CountryFacade;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;

/**
 * Country controller class
 * 
 * @author username@axity.com
 *
 */
@RestController
@RequestMapping("/api/countries")
@CrossOrigin
@Slf4j
public class CountryController
{
  @Autowired
  private CountryFacade countryFacade;

  

  /***
   * Method to get Country
   * 
   * @param limit The limit
   * @param offset The offset
   * @return A paginated result of Country
   */
  @JsonResponseInterceptor
  @GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
  @Operation(tags = "Countrys", summary = "Method to get Countrys paginated")
  public ResponseEntity<PaginatedResponseDto<CountryDto>> findCountrys(
      @RequestParam(name = "limit", defaultValue = "50", required = false)
      int limit, @RequestParam(name = "offset", defaultValue = "0", required = false)
      int offset )
  {
    var result = this.countryFacade.findCountrys( new PaginatedRequestDto( limit, offset ) );
    return ResponseEntity.ok( result );
  }

  /**
   * Method to get Country by id
   * 
   * @param id The country Id
   * @return An registry of country or 204
   */
  @JsonResponseInterceptor
  @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  @Operation(tags = "Countrys", summary = "Method to get Country by id")
  public ResponseEntity<GenericResponseDto<CountryDto>> findCountry( @PathVariable("id")
  Integer id )
  {
    var result = this.countryFacade.find( id );

    HttpStatus status = HttpStatus.OK;
    if( result == null )
    {
      status = HttpStatus.NO_CONTENT;
    }
    return new ResponseEntity<>( result, status );
  }

  private String getCountryKey( Integer id )
  {
    String key = new StringBuilder().append( "Country.byCountryId:" ).append( id ).toString();
    return key;
  }

  /**
   * Method to create a Country
   * 
   * @param dto The Country object
   * @return
   */
  @JsonResponseInterceptor
  @PostMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  @Operation(tags = "Countrys", summary = "Method to create a Country")
  public ResponseEntity<GenericResponseDto<CountryDto>> create( @RequestBody CountryDto dto )
  {
    var result = this.countryFacade.create( dto );
    return new ResponseEntity<>( result, HttpStatus.CREATED );
  }

  /**
   * Method to update a Country
   * 
   * @param id The Country Id
   * @param dto The Country object
   * @return
   */
  @JsonResponseInterceptor
  @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  @Operation(tags = "Countrys", summary = "Method to update a Country")
  public ResponseEntity<GenericResponseDto<Boolean>> update( @PathVariable("id") Integer id, @RequestBody CountryDto dto )
  {
    dto.setId( id );
    var result = this.countryFacade.update( dto );

    

    return ResponseEntity.ok( result );
  }

  /**
   * Method to delete a Country
   * 
   * @param id The Country Id
   * @return
   */
  @JsonResponseInterceptor
  @DeleteMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  @Operation(tags = "Countrys", summary = "Method to delete a Country")
  public ResponseEntity<GenericResponseDto<Boolean>> delete( @PathVariable("id") Integer id )
  {
    var result = this.countryFacade.delete( id );

    
    return ResponseEntity.ok( result );
  }

  /**
   * Ping
   * 
   * @return Pong
   */
  @JsonResponseInterceptor
  @GetMapping(path = "ping", produces = MediaType.APPLICATION_JSON_VALUE)
  @Operation(tags = "Countrys", summary = "Ping")
  public ResponseEntity<GenericResponseDto<String>> ping( )
  {
    return ResponseEntity.ok( new GenericResponseDto<>("pong") );
  }
}
