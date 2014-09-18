package com.mizore.gwt.apprise.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Apprise implements EntryPoint {
	private static final ThemeResource css = AppriseClientBundle.Util.getBundle().theme();

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		// FOR testing 
//		final Button sendButton = new Button("send");
//		sendButton.addClickHandler(new ClickHandler() {
//
//			public void onClick(ClickEvent event) {
//				Popup popup = new Popup();
//
//				FlexTable form = new FlexTable();
//				form.setWidget(0, 0, new Label("Namesdljgdflgbdsfdsjgdfblfdsngldbldsnvjldfbnfdvndln kdfjbldf,bjfbndf:vldfivnfkjvbdf"));
//				form.setWidget(1, 0, new Label("Firstname"));
//
//				form.setWidget(0, 1, new TextBox());
//				form.setWidget(1, 1, new TextBox());
//
//				popup.setWidget(form);
//				popup.show();
//			}
//		});
//		RootLayoutPanel.get().add(sendButton);
//		RootLayoutPanel.get().setWidgetTopHeight(sendButton, 10, Unit.PX, 30, Unit.PX);

		initDom(Popup.APPRISE_OVERLAY_ID, Popup.APPRISE_ID);
		RootPanel.get(Popup.APPRISE_OVERLAY_ID).addStyleName(css.overlay());
		RootPanel.get(Popup.APPRISE_ID).addStyleName(css.apprise());
	}

	// @formatter:off
	private native void initDom(String overlayId, String popupId) /*-{
		var appriseOverlayElement = $wnd.document.createElement("div");
		var appriseElement = $wnd.document.createElement("div");

		appriseOverlayElement.id = overlayId;
		appriseElement.id = popupId;

		$wnd.document.body.appendChild(appriseOverlayElement);
		$wnd.document.body.appendChild(appriseElement);

	}-*/;
	// @formatter:on
}
