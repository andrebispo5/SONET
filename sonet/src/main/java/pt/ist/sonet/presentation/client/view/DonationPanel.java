package pt.ist.sonet.presentation.client.view;

import java.util.List;

import pt.ist.sonet.exception.SonetException;
import pt.ist.sonet.presentation.client.*;
import pt.ist.sonet.service.TransferPaymentService;
import pt.ist.sonet.service.dto.AgentDto;
import pt.ist.sonet.service.dto.OrganizationDto;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class DonationPanel extends FlexTable{
	
	//private final VerticalPanel verticalPanel;
	private List<AgentDto> organizationList;
	private AgentDto selectedOrganization;
	private final Label searchLabel = new Label("Search for organizations:");
	private final TextBox searchBox = new TextBox();
	private final Button searchButton = new Button("Search");
	private final Label amountLabel = new Label("Amount:");
	private final Label title = new Label("Select an Organization To Donate:");
	private final TextBox amountTextBox = new TextBox();
	
	private final Button donateButton = new Button("Donate");
	
	public DonationPanel(List<AgentDto> organizations){
		GWT.log("presentation.client.view.DonationPanel::constructor()");
		this.organizationList = organizations;	
		this.setWidget(0, 0, title);
		title.setStyleName("showProfile");
		GWT.log("presentation.client.view.DonationPanel::constructor()" + 
				" - done!");
	}
	
	public void init(List<AgentDto> organizations, final SonetGWT rootPage, final SonetServletAsync rpcService){
		donateButton.setVisible(false);
		int row = this.getRowCount();
		if(organizations.isEmpty()){
			Label lbl = new Label("Nao tem amigos a quem possa doar");
			this.setWidget(row, 0, lbl);
		} else {
			for(AgentDto org : organizations){
				final RadioButton radio = new RadioButton("orgs", org.getName());
				radio.addClickHandler(new ClickHandler(){
	                @Override
	                public void onClick(ClickEvent event) {
	                    check(radio);
	                    donateButton.setVisible(true);
	                }
	            });
				this.setWidget(row,0,radio);
				row++;
			}
			
			
		}
		
		donateButton.addClickHandler(new ClickHandler(){
			@Override
			public void onClick(ClickEvent event){
				try{
					final int amount = Integer.parseInt(amountTextBox.getText());
					if(amount>0){
						rpcService.transferMoney(rootPage.getActiveAgent(), selectedOrganization, amount, "Donation: " + amount, new AsyncCallback<Void>(){
							public void onSuccess(Void response) {
								Window.alert("Donated:" + amount + " to " + selectedOrganization.getName() + " successfully!");
								amountTextBox.setText("");
							}
							public void onFailure(Throwable caught) {
								GWT.log("presentation.client.Sonet::onModuleLoad()::rpcService.Donate");
								GWT.log("-- Throwable: '" + caught.getClass().getName() + "'");
								Window.alert(caught.toString());
								amountTextBox.setText("");
							}
						});
					}else{
						Window.alert("Please enter a positive amount.");
						amountTextBox.setText("");
					}
				}catch(NumberFormatException e){
					Window.alert("Please enter a positive amount of euros.");
					amountTextBox.setText("");
					
				}
			}
		});
		
		searchButton.addClickHandler(new ClickHandler(){
			@Override
			public void onClick(ClickEvent event){
				final int rowIndex = getCellForEvent(event).getRowIndex();
				final String name = searchBox.getText();
				rpcService.searchOrganization(name, new AsyncCallback<AgentDto>(){
					public void onSuccess(AgentDto response){
						for(AgentDto a : organizationList){
							if(a.getUsername().equals(response.getUsername()))
								response = null;
						}
						if(response != null){
							organizationList.add(response);
							final RadioButton radio = new RadioButton("orgs", response.getName());
							radio.addClickHandler(new ClickHandler(){
								@Override
								public void onClick(ClickEvent event) {
									check(radio);
									donateButton.setVisible(true);
								}
							});
							setWidget(rowIndex-2,0,radio);
						} else {
							Window.alert("Organizations is already listed");
						}
					}
					public void onFailure(Throwable caught){
						Window.alert("No users registered with that username");
					}
				});
				searchBox.setText("");
			}
		});
		
		
		row = this.getRowCount();
		this.setWidget(row+2, 0, searchLabel);
		this.setWidget(row+3, 0, searchBox);
		this.setWidget(row+3, 1, searchButton);
		this.setWidget(row+5,0,amountLabel);
		this.setWidget(row+6,0,amountTextBox);
		this.setWidget(row+7,0,donateButton);
	}
	
	public void check(RadioButton radio){
		String str = radio.getText();
		for(AgentDto org : organizationList){
			if(org.getName().equals(str)){
				selectedOrganization = org;
				break;
			}
		}
	}
	
	
}

