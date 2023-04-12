package kr.co.zerobasemission.wifi;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WifiDTO {

    private String X_SWIFI_MGR_NO; // 관리번호
    private String X_SWIFI_WRDOFC;
    private String X_SWIFI_MAIN_NM;
    private String X_SWIFI_ADRES1;
    private String X_SWIFI_ADRES2;
    private String X_SWIFI_INSTL_FLOOR;
    private String X_SWIFI_INSTL_TY;
    private String X_SWIFI_INSTL_MBY;
    private String X_SWIFI_SVC_SE;
    private String X_SWIFI_CMCWR;
    private String X_SWIFI_CNSTC_YEAR;
    private String X_SWIFI_INOUT_DOOR;
    private String X_SWIFI_REMARS3;
    private String LAT;
    private String LNT;
    private String WORK_DTTM;
}

