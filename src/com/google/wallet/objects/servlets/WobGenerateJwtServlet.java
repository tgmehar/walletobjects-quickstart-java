package com.google.wallet.objects.servlets;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.GeneralSecurityException;
import java.security.KeyStoreException;
import java.security.SignatureException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.wallet.objects.utils.WobCredentials;
import com.google.wallet.objects.utils.WobPayload;
import com.google.wallet.objects.utils.WobUtils;
import com.google.wallet.objects.verticals.Loyalty;
import com.google.wallet.objects.verticals.Offer;

@SuppressWarnings("serial")
public class WobGenerateJwtServlet extends HttpServlet{
  public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException{
    ServletContext context = getServletContext();

    WobCredentials credentials = new WobCredentials(
        context.getInitParameter("ServiceAccountId"),
        context.getInitParameter("ServiceAccountPrivateKey"),
        context.getInitParameter("ApplicationName"),
        context.getInitParameter("IssuerId"));

    WobUtils utils = null;

    try {
      utils = new WobUtils(credentials);
    } catch (FileNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
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

    List<String> origins = new ArrayList<String>();
    origins.add("http://localhost:8888");
    origins.add("https://localhost:8888");
    origins.add("http://wobs-quickstart.appspot.com");
    origins.add("https://wobs-quickstart.appspot.com");

    WobPayload payload = new WobPayload();

    if (type.equals("loyalty")) {
      payload.addObject(Loyalty.generateLoyaltyObject(utils.getIssuerId(),
          "LoyaltyClass", "LoyaltyObject"));
    } else if (type.equals("offer")) {
      payload.addObject(Offer.generateOfferObject(utils.getIssuerId(),
          "OfferClass", "OfferObject"));
    }

    String jwt = null;
    try {
      jwt = utils.generateSaveJwt(payload, origins);
    } catch (SignatureException e) {
      e.printStackTrace();
    }

    PrintWriter out = resp.getWriter();
    out.write(jwt);
  }


}
