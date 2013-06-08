package pt.bank.ws;

import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.jws.*;
import javax.xml.ws.Service;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;

import java.awt.GradientPaint;
import java.sql.*;
import javax.sql.*;
import javax.transaction.xa.XAResource;
import javax.transaction.xa.Xid;

import pt.bank.ws.handler.BankHandlerResolver;
import pt.bank.ws.xid.XidImpl;


import com.mysql.jdbc.jdbc2.optional.MysqlXADataSource;

/*Anotation for using handlers defined on handler-chain*/
@HandlerChain(file="/handler-chain.xml")

@WebService(endpointInterface="pt.bank.ws.Bank")
public class BankImpl implements Bank{

	/*URL, user and pass used for accessing Database for each Bank on PagAmigo*/
	String url;
	String user;
	String pass;

	/*Connection to the database of each bank*/
	MysqlXADataSource xaDataSource;
	XAResource xares;
	Connection conn;
	
	boolean vouMorrer = true;


	/*xid that represents an transaction occuring on the database of the bank*/
	Xid xid;
	
	/*gtrid of xid on string for using on Treemaps*/
	String gtridString;

	/*structure of bytes to identify an transaction*/
	byte[] gtridG=new byte[4];
	byte[] bqual = new byte[] { 0x0, 0x11, 0x13 }; 

	TreeMap<String,XAResource> openResources = new TreeMap<String,XAResource>();
	TreeMap<String,Connection> openConnections = new TreeMap<String,Connection>();
	TreeMap<String,Xid> openXids = new TreeMap<String,Xid>();

	/*Connection to a database using XA interface*/
	XAConnection xaConn;
	@Resource
	private WebServiceContext webServiceContext;

	public BankImpl(){
	}

	public void CheckOpenConnection(String tid){
		if (openXids.containsKey(tid)){
			this.conn=openConnections.get(tid);
			this.xares=openResources.get(tid);
			this.xid=openXids.get(tid);
		}
		else{
			try{
				xaConn = xaDataSource.getXAConnection();
				conn = xaConn.getConnection();
				xares = xaConn.getXAResource();
				openConnections.put(tid, conn);
				openResources.put(tid, xares);
				this.xid = new com.mysql.jdbc.jdbc2.optional.MysqlXid(gtridG, bqual, 0);
				openXids.put(tid,this.xid);
			}
			catch(SQLException e){
				e.printStackTrace();
			}
		}
			
	}



	/*Starts a new connection to a database if that connection isnt established*/
	public void database(){
		gtridString = XidImpl.getXid();
		gtridG=javax.xml.bind.DatatypeConverter.parseHexBinary(gtridString);
		if (xaDataSource == null){
			MessageContext messageContext = webServiceContext.getMessageContext();
			ServletContext servletContext = (ServletContext) messageContext.get(MessageContext.SERVLET_CONTEXT);
			// retrieve parameter value defined in web.xml
			url = servletContext.getInitParameter("DB.URL");
			user = servletContext.getInitParameter("DB.USER");
			pass = servletContext.getInitParameter("DB.PASS");
			xaDataSource = new MysqlXADataSource();
			xaDataSource.setURL(url);
			xaDataSource.setUser(user);
			xaDataSource.setPassword(pass);
		}
	}

	/*Returns the balance of the account with the id given as parameter*/
	public int getBalance(String id){
		System.out.println("################################# GET BALANCE ###############################");
		database();
		int accountBalance=-1;
		try{
			xaConn = xaDataSource.getXAConnection();
			Connection conn = xaConn.getConnection();
			Statement stmt = conn.createStatement();
			String sql = "select ACCOUNTS.BALANCE from ACCOUNTS WHERE ACCOUNTS.ACCOUNTID=" +id + ";";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				accountBalance = rs.getInt("BALANCE");
			}

		}
		catch(SQLException e){
			System.out.println(e.toString());
		}

