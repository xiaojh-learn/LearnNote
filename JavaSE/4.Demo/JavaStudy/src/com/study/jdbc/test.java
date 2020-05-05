package com.study.jdbc;
import java.sql.*;

public class test {
    public static final String URL = "jdbc:MySql://localhost:3306/myemployees";
    public static final String USER = "root";
    public static final String PASSWORD = "123456";

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //1.加载驱动程序
        Class.forName("com.mysql.jdbc.Driver");

        //2.获取数据库连接
        Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);

        //3.操作数据库，实现增删改查
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT last_name, salary FROM employees");

        //4.如果有数据，rs.next()返回true
        while(rs.next()){
            System.out.println(rs.getString("last_name") + " salary                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       ：" + rs.getInt("salary"));
        }
    }
}
