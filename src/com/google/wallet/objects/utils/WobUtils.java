package com.google.wallet.objects.utils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.security.KeyStoreException;
import java.security.SignatureException;
import java.security.interfaces.RSAPrivateKey;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import net.oauth.jsontoken.JsonToken;
import net.oauth.jsontoken.crypto.RsaSHA256Signer;

import org.joda.time.Instant;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.GenericJson;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.Base64;
import com.google.api.client.util.SecurityUtils;
import com.google.api.services.walletobjects.Walletobjects;
import com.google.api.services.walletobjects.model.DateTime;
import com.google.common.io.ByteStreams;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.wallet.objects.webservice.WebserviceResponse;

/**
 * This class contains utility functions to create clients for API access and
 * generate JWTs for Save To Wallet and the Webservice API.
 *
 * @author pying
 *
 */
public class WobUtils {

  private final String SAVE_TO_WALLET = "savetowallet";
  private final String LOYALTY_WEB = "loyaltywebservice";
  private final String WOB_PROD = "https://www.googleapis.com/auth/wallet_object.issuer";
  private final String GOOGLE = "google";

  private final String serviceAccountId;
  private final RSAPrivateKey rsaKey;
  private final String applicationName;
  private final String issuerId;

  private GoogleCredential credential = null;

  private HttpTransport httpTransport;
  private JsonFactory jsonFactory;

  RsaSHA256Signer signer = null;
  Gson gson = new Gson();

  /**
   * Constructer configures the components necessary to sign JWTs and create
   * OAuth tokens for requests
   *
   * @param credentials
   * @throws FileNotFoundException
   * @throws IOException
   * @throws KeyStoreException
   * @throws GeneralSecurityException
   */
  public WobUtils(WobCredentials credentials) throws FileNotFoundException,
      IOException, KeyStoreException, GeneralSecurityException {
    serviceAccountId = credentials.getServiceAccountId();
    applicationName = credentials.getApplicationName();
    issuerId = credentials.getIssuerId();
    httpTransport = new NetHttpTransport();
    jsonFactory = new GsonFactory();

    // Check if a RSAPrivateKey is defined or if we need to generate it from a
    // file
    if (credentials.getServiceAccountPrivateKey() != null) {
      rsaKey = credentials.getServiceAccountPrivateKey();
    } else {
      String rsaKeyPath = credentials.getServiceAccountPrivateKeyPath();
      File file = new File(rsaKeyPath);
      byte[] bytes = ByteStreams.toByteArray(new FileInputStream(file));
      InputStream keyStream = new ByteArrayInputStream(bytes);
      rsaKey = (RSAPrivateKey) SecurityUtils.loadPrivateKeyFromKeyStore(
          SecurityUtils.getPkcs12KeyStore(), keyStream, "notasecret",
          "privatekey", "notasecret");
    }
    signer = new RsaSHA256Signer(serviceAccountId, null, rsaKey);
  }

  /**
   * Creates a Walletobjects client with production scopes
   *
   * @return Walletobjects client
   * @throws GeneralSecurityException
   * @throws IOException
   */
  public Walletobjects getClient() throws GeneralSecurityException, IOException {
    credential = getCredential();

    return new Walletobjects.Builder(httpTransport, jsonFactory, credential)
        .setApplicationName(applicationName).build();
  }

  /**
   * Helper function to generate the Google Credential
   *
   * @return
   * @throws GeneralSecurityException
   * @throws IOException
   */
  public GoogleCredential getCredential() throws GeneralSecurityException,
      IOException {
    List<String> scopes = new ArrayList<String>();
    scopes.add(WOB_PROD);

    return credential = new GoogleCredential.Builder()
        .setTransport(httpTransport).setJsonFactory(jsonFactory)
        .setServiceAccountId(serviceAccountId).setServiceAccountScopes(scopes)
        .setServiceAccountPrivateKey(rsaKey).build();
  }

  /**
   * Refreshes the access token and returns it.
   *
   * @return OAuth access token
   * @throws GeneralSecurityException
   * @throws IOException
   */
  public String accessToken() throws GeneralSecurityException, IOException {
    if (credential == null) {
      credential = getCredential();
    }
    credential.refreshToken();
    return credential.getAccessToken();
  }

