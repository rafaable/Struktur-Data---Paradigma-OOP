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
- `Section` → diimplementasikan oleh `CollegeSection` dan `GrowthSection`
- `CollegeSection` → memiliki banyak `Task`
- `Task` → memiliki `Deadline` sebagai komposisi
- `Main` → menggunakan `CollegeSection`, `GrowthSection`, dan `Validator`

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

```
