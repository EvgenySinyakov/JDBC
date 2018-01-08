import com.mysql.fabric.jdbc.FabricMySQLDriver;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.sql.*;

public class Main {

    public static final String URL = "jdbc:mysql://localhost:3306/users";
    public static final String USERNAME = "root";
    public static final String PASSWORD = "root";


    private static final String question2 = "create";
    private static final String question3 = "get";
    private static final String question4 = "list";
    private static final String question5 = "delete";
    private static final String question6 = "update";
    private static final String question7 = "help";

    public static void main(String[] args) throws IOException, SQLException {

        System.out.println("Hello, for more information input help and press enter");

        Connection connection;
        PreparedStatement st;

        Driver driver = new FabricMySQLDriver();
        DriverManager.registerDriver(driver);
        connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

        do {

            BufferedReader reader1 = new BufferedReader(new InputStreamReader(System.in));
            String question = reader1.readLine();
            String[] keyWords = question.split(" ");
            String kWord1 = keyWords[0];



            if (kWord1.toLowerCase().equals(question2)) {

                String kWord2 = keyWords[1];
                String kWord3 = keyWords[2];
                String kWord4 = keyWords[3];
                try {
                    st = connection.prepareStatement("INSERT INTO teammates VALUES(?, ?, ?  )");
                    st.setInt(1, Integer.valueOf(kWord2));
                    st.setString(2, kWord3);
                    st.setString(3, kWord4);
                    st.execute();

                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }

            else if (kWord1.toLowerCase().equals(question3)) {
                String kWord2 = keyWords[1];




                try {
                    st = connection.prepareStatement("SELECT * FROM teammates WHERE id =  \"" + kWord2 + "\"");
                    ResultSet res = st.executeQuery();
                    System.out.println("All you need to know about person whith ID = " + kWord2 + ":");

                    if (res.next()) {
                        String name = res.getString("name");
                        String group = res.getString("group");
                        System.out.println("His is " + name + " and his group is " + group);
                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                }

            } else if (kWord1.toLowerCase().equals(question4)) {



                try {

                    st = connection.prepareStatement("SELECT * FROM teammates");
                    ResultSet res = st.executeQuery();

                    while (res.next()) {
                        String name = res.getString("name");
                        String group = res.getString("group");
                        System.out.println("His is " + name + " and his group is " + group);
                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            else if (kWord1.toLowerCase().equals(question5)) {
                String kWord2 = keyWords[1];




                try {
                    st = connection.prepareStatement("DELETE FROM teammates WHERE id = \"" + kWord2 + "\"");
                    st.executeUpdate();
                    System.out.println("Person with id = " + kWord2 + " is deleted");


                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }


            else if (kWord1.toLowerCase().equals(question6)) {

                String kWord2 = keyWords[1];
                String kWord3 = keyWords[2];


                try {

                    st = connection.prepareStatement("UPDATE teammates SET name =\"" + kWord3 + "\"   WHERE id = \"" + kWord2 + "\"");
                    st.executeUpdate();


                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }

            else if (kWord1.toLowerCase().equals(question7)) {
                System.out.println("CREATE - inserts person into current table");
                System.out.println("GET - gives you all information about person");
                System.out.println("LIST - gives you information about all people");
                System.out.println("DELETE - deletes person");
                System.out.println("UPDATE - updates information about person ");


            }
            else System.err.println("WRONG COMMAND");





        } while (true) ;
    }
}