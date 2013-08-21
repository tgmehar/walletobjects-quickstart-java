package com.google.wallet.objects.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.GeneralSecurityException;
import java.security.KeyStoreException;

import javax.servlet.ServletContext;
import javax.servlet.http.*;

import com.google.api.client.json.GenericJson;
import com.google.api.services.walletobjects.Walletobjects;
import com.google.api.services.walletobjects.model.GenericClass;
import com.google.api.services.walletobjects.model.LoyaltyClass;
import com.google.api.services.walletobjects.model.OfferClass;
import com.google.wallet.objects.utils.WobCredentials;
import com.google.wallet.objects.utils.WobUtils;
import com.google.wallet.objects.verticals.Generic;
import com.google.wallet.objects.verticals.Loyalty;
import com.google.wallet.objects.verticals.Offer;

/**
 * This servlet handles requests to insert new Wallet Classes. It parses the
 * type URL paramter to determine the type and generates the respective Class to
 * insert. The valid types are: loyalty, offers, generic and boarding pass( if
 * you comment that section out).
 *
 * @author pying
 *
 */
@SuppressWarnings("serial")
public class WobInsertServlet extends HttpServlet {

  public void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws IOException {

    // Access credentials from web.xml
    ServletContext context = getServletContext();

    // Create the WobCredential object to use
    WobCredentials credentials = new WobCredentials(
        context.getInitParameter("ServiceAccountId"),
        context.getInitParameter("ServiceAccountPrivateKey"),
        context.getInitParameter("ApplicationName"),
        context.getInitParameter("IssuerId"));

    // Create WobUilts object to handle the heavy lifting
    WobUtils utils = null;
    Walletobjects client = null;
    try {
      utils = new WobUtils(credentials);
      client = utils.getClient();
    } catch (KeyStoreException e) {
      // Add code to catch errors
      e.printStackTrace();
    } catch (IOException e) {
      // Add code to catch errors
      e.printStackTrace();
    } catch (GeneralSecurityException e) {
      // Add code to catch errors
      e.printStackTrace();
    }

    // Get request type
    String type = req.getParameter("type");

    GenericJson response = null;

    // Create and insert type
    if (type.equals("loyalty")) {
      LoyaltyClass loyaltyClass = Loyalty.generateLoyaltyClass(
          utils.getIssuerId(), "LoyaltyClass");
      response = client.loyaltyclass().insert(loyaltyClass).execute();
    } else if (type.equals("offer")) {
      OfferClass offerClass = Offer.generateOfferClass(utils.getIssuerId(),
          "OfferClass");
      response = client.offerclass().insert(offerClass).execute();
    } else if (type.equals("generic")) {
      GenericClass genericClass = Generic.generateGenericClass(
          utils.getIssuerId(), "GenericClass");
      response = client.genericclass().insert(genericClass).execute();
    }
    // For server side insertion of Boarding passes instead of using Save to
    // Wallet
    /*
     * else if (type.equals("boardingpass")) { BoardingPassClass boardingPass =
     * BoardingPass.generateBoardingPassClass( utils.getIssuerId(),
     * "BoardingPassClass"); response =
     * client.boardingpassclass().insert(boardingPass).execute(); }
     */

    // Respond to request with class json
    PrintWriter out = resp.getWriter();
    out.write(response.toString());
  }
}
