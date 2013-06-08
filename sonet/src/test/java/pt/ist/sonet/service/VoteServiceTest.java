package pt.ist.sonet.service;

import java.util.List;
import java.util.Set;

import pt.ist.sonet.service.*;
import pt.ist.sonet.service.dto.*;
import pt.ist.sonet.domain.*;
import pt.ist.sonet.exception.*;
import jvstm.Atomic;
import pt.ist.fenixframework.pstm.Transaction;

public class VoteServiceTest extends SonetServiceTestCase {
	
	
	private static String EXISTING_INDIVIDUAL_USERNAME = "Fonfas";
	private static String EXISTING_INDIVIDUAL_USERNAME2 = "Zezinho";
	private static String EXISTING_INDIVIDUAL_USERNAME3 = "Joaozinho";
	
	

	public VoteServiceTest(String msg) {
		super(msg);
	}

	public VoteServiceTest() {
		super();
	}

	@Override
	public void setUp() {

		super.setUp();
		
		super.addIndividual(EXISTING_INDIVIDUAL_USERNAME,"Afonso", "Afonso@email", "Porto", "Portugal", "pass");
		super.addIndividual(EXISTING_INDIVIDUAL_USERNAME2,"Ze","Ze@email","Lisboa","Portugal","passss");
		super.addIndividual(EXISTING_INDIVIDUAL_USERNAME3,"Joao","Joao@email","Porto","Portugal","password");
		
		super.addPub(EXISTING_INDIVIDUAL_USERNAME2, 1000, "publicacao de teste");
		
		
		
	}

	public void testVotePlusExistentPublication() {
		// Arrange

		IndividualDto voterDto = new IndividualDto(EXISTING_INDIVIDUAL_USERNAME,"Afonso", "Afonso@email", "Porto", "Portugal", "pass");
		
		
		NoteDto noteDto=new NoteDto(1000,"publicacao de teste");
		VoteService voteService = new VoteService(voterDto,noteDto,1);

		// Act
		try {
			voteService.execute();
		} catch (AgentNotFoundException e) {
			System.out.println("------------[TEST FAIL] For Agent Not Found Exception!");
		}
		  catch (PublicationNotFoundException e) {
			System.out.println("------------[TEST FAIL] For Publication Not Found Exception!");
		}
		assertEquals("Publication should have 1 positive vote", 1, votePlusCount(1000));
	}
	
	public void testVoteMinusExistentPublication() {
		// Arrange

		IndividualDto voterDto = new IndividualDto(EXISTING_INDIVIDUAL_USERNAME,"Afonso", "Afonso@email", "Porto", "Portugal", "pass");
		
		
		NoteDto noteDto=new NoteDto(1000,"publicacao de teste");
		VoteService voteService = new VoteService(voterDto,noteDto,-1);

		// Act
		try {
			voteService.execute();
		} catch (AgentNotFoundException e) {
			System.out.println("------------[TEST FAIL] For Agent Not Found Exception!");
		}
		  catch (PublicationNotFoundException e) {
			System.out.println("------------[TEST FAIL] For Publication Not Found Exception!");
		}
		assertEquals("Publication should have 1 negative vote", 1, voteMinusCount(1000));
	}
	
	public void testVotePlusInexistentPublication() {
		// Arrange

		IndividualDto voterDto = new IndividualDto(EXISTING_INDIVIDUAL_USERNAME,"Afonso", "Afonso@email", "Porto", "Portugal", "pass");
		
		
		NoteDto noteDto=new NoteDto(1001,"publicacao de teste");
		VoteService voteService = new VoteService(voterDto,noteDto,1);

		// Act
		try {
			voteService.execute();
		} catch (AgentNotFoundException e) {
			System.out.println("------------[TEST FAIL] For Agent Not Found Exception!");
		}
		  catch (PublicationNotFoundException e) {
			System.out.println("------------[TEST PASSED] For Publication Not Found Exception! :D" );
		}
	}
	
	public void testVoteTwicePublication() {
		// Arrange

		IndividualDto voterDto = new IndividualDto(EXISTING_INDIVIDUAL_USERNAME,"Afonso", "Afonso@email", "Porto", "Portugal", "pass");
		
		
		NoteDto noteDto=new NoteDto(1000,"publicacao de teste");
		VoteService voteService = new VoteService(voterDto,noteDto,1);
		VoteService voteService1 = new VoteService(voterDto,noteDto,1);

		// Act
		try {
			voteService.execute();
			voteService1.execute();
		} catch (AgentNotFoundException e) {
			System.out.println("------------[TEST FAIL] For Agent Not Found Exception!");
		}
		  catch (PublicationNotFoundException e) {
			System.out.println("------------[TEST FAIL] For Publication Not Found Exception!" );
		}
		catch (CantVoteTwiceException e) {
			System.out.println("------------[TEST PASSED] For Cant Vote Twice Exception! :D" );
		}
	}
	
	
		
	public void testVoteSelfPublication() {
		// Arrange

		IndividualDto voterDto = new IndividualDto(EXISTING_INDIVIDUAL_USERNAME2,"Ze","Ze@email","Lisboa","Portugal","passss");
		
		
		NoteDto noteDto=new NoteDto(1000,"publicacao de teste");
		VoteService voteService = new VoteService(voterDto,noteDto,1);

		// Act
		try {
			voteService.execute();
		} catch (AgentNotFoundException e) {
			System.out.println("------------[TEST FAIL] For Agent Not Found Exception!");
		}
		  catch (PublicationNotFoundException e) {
			System.out.println("------------[TEST FAIL] For Publication Not Found Exception!" );
		}
		catch (CantVoteSelfException e) {
			System.out.println("------------[TEST PASSED] For Cant Vote Self Exception! :D" );
		}
	}
	
	
	
	public void testVotePolemicPublication() {
		// Arrange

		IndividualDto voterDto = new IndividualDto(EXISTING_INDIVIDUAL_USERNAME2,"Ze","Ze@email","Lisboa","Portugal","passss");
		IndividualDto voter2Dto = new IndividualDto(EXISTING_INDIVIDUAL_USERNAME3,"Joao","Joao@email","Porto","Portugal","password");
		
		super.setPolemicPubLimit(1);
		NoteDto noteDto=new NoteDto(1000,"publicacao de teste");
		VoteService voteService = new VoteService(voterDto,noteDto,1);
		VoteService voteService1 = new VoteService(voter2Dto,noteDto,1);

		// Act
		try {
			voteService.execute();
			voteService1.execute();
		} catch (AgentNotFoundException e) {
			System.out.println("------------[TEST FAIL] For Agent Not Found Exception!");
		}
		  catch (PublicationNotFoundException e) {
			System.out.println("------------[TEST FAIL] For Publication Not Found Exception!" );
		}
		catch (CantVoteSelfException e) {
			System.out.println("------------[TEST FAIL] For Cant Vote Self Exception!" );
		}
		catch (PolemicPublicationException e) {
			System.out.println("------------[TEST PASSED] For Polemic Publication Exception! :D" );
		}
	}

}
	
	