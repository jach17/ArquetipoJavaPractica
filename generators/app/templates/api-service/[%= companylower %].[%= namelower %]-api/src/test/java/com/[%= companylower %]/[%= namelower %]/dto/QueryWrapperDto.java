package com.[%= companylower %].[%= namelower %].dto;

import lombok.Getter;
import lombok.Setter;

/**
 * Query Wrapper Dto class
 * 
 * @author [%= username %]
 */
@Getter
@Setter
public class QueryWrapperDto
{
  private String query;
  private Object variables = new Object();
}