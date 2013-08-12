package com.google.wallet.objects.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.GeneralSecurityException;
import java.security.KeyStoreException;

import javax.servlet.ServletContext;
import javax.servlet.http.*;

import com.google.api.client.json.GenericJson;
import com.google.api.services.walletobjects.Walletobjects;
import com.google.api.services.walletobjects.model.BoardingPassClass;
import com.google.api.services.walletobjects.model.GenericClass;
import com.google.api.services.walletobjects.model.LoyaltyClass;
import com.google.api.services.walletobjects.model.OfferClass;
import com.google.wallet.objects.utils.WobCredentials;
import com.google.wallet.objects.utils.WobUtils;
import com.google.wallet.objects.verticals.BoardingPass;
import com.google.wallet.objects.verticals.Generic;
import com.google.wallet.objects.verticals.Loyalty;
import com.google.wallet.objects.verticals.Offer;

@SuppressWarnings("serial")
public class WobInsertServlet extends HttpServlet {

  public void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws IOException {

    ServletContext context = getServletContext();

    WobCredentials credentials = new WobCredentials(
        context.getInitParameter("ServiceAccountId"),
        context.getInitParameter("ServiceAccountPrivateKey"),
        context.getInitParameter("ApplicationName"),
        context.getInitParameter("IssuerId"));

    WobUtils utils = null;
    Walletobjects client = null;
    try {
      utils = new WobUtils(credentials);
      client = utils.getClient();
    } catch (KeyStoreException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (GeneralSecurityException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    String type = req.getParameter("type");

    GenericJson response = null;

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
    /*else if (type.equals("boardingpass")) {
      BoardingPassClass boardingPass = BoardingPass.generateBoardingPassClass(
          utils.getIssuerId(), "BoardingPassClass");
      response = client.boardingpassclass().insert(boardingPass).execute();
    }*/

    PrintWriter out = resp.getWriter();
    out.write(response.toString());
  }
}
