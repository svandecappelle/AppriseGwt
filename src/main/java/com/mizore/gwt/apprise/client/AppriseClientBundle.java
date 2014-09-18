package com.mizore.gwt.apprise.client;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource.NotStrict;

public interface AppriseClientBundle extends ClientBundle {

	final class Util {
		private static AppriseClientBundle bundle = null;

		public static AppriseClientBundle getBundle() {
			if (bundle == null) {
				bundle = GWT.create(AppriseClientBundle.class);

				bundle.theme().ensureInjected();
			}
			return bundle;
		}
	}

	@Source("theme.css")
	@NotStrict
	ThemeResource theme();
}
