package com.google.wallet.objects.verticals;

import java.util.ArrayList;
import java.util.List;

import com.google.api.services.walletobjects.model.Barcode;
import com.google.api.services.walletobjects.model.GenericClass;
import com.google.api.services.walletobjects.model.GenericObject;
import com.google.api.services.walletobjects.model.Image;
import com.google.api.services.walletobjects.model.LatLongPoint;
import com.google.api.services.walletobjects.model.RenderSpec;
import com.google.api.services.walletobjects.model.TypedValue;
import com.google.api.services.walletobjects.model.Uri;
import com.google.api.services.walletobjects.model.WalletObjectMessage;

/**
 * Class to generate example Generic class and objects. The example is a sample
 * library card class which contains library specific information and a library
 * card object which contains information like the books the user has out and
 * when the next book is due.
 *
 * @author pying
 *
 */
public class Generic {

  /**
   * Generate the Generic example class
   *
   * @param issuerId
   * @param classId
   * @return
   */
  public static GenericClass generateGenericClass(String issuerId,
      String classId) {
    // Define general messages
    List<WalletObjectMessage> messages = new ArrayList<WalletObjectMessage>();
    WalletObjectMessage message = new WalletObjectMessage()
        .setHeader("Welcome")
        .setBody("Welcome to Banconrista Library!")
        .setImage(
            new Image().setSourceUri(new Uri()
                .setUri("https://ssl.gstatic.com/codesite/ph/images/search-48.gif")))
        .setActionUri(new Uri().setUri("http://baconrista.com"));
    messages.add(message);

    // Define Class IssuerData
    TypedValue issuerData = new TypedValue();
    TypedValue gExpanded = new TypedValue();

    TypedValue textModule = new TypedValue();
    textModule.put("header", new TypedValue().setString("Library Benefits"));
    textModule.put("body", new TypedValue()
        .setString("For every 3 books you read you get a free latte as part"
            + " of our reading program."));

    TypedValue linksModule = new TypedValue();

    linksModule.put("uri0", new TypedValue().setUri(new Uri().setUri(
        "http://www.example.com").setDescription("Example")));
    linksModule.put("uri1", new TypedValue().setUri(new Uri().setUri(
        "http://www.baconrista.com").setDescription("Library Details")));

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
    List<RenderSpec> renderSpec = new ArrayList<RenderSpec>();

    //Set renderSpecs
    RenderSpec listRenderSpec = new RenderSpec().setViewName("g_list")
        .setTemplateFamily("1.generic1_list");
    RenderSpec expandedRenderSpec = new RenderSpec().setViewName("g_expanded")
        .setTemplateFamily("1.generic1_expanded");
    renderSpec.add(listRenderSpec);
    renderSpec.add(expandedRenderSpec);

    // Define Geofence locations
    List<LatLongPoint> locations = new ArrayList<LatLongPoint>();
    locations.add(new LatLongPoint().setLatitude(37.422601).setLongitude(
        -122.085286));

    //Create the Class
    GenericClass wobClass = new GenericClass()
        .setId(issuerId + "." + classId)
        .setVersion(1L)
        .setIssuerName("Baconrista Library")
        .setTitle("Baconrista Library")
        .setDescription("Baconrista Library Card")
        .setHomepageUri(new Uri().setUri("https://www.example.com"))
        .setTitleImage(
            new Image().setSourceUri(new Uri()
                .setUri("http://www.google.com/landing/chrome/ugc/chrome-icon.jpg")))
        .setRenderSpecs(renderSpec).setMessages(messages)
        .setReviewStatus("draft").setAllowMultipleUsersPerObject(true)
        .setLocations(locations).setIssuerData(issuerData);

    return wobClass;
  }

  /**
   * Generate a Generic wallet object
   * @param issuerId
   * @param classId
   * @param objectId
   * @return
   */
  public static GenericObject generateGenericObject(String issuerId,
      String classId, String objectId) {

    //Define a barcode
    Barcode barcode = new Barcode().setType("qrCode").setValue("28343E3")
        .setAlternateText("12345").setLabel("Member Id");

    //Define IssuerData
    TypedValue objectIssuerData = new TypedValue();
    TypedValue objectGExpanded = new TypedValue();

    TypedValue titleModule = new TypedValue();
    titleModule.put(
        "row0",
        new TypedValue().set(
            "col0",
            new TypedValue().set("label",
                new TypedValue().setString("Library Member")).set("value",
                new TypedValue().setString("Joe Smith"))));

    TypedValue objectInfoModule = new TypedValue();

    TypedValue rowZero = new TypedValue();

    TypedValue colZero = new TypedValue();
    colZero.put("label", new TypedValue().setString("Local Store"));
    colZero.put("value", new TypedValue().setString("Mountain View"));

    TypedValue colOne = new TypedValue();
    colOne.put("label", new TypedValue().setString("Books Borrowed"));
    colOne.put("value", new TypedValue().setInt(2));

    rowZero.put("col0", colZero);
    rowZero.put("col1", colOne);

    objectInfoModule.put("row0", rowZero);
    objectGExpanded.put("infoModule", objectInfoModule);
    objectGExpanded.put("titleModule", titleModule);

    objectIssuerData.put("g_expanded", objectGExpanded);

    //Define Messages
    List<WalletObjectMessage> messages = new ArrayList<WalletObjectMessage>();
    WalletObjectMessage message = new WalletObjectMessage()
        .setHeader("Hi Joe")
        .setBody("Your book is due back in 2 days!")
        .setImage(
            new Image().setSourceUri(new Uri()
                .setUri("https://ssl.gstatic.com/codesite/ph/images/search-48.gif")))
        .setActionUri(new Uri().setUri("http://baconrista.com"));
    messages.add(message);

    //Create the Object
    GenericObject object = new GenericObject()
        .setClassId(issuerId + "." + classId).setId(issuerId + "." + objectId)
        .setVersion(1L).setState("active").setBarcode(barcode)
        .setMessages(messages).setIssuerData(objectIssuerData);

    return object;
  }
}
