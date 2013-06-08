package pt.ist.sonet.service;

import pt.ist.sonet.service.*;
import pt.ist.sonet.service.dto.*;
import pt.ist.sonet.domain.*;
import pt.ist.sonet.exception.*;
import jvstm.Atomic;
import pt.ist.fenixframework.pstm.Transaction;

public class CreateNewOrganizationServiceTest extends SonetServiceTestCase {

	private static String EXISTING_ORGANIZATION_NAME = "IST";
	private static String NON_EXISTING_ORGANIZATION_NAME = "IST-LEIC";

	public CreateNewOrganizationServiceTest(String msg) {
		super(msg);
	}

	public CreateNewOrganizationServiceTest() {
		super();
	}

	@Override
	public void setUp() {

		super.setUp();
		super.addOrganization("ist", EXISTING_ORGANIZATION_NAME, "ist@email",
				"Lisboa", "Portugal", "password");
	}

	public void testCreateNewInexistentOrganization() {
		// Arrange

		OrganizationDto orgNonExistingNameDto = new OrganizationDto("leic",
				NON_EXISTING_ORGANIZATION_NAME, "ist@email", "Lisboa",
				"Portugal", "password");
		CreateAgentService createService = new CreateAgentService(
				orgNonExistingNameDto);

		// Act
		try {
			createService.execute();
		} catch (UsernameAlreadyExistsException e) {
			System.out.println("------------[TEST FAIL] FOR UsernameAlreadyExistsException!");
		}

		// Assert
		assertTrue("New Organization Was Added to Sonet",
				getAgentByName(NON_EXISTING_ORGANIZATION_NAME));
		assertEquals("The number of Agents should be incresed by one.", 2,
				super.getNumberOfAgents());
	}

	public void testCreateNewExistentOrganization() {
		// Arrange
		OrganizationDto orgExistingNameDto = new OrganizationDto("leic",
				EXISTING_ORGANIZATION_NAME, "ist@email", "Lisboa", "Portugal",
				"password");
		CreateAgentService createService = new CreateAgentService(
				orgExistingNameDto);
		int nAgentsBefore = super.getNumberOfAgents();

		// Act
		try {
			createService.execute();
		} catch (SonetException e) {
			System.out.println("------------[TEST] UsernameAlreadyExistsException CATCHED! :D");
		}

		// Assert
		assertTrue("New Organization Was Added to Sonet",
				getAgentByName(EXISTING_ORGANIZATION_NAME));
		assertEquals("The number of Agents shouldnt be incresed",
				nAgentsBefore, super.getNumberOfAgents());
	}
}