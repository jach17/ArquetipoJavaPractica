package com.axity.office.service;

import java.util.List;

import com.axity.office.commons.dto.UserDto;
import com.axity.office.commons.request.PaginatedRequestDto;
import com.axity.office.commons.response.GenericResponseDto;
import com.axity.office.commons.response.PaginatedResponseDto;

/**
 * Interface UserService
 * 
 * @author username@axity.com
 */
public interface UserService
{

  /**
   * Method to get Users
   * 
   * @param request
   * @return
   */
  PaginatedResponseDto<UserDto> findUsers( PaginatedRequestDto request );

  /**
   * Method to get User by id
   * 
   * @param id
   * @return
   */
  GenericResponseDto<UserDto> find( Integer id );

  /**
   * Method to create a User
   * 
   * @param dto
   * @return
   */
  GenericResponseDto<UserDto> create( UserDto dto );

  /**
   * Method to update a User
   * 
   * @param dto
   * @return
   */
  GenericResponseDto<Boolean> update( UserDto dto );

  /**
   * Method to delete a User
   * 
   * @param id
   * @return
   */
  GenericResponseDto<Boolean> delete( Integer id );
}
