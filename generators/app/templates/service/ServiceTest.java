package com.[%= companylower %].[%= namelower %].service;

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

import com.[%= companylower %].[%= namelower %].commons.dto.[%= namecamel %]Dto;
import com.[%= companylower %].[%= namelower %].commons.enums.ErrorCode;
import com.[%= companylower %].[%= namelower %].commons.exception.BusinessException;
import com.[%= companylower %].[%= namelower %].commons.request.PaginatedRequestDto;

/**
 * Class [%= namecamel %]ServiceTest
 * 
 * @author [%= username %]
 */
@SpringBootTest(webEnvironment = WebEnvironment.NONE)
@Transactional
class [%= namecamel %]ServiceTest
{
  private static final Logger LOG = LoggerFactory.getLogger( [%= namecamel %]ServiceTest.class );

  @Autowired
  private [%= namecamel %]Service [%= entityLower %]Service;

  /**
   * Method to validate the paginated search
   */
  @Test
  void testFind[%= namecamel %]s()
  {
    var request = new PaginatedRequestDto();
    request.setLimit( 5 );
    request.setOffset( 0 );
    var [%= entityLower %]s = this.[%= entityLower %]Service.find[%= namecamel %]s( request );

    LOG.info( "Response: {}", [%= entityLower %]s );

    assertNotNull( [%= entityLower %]s );
    assertNotNull( [%= entityLower %]s.getData() );
    assertFalse( [%= entityLower %]s.getData().isEmpty() );
  }

  /**
   * Method to validate the search by id
   * 
   * @param [%= entityLower %]Id
   */
  @ParameterizedTest
  @ValueSource(ints = { 1 })
  void testFind( Integer [%= entityLower %]Id )
  {
    var [%= entityLower %] = this.[%= entityLower %]Service.find( [%= entityLower %]Id );
    assertNotNull( [%= entityLower %] );
    LOG.info( "Response: {}", [%= entityLower %] );
  }

  /**
   * Method to validate the search by id inexistent
   */
  @Test
  void testFind_NotExists()
  {
    var [%= entityLower %] = this.[%= entityLower %]Service.find( 999999 );
    assertNull( [%= entityLower %] );
  }

  /**
   * Test method for
   * {@link com.[%= companylower %].[%= namelower %].service.impl.[%= namecamel %]ServiceImpl#create(com.[%= companylower %].[%= namelower %].commons.dto.[%= namecamel %]Dto)}.
   */
  @Test
  @Disabled("TODO: Actualizar la prueba de acuerdo a la entidad")
  void testCreate()
  {
    var dto = new [%= namecamel %]Dto();
    // Crear de acuerdo a la entidad

    var response = this.[%= entityLower %]Service.create( dto );
    assertNotNull( response );
    assertEquals( 0, response.getHeader().getCode() );
    assertNotNull( response.getBody() );

    this.[%= entityLower %]Service.delete( dto.getId() );
  }

  /**
   * Method to validate update
   */
  @Test
  @Disabled("TODO: Actualizar la prueba de acuerdo a la entidad")
  void testUpdate()
  {
    var [%= entityLower %] = this.[%= entityLower %]Service.find( 1 ).getBody();
    // TODO: actualizar de acuerdo a la entidad

    var response = this.[%= entityLower %]Service.update( [%= entityLower %] );

    assertNotNull( response );
    assertEquals( 0, response.getHeader().getCode() );
    assertTrue( response.getBody() );
    [%= entityLower %] = this.[%= entityLower %]Service.find( 1 ).getBody();

    // Verificar que se actualice el valor
  }

  /**
   * Method to validate an inexistent registry
   */
  @Test
  void testUpdate_NotFound()
  {
    var [%= entityLower %] = new [%= namecamel %]Dto();
    [%= entityLower %].setId(999999);
    var ex = assertThrows( BusinessException.class, () -> this.[%= entityLower %]Service.update( [%= entityLower %] ) );

    assertEquals( ErrorCode.[%= nameupper %]_NOT_FOUND.getCode(), ex.getCode() );
  }

  /**
   * Test method for {@link com.[%= companylower %].[%= namelower %].service.impl.[%= namecamel %]ServiceImpl#delete(java.lang.String)}.
   */
  @Test
  void testDeleteNotFound()
  {
    var ex = assertThrows( BusinessException.class, () -> this.[%= entityLower %]Service.delete( 999999 ) );
    assertEquals( ErrorCode.[%= nameupper %]_NOT_FOUND.getCode(), ex.getCode() );
  }
}
