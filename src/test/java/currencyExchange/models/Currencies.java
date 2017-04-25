package currencyExchange.models;

/**
 * Created by orb1t_ua on 4/12/17.
 */

import com.fasterxml.jackson.annotation.*;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "_collection"
})
public class Currencies {

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Currencies)) return false;

    Currencies that = (Currencies) o;

    return collection.equals(that.collection);

  }

  @Override
  public int hashCode() {
    return collection.hashCode();
  }

  @Override

  public String toString() {
    return
        ( null != collection ? "{ '_collection':" + collection +"}" : ( additionalProperties.size() > 0 ? getAdditionalPropertyesAsJSONString() : "" ) );
//        ( additionalProperties.size() > 0 ? ( null != collection ? ", " : "" ) + additionalProperties + '}' : '}' );
  }

  @JsonProperty("_collection")
  private List<Currency> collection = null;
  @JsonIgnore
  private Map<String, Object> additionalProperties = new HashMap<String, Object>();


  public JSONObject getAsJSONObject(){
    return ( null != collection ? new JSONObject( collection ) : ( additionalProperties.size() > 0 ? new JSONObject( additionalProperties ) : null ) );
  }

  /**
   * No args constructor for use in serialization
   *
   */
  public Currencies() {
  }

  /**
   *
   * @param collection
   */
  public Currencies(List<Currency> collection) {
    super();
    this.collection = collection;
  }

  @JsonProperty("_collection")
  public List<Currency> getCollection() {
    return collection;
  }

  @JsonProperty("_collection")
  public void setCollection(List<Currency> collection) {
    this.collection = collection;
  }


  @JsonAnyGetter
  public Map<String, Object> getAdditionalProperties() {
    return this.additionalProperties;
  }

  @JsonAnySetter
  public void setAdditionalProperty(String name, Object value) {
    this.additionalProperties.put(name, value);
  }


  private String getAdditionalPropertyesAsJSONString(){
    return additionalProperties.toString();//.replace("=", ":");
  }
}
