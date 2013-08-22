package com.google.wallet.objects.webservice;

/**
 * Bean for Webservice API represents the message response from your server to
 * Google
 *
 * @author pying
 *
 */
public class WebserviceResponse {
  String message;
  Response result;

  public enum Response{
    approved, rejected
  }
  public WebserviceResponse() {
  }

  public WebserviceResponse(String message, Response result) {
    setMessage(message);
    setResult(result);
  }

  public WebserviceResponse(Response result) {
    setResult(result);
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public Response getResult() {
    return result;
  }

  public void setResult(Response result) {
    this.result = result;
  }
}
