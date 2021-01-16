
# Fibabanka Java Bootcamp 3. Hafta Ödev

## Use Multithread to Simulate a Restaurant

Bir restaurantın simülasyonunu yapabilmek için ilk izlenilen yol dört class'a ayırıp bunlara yapılan işler doğrultusunda isimler vermek oldu. İlk önce Main Class'ında Threadlerin çalıştırılabilmesi için MyThread isminde üç thread objesi(customer, waiter, chef) yaratıldı. 

Threadler start metoduyla çalıştırılıp join metoduyla da katılmalarını sağladıktan sonra sırasıyla bir ilişki olması için (yani müşteri gelmeden garsonun çalışmaması, garson siparişi almadan şeflerin çalışmaması için) StateOftheRestaurant classındaki bütün metotlara hem synchronized eklendi, hem de sipariş değişkeni(order ve boolean tipinde order2) kontrol edildi. Bu şu şekilde çalışır;

1-Bütün threadler çalışmak üzere MyThread'e geldiğinde, müşteri öncesi garson çalışmaması için int tipindeki order değişkenini ilk önce 0 verdik, yani sipariş gelmeden garsonlar çalışmamalı. Siparişin de 1'lenmesi için de ilk önce müşterinin restauranta gelmesi gerekir. Yani customerIsComing metodu çalışmalı ki, garson objesi çalışsın. Order değişkeni 1'lendiğinde garsonların çalışması için Order metoduna gider.

2-Order metodunda üç garson olduğu için for döngüsünde 3'e kadar saydırdık. Bu olayın aynısını bir müşteri kuyruğu implementasyonu için de yaptık. Yani her 5 müşteri gelip 5 sn durduktan sonra kuyruktaki diğer 5 kişi geliyor ve bu restaurant kapanmadığı için program durdurulmadığı sürece habire beş kişi kuyruktan gelecektir. 

3-Order metoduna gelen waiter thread'inin müşteri gelmeden çalışmaması için int order'ı kontrol ediyoruz, buradaki order eğer 0 ise diğer threadlerle erişim sağlayıp metotdan çıkmasını sağlıyoruz. Ama sipariş 1 ise garsonlar bunu alıp chef'e iletiyor, bunu da tabii ki notifyAll() fonksiyonuyla yapıyoruz. 

3-Chef'e gelen sipariş de garsondan sipariş almadan önce çalışmaması için boolean order2'yi kontrol ediyoruz. İlk değerini false veriyoruz, yani sipariş olmadığında ve garson bu siparişi almadığında şefler çalışmamalı. Ne zaman Order metodu çalışıp order2'yi true yaparsa o zaman şefler çalışıyor. Sipariş alınıp hazır olduğu vakit de artık sipariş ortadan kalkmış oluyor. O yüzden tekrardan false yapıp o thread'i bekletiyoruz(değeri true olana kadar).

4-Ayrıca customerIsComing metodunda müşteriler menüden istedikleri kadar yemek içecek sipariş etsinler diye menü boyutundan çıkmamak üzere random objesiyle rastgele seçimler yaptılar.
