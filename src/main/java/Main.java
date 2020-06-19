import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.xml.bind.*;
import java.io.*;

public class Main {

    Double milk;
    Integer skins;
    Herd herd;

    public void unMarshalling() throws JAXBException{
        JAXBContext jaxbContext = JAXBContext.newInstance(Herd.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

        herd = (Herd) jaxbUnmarshaller.unmarshal( new File("resources/Herd") );
    }

    public void calculateMilkSkins(Integer days){
        milk = 0.0;
        skins = 0;
        for(Labyak yak : herd.getLabyaks()){
            skins++;
            Integer daysNotShaven = 0;
            yak.setAgeLastShaven(yak.getAge());
            for(int i = 0; i < days; i++) {
                milk = milk + (50 - (yak.getAge() * 100) * 0.03);
                yak.setAge(yak.getAge() + 0.01);
                daysNotShaven++;
                if(daysNotShaven > Math.ceil((8 + (yak.getAge()*100) * 0.01))){
                    skins++;
                    daysNotShaven = 0;
                    yak.setAgeLastShaven(yak.getAge());
                }
                if (yak.getAge() == 10) {
                    break;
                }
            }
        }
        JSONObject result = new JSONObject();
        result.put("milk", milk);
        result.put("skins", skins);
        System.out.println(prettyPrintJson(result.toString()));
    }

    public void order(String orderJson) throws ParseException {
        //calculateMilkSkins(days);
        JSONParser parser = new JSONParser();
        JSONObject wholeOrder = (JSONObject) parser.parse(orderJson);
        JSONObject order = (JSONObject) wholeOrder.get("order");
        Double orderedMilk =  Double.valueOf(order.get("milk").toString());
        Integer orderedSkins = Integer.valueOf(order.get("skins").toString());

        String httpStatus = "404";
        JSONObject result = new JSONObject();
        if(orderedMilk <= milk){
            httpStatus = "206";
            result.put("milk", orderedMilk);
            milk = milk - orderedMilk;
        }
        if(orderedSkins <= skins) {
            httpStatus = "206";
            result.put("skins", orderedSkins);
            skins = skins - orderedSkins;
        }
        if(result.containsKey("milk") && result.containsKey("skins")){
            httpStatus = "201";
        }
        System.out.println("HTTP Status: " + httpStatus);
        System.out.println(prettyPrintJson(result.toString()));
        System.out.println("current milk: " + milk + " - current skins: " + skins );
    }

    public void herdToJson(){
        JSONArray herdList = new JSONArray();
        JSONObject herdJson = new JSONObject();

        for (Labyak yak : herd.getLabyaks()) {
            JSONObject labyakJson = new JSONObject();
            System.out.println(yak.getName());
            System.out.println(yak.getAge());
            System.out.println(yak.getSex());
            labyakJson.put("name", yak.getName());
            labyakJson.put("age", yak.getAge());
            labyakJson.put("age-last-shaven", yak.getAgeLastShaven());
            herdList.add(labyakJson);
        }
        herdJson.put("herd", herdList);
        System.out.println();
        System.out.println(prettyPrintJson(herdJson.toString()));
    }

    private String prettyPrintJson(String uglyJson){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonParser jp = new JsonParser();
        JsonElement je = jp.parse(uglyJson);
        return gson.toJson(je);
    }

    public static void main(String args[]) throws JAXBException {
        /*unMarshallingExample();
        for(Labyak yak : herd.getLabyaks())
        {
            System.out.println(yak.getName());
            System.out.println(yak.getAge());
            System.out.println(yak.getSex());
        }
        System.out.println();

        JSONArray herdList = new JSONArray();
        JSONObject herdJson = new JSONObject();

        calculateMilkSkins(14);
        System.out.println();
        for(Labyak yak : herd.getLabyaks())
        {
            JSONObject labyakJson = new JSONObject();
            System.out.println(yak.getName());
            System.out.println(yak.getAge());
            System.out.println(yak.getSex());
            labyakJson.put("name", yak.getName());
            labyakJson.put("age", yak.getAge());
            labyakJson.put("age-last-shaven", yak.getAgeLastShaven());
            herdList.add(labyakJson);
        }
        herdJson.put("herd",herdList);
        System.out.println();
        System.out.println(herdJson);
        System.out.println();

        try {
            order();
        } catch (ParseException e) {
            e.printStackTrace();
        }*/
    }
}
