# Dokumentasi Tugas Struktur Data - Paradigma OOP
Nier kembali ke kampung halaman dengan riang, kesempatan 2 minggu yang sangat berharga untuk libur lebaran dengan beberapa hari dipotong kelas online, begitu berharga mengingat setelah liburan ini ia akan bertempur selama 3 bulan nonstop. Namun di luar pengetahuannya, pengadaan kelas WFA justru memberikannya oleh-oleh tugas yang jauh lebih banyak dari biasanya. Kini hari raya idul fitri telah berakhir dan liburan kurang beberapa hari, namun ia masih memiliki beberapa tugas yang ternyata lebih rumit dari perkiraannya. Banyaknya tugas membuat Nier kerap kali melamun dan berandai, mungkin tugas-tugasnya akan selesai jika ia tidak menunda sama sekali, mungkin ia bisa lebih tenang jika mengerjakan tugasnya sesegera mungkin tepat setelah diberikan.

Berandai saja tak ada gunanya. Sebagai mahasiswa IT yang sudah menjalani perkuliahan satu semester, ia merasa bahwa seharusnya pengetahuan yang didapat bisa digunakan untuk sesuatu. Ia pun membuat sebuah sistem untuk mengatur prioritas dan urgensi tugas. Selain itu, ia harus menjaga kesehatan mentalnya dengan journalling singkat setiap sore. Seketika terlintas sebuah ide untuk membuat program sesuai dengan kebutuhannya. _"Tugasku harus selesai, satu per satu, sedikit demi sedikit"_

