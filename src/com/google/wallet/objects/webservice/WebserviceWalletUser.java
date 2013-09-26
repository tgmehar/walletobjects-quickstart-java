package com.google.wallet.objects.webservice;

import java.util.List;

/**
 * Bean for the Webservice API represents the user information. Used for Gson
 * deserialization
 *
 * @author pying
 *
 */
public class WebserviceWalletUser {
  String firstName;
  String middleName;
  String lastName;
  String addressLine1;
  String addressLine2;
  String addressLine3;
  String city;
  String state;
  String zipcode;
  String country;
  String email;
  String phone;
  String gender;
  String birthday;
  List<String> userModifiedFields;

  public WebserviceWalletUser() {
  }

  /**
   * @return the firstName
   */
  public String getFirstName() {
    return firstName;
  }

  /**
   * @param firstName
   *          the firstName to set
   */
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  /**
   * @return the middleName
   */
  public String getMiddleName() {
    return middleName;
  }

  /**
   * @param middleName
   *          the middleName to set
   */
  public void setMiddleName(String middleName) {
    this.middleName = middleName;
  }

  /**
   * @return the lastName
   */
  public String getLastName() {
    return lastName;
  }

  /**
   * @param lastName
   *          the lastName to set
   */
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  /**
   * @return the streetAddress
   */
  public String getAddressLine1() {
    return addressLine1;
  }

  /**
   * @param streetAddress
   *          the streetAddress to set
   */
  public void setAddressLine1(String addressLine2) {
    this.addressLine1 = addressLine2;
  }

  /**
   * @return the addressLine2
   */
  public String getAddressLine2() {
    return addressLine2;
  }

  /**
   * @param addressLine2 the addressLine2 to set
   */
  public void setAddressLine2(String addressLine2) {
    this.addressLine2 = addressLine2;
  }

  /**
   * @return the addressLine3
   */
  public String getAddressLine3() {
    return addressLine3;
  }

  /**
   * @param addressLine3 the addressLine3 to set
   */
  public void setAddressLine3(String addressLine3) {
    this.addressLine3 = addressLine3;
  }

  /**
   * @return the city
   */
  public String getCity() {
    return city;
  }

  /**
   * @param city
   *          the city to set
   */
  public void setCity(String city) {
    this.city = city;
  }

  /**
   * @return the state
   */
  public String getState() {
    return state;
  }

  /**
   * @param state
   *          the state to set
   */
  public void setState(String state) {
    this.state = state;
  }

  /**
   * @return the zipcode
   */
  public String getZipcode() {
    return zipcode;
  }

  /**
   * @param zipcode
   *          the zipcode to set
   */
  public void setZipcode(String zipcode) {
    this.zipcode = zipcode;
  }

  /**
   * @return the country
   */
  public String getCountry() {
    return country;
  }

  /**
   * @param country
   *          the country to set
   */
  public void setCountry(String country) {
    this.country = country;
  }

  /**
   * @return the email
   */
  public String getEmail() {
    return email;
  }

  /**
   * @param email
   *          the email to set
   */
  public void setEmail(String email) {
    this.email = email;
  }

  /**
   * @return the phone
   */
  public String getPhone() {
    return phone;
  }

  /**
   * @param phone
   *          the phone to set
   */
  public void setPhone(String phone) {
    this.phone = phone;
  }

  /**
   * @return the gender
   */
  public String getGender() {
    return gender;
  }

  /**
   * @param gender
   *          the gender to set
   */
  public void setGender(String gender) {
    this.gender = gender;
  }

  /**
   * @return the birthday
   */
  public String getBirthday() {
    return birthday;
  }

  /**
   * @param birthday
   *          the birthday to set
   */
  public void setBirthday(String birthday) {
    this.birthday = birthday;
  }

  /**
   * @return the userModifiedFields
   */
  public List<String> getUserModifiedFields() {
    return userModifiedFields;
  }

  /**
   * @param userModifiedFields
   *          the userModifiedFields to set
   */
  public void setUserModifiedFields(List<String> userModifiedFields) {
    this.userModifiedFields = userModifiedFields;
  }
}
