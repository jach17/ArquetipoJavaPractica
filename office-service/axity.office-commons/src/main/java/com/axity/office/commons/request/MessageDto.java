package com.axity.office.commons.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Message Transfer object
 * 
 * @author username@axity.com
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageDto
{
  private String message;
  private String json;
}
