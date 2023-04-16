package kr.co.zerobasemission.service;

import com.google.gson.Gson;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import kr.co.zerobasemission.dao.WifiDAO;
import kr.co.zerobasemission.domain.WifiVO;
import kr.co.zerobasemission.dto.WifiDTO;
import lombok.extern.log4j.Log4j2;
import org.json.JSONArray;
import org.json.JSONObject;
import org.modelmapper.ModelMapper;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
public enum WifiService {
    INSTANCE;

    private WifiDAO wifiDAO;
    private ModelMapper modelMapper;

    WifiService() {
        this.wifiDAO = new WifiDAO();
        this.modelMapper = new ModelMapper();
    }
    public List<WifiDTO> selectAll() {
        List<WifiVO> wifiVOS = wifiDAO.selectAll();

        return wifiVOS.stream()
                .map(vo -> modelMapper.map(vo, WifiDTO.class))
                .collect(Collectors.toList());
    }

    public String getTotalNumber() throws IOException {

        return makeAPIUrl(1, 1).getJSONObject("TbPublicWifiInfo").get("list_total_count").toString();
    }

    public void registerDB() throws IOException {
        int totalNumber = Integer.parseInt(getTotalNumber());

        JSONObject jsonObject = makeAPIUrl(1, 15);

        JSONArray jsonArray = jsonObject.getJSONObject("TbPublicWifiInfo").getJSONArray("row");

        List<WifiDTO> wifiDTOS = new ArrayList<>();

        for (int i = 1; i < jsonArray.length(); i++) {
            JSONObject rowObject = jsonArray.getJSONObject(i);
            Gson gson = new Gson();
            WifiDTO wifiDTO = gson.fromJson(rowObject.toString(), WifiDTO.class);
            wifiDTO.setWifi_id(i);
            log.info(wifiDTO);
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
