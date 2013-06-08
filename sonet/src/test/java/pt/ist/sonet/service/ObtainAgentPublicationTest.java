package pt.ist.sonet.service;

import java.util.List;

import pt.ist.sonet.service.*;
import pt.ist.sonet.service.dto.*;
import pt.ist.sonet.domain.*;
import pt.ist.sonet.exception.*;
import jvstm.Atomic;
import pt.ist.fenixframework.pstm.Transaction;

public class ObtainAgentPublicationTest extends SonetServiceTestCase {
	
	private static String EXISTING_INDIVIDUAL_USERNAME = "Fonfas";
	private static String EXISTING_INDIVIDUAL_USERNAME2 = "Zezinho";
	private static String NON_EXISTING_INDIVIDUAL_USERNAME = "Benny";
	private static String EXISTING_ORGANIZATION_NAME = "IST";
	private static String NON_EXISTING_ORGANIZATION_NAME = "IST-LEIC";
	

	public ObtainAgentPublicationTest(String msg) {
		super(msg);
	}

	public ObtainAgentPublicationTest() {
		super();
	}

	@Override
	public void setUp() {

		super.setUp();
		super.addOrganization("istleic", EXISTING_ORGANIZATION_NAME, "ist@email",
				"Lisboa", "Portugal", "password");
		super.addOrganization("ist", NON_EXISTING_ORGANIZATION_NAME, "ist@email",
				"Lisboa", "Portugal", "password");
		super.addIndividual(EXISTING_INDIVIDUAL_USERNAME,"Afonso", "Afonso@email", "Porto", "Portugal", "pass");
		super.addIndividual(EXISTING_INDIVIDUAL_USERNAME2,"Ze", "Ze@email", "Porto", "Portugal", "pass");
		Agent istleic = returnAgentByName(EXISTING_ORGANIZATION_NAME);
		Agent zezinho = returnAgentByUsername(EXISTING_INDIVIDUAL_USERNAME2);
		super.addPubNote(istleic, 2, "Publicacao Note Teste IST-LEIC");
		super.addPubUrl(zezinho, 3, "Publicacao URL 1 Teste zezinho", 0);
		super.addPubUrl(zezinho, 4, "Publicacao URL 2 Teste zezinho", 0);
		
		
		
	}

	public void testObtainExistentPublicationNoteFromOrganization() {
		// Arrange

		OrganizationDto orgExistingNameDto = new OrganizationDto("istleic",
				EXISTING_ORGANIZATION_NAME, "ist@email", "Lisboa",
				"Portugal", "password");
		ObtainPublicationsService PublicationService = new ObtainPublicationsService(
				orgExistingNameDto, orgExistingNameDto);

		// Act
		try {
			PublicationService.execute();
		} catch (AgentNotFoundException e) {
			System.out.println("------------[TEST FAIL] FOR AgentNotFoundException!");
		}

		// Assert
		assertEquals("List of publications should have 1 element", 1, PublicationService.getPublications().size());
		assertEquals("Publications should have same ID", 2 , PublicationService.getPublications().get(0).getId());
		
	}
	
	public void testObtainInexistentPublicationFromIndividual() {
		// Arrange

		IndividualDto indExistingNameDto = new IndividualDto(EXISTING_INDIVIDUAL_USERNAME,"Afonso", "Afonso@email", "Porto", "Portugal", "pass");
		ObtainPublicationsService PublicationService = new ObtainPublicationsService(indExistingNameDto, indExistingNameDto);

		// Act
		try {
			PublicationService.execute();
		} catch (AgentNotFoundException e) {
			System.out.println("------------[TEST FAIL] FOR AgentNotFoundException!");
		}

		// Assert
		assertEquals("List of publications should be empty", 0, PublicationService.getPublications().size());
		
	}
	
	
	public void testObtainPublicationFromInexistentAgent() {
		// Arrange

		IndividualDto indInexistentNameDto = new IndividualDto(NON_EXISTING_INDIVIDUAL_USERNAME,"Bernardo", "Bernardo@email", "Porto", "Portugal", "pass");
		ObtainPublicationsService PublicationService = new ObtainPublicationsService(indInexistentNameDto, indInexistentNameDto);

		// Act
		try {
			PublicationService.execute();
		} catch (AgentNotFoundException e) {
			System.out.println("------------[TEST] AgentNotFoundException CATCHED! :D");
		}
	}
	
	public void testObtainExistentPublicationNoteFromIndividual() {
		// Arrange

		IndividualDto indExistingNameDto = new IndividualDto(EXISTING_INDIVIDUAL_USERNAME2,"Ze", "Ze@email", "Porto", "Portugal", "pass");
		ObtainPublicationsService PublicationService = new ObtainPublicationsService(
				indExistingNameDto, indExistingNameDto);

		// Act
		try {
			PublicationService.execute();
		} catch (AgentNotFoundException e) {
			System.out.println("------------[TEST FAIL] FOR AgentNotFoundException!");
		}

		// Assert
		assertEquals("List of publications should have 2 elements", 2, PublicationService.getPublications().size());
		assertEquals("Publication 1 should have ID 3", 4 , PublicationService.getPublications().get(0).getId());
		assertEquals("Publication 2 should have ID 4", 3 , PublicationService.getPublications().get(1).getId());
		
	}
}
