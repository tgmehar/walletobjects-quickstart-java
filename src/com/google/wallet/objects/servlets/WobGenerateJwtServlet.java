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

import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.walletobjects.model.LoyaltyObject;
import com.google.wallet.objects.Loyalty;
import com.google.wallet.objects.WobUtils;

@SuppressWarnings("serial")
public class WobGenerateJwtServlet extends HttpServlet{
  public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException{
    ServletContext context = getServletContext();
    
    WobUtils utils = null;
    
    try {
      utils = new WobUtils(context);
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
    
    List<String> origins = new ArrayList<String>();
    origins.add("http://localhost:8888");
    origins.add("https://localhost:8888");
    
    LoyaltyObject loyaltyObject = Loyalty.generateLoyaltyObject(utils.getIssuerId(), "ExampleClass1", "ExampleObject1");
    
    String jwt = null;
    try {
      jwt = utils.generateSaveJwt(loyaltyObject, origins);
    } catch (SignatureException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    
    PrintWriter out = resp.getWriter();
    out.write(jwt);
  }
  
  
}
