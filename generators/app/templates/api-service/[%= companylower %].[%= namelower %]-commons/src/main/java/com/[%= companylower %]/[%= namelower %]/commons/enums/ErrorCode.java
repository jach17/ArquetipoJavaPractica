package com.[%= companylower %].[%= namelower %].commons.enums;

/**
 * Error code enumeration
 * 
 * @author [%= username %]
 */
public enum ErrorCode
{

  UNKNOWN_ERROR(0), REQUIRED_FIELD(1), EXCEEDS_MAX_LENGTH(2),

  // Validation errors
  [%= nameupper %]_ALREADY_EXISTS(100), [%= nameupper %]_NOT_FOUND(101);

  private int code;

  private ErrorCode( int code )
  {
    this.code = code;
  }

  /**
   * @return the code
   */
  public int getCode()
  {
    return code;
  }

}
