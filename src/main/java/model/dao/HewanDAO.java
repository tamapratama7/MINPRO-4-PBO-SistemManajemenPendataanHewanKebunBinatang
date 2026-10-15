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
import model.Hewan;
import model.HewanDarat;
import model.HewanAir;
import model.PerawatanHewan;
/**
 *
 * @author ASUS
 */
public class HewanDAO {
    private Connection conn;
    
    public HewanDAO(){
        this.conn = Koneksi.getConnection();
    }
    
    public void tambah(Hewan hewan) {
        String sql = "INSERT INTO hewan " + "(id, nama, jenis, umur, habitat) " + "VALUES (?, ?, ?, ?, ?)";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, hewan.getId());
            pstmt.setString(2, hewan.getNama());
            pstmt.setString(3, hewan.getJenis());
            pstmt.setInt(4, hewan.getUmur());
            pstmt.setString(5, hewan.getHabitat());
            
            pstmt.executeUpdate();
            System.out.println("Data hewan berhasil ditambahkan!");
        } 
        catch (SQLException e) {
                System.out.println("Gagal menambah data hewan - " + e.getMessage());
        }
    }
    
    public List<Hewan> ambilSemuaHewan() {
        List<Hewan> daftarHewan = new ArrayList<>();
        
        String sql = "SELECT h.id, h.nama, h.jenis, h.umur, h.habitat, "
               + "p.idPerawatan, p.jenisPerawatan, p.tanggal, "
               + "hd.kecepatanLariKmJam, "
               + "ha.kedalamanRenangMeter "
               + "FROM hewan h "
               + "LEFT JOIN perawatan p ON h.id = p.idHewan "
               + "LEFT JOIN hewan_darat hd ON h.id = hd.id "
               + "LEFT JOIN hewan_air ha ON h.id = ha.id";
        
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

                if (!rs.wasNull()) {
                    HewanDarat hewan = new HewanDarat(id, nama, jenis, umur, habitat, perawatan, kecepatan);
                    daftarHewan.add(hewan);

                } else {
                    double kedalaman = rs.getDouble("kedalamanRenangMeter");

                    if (!rs.wasNull()) {
                        HewanAir hewan = new HewanAir(id, nama, jenis, umur, habitat, perawatan, kedalaman);
                        daftarHewan.add(hewan);
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Gagal mengambil data hewan - " + e.getMessage());
        }

        return daftarHewan;
    }
    
    public void update(Hewan hewan){
        String sql = "UPDATE hewan " + "SET nama = ?, jenis = ?, umur = ?, habitat = ? " + "WHERE id = ?";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, hewan.getNama());
            pstmt.setString(2, hewan.getJenis());
            pstmt.setInt(3, hewan.getUmur());
            pstmt.setString(4, hewan.getHabitat());
            pstmt.setInt(5, hewan.getId());
            
            int baris = pstmt.executeUpdate();

            if (baris > 0) {
                System.out.println("Data hewan berhasil diubah!");
            } else {
                System.out.println("ID hewan tidak ditemukan!");
            }
        } catch (SQLException e){
            System.out.println("Gagal mengubah data hewan - " + e.getMessage());
        }
    }
    
    public void hapus(int idHewan) {
        String sql = "DELETE FROM hewan WHERE id = ?";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idHewan);
            
            int baris = pstmt.executeUpdate();

            if (baris > 0) {
                System.out.println("Data hewan berhasil dihapus!");
            } else {
                System.out.println("ID hewan tidak ditemukan!");
            } 
        } catch (SQLException e){
            System.out.println("Gagal menghapus data hewan - " + e.getMessage());
        }
    }
}
