package com.njau.shop.dao;

import com.njau.shop.model.Bill;
import com.njau.shop.util.ConnectionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BillDao {

    public List<Bill> list(){
        List<Bill> list = new ArrayList<>();
        //String sql = "select b.id,b.goods_name,b.count,b.price,b.pay,b.remark,b.add_date,p.id pid,p.name " +
                    // "from bill b inner join provider p on b.provider_id=p.id ";
        String sql = "select id,goods_name,count,price,pay,remark,add_data,provider_id from bill where 1=1 ";
        Connection connection = ConnectionDB.connDB();
        try {
            PreparedStatement pst = connection.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                Bill bill = new Bill();
                bill.setId(rs.getInt("id"));
                bill.setGoodsName(rs.getString("goods_name"));
                bill.setCount(rs.getInt("count"));
                bill.setPrice(rs.getDouble("price"));
                bill.setPay(rs.getString("pay"));
                bill.setRemark(rs.getString("remark"));
                bill.setAddDate(rs.getString("add_data"));
                bill.setProviderId(rs.getString("provider_id"));
                //bill.setProviderId(rs.getInt("pid"));
                //bill.setProviderName(rs.getString("name"));
                list.add(bill);
            }
            rs.close();
            pst.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void add(Bill bill){
        Connection connection = ConnectionDB.connDB();
        String sql = "insert into bill(goods_name,count,price,pay,provider_id,remark,add_date) " +
                     "values(?,?,?,?,?,?,now())";
        try {
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setString(1,bill.getGoodsName());
            pst.setInt(2,bill.getCount());
            pst.setDouble(3,bill.getPrice());
            pst.setString(4,bill.getPay());
            pst.setString(5,bill.getProviderId());
            pst.setString(6,bill.getRemark());
            pst.executeUpdate();
            pst.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
