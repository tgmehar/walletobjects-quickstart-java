package com.google.wallet.objects.servlets;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.GeneralSecurityException;
import java.security.KeyStoreException;
import java.security.SignatureException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.api.services.walletobjects.model.LoyaltyObject;
import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.wallet.objects.Loyalty;
import com.google.wallet.objects.WobUtils;
import com.google.wallet.objects.webservice.WebserviceRequest;
import com.google.wallet.objects.webservice.WebserviceResponse;

public class WobWebserviceHandlerServlet extends HttpServlet {
  /**
   *
   */
  private static final long serialVersionUID = 1842327434636112548L;

  public void doGet(HttpServletRequest req, HttpServletResponse resp) {
    doPost(req, resp);
  }

  public void doPost(HttpServletRequest req, HttpServletResponse resp) {
    Gson gson = new Gson();
    ServletContext context = getServletContext();
    WobUtils utils = null;
    WebserviceRequest webRequest = null;
    WebserviceResponse webResponse = null;
    PrintWriter out = null;
    String jwt = null;

    try {
      webRequest = gson.fromJson(req.getReader(), WebserviceRequest.class);
      utils = new WobUtils(context);
      out = resp.getWriter();
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
    } catch (JsonSyntaxException e) {
      e.printStackTrace();
    } catch (JsonIOException e) {
      e.printStackTrace();
    }

    if(webRequest.getMethod().equals("signup"))
      webResponse = new WebserviceResponse("Welcome to baconrista", "approved");
    else
      webResponse = new WebserviceResponse("Thanks for linking to baconrista",
          "approved");

    String linkId = webRequest.getParams().getLinkingId();
    LoyaltyObject loyaltyObject =
        Loyalty.generateLoyaltyObject(utils.getIssuerId(), "ExampleClass",
            ( linkId!= null) ? linkId:"ExampleObject");

    try {
      jwt = utils.generateWebserviceResponseJwt(loyaltyObject,
          webResponse);
    } catch (SignatureException e) {
      e.printStackTrace();
    }

    out.write(jwt);
  }
}
