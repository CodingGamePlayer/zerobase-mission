package kr.co.zerobasemission.dao;

import kr.co.zerobasemission.dto.WifiDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class WifiDAO {

    public int insert(WifiDTO wifiDTO) {
        Connection conn = JDBCTemplate.getConnection();
        PreparedStatement pstmt = null;
        int result = 0;

        try {
            String sql = "INSERT INTO wifi_info VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, wifiDTO.getX_SWIFI_MGR_NO());
            pstmt.setString(2, wifiDTO.getX_SWIFI_WRDOFC());
            pstmt.setString(3, wifiDTO.getX_SWIFI_MAIN_NM());
            pstmt.setString(4, wifiDTO.getX_SWIFI_ADRES1());
            pstmt.setString(5, wifiDTO.getX_SWIFI_ADRES2());
            pstmt.setString(6, wifiDTO.getX_SWIFI_INSTL_FLOOR());
            pstmt.setString(7, wifiDTO.getX_SWIFI_INSTL_TY());
            pstmt.setString(8, wifiDTO.getX_SWIFI_INSTL_MBY());
            pstmt.setString(9, wifiDTO.getX_SWIFI_SVC_SE());
            pstmt.setString(10, wifiDTO.getX_SWIFI_CMCWR());
            pstmt.setString(11, wifiDTO.getX_SWIFI_CNSTC_YEAR());
            pstmt.setString(12, wifiDTO.getX_SWIFI_INOUT_DOOR());
            pstmt.setString(13, wifiDTO.getX_SWIFI_REMARS3());
            pstmt.setString(14, wifiDTO.getLAT());
            pstmt.setString(15, wifiDTO.getLNT());
            pstmt.setString(16, wifiDTO.getWORK_DTTM());

            result = pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTemplate.close(pstmt);
            JDBCTemplate.close(conn);
        }

        return result;
    }
}
