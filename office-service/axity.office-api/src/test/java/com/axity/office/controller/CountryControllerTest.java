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

import com.axity.office.commons.dto.CountryDto;
import com.axity.office.commons.request.PaginatedRequestDto;
import com.axity.office.commons.response.GenericResponseDto;
import com.axity.office.commons.response.PaginatedResponseDto;
import com.axity.office.facade.CountryFacade;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import lombok.extern.slf4j.Slf4j;

/**
 * Country Controller Test class
 * 
 * @author username@axity.com
 */
@Slf4j
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class CountryControllerTest
{

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private CountryFacade countryFacade;
  
  
  
  @BeforeEach
  void setUp() {
    
  }

  /**
   * Test method for {@link com.axity.office.controller.CountryController#findCountrys(int, int)}.
   * 
   * @throws Exception
   */
  @Test
  void testFindCountrys() throws Exception
  {
    int pageSize = 20;

    var data = new ArrayList<CountryDto>();
    for( int i = 0; i < pageSize; i++ )
    {
      data.add( this.createCountry( i + 1 ) );
    }
    var paginated = new PaginatedResponseDto<CountryDto>( 0, pageSize, 50, data );
    when( this.countryFacade.findCountrys( any( PaginatedRequestDto.class ) ) ).thenReturn( paginated );

    MvcResult result = mockMvc.perform( MockMvcRequestBuilders.get( "/api/countries?limit=20&offset=0" ) )
        .andExpect( status().isOk() )
        .andExpect( jsonPath( "$.header.code" ).value( "0" ) )
        .andExpect( jsonPath( "$.page" ).value( "0" ) )
        .andExpect( jsonPath( "$.size" ).value( "20" ) )
        .andExpect( jsonPath( "$.data" ).isArray() )
        .andExpect( jsonPath( "$.data[0].id" ).value( 1 ) ).andReturn();

    assertNotNull( result );
  }

  /**
   * Test method for {@link com.axity.office.controller.CountryController#findCountry(java.lang.String)}.
   * 
   * @throws Exception
   */
  @Test
  void testFindCountry() throws Exception
  {
    var country = this.createCountry( 1 );
    var generic = new GenericResponseDto<>(country);
    when( this.countryFacade.find( anyInt() ) ).thenReturn( generic );
    
    MvcResult result = mockMvc.perform( MockMvcRequestBuilders.get( "/api/countries/1" ) )
        .andExpect( status().isOk() )
        .andExpect( jsonPath( "$.header.code" ).value( "0" ) )
        .andExpect( jsonPath( "$.body.id" ).value( 1 ) ).andReturn();

    assertNotNull( result );
  }

  /**
   * Test method for
   * {@link com.axity.office.controller.CountryController#create(com.axity.office.commons.dto.CountryDto)}.
   * 
   * @throws Exception
   */
  @Test
  void testCreate() throws Exception
  {
    var country = this.createCountry( 9 );
    var generic = new GenericResponseDto<>(country);
    when(this.countryFacade.create( any( CountryDto.class ) )).thenReturn( generic );
    
    Gson gson = new GsonBuilder().create();
    
    MvcResult result = mockMvc.perform( MockMvcRequestBuilders.post( "/api/countries"  )
            .content(gson.toJson(country))
            .accept( MediaType.APPLICATION_JSON )
            .contentType( MediaType.APPLICATION_JSON ))
        .andExpect( status().isCreated() )
        .andExpect( jsonPath( "$.header.code" ).value( "0" ) )
        .andExpect( jsonPath( "$.body.id" ).value( 9 ) ).andReturn();

    assertNotNull( result );
  }

  /**
   * Test method for
   * {@link com.axity.office.controller.CountryController#update(java.lang.String, com.axity.office.commons.dto.CountryDto)}.
   * 
   * @throws Exception
   */
  @Test
  void testUpdate() throws Exception
  {
    var country = this.createCountry( 1 );
    var generic = new GenericResponseDto<>(true);
    when(this.countryFacade.update( any( CountryDto.class ) )).thenReturn( generic );
    
    Gson gson = new GsonBuilder().create();
    
    MvcResult result = mockMvc.perform( MockMvcRequestBuilders.put( "/api/countries/1"  )
            .content(gson.toJson(country))
            .accept( MediaType.APPLICATION_JSON )
            .contentType( MediaType.APPLICATION_JSON ))
        .andExpect( status().isOk() )
        .andExpect( jsonPath( "$.header.code" ).value( "0" ) )
        .andExpect( jsonPath( "$.body" ).value( "true" ) ).andReturn();

    assertNotNull( result );
  }

  /**
   * Test method for {@link com.axity.office.controller.CountryController#delete(java.lang.String)}.
   * 
   * @throws Exception
   */
  @Test
  void testDelete() throws Exception
  {
    var generic = new GenericResponseDto<>(true);
    when(this.countryFacade.delete( anyInt() )).thenReturn( generic );
    
    MvcResult result = mockMvc.perform( MockMvcRequestBuilders.delete( "/api/countries/1"  )
            .accept( MediaType.APPLICATION_JSON ))
        .andExpect( status().isOk() )
        .andExpect( jsonPath( "$.header.code" ).value( "0" ) )
        .andExpect( jsonPath( "$.body" ).value( "true" ) ).andReturn();

    assertNotNull( result );
  }

  private CountryDto createCountry( int i )
  {
    var country = new CountryDto();
    country.setId( i );
    return country;
  }
}
