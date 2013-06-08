package pt.ist.sonet;

import pt.ist.fenixframework.Config;
import pt.ist.fenixframework.FenixFramework;
import jvstm.Atomic;
import pt.ist.fenixframework.pstm.Transaction;
import pt.ist.sonet.domain.*;
import pt.ist.sonet.exception.SonetException;
import pt.ist.sonet.factory.ExternalServiceFactory;
import pt.ist.sonet.factory.LocalExternalServiceFactory;
import pt.ist.sonet.service.*;
import pt.ist.sonet.service.dto.*;

import java.lang.*;

public class SonetSetup {

	public static void main(String[] args) {
		FenixFramework.initialize(new Config() {
			{
				domainModelPath = "src/main/dml/domain.dml";
				dbAlias = "//localhost:3306/sonetdb";
				dbUsername = "sonet";
				dbPassword = "s0n3t";
				rootClass = Sonet.class;

			}
		});
		populateDomain();
	}

	
	public static void populateDomain() {
		System.out.println("---------------------------[START POPULATING]");
		IndividualDto maria = null;
		IndividualDto ze= null;
		IndividualDto alice=null;
		IndividualDto bruno=null;
		IndividualDto carlos=null;
		OrganizationDto org1= null;
		OrganizationDto org2= null;
		ExternalServiceFactory factory = new LocalExternalServiceFactory();
		ExternalServiceConfig.setFactory(factory);
		
		//ADD ORGANIZATION TECNICO LISBOA
		try {
			org1 = new OrganizationDto("ist", "Tecnico Lisboa", "info@ist.utl.pt",
					"Lisboa", "Portugal", "t3cn1co", "PUBLIC");
			
			CreateAgentService createAgent1 = new CreateAgentService(org1);
			createAgent1.execute();
		}
		catch(SonetException e){
				System.out.println(e.toString());
		}
		
		//ADD ORGANIZATION BIBLIOTECA NACIONAL
				try {
					org2 = new OrganizationDto("bn", "Biblioteca Nacional", "info@bn.pt",
							"Lisboa", "Portugal", "l1vr0s", "PUBLIC");
					
					CreateAgentService createAgent2 = new CreateAgentService(org2);
					createAgent2.execute();
				}
				catch(SonetException e){
						System.out.println(e.toString());
				}
		
		//ADD INDIVIDUAL ZE NINGUEM
		try {
			ze = new IndividualDto("zeninguem", "Zé Ninguém",
					"ze.niguem@ist.utl.pt", "NY", "USA", "zeze", "FRIEND");
			CreateAgentService createAgent3 = new CreateAgentService(ze);
			createAgent3.execute();
		}
		catch(SonetException e){
				System.out.println(e.toString());
		} 
		
		//ADD INDIVIDUAL MARIA
		try{
			maria = new IndividualDto("mariazinha", "Maria Silva",
					"mariazinha@ist.utl.pt", "Oeiras", "Portugal", "****","FRIEND");
			CreateAgentService createAgent4 = new CreateAgentService(maria);
			createAgent4.execute();
		}
		catch(SonetException e){
				System.out.println(e.toString());
		}
		
		//ADD INDIVIDUAL ALICE
		try{
			alice = new IndividualDto("alice", "Alice Alves",
					"alice.alves@sonet.pt", "Aveiro", "Portugal", "aaa","FRIEND");
			CreateAgentService createAgent5 = new CreateAgentService(alice);
			createAgent5.execute();
		}
		catch(SonetException e){
				System.out.println(e.toString());
		}
		
		//ADD INDIVIDUAL BRUNO
		try{
			bruno = new IndividualDto("bruno", "Bruno Bento",
					"bruno.bento@sonet.pt", "Beja", "Portugal", "bbb","FRIEND");
			CreateAgentService createAgent6 = new CreateAgentService(bruno);
			createAgent6.execute();
		}
		catch(SonetException e){
				System.out.println(e.toString());
		}
		
		//ADD INDIVIDUAL CARLOS
		try{
			carlos = new IndividualDto("carlos", "Carlos Calado",
					"carlos.calado@sonet.pt", "Coimbra", "Portugal", "ccc","FRIEND");
			CreateAgentService createAgent7 = new CreateAgentService(carlos);
			createAgent7.execute();
		}
		catch(SonetException e){
				System.out.println(e.toString());
		}
		
		//ADD NOTE TO MARIA 
		try {
			AddNoteService addNote1 = new AddNoteService( maria ,"Acabei!","Fim de Curso");
			addNote1.execute();
		}
		catch(SonetException e){
			System.out.println(e.toString());
		}
		
		//ADD URL TO IST
		try{
			AddUrlService addUrl1 = new AddUrlService(org1, "Tecnico", "logotipo", 0);
			addUrl1.execute();
		} catch (SonetException e){
			System.out.println(e.toString());
		}
		
		//ADD ANOTHER URL TO IST
		try{
			AddUrlService addUrl2 = new AddUrlService(org1, "ISTory", "Historia do tecnico", 5);
			addUrl2.execute();
		} catch (SonetException e){
			System.out.println(e.toString());
		}
		
		//ADD URL TO BN
		try{
			AddUrlService addUrl3 = new AddUrlService(org2, "Tolkien", "Hobbit", 10);
			addUrl3.execute();
		} catch (SonetException e){
			System.out.println(e.toString());
		}
				
		//ADD ANOTHER URL TO BN
		try{
			AddUrlService addUrl4 = new AddUrlService(org2, "camoes", "Os Lusiadas", 0);
			addUrl4.execute();
		} catch (SonetException e){
			System.out.println(e.toString());
		}
		
		//ADD NTOHER URL TO BN
		try{
			AddUrlService addUrl5 = new AddUrlService(org2, "Pessoa", "A Mensagem", 0);
			addUrl5.execute();
		} catch (SonetException e){
			System.out.println(e.toString());
		}
		
		System.out.println("---------------------------[END POPULATING]");
	}
}
