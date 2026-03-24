import java.util.*;

// interface
interface Section {
    void display();
    boolean isEmpty();
}

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

// Komposisi dari class CollegeSection
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

// Implementasi interface Section, tiap CollegeSection memiliki banyak Task
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

/* Implementasi interface Section, terdiri dari 5 list topik
masing-masing topik terdiri dari List<String> mirip journalling */
class GrowthSection implements Section {
    private List<List<String>> data;

    // constructor secara default membuat sejumlah 5 ArrayList
    public GrowthSection() {
        data = new ArrayList<>(); // data adalah representasi dari 5 topik GrowthSection
        for (int i = 0; i < 5; i++) {
            data.add(new ArrayList<>()); // isi list ke masing masing ArrayList 'data'
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

    public void display() {
        if (isEmpty()) {
            System.out.println("Tidak ada data! Enter untuk menu dan tambahkan data.");
            return;
        }

        System.out.println("\n---------------------------------------------------------------");
        System.out.println("Hari ini aku bersyukur karena");
        printList(data.get(0));

        System.out.println("Kedepannya aku bakal perbaiki hal-hal kecil seperti");
        printList(data.get(1));

        System.out.println("Dengan sepenuh hati, aku harap");
        printList(data.get(2));

        System.out.println("Apresiasi buat diriku sendiri karena");
        printList(data.get(3));

        System.out.println("Aku belajar sesuatu hari ini");
        printList(data.get(4));
    }

    private void printList(List<String> list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.println((i+1) + ". " + list.get(i));
        }
    }
}

// validator berhubungan depedency dengan Class Main
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

public class Main {
    static Scanner sc = new Scanner(System.in);
    static CollegeSection college = new CollegeSection();
    static GrowthSection growth = new GrowthSection();

    public static void main(String[] args) {

        while (true) {
            System.out.println("\n--- Menu ---");
            System.out.println("1. Tampilkan rekap");
            System.out.println("2. Edit");
            System.out.println("3. Tambah");
            System.out.println("4. Hapus");
            System.out.println("5. Exit");
            System.out.print("Pilih menu:");
            int menu = Validator.inputRange(sc,1,5,"Invalid input!\nPilih menu:");

            switch (menu) {
                case 1: displayMenu(); break;
                case 2: editMenu(); break;
                case 3: addMenu(); break;
                case 4: deleteMenu(); break;
                case 5:
                    college.getTasks().clear();
                    System.out.println("Program selesai!");
                    return;
            }
        }
    }

    // bagian method -----------------------------------------------
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
            else if (t == 4) { System.out.println("\nPelajaran yang bisa diambil hari ini");
}
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

            System.out.print("Edit list ke:");
            int idx = Validator.inputRange(sc,1,list.size(),"Invalid input!\nEdit list ke:") - 1;
            System.out.print("Isi baru:");
            list.set(idx, sc.nextLine());
        }
    }
}