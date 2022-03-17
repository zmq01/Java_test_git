package com.njau.shop.dao;

import com.njau.shop.model.Provider;
import com.njau.shop.model.UserInfo;
import com.njau.shop.util.ConnectionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProviderDao {

    /**
     * 取所有记录
     *
     * @return
     */
    public List<Provider> selAll() {
        List<Provider> list = new ArrayList<>();
        Connection connection = ConnectionDB.connDB();
        //String sql = "select * from provider";
        String sql = "select id,name,phone,address ,remark from provider where 1=1 ";
        try {
            PreparedStatement pst = connection.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Provider provider = new Provider();
                provider.setId(rs.getInt("id"));
                provider.setName(rs.getString("name"));
                provider.setPhone(rs.getString("phone"));
                provider.setAddress(rs.getString("address"));
                provider.setRemark(rs.getString("remark"));
                list.add(provider);
            }
            rs.close();
            pst.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    public void add(Provider provider){
        /*Connection connection = ConnectionDB.connDB();
        String sql = "insert into bill(goods_name,count,price,pay,provider_id,remark,add_date) " +
                "values(?,?,?,?,?,?,now())";
        try {
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setString(1,provider.getGoodsName());
            pst.setInt(2,provider.getCount());
            pst.setDouble(3,provider.getPrice());
            pst.setString(4,provider.getPay());
            pst.setInt(5,provider.getProviderId());
            pst.setString(6,provider.getRemark());
            pst.executeUpdate();
            pst.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }*/
    }
}
