package pt.largacaixa.ws;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ListContent extends Operation {

	private List<String> lista = new ArrayList<String>();
	
	public ListContent(String caixa) {
		super(caixa);
		// TODO Auto-generated constructor stub
	}

	public List<String> execute(LargaCaixaImpl lc) throws CaixaInexistente {
		
		 try {
			 super.checkBox(lc, this.caixa);
			 System.out.println("Load the JDBC driver");
			 Class.forName(dbDriverName);
			 
			 System.out.println("Create a connection to the database");
			 connection = DriverManager.getConnection(lc.dbUrl_1, dbUsername_1, dbPassword_1);
			 
	         System.out.println("Prepare a statement to insert a record");
	         String sql = "SELECT FILE FROM CAIXAS WHERE NAME = (?)";
	           
	         System.out.println(sql);
	         PreparedStatement pstmt = connection.prepareStatement(sql);
	           
	         pstmt.setString(1, this.caixa);
	         System.out.println("List the row");
	         ResultSet resultSet = pstmt.executeQuery();
     
	         while (resultSet.next()) {
	        	 String name = resultSet.getString(1);
	            lista.add(name);
	         }
	          
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
	    		 return lista;
	    	 } 
	    	 catch (SQLException ex) {
	    		 Logger lgr = Logger.getLogger(InsertContent.class.getName());
	    		 lgr.log(Level.SEVERE, ex.getMessage(), ex);
	    	 }
	     }
		 return lista;
	}
}