package pt.largacaixa.ws;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.omg.CORBA.portable.InputStream;

public class GetContent extends Operation {
	
	public GetContent(String caixa, String cid) {
		super(caixa, cid);
	}

	
public byte[] execute(LargaCaixaImpl lc) throws CaixaInexistente, ConteudoInexistenteNaCaixa {

	Blob blob = null;
	byte[] a = null;
        try {
            super.checkBox(lc, this.caixa);
            super.checkContentAvailability(lc, this.cid, this.caixa);
            
            System.out.println("Load the JDBC driver");
            Class.forName(dbDriverName);

            System.out.println("Create a connection to the database");
            connection = DriverManager.getConnection(lc.dbUrl_1, dbUsername_1, dbPassword_1);

            System.out.println("Prepare a statement to insert a record");
            String sql = "SELECT DATA FROM CAIXAS " +
            				"WHERE NAME= (?) AND FILE = (?)";
            
            
            System.out.println(sql);
            PreparedStatement pstmt = connection.prepareStatement(sql);
            
            pstmt.setString(1, this.caixa);
            pstmt.setString(2, this.cid);
           
            System.out.println("Select the row");
            ResultSet resultSet = pstmt.executeQuery();
            resultSet.next();
            System.out.println("done");

            /*NEW SCHOOL WAY*/
            try{         
            	blob = resultSet.getBlob(1);
           		a = blob.getBytes(1, (int) blob.length());
            	return a;
            
            }
            catch(Exception e){}
        } 
        
        catch (ClassNotFoundException e) {
            System.err.println("Could not find the database driver " + e);

        } 
        
        catch (SQLException e) {
            System.err.println("SQL exception " + e);
        }
        
        catch (Exception e){
        	
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
                       
            return a;

        }

   }
        
}