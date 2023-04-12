package kr.co.zerobasemission.dao;

import java.sql.*;

public class JDBCTemplate {

    public static Connection getConnection() {

        Connection conn = null;
        String url = "jdbc:sqlite:C:\\Users\\otw19\\OneDrive\\바탕 화면\\Project\\ZeroBase\\zerobase-study\\zerobase.db";

        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(url);


            Statement stmt = conn.createStatement();

            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS wifi_info(" +
                    "X_SWIFI_MGR_NO string, " +
                    "X_SWIFI_WRDOFC string, " +
                    "X_SWIFI_MAIN_NM string, " +
                    "X_SWIFI_ADRES1 string, " +
                    "X_SWIFI_ADRES2 string, " +
                    "X_SWIFI_INSTL_FLOOR string, " +
                    "X_SWIFI_INSTL_TY string, " +
                    "X_SWIFI_INSTL_MBY string, " +
                    "X_SWIFI_SVC_SE string, " +
                    "X_SWIFI_CMCWR string, " +
                    "X_SWIFI_CNSTC_YEAR string, " +
                    "X_SWIFI_INOUT_DOOR string, " +
                    "X_SWIFI_REMARS3 string, " +
                    "LAT string, " +
                    "LNT string, " +
                    "WORK_DTTM string" +
                    ")");

            System.out.println("DB Connect Success!");

        } catch (Exception e) {
            System.err.println("Exception occurred in " + e.getClass().getName() + ": " + e.getMessage());
        }

        return conn;
    }

    public static void close (Connection conn) {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (Exception e) {
            System.err.println("Exception occurred in " + e.getClass().getName() + ": " + e.getMessage());
        }
    }

    public static void close(PreparedStatement pstmt) {
        try {
            if(pstmt != null && !pstmt.isClosed())
                pstmt.close();
        } catch (SQLException e) {
            System.err.println("Exception occurred in " + e.getClass().getName() + ": " + e.getMessage());
        }
    }

    public static void close(ResultSet rs) {
        try {
            if(rs != null && !rs.isClosed())
                rs.close();
        } catch (SQLException e) {
            System.err.println("Exception occurred in " + e.getClass().getName() + ": " + e.getMessage());
        }
    }
}
