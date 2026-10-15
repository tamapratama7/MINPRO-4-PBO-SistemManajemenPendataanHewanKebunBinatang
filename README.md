# SISTEM MANAJEMEN PENDATAAN HEWAN KEBUN BINATANG

Nama: Noor Hamsyah Pratama  
NIM: 2509116046  
Kelas: B'2025

## A. Deskripsi Singkat Program  
Program Sistem Manajemen Pendataan Hewan Kebun Binatang merupakan aplikasi berbasis Java yang digunakan untuk mengelola data hewan di kebun binatang. Setiap hewan memiliki data umum berupa ID, nama, jenis, umur, dan habitat, serta dapat memiliki data perawatan yang mencakup ID perawatan, jenis perawatan, dan tanggal perawatan.  

Hewan dalam program dikategorikan menjadi dua jenis, yaitu Hewan Darat dan Hewan Air. Hewan Darat memiliki atribut khusus berupa kecepatan lari, sedangkan Hewan Air memiliki atribut khusus berupa kedalaman renang. Kedua kategori tersebut merupakan turunan dari abstract class `Hewan` dan menerapkan interface `Pelari` serta `Perenang` sesuai dengan kemampuannya.  

Program ini merupakan sistem manajemen pendataan hewan kebun binatang yang menggunakan database MySQL sebagai tempat penyimpanan data. Program dikembangkan menggunakan Java dengan menerapkan Java Database Connectivity (JDBC), PreparedStatement, dan pola Data Access Object (DAO) untuk menghubungkan program dengan database. Data hewan dan perawatannya disimpan di dalam database sehingga tetap tersedia ketika program dijalankan kembali.  

Program menyediakan fitur CRUD (Create, Read, Update, Delete), yaitu menambahkan, menampilkan, mengubah, dan menghapus data hewan. Selain itu, program dilengkapi dengan validasi input untuk membantu menjaga konsistensi data yang dimasukkan.  

## B. Struktur Package  
Program dipecah ke dalam empat package dengan pola MVC:  


```text
src/main/java
├── config
│   ├── Koneksi.java
│   └── TestKoneksi.java
├── controller
│   ├── PengelolaHewan.java
│   └── Validator.java
├── main
│   └── Main.java
├── model
│   ├── Hewan.java
│   ├── HewanDarat.java
│   ├── HewanAir.java
│   ├── PerawatanHewan.java
│   ├── Pelari.java
│   ├── Perenang.java
│   └── dao
│       ├── HewanDAO.java
│       ├── HewanDaratDAO.java
│       ├── HewanAirDAO.java
│       └── PerawatanDAO.java
└── view
    └── View.java
```

<p align="center">
  <img width="327" height="543" alt="image" src="https://github.com/user-attachments/assets/3d136a9a-e8a0-43de-b254-37f5d5f29140" />
</p>

| Package | Isi | Penjelasan |  
|---|---|---|  
| `config` | `Koneksi`, `TestKoneksi` | Berisi kelas `Koneksi` untuk menghubungkan program dengan database MySQL dan kelas `TestKoneksi` untuk menguji koneksi database. |
| `controller` | `PengelolaHewan`, `Validator` | Berisi kelas `PengelolaHewan` untuk mengatur alur pengelolaan data hewan dan kelas `Validator` untuk melakukan validasi input. |   
| `main` | `Main` | Berisi kelas `Main` sebagai titik awal program. Kelas ini menginisialisasi objek yang diperlukan dan menjalankan aplikasi. | 
| `model` | `Hewan`, `HewanDarat`, `HewanAir`, `PerawatanHewan`, `Pelari`, `Perenang` | Berisi kelas `Hewan`, `HewanDarat`, `HewanAir`, dan `PerawatanHewan` yang merepresentasikan data dalam program, serta interface `Pelari` dan `Perenang`. |  
| `model.dao` | `HewanDAO`, `HewanAirDAO`, `HewanDaratDAO`, `PerawatanDAO` |  Berisi kelas DAO yang menangani operasi database untuk data hewan dan perawatannya menggunakan JDBC dan `PreparedStatement`. |
| `view` | `View` | Berisi kelas `View` yang menangani interaksi dengan pengguna, seperti menampilkan menu, menerima input, dan menampilkan hasil operasi. | 

 ## C. Alur Program  
Berikut ada tampilan Menu Utama:
<p align="center"> 
  <img width="350" height="187" alt="image" src="https://github.com/user-attachments/assets/d5cf416e-49ac-4afa-ae0d-98809d60b33a" />
