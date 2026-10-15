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
import model.HewanDarat;
import model.PerawatanHewan;
/**
 *
 * @author ASUS
 */
public class HewanDaratDAO {
    private Connection conn;

    public HewanDaratDAO() {
        this.conn = Koneksi.getConnection();
    }
    
    public void tambah(HewanDarat hewan) {
        String sql = "INSERT INTO hewan_darat " + "(id, kecepatanLariKmJam) VALUES (?, ?)";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, hewan.getId());
            pstmt.setDouble(2, hewan.getKecepatanLariKmJam());

            pstmt.executeUpdate();
            System.out.println("Data kemampuan hewan darat berhasil ditambahkan!");
        } catch (SQLException e) {
            System.out.println("Gagal menambah data hewan darat - " + e.getMessage());
        }
    }
    
    public List<HewanDarat> ambilSemuaHewanDarat() {
        List<HewanDarat> daftarHewan = new ArrayList<>();

        String sql = "SELECT h.id, h.nama, h.jenis, h.umur, h.habitat, "
                + "p.idPerawatan, p.jenisPerawatan, p.tanggal, "
                + "hd.kecepatanLariKmJam "
                + "FROM hewan h "
                + "JOIN hewan_darat hd ON h.id = hd.id "
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

                double kecepatan = rs.getDouble("kecepatanLariKmJam");

                HewanDarat hewan = new HewanDarat(id, nama, jenis, umur, habitat, perawatan, kecepatan);
                daftarHewan.add(hewan);
            }
        } catch (SQLException e) {
            System.out.println("Gagal mengambil data hewan darat - " + e.getMessage());
        }

        return daftarHewan;
    }
    
    public void update(HewanDarat hewan) {
        String sql = "UPDATE hewan_darat " + "SET kecepatanLariKmJam = ? " + "WHERE id = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setDouble(1, hewan.getKecepatanLariKmJam());
            pstmt.setInt(2, hewan.getId());

            int baris = pstmt.executeUpdate();

            if (baris > 0) {
                System.out.println("Data kecepatan lari berhasil diubah!");
            } else {
                System.out.println("Data hewan darat tidak ditemukan!");
            }
        } catch (SQLException e) {
            System.out.println("Gagal mengubah data hewan darat - " + e.getMessage());
        }
    }
    
    public void hapus(int idHewan) {
        String sql = "DELETE FROM hewan_darat WHERE id = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idHewan);

            int baris = pstmt.executeUpdate();

            if (baris > 0) {
                System.out.println("Data kemampuan hewan darat berhasil dihapus!");
            } else {
                System.out.println("Data hewan darat tidak ditemukan!");
            }
        } catch (SQLException e) {
            System.out.println("Gagal menghapus data hewan darat - " + e.getMessage());
        }
    }
}
