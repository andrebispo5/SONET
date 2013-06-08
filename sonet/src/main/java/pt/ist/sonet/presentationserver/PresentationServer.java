package pt.ist.sonet.presentationserver;

import pt.ist.sonet.domain.*;
import pt.ist.fenixframework.Config;
import pt.ist.fenixframework.FenixFramework;
import pt.ist.fenixframework.pstm.Transaction;
import pt.ist.sonet.exception.SonetException;
import pt.ist.sonet.service.*;
import pt.ist.sonet.service.dto.IndividualDto;
import pt.ist.sonet.service.dto.NoteDto;
import pt.ist.sonet.service.dto.OrganizationDto;

public class PresentationServer {
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
		
		Sonet sonet = null;
		Agent maria = null;
		Agent ze=null;
		Agent org1 = null;
		NoteDto noteDto = null;
		OrganizationDto org1Dto = null;
		IndividualDto zeDto = null;
		IndividualDto mariaDto = null;
		
		
		
		System.out.println("     ");
		System.out
				.println("###########################################################");
		System.out
				.println("# Welcome to the Sonet Social Network Presentation Server!#\n");
		System.out
				.println("###########################################################");

		// GET SONET
		try {
			Transaction.begin();
			sonet = FenixFramework.getRoot();
			Transaction.commit();
		} catch (SonetException e) {
			System.out.println(e.toString());
		}
		
		// GETS MARIA
		try {
			Transaction.begin();
			maria = sonet.getAgentByUsername("mariazinha");
			Transaction.commit();
		} catch (SonetException e) {
			System.out.println(e.toString());
		}

		// GETS TECNICO LISBOA
		try {
			Transaction.begin();
			org1 = sonet.getAgentByName("Tecnico Lisboa");
			Transaction.commit();
		} catch (SonetException e) {
			System.out.println(e.toString());
		}

		// GETS ZE NINGUEM
		try {
			Transaction.begin();
			ze = sonet.getAgentByUsername("zeninguem");
			Transaction.commit();
		} catch (SonetException e) {
			System.out.println(e.toString());
		}
		
		// MARIA ADDS IST
		try {
			Transaction.begin();
			mariaDto = (IndividualDto) maria.createDto();
			org1Dto = (OrganizationDto) org1.createDto();
			Transaction.commit();
			AddFriendService mariaRequestIST = new AddFriendService(mariaDto,org1Dto);
			mariaRequestIST.execute();
		} catch (SonetException e) {
			System.out.println(e.toString());
		}


		// ADD COMMENT TO MARIA PUBLICATION BY IST
		try {
			Transaction.begin();
			Publication note = maria.getPublicationById(1);
			noteDto = (NoteDto) note.createDto();
			Transaction.commit();
			AddCommentService commentOrg = new AddCommentService(org1Dto,
					noteDto, "Parabens, Maria");
			commentOrg.execute();
		} catch (SonetException e) {
			System.out.println(e.toString());
		}

		
		// SEND FRIEND REQUEST TO MARIA BY ZE
		try {
			Transaction.begin();
			mariaDto = (IndividualDto) maria.createDto();
			zeDto= (IndividualDto) ze.createDto();
			Transaction.commit();
			AddFriendService zeRequestMaria = new AddFriendService(zeDto,
					mariaDto);
			zeRequestMaria.execute();
		} catch (SonetException e) {
			System.out.println(e.toString());
		}

		// MARIA ACCEPT FRIEND REQUEST
		try {
			AcceptConnectionService a = new AcceptConnectionService(zeDto,
					mariaDto);
			a.execute();
		} catch (SonetException e) {
			System.out.println(e.toString());
		}

		

		// ADD A COMMENT TO MARIAS PUBLICATION BY ZE
		try {
			AddCommentService commentZe = new AddCommentService(zeDto, noteDto,
					"Parabens, Maria");
			commentZe.execute();
		} catch (SonetException e) {
			System.out.println(e.toString());
		}

		
		// LIST ALL AGENTS
		try {
			Transaction.begin();
			sonet.listAllAgents();
			Transaction.commit();
		} catch (SonetException e) {
			System.out.println(e.toString());
		}
		
	}
}
