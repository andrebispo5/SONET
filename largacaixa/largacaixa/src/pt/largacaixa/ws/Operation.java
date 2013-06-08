package pt.largacaixa.ws;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.datatype.XMLGregorianCalendar;

public abstract class Operation {

	protected String dbDriverName = "com.mysql.jdbc.Driver"; //TODO: asihdush
	protected String dbUsername_1 = "largacaixauser";
	protected String dbPassword_1 = "largacaixapass";
		
	protected String caixa;
	protected String cid;
	
	protected Connection connection;
	protected PreparedStatement pstmt;
	
	protected GregorianCalendar gc; 
	protected XMLGregorianCalendar xgc;
	
	
    public Operation(String caixa, String cid) {
		this.caixa = caixa;
		this.cid = cid;
	}
    
    public Operation(String caixa) {
		this.caixa = caixa;
	}
    
    public void checkBox(LargaCaixaImpl lc, String caixa) throws CaixaInexistente {
    	
    	try {
    		System.out.println("Load the JDBC driver");
            Class.forName(dbDriverName);

            System.out.println("Create a connection to the database");
            connection = DriverManager.getConnection(lc.dbUrl_1, dbUsername_1, dbPassword_1);
            System.out.println("Prepare a statement to insert a record");
    		
	    	String checkBox = "SELECT NAME FROM CAIXAS_ID WHERE NAME = (?)"; 
	        pstmt = connection.prepareStatement(checkBox);
	        pstmt.setString(1, caixa);
	        ResultSet rs = pstmt.executeQuery();
	    
	        if (!rs.next()) {
            	connection.close();
            	throw new CaixaInexistente(caixa, new CaixaInexistenteType());
	        }
	    	
	 
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
    
    
    public void checkFileName(LargaCaixaImpl lc, String caixa, String cid) throws ConteudoInvalido {
    	
    	try {
    		System.out.println("Load the JDBC driver");
            Class.forName(dbDriverName);

            System.out.println("Create a connection to the database");
            connection = DriverManager.getConnection(lc.dbUrl_1, dbUsername_1, dbPassword_1);
            System.out.println("Prepare a statement to insert a record");
 
	    	String checkBox = "SELECT FILE FROM CAIXAS WHERE NAME = (?) AND FILE= (?)";
	        pstmt = connection.prepareStatement(checkBox);
	        pstmt.setString(1, caixa);
	        pstmt.setString(2, cid);
	      
	        ResultSet rs = pstmt.executeQuery();
       
	        if (rs.next()) {
            	connection.close();
            	throw new ConteudoInvalido(cid, new ConteudoInvalidoType());
	        }
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
    

	public void checkContentAvailability(LargaCaixaImpl lc, String cid, String caixa) throws ConteudoInexistenteNaCaixa{
    	
    	try {
    		System.out.println("Load the JDBC driver");
            Class.forName(dbDriverName);

            System.out.println("Create a connection to the database");
            connection = DriverManager.getConnection(lc.dbUrl_1, dbUsername_1, dbPassword_1);
            System.out.println("Prepare a statement to insert a record");
            
	    	String checkContent = "SELECT FILE FROM CAIXAS WHERE FILE= (?) AND CAIXA = (?)"; 
	        pstmt = connection.prepareStatement(checkContent);
	    	pstmt.setString(1, cid);
	    	pstmt.setString(1, caixa);
	        ResultSet rs = pstmt.executeQuery();
	        
	        if (!rs.next()) {
            	connection.close();
            	throw new ConteudoInexistenteNaCaixa(cid, new ConteudoInexistenteNaCaixaType());
	        }	 
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