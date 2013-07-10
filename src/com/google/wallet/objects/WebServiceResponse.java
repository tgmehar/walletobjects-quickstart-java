package com.google.wallet.objects;

public class WebServiceResponse {
  String message;
  String result;
  
  public WebServiceResponse(){}
 
  public WebServiceResponse(String message, String Result){
    setMessage(message);
    setResult(result);
  }
  
  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getResult() {
    return result;
  }

  public void setResult(String result) {
    this.result = result;
  }
}
