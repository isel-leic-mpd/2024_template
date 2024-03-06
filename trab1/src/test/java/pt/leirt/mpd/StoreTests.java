package pt.leirt.mpd;

import org.junit.jupiter.api.Test;
import pt.leirt.mpd.products.Electronics;
import pt.leirt.mpd.products.Resolution;
import pt.leirt.mpd.products.Speaker;
import pt.leirt.mpd.products.TV;

import java.io.IOException;
import java.io.StringWriter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static pt.leirt.mpd.TestUtils.count;

public class StoreTests {
  private final static Store store = new Store();
  
  // Store catalog
  static {
    store.addCatalog(StoreDB.sonyX95)
         .addCatalog(StoreDB.samsungU7)
         //.addCatalog(StoreDB.packBig)
         .addCatalog(StoreDB.jblX300)
         .addCatalog(StoreDB.samsungS250);
         //.addCatalog(StoreDB.iPhone15)
         //.addCatalog(StoreDB.samsungS23)
         //.addCatalog(StoreDB.packSamsung)
         //.addCatalog(StoreDB.packTvs);
  }
  
  @Test
  public void productsFromSamsungTests() {
    List<Electronics> expected =
        List.of(new TV("u7", "Samsung", 2000, StoreDB.uhd, 60),
            new Speaker("s250", "Samsung", 200, 60));
    
    Iterable<Electronics> result = store.fromSamsung() ;
    
    System.out.println(result);
    assertEquals(expected.size(), count(result));
    assertEquals(expected, result);
  }
  
  @Test
  public void getAbove60InchesTvsTest() {
    List<TV> expected =
        List.of(new TV("X95", "Sony", 3000, StoreDB.uhd, 65.0));
    
    Iterable<TV> result = store.getAboveSizeTvs(64) ;
    
    System.out.println(result);
    
    assertEquals(expected.size(), count(result));
    assertEquals(expected, result);
  }
  
  @Test
  public void getSpeakersBetween40And50WattsTest() {
    List<Speaker> expected = List.of(StoreDB.jblX300);
    var result = store.getSpeakersInPowerInterval(40,50);
    var expectedCount = expected.size();
    assertEquals(expectedCount, count(result));
    assertEquals(expected, result);
  }
  
  /*
  @Test
  public void getMostExpensiveCommunicationDeviceTest() {
    var expected = StoreDB.iPhone15;
    var result = store.getMostExpensiveCommunicationDevice();
    assertNotNull(result);
    assertEquals(expected, result);
  }
  */
  
  
  /*
  @Test
  public void getAllIndividualProductsInPackTest() {
    int count = 0;
    for(var p : StoreDB.packBig) {
      System.out.println(p);
      count++;
    }
    var expected = 7; // packBig as 7 individual products
    assertEquals(expected, count);
  }
  */
  
  @Test
  public void getIndividualDispalysCountInCatalogTest() {
    var expectedCount=4;
    assertEquals(expectedCount, count(store.getDisplaysSummary()));
  }
  
  @Test
  public void getMostExpensiveProductInPack() {
    var expected = StoreDB.sonyX95;
    assertEquals(expected, store.getMostExpensiveIndividualProductInPacks());
  }
  

  /*
  @Test
  public void getCheapestSmartPhoneWithBatteryGreaterThen2000mAh() {
    var expectedPhone = StoreDB.samsungS23;
    var result = store.getCheapestSmartPhoneWithBatteryGreaterThen(2000);
    assertEquals(expectedPhone, result);
    System.out.println(result);
  }
  */
  
  @Test
  public void getPromoTVsWith20PercentDiscountTest() {
    var result = store.getPromoTVsWith20PercentDiscount();
    assertEquals(0, count(result) );
  }
}
