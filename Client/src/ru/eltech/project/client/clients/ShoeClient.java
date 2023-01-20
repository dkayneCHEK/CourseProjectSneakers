package ru.eltech.project.client.clients;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import ru.eltech.project.api.data.Shoe;
import ru.eltech.project.api.data.Size;
import ru.eltech.project.client.DataManager;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.List;

public class ShoeClient {
    private static HttpURLConnection httpURLConnection;
    private static String ADD_SHOE_URL = "http://localhost:8080/addShoe";
    private static String ADD_SHOE_SIZE_URL = "http://localhost:8080/addShoeSize";
    private static String UPDATE_SHOE_URL = "http://localhost:8080/updateShoe";
    private static String GET_ALL_SHOE_URL = "http://localhost:8080/getAllShoes";
    private static String GET_ALL_SHOE_SIZES_URL = "http://localhost:8080/getAllShoeSize";
    private static String DEL_SHOE_URL = "http://localhost:8080/delShoe";

    public static String addShoe(Shoe shoe) {
        try {
            URL url = new URL(ADD_SHOE_URL + "?name=" + prepareParam(shoe.getName()) + "&about=" + prepareParam(shoe.getAbout()) +
                    "&brand=" + prepareParam(shoe.getBrand()) + "&color=" + prepareParam(shoe.getColor()) + "&sale=" + shoe.getSale() +
                    "&price=" + shoe.getPrice() + "&articul=" + prepareParam(shoe.getArticul()) + "&picture=" + prepareParam(shoe.getPicture()));
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");

            int responseCode = httpURLConnection.getResponseCode();
            System.out.println("POST Response Code :: " + responseCode);

            if (responseCode == HttpURLConnection.HTTP_OK) { // success
                DataManager.addToCATALOG(shoe);
                BufferedReader in = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                return response.toString();
            }
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String s = reader.readLine();
            return s;
            //TODO: ADD IMAGE LOAD
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void addShoeSize(Size size) {
        try {
            URL url = new URL(ADD_SHOE_SIZE_URL + "?size=" + size.getSize() + "&shoe_id=" + prepareParam(size.getShoeId()) +
                    "&quantity=" + size.getQuantity());
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");

            int responseCode = httpURLConnection.getResponseCode();
            System.out.println("POST Response Code :: " + responseCode);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Shoe> getAllShoes() {
        try {
            URL url = new URL(GET_ALL_SHOE_URL);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");

            int responseCode = httpURLConnection.getResponseCode();
            System.out.println("POST Response Code :: " + responseCode);

            if (responseCode == HttpURLConnection.HTTP_OK) { // success
                ObjectMapper objectMapper = new ObjectMapper();
                objectMapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
                objectMapper.enable(
                        DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
                CollectionType javaType = objectMapper.getTypeFactory()
                        .constructCollectionType(List.class, Shoe.class);
                List<Shoe> res = objectMapper.readValue(httpURLConnection.getInputStream(), javaType);

                return res;
            }
            return null;
            //TODO: ADD IMAGE LOAD
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void delShoe(String id) {
        try {
            URL url = new URL(DEL_SHOE_URL + "?id=" + id);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");

            int responseCode = httpURLConnection.getResponseCode();
            System.out.println("POST Response Code :: " + responseCode);

            if (responseCode == HttpURLConnection.HTTP_OK) { // success
                DataManager.delFromCATALOG(id);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void updateShoe(Shoe shoe) {
        try {
            URL url = new URL(UPDATE_SHOE_URL + "?name=" + prepareParam(shoe.getName()) + "&about=" + prepareParam(shoe.getAbout()) +
                    "&brand=" + prepareParam(shoe.getBrand()) + "&color=" + prepareParam(shoe.getColor()) + "&sale=" + shoe.getSale() +
                    "&price=" + shoe.getPrice() + "&articul=" + prepareParam(shoe.getArticul()) + "&picture=" + prepareParam(shoe.getPicture()) + "&id=" + prepareParam(shoe.getId()));
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");

            int responseCode = httpURLConnection.getResponseCode();
            System.out.println("POST Response Code :: " + responseCode);

            if (responseCode == HttpURLConnection.HTTP_OK) { // success
                DataManager.setCATALOG(ShoeClient.getAllShoes());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Size> getAllShoeSizes(String shoe_id) {
        try {
            URL url = new URL(GET_ALL_SHOE_SIZES_URL + "?shoe_id=" + shoe_id);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");

            int responseCode = httpURLConnection.getResponseCode();
            System.out.println("POST Response Code :: " + responseCode);

            if (responseCode == HttpURLConnection.HTTP_OK) { // success
                ObjectMapper objectMapper = new ObjectMapper();
                objectMapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
                objectMapper.enable(
                        DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
                CollectionType javaType = objectMapper.getTypeFactory()
                        .constructCollectionType(List.class, Size.class);
                List<Size> res = objectMapper.readValue(httpURLConnection.getInputStream(), javaType);

                return res;
            }
            return null;
            //TODO: ADD IMAGE LOAD
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String prepareParam(String param) throws UnsupportedEncodingException {
        return URLEncoder.encode(param, "UTF-8");
    }
}
