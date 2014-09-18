package com.mizore.gwt.apprise.client;

import com.google.gwt.user.client.ui.SimplePanel;

public class AppriseOverlay extends SimplePanel{

	private static final ThemeResource css = AppriseClientBundle.Util.getBundle().theme();
	
	public AppriseOverlay() {
		super.addStyleName(css.overlay());
	}
}