  /**
   * Generates a failure Webservice API failure response JWT.
   *
   * @param resp
   * @return
   * @throws SignatureException
   */
  public String generateWebserviceFailureResponseJwt(WebserviceResponse resp)
      throws SignatureException {
    return generateWebserviceResponseJwt(null, resp);
  }

  /**
   * Generates the linking/signup JWT from a Wallet Object
   *
   * @param object
   * @param resp
   * @return
   * @throws SignatureException
   */
  public String generateWebserviceResponseJwt(GenericJson object,
      WebserviceResponse resp) throws SignatureException {

    JsonToken token = new JsonToken(signer);
    token.setAudience(GOOGLE);
    token.setParam("typ", LOYALTY_WEB);
    token.setIssuedAt(new Instant(
        Calendar.getInstance().getTimeInMillis() - 5000L));
    WobPayload payload = new WobPayload();

    if (object != null) {
      object.setFactory(new GsonFactory());
      payload.addObject(object);
    }

    payload.setResponse(resp);
    JsonObject obj = gson.toJsonTree(payload).getAsJsonObject();
    token.getPayloadAsJsonObject().add("payload", obj);
    return token.serializeAndSign();
  }

  /**
   * Generates the Save to Wallet JWT from a Wallet Object. Useful for when you
   * create Save to Wallet JWTs with a single object.
   *
   * @param object
   * @param origins
   * @return
   * @throws SignatureException
   */
  public String generateSaveJwt(GenericJson object, List<String> origins)
      throws SignatureException {
    WobPayload payload = new WobPayload();
    payload.addObject(object);
    return generateSaveJwt(payload, origins);
  }

  /**
   * Generates the Save to Wallet JWT from a WobPayload. Useful for when you
   * create Save to Wallet JWTs with multiple objects/classes.
   *
   * @param payload
   * @param origins
   * @return
   * @throws SignatureException
   */
  public String generateSaveJwt(WobPayload payload, List<String> origins)
      throws SignatureException {
    JsonToken token = new JsonToken(signer);
    token.setAudience(GOOGLE);
    token.setParam("typ", SAVE_TO_WALLET);
    token.setIssuedAt(new Instant(
        Calendar.getInstance().getTimeInMillis() - 5000L));
    JsonObject obj = gson.toJsonTree(payload).getAsJsonObject();
    token.getPayloadAsJsonObject().add("payload", obj);
    token.getPayloadAsJsonObject().add("origins", gson.toJsonTree(origins));
    return token.serializeAndSign();
  }

  /**
   * Convert RFC3339 String YYYY-MM-DDTHH:MM:SSZ to DateTime object
   *
   * @param rfc3339
   * @return
   */
  public static DateTime toDateTime(String rfc3339) {
    return new DateTime().setDate(com.google.api.client.util.DateTime
        .parseRfc3339(rfc3339));
  }

  /**
   * Returns Service Account id for quick access
   *
   * @return
   */
  public String getServiceAccountId() {
    return serviceAccountId;
  }

  /**
   * Returns Issuer Id for quick access
   *
   * @return
   */
  public String getIssuerId() {
    return issuerId;
  }

  /**
   * Converts base64 encoded RSA Private Key String to RSAPrivateKey object
   *
   * Useful if you want to hardcode your RSA Private Key as a variable i.e. in
   * testing
   *
   * @param keyString
   * @return
   * @throws KeyStoreException
   * @throws IOException
   * @throws GeneralSecurityException
   * @throws Base64DecodingException
   */
  public static RSAPrivateKey generateRsaPrivateKey(String keyString)
      throws KeyStoreException, IOException, GeneralSecurityException {
    InputStream keyStream = new ByteArrayInputStream(
        Base64.decodeBase64(keyString));
    return (RSAPrivateKey) SecurityUtils.loadPrivateKeyFromKeyStore(
        SecurityUtils.getPkcs12KeyStore(), keyStream, "notasecret",
        "privatekey", "notasecret");
  }
}
