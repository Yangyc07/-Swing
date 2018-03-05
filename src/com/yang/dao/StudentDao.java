
package com.yang.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import utils.dbutils;
import com.yang.model.Student;
import java.util.List;

import java.util.logging.Level;
import java.util.logging.Logger;

public class StudentDao {

    Connection conn = null;  
    PreparedStatement psmt = null; 
    ResultSet rs = null;
    Statement stas = null;

    public StudentDao() {
        super();
    }

    //查询所有的学生信息
    public ArrayList<Student> queryAllUser() {
        ArrayList<Student> list = new ArrayList<Student>();
        try {
            String sql = "select * from student";
            conn = dbutils.getConnection();
            psmt = conn.prepareStatement(sql);
            rs = psmt.executeQuery();
            while (rs.next()) {
                String stuid = rs.getString(1);
                String name = rs.getString(2);
                String gender = rs.getString(3);
                String age = rs.getString(4);
                Student student = new Student(stuid, name, gender, age);
                list.add(student);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    //添加学生信息
    public void addUser(String stuid, String name, String gender, String age) {
        try {
            conn = dbutils.getConnection();
            String sql = "insert into  student values('" + stuid + "','" + name + "','" + gender + "','" + age + "')";
            stas = conn.createStatement();
            stas.execute(sql);
        } catch (SQLException ex) {
            Logger.getLogger(StudentDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            dbutils.close(rs, stas, conn);
        }
    }

    //删除学生信息
    public boolean deleteUser(String stuid) {
        try {
            String sql = "delete from student where stuid='" + stuid + "'";
            conn = dbutils.getConnection();
            stas = conn.createStatement();
            stas.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(StudentDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            dbutils.close(rs, stas, conn);
        }
        return true;
    }

    //更新学生信息
    public boolean updateUser(String stuid, String name, String gender, String age) {
        String sql = "update student set  username = '" + name + "',gender='" + gender + "',age='" + age + "' where stuid='" + stuid + "'";
        try {
            conn = dbutils.getConnection();
            stas = conn.createStatement();
            stas.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(StudentDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            dbutils.close(rs, stas, conn);
        }
        return true;
    }
    //查询个人信息
    public Student query(String stuid) {
        Student student = null;
        try {
            String sql = "select *from student where stuid='" + stuid + "'";
            conn = dbutils.getConnection();
            psmt = conn.prepareStatement(sql);
            rs = psmt.executeQuery();
            while (rs.next()) {
                student = new Student();
                student.setStuid(rs.getString("stuid"));
                student.setName(rs.getString("username"));
                student.setGender(rs.getString("gender"));
                student.setAge(rs.getString("age"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return student;
    }
}
