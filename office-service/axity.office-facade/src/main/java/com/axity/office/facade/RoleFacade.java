package com.axity.office.facade;

import java.util.List;

import com.axity.office.commons.dto.RoleDto;
import com.axity.office.commons.request.PaginatedRequestDto;
import com.axity.office.commons.response.GenericResponseDto;
import com.axity.office.commons.response.PaginatedResponseDto;

import graphql.schema.DataFetchingEnvironment;

/**
 * Interface RoleFacade
 * 
 * @author username@axity.com
 */
public interface RoleFacade
{
  /**
   * Method to get Roles
   * 
   * @param request
   * @return
   */
  PaginatedResponseDto<RoleDto> findRoles( PaginatedRequestDto request );

  /**
   * Method to get Role by id
   * 
   * @param id
   * @return
   */
  GenericResponseDto<RoleDto> find( Integer id );

  /**
   * Method to create a Role
   * 
   * @param dto
   * @return
   */
  GenericResponseDto<RoleDto> create( RoleDto dto );

  /**
   * Method to update a Role
   * 
   * @param dto
   * @return
   */
  GenericResponseDto<Boolean> update( RoleDto dto );

  /**
   * Method to delete a Role
   * 
   * @param id
   * @return
   */
  GenericResponseDto<Boolean> delete( Integer id );
}
