package currencyExchange.controllers;


import currencyExchange.models.ApiRole;
import currencyExchange.models.Currencies;
import currencyExchange.models.Currency;
import org.springframework.http.*;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

/**
 * Created by orb1t_ua on 4/12/17.
 */
public class CurrenciesController {

  private String API_BASE = "http://localhost:8000/app_dev.php/api/v1/";
  private String API_ROUTE = "currencies";
  private String API_KEY_ADMIN = "tR6TI49mh4fbKAuSjm9L";
  private String API_KEY_USER = "YECRSbpLDCXrJXCrVKOU";


  RestTemplate restTemplate;// = new RestTemplate();
  HttpHeaders headers;// = new HttpHeaders();
  public static String responseBody;
  public static HttpHeaders responseHeaders;
  public static HttpStatus responseStatus;

  public ResponseEntity makeRequest (String route, HttpMethod method, Class responseType, ApiRole role, String body){

    ResponseEntity model = null;
    headers.set("authorization", role.getApiKey());
    try {
      String routeId = "";
      HttpEntity<?> request = new HttpEntity<Object>(body, headers);

      model =  restTemplate.exchange("http://localhost:8000/app_dev.php" + route + ( routeId.length() > 0 ? "/" + routeId : "" ), method, request, responseType);
      System.out.println(model);
    } catch (Exception e) {
      defaultErrorHandler ( e );
    }
    return model;
  }

  public ResponseEntity deleteCurrencyById(ApiRole role, Integer currencyId){
    ResponseEntity<Currency> model = null;
    headers.set("authorization", role.getApiKey());
    try {
      model = restTemplate.exchange(API_BASE + API_ROUTE + "/" + currencyId, HttpMethod.DELETE, new HttpEntity<Object>(headers),Currency.class);
    } catch (Exception e) {
      defaultErrorHandler ( e );
    }
    return model;
  }

  public Currency postCurrency ( ApiRole role, Currency currency ){
    Currency model = null;
    headers.set("authorization", role.getApiKey());
    String body =  "{\"code\":\"US\",\"country\":\"USA\",\"value\":\"1.4\"}";//currency.toString(); //
    HttpEntity<?> request = new HttpEntity<Object>(body, headers);
    try {
      model = restTemplate.postForObject(API_BASE + API_ROUTE + "/", request, Currency.class);
    } catch (Exception e) {
      defaultErrorHandler ( e );
    }
    return model;//.getBody();
  }

  public Currencies postCurrency1 (ApiRole role, Currency currency ){
    Currencies model = null;
    headers.set("authorization", role.getApiKey());
    String body =  "{\"code\":\"US\",\"country\":\"USA\",\"value\":\"1.4\"}";//currency.toString(); //
    HttpEntity<?> request = new HttpEntity<Object>(body, headers);
    try {
      model = restTemplate.postForObject(API_BASE + API_ROUTE + "/", request, Currencies.class);
    } catch (Exception e) {
      defaultErrorHandler ( e );
    }
    return model;//.getBody();
  }

  public Currency getCurrencyById ( ApiRole role, Integer currencyId ) {
    ResponseEntity<Currency> model = null;
    headers.set("authorization", role.getApiKey());
    try {
      model = restTemplate.exchange(API_BASE + API_ROUTE + "/" + currencyId, HttpMethod.GET, new HttpEntity<Object>(headers), Currency.class);
    } catch (Exception e) {
      defaultErrorHandler ( e );
    }
    return model.getBody();
  }

  public Currencies getCurrencies ( ApiRole role ){
    ResponseEntity<Currencies> models = null;
    headers.set("authorization", role.getApiKey());
    try {
      models = restTemplate.exchange(API_BASE + API_ROUTE + "/", HttpMethod.GET, new HttpEntity<Object>(headers), Currencies.class);
    } catch ( Exception e ){
      defaultErrorHandler(e);
    }
    return models.getBody();
  }


  public CurrenciesController() {
    restTemplate = new RestTemplate();
    headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    headers.set("Accept", "application/json");
  }


  public void defaultErrorHandler(Exception e) {
    if (e instanceof HttpStatusCodeException) {
      responseBody = ((HttpStatusCodeException) e).getResponseBodyAsString();// getResponseHeaders().get("x-app-err-id");
      responseHeaders = ((HttpStatusCodeException) e).getResponseHeaders();
      responseStatus = ((HttpStatusCodeException) e).getStatusCode();
    }
    if (e instanceof HttpServerErrorException) {
      System.out.println(((HttpServerErrorException) e).toString());
      System.out.println(((HttpServerErrorException) e).getResponseBodyAsString());
      System.out.println(((HttpServerErrorException) e).getMessage());
    }
    e.printStackTrace();
//    throw new RuntimeException();
  }
}
