package com.axity.office.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * Query Wrapper Dto class
 * 
 * @author username@axity.com
 */
@Getter
@Setter
public class QueryWrapperDto
{
  private String query;
  private Object variables = new Object();
}