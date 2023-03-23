package com.axity.office.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

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

import com.axity.office.commons.dto.RoleDto;
import com.axity.office.commons.dto.UserDto;
import com.axity.office.commons.enums.ErrorCode;
import com.axity.office.commons.exception.BusinessException;
import com.axity.office.commons.request.PaginatedRequestDto;

/**
 * Class UserServiceTest
 * 
 * @author username@axity.com
 */
@SpringBootTest(webEnvironment = WebEnvironment.NONE)
@Transactional
class UserServiceTest {
  private static final Logger LOG = LoggerFactory.getLogger(UserServiceTest.class);

  @Autowired
  private UserService userService;

  /**
   * Method to validate the paginated search
   */
  @Test
  void testFindUsers() {
    var request = new PaginatedRequestDto();
    request.setLimit(5);
    request.setOffset(0);
    var users = this.userService.findUsers(request);

    LOG.info("Response: {}", users);

    assertNotNull(users);
    assertNotNull(users.getData());
    assertFalse(users.getData().isEmpty());
  }

  /**
   * Method to validate the search by id
   * 
   * @param userId
   */
  @ParameterizedTest
  @ValueSource(ints = { 1 })
  void testFind(Integer userId) {
    var user = this.userService.find(userId);
    assertNotNull(user);
    LOG.info("Response: {}", user);
  }

  /**
   * Method to validate the search by id inexistent
   */
  @Test
  void testFind_NotExists() {
    var user = this.userService.find(999999);
    assertNull(user);
  }

  /**
   * @param id
   * @return Instance of RoleDto
   */
  private RoleDto createRole(int id) {
    var role = new RoleDto();
    role.setId(id);
    return role;
  }

  /**
   * Test method for create user happy path
   * {@link com.axity.office.service.impl.UserServiceImpl#create(com.axity.office.commons.dto.UserDto)}.
   */
  @Test
  void testCreate() {
    // Data inicial
    var list = new ArrayList<RoleDto>();
    list.add(createRole(1));

    var dto = new UserDto();
    dto.setUsername("JonathanDev");
    dto.setEmail("jonathan.dev@axity.com");
    dto.setName("Jonathan");
    dto.setLastName("Aldana");
    dto.setRoles(list);

    // Llamada
    var response = this.userService.create(dto);

    // Validación
    assertNotNull(response);
    assertEquals("OK", response.getHeader().getMessage());
    assertNotNull(response.getBody());

    this.userService.delete(dto.getId());
  }

  /***** ESCENARIO UNO *****/

  /**
   * Test method for create user with one role
   * {@link com.axity.office.service.impl.UserServiceImpl#create(com.axity.office.commons.dto.UserDto)}.
   */
  @Test
  void testCreateWithOneRole() {
    // Data inicial
    var list = new ArrayList<RoleDto>();
    list.add(createRole(1));

    var dto = new UserDto();
    dto.setUsername("JonathanDev");
    dto.setEmail("jonathan.dev@axity.com");
    dto.setName("Jonathan");
    dto.setLastName("Aldana");
    dto.setRoles(list);

    // Llamada
    var response = this.userService.create(dto);

    // Validación
    assertNotNull(response);
    assertEquals("OK", response.getHeader().getMessage());
    assertNotNull(response.getBody());

    this.userService.delete(dto.getId());
  }

  /**
   * Test method for create user with many roles
   * {@link com.axity.office.service.impl.UserServiceImpl#create(com.axity.office.commons.dto.UserDto)}.
   */
  @Test
  void testCreateWithManyRole() {
    // Data inicial
    var list = new ArrayList<RoleDto>();
    list.add(createRole(1));
    list.add(createRole(2));
    list.add(createRole(3));

    var dto = new UserDto();
    dto.setUsername("JonathanDev");
    dto.setEmail("jonathan.dev@axity.com");
    dto.setName("Jonathan");
    dto.setLastName("Aldana");
    dto.setRoles(list);

    // Llamada
    var response = this.userService.create(dto);

    // Validación
    assertNotNull(response);
    assertEquals("OK", response.getHeader().getMessage());
    assertNotNull(response.getBody());

    this.userService.delete(dto.getId());
  }

  /***** ESCENARIO DOS Y TRES *****/

  /**
   * Test method for validate username already exist in bd
   * {@link com.axity.office.service.impl.UserServiceImpl#create(com.axity.office.commons.dto.UserDto)}.
   */
  @Test
  void testValidateUsernameAlreadyExist() {
    // Data inicial
    var list = new ArrayList<RoleDto>();
    list.add(createRole(1));

    var dto = new UserDto();
    dto.setUsername("denise.alford"); // Username alreadyexist in db
    dto.setEmail("jonathan.dev@axity.com");
    dto.setName("Jonathan");
    dto.setLastName("Aldana");
    dto.setRoles(list);

    // Llamada
    var response = this.userService.create(dto);

    // Validación
    assertNotNull(response);
    assertEquals(ErrorCode.USERNAME_ALREADY_EXISTS.getCode(), response.getHeader().getCode());
  }

