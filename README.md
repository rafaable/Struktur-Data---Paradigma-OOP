# Dokumentasi Tugas Struktur Data - Paradigma OOP
Nier kembali ke kampung halaman dengan riang, kesempatan 2 minggu yang sangat berharga untuk libur lebaran dengan beberapa hari dipotong kelas online, begitu berharga mengingat setelah liburan ini ia akan bertempur selama 3 bulan nonstop. Namun di luar pengetahuannya, pengadaan kelas WFA justru memberikannya oleh-oleh tugas yang jauh lebih banyak dari biasanya. Kini hari raya idul fitri telah berakhir dan liburan kurang beberapa hari, namun ia masih memiliki beberapa tugas yang ternyata lebih rumit dari perkiraannya. Banyaknya tugas membuat Nier kerap kali melamun dan berandai, mungkin tugas-tugasnya akan selesai jika ia tidak menunda sama sekali, mungkin ia bisa lebih tenang jika mengerjakan tugasnya sesegera mungkin tepat setelah diberikan.

Berandai saja tak ada gunanya. Sebagai mahasiswa IT yang sudah menjalani perkuliahan satu semester, ia merasa bahwa seharusnya pengetahuan yang didapat bisa digunakan untuk sesuatu. Ia pun membuat sebuah sistem untuk mengatur prioritas dan urgensi tugas. Selain itu, ia harus menjaga kesehatan mentalnya dengan journalling singkat setiap sore. Seketika terlintas sebuah ide untuk membuat program sesuai dengan kebutuhannya. _"Tugasku harus selesai, satu per satu, sedikit demi sedikit"_

## Deskripsi kasus

Bayangkan sebuah hari di mana semua tugas kuliah dan refleksi pribadimu tertata rapi dalam satu aplikasi kecil. Inilah inti dari kode yang ditulis Nier. Kode ini seperti lembaran jurnal interaktif harian dengan fitur CRUD dan penerapan beberapa konsep OOP

Aplikasi ini dibagi menjadi **dua dunia besar**:  

1. **College Section** merupakan ranah tugas dan ujian yang akan datang dalam waktu dekat. Di sini, setiap tugas dan ujian disimpan sebagai objek `Task` yang masing-masing terdiri dari:
   - **Kategori**: Exam atau Task  
   - **Mata kuliah**: 6 mata kuliah selama satu semester 
   - **Deskripsi** yang menjelaskan rincian tugas Nier
   - **Deadline** yang dibungkus dalam **class `Deadline`** untuk menjaga hari dan jam tetap konsisten, serta  
   - **Difficulty** dengan skala 1–5  

3. **Growth Section** merupakan dunia refleksi pribadi. Sebuah daftar 5 topik yang dikemas dalam List<String>, masing-masing bisa diisi dengan catatan harian untuk menjaga positive thinking, seperti:
   - “Hari ini aku bersyukur karena …”  
   - “Apa yang bisa kuperbaiki hari ini …”  
   - “Sincere wishlist hari ini …”  
   - “Progress/achievement kecil hari ini …”  
   - “Pelajaran yang bisa diambil hari ini …”  Masing-masing topik disimpan sebagai `List<String>` di dalam list yang lebih besar, sehingga mudah diakses dan dimodifikasi.

Struktur kode terdiri dari
- **Interface `Section`**  
  Menjadi jembatan bagi `CollegeSection` dan `GrowthSection` agar keduanya bisa ditampilkan oleh fungsi `display()` dan dicek oleh `isEmpty()`. Konsep ini menciptakan standardisasi bahwa semua section harus punya cara untuk muncul di layar.

- **Class `Task` dan `Deadline`**  
  `Task` adalah inti dari CollegeSection. `Deadline` dibuat terpisah supaya setiap logika terkait waktu—misal konversi ke jam total—tetap rapi, tidak bercampur dengan deskripsi atau kategori tugas.

- **Class `CollegeSection`**  
  Menyimpan list `Task`, bisa menambah, menghapus, mengurutkan berdasarkan deadline dan difficulty. Bayangkan ini seperti rak buku yang selalu rapi diurutkan otomatis.

- **Class `GrowthSection`**  
  Menyimpan lima list refleksi, satu untuk setiap topik. Bisa menambah catatan, menghapus, dan menampilkan semuanya dengan urutan yang jelas. Kodenya sengaja dibuat generik, sehingga menambah topik cukup dengan mengubah list di konstruktor.

- **Class `Validator`**  
  Penjaga input. Semua input dari user dicek di sini supaya tidak salah format atau out-of-range. Misal: angka untuk kategori, difficulty, atau pilihan topik. Ini seperti filter pintu masuk yang memastikan semua data yang masuk valid.

- **Class `Main`**  
  Tempat seluruh interaksi terjadi. Semua logika input, pemanggilan display, dan manipulasi data dilakukan di sini.
  Ada menu:  
  1. Tampilkan rekap  
  2. Edit  
  3. Tambah  
  4. Hapus  
  5. Exit  

