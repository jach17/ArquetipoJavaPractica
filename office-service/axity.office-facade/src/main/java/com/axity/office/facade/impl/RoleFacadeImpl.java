package com.axity.office.facade.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.axity.office.commons.dto.RoleDto;
import com.axity.office.commons.request.PaginatedRequestDto;
import com.axity.office.commons.response.GenericResponseDto;
import com.axity.office.commons.response.PaginatedResponseDto;
import com.axity.office.facade.RoleFacade;
import com.axity.office.service.RoleService;

/**
 * Class RoleFacadeImpl
 * @author username@axity.com
 */
@Service
@Transactional
public class RoleFacadeImpl implements RoleFacade
{
  @Autowired
  private RoleService roleService;
  /**
   * {@inheritDoc}
   */
  @Override
  public PaginatedResponseDto<RoleDto> findRoles( PaginatedRequestDto request )
  {
    return this.roleService.findRoles( request );
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public GenericResponseDto<RoleDto> find( Integer id )
  {
    return this.roleService.find( id );
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public GenericResponseDto<RoleDto> create( RoleDto dto )
  {
    return this.roleService.create( dto );
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public GenericResponseDto<Boolean> update( RoleDto dto )
  {
    return this.roleService.update( dto );
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public GenericResponseDto<Boolean> delete( Integer id )
  {
    return this.roleService.delete( id );
  }
}
