package com.google.wallet.objects.utils;


import java.util.ArrayList;
import java.util.List;

import com.google.api.client.json.GenericJson;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.walletobjects.model.BoardingPassObject;
import com.google.api.services.walletobjects.model.GenericObject;
import com.google.api.services.walletobjects.model.LoyaltyObject;
import com.google.api.services.walletobjects.model.OfferObject;
import com.google.gson.Gson;
import com.google.wallet.objects.webservice.WebserviceResponse;

/**
 * Bean to represent the Wob payload object
 *
 * @author pying
 *
 */
public class WobPayload {
  private List<GenericJson> loyaltyObjects;
  private List<GenericJson> offerObjects;
  private List<GenericJson> genericObjects;
  private List<GenericJson> boardingPassObjects;

  private WebserviceResponse webserviceResponse;
  Gson gson = new Gson();
  JsonFactory jsonFactory = new GsonFactory();

  public WobPayload(){}

  public void addObject(GenericJson object){
    object.setFactory(jsonFactory);

    if (LoyaltyObject.class.isAssignableFrom(object.getClass())) {
      addLoyaltyObject(gson.fromJson(object.toString(), GenericJson.class));
    } else if (OfferObject.class.isAssignableFrom(object.getClass())) {
      addOfferObject(gson.fromJson(object.toString(), GenericJson.class));
    } else if (GenericObject.class.isAssignableFrom(object.getClass())) {
      addGenericObject(gson.fromJson(object.toString(), GenericJson.class));
    } else if (BoardingPassObject.class.isAssignableFrom(object.getClass())) {
      addBoardingPassObject(gson.fromJson(object.toString(),
          GenericJson.class));
    } else
      throw new IllegalArgumentException("Invalid Object type: "
          + object.getClass());
  }

  public void addLoyaltyObject(GenericJson object){
    if (loyaltyObjects == null){
      loyaltyObjects = new ArrayList<GenericJson>();
    }
    loyaltyObjects.add(object);
  }

  public List<GenericJson> getLoyaltyObjects() {
    return loyaltyObjects;
  }

  public void setLoyaltyObjects(List<GenericJson> loyaltyObject) {
    this.loyaltyObjects = loyaltyObject;
  }

  public void addOfferObject(GenericJson object){
    if (offerObjects == null){
      offerObjects = new ArrayList<GenericJson>();
    }
    offerObjects.add(object);
  }

  public List<GenericJson> getOfferObjects() {
    return offerObjects;
  }

  public void setOfferObjects(List<GenericJson> offerObject) {
    this.offerObjects = offerObject;
  }

  public void addGenericObject(GenericJson object){
    if (genericObjects == null){
      genericObjects = new ArrayList<GenericJson>();
    }
    genericObjects.add(object);
  }

  public List<GenericJson> getGenericObjects() {
    return genericObjects;
  }

  public void setGenericObjects(List<GenericJson> genericObject) {
    this.genericObjects = genericObject;
  }

  public WebserviceResponse getResponse() {
    return webserviceResponse;
  }

  public void setResponse(WebserviceResponse resp) {
    this.webserviceResponse = resp;
  }

  public List<GenericJson> getBoardingPassObjects() {
    return boardingPassObjects;
  }

  public void setBoardingPassObjects(List<GenericJson> boardingPassObjects) {
    this.boardingPassObjects = boardingPassObjects;
  }

  public void addBoardingPassObject(GenericJson object){
    if (boardingPassObjects == null){
      boardingPassObjects = new ArrayList<GenericJson>();
    }
    boardingPassObjects.add(object);
  }
}
