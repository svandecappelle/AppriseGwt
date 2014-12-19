package com.mizore.gwt.apprise.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.TextBox;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Apprise implements EntryPoint {
	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		// FOR testing
		final Button sendButton = new Button("send");
		sendButton.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {
				Popup popup = new Popup();

				FlexTable form = new FlexTable();
				form.setWidget(0, 0, new Label("Namesdljgdflgbdsfdsjgdfblfdsngldbldsnvjldfbnfdvndln kdfjbldf,bjfbndf:vldfivnfkjvbdf"));
				form.setWidget(1, 0, new Label("Firstname"));

				form.setWidget(0, 1, new TextBox());
				form.setWidget(1, 1, new TextBox());

				popup.setWidget(form);
				popup.show();

				Popup popup2 = new Popup();
				popup2.setWidget(new Label("test"));
				popup2.show();
			}
		});
		RootLayoutPanel.get().add(sendButton);
		RootLayoutPanel.get().setWidgetTopHeight(sendButton, 10, Unit.PX, 30, Unit.PX);

	}

}
