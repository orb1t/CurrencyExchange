    package currencyExchange.models;

    import com.fasterxml.jackson.annotation.*;
    import com.github.reinert.jjschema.Attributes;
    import org.json.JSONObject;

    import java.util.HashMap;
    import java.util.Map;

//    import com.github.reinert.jjschema.Attributes;
//    import org.apache.commons.lang.builder.ToStringBuilder;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({
        "id",
        "code",
        "country",
        "value"
    })
    @Attributes(title = "Currency", description = "Schema for a Currency")
    public class Currency {

      @JsonProperty("id")
      @Attributes(required = true, description = "ID of the Currency")
      private Integer id;
      @JsonProperty("code")
      @Attributes(required = true, minLength = 1, maxLength = 255, description = "Country Code of Currency")
      private String code;
      @JsonProperty("country")
      @Attributes(required = true, minLength = 1, maxLength = 255, description = "Country Name of Currency")
      private String country;
      @JsonProperty("value")
      @Attributes(required = true, description = "Exchange rate value")
      private Double value;
      @JsonIgnore
      private Map<String, Object> additionalProperties = new HashMap<String, Object>();

      public JSONObject getAsJSONObject(){
        return new JSONObject( this );
      }

      /**
       * No args constructor for use in serialization
       *
       */
      public Currency() {
      }

      /**
       *
       * @param id
       * @param value
       * @param code
       * @param country
       */
      public Currency(Integer id, String code, String country, Double value) {
        super();
        this.id = id;
        this.code = code;
        this.country = country;
        this.value = value;
      }

      @JsonProperty("id")
      public Integer getId() {
        return id;
      }

      @JsonProperty("id")
      public void setId(Integer id) {
        this.id = id;
      }

      @JsonProperty("code")
      public String getCode() {
        return code;
      }

      @JsonProperty("code")
      public void setCode(String code) {
        this.code = code;
      }

      @JsonProperty("country")
      public String getCountry() {
        return country;
      }

      @JsonProperty("country")
      public void setCountry(String country) {
        this.country = country;
      }

      @JsonProperty("value")
      public Double getValue() {
        return value;
      }

      @JsonProperty("value")
      public void setValue(Double value) {
        this.value = value;
      }

    //  @Override
    //  public String toString() {
    //    return "";//ToStringBuilder.reflectionToString(this);
    //  }

      @Override
      public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Currency)) return false;

        Currency currency = (Currency) o;

        if (!id.equals(currency.id)) return false;
        if (!code.equals(currency.code)) return false;
        if (!country.equals(currency.country)) return false;
        return value.equals(currency.value);

      }

      @Override
      public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + code.hashCode();
        result = 31 * result + country.hashCode();
        result = 31 * result + value.hashCode();
        return result;
      }

      @Override
      public String toString() {
        return "{" +
            "'id':" + id +
            ", 'code':'" + code + '\'' +
            ", 'country':'" + country + '\'' +
            ", 'value':" + value +
            ( additionalProperties.size() > 0 ? ", 'additionalProperties':" + additionalProperties + '}' : '}' );
      }

      @JsonAnyGetter
      public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
      }

      @JsonAnySetter
      public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
      }

    }
