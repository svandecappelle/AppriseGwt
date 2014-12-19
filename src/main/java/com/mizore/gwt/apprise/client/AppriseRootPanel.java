package com.mizore.gwt.apprise.client;

public final class AppriseRootPanel {

	protected AppriseRootPanel() {
	}

	protected void insert() {
		this.insert(Popup.APPRISE_ID);
	}

	// @formatter:off
	private native void insert(String id) /*-{
		var appriseElement = $wnd.document.createElement("div");
		appriseElement.id = id;
		$wnd.document.body.appendChild(appriseElement);
	}-*/;	
	// formatter:on
}
