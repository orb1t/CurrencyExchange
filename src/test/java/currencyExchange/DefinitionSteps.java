package currencyExchange;

import currencyExchange.controllers.CurrenciesController;// CurrenciesController;
import currencyExchange.helpers.CurrencyExchangeTestsHelper;
import currencyExchange.models.ApiRole;
import org.everit.json.schema.Schema;
import org.everit.json.schema.loader.SchemaLoader;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static currencyExchange.helpers.CurrencyExchangeTestsHelper.readFile;
import static currencyExchange.helpers.CurrencyExchangeTestsHelper.schemasMap;
import static org.junit.Assert.assertEquals;

public class DefinitionSteps {

  private ApiRole client;
  private String route;
  private HttpMethod request;
  private String body;
  private ResponseEntity response;
  private CurrenciesController currenciesController;

  @Given("Client's role is $role")
  public void givenClientsRoleIs(String role) {
    client = ApiRole.valueOf(role);
  }

  @Given("service DB is in initial state")
  public void givenServiceDBIsInInitialState() {
    CurrencyExchangeTestsHelper.resetDbData();
  }

  @When("Client makes $request request to route $route")
  public void whenClientMakesRequestToRoute(String request, String route) {
    this.route = route.replace("'", "");
    this.request = HttpMethod.valueOf(request);
    currenciesController = new CurrenciesController();
    response = currenciesController.makeRequest(this.route, this.request, String.class, this.client, this.body);
  }

  @Given("request params is $params")
  public void givenRequestParamsIs(String params) {
    this.body = params;
  }

  @Then("the response status code should be $code")
  public void thenTheResponseStatusCodeShouldBe(Integer code) {
    assertEquals ( HttpStatus.valueOf( code ), ( null != response ? response.getStatusCode() : currenciesController.responseStatus ) );
  }

  @Then("response body should comply with $schema schema")
  public void thenResponseBodyShouldComplyWithSchema( String schema ) {
    String schemaJson = readFile ( schemasMap.get(schema.toLowerCase()).getAbsolutePath() );
    JSONObject jsonSchema = new JSONObject( new JSONTokener( schemaJson ) );
    JSONObject jsonSubject = new JSONObject( ( null != response ? response.getBody().toString() : currenciesController.responseBody ) );

    Schema schemaValidator = SchemaLoader.load(jsonSchema);
    schemaValidator.validate(jsonSubject);
  }

}