		return accountBalance;
	}

	/*Creates a new account with the id and balance given*/
	public void createAccount(String id, int balance){
		database();
		try{
			xaConn = xaDataSource.getXAConnection();
			Connection conn = xaConn.getConnection();
			String sql = "INSERT INTO ACCOUNTS (ACCOUNTID, BALANCE) VALUES (?,?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setInt(2, balance);
			pstmt.executeUpdate();
		}
		catch(SQLException e){
			System.out.println(e.toString());
		}
	} 

	/*Deletes the account with the ID given*/
	public void deleteAccount(String id){
		database();
		try{
			xaConn = xaDataSource.getXAConnection();
			Connection conn = xaConn.getConnection();
			String sql = "DELETE FROM ACCOUNTS WHERE ACCOUNTID=" + id +";";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();
		}
		catch(SQLException e){
			System.out.println(e.toString());
		}
	} 

	/*Method used for local transaction(in the same bank)*/
	public boolean startLocalTransfer(String orig, String dest, int balance){
		System.out.println("################################# START LOCAL TRANSFER ###############################");
		bqual = new byte[] { 0x10, 0x15, 0x13 }; // these byte values are arbitrary	
		database();
		CheckOpenConnection(gtridString);
		/*Variable where we will store the balance of the account before Deposit*/
		int accountBalance=-1;
		try{
			/*Start the Transaction with given xid*/
			xares.start(xid, javax.transaction.xa.XAResource.TMNOFLAGS);

			/*Start DB Operations*/
			Statement stmt = conn.createStatement();
			/*Take from orig account*/
			String sql = "select ACCOUNTS.BALANCE from ACCOUNTS WHERE ACCOUNTS.ACCOUNTID=" + orig + ";";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				accountBalance = rs.getInt("BALANCE");
			}
			int newBalance=accountBalance-balance;
			String sql1 = "UPDATE ACCOUNTS SET BALANCE=" + newBalance + " WHERE ACCOUNTID=" + orig +";";
			PreparedStatement pstmt = conn.prepareStatement(sql1);
			pstmt.executeUpdate();

			/*Put on dest account*/
			String sql2 = "select ACCOUNTS.BALANCE from ACCOUNTS WHERE ACCOUNTS.ACCOUNTID=" + dest + ";";
			ResultSet rs2 = stmt.executeQuery(sql2);
			while (rs2.next()) {
				accountBalance = rs2.getInt("BALANCE");
			}
			int newBalance2=accountBalance+balance;
			String sql3 = "UPDATE ACCOUNTS SET BALANCE=" + newBalance2 + " WHERE ACCOUNTID=" + dest +";";
			PreparedStatement pstmt2 = conn.prepareStatement(sql3);
			pstmt2.executeUpdate();
			xares.end(xid, javax.transaction.xa.XAResource.TMSUCCESS);
		}
		catch (javax.transaction.xa.XAException xae){ 
			System.out.println("distributed Transaction failed");
			System.out.println("XAException error code = " + xae.errorCode);
			System.out.println("XAException message = " + xae.getMessage());
			return false;
		}
		catch(SQLException e){
			System.out.println(e.toString());
			return false;
		}
		return true;

	}

	/*Method called by the Coordinator of 2PC to prepare the bank for an distributed transaction of deposit*/
	@SuppressWarnings("deprecation")
	public int startDeposit(String id, int balance){
		System.out.println("################################# START DEPOSIT ###############################");
		database();
		try{
			bqual=new byte[] { 0x0, 0x11, 0x23 };
			CheckOpenConnection(gtridString);
			/*End Active Transaction*/
			/*Variable where we will store the balance of the account before Deposit*/
			int accountBalance=-1;
			try{
				if(vouMorrer){
					System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%% TRANSACÇAO VAI FALHAR %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
					Thread.sleep(3000);
					vouMorrer = false;
					Thread.currentThread().destroy();
				}
			}
			catch(InterruptedException e){
				e.printStackTrace();
			}

			/*Starts deposit transaction*/
			xares.start(xid, javax.transaction.xa.XAResource.TMNOFLAGS);
			Statement stmt = conn.createStatement();
			String sql = "select ACCOUNTS.BALANCE from ACCOUNTS WHERE ACCOUNTS.ACCOUNTID=" +id + ";";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				accountBalance = rs.getInt("BALANCE");
			}
			int newBalance=accountBalance+balance;
			String sql1 = "UPDATE ACCOUNTS SET BALANCE=" + newBalance + " WHERE ACCOUNTID=" + id+";";
			PreparedStatement pstmt = conn.prepareStatement(sql1);
			pstmt.executeUpdate();
			xares.end(xid, javax.transaction.xa.XAResource.TMSUCCESS);
		}
		catch (javax.transaction.xa.XAException xae){ 
			System.out.println("distributed Transaction failed");
			System.out.println("XAException error code = " + xae.errorCode);
			System.out.println("XAException message = " + xae.getMessage());
			return 0;
		}
		catch(SQLException e){
			System.out.println(e.toString());
			return 0;
		}
		return 1;
	}



	/*Method called by the Coordinator of 2PC to prepare the bank for an distributed transaction of Withdraw*/
	public int startWithdraw(String id, int balance){
		System.out.println("################################# START WITHDRAW ###############################");
		database();
		
		try{
			
			bqual=new byte[] { 0x0, 0x11, 0x01 };
			/*Make connection to DB*/
			CheckOpenConnection(gtridString);
			int accountBalance=-1;
			/*Starts withdraw transaction*/
			xares.start(xid, javax.transaction.xa.XAResource.TMNOFLAGS);
			Statement stmt = conn.createStatement();
			String sql = "select ACCOUNTS.BALANCE from ACCOUNTS WHERE ACCOUNTS.ACCOUNTID=" +id + ";";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				accountBalance = rs.getInt("BALANCE");
			}
			int newBalance=accountBalance-balance;
			String sql1 = "UPDATE ACCOUNTS SET BALANCE=" + newBalance + " WHERE ACCOUNTID=" + id+";";
			PreparedStatement pstmt = conn.prepareStatement(sql1);
			pstmt.executeUpdate();
			xares.end(xid, javax.transaction.xa.XAResource.TMSUCCESS);
		}
		catch (javax.transaction.xa.XAException xae){ 
			System.out.println("distributed Transaction failed");
			System.out.println("XAException error code = " + xae.errorCode);
			System.out.println("XAException message = " + xae.getMessage());
			return 0;
		}
		catch(SQLException e){
			System.out.println(e.toString());
			return 0;
		}
		return 1;
	}

	//Method called by 2PC Coordinator to confirm that a Withdraw or Deposit transaction can be commited
	public boolean endTransaction(boolean canCommit){
		System.out.println("################################# CONFIRM TRANSACTION ###############################");
		database();
		try{
				/*Make connection to DB*/
				CheckOpenConnection(gtridString);

				int rc = xares.prepare(xid);

				if(!canCommit)
					rc = javax.transaction.xa.XAResource.TMFAIL;

				if(rc == javax.transaction.xa.XAResource.XA_OK){
					xares.commit(xid, false);
				}
				else{
					xares.rollback(xid);
				}
				openResources.remove(gtridString);
				openConnections.remove(gtridString);
				openXids.remove(gtridString);
				return true;
		}
		catch (javax.transaction.xa.XAException xae){ 
			System.out.println("distributed Transaction failed");
			System.out.println("XAException error code = " + xae.errorCode);
			System.out.println("XAException message = " + xae.getMessage());
			return false;
		}
	}

	public void abortTransaction(){
		try{
			xares.end(xid, javax.transaction.xa.XAResource.TMSUCCESS);
			xares.prepare(xid);
			xares.rollback(xid);
		}catch (javax.transaction.xa.XAException xae){ 
			System.out.println("distributed Transaction failed");
			System.out.println("XAException error code = " + xae.errorCode);
			System.out.println("XAException message = " + xae.getMessage());
		}
	}
	
	
}

