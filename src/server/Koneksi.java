/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author ngadmin
 */
public class Koneksi {

    private static Connection connection = null;

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection("jdbc:mysql://192.168.1.204:3306/mahasiswa", "root", "");
        } catch (Exception e) {
            System.out.println(e.getMessage());

        }
        return connection;
    }

    public static void main(String[] args) {

        Koneksi.getConnection();

    }

}
