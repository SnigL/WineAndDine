package com.example.wineanddine;

import com.example.wineanddine.ui.mainMenuBar;
import com.example.wineanddine.ui.newPanel;
import com.example.wineanddine.ui.tblDataModel;
import com.vaadin.Application;
import com.vaadin.annotations.AutoGenerated;
import com.vaadin.ui.Window;


/**
 * Build the main menu with some stuff.
 * @author Filip
 */

public class WineanddineApplication extends Application {
	
	private mainMenuBar menubar = new mainMenuBar();
	private newPanel mainPanel = new newPanel();
	private tblDataModel mainTable = new tblDataModel();
	
	@Override
	public void init() {
		setTheme("runo");
		buildMainLayout();
		buildMainMenu();
	}
/**
 * Create top main menubar.
 */
	public void buildMainMenu() {
		getMainWindow().addComponent(menubar);
		/**getMainWindow().addComponent(mainPanel);*/
	}
/**
 * Generates main calendar.
 */
	@AutoGenerated
	public void buildMainLayout() {
		Window mainWindow = new Window("Calendar");
		setMainWindow(mainWindow);
		mainWindow.addComponent(mainPanel);
	}
}
