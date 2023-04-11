package kr.co.zerobasemission.wifi;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WifiDTO {

    private String manageNum;
    private String gu;
    private String wifiName;
    private String roadName;
    private String detailAddress;
    private String floor;
    private String type;
    private String manager;
    private String serviceType;
    private String wifiType;
    private String year;
    private String inOut;
    private String environment;
    private float x;
    private float y;
    private String workDate;

    public WifiDTO(float x, float y) {
        this.x = x;
        this.y = y;
    }
}
