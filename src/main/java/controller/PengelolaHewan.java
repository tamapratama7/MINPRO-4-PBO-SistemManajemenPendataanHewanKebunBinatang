/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

/**
 *
 * @author ASUS
 */

import java.util.ArrayList;
import model.Hewan;
import model.PerawatanHewan;
import model.HewanAir;
import model.HewanDarat;
import model.dao.HewanDAO;
import model.dao.HewanDaratDAO;
import model.dao.HewanAirDAO;
import model.dao.PerawatanDAO;

public class PengelolaHewan {

    private ArrayList<Hewan> daftarHewan;
    
    private final HewanDAO hewanDAO;
    private final HewanDaratDAO hewanDaratDAO;
    private final HewanAirDAO hewanAirDAO;
    private final PerawatanDAO perawatanDAO;
    
    private static int nextId = 1;
    private static int nextIdPerawatan = 101;
    
    //Constructor
    public PengelolaHewan() {
        hewanDAO = new HewanDAO();
        hewanDaratDAO = new HewanDaratDAO();
        hewanAirDAO = new HewanAirDAO();
        perawatanDAO = new PerawatanDAO();

        daftarHewan = new ArrayList<>();
        muatDataDariDatabase();
    }
    
    private void muatDataDariDatabase() {
        daftarHewan = new ArrayList<>(hewanDAO.ambilSemuaHewan());

        nextId = 1;
        nextIdPerawatan = 101;

        for (Hewan hewan : daftarHewan) {
            if (hewan.getId() >= nextId) {
                nextId = hewan.getId() + 1;
            }

            PerawatanHewan perawatan = hewan.getPerawatan();

            if (perawatan != null
                    && perawatan.getIdPerawatan() >= nextIdPerawatan) {
                nextIdPerawatan = perawatan.getIdPerawatan() + 1;
            }
        }
    }

    public int idHewanAuto() {
        return nextId++;
    }
    
    public int idPerawatanAuto() {
        return nextIdPerawatan++;
    }
    
    private Hewan cariHewan(int id){
        for (Hewan h : daftarHewan){
            if (h.getId() == id){
                return h;
            }
        }
        return null;
    }
    
    public Hewan cariHewanBerdasarkanId(int id) {
        return cariHewan(id);
    }
    
    public boolean cekId(int id){
        muatDataDariDatabase();
        return Validator.cekId(daftarHewan, id);
    }
    
    public boolean cekIdPerawatan(int idPerawatan) {
        muatDataDariDatabase();
        return Validator.cekIdPerawatan(daftarHewan, idPerawatan);
    }
    
    public boolean cekIdPerawatan(int idPerawatan, int idHewanDikecualikan) {
        muatDataDariDatabase();
        return Validator.cekIdPerawatan(daftarHewan, idPerawatan, idHewanDikecualikan);
    }
    
    //C
    public boolean tambahHewan(Hewan hewan) {
        if (hewan == null) {
           System.out.println("Data hewan tidak boleh kosong!");
           return false;
        }

        if (cekId(hewan.getId())) {
            System.out.println("ID hewan sudah digunakan!");
            return false;
        }

        hewanDAO.tambah(hewan);

        if (hewan.getClass() == HewanDarat.class) {
            hewanDaratDAO.tambah((HewanDarat) hewan);
        } else if (hewan.getClass() == HewanAir.class) {
            hewanAirDAO.tambah((HewanAir) hewan);
        }

        if (hewan.getPerawatan() != null) {
            perawatanDAO.tambah(hewan.getId(), hewan.getPerawatan());
        }

        muatDataDariDatabase();

        return cariHewan(hewan.getId()) != null;
    }
    
    //R
    public ArrayList<Hewan> getDaftarHewan() {
        muatDataDariDatabase();
        return daftarHewan;
    }
    
    //U
    public boolean ubahHewan(int id, String nama, String jenis, int umur, String habitat, PerawatanHewan perawatanBaru) {
        muatDataDariDatabase();
        
        Hewan hewan = cariHewan(id);
        
        if (hewan == null){
            System.out.println("Data hewan tidak ditemukan!");
            return false;
        }
        
        PerawatanHewan perawatanLama = hewan.getPerawatan();
        
        hewan.setNama(nama);
        hewan.setJenis(jenis);
        hewan.setUmur(umur);
        hewan.setHabitat(habitat);
        
        hewanDAO.update(hewan);
        
        if (perawatanBaru != null){
            if(perawatanLama != null){
                perawatanDAO.updatePerawatan(id, perawatanBaru);
            } else {
                perawatanDAO.tambah(id, perawatanBaru);
            }
        } else if (perawatanLama != null){
            perawatanDAO.hapusPerawatan(id);
        }
        
        muatDataDariDatabase();
        
        System.out.println("Proses perubahan data hewan selesai.");
        
        return cariHewan(id) != null;
    }
    
    //D
    public boolean hapusHewan(int id) {
        muatDataDariDatabase();
        
        if (cariHewan(id) == null) {
            System.out.println("Data hewan tidak ditemukan!");
            return false;
        }
        
        hewanDAO.hapus(id);
        
        muatDataDariDatabase();
        
        return cariHewan(id) == null;
    }
}

