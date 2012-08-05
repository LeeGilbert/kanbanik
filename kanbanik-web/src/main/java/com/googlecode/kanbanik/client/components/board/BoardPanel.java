package com.googlecode.kanbanik.client.components.board;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Widget;

public class BoardPanel extends Composite {
	
	interface MyUiBinder extends UiBinder<Widget, BoardPanel> {}
	private static MyUiBinder uiBinder = GWT.create(MyUiBinder.class);
	
	@UiField(provided=true)
	Panel projects;
	
	@UiField
	Label boardName;
	
	public BoardPanel(String name, Panel projects) {
		this.projects = projects;
		initWidget(uiBinder.createAndBindUi(this));
		
		boardName.setText(name);
	}
}