Alur Penggunaan
1. User masuk ke menu utama.  
2. Memilih menu tampilkan, edit, tambah, hapus, atau exit
3. Terdapat pilihan section setelah memilih nomor menu, yaitu CollegeSection dan GrowthSection
4. Jika memilih menu tambah pada CollegeSection maka akan diarahkan untuk memilih tipe tugas atau ujian, memilih kode mata kuliah, menambahkan deskripsi, deadline, dan difficulty. Nier membuat penyimpanannya urut berdasarkan tenggat paling singkat dan kerumitan paling tinggi
5. Jika menambahkan GrowthSection, maka akan muncul kelima topik satu per satu, setiap topik akan menampilkan nomor yang bisa diisi dengan input relevan, sistemnya memungkinkan Nier bisa lanjut ke topik berikutnya atau mengakhiri input dengan mengetik `xxx`.  Terdapat pula output yang menginformasikan pengguna baru, sehingga user lain bisa menggunakan program buatan Nier dengan mudah
6. Jika memilih untuk menampilkan data, program akan memanggil metode `display()` dari masing-masing section
7. Untuk menghapus atau mengedit, pengguna diarahkan untuk memilih section mana yang ingin dihapus, pengguna juga ditampilkan list terlebih dahulu untuk bisa memilih list mana yang akan dihapus. Data terbaru akan diurutkan kembali oleh sistem supaya tetap sesuai urutan urgensinya.
Semua input diperiksa validitasnya oleh sistem

## Class Diagram
<img width="1100" height="654" alt="image" src="https://github.com/user-attachments/assets/b2e401e7-f575-4805-9132-2bf87079f371" />
Class diagram dari kode ini menggambarkan hubungan antara beberapa bagian utama dalam aplikasi pengelolaan tugas dan catatan harian.

1. **Section (Interface)**  
   Interface ini berisi kontrak dasar untuk semua section yang ada di program.  
   - Method:
     - `display()` → menampilkan isi section.
     - `isEmpty()` → mengecek apakah section kosong.  
   Interface ini diimplementasikan oleh **CollegeSection** dan **GrowthSection**, sehingga keduanya wajib menyediakan implementasi method `display()` dan `isEmpty()`.

2. **CollegeSection (Implements Section)**  
   Section ini bertanggung jawab untuk mengelola daftar `Task`.  
   - Atribut:
     - `tasks: ArrayList<Task>` → menyimpan semua task/exam.  
   - Method utama:
     - `addTask(Task t)` → menambahkan task baru dan mengurutkan berdasarkan deadline & difficulty.
     - `deleteTask(int index)` → menghapus task tertentu.
     - `sort()` → mengurutkan tasks berdasarkan total jam deadline dan difficulty.
     - `display()` → menampilkan semua task.
   - Hubungan:
     - Mengimplementasikan `Section`.
     - **CollegeSection → Task** → asosiasi 1-to-many (satu CollegeSection punya banyak Task).

3. **Task**  
   Merepresentasikan satu tugas atau ujian.  
   - Atribut:
     - `category: int` → 1=Exam, 2=Task.
     - `kodeMatkul: int` → kode mata kuliah.
     - `desc: String` → deskripsi tugas.
     - `deadline: Deadline` → deadline tugas.
     - `difficulty: int` → tingkat kesulitan 1-5.
   - Method:
     - Getter dan setter untuk semua atribut.
     - `display(int index)` → menampilkan task dengan detail dan index.
   - Hubungan:
     - **Task → Deadline** → komposisi (Task memiliki Deadline).

4. **Deadline**  
   Merepresentasikan waktu penyelesaian task dalam hari dan jam.  
   - Atribut:
     - `hari: int`
     - `jam: int`
   - Method:
     - `toHours()` → menghitung total jam.
     - Getter dan setter untuk hari dan jam.

5. **GrowthSection (Implements Section)**  
   Section ini menyimpan catatan refleksi harian dalam 5 topik berbeda.  
   - Atribut:
     - `data: List<List<String>>` → daftar topik, tiap topik bisa menyimpan beberapa catatan.
   - Method:
     - `add(int index, String val)` → menambahkan catatan ke topik tertentu.
     - `get(int index)` → mengambil semua catatan dari topik tertentu.
     - `delete(int topic, int index)` → menghapus catatan tertentu.
     - `display()` → menampilkan semua catatan per topik.
   - Hubungan:
     - Mengimplementasikan `Section`.

6. **Validator**  
   Kelas utilitas statis untuk validasi input user.  
   - Method:
     - `inputRange(Scanner, min, max, msg)` → validasi angka dalam range.
     - `inputDesc(Scanner)` → validasi panjang deskripsi.
     - `inputDeadline(Scanner)` → validasi format dan range deadline.

7. **Main**  
   Entry point program yang mengontrol menu dan alur program.  
   - Atribut:
     - `static CollegeSection college`
     - `static GrowthSection growth`
     - `static Scanner sc`
   - Menggunakan:
     - `CollegeSection` untuk menambah, menampilkan, mengedit, dan menghapus task.
     - `GrowthSection` untuk menambah, menampilkan, mengedit, dan menghapus catatan refleksi.
     - `Validator` untuk validasi semua input user.

**Singkatnya:**
- `Section` → diimplementasikan oleh `CollegeSection` dan `GrowthSection`.
- `CollegeSection` → memiliki banyak `Task`.
- `Task` → memiliki `Deadline` (komposisi).
- `Main` → menggunakan `CollegeSection`, `GrowthSection`, dan `Validator`.
