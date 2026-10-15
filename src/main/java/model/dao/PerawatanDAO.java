/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.PerawatanHewan;
import config.Koneksi;

/**
 *
 * @author ASUS
 */
public class PerawatanDAO {
    private Connection conn;
    
    public PerawatanDAO(){
        this.conn = Koneksi.getConnection();
    }
    
    
    public void tambah(int idHewan, PerawatanHewan perawatan){
        if (!cekIdHewan(idHewan)) {
            System.out.println("ID hewan tidak ditemukan");
            return;
        }
        
        String sql = "INSERT INTO perawatan " + "(idPerawatan, idHewan, JenisPerawatan, tanggal) " + "VALUES (?, ?, ?, ?)";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, perawatan.getIdPerawatan());
            pstmt.setInt(2, idHewan);
            pstmt.setString(3, perawatan.getJenisPerawatan());
            pstmt.setString(4, perawatan.getTanggal());
            
            pstmt.executeUpdate();
            System.out.println("Data Perawatan Berhasil Ditambahkan!");
        } catch (SQLException e) {
            System.out.println("Gagal menambah perawatan - " + e.getMessage());
        }
    }
    
    public List<PerawatanHewan> ambilSemuaPerawatan(){
        List<PerawatanHewan> daftarPerawatan = new ArrayList<>();
        
        String sql = "SELECT * FROM perawatan";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery()){
            
            while (rs.next()){
                int idPerawatan = rs.getInt("idPerawatan");
                String jenisPerawatan = rs.getString("jenisPerawatan");
                String tanggal = rs.getString("tanggal");
                
                PerawatanHewan perawatan = new PerawatanHewan(idPerawatan, jenisPerawatan, tanggal);
                
                daftarPerawatan.add(perawatan);
            }
        } catch (SQLException e){
            System.out.println("Gagal mengambil data perawatan - " + e.getMessage());
        }
        
        return daftarPerawatan;
    }
    
    public void updatePerawatan(int idHewan, PerawatanHewan perawatan){
        if (!cekIdHewan(idHewan)) {
            System.out.println("ID hewan tidak ditemukan!");
            return;
        }
        
        String sql = "UPDATE perawatan " + "SET jenisPerawatan = ?, tanggal = ? " + "WHERE idHewan = ?";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, perawatan.getJenisPerawatan());
            pstmt.setString(2, perawatan.getTanggal());
            pstmt.setInt(3, idHewan);
            
            int baris = pstmt.executeUpdate();
            
            if (baris > 0){
                System.out.println("Data perawatan berhasil diubah!");
            } else {
                System.out.println("Data perawatan tidak ditemukan!");
            }
        } catch (SQLException e){
            System.out.println("Gagal mengubah data perawatan - " + e.getMessage());
        }
    }
    
    public void hapusPerawatan(int idHewan){
        if (!cekIdHewan(idHewan)) {
            System.out.println("ID hewan tidak ditemukan!");
            return;
        }
        
        String sql = "DELETE FROM perawatan WHERE idHewan = ?";
        
        
        try(PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setInt(1, idHewan);
            
            int baris = pstmt.executeUpdate();
            
            if (baris > 0){
                System.out.println("Data perawatan berhasil dihapus!");
            } else {
                System.out.println("Data perawatan tidak ditemukan!");
            }
        } catch (SQLException e){
            System.out.println("Gagal menghapus data perawatan - " +  e.getMessage());
        }
    }
    
    
    
    private boolean cekIdHewan(int idHewan) {
        String sql = "SELECT id FROM hewan WHERE id = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idHewan);

            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next();
            }

        } catch (SQLException e) {
            System.out.println(
                    "Gagal memeriksa ID hewan - " + e.getMessage()
            );
            return false;
        }
    }
}
