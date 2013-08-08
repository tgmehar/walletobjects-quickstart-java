package com.google.wallet.objects.verticals;

import java.util.ArrayList;
import java.util.List;

import com.google.api.services.walletobjects.model.Barcode;
import com.google.api.services.walletobjects.model.DateTime;
import com.google.api.services.walletobjects.model.Image;
import com.google.api.services.walletobjects.model.LatLongPoint;
import com.google.api.services.walletobjects.model.LoyaltyClass;
import com.google.api.services.walletobjects.model.LoyaltyObject;
import com.google.api.services.walletobjects.model.LoyaltyPoints;
import com.google.api.services.walletobjects.model.LoyaltyPointsBalance;
import com.google.api.services.walletobjects.model.RenderSpec;
import com.google.api.services.walletobjects.model.TimeInterval;
import com.google.api.services.walletobjects.model.TypedValue;
import com.google.api.services.walletobjects.model.Uri;
import com.google.api.services.walletobjects.model.WalletObjectMessage;

public class Loyalty {

  /**
   * Generates a Loyalty Object
   *
   * @param issuerId
   * @param classId
   * @param objectId
   * @return loyaltyObject
   */
  public static LoyaltyObject generateLoyaltyObject(String issuerId,
      String classId, String objectId) {
    // Define Barcode
    Barcode barcode = new Barcode().setType("qrCode").setValue("28343E3")
        .setAlternateText("12345").setLabel("User Id");

    // Define Messages:
    List<WalletObjectMessage> messages = new ArrayList<WalletObjectMessage>();
    WalletObjectMessage message = new WalletObjectMessage()
        .setHeader("Hi Joe")
        .setBody(
            "Click here for a coupon to use at your local Baconrista shop!")
        .setImage(
            new Image().setSourceUri(new Uri()
                .setUri("https://ssl.gstatic.com/codesite/ph/images/search-48.gif")))
        .setActionUri(new Uri().setUri("http://baconrista.com"));
    messages.add(message);

    // Define Points
    LoyaltyPoints points = new LoyaltyPoints()
        .setLabel("Points")
        .setBalance(new LoyaltyPointsBalance().setString("500"))
        .setPointsValidInterval(
            new TimeInterval().setEnd(new DateTime()
                .setDate(com.google.api.client.util.DateTime
                    .parseRfc3339("2011-06-03T10:00:00.000-07:00"))))
        .setPointsType("rewards");

    // Define IssuerData
    TypedValue objectIssuerData = new TypedValue();

    TypedValue objectGExpanded = new TypedValue();
    TypedValue objectInfoModule = new TypedValue();

    TypedValue rowZero = new TypedValue();

    TypedValue colZero = new TypedValue();
    colZero.put("label", new TypedValue().setString("Local Store"));
    colZero.put("value", new TypedValue().setString("Mountain View"));

    TypedValue colOne = new TypedValue();
    colOne.put("label", new TypedValue().setString("Next Reward In"));
    colOne.put("value", new TypedValue().setInt(2));

    rowZero.put("col0", colZero);
    rowZero.put("col1", colOne);

    objectInfoModule.put("row0", rowZero);
    objectGExpanded.put("infoModule", objectInfoModule);

    objectIssuerData.put("g_expanded", objectGExpanded);

    // Define Wallet Instance
    LoyaltyObject object = new LoyaltyObject()
        .setClassId(issuerId + "." + classId).setId(issuerId + "." + objectId)
        .setVersion(1L).setState("active").setBarcode(barcode)
        .setMessages(messages).setAccountName("Joe Smith")
        .setAccountId("1234567890").setLoyaltyPoints(points)
        .setIssuerData(objectIssuerData);

    return object;
  }