</p>  

Lalu, Dibawah ini adalah Alur Program:  
### 1. Tambah Data Hewan 

<p align="center">  
  <img width="425" height="397" alt="image" src="https://github.com/user-attachments/assets/3999ca21-a1d9-4b95-902d-9aca68dafa4e" />
</p>  

ID hewan dibuat otomatis lewat `idHewanAuto()` sebelum lanjut ke input data umum (nama, jenis, umur, habitat). Pengguna kemudian memilih kategori antara Hewan Darat atau Hewan Air, yang menentukan atribut khusus apa yang ditanyakan selanjutnya (kecepatan lari / kedalaman renang). Terakhir, data perawatan diminta, dengan ID perawatan dibuat otomatis lewat `idPerawatanAuto()` dan tanggal divalidasi lewat `Validator.cekTanggal()` supaya tidak boleh di masa depan. Kalau semua lolos, objek HewanDarat/HewanAir baru dibuat dan disimpan.

### 2. Lihat Data Hewan  

<p align="center">  
  <img width="412" height="477" alt="image" src="https://github.com/user-attachments/assets/09e5d6e9-e7d7-44bb-b61a-46d83b617b5f" />
  <img width="366" height="420" alt="image" src="https://github.com/user-attachments/assets/2a1b0ec1-0f27-4b46-be66-a13e003dfe3b" />
</p> 

Program akan menampilkan semua data hewan satu per satu dengan memanggil method `tampilkanInfoLengkap()` dan `tampilkanKemampuan()` pada setiap hewan di dalam daftar. Karena HewanDarat dan HewanAir masing-masing punya versi method ini sendiri, tampilan yang muncul otomatis beda sesuai jenis hewannya. Kalau Hewan Darat yang muncul info hewan darat dan kemampuan berlari, kalau Hewan Air yang muncul info hewan air dan kemampuan berenang.  

### 3. Ubah Data Hewan   

<p align="center">  
  <img width="472" height="306" alt="image" src="https://github.com/user-attachments/assets/b45a85e2-3b01-4b17-a879-9a51841dde1d" />
</p> 

Setelah ID hewan diverifikasi ada lewat `cekId()`, objek lama diambil lewat `cariHewanBerdasarkanId()` untuk mendeteksi label atribut khusus lewat `getLabel()`, sehingga program otomatis menanyakan "kecepatan lari" atau "kedalaman renang" sesuai jenis hewannya. Pengguna lalu mengisi data umum baru (nama, jenis, umur, habitat) dan data perawatan baru, dengan pengecekan ID perawatan yang mengecualikan data milik hewan itu sendiri dan tanggal divalidasi lewat `Validator.cekTanggal()`. Data umum dan perawatan disimpan lewat `ubahHewan()`, sedangkan atribut khusus disimpan lewat `setNilai()`. ID hewan tidak berubah.  

### 4. Hapus Data Hewan

<p align="center"> 
  <img width="341" height="115" alt="image" src="https://github.com/user-attachments/assets/49366c83-6c37-4f81-b435-2508ff5a9cde" />
</p>  

Input ID langsung diteruskan ke `hapusHewan()`, yang menangani sendiri pencarian sekaligus penghapusan objek dari daftarHewan. Kalau ID tidak ditemukan, method ini mengembalikan false dan pesan error ditampilkan.  

### 5. Keluar  

<p align="center"> 
  <img width="258" height="57" alt="image" src="https://github.com/user-attachments/assets/54c0935b-1b58-418d-8bf8-46197bbb279a" />
</p>  

Perulangan do-while berhenti, `input.close()` dipanggil, program selesai.

## D. Encapsulation  
Atribut di class `model` disembunyikan dari luar class sehingga hanya bisa diakses lewat getter dan setter `public`:  

- `HewanDarat` (`kecepatanLariKmJam`), `HewanAir` (`kedalamanRenangMeter`), dan `PerawatanHewan` (`idPerawatan`, `jenisPerawatan`, `tanggal`) memakai modifier `private`.  
- Atribut umum di `Hewan` (`id`, `nama`, `jenis`, `umur`, `habitat`, `perawatan`) memakai modifier `protected`, supaya subclass bisa memakainya langsung (misalnya `nama` dipakai di   `berlari()` dan `berenang()`), tetapi class lain di luar hierarki tetap harus lewat getter/setter.  
- `id` dan `idPerawatan` dibuat `final` sehingga tidak bisa diubah setelah objek dibuat.

