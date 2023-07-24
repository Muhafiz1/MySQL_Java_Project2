package org.example;

import java.sql.*;

public class Main {


    private static final String connectionUrl = "jdbc:mysql://localhost:3306/deneme1";

    private static final String USER_SELECT_STRING = "select * from ogrenci";




    public static void main(String[] args) throws ClassNotFoundException, SQLException {


        // Class.forName("com.mysql.cj.jdbc.Driver");


        try (Connection con = DriverManager.getConnection(connectionUrl, "root", "Hafiz1275")) {


            Statement statement = con.createStatement();


            ResultSet result = statement.executeQuery(USER_SELECT_STRING);

            // **************************   UPDATE *******************************
            String newName = "Update name";
            int ogrenciIdToUpdate = 1;

            // Güncelleme sorgusu
            String updateQuery = "UPDATE ogrenci SET ogrenci_isim = ? WHERE ogrenci_id = ?";

            // PreparedStatement oluşturma
            try (PreparedStatement preparedStatement = con.prepareStatement(updateQuery)) {
                // Parametreleri belirleme
                preparedStatement.setString(1, newName);
                preparedStatement.setInt(2, ogrenciIdToUpdate);

                // Güncelleme işlemini gerçekleştirme
                int affectedRows = preparedStatement.executeUpdate();

                if (affectedRows > 0) {
                    System.out.println("Güncelleme işlemi başarıyla tamamlandı.");
                } else {
                    System.out.println("Güncelleme işlemi başarısız oldu.");
                }
            }

            // **************************** INSERT *************************************
            // Eklenecek yeni veri
            String newName2 = "Yeni Öğrencii";
            String newSurname = "Y.Ö. Surname";
            int id = 68;
            boolean aktiflik_durumu =false;

            // Ekleme sorgusu
            String insertQuery = "INSERT INTO ogrenci (ogrenci_isim, ogrenci_id,ogrenci_soyiism,aktiflik_durumu) VALUES (?,?,?,?)";

            // PreparedStatement oluşturma
            try (PreparedStatement preparedStatement = con.prepareStatement(insertQuery)) {
                // Parametreleri belirleme
                preparedStatement.setString(1, newName2);
                preparedStatement.setInt(2, id);
                preparedStatement.setString(3, newSurname);
                preparedStatement.setBoolean(4, aktiflik_durumu);

                // Ekleme işlemini gerçekleştirme
                int affectedRows = preparedStatement.executeUpdate();

                if (affectedRows > 0) {
                    System.out.println("Ekleme işlemi başarıyla tamamlandı.");
                } else {
                    System.out.println("Ekleme işlemi başarısız oldu.");
                }
            }

            // **************************** INSERT *************************************


            int ogrenciIdToDelete = 2;

            // Silme sorgusu
            String deleteQuery = "DELETE FROM ogrenci WHERE ogrenci_id = ?";

            // PreparedStatement oluşturma
            try (PreparedStatement preparedStatement = con.prepareStatement(deleteQuery)) {
                // Parametreyi belirleme
                preparedStatement.setInt(1, ogrenciIdToDelete);

                // Silme işlemini gerçekleştirme
                int affectedRows = preparedStatement.executeUpdate();

                if (affectedRows > 0) {
                    System.out.println("Silme işlemi başarıyla tamamlandı.");
                } else {
                    System.out.println("Silme işlemi başarısız oldu.");
                }
            }




            while (result.next()) {

                System.out.print(result.getString(1) +  "  " );
                System.out.print(result.getString(2)  + "  "  );
                System.out.print(result.getString(3)  + "  "  );
                System.out.println(result.getString(4));
            }

        } catch (SQLException sqlException) {
            System.out.println(" SQL HATASI");
        }


    }
}