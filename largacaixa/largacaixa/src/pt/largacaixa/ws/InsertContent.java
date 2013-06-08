package pt.largacaixa.ws; //TODO: change this to anotapakd

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.sql.rowset.serial.SerialBlob;

public class InsertContent extends Operation {

	private byte[] data;
	private int preco;
	
    public InsertContent(String caixa, String cid, int preco ,byte[] data) {
    	super(caixa, cid);
    	this.preco = preco;
    	this.data = data;
    }
    
    public InsertContent(String caixa, String cid, byte[] data) {
    	super(caixa, cid);
    	this.preco = 0;
    	this.data = data;
    }
    
    public void execute(LargaCaixaImpl lc) throws CaixaInexistente, ConteudoInvalido {
        
        try {
        	super.checkBox(lc, this.caixa);
        	super.checkFileName(lc, this.caixa, this.cid);
        	
            System.out.println("Load the JDBC driver");
            Class.forName(dbDriverName);

            System.out.println("Create a connection to the database");
            
//            System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&" + lc.dbUrl_1 +
//            					"&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
            
            connection = DriverManager.getConnection(lc.dbUrl_1, dbUsername_1, dbPassword_1);
            System.out.println("Prepare a statement to insert a record");
          
            String sql = "INSERT INTO CAIXAS (NAME, FILE, PRECO, DATA) VALUES(?,?,?,?)";
       
           //TODO: bichin não mete imagens
            
            System.out.println(sql);
            PreparedStatement pstmt = connection.prepareStatement(sql);

            pstmt.setString(1, this.caixa);
            pstmt.setString(2, this.cid);
            pstmt.setInt(3, preco);
            Blob blob = new SerialBlob(data);
            pstmt.setBlob(4, blob);

            
            System.out.println("Insert the row");
            pstmt.executeUpdate();
            System.out.println("done");

            System.out.println("Close connection");
            connection.close();

        } 
        catch (ClassNotFoundException e) {
            System.err.println("Could not find the database driver " + e);

        } 
        catch (SQLException e) {
            System.err.println("SQL exception " + e);
        }
        
        finally {
            try {
                if (pstmt != null) {
                	pstmt.close();
                }
                if (connection != null) {
                	connection.close();
                }
            }   
            catch (SQLException ex) {
                Logger lgr = Logger.getLogger(InsertContent.class.getName());
                lgr.log(Level.SEVERE, ex.getMessage(), ex);
            }
        }
   }    
}