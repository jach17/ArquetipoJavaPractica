package com.axity.office.facade;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import com.axity.office.commons.dto.RoleDto;
import com.axity.office.commons.request.PaginatedRequestDto;
import com.axity.office.commons.response.GenericResponseDto;
import com.axity.office.commons.response.PaginatedResponseDto;
import com.axity.office.facade.impl.RoleFacadeImpl;
import com.axity.office.service.RoleService;

/**
 * Class RoleFacadeTest
 * 
 * @author username@axity.com
 */
class RoleFacadeTest
{
  private RoleFacade roleFacade;
  private RoleService roleService;

  @BeforeEach
  public void setUp()
  {
    this.roleFacade = new RoleFacadeImpl();

    this.roleService = mock( RoleService.class );
    ReflectionTestUtils.setField( this.roleFacade, "roleService", this.roleService );
  }

  /**
   * Test method for
   * {@link com.axity.office.facade.impl.RoleFacadeImpl#findRoles(com.axity.office.commons.request.PaginatedRequestDto)}.
   */
  @Test
  void testFindRoles()
  {
    int pageSize = 20;

    var data = new ArrayList<RoleDto>();
    for( int i = 0; i < pageSize; i++ )
    {
      data.add( this.createRole( i + 1 ) );
    }
    var paginated = new PaginatedResponseDto<RoleDto>( 0, pageSize, 50, data );
    when( this.roleService.findRoles( any( PaginatedRequestDto.class ) ) ).thenReturn( paginated );

    var result = this.roleFacade.findRoles( new PaginatedRequestDto( pageSize, 0 ) );
    assertNotNull( result );
  }

  /**
   * Test method for {@link com.axity.office.facade.impl.RoleFacadeImpl#find(java.lang.String)}.
   */
  @Test
  void testFind()
  {
    var response = new GenericResponseDto<RoleDto>( this.createRole( 1 ) );
    when( this.roleService.find( anyInt() ) ).thenReturn( response );

    var result = this.roleFacade.find( 1 );
    assertNotNull( result );
  }

  /**
   * Test method for
   * {@link com.axity.office.facade.impl.RoleFacadeImpl#create(com.axity.office.commons.dto.RoleDto)}.
   */
  @Test
  void testCreate()
  {
    var role = this.createRole( 8 );
    var response = new GenericResponseDto<>( role );
    when( this.roleService.create( any( RoleDto.class ) ) ).thenReturn( response );

    var result = this.roleFacade.create( role );
    assertNotNull( result );
  }

  /**
   * Test method for
   * {@link com.axity.office.facade.impl.RoleFacadeImpl#update(com.axity.office.commons.dto.RoleDto)}.
   */
  @Test
  void testUpdate()
  {
    var role = this.createRole( 1 );

    var response = new GenericResponseDto<>( true );
    when( this.roleService.update( any( RoleDto.class ) ) ).thenReturn( response );
    var result = this.roleFacade.update( role );
    assertNotNull( result );
  }

  /**
   * Test method for {@link com.axity.office.facade.impl.RoleFacadeImpl#delete(java.lang.String)}.
   */
  @Test
  void testDelete()
  {
    var response = new GenericResponseDto<>( true );
    when( this.roleService.delete( anyInt() ) ).thenReturn( response );

    var result = this.roleFacade.delete( 9 );
    assertNotNull( result );
  }

  private RoleDto createRole( int i )
  {
    var role = new RoleDto();
    role.setId( i );
    return role;
  }
}
