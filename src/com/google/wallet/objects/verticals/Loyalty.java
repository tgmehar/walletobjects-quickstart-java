package com.google.wallet.objects.verticals;

import java.util.ArrayList;
import java.util.List;

import com.google.api.services.walletobjects.model.Barcode;
import com.google.api.services.walletobjects.model.DateTime;
import com.google.api.services.walletobjects.model.Image;
import com.google.api.services.walletobjects.model.InfoModuleData;
import com.google.api.services.walletobjects.model.LabelValue;
import com.google.api.services.walletobjects.model.LabelValueRow;
import com.google.api.services.walletobjects.model.LatLongPoint;
import com.google.api.services.walletobjects.model.LinksModuleData;
import com.google.api.services.walletobjects.model.LoyaltyClass;
import com.google.api.services.walletobjects.model.LoyaltyObject;
import com.google.api.services.walletobjects.model.LoyaltyPoints;
import com.google.api.services.walletobjects.model.LoyaltyPointsBalance;
import com.google.api.services.walletobjects.model.RenderSpec;
import com.google.api.services.walletobjects.model.TextModuleData;
import com.google.api.services.walletobjects.model.TimeInterval;
import com.google.api.services.walletobjects.model.TypedValue;
import com.google.api.services.walletobjects.model.Uri;
import com.google.api.services.walletobjects.model.WalletObjectMessage;

/**
 * Class to generate example Loyalty class and objects
 *
 * @author pying
 *
 */
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
    Barcode barcode = new Barcode().setType("pdf417").setValue("12345678901234");

    // Define Points
    LoyaltyPoints points = new LoyaltyPoints()
        .setLabel("Balance")
        .setBalance(new LoyaltyPointsBalance().setString("$25.00"));
/*
    // Define Text Module Data
    List<TextModuleData> textModuleDatas = new ArrayList<TextModuleData>();

    TextModuleData textModuleData = new TextModuleData().setHeader("Text Header").setBody("Text Body");
    textModuleDatas.add(textModuleData);

    // Define Uris
    List<Uri> uris = new ArrayList<Uri>();
    Uri uri1 = new Uri().setDescription("uri 1 description").setUri("http://www.google.com");
    Uri uri2 = new Uri().setDescription("uri 2 description").setUri("http://developers.google.com");

    uris.add(uri1);
    uris.add(uri2);

    LinksModuleData linksModuleData = new LinksModuleData().setUris(uris);

    // Define Info Module
    List<LabelValue> row0cols = new ArrayList<LabelValue>();
    LabelValue row0col0 = new LabelValue().setLabel("Label0-0").setValue("value0-0");
    LabelValue row0col1 = new LabelValue().setLabel("label0-1").setValue("value0-1");
    row0cols.add(row0col0);
    row0cols.add(row0col1);

    List<LabelValue> row1cols = new ArrayList<LabelValue>();
    LabelValue row1col0 = new LabelValue().setLabel("Label1-0").setValue("value1-0");
    LabelValue row1col1 = new LabelValue().setLabel("label1-1").setValue("value01-1");
    row1cols.add(row1col0);
    row1cols.add(row1col1);

    List<LabelValueRow> rows = new ArrayList<LabelValueRow>();
    LabelValueRow row0 = new LabelValueRow().setHexBackgroundColor("#AEAEAE").setColumns(row0cols);
    LabelValueRow row1 = new LabelValueRow().setHexBackgroundColor("#AEAEAE").setColumns(row1cols);

    rows.add(row0);
    rows.add(row1);

    InfoModuleData infoModuleData = new InfoModuleData().setHexFontColor("#FF3300").setHexBackgroundColor("#ABABAB").setShowLastUpdateTime(true).setLabelValueRows(rows);

    // Define Wallet Instance
    LoyaltyObject object = new LoyaltyObject()
        .setClassId(issuerId + "." + classId).setId(issuerId + "." + objectId)
        .setVersion(1L).setState("active").setBarcode(barcode).setInfoModuleData(infoModuleData)
        .setMessages(messages).setAccountName("Joe Smith").setTextModuleDatas(textModuleDatas)
        .setAccountId("1234567890").setLoyaltyPoints(points).setLinksModuleData(linksModuleData);
*/

 // Define IssuerData
    TypedValue objectIssuerData = new TypedValue();

    TypedValue objectGExpanded = new TypedValue();
    TypedValue objectInfoModule = new TypedValue();

    TypedValue rowZero = new TypedValue();

    TypedValue colZero = new TypedValue();
    colZero.put("label", new TypedValue().setString("Next Reward In"));
    colZero.put("value", new TypedValue().setString("8 Points"));

    TypedValue colOne = new TypedValue();
    colOne.put("label", new TypedValue().setString("Next Level In"));
    colOne.put("value", new TypedValue().setString("0 Points"));

    rowZero.put("col0", colZero);
    rowZero.put("col1", colOne);

    TypedValue rowOne = new TypedValue();

    TypedValue rowOneColZero = new TypedValue();
    rowOneColZero.put("label", new TypedValue().setString("Next Reward In"));
    rowOneColZero.put("value", new TypedValue().setString("8 Points"));

    rowOne.put("col0", rowOneColZero);

    objectInfoModule.put("row0", rowZero);
    objectInfoModule.put("row1", rowOne);
    objectGExpanded.put("infoModule", objectInfoModule);

    objectIssuerData.put("g_expanded", objectGExpanded);

    // Define Wallet Instance
    LoyaltyObject object = new LoyaltyObject()
        .setClassId(issuerId + "." + classId).setId(issuerId + "." + objectId)
        .setVersion(1L).setState("active").setBarcode(barcode)
        .setAccountName("Caffeine Jones")
        .setAccountId("12345678901234").setLoyaltyPoints(points)
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
        .setReviewStatus("underReview").setAllowMultipleUsersPerObject(true)
        .setLocations(locations);

    return wobClass;
  }
}
