package ru.eltech.project.client.clients;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.eltech.project.api.data.Client;

public class AuthClient {
    // GET
            /*URL url = new URL("http://localhost:8080/auth?login=16&password=44");
            URLConnection urlConnection = url.openConnection();
            InputStream inputStream = urlConnection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String s = reader.readLine();*/
    private static HttpURLConnection httpURLConnection;
    private static String AUTH_URL = "http://localhost:8080/auth";
    private static String REG_URL = "http://localhost:8080/registration";

    public static String authClient(String login, String password) {
        try {
            URL obj = new URL(AUTH_URL);
            httpURLConnection = (HttpURLConnection) obj.openConnection();
            httpURLConnection.setRequestMethod("POST");

            httpURLConnection.setDoOutput(true);
            OutputStream os = httpURLConnection.getOutputStream();
            String params = "login=" + login + "&password=" + password + "&isAdmin=false";
            os.write(params.getBytes());
            os.flush();
            os.close();

            int responseCode = httpURLConnection.getResponseCode();
            System.out.println("POST Response Code :: " + responseCode);

            if (responseCode == HttpURLConnection.HTTP_OK) { // success
                BufferedReader in = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                return response.toString();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return "Not found";
    }

    public static String authAdmin(String login, String password) {
        try {
            URL obj = new URL(AUTH_URL);
            httpURLConnection = (HttpURLConnection) obj.openConnection();
            httpURLConnection.setRequestMethod("POST");

            httpURLConnection.setDoOutput(true);
            OutputStream os = httpURLConnection.getOutputStream();
            String params = "login=" + login + "&password=" + password + "&isAdmin=true";
            os.write(params.getBytes());
            os.flush();
            os.close();

            int responseCode = httpURLConnection.getResponseCode();
            System.out.println("POST Response Code :: " + responseCode);

            if (responseCode == HttpURLConnection.HTTP_OK) { // success
                BufferedReader in = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                return response.toString();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return "Not found";
    }

    public static String registration(Client client, String login, String password) {
        try {
            URL obj = new URL(REG_URL);
            httpURLConnection = (HttpURLConnection) obj.openConnection();
            httpURLConnection.setRequestMethod("POST");

            httpURLConnection.setDoOutput(true);
            OutputStream os = httpURLConnection.getOutputStream();
            String params = "login=" + login + "&password=" + password + "&name=" + client.getName()
                    + "&address=" + client.getAddress() + "&mail=" + client.getEmail() + "&phone=" + client.getPhone();
            os.write(params.getBytes());
            os.flush();
            os.close();

            int responseCode = httpURLConnection.getResponseCode();
            System.out.println("POST Response Code :: " + responseCode);

            if (responseCode == HttpURLConnection.HTTP_OK) { // success
                BufferedReader in = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                return response.toString();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return "Not found";
    }

}
