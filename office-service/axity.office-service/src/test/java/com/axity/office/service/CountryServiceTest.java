package com.axity.office.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.transaction.annotation.Transactional;

import com.axity.office.commons.dto.CountryDto;
import com.axity.office.commons.enums.ErrorCode;
import com.axity.office.commons.exception.BusinessException;
import com.axity.office.commons.request.PaginatedRequestDto;

/**
 * Class CountryServiceTest
 * 
 * @author username@axity.com
 */
@SpringBootTest(webEnvironment = WebEnvironment.NONE)
@Transactional
class CountryServiceTest
{
  private static final Logger LOG = LoggerFactory.getLogger( CountryServiceTest.class );

  @Autowired
  private CountryService countryService;

  /**
   * Method to validate the paginated search
   */
  @Test
  void testFindCountrys()
  {
    var request = new PaginatedRequestDto();
    request.setLimit( 5 );
    request.setOffset( 0 );
    var countrys = this.countryService.findCountrys( request );

    LOG.info( "Response: {}", countrys );

    assertNotNull( countrys );
    assertNotNull( countrys.getData() );
    assertFalse( countrys.getData().isEmpty() );
  }

  /**
   * Method to validate the search by id
   * 
   * @param countryId
   */
  @ParameterizedTest
  @ValueSource(ints = { 1 })
  void testFind( Integer countryId )
  {
    var country = this.countryService.find( countryId );
    assertNotNull( country );
    LOG.info( "Response: {}", country );
  }

  /**
   * Method to validate the search by id inexistent
   */
  @Test
  void testFind_NotExists()
  {
    var country = this.countryService.find( 999999 );
    assertNull( country );
  }

  /**
   * Test method for
   * {@link com.axity.office.service.impl.CountryServiceImpl#create(com.axity.office.commons.dto.CountryDto)}.
   */
  @Test
  @Disabled("TODO: Actualizar la prueba de acuerdo a la entidad")
  void testCreate()
  {
    var dto = new CountryDto();
    // Crear de acuerdo a la entidad

    var response = this.countryService.create( dto );
    assertNotNull( response );
    assertEquals( 0, response.getHeader().getCode() );
    assertNotNull( response.getBody() );

    this.countryService.delete( dto.getId() );
  }

  /**
   * Method to validate update
   */
  @Test
  @Disabled("TODO: Actualizar la prueba de acuerdo a la entidad")
  void testUpdate()
  {
    var country = this.countryService.find( 1 ).getBody();
    // TODO: actualizar de acuerdo a la entidad

    var response = this.countryService.update( country );

    assertNotNull( response );
    assertEquals( 0, response.getHeader().getCode() );
    assertTrue( response.getBody() );
    country = this.countryService.find( 1 ).getBody();

    // Verificar que se actualice el valor
  }

  /**
   * Method to validate an inexistent registry
   */
  @Test
  void testUpdate_NotFound()
  {
    var country = new CountryDto();
    country.setId(999999);
    var ex = assertThrows( BusinessException.class, () -> this.countryService.update( country ) );

    assertEquals( ErrorCode.OFFICE_NOT_FOUND.getCode(), ex.getCode() );
  }

  /**
   * Test method for {@link com.axity.office.service.impl.CountryServiceImpl#delete(java.lang.String)}.
   */
  @Test
  void testDeleteNotFound()
  {
    var ex = assertThrows( BusinessException.class, () -> this.countryService.delete( 999999 ) );
    assertEquals( ErrorCode.OFFICE_NOT_FOUND.getCode(), ex.getCode() );
  }
}
