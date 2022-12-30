import java.sql.*;

public class JDBC01_Query01 {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        // 1-- ilgili Driver i yuklemeliyiz
        //  -- Driver i bulamama durumunda ClassNotFoundException exception filatmasini istiyoruz
        Class.forName("com.mysql.cj.jdbc.Driver");


        // 2-- baglanti icin username ve password girisi yapmaliyiz
        // -- getConnection  hata durumunda SQLException exception ekledik
        Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/sys?serverTimezone=UTC", "root", "314159");

        // 3-- SQL Query'leri icin bir Statement objesi olusturup, javada SQL icin alan olusturacagiz
       Statement st= con.createStatement();

       // 4-- SQL sorgularini yazip calistirabiliriz.
        ResultSet veri =st.executeQuery(" SELECT * FROM calisanlar");

        // 5-- sonuclari gormek icin iteration ile set icindeki elemanlari while ile yazdiracagiz
        /*
        id INT PRIMARY KEY,
		isim VARCHAR(50),
		sehir VARCHAR(50),
		maas INT,
		sirket VARCHAR(20)
         */
        while (veri.next()){
            System.out.println(veri.getInt("id")+ " " + veri.getString("isim")+ " " + veri.getString("sehir")+ " "
                    + veri.getInt("maas")+ " " + veri.getString("sirket"));
        }

        con.close();
        st.close();
        veri.close();



    }
}





