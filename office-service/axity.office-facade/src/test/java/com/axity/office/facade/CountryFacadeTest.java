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

import com.axity.office.commons.dto.CountryDto;
import com.axity.office.commons.request.PaginatedRequestDto;
import com.axity.office.commons.response.GenericResponseDto;
import com.axity.office.commons.response.PaginatedResponseDto;
import com.axity.office.facade.impl.CountryFacadeImpl;
import com.axity.office.service.CountryService;

/**
 * Class CountryFacadeTest
 * 
 * @author username@axity.com
 */
class CountryFacadeTest
{
  private CountryFacade countryFacade;
  private CountryService countryService;

  @BeforeEach
  public void setUp()
  {
    this.countryFacade = new CountryFacadeImpl();

    this.countryService = mock( CountryService.class );
    ReflectionTestUtils.setField( this.countryFacade, "countryService", this.countryService );
  }

  /**
   * Test method for
   * {@link com.axity.office.facade.impl.CountryFacadeImpl#findCountrys(com.axity.office.commons.request.PaginatedRequestDto)}.
   */
  @Test
  void testFindCountrys()
  {
    int pageSize = 20;

    var data = new ArrayList<CountryDto>();
    for( int i = 0; i < pageSize; i++ )
    {
      data.add( this.createCountry( i + 1 ) );
    }
    var paginated = new PaginatedResponseDto<CountryDto>( 0, pageSize, 50, data );
    when( this.countryService.findCountrys( any( PaginatedRequestDto.class ) ) ).thenReturn( paginated );

    var result = this.countryFacade.findCountrys( new PaginatedRequestDto( pageSize, 0 ) );
    assertNotNull( result );
  }

  /**
   * Test method for {@link com.axity.office.facade.impl.CountryFacadeImpl#find(java.lang.String)}.
   */
  @Test
  void testFind()
  {
    var response = new GenericResponseDto<CountryDto>( this.createCountry( 1 ) );
    when( this.countryService.find( anyInt() ) ).thenReturn( response );

    var result = this.countryFacade.find( 1 );
    assertNotNull( result );
  }

  /**
   * Test method for
   * {@link com.axity.office.facade.impl.CountryFacadeImpl#create(com.axity.office.commons.dto.CountryDto)}.
   */
  @Test
  void testCreate()
  {
    var country = this.createCountry( 8 );
    var response = new GenericResponseDto<>( country );
    when( this.countryService.create( any( CountryDto.class ) ) ).thenReturn( response );

    var result = this.countryFacade.create( country );
    assertNotNull( result );
  }

  /**
   * Test method for
   * {@link com.axity.office.facade.impl.CountryFacadeImpl#update(com.axity.office.commons.dto.CountryDto)}.
   */
  @Test
  void testUpdate()
  {
    var country = this.createCountry( 1 );

    var response = new GenericResponseDto<>( true );
    when( this.countryService.update( any( CountryDto.class ) ) ).thenReturn( response );
    var result = this.countryFacade.update( country );
    assertNotNull( result );
  }

  /**
   * Test method for {@link com.axity.office.facade.impl.CountryFacadeImpl#delete(java.lang.String)}.
   */
  @Test
  void testDelete()
  {
    var response = new GenericResponseDto<>( true );
    when( this.countryService.delete( anyInt() ) ).thenReturn( response );

    var result = this.countryFacade.delete( 9 );
    assertNotNull( result );
  }

  private CountryDto createCountry( int i )
  {
    var country = new CountryDto();
    country.setId( i );
    return country;
  }
}
