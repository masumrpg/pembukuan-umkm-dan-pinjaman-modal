# Dokumentasi Aplikasi Pembukuan UMKM

## Deskripsi

Aplikasi Pembukuan UMKM hadir sebagai solusi inovatif bagi pelaku usaha kecil dan menengah, terutama nasabah Bank
Mandiri, yang ingin mengelola keuangan mereka dengan mudah dan efisien. Dalam dunia yang semakin terhubung dan cepat
berubah, pengelolaan keuangan yang tepat dan transparan menjadi kunci keberhasilan setiap bisnis. Melalui aplikasi ini,
pengusaha UMKM dapat mencatat semua transaksi mereka secara otomatis, melacak laporan keuangan, serta mempermudah
pengajuan pinjaman kepada pihak bank tanpa birokrasi yang rumit.

---

## Ide dari Aplikasi Ini

Di Indonesia, UMKM (Usaha Mikro, Kecil, dan Menengah) merupakan tulang punggung perekonomian. Namun, seringkali mereka
menghadapi tantangan besar dalam mengelola keuangan dan mendapatkan akses pembiayaan. Banyak pengusaha kecil merasa
kesulitan dalam mencatat arus kas atau melaporkan kondisi keuangan yang dapat membantu mereka memperoleh pinjaman dari
bank.

Aplikasi Pembukuan UMKM hadir untuk menjembatani tantangan ini. Bayangkan seorang pengusaha yang baru memulai bisnis
kuliner, atau bahkan pemilik toko kelontong di desa, yang tidak memiliki akses ke software pembukuan mahal atau terlalu
rumit. Dengan aplikasi ini, mereka dapat mengatur keuangan mereka, mengajukan pinjaman, dan melaporkan transaksi secara
mudah—semua dalam satu platform yang terintegrasi.

---

## Fitur Utama

1. **Manajemen Keuangan**  
   Aplikasi ini memungkinkan pengusaha untuk mencatat setiap pendapatan dan pengeluaran mereka dalam bentuk yang
   sederhana dan terorganisir. Tidak perlu lagi buku besar manual atau spreadsheet yang membingungkan.

2. **Pengajuan Pinjaman**  
   Mengajukan pinjaman kepada Bank Mandiri kini bisa dilakukan langsung melalui aplikasi. Pengusaha dapat mengajukan
   pinjaman tanpa harus antri panjang atau menunggu berbulan-bulan untuk mendapatkan keputusan. Semua dilakukan dalam
   hitungan hari, bahkan jam!

3. **Laporan Keuangan Real-Time**  
   Dengan tampilan yang intuitif, aplikasi ini menyajikan laporan keuangan dalam format yang mudah dipahami, bahkan oleh
   pengguna yang tidak berpengalaman dalam akuntansi.

---

## Teknologi yang Digunakan

- **Backend:** Java Spring Framework (Spring IoC) - Dibangun dengan arsitektur modular untuk memudahkan pengembangan dan
  pemeliharaan aplikasi.
- **Database:** Native SQL Query PostgreSql - Menjamin kecepatan dan efisiensi dalam pengelolaan data besar yang
  dihasilkan oleh
  transaksi UMKM.
- **Pemrograman Fungsional:** Java Stream - Memudahkan pemrosesan data dalam jumlah besar secara efisien.

---

## Detail Implementasi

# Dokumentasi Struktur Tabel Aplikasi Pembukuan UMKM

## 1. Tabel `categories`

### Kolom

| Kolom         | Tipe Data    | Ketentuan        |
|---------------|--------------|------------------|
| `id`          | varchar(255) | NOT NULL, Unique |
| `created_at`  | timestamp(6) | NOT NULL         |
| `description` | text         |                  |
| `name`        | varchar(50)  | NOT NULL         |
| `type`        | varchar(255) | NOT NULL         |
| `updated_at`  | timestamp(6) | NOT NULL         |

### Indeks

- **#1:** Unique Index pada `id`.

### Kunci

- **PK (id):** Primary Key pada kolom `id`.

### Cek

- **categories_type_check:** Memastikan bahwa kolom `type` hanya dapat berisi nilai 'INCOME' atau 'EXPENSE'.

---

## 2. Tabel `loan_applications`

### Kolom

| Kolom             | Tipe Data     | Ketentuan        |
|-------------------|---------------|------------------|
| `id`              | varchar(255)  | NOT NULL, Unique |
| `approval_status` | varchar(255)  | NOT NULL         |
| `approved_at`     | timestamp(6)  |                  |
| `created_at`      | timestamp(6)  | NOT NULL         |
| `duration_months` | integer       | NOT NULL         |
| `interest_rate`   | numeric(5,2)  | NOT NULL         |
| `loan_amount`     | numeric(15,2) | NOT NULL         |
| `notes`           | text          |                  |
| `approved_by`     | varchar(255)  |                  |
| `user_id`         | varchar(255)  | NOT NULL         |

### Indeks

- **#1:** Unique Index pada `id`.

### Kunci

- **PK (id):** Primary Key pada kolom `id`.
- **FK (approved_by):** Foreign Key pada kolom `approved_by`, merujuk ke tabel `users(id)`.
- **FK (user_id):** Foreign Key pada kolom `user_id`, merujuk ke tabel `users(id)`.

### Cek

- **loan_applications_approval_status_check:** Memastikan bahwa `approval_status` hanya dapat berisi nilai 'PENDING', '
  APPROVED', atau 'REJECTED'.

