package com.axity.office.controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.axity.office.commons.dto.UserDto;
import com.axity.office.commons.request.PaginatedRequestDto;
import com.axity.office.commons.response.GenericResponseDto;
import com.axity.office.commons.response.PaginatedResponseDto;
import com.axity.office.facade.UserFacade;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import lombok.extern.slf4j.Slf4j;

/**
 * User Controller Test class
 * 
 * @author username@axity.com
 */
@Slf4j
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class UserControllerTest
{

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private UserFacade userFacade;
  
  
  
  @BeforeEach
  void setUp() {
    
  }

  /**
   * Test method for {@link com.axity.office.controller.UserController#findUsers(int, int)}.
   * 
   * @throws Exception
   */
  @Test
  void testFindUsers() throws Exception
  {
    int pageSize = 20;

    var data = new ArrayList<UserDto>();
    for( int i = 0; i < pageSize; i++ )
    {
      data.add( this.createUser( i + 1 ) );
    }
    var paginated = new PaginatedResponseDto<UserDto>( 0, pageSize, 50, data );
    when( this.userFacade.findUsers( any( PaginatedRequestDto.class ) ) ).thenReturn( paginated );

    MvcResult result = mockMvc.perform( MockMvcRequestBuilders.get( "/api/users?limit=20&offset=0" ) )
        .andExpect( status().isOk() )
        .andExpect( jsonPath( "$.header.code" ).value( "0" ) )
        .andExpect( jsonPath( "$.page" ).value( "0" ) )
        .andExpect( jsonPath( "$.size" ).value( "20" ) )
        .andExpect( jsonPath( "$.data" ).isArray() )
        .andExpect( jsonPath( "$.data[0].id" ).value( 1 ) ).andReturn();

    assertNotNull( result );
  }

  /**
   * Test method for {@link com.axity.office.controller.UserController#findUser(java.lang.String)}.
   * 
   * @throws Exception
   */
  @Test
  void testFindUser() throws Exception
  {
    var user = this.createUser( 1 );
    var generic = new GenericResponseDto<>(user);
    when( this.userFacade.find( anyInt() ) ).thenReturn( generic );
    
    MvcResult result = mockMvc.perform( MockMvcRequestBuilders.get( "/api/users/1" ) )
        .andExpect( status().isOk() )
        .andExpect( jsonPath( "$.header.code" ).value( "0" ) )
        .andExpect( jsonPath( "$.body.id" ).value( 1 ) ).andReturn();

    assertNotNull( result );
  }

  /**
   * Test method for
   * {@link com.axity.office.controller.UserController#create(com.axity.office.commons.dto.UserDto)}.
   * 
   * @throws Exception
   */
  @Test
  void testCreate() throws Exception
  {
    var user = this.createUser( 9 );
    var generic = new GenericResponseDto<>(user);
    when(this.userFacade.create( any( UserDto.class ) )).thenReturn( generic );
    
    Gson gson = new GsonBuilder().create();
    
    MvcResult result = mockMvc.perform( MockMvcRequestBuilders.post( "/api/users"  )
            .content(gson.toJson(user))
            .accept( MediaType.APPLICATION_JSON )
            .contentType( MediaType.APPLICATION_JSON ))
        .andExpect( status().isCreated() )
        .andExpect( jsonPath( "$.header.code" ).value( "0" ) )
        .andExpect( jsonPath( "$.body.id" ).value( 9 ) ).andReturn();

    assertNotNull( result );
  }

  /**
   * Test method for
   * {@link com.axity.office.controller.UserController#update(java.lang.String, com.axity.office.commons.dto.UserDto)}.
   * 
   * @throws Exception
   */
  @Test
  void testUpdate() throws Exception
  {
    var user = this.createUser( 1 );
    var generic = new GenericResponseDto<>(true);
    when(this.userFacade.update( any( UserDto.class ) )).thenReturn( generic );
    
    Gson gson = new GsonBuilder().create();
    
    MvcResult result = mockMvc.perform( MockMvcRequestBuilders.put( "/api/users/1"  )
            .content(gson.toJson(user))
            .accept( MediaType.APPLICATION_JSON )
            .contentType( MediaType.APPLICATION_JSON ))
        .andExpect( status().isOk() )
        .andExpect( jsonPath( "$.header.code" ).value( "0" ) )
        .andExpect( jsonPath( "$.body" ).value( "true" ) ).andReturn();

    assertNotNull( result );
  }

  /**
   * Test method for {@link com.axity.office.controller.UserController#delete(java.lang.String)}.
   * 
   * @throws Exception
   */
  @Test
  void testDelete() throws Exception
  {
    var generic = new GenericResponseDto<>(true);
    when(this.userFacade.delete( anyInt() )).thenReturn( generic );
    
    MvcResult result = mockMvc.perform( MockMvcRequestBuilders.delete( "/api/users/1"  )
            .accept( MediaType.APPLICATION_JSON ))
        .andExpect( status().isOk() )
        .andExpect( jsonPath( "$.header.code" ).value( "0" ) )
        .andExpect( jsonPath( "$.body" ).value( "true" ) ).andReturn();

    assertNotNull( result );
  }

  private UserDto createUser( int i )
  {
    var user = new UserDto();
    user.setId( i );
    return user;
  }
}
