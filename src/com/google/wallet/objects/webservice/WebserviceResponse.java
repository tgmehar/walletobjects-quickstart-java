package com.google.wallet.objects.webservice;

import java.util.List;

/**
 * Bean for Webservice API represents the message response from your server to
 * Google
 *
 * @author pying
 *
 */
public class WebserviceResponse {
  //String message;
  List<String> invalidField;

  ResponseCode status;

  public enum ResponseCode{
    ERROR_INVALID_DATA_FORMAT,
    ERROR_DATA_ON_MERCHANT_RECORD_DIFFERENT,
    ERROR_INVALID_LINKING_ID,
    ERROR_PREEXISTING_ACCOUNT_REQUIRES_LINKING,
    ERROR_ACCOUNT_ALREADY_LINKED,
    SUCCESS,
    SUCCESS_ACCOUNT_ALREADY_CREATED,
    SUCCESS_ACCOUNT_ALREADY_LINKED,
  }

  public WebserviceResponse() {
  }

  public WebserviceResponse(ResponseCode status){
    this.status = status;
  }

  public WebserviceResponse(ResponseCode status, List<String> invalidFields) {
    this.status = status;
    this.invalidField = invalidFields;
  }

  /**
   * @return the invalidFieldNames
   */
  public List<String> getInvalidFieldNames() {
    return invalidField;
  }

  /**
   * @param invalidFieldNames the invalidFieldNames to set
   */
  public void setInvalidFieldNames(List<String> invalidFieldNames) {
    this.invalidField = invalidFieldNames;
  }

  /**
   * @return the status
   */
  public ResponseCode getStatus() {
    return status;
  }

  /**
   * @param status the status to set
   */
  public void setStatus(ResponseCode status) {
    this.status = status;
  }
}
