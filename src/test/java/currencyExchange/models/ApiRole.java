package currencyExchange.models;

/**
 * Created by orb1t_ua on 4/19/17.
 */
public enum ApiRole {
  Admin ( "tR6TI49mh4fbKAuSjm9L" ),
  User ( "YECRSbpLDCXrJXCrVKOU"),
  UnAuthorized ( "" );

  private String apiKey;
  ApiRole(String apiKey) {
    this.apiKey = apiKey;
  }

  public String getApiKey(){
    return this.apiKey;
  }
};
