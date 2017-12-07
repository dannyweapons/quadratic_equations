package login;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;

class LoginBean {

    Statement s;
    ResultSet rs;
    Connection con = null;

    public LoginBean() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/quadratic_equations", "root", "mysqlroot");
            s = con.createStatement();
        } catch (SQLException e) {
            System.err.println("Error SQL al intentar conectar con la base de datos");
            e.printStackTrace();
        } catch (ClassNotFoundException ee) {
            System.err.println("No se pudo cargar la clase " + ee);
        }
    }

    public boolean validar(String user, String pass) {

        try {
            rs = s.executeQuery("SELECT * FROM users WHERE email = '" + user + "' and password = '" + pass + "';");

            while (rs.next()) {
                return true;
            }
            
        } catch (SQLException e) {
            System.err.println("Error SQL al intentar conectar con la base de datos");
            e.printStackTrace();
        }

        return false;
    }

    public String getName(String user, String pass) {
        String name = "";
        try {
            rs = s.executeQuery("SELECT * FROM users WHERE email = '" + user + "' and password = '" + pass + "';");

            while (rs.next()) {
                name = rs.getString("name");
                return name;
            }
        } catch (SQLException e) {
            System.err.println("Error SQL al intentar conectar con la base de datos");
            e.printStackTrace();
        }

        return name;
    }

}
