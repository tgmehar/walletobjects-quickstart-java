package com.google.wallet.objects.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.GeneralSecurityException;
import java.security.KeyStoreException;

import javax.servlet.ServletContext;
import javax.servlet.http.*;

import com.google.api.services.walletobjects.Walletobjects;
import com.google.api.services.walletobjects.model.LoyaltyClass;
import com.google.gson.Gson;
import com.google.wallet.objects.Loyalty;
import com.google.wallet.objects.WobUtils;

@SuppressWarnings("serial")
public class WobInsertServlet extends HttpServlet {

  public void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws IOException {
    
    ServletContext context = getServletContext();
    WobUtils utils = null;
    Walletobjects client = null;
    try {
      utils = new WobUtils(context);
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
    
    LoyaltyClass loyaltyClass = Loyalty.generateLoyaltyClass(utils.getIssuerId(), "ExampleClass1");
    LoyaltyClass insertResponse = client.loyaltyclass().insert(loyaltyClass).execute();
    Gson gson = new Gson();
    
    PrintWriter out = resp.getWriter();
    out.write(gson.toJson(insertResponse));
  }
}
