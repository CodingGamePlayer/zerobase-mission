package kr.co.zerobasemission.service;

import com.google.gson.Gson;
import com.squareup.okhttp.*;
import kr.co.zerobasemission.dao.WifiDAO;
import kr.co.zerobasemission.dto.WifiDTO;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public enum WifiService {
    INSTANCE;

    public String getTotalNumber() throws IOException {

        return makeAPIUrl(1, 1).getJSONObject("TbPublicWifiInfo").get("list_total_count").toString();
    }

    public void registerDB() throws IOException {
        int totalNumber = Integer.parseInt(getTotalNumber());

        JSONObject jsonObject = makeAPIUrl(1, 15);

        JSONArray jsonArray = jsonObject.getJSONObject("TbPublicWifiInfo").getJSONArray("row");

        List<WifiDTO> wifiDTOS = new ArrayList<>();

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject rowObject = jsonArray.getJSONObject(i);
            Gson gson = new Gson();
            WifiDTO wifiDTO = gson.fromJson(rowObject.toString(), WifiDTO.class);
            wifiDTOS.add(wifiDTO);
        }
        for (int i = 0; i < 10; i++) {
            new WifiDAO().insert(wifiDTOS.get(i));
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