---

## 3. Tabel `monthly_reports`

### Kolom

| Kolom            | Tipe Data     | Ketentuan        |
|------------------|---------------|------------------|
| `id`             | varchar(255)  | NOT NULL, Unique |
| `created_at`     | timestamp(6)  | NOT NULL         |
| `ending_balance` | numeric(15,2) | NOT NULL         |
| `month`          | integer       | NOT NULL         |
| `status`         | varchar(255)  | NOT NULL         |
| `total_expense`  | numeric(15,2) | NOT NULL         |
| `total_income`   | numeric(15,2) | NOT NULL         |
| `updated_at`     | timestamp(6)  | NOT NULL         |
| `year`           | integer       | NOT NULL         |
| `user_id`        | varchar(255)  | NOT NULL         |

### Indeks

- **#1:** Unique Index pada `id`.

### Kunci

- **PK (id):** Primary Key pada kolom `id`.
- **FK (user_id):** Foreign Key pada kolom `user_id`, merujuk ke tabel `users(id)`.

### Cek

- **monthly_reports_status_check:** Memastikan bahwa `status` hanya dapat berisi nilai 'DRAFT' atau 'FINAL'.

---

## 4. Tabel `transactions`

### Kolom

| Kolom                | Tipe Data     | Ketentuan        |
|----------------------|---------------|------------------|
| `id`                 | varchar(255)  | NOT NULL, Unique |
| `amount`             | numeric(15,2) | NOT NULL         |
| `created_at`         | timestamp(6)  | NOT NULL         |
| `description`        | text          | NOT NULL         |
| `payment_type`       | varchar(255)  | NOT NULL         |
| `status`             | varchar(255)  | NOT NULL         |
| `transaction_date`   | date          | NOT NULL         |
| `transaction_number` | varchar(30)   | NOT NULL, Unique |
| `transaction_proof`  | varchar(255)  |                  |
| `updated_at`         | timestamp(6)  | NOT NULL         |
| `category_id`        | varchar(255)  | NOT NULL         |
| `user_id`            | varchar(255)  | NOT NULL         |

### Indeks

- **#1:** Unique Index pada `id`.
- **uk3w93192dhkdixcb3xncuf84pj:** Unique Index pada `transaction_number`.

### Kunci

- **PK (id):** Primary Key pada kolom `id`.
- **AK (transaction_number):** Alternate Key pada kolom `transaction_number`.
- **FK (category_id):** Foreign Key pada kolom `category_id`, merujuk ke tabel `categories(id)`.
- **FK (user_id):** Foreign Key pada kolom `user_id`, merujuk ke tabel `users(id)`.

### Cek

- **transactions_payment_type_check:** Memastikan bahwa `payment_type` hanya dapat berisi nilai 'CASH', 'TRANSFER', '
  DEBIT', atau 'CREDIT'.
- **transactions_status_check:** Memastikan bahwa `status` hanya dapat berisi nilai 'PENDING', 'COMPLETED', atau '
  CANCELLED'.

---

## 5. Tabel `users`

### Kolom

| Kolom          | Tipe Data    | Ketentuan        |
|----------------|--------------|------------------|
| `id`           | varchar(255) | NOT NULL, Unique |
| `address`      | text         |                  |
| `created_at`   | timestamp(6) | NOT NULL         |
| `email`        | varchar(100) | NOT NULL, Unique |
| `full_name`    | varchar(100) | NOT NULL         |
| `password`     | varchar(255) | NOT NULL         |
| `phone_number` | varchar(15)  |                  |
| `status`       | varchar(255) | NOT NULL         |
| `updated_at`   | timestamp(6) | NOT NULL         |
| `username`     | varchar(50)  | NOT NULL, Unique |

### Indeks

- **#1:** Unique Index pada `id`.
- **uk6dotkott2kjsp8vw4d0m25fb7:** Unique Index pada `email`.
- **ukr43af9ap4edm43mmtq01oddj6:** Unique Index pada `username`.

### Kunci

- **PK (id):** Primary Key pada kolom `id`.
- **AK (email):** Alternate Key pada kolom `email`.
- **AK (username):** Alternate Key pada kolom `username`.

### Cek

- **users_status_check:** Memastikan bahwa `status` hanya dapat berisi nilai 'ACTIVE' atau 'INACTIVE'.

---

## API Endpoints

### 1. User Management

- **`POST /api/users/register`**  
  Untuk mendaftarkan pengguna baru.

- **`POST /api/users/login`**  
  Untuk autentikasi pengguna dan mendapatkan JWT.

### 2. Loan Application

- **`POST /api/loans/apply`**  
  Mengajukan pinjaman baru.

- **`GET /api/loans`**  
  Mendapatkan daftar pengajuan pinjaman berdasarkan status atau ID pengguna.

- **`PUT /api/loans/{id}/approve`**  
  Menyetujui pengajuan pinjaman.

- **`PUT /api/loans/{id}/reject`**  
  Menolak pengajuan pinjaman.

---

## Catatan Tambahan

1. Semua operasi CRUD dilakukan menggunakan Native SQL Query.
2. Aplikasi ini dirancang dengan fokus pada integrasi dengan sistem internal Bank Mandiri.
3. Dokumentasi API lebih lengkap tersedia
   di [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html).

---

## Kontributor

- **Ma'sum** - Calon Developer di,
- **Bank Mandiri Team** - Konsultan Bisnis

---
