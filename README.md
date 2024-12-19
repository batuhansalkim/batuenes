# LibraryManagementSystem
Batuhan SALKIM 1210505904 - Github Adresi : https://github.com/batuhansalkim
Enes YENİGÜN 1220505062 - github Adresi : https://github.com/Enesyngn
Kütüphane Yönetim Sistemi

Bu proje, Java Swing ile geliştirilmiş bir Kütüphane Yönetim Sistemi uygulamasıdır. Sistem, öğrenciler, personel ve yöneticiler için kitap ve kullanıcı yönetimini kolaylaştıracak bir platform sunar. Kullanıcı dostu bir arayüz ve verimli bir mimari ile tasarlanan bu uygulama, rol tabanlı erişim kontrolü sağlayarak her seviyeden kullanıcıya hitap eder.

Özellikler

Giriş ve Kayıt: Kullanıcılar sisteme giriş yapabilir veya yeni hesap oluşturabilir.

Rol Bazlı Erişim: Öğrenci, personel ve yönetici rollerine göre farklı işlevler sunulur.

Kitap Yönetimi: Kitapları listeleme, arama ve kategorilere göre filtreleme.

Kullanıcı Yönetimi: Personel veya yöneticiler öğrenci ve personel kayıtlarını düzenleyebilir, silebilir veya yeni kullanıcılar ekleyebilir.

Dinamik Arama: Kitap ve kullanıcıları özelleştirilmiş arama kutularıyla filtreleme.

Modern Arayüz: Temiz ve sezgisel bir tasarımla kolay kullanım.

Kullanılan Teknolojiler

Programlama Dili: Java

Arayüz: Java Swing

Veri Erişimi: Controller katmanı ile veritabanı simülasyonu

Mimari: MVC (Model-View-Controller) tasarım deseni

Proje Yapısı

1. Ana Sınıf (Main.java)

Uygulamanın başlangıç noktısı.

LoginPage çağrılarak kullanıcının giriş yapması sağlanır.

2. LoginPage.java

Kullanıcıların sisteme giriş yapması veya kayıt ekranına yönlendirilmesini sağlar.

Kullanıcı bilgilerinin doğrulanması ve uygun rolün atanması.

3. RegisterPage.java

Yeni kullanıcıların sisteme kayıt olması için form tabanlı bir arayüz sunar.

4. HomePage.java

Kullanıcının rolüne göre farklı sayfalara yönlendirme yapar.

Öğrenci: StuBooksPage.java

Personel/Yönetici: StudentsPage.java

5. StuBooksPage.java

Öğrenciler için kitap listeleme sayfası.

Kitapları listeleme, kategorilere göre filtreleme ve dinamik arama işlevleri sunar.

6. StudentsPage.java

Kullanıcı yönetim ekranı.

Personel ve yöneticiler öğrenci ve personel listelerini görüntüleyebilir, düzenleyebilir veya silebilir.

7. Controller Sınıfları

UserController.java: Kullanıcıların doğrulanması, eklenmesi, düzenlenmesi ve silinmesi.

BookController.java: Kitapların yönetimi.

8. Model Sınıfları

User.java: Kullanıcı bilgilerini temsil eder.

Book.java: Kitap bilgilerini temsil eder.

Ekran Görüntüleri

Giriş Sayfası: Kullanıcı adı ve şire ile giriş ekranı.
![image](https://github.com/user-attachments/assets/00da2804-c852-4c5b-9aa2-40b1daaa0ebf)
![image](https://github.com/user-attachments/assets/6d4e3dcb-d104-4453-a83a-ae4f7039650b)

Ana Sayfa: Kullanıcı rolüne göre dinamik olarak yönlendirme yapılan ekran.
![image](https://github.com/user-attachments/assets/efe02453-2e0d-4973-b721-9b2c84995636)
![image](https://github.com/user-attachments/assets/51f3a025-e4b0-4a00-89e6-7dc84553fd4c)
![image](https://github.com/user-attachments/assets/dfb83fac-f823-4eb1-a51a-985280fe8277)

Kitap Listeleme: Kitapları listeleme ve arama yapma ekranı.
![image](https://github.com/user-attachments/assets/7ef5d953-f473-475b-ac0c-f845242c0863)

Kullanıcı Yönetimi: Kullanıcıları düzenleme, silme ve ekleme ekranı.
![image](https://github.com/user-attachments/assets/eab4117a-1a88-48d2-9f66-845b64d77007)
