/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AksesRMI;

import Entitas.Mahasiswa;
import Services.ServiceMahasiswa;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import server.Koneksi;

/**
 *
 * @author ngadmin
 */
public class AksesMahasiswa extends UnicastRemoteObject implements ServiceMahasiswa {

    public AksesMahasiswa() throws RemoteException {
    }

    @Override
    public List getMahasiswa() throws RemoteException {
        List listMahasiswa = new ArrayList<>();
        Statement statement = null;
        try {
            statement = Koneksi.getConnection().createStatement();
            try (ResultSet resultSet = statement.executeQuery("select * from mahasiswa")) {
                while (resultSet.next()) {
                    Mahasiswa mahasiswa = new Mahasiswa();
                    mahasiswa.setNim(resultSet.getString(1));
                    mahasiswa.setNama(resultSet.getString(2));
                    mahasiswa.setProdi(resultSet.getString(3));
                    listMahasiswa.add(mahasiswa);

                }
            }
            return listMahasiswa;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (Exception e) {
                }

            }
        }
    }

    /**
     *
     * @param mahasiswa
     * @throws RemoteException
     */
    @Override
    public void insert(Mahasiswa mahasiswa) throws RemoteException {
        Statement statement = null;
        try {
            statement = Koneksi.getConnection().createStatement();
            statement.executeUpdate("insert into mahasiswa values ('" + mahasiswa.getNim() + "'"
                    + ",'" + mahasiswa.getNama() + "','" + mahasiswa.getProdi() + "')");

        } catch (Exception e) {
            System.out.println(e.getMessage());

        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (Exception e) {
                }

            }
        }
    }

    @Override
    public void update(Mahasiswa mahasiswa) throws RemoteException {
        Statement statement = null;
        try {
            statement = Koneksi.getConnection().createStatement();
            statement.executeUpdate("update mahasiswa set nama='" + mahasiswa.getNama()
                    + "',prodi='" + mahasiswa.getProdi() + "'where nim='" + mahasiswa.getNim() + "'");

        } catch (Exception e) {
            System.out.println(e.getMessage());

        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (Exception e) {
                }

            }
        }
    }

    @Override
    public void delete(Mahasiswa mahasiswa) throws RemoteException {
        Statement statement = null;
        try {
            statement = Koneksi.getConnection().createStatement();
            statement.executeUpdate("delete from mahasiswa where nim='" + mahasiswa.getNim() + "'");

        } catch (Exception e) {
            System.out.println(e.getMessage());

        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (Exception e) {
                }

            }
        }
    }
}