Setiap setter juga dibekali validasi, bukan sekadar menyimpan nilai mentah. Berikut adalah salah satu contoh di `Hewan.getNama` dan `Hewan.setNama()`:  

<p align="center">  
  <img width="792" height="233" alt="image" src="https://github.com/user-attachments/assets/abdc1b4c-8411-404e-9219-def42307e2eb" />
</p>   

Contoh lain di `HewanDarat.setKecepatanLariKmJam()` yang menolak nilai negatif, dan `PerawatanHewan.setTanggal()` yang menolak format selain `dd-mm-yyyy`.  

Penerapan lain dari encapsulation terlihat dari akses yang sengaja dibatasi:  

- `cariHewan()` di `PengelolaHewan` dibuat `private` karena hanya dipakai secara internal. Untuk kebutuhan luar disediakan `cariHewanBerdasarkanId()` sebagai pintu masuk `public`.  
- `daftarHewan` di `PengelolaHewan` dibuat `private`, hanya bisa diakses lewat method CRUD dan `getDaftarHewan()`.  
- `nextId` dan `nextIdPerawatan` dibuat `private static`, sehingga penomoran ID hanya bisa berjalan lewat `idHewanAuto()` dan `idPerawatanAuto()`.
- Constructor `Validator()` dibuat `private` karena class ini murni kumpulan method `static` yang tidak boleh diinstansiasi.  
- `Scanner input` di `View` dibuat `private final`, begitu juga helper `bacaString()` dan `bacaDouble()` yang hanya dipakai di dalam `View`.

## E. Inheritance 
`Hewan` bertindak sebagai superclass yang mewariskan atribut dan method umum (id, nama, jenis, umur, habitat, perawatan, beserta getter/setter-nya) ke dua **subclass**, yaitu `HewanDarat` dan `HewanAir`.

Superclass:  
<p align="center">  
  <img width="322" height="27" alt="image" src="https://github.com/user-attachments/assets/a1906cd3-0a98-4a39-aa8a-ce112dfa7f19" />
</p>

Subclass:  
<p align="center"> 
  <img width="651" height="20" alt="image" src="https://github.com/user-attachments/assets/baa22ffd-7d6e-4c0b-a839-74d287f7a44b" />
  <img width="646" height="27" alt="image" src="https://github.com/user-attachments/assets/0d794f46-1ed7-4697-9e54-6020fa5b9b04" />
</p>  

Kedua subclass tidak menulis ulang atribut umum dari nol. Constructor-nya cukup memanggil super(...) untuk mengisi bagian yang diwariskan, baru kemudian mengisi atribut khususnya sendiri. Contoh di HewanDarat:  

<p align="center">   
  <img width="1347" height="92" alt="image" src="https://github.com/user-attachments/assets/c886f935-5ec1-48f1-82c5-123d45aa9e08" />
</p>  

Kedua subclass dideklarasikan `final` karena memang tidak dirancang untuk diwariskan lagi.  

## F. Polymorphism  
Method Overriding - Subclass menimpa method dari superclass/interface dengan perilakunya sendiri:  

| Method | `HewanDarat` | `HewanAir` |
|---|---|---|
| `tampilkanInfoLengkap()` | Memanggil `super.tampilkanInfoLengkap()` lalu menambah "Info Tambahan : Hewan darat" | Memanggil `super.tampilkanInfoLengkap()` lalu menambah "Info Tambahan : Hewan air" |
| `getLabel()` | `"kecepatan lari (km/jam)"` | `"kedalaman renang (meter)"` |
| `setNilai(double)` | Memanggil `setKecepatanLariKmJam()` | Memanggil `setKedalamanRenangMeter()` |
| `tampilkanKemampuan()` | Mencetak hasil `berlari()` | Mencetak hasil `berenang()` |  

Method Overloading - `cekIdPerawatan()` memiliki dua versi dengan jumlah parameter berbeda, baik di `Validator` maupun di `PengelolaHewan`:  

<p align="center">
  <img width="1128" height="367" alt="image" src="https://github.com/user-attachments/assets/784cd013-8dc2-4788-a208-3c8493599b06" />
</p>

Versi 2 parameter hanya memanggil versi 3 parameter dengan `idHewanDikecualikan` diisi `-1`. Nilai `-1` dipilih karena ID hewan asli tidak mungkin negatif, sehingga tidak ada hewan yang benar-benar dikecualikan dan semua hewan tetap dicek. Dengan begitu logika pengecekan cukup ditulis sekali. Versi 3 parameter dipakai di menu Ubah, supaya hewan yang sedang diubah tidak dianggap bentrok dengan ID perawatannya sendiri.  

