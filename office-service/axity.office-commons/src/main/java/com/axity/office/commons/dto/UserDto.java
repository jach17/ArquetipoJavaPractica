package com.axity.office.commons.dto;

import java.io.Serializable;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * User Transfer Object class
 * 
 * @author username@axity.com
 */
@Getter
@Setter
public class UserDto implements Serializable
{
  private static final long serialVersionUID = 1L;

  
  @Schema(required = true, description = "The id")
  private  Integer id;
  
  @Schema(required = true, description = "The username")
  private  String username;
  
  @Schema(required = true, description = "The email")
  @JsonIgnore
  private  String email;
  
  @Schema(required = true, description = "The name")
  private  String name;
  
  @Schema(required = true, description = "The lastname")
  private  String lastName;
  
  @Schema(required = true, description = "The roles")
  private  List<RoleDto> roles;
  

  

  /**
   * {@inheritDoc}
   */
  @Override
  public String toString()
  {
    Gson gson = new GsonBuilder().disableHtmlEscaping().create();
    return gson.toJson( this );
  }
}
