package com.google.wallet.objects.verticals;

import java.util.ArrayList;
import java.util.List;

import com.google.api.services.walletobjects.model.Barcode;
import com.google.api.services.walletobjects.model.Image;
import com.google.api.services.walletobjects.model.LatLongPoint;
import com.google.api.services.walletobjects.model.OfferClass;
import com.google.api.services.walletobjects.model.OfferObject;
import com.google.api.services.walletobjects.model.RenderSpec;
import com.google.api.services.walletobjects.model.TextModuleData;
import com.google.api.services.walletobjects.model.Uri;

/**
 * Generates an example Offer Class and Object
 *
 * @author pying
 *
 */
public class Offer {

  /**
   * Create an example Offer Object
   *
   * @param issuerId
   * @param classId
   * @param objectId
   * @return
   */
  public static OfferObject generateOfferObject(String issuerId,
      String classId, String objectId) {

    // Define Barcode
    Barcode barcode = new Barcode().setType("upcA").setValue("123456789012")
        .setAlternateText("12345").setLabel("User Id");

    // Define Uris
    List<Uri> uris = new ArrayList<Uri>();
    Uri uri1 = new Uri().setDescription("Help").setUri("http://wallet.google.com");
    Uri uri2 = new Uri().setDescription("Documentation").setUri("http://developers.google.com/commerce/wallet/objects");
    uris.add(uri1);
    uris.add(uri2);

    // Define Text Areas
    List<TextModuleData> textModulesData = new ArrayList<TextModuleData>();

    TextModuleData details = new TextModuleData().setHeader("Details").setBody("20% off one cup of coffee per visit");
    TextModuleData finePrint = new TextModuleData().setHeader("Fineprint").setBody("Limit one per customer per bearer token");

    textModulesData.add(details);
    textModulesData.add(finePrint);

    // Define Wallet Object
    OfferObject object = new OfferObject().setClassId(issuerId + "." + classId)
        .setId(issuerId + "." + objectId).setVersion(1L).setBarcode(barcode)
        .setState("active");

    return object;
  }

  /**
   * Create an example Offer Class
   *
   * @param issuerId
   * @param classId
   * @return
   */
  public static OfferClass generateOfferClass(String issuerId,
      String classId) {

    List<RenderSpec> renderSpec = new ArrayList<RenderSpec>();

    RenderSpec listRenderSpec = new RenderSpec().setViewName("g_list")
        .setTemplateFamily("1.offer1_list");
    RenderSpec expandedRenderSpec = new RenderSpec().setViewName("g_expanded")
        .setTemplateFamily("1.offer1_expanded");

    renderSpec.add(listRenderSpec);
    renderSpec.add(expandedRenderSpec);

    List<LatLongPoint> locations = new ArrayList<LatLongPoint>();
    locations.add(new LatLongPoint().setLatitude(37.442087).setLongitude(
        -122.161446));
    locations.add(new LatLongPoint().setLatitude(37.429379).setLongitude(
        -122.122730));
    locations.add(new LatLongPoint().setLatitude(37.333646).setLongitude(
        -121.884853));

    OfferClass wobClass = new OfferClass()
        .setId(issuerId + "." + classId)
        .setVersion(1L)
        .setIssuerName("Baconrista Coffee")
        .setTitle("20% off one cup of coffee")
        .setProvider("Baconrista Deals")
        .setTitleImage(
            new Image().setSourceUri(new Uri()
                .setUri("http://3.bp.blogspot.com/-AvC1agljv9Y/TirbDXOBIPI/AAAAAAAACK0/hR2gs5h2H6A/s1600/Bacon%2BWallpaper.png")))
        .setRenderSpecs(renderSpec).setRedemptionChannel("both")
        .setReviewStatus("underReview")
        .setLocations(locations).setAllowMultipleUsersPerObject(true);
    return wobClass;
  }
}