## G. Abstraction  
### 1. Abstract class `Hewan`  

`Hewan` dideklarasikan dengan `public abstract class` sehingga tidak bisa diinstansiasi langsung (`new Hewan(...)` akan error), karena yang ada di kebun binatang hanyalah hewan darat atau hewan air.  

<p align="center">
  <img width="340" height="37" alt="Screenshot 2026-10-08 223903" src="https://github.com/user-attachments/assets/33a79df6-7e5e-48a5-bbde-3456a9708b54" />
</p>

### 2. Abstract method di `Hewan`  

Di dalam `Hewan` ada tiga abstract method, yaitu method yang hanya dideklarasikan tanpa isi. `Hewan` hanya menentukan *apa* yang harus ada, sedangkan *bagaimana* caranya diserahkan ke subclass.  

<p align="center">
  <img width="487" height="130" alt="Screenshot 2026-10-08 224034" src="https://github.com/user-attachments/assets/cbd58b15-3647-44c2-a8b3-d635782e3d5f" />
</p>

### 3. Overriding abstract method di subclass  

Setiap subclass wajib mengimplementasikan ketiga method tersebut dengan `@Override`. Contoh `getLabel()` yang isinya berbeda di tiap subclass:  

Hewan Darat  
<p align="center">  
  <img width="392" height="107" alt="image" src="https://github.com/user-attachments/assets/7f68dd9e-3310-4d59-80d9-e544f5830109" />
</p>

Hewan Air  
<p align="center">  
  <img width="395" height="97" alt="image" src="https://github.com/user-attachments/assets/49d2e363-0965-4220-9e4e-d0333fb73041" />
</p>  

Contoh lain `setNilai()` di `HewanDarat`, yang meneruskan nilai ke atribut khususnya:  

<p align="center">   
  <img width="390" height="96" alt="image" src="https://github.com/user-attachments/assets/a428b036-c13f-4823-9c2c-023bdb7e20cd" />
</p>

## H. Interface

Ada dua interface di package `model`:  

<p align="center">   
  <img width="272" height="67" alt="image" src="https://github.com/user-attachments/assets/b57bab6a-b445-42fe-a3ef-addb1405c154" />
  <img width="296" height="65" alt="image" src="https://github.com/user-attachments/assets/fd5f2ae7-b7db-4ec8-9101-817d343069a5" />
</p>

Keduanya mendefinisikan kemampuan hewan, bukan jenis hewannya. Itulah alasan kemampuan ini dipisah menjadi interface dan tidak ditaruh di `Hewan`: tidak semua hewan bisa berlari atau berenang, dan di Java sebuah class hanya boleh `extends` satu class tetapi boleh `implements` banyak interface.  

| Class | Implementasi | Hasil method |
|---|---|---|
| `HewanDarat` | `implements Pelari` | `berlari()` mengembalikan `"<nama> berlari dengan kecepatan <x> km/jm."` |
| `HewanAir` | `implements Perenang` | `berenang()` mengembalikan `"<nama> berenang hingga kedalaman <x> meter."` |
 
Method interface ini dipakai di `tampilkanKemampuan()` pada masing-masing class, yang dipanggil oleh menu Lihat Data Hewan:   

Hewan Darat
<p align="center"> 
  <img width="361" height="105" alt="Screenshot 2026-10-08 224846" src="https://github.com/user-attachments/assets/2c49e24a-aac6-4ccb-877c-4e8d29276448" />
</p>

Hewan Air  
<p align="center">   
  <img width="411" height="101" alt="image" src="https://github.com/user-attachments/assets/c4303d51-6838-41de-a7ed-d8b840ccd99a" />
</p>

Dengan rancangan ini, jika suatu saat ada hewan baru yang punya lebih dari satu kemampuan (misalnya hewan amfibi yang bisa berlari sekaligus berenang), class-nya cukup `implements Pelari, Perenang` tanpa mengubah class lain.
  
<p align="center">   
    <img width="638" height="26" alt="image" src="https://github.com/user-attachments/assets/b8911b1e-324e-4a6b-8fbd-0b445b82ea3c" />
    <img width="637" height="22" alt="image" src="https://github.com/user-attachments/assets/71be1276-acff-405b-89bb-f18190928488" />
</p>  

## I. Penerapan JDBC

