package com.mizore.gwt.apprise.client;

public class AppriseRootOverlayPanel {

	protected AppriseRootOverlayPanel() {
	}

	protected void insert() {
		this.insert(Popup.APPRISE_OVERLAY_ID);
	}

	// @formatter:off
	private native void insert(String id) /*-{
		var appriseOverlayElement = $wnd.document.createElement("div");
		appriseOverlayElement.id = id;
		$wnd.document.body.appendChild(appriseOverlayElement);
	}-*/;
	// @formatter:on
}
