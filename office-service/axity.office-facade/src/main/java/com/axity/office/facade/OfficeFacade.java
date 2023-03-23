package com.axity.office.facade;

import java.util.List;

import com.axity.office.commons.dto.OfficeDto;
import com.axity.office.commons.request.PaginatedRequestDto;
import com.axity.office.commons.response.GenericResponseDto;
import com.axity.office.commons.response.PaginatedResponseDto;

import graphql.schema.DataFetchingEnvironment;

/**
 * Interface OfficeFacade
 * 
 * @author username@axity.com
 */
public interface OfficeFacade
{
  /**
   * Method to get Offices
   * 
   * @param request
   * @return
   */
  PaginatedResponseDto<OfficeDto> findOffices( PaginatedRequestDto request );

  /**
   * Method to get Office by id
   * 
   * @param id
   * @return
   */
  GenericResponseDto<OfficeDto> find( Integer id );

  /**
   * Method to create a Office
   * 
   * @param dto
   * @return
   */
  GenericResponseDto<OfficeDto> create( OfficeDto dto );

  /**
   * Method to update a Office
   * 
   * @param dto
   * @return
   */
  GenericResponseDto<Boolean> update( OfficeDto dto );

  /**
   * Method to delete a Office
   * 
   * @param id
   * @return
   */
  GenericResponseDto<Boolean> delete( Integer id );
}
