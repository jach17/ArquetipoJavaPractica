package com.axity.office.commons.enums;

/**
 * Error code enumeration
 * 
 * @author username@axity.com
 */
public enum ErrorCode {

  UNKNOWN_ERROR(0), REQUIRED_FIELD(1), EXCEEDS_MAX_LENGTH(2),

  // Validation errors
  OFFICE_ALREADY_EXISTS(100), OFFICE_NOT_FOUND(101),

  // Already exist error
  USERNAME_ALREADY_EXISTS(200),
  EMAIL_ALREADY_EXISTS(300), ROLE_NOT_FOUND(301), NOT_ROLE_SELECTED(302);

  private int code;

  private ErrorCode(int code) {
    this.code = code;
  }

  /**
   * @return the code
   */
  public int getCode() {
    return code;
  }

}
