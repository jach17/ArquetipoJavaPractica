package com.[%= companylower %].[%= namelower %].commons.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Message Transfer object
 * 
 * @author [%= username %]
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageDto
{
  private String message;
  private String json;
}
