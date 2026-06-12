# Github
Bisa di powershell windows btw
### Baru bikin pertama kali
Masuk ke folder tempat kamu mau nyimpen file backendnya, misal:
```
cd Documents        # atau folder mana saja yang kamu mau
```
Clone repo
```
git clone https://github.com/rafaable/Backend_RentalMotor.git
```
Masuk dulu ke folder baru yang udah di-clone!
```
cd Backend_RentalMotor
```
Kalau udah masuk, ketik ini buat masuk terminal vscode langsung
```
code .
```
### Sebelumnya udah clone
Kalau sebelumnya udah clone dan **jaga jaga kalau beberapa file di github ada perubahan**, kalian bisa pull dulu TAPI pastikan kalian sudah masuk folder backendnya, misal:
```
cd path/ke/Backend_RentalMotor
```
Baru pull
```
git pull origin main
code .
```
### Prosedur push
Ini buat cek dulu, setelah kamu ngerjain tuh yang berubah apa aja
```
git status
```
1. Case mau push semua perubahan (tapi koordinasi dulu kalau pas cek git status, file bagian anggota lain ikut keubah)
   ```
   git add .
   git commit -m "nambahin atau ngubah apa gitu misal"
   git push origin main
   ```
2. Push folder tertentu saja:
   ```
   git add nama-folder/
   git commit -m "update folder ini"
   git push origin main
   ```
3. Push file tertentu saja:
   ```
   git add folder/nama-file.py
   git commit -m "update file ini"
   git push origin main
   ```


