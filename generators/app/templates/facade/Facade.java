package com.[%= companylower %].[%= namelower %].facade;

import java.util.List;

import com.[%= companylower %].[%= namelower %].commons.dto.[%= namecamel %]Dto;
import com.[%= companylower %].[%= namelower %].commons.request.PaginatedRequestDto;
import com.[%= companylower %].[%= namelower %].commons.response.GenericResponseDto;
import com.[%= companylower %].[%= namelower %].commons.response.PaginatedResponseDto;

import graphql.schema.DataFetchingEnvironment;

/**
 * Interface [%= namecamel %]Facade
 * 
 * @author [%= username %]
 */
public interface [%= namecamel %]Facade
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
