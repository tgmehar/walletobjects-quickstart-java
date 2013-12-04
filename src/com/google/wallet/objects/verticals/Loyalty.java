package com.google.wallet.objects.verticals;

import java.util.ArrayList;
import java.util.List;
import com.google.api.services.walletobjects.model.Barcode;
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
    Barcode barcode = new Barcode().setType("qrCode")
    		.setValue("28343E3")
    		.setAlternateText("12345")
    		.setLabel("User Id");

    // Define Points
    LoyaltyPoints points = new LoyaltyPoints()
        .setLabel("Points")
        .setPointsType("points")
        .setBalance(new LoyaltyPointsBalance().setString("500"));

    // Define Text Module Data
    List<TextModuleData> textModulesData = new ArrayList<TextModuleData>();
    TextModuleData textModuleData = new TextModuleData()
    		.setHeader("Jane's Baconrista Rewards")
    		.setBody("You are 5 coffees away from receiving a free " +
                    "bacon fat latte");
    textModulesData.add(textModuleData);

    // Define Links Module Data
    List<Uri> uris = new ArrayList<Uri>();
    Uri uri1 = new Uri().setDescription("My Baconrista Account")
    		.setUri("http://www.baconrista.com/myaccount?id=1234567890");
    uris.add(uri1);
    LinksModuleData linksModuleData = new LinksModuleData().setUris(uris);

    // Define Info Module
    List<LabelValue> row0cols = new ArrayList<LabelValue>();
    LabelValue row0col0 = new LabelValue().setLabel("Member Name")
    		.setValue("Jane Doe");
    LabelValue row0col1 = new LabelValue().setLabel("Membership #")
    		.setValue("1234567890");
    row0cols.add(row0col0);
    row0cols.add(row0col1);

    List<LabelValue> row1cols = new ArrayList<LabelValue>();
    LabelValue row1col0 = new LabelValue().setLabel("Next Reward in")
    		.setValue("2 coffees");
    LabelValue row1col1 = new LabelValue().setLabel("Member Since")
    		.setValue("01/15/2013");
    row1cols.add(row1col0);
    row1cols.add(row1col1);

    List<LabelValueRow> rows = new ArrayList<LabelValueRow>();
    LabelValueRow row0 = new LabelValueRow().setHexBackgroundColor("#BBCCFC")
    		.setHexFontColor("#000000").setColumns(row0cols);
    LabelValueRow row1 = new LabelValueRow().setHexBackgroundColor("#FFFB00")
    		.setHexFontColor("#EDEDDD").setColumns(row1cols);

    rows.add(row0);
    rows.add(row1);

    InfoModuleData infoModuleData = new InfoModuleData().setHexFontColor("#FFFFFF")
    		.setHexBackgroundColor("#FC058C")
    		.setShowLastUpdateTime(true)
    		.setLabelValueRows(rows);

    // Define general messages
    List<WalletObjectMessage> messages = new ArrayList<WalletObjectMessage>();
    WalletObjectMessage message = new WalletObjectMessage()
        .setHeader("Jane, welcome to Banconrista Rewards!")
        .setBody("Thanks for joining our program. Show this message to " +
                "our barista for your first free coffee on us!")
        .setImage(
            new Image().setSourceUri(new Uri()
                .setUri("http://farm4.staticflickr.com/3723/11177041115_6e6a3b6f49_o.jpg")))
        .setActionUri(new Uri().setUri("http://baconrista.com"));
    messages.add(message);

    // Define Wallet Instance
    LoyaltyObject object = new LoyaltyObject()
        .setClassId(issuerId + "." + classId).setId(issuerId + "." + objectId)
        .setVersion(1L).setState("active").setBarcode(barcode).setInfoModuleData(infoModuleData)
        .setAccountName("Jane Doe").setTextModulesData(textModulesData)
        .setMessages(messages).setLinksModuleData(linksModuleData)
        .setAccountId("1234567890").setLoyaltyPoints(points);

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

    // Define rendering templates per view
    List<RenderSpec> renderSpec = new ArrayList<RenderSpec>();

    RenderSpec listRenderSpec = new RenderSpec().setViewName("g_list")
        .setTemplateFamily("1.loyaltyCard1_list");
    RenderSpec expandedRenderSpec = new RenderSpec().setViewName("g_expanded")
        .setTemplateFamily("1.loyaltyCard1_expanded");

    renderSpec.add(listRenderSpec);
    renderSpec.add(expandedRenderSpec);

    // Define Text Module Data
    List<TextModuleData> textModulesData = new ArrayList<TextModuleData>();

    TextModuleData textModuleData = new TextModuleData().setHeader("Rewards details")
    		.setBody("Welcome to Baconrista rewards.  For every 5 coffees purchased " +
                   "you'll receive a free bacon fat latte");
    textModulesData.add(textModuleData);

    // Define Links Module Data
    List<Uri> uris = new ArrayList<Uri>();
    Uri uri1 = new Uri().setDescription("Baconrista").setUri("http://www.baconrista.com/");
    uris.add(uri1);
    LinksModuleData linksModuleData = new LinksModuleData().setUris(uris);

    // Define Info Module
    InfoModuleData infoModuleData = new InfoModuleData().setHexFontColor("#FF3300")
    		.setHexBackgroundColor("#ABABAB")
    		.setShowLastUpdateTime(true);

    // Define general messages
    List<WalletObjectMessage> messages = new ArrayList<WalletObjectMessage>();
    WalletObjectMessage message = new WalletObjectMessage()
        .setHeader("Welcome to Banconrista Rewards!")
        .setBody("Featuring our new bacon donuts.")
        .setImage(
            new Image().setSourceUri(new Uri()
                .setUri("http://farm8.staticflickr.com/7302/11177240353_115daa5729_o.jpg")))
        .setActionUri(new Uri().setUri("http://baconrista.com"));
    messages.add(message);

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
                .setUri("http://farm8.staticflickr.com/7340/11177041185_a61a7f2139_o.jpg")))
        .setRewardsTierLabel("Tier").setRewardsTier("Gold")
        .setTextModulesData(textModulesData)
        .setLinksModuleData(linksModuleData)
        .setInfoModuleData(infoModuleData)
        .setAccountNameLabel("Member Name").setAccountIdLabel("Member Id")
        .setRenderSpecs(renderSpec).setMessages(messages)
        .setReviewStatus("underReview").setAllowMultipleUsersPerObject(true)
        .setLocations(locations);

    return wobClass;
  }
}
