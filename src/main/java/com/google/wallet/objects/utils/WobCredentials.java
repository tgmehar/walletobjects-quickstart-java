package com.google.wallet.objects.utils;

import java.security.interfaces.RSAPrivateKey;

/**
 * Class to define Wallet Object related credentials. These credentials are used
 * with WobUtils to simplify the OAuth token generation and JWT signing.
 *
 * If you're using a key file, specify the privateKeyPath using the first
 * constructor. If you've defined the private key as a base 64 encoded string,
 * convert it to an RSAPrivate Key with WobUtils.getRsaPrivateKey.
 *
 * @author pying
 *
 */
public class WobCredentials {
  String serviceAccountId;
  String serviceAccountPrivateKeyPath;
  String applicationName;
  String issuerId;
  RSAPrivateKey serviceAccountPrivateKey;

  /**
   * Constructor for defining a path to your key.p12 file.
   *
   * @param serviceAccountId
   * @param privateKeyPath
   * @param applicationName
   * @param issuerId
   */
  public WobCredentials(String serviceAccountId, String privateKeyPath,
      String applicationName, String issuerId) {
    setServiceAccountId(serviceAccountId);
    setServiceAccountPrivateKeyPath(privateKeyPath);
    setApplicationName(applicationName);
    setIssuerId(issuerId);
  }

  /**
   * Constructor for a base64 encoded private key String.
   *
   * @param serviceAccountId
   * @param privateKey
   * @param applicationName
   * @param issuerId
   */
  public WobCredentials(String serviceAccountId, RSAPrivateKey privateKey,
      String applicationName, String issuerId) {
    setServiceAccountId(serviceAccountId);
    setServiceAccountPrivateKey(privateKey);
    setApplicationName(applicationName);
    setIssuerId(issuerId);
  }

  /**
   * @return the serviceAccountPrivateKey
   */
  public RSAPrivateKey getServiceAccountPrivateKey() {
    return serviceAccountPrivateKey;
  }

  /**
   * @param serviceAccountPrivateKey
   *          the serviceAccountPrivateKey to set
   */
  public void setServiceAccountPrivateKey(RSAPrivateKey serviceAccountPrivateKey) {
    this.serviceAccountPrivateKey = serviceAccountPrivateKey;
  }

  /**
   * @return the serviceAccountId
   */
  public String getServiceAccountId() {
    return serviceAccountId;
  }

  /**
   * @param serviceAccountId
   *          the serviceAccountId to set
   */
  public void setServiceAccountId(String serviceAccountId) {
    this.serviceAccountId = serviceAccountId;
  }

  /**
   * @return the serviceAccountPrivateKey
   */
  public String getServiceAccountPrivateKeyPath() {
    return serviceAccountPrivateKeyPath;
  }

  /**
   * @param serviceAccountPrivateKey
   *          the serviceAccountPrivateKey to set
   */
  public void setServiceAccountPrivateKeyPath(String serviceAccountPrivateKey) {
    this.serviceAccountPrivateKeyPath = serviceAccountPrivateKey;
  }

  /**
   * @return the applicationName
   */
  public String getApplicationName() {
    return applicationName;
  }

  /**
   * @param applicationName
   *          the applicationName to set
   */
  public void setApplicationName(String applicationName) {
    this.applicationName = applicationName;
  }

  /**
   * @return the issuerId
   */
  public String getIssuerId() {
    return issuerId;
  }

  /**
   * @param issuerId
   *          the issuerId to set
   */
  public void setIssuerId(String issuerId) {
    this.issuerId = issuerId;
  }
}
