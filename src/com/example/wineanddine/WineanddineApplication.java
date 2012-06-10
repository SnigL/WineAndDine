package com.example.wineanddine;

import java.sql.*;
import com.vaadin.Application;
import com.vaadin.data.Property;
import com.vaadin.data.util.QueryContainer;
import com.vaadin.ui.*;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.MenuBar.MenuItem;

public class WineanddineApplication extends Application {
	
	protected Window addNewWindow;
	protected Window loginWindow;
	protected Property nameproperty;
	
	@Override
	public void init() {
		/** Set maintheme to Runo */
		setTheme("runo");
		/** Main Window */
		final Window mainWindow = new Window("Beer and Wine");
		/** Initialization of the menubar */
		MenuBar menubar = new MenuBar();
		MenuBar.MenuItem home = menubar.addItem("Home", null, null);
			MenuBar.MenuItem add_new = home.addItem("Add new", new MenuBar.Command() {
				
				public void menuSelected(MenuItem selectedItem) {
					addNewWindow = new Window("Add new");
					addNewWindow.setHeight("400px");
					addNewWindow.setWidth("400px");
			        addNewWindow.setPositionX(200);
			        addNewWindow.setPositionY(100);
			        
			        final TextField alcoholName = new TextField("Name: ");
			        addNewWindow.addComponent(alcoholName);
			        final Select alcoholDepartment = new Select("Department");
			        	alcoholDepartment.addItem("Red Wine");
			        	alcoholDepartment.addItem("White Wine");
			        	alcoholDepartment.addItem("Rose Whine");
			        	alcoholDepartment.addItem("Beer");
			        	alcoholDepartment.addItem("Whiskey");
			        	alcoholDepartment.addItem("Calvados");
			        	alcoholDepartment.addItem("Gin");		 
			        alcoholDepartment.setNullSelectionAllowed(false);
			        addNewWindow.addComponent(alcoholDepartment);
			        final TextField alcoholCountry = new TextField("Country:");
			        addNewWindow.addComponent(alcoholCountry);
			        final Select alcoholRating = new Select("Rating: ");
			        	alcoholRating.addItem("5");
			        	alcoholRating.addItem("4");
			        	alcoholRating.addItem("3");
			        	alcoholRating.addItem("2");
			        	alcoholRating.addItem("1");
			        alcoholRating.setNullSelectionAllowed(false);
			        addNewWindow.addComponent(alcoholRating);
			        Button save = new Button("Save", new Button.ClickListener() {
			        
						public void buttonClick(ClickEvent event) {
							Connection con = null;
							String dbUrl = "jdbc:mysql://localhost:3306/";
							String db = "wineanddine";
							String driver = "com.mysql.jdbc.Driver";
							try {
								Class.forName(driver);
								con = DriverManager.getConnection(dbUrl + db, "root", "");
								try {
									Statement st = con.createStatement();
									int val = st.executeUpdate("INSERT INTO alcohol VALUES('"+alcoholName+"', '"+alcoholCountry+"', '"+alcoholDepartment+"', '"+alcoholRating+"')");
									
								} catch (SQLException s) {
									
								}
							} catch (Exception e) {
								e.printStackTrace();
						}
							mainWindow.removeWindow (addNewWindow);
							mainWindow.showNotification("Saved");
						}				
			        });
			        addNewWindow.addComponent(save);
			        
			        mainWindow.addWindow(addNewWindow);
					
				}
			});
			/** The rest of the menubar*/
		MenuBar.MenuItem bevearages = menubar.addItem("Bevearages", null, null);
			MenuBar.MenuItem bevearages_wine = bevearages.addItem("Wine", null, null);
			MenuBar.MenuItem bevearages_beer = bevearages.addItem("Beer", null, null);
			MenuBar.MenuItem bevearages_whiskey = bevearages.addItem("Whiskey", null, null);
		MenuBar.MenuItem logInMenuItem = menubar.addItem("Log in", new MenuBar.Command() {
			
			public void menuSelected(MenuItem selectedItem) {
				loginWindow = new Window("Log in");
				loginWindow.setHeight("200px");
				loginWindow.setWidth("200px");
		        loginWindow.setPositionX(200);
		        loginWindow.setPositionY(100);
				loginWindow.addComponent(new TextField("Username"));
				loginWindow.addComponent(new TextField("Password"));
				loginWindow.addComponent(new Button("Log in", new Button.ClickListener() {
					
					public void buttonClick(ClickEvent event) {
						mainWindow.removeWindow(loginWindow);
						mainWindow.showNotification("Logging in");
					}
				}));
				mainWindow.addWindow(loginWindow);				
			}
		});
		
		Panel main = new Panel("Beer and Wine");
		mainWindow.addComponent(menubar);
		mainWindow.addComponent(main);	
		/** Main table to show items from database */
		String dbUrlnew = "jdbc:mysql://localhost:3306/";
		String dbnew = "wineanddine";
		String driver = "com.mysql.jdbc.Driver";
		Table tblDataModel = null;
		try {
			try {
				Class.forName(driver);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			Connection conn = DriverManager.getConnection(dbUrlnew + dbnew, "root", "");
			QueryContainer qcSQL = new QueryContainer("SELECT * FROM alcohol", conn);
			tblDataModel = new Table();
			tblDataModel.setContainerDataSource(qcSQL);
			tblDataModel.setVisible(true);
			tblDataModel.setEnabled(true);
			tblDataModel.setSelectable(true);
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		setMainWindow(mainWindow);
		main.addComponent(tblDataModel);
	}
}	