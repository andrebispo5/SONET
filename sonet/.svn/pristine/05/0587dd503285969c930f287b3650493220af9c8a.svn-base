package pt.ist.sonet.service;

import java.util.List;
import java.util.Set;

import pt.ist.sonet.service.*;
import pt.ist.sonet.service.dto.*;
import pt.ist.sonet.domain.*;
import pt.ist.sonet.exception.*;
import jvstm.Atomic;
import pt.ist.fenixframework.pstm.Transaction;

public class CreateConnectionTest extends SonetServiceTestCase {
	
	private static String NON_EXISTING_ORGANIZATION_NAME = "IST-LEIC";
	private static String EXISTING_INDIVIDUAL_USERNAME = "Fonfas";
	private static String EXISTING_INDIVIDUAL_USERNAME2 = "Zezinho";
	private static String NON_EXISTING_INDIVIDUAL_USERNAME = "Benny";
	private static String EXISTING_ORGANIZATION_NAME = "IST";
	
	

	public CreateConnectionTest(String msg) {
		super(msg);
	}

	public CreateConnectionTest() {
		super();
	}

	@Override
	public void setUp() {

		super.setUp();
		super.addOrganization("istleic", EXISTING_ORGANIZATION_NAME, "ist@email","Lisboa", "Portugal", "password");
		super.addIndividual(EXISTING_INDIVIDUAL_USERNAME,"Afonso", "Afonso@email", "Porto", "Portugal", "pass");
		super.addIndividual(EXISTING_INDIVIDUAL_USERNAME2,"Ze", "Ze@email", "Porto", "Portugal", "pass");
		
		
		
	}

	public void testCreateConnectionToInexistentAgent() {
		// Arrange

		IndividualDto sourceDto = new IndividualDto(EXISTING_INDIVIDUAL_USERNAME,"Afonso", "Afonso@email", "Porto", "Portugal", "pass");
		IndividualDto targetDto = new IndividualDto(NON_EXISTING_INDIVIDUAL_USERNAME,"Bernardo", "Bernardo@email", "Porto", "Portugal", "pass");
		AddFriendService addFriendService = new AddFriendService(sourceDto,targetDto);

		// Act
		try {
			addFriendService.execute();
		} catch (AgentNotFoundException e) {
			System.out.println("------------[TEST] UsernameAlreadyExistsException CATCHED! :D");
		}
	}
	
	public void testCreateConnectionToIndividualAgent() {
		// Arrange

		IndividualDto sourceDto = new IndividualDto(EXISTING_INDIVIDUAL_USERNAME,"Afonso", "Afonso@email", "Porto", "Portugal", "pass");
		IndividualDto targetDto = new IndividualDto(EXISTING_INDIVIDUAL_USERNAME2,"Ze", "Ze@email", "Porto", "Portugal", "pass");
		AddFriendService addFriendService = new AddFriendService(sourceDto,targetDto);

		// Act
		try {
			addFriendService.execute();
		} catch (AgentNotFoundException e) {
			System.out.println("------------[TEST FAIL] FOR AgentNotFoundException!");
		}
		assertTrue("New Individual Was Added to Requests", hasRequest(EXISTING_INDIVIDUAL_USERNAME2,EXISTING_INDIVIDUAL_USERNAME));
		assertEquals("Agent should have 1 friend request", 1, numberRequests(EXISTING_INDIVIDUAL_USERNAME2));
	}
	
	public void testCreateConnectionToIndividualAndOrgAgent() {
		// Arrange

		IndividualDto sourceDto = new IndividualDto(EXISTING_INDIVIDUAL_USERNAME,"Afonso", "Afonso@email", "Porto", "Portugal", "pass");
		IndividualDto targetDto = new IndividualDto(EXISTING_INDIVIDUAL_USERNAME2,"Ze", "Ze@email", "Porto", "Portugal", "pass");
		OrganizationDto targetDto2 = new OrganizationDto("istleic", EXISTING_ORGANIZATION_NAME, "ist@email","Lisboa", "Portugal", "password");
		AddFriendService addFriendService = new AddFriendService(sourceDto,targetDto);
		AddFriendService addFriendService2 = new AddFriendService(sourceDto,targetDto2);
		

		// Act
		try {
			addFriendService.execute();
			addFriendService2.execute();
		} catch (AgentNotFoundException e) {
			System.out.println("------------[TEST FAIL] FOR AgentNotFoundException!");
		}
		catch (LimitOfFriendsReachedException e){
			System.out.println("------------[TEST] LimitOfFriendsReachedException CATCHED! :D");
		}
		assertEquals("Individual Agent should have 1 friend request", 1, numberRequests(EXISTING_INDIVIDUAL_USERNAME2));
		assertEquals("Organization Agent should have 0 friend request", 0, numberRequestsOrg(EXISTING_ORGANIZATION_NAME));
		
	}
}
	
	