package com.njau.shop.dao;

import com.njau.shop.model.UserInfo;
import com.njau.shop.util.ConnectionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao {

    /**
     * 判断是否登录成功
     * @param username
     * @param password
     * @return
     */
    public boolean isExist(String username,String password){
        boolean flag = false;
        String sql = "select count(*) num from user_data where username=? and password=?";
        Connection connection = ConnectionDB.connDB();
        try {
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setString(1,username);
            pst.setString(2,password);
            ResultSet rs = pst.executeQuery();
            if(rs.next()){
                int num = rs.getInt("num");
                //有此用户名和密码的用户
                if(num > 0) flag = true;
            }
            rs.close();
            pst.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 新增
     * @param userInfo
     */
    public void add(UserInfo userInfo){
        Connection connection = ConnectionDB.connDB();
        String sql = "insert into user_info(username,password,sex,age,phone,address) values(?,?,?,?,?,?)";
        try {
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setString(1,userInfo.getUsername());
            pst.setString(2,userInfo.getPassword());
            pst.setString(3,userInfo.getSex());
            pst.setInt(4,userInfo.getAge());
            pst.setString(5,userInfo.getPhone());
            pst.setString(6,userInfo.getAddress());
            pst.executeUpdate();
            pst.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 取所有记录
     * @return
     */
    public List<UserInfo> selAll(UserInfo qUserInfo){
        List<UserInfo> list = new ArrayList<>();
        Connection connection = ConnectionDB.connDB();
        String sql = "select id,username,password,sex,age,phone,address from user_info where 1=1 ";
        if(qUserInfo != null){
            String username = qUserInfo.getUsername();
            String sex = qUserInfo.getSex();
            if(username != null && !username.equals("")){//username有值
                sql += "and username like '" + username + "%' ";
            }
            if(sex != null && !sex.equals("")){
                sql += "and sex='" + sex + "' ";
            }
            System.out.println(sql);
        }
        try {
            PreparedStatement pst = connection.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                UserInfo userInfo = new UserInfo();
                userInfo.setId(rs.getInt("id"));
                userInfo.setUsername(rs.getString("username"));
                userInfo.setPassword(rs.getString("password"));
                userInfo.setSex(rs.getString("sex"));
                userInfo.setAge(rs.getInt("age"));
                userInfo.setPhone(rs.getString("phone"));
                userInfo.setAddress(rs.getString("address"));
                list.add(userInfo);
            }
            rs.close();
            pst.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 删除
     * @param id
     */
    public void del(Integer id){
        Connection connection = ConnectionDB.connDB();
        //String sql = "delete from user_info where id=?";
        String sql = "delete from user_info where id="+id;
        try {
            PreparedStatement pst = connection.prepareStatement(sql);
            //pst.setInt(1,id);
            pst.executeUpdate();
            pst.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据主键加载记录
     * @param id
     * @return
     */
    public UserInfo getById(Integer id){
        Connection connection = ConnectionDB.connDB();
        UserInfo userInfo = new UserInfo();
        String sql = "select id,username,password,sex,age,phone,address from user_info where id="+id;
        try {
            PreparedStatement pst = connection.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            if(rs.next()){
                userInfo.setId(rs.getInt("id"));
                userInfo.setUsername(rs.getString("username"));
                userInfo.setPassword(rs.getString("password"));
                userInfo.setSex(rs.getString("sex"));
                userInfo.setAge(rs.getInt("age"));
                userInfo.setPhone(rs.getString("phone"));
                userInfo.setAddress(rs.getString("address"));
            }
            pst.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userInfo;
    }

    /**
     * 修改
     * @param userInfo
     * @throws SQLException
     */
    public void update(UserInfo userInfo)  {
        Connection connection = ConnectionDB.connDB();
        String sql = "update user_info set username=?,password=?,sex=?,age=?,phone=?,address=? where id=?";
        PreparedStatement pst = null;
        try {
            pst = connection.prepareStatement(sql);
            pst.setString(1,userInfo.getUsername());
            pst.setString(2,userInfo.getPassword());
            pst.setString(3,userInfo.getSex());
            pst.setInt(4,userInfo.getAge());
            pst.setString(5,userInfo.getPhone());
            pst.setString(6,userInfo.getAddress());
            pst.setInt(7,userInfo.getId());
            pst.executeUpdate();
            pst.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args){
        /*UserDao userDao = new UserDao();
        List<UserInfo> list = userDao.selAll();
        for(UserInfo u: list){
            System.out.println(u);
        }*/
    }
}
