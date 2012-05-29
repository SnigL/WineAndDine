package com.example.wineanddine;

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

	@Override
	public void init() {
		setTheme("runo");
		final Window mainWindow = new Window("Beer and Wine");
		MenuBar menubar = new MenuBar();
		MenuBar.MenuItem home = menubar.addItem("Home", null, null);
			MenuBar.MenuItem add_new = home.addItem("Add new", new MenuBar.Command() {
				
				public void menuSelected(MenuItem selectedItem) {
					mywindow = new Window("Add new");
					mywindow.setHeight("400px");
					mywindow.setWidth("400px");
			        mywindow.setPositionX(200);
			        mywindow.setPositionY(100);
			        
			        TextField name = new TextField("Name: ");
			        mywindow.addComponent(name);
			        Select department = new Select("Department");
			        	department.addItem("Red Wine");
			        	department.addItem("White Wine");
			        	department.addItem("Rose Whine");
			        	department.addItem("Beer");
			        	department.addItem("Whiskey");
			        	department.addItem("Calvados");
			        	department.addItem("Gin");		 
			        department.setNullSelectionAllowed(false);
			        mywindow.addComponent(department);
			        TextField country = new TextField("Country:");
			        mywindow.addComponent(country);
			        Select rating = new Select("Rating: ");
			        	rating.addItem("5");
			        	rating.addItem("4");
			        	rating.addItem("3");
			        	rating.addItem("2");
			        	rating.addItem("1");
			        rating.setNullSelectionAllowed(false);
			        mywindow.addComponent(rating);
			        Button save = new Button("Save", new Button.ClickListener() {
			        
						public void buttonClick(ClickEvent event) {
							mainWindow.removeWindow (mywindow);
							mainWindow.showNotification("Saved");
						}
						
			        });
			        mywindow.addComponent(save);
			        
			        mainWindow.addWindow(mywindow);
					
				}
			});
			
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
		
		final Table table = new Table();
		table.addContainerProperty("Name", String.class, null);
		table.addContainerProperty("Department", String.class, null);
		table.addContainerProperty("Country", String.class, null);
		table.addContainerProperty("Rating", String.class, null);
		table.setSelectable(true);
		table.setImmediate(true);
		
		
		final Label current = new Label("Selected: -");
		table.addListener(new Property.ValueChangeListener() {

		public void valueChange(ValueChangeEvent event) {
				current.setValue("Selected: " + table.getValue());
			}
		});
		
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
		
		/*Accordion accordion = new Accordion();
		accordion.setSizeFull();
		
		Label l1 = new Label("There are no previously saved actions.");
		Label l2 = new Label("There are no saved notes.");
		Label l3 = new Label("There are currently no issues.");
		
		accordion.addTab(l1, "Saved Settings", null);
		accordion.addTab(l2, "Notes", null);
		accordion.addTab(l3, "Issues", null);
		
		Panel AccPanel = new Panel("Tasks");
		AccPanel.setWidth("300px");
		AccPanel.setHeight("300px");
		AccPanel.addComponent(accordion);
		
		AccPanel.getLayout().setSizeFull();
		AccPanel.getLayout().setMargin(false);
		main.addComponent(AccPanel);*/
		
		setMainWindow(mainWindow);
		
	}
}	
	
