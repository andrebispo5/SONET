package pt.largacaixa.ws; //TODO: change this to anotapakd

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DeleteContent extends Operation {

	public DeleteContent(String caixa, String cid) {
		super(caixa, cid);
    }
        
    public void execute(LargaCaixaImpl lc) throws CaixaInexistente, ConteudoInexistenteNaCaixa {
    	//maybe we should make an interface to this or better an abstract class whit template method...
        
    	Connection connection = null;
        
    	try {
        	
        	super.checkBox(lc, this.caixa);
            super.checkContentAvailability(lc, this.cid, this.caixa);
            
            System.out.println("Load the JDBC driver");
            Class.forName(dbDriverName);

            System.out.println("Create a connection to the database");
            connection = DriverManager.getConnection(lc.dbUrl_1, dbUsername_1,
                    dbPassword_1);

            System.out.println("Prepare a statement to delete a record");

            //TODO: this is wrong.. will delete all FILE
            //NOTANYMORE !! !
            String sql = "DELETE FROM CAIXAS WHERE NAME = (?) AND FILE = (?)";
            System.out.println(sql);
            PreparedStatement pstmt = connection.prepareStatement(sql);

            pstmt.setString(1, this.caixa);
            pstmt.setString(2, this.cid);

            System.out.println("Delete the row");
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
                Logger lgr = Logger.getLogger(DeleteContent.class.getName());
                lgr.log(Level.SEVERE, ex.getMessage(), ex);
            }
        }
   }
}