package com.mizore.gwt.apprise.client;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Timer;
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

	private boolean initialShow = true;

	private FlowPanel popupWidget;

	private SimplePanel appriseContent;
	private FlowPanel appriseButtons;

	private String idOverlay;
	private String idPopup;

	public Popup() {
		String idUnique = DOM.createUniqueId();
		idOverlay = APPRISE_OVERLAY_ID + idUnique;
		idPopup = APPRISE_ID + idUnique;

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
					adjustWidth(idPopup);
				}
			}
		});
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

	public void show() {
		if (initialShow) {
			Scheduler.get().scheduleEntry(new ScheduledCommand() {

				@Override
				public void execute() {
					initDom(idOverlay, idPopup);
					RootPanel.get(idOverlay).addStyleName(css.overlay());
					RootPanel.get(idPopup).addStyleName(css.apprise());

					initialShow = false;

					Scheduler.get().scheduleEntry(new ScheduledCommand() {

						@Override
						public void execute() {

							showInitial();
						}
					});
				}
			});

		} else {
			isShowing = true;

			RootPanel.get(this.idOverlay).addStyleName(css.overlay());
			RootPanel.get(this.idPopup).addStyleName(css.apprise());

			RootPanel.get(this.idPopup).add(this);
			RootPanel.get(this.idOverlay).addStyleName(css.show());
			RootPanel.get(this.idPopup).addStyleName(css.show());
			adjustWidth(idPopup);
		}
	}

	private void showInitial() {
		Timer t = new Timer() {

			@Override
			public void run() {
				RootPanel.get(idPopup).add(Popup.this);
				RootPanel.get(idOverlay).addStyleName(css.show());
				RootPanel.get(idPopup).addStyleName(css.show());
				adjustWidth(idPopup);
			}
		};
		t.schedule(100);

	}

	public void hide() {
		isShowing = false;
		RootPanel.get(this.idPopup).remove(this);
		RootPanel.get(this.idPopup).removeStyleName(css.show());
		RootPanel.get(this.idOverlay).removeStyleName(css.show());
	}

	public Widget asWidget() {
		return popupWidget;
	}

	public void remove() {
		RootPanel.get(this.idPopup).removeFromParent();
		RootPanel.get(this.idOverlay).removeFromParent();
	}

	// @formatter:off
	private native void adjustWidth(String idPopup) /*-{
		var window_width = $wnd.innerWidth, w = "20%", l = "40%";
		if (window_width <= 800) {
			w = "90%", l = "5%";
		} else if (window_width <= 1400 && window_width > 800) {
			w = "70%", l = "15%";
		} else if (window_width <= 1800 && window_width > 1400) {
			w = "50%", l = "25%";
		} else if (window_width <= 2200 && window_width > 1800) {
			w = "40%", l = "30%";
		} else if (window_width >= 2200) {
			w = "40%", l = "30%";
		}
		$wnd.document.getElementById(idPopup).style.cssText = 'width: ' + w
				+ ';left:' + l + ';';
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

	public void addStyleName(String style) {
		asWidget().addStyleName(style);
	}

	public void removeStyleName(String style) {
		asWidget().removeStyleName(style);
	}

}
