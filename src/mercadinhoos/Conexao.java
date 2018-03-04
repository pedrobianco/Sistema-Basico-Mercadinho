package mercadinhoos;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;

import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author pedro
 */
public final class Conexao {

   private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/produto";
    private static final String USER = "root";
    private static final String PASS = "pedro13";

    Connection cn = null;
       
   public static Connection getconnection() {
        try {
            Class.forName(DRIVER);
            return (Connection) DriverManager.getConnection(URL,USER, PASS);
        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro na Conexao");
        }
       return null;
    }
      
}
