package com.example.wineanddine.ui;

import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;

/**
 * @author Filip
 *
 */
public class newPanel extends Panel {
	
	public Panel mainPanel;
/**
 * Generates new Panel with name.
 */
	public void buildMainPanel() {
		mainPanel = new Panel("Beer and Wine");
		mainPanel.addComponent(new Label("Test"));
	}
}
