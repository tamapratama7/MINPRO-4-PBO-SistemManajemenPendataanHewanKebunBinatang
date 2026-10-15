/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dao;
import config.Koneksi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.HewanAir;
import model.PerawatanHewan;
/**
 *
 * @author ASUS
 */
public class HewanAirDAO {
    private Connection conn;

    public HewanAirDAO() {
        this.conn = Koneksi.getConnection();
    }
    
    public void tambah(HewanAir hewan) {
        String sql = "INSERT INTO hewan_air " + "(id, kedalamanRenangMeter) VALUES (?, ?)";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, hewan.getId());
            pstmt.setDouble(2, hewan.getKedalamanRenangMeter());

            pstmt.executeUpdate();
            System.out.println("Data kemampuan hewan air berhasil ditambahkan!");
        } catch (SQLException e) {
            System.out.println("Gagal menambah data hewan air - " + e.getMessage());
        }
    }
    
    public List<HewanAir> ambilSemuaHewanAir() {
        List<HewanAir> daftarHewan = new ArrayList<>();

        String sql = "SELECT h.id, h.nama, h.jenis, h.umur, h.habitat, "
                + "p.idPerawatan, p.jenisPerawatan, p.tanggal, "
                + "ha.kedalamanRenangMeter "
                + "FROM hewan h "
                + "JOIN hewan_air ha ON h.id = ha.id "
                + "LEFT JOIN perawatan p ON h.id = p.idHewan";

        try (PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String nama = rs.getString("nama");
                String jenis = rs.getString("jenis");
                int umur = rs.getInt("umur");
                String habitat = rs.getString("habitat");

                PerawatanHewan perawatan = null;
                int idPerawatan = rs.getInt("idPerawatan");

                if (!rs.wasNull()) {
                    perawatan = new PerawatanHewan(
                            idPerawatan,
                            rs.getString("jenisPerawatan"),
                            rs.getString("tanggal")
                    );
                }

                double kedalaman = rs.getDouble("kedalamanRenangMeter");

                HewanAir hewan = new HewanAir(id, nama, jenis, umur, habitat, perawatan, kedalaman);
                daftarHewan.add(hewan);
            }
        } catch (SQLException e) {
            System.out.println("Gagal mengambil data hewan air - " + e.getMessage());
        }

        return daftarHewan;
    }
    
    public void update(HewanAir hewan) {
        String sql = "UPDATE hewan_air " + "SET kedalamanRenangMeter = ? WHERE id = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setDouble(1, hewan.getKedalamanRenangMeter());
            pstmt.setInt(2, hewan.getId());

            int baris = pstmt.executeUpdate();

            if (baris > 0) {
                System.out.println("Data kedalaman renang berhasil diubah!");
            } else {
                System.out.println("Data hewan air tidak ditemukan!");
            }
        } catch (SQLException e) {
            System.out.println("Gagal mengubah data hewan air - " + e.getMessage());
        }
    }
    
    public void hapus(int idHewan) {
        String sql = "DELETE FROM hewan_air WHERE id = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idHewan);

            int baris = pstmt.executeUpdate();

            if (baris > 0) {
                System.out.println("Data kemampuan hewan air berhasil dihapus!");
            } else {
                System.out.println("Data hewan air tidak ditemukan!");
            }
        } catch (SQLException e) {
            System.out.println("Gagal menghapus data hewan air - " + e.getMessage());
        }
    }
}
