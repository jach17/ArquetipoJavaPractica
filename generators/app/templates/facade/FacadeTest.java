package com.[%= companylower %].[%= namelower %].facade;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import com.[%= companylower %].[%= namelower %].commons.dto.[%= namecamel %]Dto;
import com.[%= companylower %].[%= namelower %].commons.request.PaginatedRequestDto;
import com.[%= companylower %].[%= namelower %].commons.response.GenericResponseDto;
import com.[%= companylower %].[%= namelower %].commons.response.PaginatedResponseDto;
import com.[%= companylower %].[%= namelower %].facade.impl.[%= namecamel %]FacadeImpl;
import com.[%= companylower %].[%= namelower %].service.[%= namecamel %]Service;

/**
 * Class [%= namecamel %]FacadeTest
 * 
 * @author [%= username %]
 */
class [%= namecamel %]FacadeTest
{
  private [%= namecamel %]Facade [%= entityLower %]Facade;
  private [%= namecamel %]Service [%= entityLower %]Service;

  @BeforeEach
  public void setUp()
  {
    this.[%= entityLower %]Facade = new [%= namecamel %]FacadeImpl();

    this.[%= entityLower %]Service = mock( [%= namecamel %]Service.class );
    ReflectionTestUtils.setField( this.[%= entityLower %]Facade, "[%= entityLower %]Service", this.[%= entityLower %]Service );
  }

  /**
   * Test method for
   * {@link com.[%= companylower %].[%= namelower %].facade.impl.[%= namecamel %]FacadeImpl#find[%= namecamel %]s(com.[%= companylower %].[%= namelower %].commons.request.PaginatedRequestDto)}.
   */
  @Test
  void testFind[%= namecamel %]s()
  {
    int pageSize = 20;

    var data = new ArrayList<[%= namecamel %]Dto>();
    for( int i = 0; i < pageSize; i++ )
    {
      data.add( this.create[%= namecamel %]( i + 1 ) );
    }
    var paginated = new PaginatedResponseDto<[%= namecamel %]Dto>( 0, pageSize, 50, data );
    when( this.[%= entityLower %]Service.find[%= namecamel %]s( any( PaginatedRequestDto.class ) ) ).thenReturn( paginated );

    var result = this.[%= entityLower %]Facade.find[%= namecamel %]s( new PaginatedRequestDto( pageSize, 0 ) );
    assertNotNull( result );
  }

  /**
   * Test method for {@link com.[%= companylower %].[%= namelower %].facade.impl.[%= namecamel %]FacadeImpl#find(java.lang.String)}.
   */
  @Test
  void testFind()
  {
    var response = new GenericResponseDto<[%= namecamel %]Dto>( this.create[%= namecamel %]( 1 ) );
    when( this.[%= entityLower %]Service.find( anyInt() ) ).thenReturn( response );

    var result = this.[%= entityLower %]Facade.find( 1 );
    assertNotNull( result );
  }

  /**
   * Test method for
   * {@link com.[%= companylower %].[%= namelower %].facade.impl.[%= namecamel %]FacadeImpl#create(com.[%= companylower %].[%= namelower %].commons.dto.[%= namecamel %]Dto)}.
   */
  @Test
  void testCreate()
  {
    var [%= entityLower %] = this.create[%= namecamel %]( 8 );
    var response = new GenericResponseDto<>( [%= entityLower %] );
    when( this.[%= entityLower %]Service.create( any( [%= namecamel %]Dto.class ) ) ).thenReturn( response );

    var result = this.[%= entityLower %]Facade.create( [%= entityLower %] );
    assertNotNull( result );
  }

  /**
   * Test method for
   * {@link com.[%= companylower %].[%= namelower %].facade.impl.[%= namecamel %]FacadeImpl#update(com.[%= companylower %].[%= namelower %].commons.dto.[%= namecamel %]Dto)}.
   */
  @Test
  void testUpdate()
  {
    var [%= entityLower %] = this.create[%= namecamel %]( 1 );

    var response = new GenericResponseDto<>( true );
    when( this.[%= entityLower %]Service.update( any( [%= namecamel %]Dto.class ) ) ).thenReturn( response );
    var result = this.[%= entityLower %]Facade.update( [%= entityLower %] );
    assertNotNull( result );
  }

  /**
   * Test method for {@link com.[%= companylower %].[%= namelower %].facade.impl.[%= namecamel %]FacadeImpl#delete(java.lang.String)}.
   */
  @Test
  void testDelete()
  {
    var response = new GenericResponseDto<>( true );
    when( this.[%= entityLower %]Service.delete( anyInt() ) ).thenReturn( response );

    var result = this.[%= entityLower %]Facade.delete( 9 );
    assertNotNull( result );
  }

  private [%= namecamel %]Dto create[%= namecamel %]( int i )
  {
    var [%= entityLower %] = new [%= namecamel %]Dto();
    [%= entityLower %].setId( i );
    return [%= entityLower %];
  }
}
