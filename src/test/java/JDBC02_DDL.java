import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBC02_DDL {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

    /*
 	A) CREATE TABLE, DROP TABLE, ALTER TABLE gibi DDL ifadeleri icin sonuc kümesi (ResultSet)
 	   dondurmeyen metotlar kullanilmalidir. Bunun icin JDBC'de 2 alternatif bulunmaktadir.
​
 	    1) execute() metodu
 	    2) executeUpdate() metodu.
​
 	B) - execute() metodu her tur SQL ifadesiyle kullanibilen genel bir komuttur.
 	   - execute(), Boolean bir deger dondurur. DDL islemlerinde false dondururken,
 	     DML islemlerinde true deger dondurur.
 	   - Ozellikle, hangi tip SQL ifadesinin kullanilmasinin gerektiginin belli olmadigi
 	     durumlarda tercih edilmektedir.
​
 	C) - executeUpdate() metodu ise INSERT, Update gibi DML islemlerinde yaygin kullanilir.
 	   - bu islemlerde islemden etkilenen satir sayisini dondurur.
 	   - Ayrıca, DDL islemlerinde de kullanilabilir ve bu islemlerde 0 dondurur.
 */

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sys?serverTimezone=UTC", "root", "314159");
        Statement st = con.createStatement();

    /*======================================================================
		  ORNEK1: isciler tablosunu siliniz.
 	========================================================================*/

        //String dropTable = "DROP TABLE isciler";

        //if (!st.execute(dropTable))
        //    System.out.println("Isciler tablosu silindi!");
        //else
        //    System.out.println("Isciler tablosu silme islemi basariyla gerceklesmedi!");

    /*=======================================================================
          ORNEK2: isciler adinda bir tablo olusturunuz id int,
          birim VARCHAR(10), maas int
	========================================================================*/

        // String createTable = "CREATE TABLE isciler" +
        //                     "(id INT, " +
        //                     "birim VARCHAR(10), " +
        //                     "maas INT)";

        // if (!st.execute(createTable))
        //     System.out.println("Isciler tablosu create edildi!");
        // else
        //     System.out.println("Isciler tablosu create islemi basariyla gerceklesmedi!");

        /*=======================================================================
		  ORNEK3: isciler tablosuna yeni bir kayit (80, 'ARGE', 4000)
		  ekleyelim
		========================================================================*/

        //String insertVeri = "INSERT INTO isciler VALUES(80, 'ARGE', 4000)";

        //int satirSayisi = st.executeUpdate(insertVeri);

        //System.out.println("Islemden etkilenen satir sayisi : " + satirSayisi);

         /*=======================================================================
	      ORNEK4: isciler tablosuna birden fazla yeni kayıt ekleyelim.

	        INSERT INTO isciler VALUES(70, 'HR', 5000)
            INSERT INTO isciler VALUES(60, 'LAB', 3000)
            INSERT INTO isciler VALUES(50, 'ARGE', 4000)
	     ========================================================================*/

        //  System.out.println("=============== 1. Yontem ==============");

        //  String [] sorgular = {"INSERT INTO isciler VALUES(70, 'HR', 5000)",
        //                        "INSERT INTO isciler VALUES(60, 'LAB', 3000)",
        //                        "INSERT INTO isciler VALUES(50, 'ARGE', 4000)"};
        //  int count
        //  for (String each : sorgular) {
        //      count+= st.executeUpdate(each);
        //  }
        //  System.out.println(count + " satir eklendi!");

        // Ayri ayri sorgular ile veritabanina tekrar tekrar ulasmak islemlerin
        // yavas yapilmasina yol acar. 10000 tane veri kaydi yapildigi dusunuldugunde
        // bu kotu bir yaklasimdir.

      //  System.out.println("=============== 2. Yontem ==============");

        // 2.YONTEM (addBatch ve executeBatch() metotlari ile)
        // ----------------------------------------------------
        // addBatch metodu ile SQL ifadeleri gruplandirilabilir ve executeBatch()
        // metodu ile veritabanina bir kere gonderilebilir.
        // executeBatch() metodu bir int [] dizi dondurur. Bu dizi her bir ifade sonucunda
        // etkilenen satir sayisini gosterir.

     //  String [] sorgular2 = {"INSERT INTO isciler VALUES(40, 'HR', 6000)",
     //          "INSERT INTO isciler VALUES(30, 'LAB', 2000)",
     //          "INSERT INTO isciler VALUES(20, 'ARGE', 5000)"};

     //  for (String each : sorgular2) {
     //      st.addBatch(each);
     //  }

     //  st.executeBatch();

     //  System.out.println("Satirlar eklendi");

        /*=======================================================================
		  ORNEK5: isciler tablosundaki maasi 5000'den az olan iscilerin maasina
		   %10 zam yapiniz
		========================================================================*/
        String update="UPDATE isciler SET maas=maas*1.1 WHERE maas<5000;";
        int satir=st.executeUpdate(update);
        System.out.println(satir+" satir guncelledi");

        con.close();
        st.close();

    }

}



