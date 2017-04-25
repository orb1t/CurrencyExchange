package currencyExchange.helpers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by orb1t_ua on 4/12/17.
 */
public final class CurrencyExchangeTestsHelper {

  private static final String dbContentMigration = "20170408200027";
  private static final String appUnderTestRootPath = "/home/orb1t_ua/_wrx_/qa/CurrencyExchange-master/";

  public static final Map<String, File> schemasMap = new HashMap<String, File>()
  {{
    put("currency", new File("/home/orb1t_ua/_wrx_/qa/currencyExchange/src/test/resources/currencyExchange/schemas/currency_schema.json"));
    put("currencies", new File("/home/orb1t_ua/_wrx_/qa/currencyExchange/src/test/resources/currencyExchange/schemas/currencies_schema.json"));
  }};

//  public static final File currencySchemaFile = new File("/home/orb1t_ua/_wrx_/qa/CurrencyExchangeAutoTests/src/main/resources/currency_schema.json");
//  public static final File currenciesSchemaFile = new File("/home/orb1t_ua/_wrx_/qa/CurrencyExchangeAutoTests/src/main/resources/currencies_schema.json");

//  public static final Currency currencyToPost = new Currency(3, "US", "USA", 1.4);

  public static String readFile(String filename) {
    File f = new File(filename);
    try {
      byte[] bytes = Files.readAllBytes(f.toPath());
      return new String(bytes,"UTF-8");
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return "";
  }


  public static void resetDbData(){
    Runtime rt = Runtime.getRuntime();
    try {
      System.out.println("Preparing Db for Test Run");
      System.out.println("Rolling Back Db migration #"+dbContentMigration);
      Process pr = rt.exec("php " + appUnderTestRootPath + "bin/console doctrine:migrations:execute " + dbContentMigration + " --down");
      pr.waitFor();
//      System.out.println("Preparing Db for Test Run");
      System.out.println("Re-applying Db migration #"+dbContentMigration);
      pr = rt.exec("php " + appUnderTestRootPath + "bin/console doctrine:migrations:execute " + dbContentMigration + " --up");
      pr.waitFor();
    } catch (Exception e) {
      e.printStackTrace();
      throw new RuntimeException(e);
    }
  }

//  public static Currencies getLocalDbData() {
//    return localDbData;
//  }


//  public static Currency[] getInitialState() {
//    return initialState;
//  }

//  public void setInitialState(Currency[] initialState) {
//    this.initialState = initialState;
//  }

//  private static Currency[] initialState = {
//      new Currency(1, "UAH", "Ukraine", 0.037),
//      new Currency(2, "GBP", "Great Britain", 1.25)
//  };
//  private Currency uah = new Currency(1, "UAH", "Ukraine", 0.037);
//  private Currency gbp = new Currency(2, "GBP", "Great Britain", 1.25);
//  private static Currencies localDbData = new Currencies(new ArrayList<Currency>(Arrays.asList(initialState)));

}
