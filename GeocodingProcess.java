package MainGeocod;

import Geocoding.JsonReader;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by 1234 on 4/2/2017.
 */
public class GeocodingProcess{

    JSONObject response;

    public String name;
    public String adName;
    public String initialAddress;
    public String lat;
    public String lng;
    public String region;


    public GeocodingProcess(String location) throws IOException {

        String url = "https://geocode-maps.yandex.ru/1.x/?format=json&geocode=" + location + "&results=1";

        response = JsonReader.read(url);

    }






    public String getArea(){

       JSONObject geoObjectCollection = response.getJSONObject("response")
                .getJSONObject("GeoObjectCollection");


        JSONObject featureMember = geoObjectCollection.getJSONArray("featureMember").getJSONObject(0);

        JSONObject geoObject = featureMember.getJSONObject("GeoObject");

        JSONObject metaDataProperty = geoObject.getJSONObject("metaDataProperty")

                .getJSONObject("GeocoderMetaData")
                .getJSONObject("Address");

        JSONObject components = metaDataProperty.getJSONArray("Components").getJSONObject(2);


        region = components.getString("name");


        return region;
    }


    public String getLat() {

        JSONObject geoObjectCollection = response.getJSONObject("response")
                .getJSONObject("GeoObjectCollection");


        JSONObject featureMember = geoObjectCollection.getJSONArray("featureMember").getJSONObject(0);


        JSONObject geoObject = featureMember.getJSONObject("GeoObject");

        JSONObject point = geoObject.getJSONObject("Point");
        String loc_coordinates = point.getString("pos");

        String[] coordinates = loc_coordinates.split(" ");

        return lat=coordinates[1];
    }

    public String getLng() {

        JSONObject geoObjectCollection = response.getJSONObject("response")
                .getJSONObject("GeoObjectCollection");


        JSONObject featureMember = geoObjectCollection.getJSONArray("featureMember").getJSONObject(0);


        JSONObject geoObject = featureMember.getJSONObject("GeoObject");

        JSONObject point = geoObject.getJSONObject("Point");
        String loc_coordinates = point.getString("pos");

        String[] coordinates = loc_coordinates.split(" ");

        return lng=coordinates[0];
    }
}
