package com.example.wineanddine.ui;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Select;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Window;

public class mainMenuBar extends MenuBar {

	private Window addNewWindow;
	private Window loginWindow;
	
	public mainMenuBar() {
		MenuBar.MenuItem home = addItem("Home", null, null);
		MenuBar.MenuItem add_new = home.addItem("Add new", new MenuBar.Command() {
			
			public void menuSelected(MenuItem selectedItem) {
				addNewWindow = new Window("Add new");
				addNewWindow.setHeight("400px");
				addNewWindow.setWidth("400px");
		        addNewWindow.setPositionX(200);
		        addNewWindow.setPositionY(100);
		        
		        final TextField alcoholName = new TextField("Name");
		        alcoholName.setNullSettingAllowed(false);
		        addNewWindow.addComponent(alcoholName);
		        final Select alcoholDepartment = new Select("Department");
		        	alcoholDepartment.addItem("Red Wine");
		        	alcoholDepartment.addItem("White Wine");
		        	alcoholDepartment.addItem("Rose Wine");
		        	alcoholDepartment.addItem("Beer");
		        	alcoholDepartment.addItem("Whiskey");
		        	alcoholDepartment.addItem("Calvados");
		        	alcoholDepartment.addItem("Gin");
		        alcoholDepartment.setNullSelectionAllowed(false);
		        addNewWindow.addComponent(alcoholDepartment);
		        final TextField alcoholCountry = new TextField("Country");
		        alcoholCountry.setNullSettingAllowed(false);
		        addNewWindow.addComponent(alcoholCountry);
		        final Select alcoholRating = new Select("Rating");
		        	alcoholRating.addItem("5");
		        	alcoholRating.addItem("4");
		        	alcoholRating.addItem("3");
		        	alcoholRating.addItem("2");
		        	alcoholRating.addItem("1");
		        alcoholRating.setNullSelectionAllowed(false);
		        addNewWindow.addComponent(alcoholRating);
		        final Button save = new Button("Save", new Button.ClickListener() {
					
					public void buttonClick(ClickEvent event) {
						Connection con = null;
						String dbUrl = "jdbc:mysql://localhost:3306";
						String db = "wineanddine";
						String driver = "com.mysql.jdbc.Driver";
						
						try {
							Class.forName(driver);
							con = DriverManager.getConnection(dbUrl + db, "root", "");
							try {
								Statement st = con.createStatement();
								int val = st.executeUpdate("insert into alcohol values('"+alcoholName+"', '"+alcoholCountry+"', '"+alcoholDepartment+"', '"+alcoholRating+"')");
								
								} catch (SQLException s) {
		        					
		        				}
							} catch (Exception e) {
								e.printStackTrace();
							}

						addNewWindow.removeWindow(addNewWindow);
						getWindow().showNotification("Saved");
						}
						
					});
		        addNewWindow.addComponent(save);
		        getWindow().addWindow(addNewWindow);
			}
		});
		MenuBar.MenuItem bevearages = addItem("Bevearages", null, null);
			MenuBar.MenuItem bevearages_wine = bevearages.addItem("Wine", null, null);
			MenuBar.MenuItem bevearages_beer = bevearages.addItem("Beer", null, null);
			MenuBar.MenuItem bevearages_whiskey = bevearages.addItem("Whiskey", null, null);
		MenuBar.MenuItem logInMenuItem = addItem("Log in", new MenuBar.Command() {
			
			public void menuSelected(MenuItem selectedItem) {
				loginWindow = new Window("Log in");
				loginWindow.setHeight("200px");
				loginWindow.setWidth("200px");
				loginWindow.setPositionX(200);
				loginWindow.setPositionX(100);
				loginWindow.addComponent(new TextField("Username"));
				loginWindow.addComponent(new TextField("Password"));
				loginWindow.addComponent(new Button("Log in", new Button.ClickListener() {
					
					public void buttonClick(ClickEvent event) {
						getWindow().removeWindow(loginWindow);
						getWindow().showNotification("Logging in");
					}
				}));
			}
		});
	}
}
