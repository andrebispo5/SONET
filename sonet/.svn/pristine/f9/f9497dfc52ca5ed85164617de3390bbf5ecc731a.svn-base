package pt.ist.sonet.presentation.client.view;

import java.util.List;

import pt.ist.sonet.exception.SonetException;
import pt.ist.sonet.presentation.client.SonetGWT;
import pt.ist.sonet.presentation.client.SonetServletAsync;
import pt.ist.sonet.service.dto.AgentDto;
import pt.ist.sonet.service.dto.PublicationDto;
import pt.ist.sonet.service.dto.UrlDto;

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

public class BuyContentPanel extends FlexTable {

	private List<UrlDto> contentList;
	private UrlDto selectedContent;
	private final Label amountLabel = new Label("Amount:");
	private final Label title = new Label("Select a Publication to purchase:");
	private final TextBox amountTextBox = new TextBox();

	private final Button purchaseButton = new Button("Purchase");

	public BuyContentPanel(List<UrlDto> contentList){
		GWT.log("presentation.client.view.DonationPanel::constructor()");
		this.contentList = contentList;	
		this.setWidget(0, 0, title);
		title.setStyleName("showProfile");
		GWT.log("presentation.client.view.DonationPanel::constructor()" + 
				" - done!");
	}

	public void init(List<UrlDto> contentList, final AgentDto visitedAgent, final SonetGWT rootPage, final SonetServletAsync rpcService){
		if(contentList.isEmpty()){
			Label lbl = new Label("Nao tem conteudo para comprar.");
			this.setWidget(0, 0, lbl);
		} else {
			int row = this.getRowCount();
			for(UrlDto pub : contentList){
				GWT.log(pub.getUrl());
					final RadioButton radio = new RadioButton("pubs", pub.getUrl());
					radio.addClickHandler(new ClickHandler(){
						@Override
						public void onClick(ClickEvent event) {
							check(radio);
						}
					});
					this.setWidget(row,0,radio);
					row++;
			}


			purchaseButton.addClickHandler(new ClickHandler(){
				@Override
				public void onClick(ClickEvent event){
					if (selectedContent.getPrice() > 0){						
						rpcService.transferMoney(rootPage.getActiveAgent(), visitedAgent, selectedContent.getPrice(), "Content " + selectedContent.getUrl() + " bought for " + selectedContent.getPrice(), new AsyncCallback<Void>(){
							public void onSuccess(Void response){
								rpcService.buyContent(rootPage.getActiveAgent(), visitedAgent, selectedContent.getUrl(), "Purchase of " + selectedContent.getUrl(), new AsyncCallback<Void>(){
									public void onSuccess(Void response){
										Window.alert("Content purchased successfully");
									}
									public void onFailure(Throwable caught){
										GWT.log("presentation.client.Sonet::onModuleLoad()::rpcService.createContact");
										GWT.log("-- Throwable: '" + caught.getClass().getName() + "'");
										SonetException ex = (SonetException) caught;
										rootPage.showErrorMessage("ERRO!");
									}
								});
							}
							public void onFailure(Throwable caught){
								GWT.log("presentation.client.Sonet::onModuleLoad()::rpcService.createContact");
								GWT.log("-- Throwable: '" + caught.getClass().getName() + "'");
								SonetException ex = (SonetException) caught;
								rootPage.showErrorMessage("ERRO!");
							}
						});
					} else {
						rpcService.buyContent(rootPage.getActiveAgent(), visitedAgent, selectedContent.getUrl(), "Purchase of " + selectedContent.getUrl(), new AsyncCallback<Void>(){
							public void onSuccess(Void response){
								Window.alert("Content purchased successfully");
							}
							public void onFailure(Throwable caught){
								GWT.log("presentation.client.Sonet::onModuleLoad()::rpcService.createContact");
								GWT.log("-- Throwable: '" + caught.getClass().getName() + "'");
								SonetException ex = (SonetException) caught;
								rootPage.showErrorMessage("ERRO!");
							}
						});
					}
				}});

			this.setWidget(row+1,0,amountLabel);
			this.setWidget(row+2,0,amountTextBox);
			this.setWidget(row+3,0,purchaseButton);
		}
	}

	public void check(RadioButton radio){
		String str = radio.getText();
		for(UrlDto pub : contentList){
			if(((UrlDto) pub).getUrl().equals(str)){
				selectedContent = ((UrlDto) pub);
				break;
			}
		}
	}

}
