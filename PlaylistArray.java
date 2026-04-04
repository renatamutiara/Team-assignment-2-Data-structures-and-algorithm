import java.util.Scanner;

// Identitas Kelompok
// Nama Anggota 1: DARREN JEHONATHAN - 2902714100
// Nama Anggota 2: JOSHUA CHRISTIAN SUPIT- 2902706736
// Nama Anggota 3: GABRIEL SIGALINGGING - 2902724442
// Nama Anggota 4: RENATA MUTIARA NIRWANA - 2902733131
// Nama Anggota 5: EVELYN KIMORA TJIU - 2902692466

// Class Lagu (Sesuai tugas sebelumnya)
class Lagu {
    private String judul;
    private String artis;
    private double durasi;

    public Lagu(String judul, String artis, double durasi) {
        this.judul = judul;
        this.artis = artis;
        this.durasi = durasi;
    }

    public String getJudul() { return judul; }
    public String getArtis() { return artis; }
    public double getDurasi() { return durasi; }

    public void tampilkanInfo() {
        System.out.println("Judul: " + judul + " | Artis: " + artis + " | Durasi: " + durasi + " menit");
    }
}

// Class KelolaPlaylist untuk mengatur operasi array (Sebelumnya bernama PlaylistArray)
class KelolaPlaylist {
    private Lagu[] playlist = new Lagu[10]; // Maksimal 10 lagu
    private int jumlahLagu = 0;

    // 1. Traversal: menampilkan semua isi array
    public void tampilkanSemuaLagu() {
        if (jumlahLagu == 0) {
            System.out.println("Playlist masih kosong nih.");
            return;
        }
        System.out.println("\n--- Daftar Playlist ---");
        for (int i = 0; i < jumlahLagu; i++) {
            System.out.print((i + 1) + ". ");
            playlist[i].tampilkanInfo();
        }
    }

    // 2. Insertion: menambahkan data ke array
    public void tambahLagu(Lagu laguBaru) {
        if (jumlahLagu < 10) {
            playlist[jumlahLagu] = laguBaru;
            jumlahLagu++;
            System.out.println("Sip, lagu '" + laguBaru.getJudul() + "' berhasil ditambah.");
        } else {
            System.out.println("Maaf, playlist udah penuh (maks 10 lagu).");
        }
    }

    // 3. Deletion: hapus dan geser elemen
    public void hapusLagu(String judul) {
        int index = -1;
        // Cari dulu lagunya
        for (int i = 0; i < jumlahLagu; i++) {
            if (playlist[i].getJudul().equalsIgnoreCase(judul)) {
                index = i;
                break;
            }
        }

        if (index != -1) {
            // Kalau ketemu, geser elemen dari kanan ke kiri buat nutup array yang kosong
            for (int i = index; i < jumlahLagu - 1; i++) {
                playlist[i] = playlist[i + 1];
            }
            playlist[jumlahLagu - 1] = null; // Kosongin index terakhir
            jumlahLagu--;
            System.out.println("Lagu '" + judul + "' berhasil dihapus.");
        } else {
            System.out.println("Lagu '" + judul + "' gak ketemu.");
        }
    }

    // 4. Searching: cari lagu menggunakan linear search
    public void cariLagu(String judul) {
        for (int i = 0; i < jumlahLagu; i++) {
            if (playlist[i].getJudul().equalsIgnoreCase(judul)) {
                System.out.println("Ketemu! Ada di urutan ke-" + (i + 1));
                playlist[i].tampilkanInfo();
                return; // Langsung keluar method kalau udah ketemu
            }
        }
        System.out.println("Lagu '" + judul + "' gak ada di playlist.");
    }

    // 5. Sorting: Mengurutkan lagu berdasarkan durasi terpendek menggunkan bubble sort
    public void urutkanLaguBerdasarkanDurasi() {
        for (int i = 0; i < jumlahLagu - 1; i++) {
            for (int j = 0; j < jumlahLagu - i - 1; j++) {
                if (playlist[j].getDurasi() > playlist[j + 1].getDurasi()) {
                    Lagu temp = playlist[j];
                    playlist[j] = playlist[j + 1];
                    playlist[j + 1] = temp;
                }
            }
        }
        System.out.println("Playlist udah diurutin berdasarkan durasi.");
    }
}


public class PlaylistArray {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        KelolaPlaylist playlist = new KelolaPlaylist(); // Menggunakan class KelolaPlaylist
        int menu = 0;

        while (menu != 6) {
            System.out.println("\n=== MENU PLAYLIST MUSIK ===");
            System.out.println("1. Tampilkan semua lagu");
            System.out.println("2. Tambah lagu baru");
            System.out.println("3. Hapus lagu berdasarkan judul");
            System.out.println("4. Cari lagu berdasarkan judul");
            System.out.println("5. Urutkan lagu berdasarkan durasi");
            System.out.println("6. Keluar");
            System.out.print("Pilih menu: ");
            
            if (scanner.hasNextInt()) {
                menu = scanner.nextInt();
                scanner.nextLine(); // bersihin enter

                switch (menu) {
                    case 1:
                        playlist.tampilkanSemuaLagu();
                        break;
                    case 2:
                        System.out.print("Judul Lagu: ");
                        String judul = scanner.nextLine();
                        System.out.print("Artis: ");
                        String artis = scanner.nextLine();
                        System.out.print("Durasi (menit, contoh 3.5): ");
                        
                        // Validasi input durasi
                        while (!scanner.hasNextDouble()) {
                            System.out.print("Input salah, masukkan durasi pakai angka (contoh 3.5): ");
                            scanner.next();
                        }
                        double durasi = scanner.nextDouble();
                        scanner.nextLine(); // bersihin enter
                        
                        playlist.tambahLagu(new Lagu(judul, artis, durasi));
                        break;
                    case 3:
                        System.out.print("Judul lagu yang mau dihapus: ");
                        String judulHapus = scanner.nextLine();
                        playlist.hapusLagu(judulHapus);
                        break;
                    case 4:
                        System.out.print("Judul lagu yang dicari: ");
                        String judulCari = scanner.nextLine();
                        playlist.cariLagu(judulCari);
                        break;
                    case 5:
                        playlist.urutkanLaguBerdasarkanDurasi();
                        break;
                    case 6:
                        System.out.println("Keluar program...");
                        break;
                    default:
                        System.out.println("Menu gak ada, pilih 1-6 aja.");
                }
            } else {
                System.out.println("Input salah, masukin angka ya.");
                scanner.next(); // bersihin input yang salah
            }
        }
        scanner.close();
    }
}