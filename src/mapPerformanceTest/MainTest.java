
/**
 * Created by admin on 2015/4/21.
 */
public class MainTest {

    public static void main(String[] args) throws  Exception{
        Integer caseAmount = 200000;
        HashMapTest hmt = new HashMapTest();
        hmt.setCaseAmount(caseAmount);
        hmt.init();
        hmt.run();
        hmt.result();
        System.out.println("================HashMap End====================");
        HashTableTest htt = new HashTableTest();
        htt.setCaseAmount(caseAmount);
        htt.init();
        htt.run();
        htt.result();
        System.out.println("================HashTable End====================");
        LinkedHashMapTest lhmt = new LinkedHashMapTest();
        lhmt.setCaseAmount(caseAmount);
        lhmt.init();
        lhmt.run();
        lhmt.result();
        System.out.println("================LinkedHashMap End====================");
        TreeMapTest tmt = new TreeMapTest();
        tmt.setCaseAmount(caseAmount);
        tmt.init();
        tmt.run();
        tmt.result();
        System.out.println("================TreeMap End====================");

    }
}
