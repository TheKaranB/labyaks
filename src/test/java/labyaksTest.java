import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.junit.Test;

import javax.xml.bind.JAXBException;

public class labyaksTest {

    //Main main = new Main();

    @Test
    public void testOrder1() {
        Main main = new Main();
        try {
            main.unMarshalling();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        for (Labyak yak : main.herd.getLabyaks()) {
            System.out.println(yak.getName());
            System.out.println(yak.getAge());
            System.out.println(yak.getSex());
        }
        System.out.println();

        main.calculateMilkSkins(14);
        System.out.println();
        main.herdToJson();
        System.out.println();

        try {
            main.order("{\n" +
                    "\"customer\" : \"Medvedev\",\n" +
                    "\"order\" : { \"milk\" : 800, \"skins\" : 2 }\n" +
                    "}");
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testOrder2() {
        Main main = new Main();
        try {
            main.unMarshalling();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        for (Labyak yak : main.herd.getLabyaks()) {
            System.out.println(yak.getName());
            System.out.println(yak.getAge());
            System.out.println(yak.getSex());
        }
        System.out.println();

        main.calculateMilkSkins(14);
        System.out.println();
        main.herdToJson();
        System.out.println();

        try {
            main.order("{    \"customer\" : \"Medvedev\",    \"order\" : { \"milk\" : 1200, \"skins\" : 3 }  }");
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
