import java.util.*;

public class Main {

//    @SuppressWarnings("unchecked")
    public static void main(String[] args) {

        Set set = new HashSet<>();

        String s = "hello";

//        Integer i = 1;

        set.add(s);
        set.add(s);


//        for (int j = 0; j < set.size(); j++) {
//            System.out.println(set..toString());
//        }
        Iterator iterator = set.iterator();
        while (iterator.hasNext()) {
            ((String) iterator.next()).toString();
        }

        Map<Integer, String> map = new HashMap<>();
        map.put(1, "1,0");
        map.put(1, "2,p");
        System.out.println(map.toString());

    }
}
