package pt.ist.sonet;

import pt.ist.fenixframework.Config;
import pt.ist.fenixframework.FenixFramework;
import pt.ist.fenixframework.pstm.Transaction;

import pt.ist.pagamigo.*;
import pt.ist.sonet.domain.*;
import pt.ist.sonet.exception.SonetException;

public class SonetApplication {

	public static void main(String[] args) {
		FenixFramework.initialize(new Config() {
			{
				dbAlias = "//localhost:3306/sonetdb";
				dbUsername = "sonet";
				dbPassword = "s0n3t";
				domainModelPath = "src/main/dml/domain.dml";
				rootClass = Sonet.class;
			}
		});
		System.out.println("     ");
		System.out
				.println("##################################################");
		System.out
				.println("#       Welcome to the Sonet Social Network!     #\n");
		System.out
				.println("##################################################");
		boolean committed = false;

		try {
			Transaction.begin();
			Sonet sonet = FenixFramework.getRoot();
			PagAmigo miguxo=new PagAmigoLocal();
			Transaction.commit();
			
			Transaction.begin();
			Individual maria = (Individual) sonet.getAgentByUsername("mariazinha");
			Individual ze = (Individual) sonet.getAgentByUsername("zeninguem");
			Transaction.commit();
			
			Transaction.begin();
			for (Publication a : maria.getPublicationSet()) {
				maria.votePlus(a, sonet);
				ze.votePlus(a, sonet);
			}
			Transaction.commit();
			
			Transaction.begin();
			sonet.listAllAgents();
			Transaction.commit();
			
			Transaction.begin();
			ze.addFriend(maria);
			Transaction.commit();
			
			Transaction.begin();
			maria.acceptRequest(ze.getUsername());
			Transaction.commit();

			Transaction.begin();
			sonet.listAllAgents();
			Transaction.commit();

			Transaction.begin();
			for (Publication a : maria.getPublicationSet()) {
				ze.votePlus(a, sonet);
				System.out.println("PUBLICATION WITH ID: " +a.getId() + " HAS POSITIVE VOTES:"
						+ a.getScorePlus());
			}
			Transaction.commit();
			committed = true;
		} catch(SonetException e){
			System.out.println(e.toString());
		} finally {		
			if (!committed)
				Transaction.abort();
		}
	}
}
