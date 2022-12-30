import java.sql.*;

public class JDBC01_Query02 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/sys?serverTimezone=UTC", "root", "314159");
        Statement st= con.createStatement();


        /*=======================================================================
         ORNEK2: Ankara'da yasayan calisanlarin isim ve maaslarini  maas ters
          sirali olarak listeleyiniz.
        ========================================================================*/

        ResultSet veri =st.executeQuery(" SELECT isim,maas FROM calisanlar where sehir='Ankara' order by maas desc");

        while (veri.next()){
            System.out.println(veri.getString("isim")+ " "+ veri.getInt("maas"));
        }
        System.out.println("======================================");
        /*=======================================================================
          ORNEK3: Maasi en yuksek 3 kisinin adini, yasadigi sehri ve maasini
           isim sirali listeyiniz.
        ========================================================================*/
        String sorgu="select isim,sehir,maas from calisanlar order by maas desc limit 3";

        ResultSet veri2=st.executeQuery(sorgu);

        while (veri2.next()){
            System.out.println(veri2.getString(1)+"-"+veri2.getString(2)+
                               "-"+veri2.getInt(3));
        }
        // NOT1 : Sorgulama icin get ile istenirse sütun (field) ismini yazabilecegimiz gibi sutun index
        // (field olusturulma sirasina gore) yazilabilir.

        // NOT2 : Sorgumuzda SELECT'ten sonra sadece belli fieldlari dondurmesini istiyorsak
        // get ile cagirdigimiz field indexleri sorguda belirttigimiz sirayla ifade etmemiz gerekiyor



        con.close();
        st.close();
        veri.close();
        veri2.close();

    }
}
