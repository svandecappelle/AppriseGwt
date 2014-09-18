package com.mizore.gwt.apprise.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasOneWidget;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

public class Popup implements IsWidget, HasOneWidget {

	protected static final String APPRISE_ID = "apprise-popup";
	protected static final String APPRISE_OVERLAY_ID = "apprise-overlay";

	private static final ThemeResource css = AppriseClientBundle.Util.getBundle().theme();
	private boolean isShowing;

	private FlowPanel popupWidget;

	private SimplePanel appriseContent;
	private FlowPanel appriseButtons;

	public Popup() {
		this.appriseContent = new SimplePanel();
		this.appriseButtons = new FlowPanel();
		popupWidget = new FlowPanel();

		SimplePanel appriseInner = new SimplePanel(appriseContent);
		appriseInner.addStyleName(css.appriseInner());
		appriseContent.addStyleName(css.appriseContent());
		appriseButtons.addStyleName(css.appriseButtons());

		popupWidget.add(appriseInner);
		popupWidget.add(appriseButtons);

		Button okButton = new Button("OK");
		this.appriseButtons.add(okButton);

		okButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				hide();
			}
		});
		Window.addResizeHandler(new ResizeHandler() {

			@Override
			public void onResize(ResizeEvent event) {
				if (isShowing) {
					adjustWidth();
				}
			}
		});
	}

	public void show() {
		isShowing = true;
		RootPanel.get(APPRISE_ID).add(this);
		RootPanel.get(APPRISE_OVERLAY_ID).addStyleName(css.show());
		RootPanel.get(APPRISE_ID).addStyleName(css.show());
		adjustWidth();
	}

	public void hide() {
		isShowing = false;
		RootPanel.get(APPRISE_ID).remove(this);
		RootPanel.get(APPRISE_OVERLAY_ID).removeStyleName(css.show());
		RootPanel.get(APPRISE_ID).removeStyleName(css.show());
	}

	public Widget asWidget() {
		return popupWidget;
	}

	// @formatter:off
	private native void adjustWidth() /*-{
		var window_width = $wnd.innerWidth, w = "20%", l = "40%";
		if (window_width <= 800) {
			w = "90%", l = "5%";
		} else if (window_width <= 1400 && window_width > 800) {
			w = "70%", l = "15%";
		} else if (window_width <= 1800 && window_width > 1400) {
			w = "50%", l = "25%";
		} else if (window_width <= 2200 && window_width > 1800) {
			w = "40%", l = "30%";
		}else if (window_width >= 2200 ){
			w = "40%", l = "30%";
		}
		$wnd.document.getElementById("apprise-popup").style.cssText = 'width: '
				+ w + ';left:' + l + ';';
	}-*/;

	// @formatter:on

	@Override
	public void setWidget(IsWidget w) {
		appriseContent.setWidget(w);
	}

	@Override
	public Widget getWidget() {
		return appriseContent.getWidget();
	}

	@Override
	public void setWidget(Widget w) {
		appriseContent.setWidget(w);
	}

}
