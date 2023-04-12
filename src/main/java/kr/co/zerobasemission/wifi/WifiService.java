package kr.co.zerobasemission.wifi;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.squareup.okhttp.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public enum WifiService {
    INSTANCE;

    public String getTotalNumber() throws IOException {

        return makeAPIUrl(1, 1).getJSONObject("TbPublicWifiInfo").get("list_total_count").toString();
    }

    public void registerDB(int end) throws IOException {
        int totalNumber = Integer.parseInt(getTotalNumber());

        JSONObject jsonObject = makeAPIUrl(1, totalNumber);

        JSONArray jsonArray = jsonObject.getJSONObject("TbPublicWifiInfo").getJSONArray("row");

        List<WifiDTO> wifiDTOS = new ArrayList<>();

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject rowObject = jsonArray.getJSONObject(i);
            Gson gson = new Gson();
            WifiDTO wifiDTO = gson.fromJson(rowObject.toString(), WifiDTO.class);
            wifiDTOS.add(wifiDTO);
        }

    }


    private JSONObject makeAPIUrl(int start, int end) throws IOException {
        StringBuilder urlBuilder = new StringBuilder("http://openapi.seoul.go.kr:8088");
        urlBuilder.append("/" + URLEncoder.encode("515a524e6f6f74773834526e586d58", "UTF-8"));
        urlBuilder.append("/" + URLEncoder.encode("json", "UTF-8"));
        urlBuilder.append("/" + URLEncoder.encode("TbPublicWifiInfo", "UTF-8"));
        urlBuilder.append("/" + URLEncoder.encode(String.valueOf(start), "UTF-8"));
        urlBuilder.append("/" + URLEncoder.encode(String.valueOf(end), "UTF-8"));

        return requestAPI(urlBuilder.toString());
    }

    private JSONObject requestAPI(String url) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();
        String jsonData = response.body().string();

        return new JSONObject(jsonData);
    }
}
