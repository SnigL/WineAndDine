package com.example.wineanddine;

import java.sql.*;
import java.sql.Connection;

import com.vaadin.Application;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.ui.*;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.MenuBar.MenuItem;

public class WineanddineApplication extends Application {
	
	protected Window mywindow;
	protected Window loginWindow;
	protected Property nameproperty;
	
	private Connection c;

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
					mywindow = new Window("Add new");
					mywindow.setHeight("400px");
					mywindow.setWidth("400px");
			        mywindow.setPositionX(200);
			        mywindow.setPositionY(100);
			        
			        final TextField name = new TextField("Name: ");
			        mywindow.addComponent(name);
			        final Select department = new Select("Department");
			        	department.addItem("Red Wine");
			        	department.addItem("White Wine");
			        	department.addItem("Rose Whine");
			        	department.addItem("Beer");
			        	department.addItem("Whiskey");
			        	department.addItem("Calvados");
			        	department.addItem("Gin");		 
			        department.setNullSelectionAllowed(false);
			        mywindow.addComponent(department);
			        final TextField country = new TextField("Country:");
			        mywindow.addComponent(country);
			        final Select rating = new Select("Rating: ");
			        	rating.addItem("5");
			        	rating.addItem("4");
			        	rating.addItem("3");
			        	rating.addItem("2");
			        	rating.addItem("1");
			        rating.setNullSelectionAllowed(false);
			        mywindow.addComponent(rating);
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
									int val = st.executeUpdate("INSERT INTO alcohol VALUES('"+name+"', '"+country+"', '"+department+"', '"+rating+"')");
									
								} catch (SQLException s) {
									
								}
							} catch (Exception e) {
								e.printStackTrace();
						}
							mainWindow.removeWindow (mywindow);
							mainWindow.showNotification("Saved");
						}
						
						
			        });
			        mywindow.addComponent(save);
			        
			        mainWindow.addWindow(mywindow);
					
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
		/** Main table to show items */
		final Table table = new Table();
		table.addContainerProperty("Name", String.class, null);
		table.addContainerProperty("Department", String.class, null);
		table.addContainerProperty("Country", String.class, null);
		table.addContainerProperty("Rating", String.class, null);
		table.setSelectable(true);
		table.setImmediate(true);
		 		
		/** Display marked item */
		final Label current = new Label("Selected: -");
		table.addListener(new Property.ValueChangeListener() {

		public void valueChange(ValueChangeEvent event) {
				current.setValue("Selected: " + table.getValue());
				
			}
		});
		/** Added refdata */
		table.addItem(new Object[] {
			    "50&50","Red Whine","Italy","4"}, new String("50&50"));
		table.addItem(new Object[] {
			    "A Mano","White Whine","Italy","2"}, new String("A Mano"));
		table.addItem(new Object[] {
			    "1698","Beer","Great Britain","5"}, new String("1698"));
		table.setColumnCollapsingAllowed(true);
		table.setWidth("500px"); 
		
		main.addComponent(table);
		main.addComponent(current);
	
		setMainWindow(mainWindow);			

	}
	
}	