Java Database Connectivity (JDBC) digunakan untuk menghubungkan program Java dengan database MySQL. Melalui JDBC, program dapat menjalankan operasi untuk menyimpan, membaca, mengubah, dan menghapus data.

### 1. Koneksi Database  

<p align="center">   
  <img width="881" height="632" alt="image" src="https://github.com/user-attachments/assets/596aacb4-b09e-45d8-b1c0-9944bdcb045a" />
</p>  

Pada gambar di atas merupakan kelas `Koneksi` yang berfungsi untuk menghubungkan program Java dengan database MySQL bernama `kebun_binatang`. Koneksi dilakukan menggunakan JDBC melalui `DriverManager.getConnection()`. Kelas ini menyediakan koneksi yang digunakan oleh kelas DAO untuk menjalankan operasi terhadap database, sehingga program dapat menyimpan dan mengambil data hewan serta data perawatannya.

### 2. Uji Koneksi  

<p align="center">   
  <img width="532" height="437" alt="image" src="https://github.com/user-attachments/assets/0c4dad35-f52a-4857-a994-3e2f9153c208" />
</p>  

Pada gambar di atas merupakan kelas `TestKoneksi` yang digunakan untuk menguji apakah program Java berhasil terhubung dengan database MySQL. Pengujian ini membantu memastikan bahwa konfigurasi koneksi dan driver JDBC dapat digunakan sebelum program menjalankan operasi pengelolaan data.

### 3. PreparedStatement   
`PreparedStatement` digunakan untuk menjalankan perintah SQL dengan parameter. Penggunaan parameter membantu mengurangi risiko SQL injection dan mempermudah pengiriman nilai ke dalam perintah SQL.  

<p align="center">   
  <img width="1052" height="385" alt="image" src="https://github.com/user-attachments/assets/6c3217e9-b05a-4dc0-97d6-5aafcb517613" />
</p>   

### 4. Data Access Object (DAO)  
Pola Data Access Object (DAO) diterapkan dengan memisahkan operasi database dari bagian lain program. Setiap kelas DAO bertanggung jawab menangani operasi database untuk jenis data tertentu.

Kelas DAO yang digunakan meliputi:

- `HewanDAO`: Menangani operasi database untuk data hewan.
- `HewanDaratDAO`: Menangani operasi database untuk data hewan darat.
- `HewanAirDAO`: Menangani operasi database untuk data hewan air.
- `PerawatanDAO`: Menangani operasi database untuk data perawatan hewan.

<p align="center">  
  <img width="455" height="115" alt="Screenshot 2026-10-15 210657" src="https://github.com/user-attachments/assets/cd2332bf-e7e8-4a0f-af66-7b7082aa0d5f" />  
  <img width="1006" height="537" alt="Screenshot 2026-10-15 210718" src="https://github.com/user-attachments/assets/90ce0394-6855-414e-9f41-712856fa96a0" />  
</p>   

Pada gambar di atas ditunjukkan implementasi kelas DAO yang bertugas mengambil data perawatan hewan dari database MySQL. Konstruktor HewanDAO menggunakan Koneksi.getConnection() untuk memperoleh koneksi database. Selanjutnya, method ambilSemuaPerawatan() menjalankan perintah SQL SELECT menggunakan PreparedStatement. Hasil query dibaca menggunakan ResultSet, kemudian setiap baris data diubah menjadi objek PerawatanHewan dan dimasukkan ke dalam sebuah List untuk dikembalikan dan digunakan oleh program.

### 5. Operasi CRUD

Operasi CRUD digunakan untuk mengelola data yang tersimpan di database, yaitu:

- **Create**: Menambahkan data baru.
- **Read**: Mengambil dan menampilkan data.
- **Update**: Memperbarui data yang sudah ada.
- **Delete**: Menghapus data sesuai pilihan pengguna.

Sebelum ditambah:   
<p align="center">  
  <img width="713" height="176" alt="image" src="https://github.com/user-attachments/assets/c0e4bfc4-8f3a-425f-b482-a250938838e5" />
</p>   

Proses Ditambah:  
<p align="center">
  <img width="422" height="458" alt="image" src="https://github.com/user-attachments/assets/9c8fd705-808b-43ae-b030-4e7024517aff" />  
</p>

Setelah Ditambah:  
<p align="center">  
  <img width="708" height="205" alt="image" src="https://github.com/user-attachments/assets/127e139c-8ecd-49df-9339-717bfd8e9f8f" />  
</p>

