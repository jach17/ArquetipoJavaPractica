package com.axity.office.facade.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.axity.office.commons.dto.UserDto;
import com.axity.office.commons.request.PaginatedRequestDto;
import com.axity.office.commons.response.GenericResponseDto;
import com.axity.office.commons.response.PaginatedResponseDto;
import com.axity.office.facade.UserFacade;
import com.axity.office.service.UserService;

/**
 * Class UserFacadeImpl
 * @author username@axity.com
 */
@Service
@Transactional
public class UserFacadeImpl implements UserFacade
{
  @Autowired
  private UserService userService;
  /**
   * {@inheritDoc}
   */
  @Override
  public PaginatedResponseDto<UserDto> findUsers( PaginatedRequestDto request )
  {
    return this.userService.findUsers( request );
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public GenericResponseDto<UserDto> find( Integer id )
  {
    return this.userService.find( id );
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public GenericResponseDto<UserDto> create( UserDto dto )
  {
    return this.userService.create( dto );
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public GenericResponseDto<Boolean> update( UserDto dto )
  {
    return this.userService.update( dto );
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public GenericResponseDto<Boolean> delete( Integer id )
  {
    return this.userService.delete( id );
  }
}
