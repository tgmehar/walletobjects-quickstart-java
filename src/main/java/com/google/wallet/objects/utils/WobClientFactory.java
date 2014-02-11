package com.google.wallet.objects.utils;

import com.google.api.services.walletobjects.Walletobjects;

import java.util.*;

/**
 * Created by pying on 2/6/14.
 */
public class WobClientFactory {

  private static Map<String, Walletobjects> clients = new HashMap<String, Walletobjects>();

  private WobClientFactory() {
  }

  /**
   * Creates a Walletobjects client with production scopes
   *
   * @return Walletobjects client
   */
  public static Walletobjects getWalletObjectsClient(WobCredentials credential) {
    String key = credential.toString();
    Walletobjects client = clients.get(key);
    if (client == null) {
      client = new Walletobjects.Builder(credential.httpTransport, credential.jsonFactory, credential.getGoogleCredential())
          //.setRootUrl("https://www-googleapis-staging.sandbox.google.com")
          .setApplicationName(credential.getApplicationName()).build();
      clients.put(key, client);
    }
    return client;
  }
}
