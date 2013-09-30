package com.example.wineanddine.ui;

import com.vaadin.ui.Table;

public class tblDataModel extends Table {
	
	public Table mainTable;

	public void buildMainPanel() {
		mainTable = new Table();
		mainTable.setVisible(true);
		mainTable.setEnabled(true);
		mainTable.setEditable(false);
		mainTable.setSelectable(false);
	}
}
