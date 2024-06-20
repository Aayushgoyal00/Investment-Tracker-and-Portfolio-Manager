
package collection;

import java.io.*;
import java.sql.*;
import java.util.Scanner;

public class UserManager implements sql_connection {
    Scanner sc = new Scanner(System.in);

    public boolean registerUser(String username, String password, String name) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("username_password.csv"));
            BufferedWriter writer = new BufferedWriter(new FileWriter("username_password.csv",true));
            String add_username = username + "," + password + "," + name;
            writer.write(add_username+"\n" );
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public String loginUser(String username, String password) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("username_password.csv"));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[0].equals(username) && parts[1].equals(password)) {
                    return parts[2];
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String correct_user(String username) {
        int repeat=1;

            try {
                while(repeat==1){
                    BufferedReader reader = new BufferedReader(new FileReader("username_password.csv"));

                    String line;
                    int x=0;
                    while ((line = reader.readLine()) != null) {
                        String[] parts = line.split(",");
                        if (parts[0].equals(username)) {
                            System.out.println("This username is already taken.");
                            System.out.println("Try a different username:-");
                            username=sc.nextLine();
                            x=1;
                            break;
                        }
                    }
                    if(x==0){repeat=0;}
                    reader.close();
                }
                    return username;


            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }

    }

    public void create_newUserInSQL(String correctUsername, String pass_word, String name) {
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            String sqlInsert = "INSERT INTO users (Username,Password,Name) VALUES (?, ?, ?)";

            PreparedStatement statement = connection.prepareStatement(sqlInsert);
            statement.setString(1, correctUsername);
            statement.setString(2, pass_word);
            statement.setString(3, name);
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new user was inserted successfully!\n");
            }
            statement.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("Connection failed!");
            e.printStackTrace();
        }
    }

}
