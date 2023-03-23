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

import com.axity.office.commons.dto.OfficeDto;
import com.axity.office.commons.enums.ErrorCode;
import com.axity.office.commons.exception.BusinessException;
import com.axity.office.commons.request.PaginatedRequestDto;

/**
 * Class OfficeServiceTest
 * 
 * @author username@axity.com
 */
@SpringBootTest(webEnvironment = WebEnvironment.NONE)
@Transactional
class OfficeServiceTest
{
  private static final Logger LOG = LoggerFactory.getLogger( OfficeServiceTest.class );

  @Autowired
  private OfficeService officeService;

  /**
   * Method to validate the paginated search
   */
  @Test
  void testFindOffices()
  {
    var request = new PaginatedRequestDto();
    request.setLimit( 5 );
    request.setOffset( 0 );
    var offices = this.officeService.findOffices( request );

    LOG.info( "Response: {}", offices );

    assertNotNull( offices );
    assertNotNull( offices.getData() );
    assertFalse( offices.getData().isEmpty() );
  }

  /**
   * Method to validate the search by id
   * 
   * @param officeId
   */
  @ParameterizedTest
  @ValueSource(ints = { 1 })
  void testFind( Integer officeId )
  {
    var office = this.officeService.find( officeId );
    assertNotNull( office );
    LOG.info( "Response: {}", office );
  }

  /**
   * Method to validate the search by id inexistent
   */
  @Test
  void testFind_NotExists()
  {
    var office = this.officeService.find( 999999 );
    assertNull( office );
  }

  /**
   * Test method for
   * {@link com.axity.office.service.impl.OfficeServiceImpl#create(com.axity.office.commons.dto.OfficeDto)}.
   */
  @Test
  @Disabled("TODO: Actualizar la prueba de acuerdo a la entidad")
  void testCreate()
  {
    var dto = new OfficeDto();
    // Crear de acuerdo a la entidad

    var response = this.officeService.create( dto );
    assertNotNull( response );
    assertEquals( 0, response.getHeader().getCode() );
    assertNotNull( response.getBody() );

    this.officeService.delete( dto.getId() );
  }

  /**
   * Method to validate update
   */
  @Test
  @Disabled("TODO: Actualizar la prueba de acuerdo a la entidad")
  void testUpdate()
  {
    var office = this.officeService.find( 1 ).getBody();
    // TODO: actualizar de acuerdo a la entidad

    var response = this.officeService.update( office );

    assertNotNull( response );
    assertEquals( 0, response.getHeader().getCode() );
    assertTrue( response.getBody() );
    office = this.officeService.find( 1 ).getBody();

    // Verificar que se actualice el valor
  }

  /**
   * Method to validate an inexistent registry
   */
  @Test
  void testUpdate_NotFound()
  {
    var office = new OfficeDto();
    office.setId(999999);
    var ex = assertThrows( BusinessException.class, () -> this.officeService.update( office ) );

    assertEquals( ErrorCode.OFFICE_NOT_FOUND.getCode(), ex.getCode() );
  }

  /**
   * Test method for {@link com.axity.office.service.impl.OfficeServiceImpl#delete(java.lang.String)}.
   */
  @Test
  void testDeleteNotFound()
  {
    var ex = assertThrows( BusinessException.class, () -> this.officeService.delete( 999999 ) );
    assertEquals( ErrorCode.OFFICE_NOT_FOUND.getCode(), ex.getCode() );
  }
}
