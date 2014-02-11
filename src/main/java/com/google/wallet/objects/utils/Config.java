package com.google.wallet.objects.utils;

import javax.servlet.ServletContext;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by pying on 2/7/14.
 */
public class Config {

  private static Config config;

  private static Map<String, WobCredentials> credentials;

  private Config() {
  }

  public static Config getInstance() {
    if (config == null) {

      config = new Config();
      credentials = new HashMap<String, WobCredentials>();
    }
    return config;
  }

  public WobCredentials getCredentials(ServletContext context) throws IOException, GeneralSecurityException {
    String key = context.getInitParameter("IssuerId") + context.getInitParameter("ServiceAccountPrivateKey");

    WobCredentials credential = credentials.get(key);

    if (credential == null) {
      credential = new WobCredentials(
          context.getInitParameter("ServiceAccountEmailAddress"),
          context.getInitParameter("ServiceAccountPrivateKey"),
          context.getInitParameter("ApplicationName"),
          context.getInitParameter("IssuerId"));
      credentials.put(key, credential);
    }
    return credential;
  }
}
