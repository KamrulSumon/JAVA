import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * List of HashMap
 * khasan on 3/31/17.
 */
public class ListHashMap {

  public static void main(String[] args) {

    List<Map<String, String>> mapList = new ArrayList<Map<String, String>>();
    Map<String, String> map1 = new HashMap<String, String>();
    map1.put("food", "pizza");
    Map<String, String> map2 = new HashMap<String, String>();
    map2.put("drink", "coke");
    Collections.addAll(mapList, map1, map2);

    for (Map<String, String> map : mapList) {
      System.out.println("food is " + map.get("food"));
      System.out.println("drink is " + map.get("drink"));
    }

  }
}
