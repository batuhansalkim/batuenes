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

Ana Sayfa: Kullanıcı rolüne göre dinamik olarak yönlendirme yapılan ekran.

Kitap Listeleme: Kitapları listeleme ve arama yapma ekranı.

Kullanıcı Yönetimi: Kullanıcıları düzenleme, silme ve ekleme ekranı.