# Daftar Isi
- [Deskripsi kasus](#deskripsi-kasus)
- [Class Diagram](#class-diagram)
- [Screenshot output](#screenshot-output)
  - [Menu tambah](#menu-tambah)
  - [Menu tampilkan rekap](#menu-tampilkan-rekap)
  - [Menu edit](#menu-edit)
  - [Menu hapus](#menu-hapus)
  - [Menu exit](#menu-exit)
- [Prinsip OOP yang diterapkan](#prinsip-oop-yang-diterapkan)
- [Kenapa kode ini unik dan menarik](#kenapa-kode-ini-unik-dan-menarik)
- [Kode program Java](#kode-program-java)

## Deskripsi kasus

Bayangkan sebuah hari di mana semua tugas kuliah dan refleksi pribadi tertata rapi dalam satu aplikasi kecil. Inilah inti dari kode yang ditulis Nier. Kode ini seperti lembaran jurnal interaktif harian dengan fitur CRUD dan penerapan beberapa konsep OOP

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
- `Section` → diimplementasikan oleh `CollegeSection` dan `GrowthSection`
- `CollegeSection` → memiliki banyak `Task`
- `Task` → memiliki `Deadline` sebagai komposisi
- `Main` → menggunakan `CollegeSection`, `GrowthSection`, dan `Validator`

## Screenshot output
### Menu tambah
Menambahkan College Section<br>
<p align="center">
<img width="354" height="539" alt="image" src="https://github.com/user-attachments/assets/116a5294-8bdc-4e7a-93b0-17e137d17b01" /><br>
<img width="371" height="335" alt="image" src="https://github.com/user-attachments/assets/1d27bec2-92ae-4c2a-83a4-a13f366170bb" /><br>
<img width="379" height="445" alt="image" src="https://github.com/user-attachments/assets/8556409a-f0e7-45d2-b7e6-a1d0c2bc59bf" />
</p><br>

Menambahkan growth section<br>
<p align="center">
<img width="708" height="867" alt="image" src="https://github.com/user-attachments/assets/cb6eb681-ae58-45dd-8b25-dbb5fb5f1671" />
</p><br>

### Menu tampilkan rekap
Pilih section : 1<br>
<p align="center">
<img width="537" height="528" alt="image" src="https://github.com/user-attachments/assets/371d4e51-40dd-432d-8ffb-e22b2d5211dc" /><br>
</p>
Pilih section : 2<br>
<p align="center">
<img width="537" height="652" alt="image" src="https://github.com/user-attachments/assets/faed2eaa-2351-4d3c-909e-fb11e38b2676" /><br>
</p>
Pilih section : Both<br>
<p align="center">
<img width="575" height="748" alt="image" src="https://github.com/user-attachments/assets/26ee15f3-5bcd-4fac-846d-dea12c49ee79" /><br>
</p>

### Menu edit
Edit College Section<br>
<p align="center">
<img width="522" height="648" alt="image" src="https://github.com/user-attachments/assets/644b5417-ba56-45cc-8bea-9e807625d9cb" />
</p><br>
Edit growth section<br>
<p align="center">
<img width="438" height="487" alt="image" src="https://github.com/user-attachments/assets/92e92a19-26bc-4c7b-af3c-1c4a129f92e3" />
</p><br>
Cek perubahan<br>
<p align="center">
<img width="705" height="884" alt="image" src="https://github.com/user-attachments/assets/93be5641-e28d-4c20-a19e-61249caaf95e" />
</p><br>

### Menu hapus
Hapus College Section<br>
<p align="center">
<img width="574" height="538" alt="image" src="https://github.com/user-attachments/assets/e4cc790c-6959-4ce6-ade8-2b7e12d6711f" />
</p><br>
Hapus Growth Section<br>
<p align="center">
<img width="433" height="534" alt="image" src="https://github.com/user-attachments/assets/c9174ac1-4c0d-4c7d-a33e-cefcec5c9705" />
</p><br>
Cek perubahan<br>
<p align="center">
<img width="731" height="799" alt="image" src="https://github.com/user-attachments/assets/b18fdc08-52a7-4dca-aef2-250046e1385e" />
</p><br>

### Menu exit
<p align="center">
<img width="700" height="173" alt="image" src="https://github.com/user-attachments/assets/624efbed-83c3-4fd5-a712-aae75402ab1b" />
</p><br>

## Prinsip OOP yang diterapkan

Program ini secara konsisten menerapkan prinsip **Object-Oriented Programming (OOP)** untuk menjaga modularitas, keterbacaan, keamanan data, dan kemudahan pengembangan di masa depan.

### 1. Encapsulation
Setiap class menyimpan **data privat** dan menyediakan **getter/setter** untuk akses.  
Contoh:
  - `Deadline` menyimpan `hari` dan `jam`.
  - `Task` menyimpan `category`, `kodeMatkul`, `desc`, `deadline`, `difficulty`.  
Manfaat:
  - Mengontrol akses data, mencegah manipulasi langsung.
  - Mempermudah validasi dan pengaturan data.

### 2. Composition
Class kompleks dibangun dari **objek lebih kecil**.
Contoh:
  - `CollegeSection` **memiliki banyak `Task`**.
  - `GrowthSection` **memiliki list string** untuk tiap topik journaling.
Manfaat:
  - Kode lebih modular.
  - Bagian-bagian kecil bisa di-reuse di class lain.
  - Memudahkan sorting, penambahan, dan penghapusan data.

### 3. Interface & Abstraction
`Section` adalah **interface** yang mendefinisikan kontrak `display()` dan `isEmpty()`.
`CollegeSection` dan `GrowthSection` mengimplementasikan interface ini:
  - **CollegeSection**: menampilkan daftar tugas/ujian.
  - **GrowthSection**: menampilkan daftar catatan harian di 5 topik.
Abstraction:
  - Main program memanggil `display()` dan `isEmpty()` tanpa peduli implementasi internal.
  - Memisahkan **apa yang dilakukan** (interface) dengan **bagaimana dilaksanakan** (class implementasi).

### 4. Polymorphism
Terjadi saat **Main memanggil method interface `display()` atau `isEmpty()`** pada objek `Section`.
Contoh nyata:
```java
Section s;
s = college; s.display();  // CollegeSection
s = growth; s.display();   // GrowthSection
```

## Kenapa kode ini unik dan menarik

Single Responsibility Principle (SRP) di Kode Ini
Salah satu aspek paling menonjol dari kode ini adalah penerapan **Single Responsibility Principle (SRP)** secara konsisten. SRP menyatakan bahwa **setiap class harus punya satu alasan untuk berubah**, atau dengan kata lain, **hanya menangani satu tanggung jawab spesifik**. Kode ini menunjukkan prinsip ini dengan jelas melalui pembagian tugas antar class.
1. Class `Task`
- Tanggung jawab: **merepresentasikan satu tugas atau ujian**.
- Menyimpan semua informasi terkait task: kategori, kode mata kuliah, deskripsi, deadline, dan tingkat kesulitan.
- Semua method getter/setter dan `display(int index)` hanya berfokus pada data task itu sendiri.
- Tidak ada logic menu, input, atau penyimpanan task di sini. Itu berarti `Task` **tidak punya alasan lain untuk berubah** selain jika struktur atau representasi task berubah.
2. Class `Deadline`
- Tanggung jawab: **mengelola informasi deadline**.
- Menyimpan `hari` dan `jam`, serta menyediakan method `toHours()` untuk konversi.
- Tidak tahu tentang tugas, section, atau menu user. Ini memastikan `Deadline` **hanya bertanggung jawab pada deadline**, sehingga jika perhitungan waktu berubah, hanya class ini yang perlu diubah.
3. Class `CollegeSection` dan `GrowthSection`
- Tanggung jawab: **mengelola koleksi data** (tasks atau journaling growth).
- Menyediakan method `add()`, `delete()`, `sort()`, dan `display()`.
- Tidak melakukan validasi input (diserahkan ke `Validator`) atau mengatur alur menu (diserahkan ke `Main`).
- Memastikan setiap section fokus pada **penyimpanan dan tampilan data** saja.
4. Class `Validator`
- Tanggung jawab: **memvalidasi semua input user**.
- Method `inputRange()`, `inputDesc()`, `inputDeadline()` hanya menangani validasi.
- Tidak tahu tentang menu, section, atau bagaimana data disimpan.
- Jika aturan validasi berubah, hanya `Validator` yang perlu diubah—tidak ada class lain yang terganggu.
5. Class `Main`
- Tanggung jawab: **mengatur alur program dan interaksi user**.
- Semua menu, switch-case, dan pemanggilan section dilakukan di sini.
- Main tidak menyimpan data atau memvalidasi input sendiri, sehingga tetap fokus pada kontrol alur.

Kenapa Ini Menarik?
- Kode ini **menepati SRP** hampir di semua class
- Perubahan di satu class **tidak merusak class lain**, misal:
  - Mengubah cara sort task tidak akan memengaruhi validator atau menu.
  - Menambah topik baru di GrowthSection tidak mengganggu CollegeSection.
- Modularitas ini meningkatkan **maintainability, testability, dan readability**
- SRP yang konsisten ini membuat kode terlihat matang, profesional, dan siap dikembangkan

Kode ini bukan sekadar implementasi OOP biasa; ia menunjukkan **pemahaman mendalam tentang prinsip OOP** dan kemampuan untuk menggabungkan berbagai konsep secara harmonis. Banyak program serupa hanya fokus pada satu tipe data atau satu alur menu sederhana. Di sini, struktur kode menunjukkan perencanaan matang dan fleksibilitas tinggi.

Pertama, penggunaan **interface `Section`** membuat kode ini langsung terlihat cerdas. Alih-alih menulis ulang logika display untuk setiap section, programmer sudah memikirkan **abstraction dan polymorphism** dari awal. Main program bisa memanggil method `display()` dan `isEmpty()` tanpa perlu mengetahui kerumitan implementasi internal masing-masing section. Ini membuat kode terlihat bersih sekaligus scalable; menambahkan section baru akan sesederhana mengimplementasikan interface yang sama. Banyak kode lain masih menulis method display secara hardcoded untuk tiap tipe data, yang membuatnya sulit dikembangkan. Kode ini sudah lebih maju dari itu.

Kedua, konsep **composition** diimplementasikan dengan sangat elegan. `CollegeSection` menyimpan banyak `Task`, dan setiap `Task` berisi `Deadline`. Hal ini membuat data tersusun secara **hierarkis tapi fleksibel**. Tidak hanya itu, `GrowthSection` menyimpan lima topik berbeda dengan list string untuk journaling harian. Pendekatan ini tidak hanya modular, tapi juga kreatif, karena menggabungkan logika manajemen tugas dan personal development dalam satu program. Ini adalah kombinasi yang jarang terlihat di program student project biasa.

Selanjutnya, penggunaan **Validator** menunjukkan perhatian terhadap **robustness dan user experience**. Semua input divalidasi dengan teliti, dari rentang pilihan menu hingga deskripsi karakter terbatas dan format deadline. Konsep dependency di sini juga rapi: Main tergantung pada Validator, tapi logika menu tetap bersih, bebas dari boilerplate validasi. Banyak kode lain mencampur validasi dengan logic menu sehingga terlihat berantakan, sementara di sini desainnya profesional.

Kode ini juga menunjukkan **pemikiran algoritmik** dengan `CollegeSection.sort()`. Tugas diurutkan berdasarkan **deadline terlebih dahulu**, kemudian **difficulty** sebagai prioritas sekunder. Ini bukan sekadar menampilkan data; ini memperlihatkan **perhatian pada manajemen waktu dan prioritas**, sesuatu yang jarang dipikirkan di level student project. Hal kecil ini membuat kode terlihat “pintar” dan realistis, seolah program ini bisa digunakan secara nyata untuk membantu mahasiswa mengelola tugas dan ujian.



## Kode program Java
Kode lengkap ada [di sini](https://github.com/rafaable/Struktur-Data---Paradigma-OOP/blob/main/Main.java)

```java
interface Section {
    void display();
    boolean isEmpty();
}
```

Interface `Section` nantinya akan diimplementasikan di `GrowthSection` dan `CollegeSection`
- `display()` : menampilkan konten section
- `isEmpty()` : mengembalikan `true` jika section kosong.


```java
class Deadline {
    private int hari;
    private int jam;

    public Deadline(int hari, int jam) {
        this.hari = hari;
        this.jam = jam;
    }

    public int toHours() {
        return hari * 24 + jam;
    }

    public int getHari() { return hari; }
    public int getJam() { return jam; }

    public void setHari(int hari) { this.hari = hari; }
    public void setJam(int jam) { this.jam = jam; }
}
```

Class `Deadline` menyimpan informasi tenggat waktu berupa hari dan jam, class ini berperan sebagai objek yang memudahkan penyimpanan hari & jam sekaligus. Method `toHours()` mengonversi hari menjadi jam yang bertujuan pada penggunaannya di `CollegeSection.sort()` untuk menentukan prioritas task berdasarkan deadline dengan waktu paling sedikit. Deadline dibuat sebagai class untuk membuat kode lebih modular, mudah diubah, dan lebih aman.

```java
class Task {
    private int category;
    private int kodeMatkul;
    private String desc;
    private Deadline deadline;
    private int difficulty;

    public Task(int category, int kodeMatkul, String desc, Deadline deadline, int difficulty) {
        this.category = category;
        this.kodeMatkul = kodeMatkul;
        this.desc = desc;
        this.deadline = deadline;
        this.difficulty = difficulty;
    }

    public int getCategory() { return category; }
    public int getKodeMatkul() { return kodeMatkul; }
    public String getDesc() { return desc; }
    public Deadline getDeadline() { return deadline; }
    public int getDifficulty() { return difficulty; }

    public void setCategory(int c) { category = c; }
    public void setKodeMatkul(int k) { kodeMatkul = k; }
    public void setDesc(String d) { desc = d; }
    public void setDeadline(Deadline d) { deadline = d; }
    public void setDifficulty(int d) { difficulty = d; }

    private String getCategoryString() {
        return category == 1 ? "Exam" : "Task";
    }

    private String getMatkulString() {
        switch (kodeMatkul) {
            case 1: return "Strukdat";
            case 2: return "SISOP";
            case 3: return "SBD";
            case 4: return "Kalkulus 2";
            case 5: return "IMK";
            case 6: return "AE";
        }
        return "";
    }

    public void display(int index) {
        System.out.println("List No. " + (index+1) + " : " + getCategoryString() + " " + getMatkulString());
        System.out.println("| " + desc);
        System.out.println("| Deadline " + deadline.getHari() + " hari " + deadline.getJam() + " jam");
        System.out.println("| Difficulty " + difficulty);
    }
}
```
Class `Task` merepresentasikan tugas atau ujian di college section, merupakan komposisi dari class CollegeSection
  - `category` adalah tipe tugas yang ketika input akan direpresentasikan dalam angka. 1 = Exam, 2 = Task
  - `kodeMatkul` adalah kode mata kuliah barupa angka 1 - 6 yang mewakili 6 mata kuliah selama semester 2
  - `desc` membantu mengingat rincian dan ketentuan tugas
  - `deadline` berfungsi untuk menyimpan dan mengolah input tenggat waktu
  - `difficulty` ialah tingkat kesulitan (1-5) yang akan dipertimbangkan sebagai skala prioritas
  - `display(int index)` menampilkan informasi task berdasarkan index ke sekian
  - `getCategoryString()` & `getMatkulString()` membantu konversi kode menjadi string yang lebih readable

```java
class CollegeSection implements Section {
    private ArrayList<Task> tasks = new ArrayList<>();

    public void addTask(Task t) { // komposisi Class Task di sini
        tasks.add(t);
        sort();
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void deleteTask(int index) {
        tasks.remove(index);
    }

    public void sort() { // urutan prioritas tugas berdasarkan deadline & kerumitan
        tasks.sort((a, b) -> {
            int d = a.getDeadline().toHours() - b.getDeadline().toHours();
            if (d == 0) return b.getDifficulty() - a.getDifficulty();
            return d;
        });
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    public void display() {
        if (isEmpty()) {
            System.out.println("Tidak ada data! Enter untuk menu dan tambahkan data.");
            return;
        }

        System.out.println("---------------------------------------------------------------");
        System.out.println("Task & Exam List!");
        System.out.println("Total: " + tasks.size());

        for (int i = 0; i < tasks.size(); i++) {
            tasks.get(i).display(i);
        }
    }
}
```
Class `CollegeSection` mengimplementasikan interface `Section` dan menyimpan banyak `Task` sebagai komposisinya. 

Terdapat atribut private `task` berupa ArrayList dari class Task karena CollegeSection berfungsi untuk menyimpan banyak list tugas dan ujian, atribut private ini akan diakses dari luar menggunakan `getTasks()`. 

Dalam CollegeSection juga dibuat beberapa fungsi untuk mengolah task seperti `addTask()` yang fungsinya mirip seperti setter, namun sekaligus melakukan tugas `sort()` yangmana fungsi `sort()` juga didefinisikan dalam class CollegeSection, cara kerjanya adalah dengan mendahulukan list yang waktunya paling sedikit, list dengan tenggat waktu sama akan diprioritaskan jika kesulitannya lebih tinggi

Terdapat pula fungsi boolean `isEmpty()` dan fungsi `display()` yang didalamnya terdapat validasi apakah list masih kosong atau tidak. Jika list tidak kosong akan dilakukan print output dengan looping, tiap indeks `i` pada array `task` akan dijalankan fungsi `display(i)` yangmana fungsi display dengan parameter index diambil dari class Task

```java
class GrowthSection implements Section {
    private List<List<String>> data;

    public GrowthSection() {
        data = new ArrayList<>(); 
        for (int i = 0; i < 5; i++) {
            data.add(new ArrayList<>()); 
        }
    }

    public void add(int index, String val) {
        data.get(index).add(val);
    }

    public ArrayList<String> get(int index) {
        return (ArrayList<String>) data.get(index);
    }

    public void delete(int topic, int index) {
        data.get(topic).remove(index);
    }

    public boolean isEmpty() {
        for (List<String> l : data) {
            if (!l.isEmpty()) return false;
        }
        return true;
    }
```
Class `GrowthSection` memiliki atribut private `data` yang diakses menggunakan fungsi `get(int index)`, atribut ini merupakan kotak berisi Array dalam Array atau `List<List<String>>` dikarenakan section ini memiliki 5 topik yang harus dikemas dalam sebuat list, setiap topik akan diisi beberapa input sehingga tiap list topik perlu diberikan list juga. Sama seperti `CollegeSection`, section ini juga dilengkapi funggsi add, delete, isEmpty dan display. Meskipun memiliki beberapa fungsionalitas sama, tidak semua bisa dijadikan satu interface karena perbedaan parameter yang dibutuhkan oleh fungi tersebut.

```java
class Validator {
    static int inputRange(Scanner sc, int min, int max, String msg) {
        int x;
        while (true) {
            try {
                x = Integer.parseInt(sc.nextLine());
                if (x >= min && x <= max) return x;
            } catch (Exception e) {}
            System.out.print(msg);
        }
    }

    static String inputDesc(Scanner sc) {
        String s;
        while (true) {
            s = sc.nextLine();
            if (s.length() <= 50) return s;
            System.out.print("Input lebih dari 50 karakter!\nInput ulang deskripsi:");
        }
    }

    static Deadline inputDeadline(Scanner sc) {
        while (true) {
            try {
                String input = sc.nextLine();
                String[] p = input.split(" ");
                int hari = Integer.parseInt(p[0]);
                int jam = Integer.parseInt(p[2]);
                if (jam >= 0 && jam <= 24) return new Deadline(hari, jam);
            } catch (Exception e) {}
            System.out.println("Invalid! Input sesuai format atau rentang jam yang benar.");
            System.out.print("Input deadline:");
        }
    }
}
```
Class `Validator` sangat berperan penting dalam memeriksa input dari user agar program tetap aman dan konsisten. Semua method bersifat **static**, sehingga bisa langsung dipanggil tanpa membuat objek `Validator`. Ini sengikuti salah satu prinsip **Single Responsibility**, di mana setiap class punya tugas jelas,sebagaimana class `Validator` khusus untuk validasi

Method `inputRange` memastikan input berupa angka (`int`) dalam **rentang tertentu** (`min` sampai `max`), method ini menarik karena bisa digunakan di banyak situasi berbeda tanpa menulis ulang kode yang mirip, misal memilih menu (1–5), memilih kode mata kuliah (1–6), memasukkan tingkat difficulty (1–5) dan lain-lain. Method ii juga bisa customize pesan error dan akan otomatis meminta input ulang hingga valid

Class ini juga memiliki method `inputDesc` untuk membatas input supaya tidak melebihi 50 karakter, serta method `inputDeadline` untuk membatasi hari dan jam dalam rentang yang masuk akal

```java
static void addMenu() {
        System.out.println("\nPilih section untuk ditambahkan\n1. College section\n2. Growth section");
        System.out.print("Pilih section:");
        int c = Validator.inputRange(sc,1,2,"Invalid input!\nPilih section:");

        if (c == 1) addCollege();
        else addGrowth();
    }

    static void addCollege() {
        while (true) {
            System.out.println("\nKategori 1.Exam 2.Task");
            System.out.print("Pilih kategori:");
            int cat = Validator.inputRange(sc,1,2,"Invalid input!\nPilih kategori:");

            System.out.println("\nKode mata kuliah");
            System.out.println("1. Strukdat");
            System.out.println("2. SISOP");
            System.out.println("3. SBD");
            System.out.println("4. Kalkulus 2");
            System.out.println("5. IMK");
            System.out.println("6. AE");
            System.out.print("Pilih kode matkul:");
            int mk = Validator.inputRange(sc,1,6,"Invalid input!\nPilih kode matkul:");

            System.out.print("Deskripsi:");
            String desc = Validator.inputDesc(sc);

            System.out.print("Deadline (contoh 1 hari 3 jam):");
            Deadline d = Validator.inputDeadline(sc);

            System.out.print("Difficulty 1 - 5:");
            int diff = Validator.inputRange(sc,1,5,"Input harus rentang 1-5!\nInput ulang difficulty: ");

            college.addTask(new Task(cat,mk,desc,d,diff));

            System.out.print("Tambah lagi? 1.Ya 2.Tidak\nPilih opsi:");
            int opt = Validator.inputRange(sc,1,2,"Invalid input!\nPilih opsi:");
            if (opt == 2) break;
        }
    }

    static void addGrowth() {
        String[] titles = {
            "Kejadian & orang baik hari ini",
            "Apa yang bisa kuperbaiki hari ini",
            "Sincere wishlist hari ini",
            "Progress/achievement kecil hari ini",
            "Pelajaran yang bisa diambil hari ini"
        };

        for (int i = 0; i < 5; i++) {
            System.out.println("\n--- Part O" + (i+1) + "/O5 ) " + titles[i]);
            System.out.println("(ketik xxx untuk lanjut ke section berikutnya)");

            int nomor = 1;

            while (true) {
                System.out.print(nomor + ". ");
                String s = sc.nextLine();

                if (s.equals("xxx")) break;

                growth.add(i, s);
                nomor++;
            }
        }

        System.out.println("Berhasil menambahkan growth section!");
    }

    static void displayMenu() {
        System.out.println("\nPilih section untuk ditampilkan");
        System.out.print("1. College section\n2. Growth section\n3. Both\nPilih section:");
        int c = Validator.inputRange(sc,1,3,"Invalid input!\nPilih section:");

        if (c == 1) college.display();
        else if(c == 2) growth.display();
        else {
            college.display();
            growth.display();
        }
        sc.nextLine();
    }

    static void deleteMenu() {
        System.out.println("\nPilih section untuk dihapus");
        System.out.print("1. College section\n2. Growth section\nPilih section:");
        int c = Validator.inputRange(sc,1,2,"Invalid input!\nPilih section:");

        if (c == 1) {
            if (college.isEmpty()) {
                System.out.println("Tidak ada data! Enter untuk menu dan tambahkan data.");
                sc.nextLine();
                return;
            }
            college.display();
            System.out.println("Hapus index:");
            int idx = Validator.inputRange(sc,1,college.getTasks().size(),"Tidak ada data!") - 1;
            college.deleteTask(idx);
        } else {
            if (growth.isEmpty()) {
                System.out.println("Tidak ada data! Enter untuk menu dan tambahkan data.");
                sc.nextLine();
                return;
            }

            System.out.println("\nPilih topik growth:");
            System.out.println("1. Kejadian & orang baik hari ini");
            System.out.println("2. Apa yang bisa kuperbaiki hari ini");
            System.out.println("3. Sincere wishlist hari ini");
            System.out.println("4. Progress/achievement kecil hari ini");
            System.out.println("5. Pelajaran yang bisa diambil hari ini");
            System.out.print("Pilih topik 1-5:");
            int t = Validator.inputRange(sc,1,5,"Invalid input! Pilih topik 1 - 5:") - 1;
            ArrayList<String> list = growth.get(t);

            if (t == 0) { System.out.println("\nKejadian & orang baik hari ini");} 
            else if (t == 1) { System.out.println("\nApa yang bisa kuperbaiki hari ini");} 
            else if (t == 2) { System.out.println("\nSincere wishlist hari ini");} 
            else if (t == 3) { System.out.println("\nProgress/achievement kecil hari ini");} 
            else if (t == 4) { System.out.println("\nPelajaran yang bisa diambil hari ini");}
            for (int i=0;i<list.size();i++) {
                System.out.println((i+1)+". "+list.get(i));
            }

            System.out.print("Pilih list ke:");
            int idx = Validator.inputRange(sc,1,list.size(),"Invalid input!") - 1;
            growth.delete(t, idx);
        }
    }

    static void editMenu() {
        System.out.println("\nPilih section untuk diubah");
        System.out.print("1. College section\n2. Growth section\nPilih section:");
        int c = Validator.inputRange(sc,1,2,"Invalid input!\nPilih section:");

        if (c == 1) {
            if (college.isEmpty()) {
                System.out.println("Tidak ada data! Enter untuk menu dan tambahkan data.");
                sc.nextLine();
                return;
            }
            college.display();
            System.out.print("Edit list nomor: ");
            int idx = Validator.inputRange(sc,1,college.getTasks().size(),"Invalid") - 1;
            Task t = college.getTasks().get(idx);

            System.out.println("\nKategori 1.Exam 2.Task");
            System.out.print("Edit kategori:");
            t.setCategory(Validator.inputRange(sc,1,2,"Invalid input!\nEdit kategori:"));

            System.out.println("\nKode mata kuliah");
            System.out.println("1. Strukdat");
            System.out.println("2. SISOP");
            System.out.println("3. SBD");
            System.out.println("4. Kalkulus 2");
            System.out.println("5. IMK");
            System.out.println("6. AE");
            System.out.print("Edit kode matkul:");
            t.setKodeMatkul(Validator.inputRange(sc,1,6,"Invalid input!\nEdit kode matkul:"));

            System.out.print("Edit desc:");
            t.setDesc(Validator.inputDesc(sc));

            System.out.print("Edit deadline:");
            t.setDeadline(Validator.inputDeadline(sc));

            System.out.print("Edit difficulty:");
            t.setDifficulty(Validator.inputRange(sc,1,5,"Invalid input!\nEdit difficulty:"));

            college.sort();

        } else {
            if (growth.isEmpty()) {
                System.out.println("Tidak ada data! Enter untuk menu dan tambahkan data.");
                sc.nextLine();
                return;
            }

            System.out.println("\nPilih topik growth:");
            System.out.println("1. Kejadian & orang baik hari ini");
            System.out.println("2. Apa yang bisa kuperbaiki hari ini");
            System.out.println("3. Sincere wishlist hari ini");
            System.out.println("4. Progress/achievement kecil hari ini");
            System.out.println("5. Pelajaran yang bisa diambil hari ini");
            System.out.print("Topik 1-5:");
            int t = Validator.inputRange(sc,1,5,"Invalid input!\nTopik 1-5:") - 1;

            if (t == 0) { System.out.println("\nKejadian & orang baik hari ini");} 
            else if (t == 1) { System.out.println("\nApa yang bisa kuperbaiki hari ini");} 
            else if (t == 2) { System.out.println("\nSincere wishlist hari ini");} 
            else if (t == 3) { System.out.println("\nProgress/achievement kecil hari ini");} 
            else if (t == 4) { System.out.println("\nPelajaran yang bisa diambil hari ini");}
            ArrayList<String> list = growth.get(t);

            for (int i=0;i<list.size();i++) {
                System.out.println((i+1)+". "+list.get(i));
            }

```
Pada bagian awal class ini terdapat `college` yang merupakan instansiasi objek dari `CollegeSection`, serta `growth` yang merupakan instansiasi objek dari `GrowthSection()`

Class `main` berisi tampilan yang pertama kali dilihat user, yaitu `menu`, tiap menu akan dijalankan oleh beberapa fungsi sesuai kinerja yang dibutuhkan, fungsi-sungsi tersebut diletakkan dalam satu class `Main` yang sama melihat cara kerja satu sama lain yang saling berkesinambungan. 

1. Tampilkan rekap menggunakan `displayMenu()`

Pengguna akan diminta untuk memilih section menggunakan kode angka, lalu divalidasi menggunakan method `inputRange` dari class `Validator`. Memilih section 1 maka akan dijalankan `college.display()`, yaitu method `display()` dari class `CollegeSection`, class asal dari objek college. Memilih section 2 maka akan dijalankan `growth.display()`, yaitu method `display()` dari class `GrowthSection`, class asal dari objek `growth`. Pengguna harus menekan tombol enter untuk kembali ke opsi menu, tujuannya supaya list yang ditampilkan tidak tertimbun opsi menu yang muncul lebih dini

2. Edit dengan fungsi `editMenu()`

Akan dilakukan validasi ketika user memilih section untuk diedit dalam kode angka.

Jika user memilih angka 1 atau edit college, akan dilakukan pemeriksaan data kosong. Lalu user akan ditampilkan list yang sudah ada agar bisa menentukan list ke berapa yang akan diedit. Setelahnya user akan diminta melakukan input untuk perubahan kategori, mata kuliah, sedkripsi, deadline, dan difficuly. Input dilakukan dengan setter karena sebelumnya atribut bersifat private. Input akan diurutkan ulang berdasarkan prioritas dan urgensinya

Jika user memilih angka 1 atau edit college, akan dilakukan pemeriksaan data kosong. User akan diminta memilih topik growth mana yang ingin diedit untuk ditampilkan listnya oleh sistem. Setelah list topik terkait ditampilkan, user diminta memilih list mana yang akan diedit, disertai validasi. User selanjutnya akan menginput isi yang baru dan harus menekan enter untuk kembali ke menu utama

3. Tambah dengan `addMenu()`

Pada bagian awal fungsi, user akna diminta untuk memilih section diserttai validasi

Opsi pertama bertujuan unutk menambahkan data tugas/exam di objek college. Di sini, validator banyak digunakan terutama untuk memasukkan data baru. Setelah data baru dimasukkan, akan dijalankan fungsi addTask() untuk objek `college` dengan memasukkan parameter sesuai input baru yang dimasukkan user. Di akhir user akan ditawarkan apakah ingin melakukan penambahan lagi untuk mempermudah tambah data beruntun

Pada opsi kedua `addGrowth()` dibuat array tittle yang mewakili 5 topik growth. Dilakukan looping untuk kelima topik, di mana setiap iterasi akan terdapat nested loop untuk memungkinkan menulis beberapa list dalam satu topik. Nested loop atau pengisian list per topik akan berakhir dengan mengetikkan "xxx" dan user akan diberikan konfirmasi apabila growth section telah ditambahkan

4. Hapus dengan `deleteMenu()`

User akan diminta memilih section terlebih dahulu, disertai validasi dengan memanggil fungsi `inputRange` dari class `Validator` yang sebelumnya dibuat

Jika dilakukan input angka 1, akan diarahkan ke penghapusan objek college dengan dilakukan validasi untuk memastikan list tidak kosong. Setelahnya akan dilakukan display dan user diminta memilih list ke berapa yang akan dihapus, input bagian ini juga disertai validasi dan peringatan jika invalid input.

Jika dilakukan input angka 2, dilakukan validasi `isEmpty()` sebelum akhirnya user diminta memilih topik growth mana yang akan dihapus. Setelah memilih topik, user akan ditampilkan topik terkait untuk selanjutnya memilih list spesifik yang ingin dihapus, input bagian ini juga disertai validasi

5. Exit

Sebelum keluar dari program, sistem akan menghapus objek college sekaligus growth