  /**
   * Test method for validate email already exist in bd
   * {@link com.axity.office.service.impl.UserServiceImpl#create(com.axity.office.commons.dto.UserDto)}.
   */
  @Test
  void testValidateEmailAlreadyExist() {
    // Data inicial
    var list = new ArrayList<RoleDto>();
    list.add(createRole(1));

    var dto = new UserDto();
    dto.setUsername("JonathanDev");
    dto.setEmail("denise.alford@company.net"); // Email alreadyexist in db
    dto.setName("Jonathan");
    dto.setLastName("Aldana");
    dto.setRoles(list);

    // Llamada
    var response = this.userService.create(dto);

    // Validación
    assertNotNull(response);
    assertEquals(ErrorCode.EMAIL_ALREADY_EXISTS.getCode(), response.getHeader().getCode());
  }

  /***** ESCENARIO CUATRO *****/

  /**
   * Test method for validate if role selected does not exist
   * {@link com.axity.office.service.impl.UserServiceImpl#create(com.axity.office.commons.dto.UserDto)}.
   */
  @Test
  void testValidateRolesSelectedNotExist() {
    // Data inicial
    var list = new ArrayList<RoleDto>();
    list.add(createRole(99)); // Role selected does not exist in db

    var dto = new UserDto();
    dto.setUsername("JonathanDev");
    dto.setEmail("jonathan.dev@axity.com");
    dto.setName("Jonathan");
    dto.setLastName("Aldana");
    dto.setRoles(list);

    // Llamada
    var response = this.userService.create(dto);

    // Validación
    assertNotNull(response);
    assertEquals(ErrorCode.ROLE_NOT_FOUND.getCode(), response.getHeader().getCode());
  }

  /***** ESCENARIO CINCO *****/

  /**
   * Test method for validate if roles list is empty
   * {@link com.axity.office.service.impl.UserServiceImpl#create(com.axity.office.commons.dto.UserDto)}.
   */
  @Test
  void testValidateRolesEmpty() {
    // Data inicial
    var list = new ArrayList<RoleDto>();// Roles list empty does not exist in db

    var dto = new UserDto();
    dto.setUsername("JonathanDev");
    dto.setEmail("jonathan.dev@axity.com");
    dto.setName("Jonathan");
    dto.setLastName("Aldana");
    dto.setRoles(list);

    // Llamada
    var response = this.userService.create(dto);

    // Validación
    assertNotNull(response);
    assertEquals(ErrorCode.NOT_ROLE_SELECTED.getCode(), response.getHeader().getCode());
  }

  /**
   * Test method for validate if roles list is null
   * {@link com.axity.office.service.impl.UserServiceImpl#create(com.axity.office.commons.dto.UserDto)}.
   */
  @Test
  void testValidateRolesNull() {
    // Data inicial
    var list = new ArrayList<RoleDto>();// Roles list empty does not send to user

    var dto = new UserDto();
    dto.setUsername("JonathanDev");
    dto.setEmail("jonathan.dev@axity.com");
    dto.setName("Jonathan");
    dto.setLastName("Aldana");

    // Llamada
    var response = this.userService.create(dto);

    // Validación
    assertNotNull(response);
    assertEquals(ErrorCode.NOT_ROLE_SELECTED.getCode(), response.getHeader().getCode());
  }

  /**
   * Method to validate update
   */
  @Test
  @Disabled("TODO: Actualizar la prueba de acuerdo a la entidad")
  void testUpdate() {
    var user = this.userService.find(1).getBody();
    // TODO: actualizar de acuerdo a la entidad

    var response = this.userService.update(user);

    assertNotNull(response);
    assertEquals(0, response.getHeader().getCode());
    assertTrue(response.getBody());
    user = this.userService.find(1).getBody();

    // Verificar que se actualice el valor
  }

  /**
   * Method to validate an inexistent registry
   */
  @Test
  void testUpdate_NotFound() {
    var user = new UserDto();
    user.setId(999999);
    var ex = assertThrows(BusinessException.class, () -> this.userService.update(user));

    assertEquals(ErrorCode.OFFICE_NOT_FOUND.getCode(), ex.getCode());
  }

  /**
   * Test method for
   * {@link com.axity.office.service.impl.UserServiceImpl#delete(java.lang.String)}.
   */
  @Test
  void testDeleteNotFound() {
    var ex = assertThrows(BusinessException.class, () -> this.userService.delete(999999));
    assertEquals(ErrorCode.OFFICE_NOT_FOUND.getCode(), ex.getCode());
  }
}