  /**
   * Generates a Loyalty Class
   *
   * @param issuerId
   * @param classId
   * @return loyaltyClass
   */
  public static LoyaltyClass generateLoyaltyClass(String issuerId,
      String classId) {
    // Define general messages
    List<WalletObjectMessage> messages = new ArrayList<WalletObjectMessage>();
    WalletObjectMessage message = new WalletObjectMessage()
        .setHeader("Welcome")
        .setBody("Welcome to Banconrista Rewards!")
        .setImage(
            new Image().setSourceUri(new Uri()
                .setUri("https://ssl.gstatic.com/codesite/ph/images/search-48.gif")))
        .setActionUri(new Uri().setUri("http://baconrista.com"));
    messages.add(message);

    // Define Class IssuerData
    TypedValue issuerData = new TypedValue();

    TypedValue gExpanded = new TypedValue();
    TypedValue textModule = new TypedValue();
    textModule.put("header", new TypedValue().setString("Rewards details"));
    textModule.put("body", new TypedValue()
        .setString("Welcome to Baconrista rewards.  For every"
            + "5 coffees purchased you'll receive a free bacon fat latte"));

    TypedValue linksModule = new TypedValue();

    linksModule.put("uri0", new TypedValue().setUri(new Uri().setUri(
        "http://www.example.com").setDescription("Example")));

    TypedValue infoModule = new TypedValue();

    infoModule.put("fontColor", new TypedValue().setString("#FF3300"));
    infoModule.put("backgroundColor", new TypedValue().setString("#ABABAB"));
    infoModule.put(
        "row0",
        new TypedValue().set(
            "col0",
            new TypedValue()
                .set("label", new TypedValue().setString("Label 0")).set(
                    "value", new TypedValue().setString("Value 0"))).set(
            "col1",
            new TypedValue()
                .set("label", new TypedValue().setString("Label 1")).set(
                    "value", new TypedValue().setString("Value1"))));
    infoModule.put(
        "row1",
        new TypedValue().set(
            "col0",
            new TypedValue()
                .set("label", new TypedValue().setString("Label 0")).set(
                    "value", new TypedValue().setString("Value 0"))).set(
            "col1",
            new TypedValue()
                .set("label", new TypedValue().setString("Label 1")).set(
                    "value", new TypedValue().setString("Value1"))));

    gExpanded.put("textModule", textModule);
    gExpanded.put("linksModule", linksModule);
    gExpanded.put("infoModule", infoModule);

    issuerData.put("g_expanded", gExpanded);

    // Define rendering templates per view
    List<RenderSpec> renderSpec = new ArrayList<RenderSpec>();

    RenderSpec listRenderSpec = new RenderSpec().setViewName("g_list")
        .setTemplateFamily("1.loyaltyCard1_list");
    RenderSpec expandedRenderSpec = new RenderSpec().setViewName("g_expanded")
        .setTemplateFamily("1.loyaltyCard1_expanded");

    renderSpec.add(listRenderSpec);
    renderSpec.add(expandedRenderSpec);

    // Define Geofence locations
    List<LatLongPoint> locations = new ArrayList<LatLongPoint>();
    locations.add(new LatLongPoint().setLatitude(37.422601).setLongitude(
        -122.085286));

    // Create class
    LoyaltyClass wobClass = new LoyaltyClass()
        .setId(issuerId + "." + classId)
        .setVersion(1L)
        .setIssuerName("Baconrista")
        .setProgramName("Baconrista Rewards")
        .setHomepageUri(new Uri().setUri("https://www.example.com"))
        .setProgramLogo(
            new Image().setSourceUri(new Uri()
                .setUri("http://www.google.com/landing/chrome/ugc/chrome-icon.jpg")))
        .setRewardsTierLabel("Tier").setRewardsTier("Gold")
        .setAccountNameLabel("Member Name").setAccountIdLabel("Member Id")
        .setRenderSpecs(renderSpec).setMessages(messages)
        .setReviewStatus("draft").setAllowMultipleUsersPerObject(true)
        .setLocations(locations).setIssuerData(issuerData);

    return wobClass;
  }
}
