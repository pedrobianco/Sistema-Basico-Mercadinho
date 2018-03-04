package mercadinhoos;

import com.mysql.jdbc.Connection;
import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Int;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import mercadinhoos.Mercadinho;

/**
 *
 * @author Pedro
 */
public class MercadinhoDao {
    
    Connection con;
    
    public MercadinhoDao(){
        con = Conexao.getconnection();
}
    
      public void inserir(Mercadinho p) {
        try {
            PreparedStatement stmt = this.con.prepareStatement
            ("INSERT INTO produtos (id, nome, categoria, valor, marca) VALUES (?, ?, ?, ?, ?)");
            stmt.setInt(1, p.getCodigo());
            stmt.setString(2, p.getNome());
            stmt.setString(3, p.getCategoria());
            stmt.setDouble(4, p.getValor());
            stmt.setString(5, p.getMarca());
            stmt.executeUpdate();
           
            JOptionPane.showMessageDialog(null, "Salvo com Sucesso!!");
            
        } catch (SQLException ex) {
            Logger.getLogger(Mercadinho.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
      
    public void atualizar(Mercadinho p){
        
        PreparedStatement stmt = null;
        
        try{
            
            stmt = con.prepareStatement
                 ("UPDATE produtos SET nome = ?, categoria = ?, valor = ?, marca = ? where id = ?");
            stmt.setString(1, p.getNome());
            stmt.setString(2 , p.getCategoria());
            stmt.setDouble(3, p.getValor());
            stmt.setString(4, p.getMarca());
            stmt.setInt(5, p.getCodigo());
            
            stmt.execute();
            
            JOptionPane.showMessageDialog(null, "Produto Atualizado com Sucesso!");
            
        } catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro ao Atualizar o produto !");
        }
        
    }
    
    public void deletar(Mercadinho p){
        
        PreparedStatement stmt = null;
        
        try {
            
            stmt = con.prepareStatement("DELETE FROM produtos where id = ?");
            stmt.setInt(1, p.getCodigo());
            
            stmt.execute();
            
            JOptionPane.showMessageDialog(null, "Deletado com Sucesso!");
            
        } catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro ao Deletar!");
        }
        
    }
    
   // public List<Mercadinho> buscarpornome(String nome)
    
    public void Buscar (Mercadinho p){
        
        PreparedStatement stmt = null;
        ResultSet rss = null;
        
       // List<Mercadinho> mercadinhos = new ArrayList<>();
        
       // Mercadinho p = new Mercadinho();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM produtos WHERE id = ?");
            stmt.setInt(1, p.getCodigo());
            
            int code;
            Double vall;
            String nom,cat,marc;
           
            
           // while (rss.next()){
            
                
                code = rss.getInt("id");
                nom = rss.getString("nome");
                cat = rss.getString("categoria");
                vall = rss.getDouble("valor");
                marc = rss.getString("marca");
                
                JOptionPane.showMessageDialog(null, " Codigo: " + code + "\n " + "Nome: " + nom + "\n " 
                + "Categoria: " + cat + "\n " + "Valor : " + vall + "\n" + "Marca : " + marc );
                
                 rss = stmt.executeQuery();
           // }
            
        } catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro ao Buscar");
        }
        
        
    }
      
      
    
}
