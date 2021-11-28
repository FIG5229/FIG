package com.embracesource.traffic.base.utils;


import com.embracesource.traffic.time.dto.YmlDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import javax.annotation.PostConstruct;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

@Component
public class StaticDataLoader {

    public static final Map<String,String> LJZD = new HashMap<>();
    public static final Map<String,String> ZMZD = new HashMap<>();
    public static final Map<String,String> ZMLJ = new HashMap<>();

    @Autowired
    private YmlDTO ymlDTO;


    @PostConstruct
    public void init(){
        try {
            String driver = ymlDTO.getDriver();
            String url = ymlDTO.getUrl();
            String username = ymlDTO.getUsername();
            String pwd = ymlDTO.getPassword();
            Class.forName(driver);
            Connection conn= DriverManager.getConnection(url,username,pwd);
            getLjZd(conn);
            getZmZd(conn);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private static void getLjZd(Connection conn) {

        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "select ljpym,ljjc from view_ljzd";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()){
                LJZD.put(rs.getString("ljpym").trim(),rs.getString("ljjc"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                if(rs != null){
                    rs.close();
                }
                if (ps != null){
                    ps.close();
                }
            }catch (Exception ex){}
        }

    }

    private static void getZmZd(Connection conn) {

        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "select * from DIC_STATION where dblm is not null";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()){
                ZMZD.put(rs.getString("dblm"),rs.getString("ZM"));
                ZMLJ.put(rs.getString("dblm"),rs.getString("ljpym"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                if(rs != null){
                    rs.close();
                }
                if (ps != null){
                    ps.close();
                }
            }catch (Exception ex){}
        }
    }


}
