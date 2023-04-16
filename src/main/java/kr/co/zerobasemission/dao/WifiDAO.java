package kr.co.zerobasemission.dao;

import kr.co.zerobasemission.dto.WifiDTO;
import kr.co.zerobasemission.domain.WifiVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WifiDAO {

    public int insert(WifiDTO wifiDTO) {
        Connection conn = JDBCTemplate.getConnection();
        PreparedStatement pstmt = null;
        int result = 0;

        try {
            String sql = "INSERT INTO wifi_info VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, wifiDTO.getWifi_id());
            pstmt.setString(2, wifiDTO.getX_SWIFI_MGR_NO());
            pstmt.setString(3, wifiDTO.getX_SWIFI_WRDOFC());
            pstmt.setString(4, wifiDTO.getX_SWIFI_MAIN_NM());
            pstmt.setString(5, wifiDTO.getX_SWIFI_ADRES1());
            pstmt.setString(6, wifiDTO.getX_SWIFI_ADRES2());
            pstmt.setString(7, wifiDTO.getX_SWIFI_INSTL_FLOOR());
            pstmt.setString(8, wifiDTO.getX_SWIFI_INSTL_TY());
            pstmt.setString(9, wifiDTO.getX_SWIFI_INSTL_MBY());
            pstmt.setString(10, wifiDTO.getX_SWIFI_SVC_SE());
            pstmt.setString(11, wifiDTO.getX_SWIFI_CMCWR());
            pstmt.setString(12, wifiDTO.getX_SWIFI_CNSTC_YEAR());
            pstmt.setString(13, wifiDTO.getX_SWIFI_INOUT_DOOR());
            pstmt.setString(14, wifiDTO.getX_SWIFI_REMARS3());
            pstmt.setString(15, wifiDTO.getLAT());
            pstmt.setString(16, wifiDTO.getLNT());
            pstmt.setString(17, wifiDTO.getWORK_DTTM());

            result = pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTemplate.close(conn);
            JDBCTemplate.close(pstmt);
        }

        return result;
    }

    public List<WifiVO> selectAll() {
        Connection conn = JDBCTemplate.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<WifiVO> list = new ArrayList<>();

        try {
            String sql = "SELECT * FROM wifi_info";
            pstmt = conn.prepareStatement(sql);

            rs = pstmt.executeQuery();


            while (rs.next()) {
                WifiVO wifiVO = WifiVO.builder()
                        .wifi_id(rs.getInt("wifi_id"))
                        .X_SWIFI_MGR_NO(rs.getString("X_SWIFI_MGR_NO"))
                        .X_SWIFI_WRDOFC(rs.getString("X_SWIFI_WRDOFC"))
                        .X_SWIFI_MAIN_NM(rs.getString("X_SWIFI_MAIN_NM"))
                        .X_SWIFI_ADRES1(rs.getString("X_SWIFI_ADRES1"))
                        .X_SWIFI_ADRES2(rs.getString("X_SWIFI_ADRES2"))
                        .X_SWIFI_INSTL_FLOOR(rs.getString("X_SWIFI_INSTL_FLOOR"))
                        .X_SWIFI_INSTL_TY(rs.getString("X_SWIFI_INSTL_TY"))
                        .X_SWIFI_INSTL_MBY(rs.getString("X_SWIFI_INSTL_MBY"))
                        .X_SWIFI_SVC_SE(rs.getString("X_SWIFI_SVC_SE"))
                        .X_SWIFI_CMCWR(rs.getString("X_SWIFI_CMCWR"))
                        .X_SWIFI_CNSTC_YEAR(rs.getString("X_SWIFI_CNSTC_YEAR"))
                        .X_SWIFI_INOUT_DOOR(rs.getString("X_SWIFI_INOUT_DOOR"))
                        .X_SWIFI_REMARS3(rs.getString("X_SWIFI_REMARS3"))
                        .LAT(rs.getString("LAT"))
                        .LNT(rs.getString("LNT"))
                        .WORK_DTTM(rs.getString("WORK_DTTM"))
                        .build();

                list.add(wifiVO);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCTemplate.close(conn);
            JDBCTemplate.close(pstmt);
            JDBCTemplate.close(rs);
        }


        return list;
    }

    public WifiVO selectOne(Long id) {
        Connection conn = JDBCTemplate.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        WifiVO wifiVO = null;

        try {
            String sql = "SELECT * FROM wifi_info WHERE wifi_id = ?";
            pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, Math.toIntExact(id));

            rs = pstmt.executeQuery();


            wifiVO = WifiVO.builder()
                    .wifi_id(rs.getInt("wifi_id"))
                    .X_SWIFI_MGR_NO(rs.getString("X_SWIFI_MGR_NO"))
                    .X_SWIFI_WRDOFC(rs.getString("X_SWIFI_WRDOFC"))
                    .X_SWIFI_MAIN_NM(rs.getString("X_SWIFI_MAIN_NM"))
                    .X_SWIFI_ADRES1(rs.getString("X_SWIFI_ADRES1"))
                    .X_SWIFI_ADRES2(rs.getString("X_SWIFI_ADRES2"))
                    .X_SWIFI_INSTL_FLOOR(rs.getString("X_SWIFI_INSTL_FLOOR"))
                    .X_SWIFI_INSTL_TY(rs.getString("X_SWIFI_INSTL_TY"))
                    .X_SWIFI_INSTL_MBY(rs.getString("X_SWIFI_INSTL_MBY"))
                    .X_SWIFI_SVC_SE(rs.getString("X_SWIFI_SVC_SE"))
                    .X_SWIFI_CMCWR(rs.getString("X_SWIFI_CMCWR"))
                    .X_SWIFI_CNSTC_YEAR(rs.getString("X_SWIFI_CNSTC_YEAR"))
                    .X_SWIFI_INOUT_DOOR(rs.getString("X_SWIFI_INOUT_DOOR"))
                    .X_SWIFI_REMARS3(rs.getString("X_SWIFI_REMARS3"))
                    .LAT(rs.getString("LAT"))
                    .LNT(rs.getString("LNT"))
                    .WORK_DTTM(rs.getString("WORK_DTTM"))
                    .build();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCTemplate.close(conn);
            JDBCTemplate.close(pstmt);
            JDBCTemplate.close(rs);
        }

        return wifiVO;
    }
}
