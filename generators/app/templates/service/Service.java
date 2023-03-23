package com.[%= companylower %].[%= namelower %].service;

import java.util.List;

import com.[%= companylower %].[%= namelower %].commons.dto.[%= namecamel %]Dto;
import com.[%= companylower %].[%= namelower %].commons.request.PaginatedRequestDto;
import com.[%= companylower %].[%= namelower %].commons.response.GenericResponseDto;
import com.[%= companylower %].[%= namelower %].commons.response.PaginatedResponseDto;

/**
 * Interface [%= namecamel %]Service
 * 
 * @author [%= username %]
 */
public interface [%= namecamel %]Service
{

  /**
   * Method to get [%= namecamel %]s
   * 
   * @param request
   * @return
   */
  PaginatedResponseDto<[%= namecamel %]Dto> find[%= namecamel %]s( PaginatedRequestDto request );

  /**
   * Method to get [%= namecamel %] by id
   * 
   * @param id
   * @return
   */
  GenericResponseDto<[%= namecamel %]Dto> find( Integer id );

  /**
   * Method to create a [%= namecamel %]
   * 
   * @param dto
   * @return
   */
  GenericResponseDto<[%= namecamel %]Dto> create( [%= namecamel %]Dto dto );

  /**
   * Method to update a [%= namecamel %]
   * 
   * @param dto
   * @return
   */
  GenericResponseDto<Boolean> update( [%= namecamel %]Dto dto );

  /**
   * Method to delete a [%= namecamel %]
   * 
   * @param id
   * @return
   */
  GenericResponseDto<Boolean> delete( Integer id );
}
