package com.mizore.gwt.apprise.client;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.dom.client.Style.Position;
import com.google.gwt.dom.client.Style.Unit;
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
	private AppriseElement popup;
	private AppriseElement overlay;

	public Popup(boolean addokButton) {
		this();
		if (addokButton) {
			this.addButton("OK").addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					hide();
				}
			});
		}
	}

	public Popup() {
		String idUnique = DOM.createUniqueId();
		idOverlay = APPRISE_OVERLAY_ID + idUnique;
		idPopup = APPRISE_ID + idUnique;

		this.appriseContent = new SimplePanel();
		this.appriseButtons = new FlowPanel();
		popupWidget = new FlowPanel();
		popupWidget.addStyleName(css.popupwidget());

		SimplePanel appriseInner = new SimplePanel(appriseContent);
		appriseInner.addStyleName(css.appriseInner());
		appriseContent.addStyleName(css.appriseContent());
		appriseButtons.addStyleName(css.appriseButtons());

		popupWidget.add(appriseInner);

		Window.addResizeHandler(new ResizeHandler() {

			@Override
			public void onResize(ResizeEvent event) {
				if (isShowing) {
					adjustWidth(idPopup);
				}
			}
		});
	}

	private void initDom(String overlayId, String popupId) {
		RootPanel appriseRoot = RootPanel.get(APPRISE_ID);
		if (appriseRoot == null) {
			new AppriseRootPanel().insert();
		}

		this.popup = new AppriseElement(popupId);
		this.overlay = new AppriseElement(overlayId);

		RootPanel.get(APPRISE_ID).add(this.overlay);
		RootPanel.get(APPRISE_ID).add(this.popup);

	}

	public void show() {
		isShowing = true;
		Scheduler.get().scheduleEntry(new ScheduledCommand() {

			@Override
			public void execute() {
				initDom(idOverlay, idPopup);

				overlay.addStyleName(css.overlay());
				popup.addStyleName(css.apprise());

				initialShow = false;

				Scheduler.get().scheduleEntry(new ScheduledCommand() {

					@Override
					public void execute() {

						showInitial();
					}
				});
			}
		});
	}

	private void showInitial() {
		Timer t = new Timer() {

			@Override
			public void run() {
				popup.setWidget(Popup.this);
				overlay.addStyleName(css.show());
				popup.addStyleName(css.show());
				adjustWidth(idPopup);
			}
		};
		t.schedule(100);

	}

	public void hide() {
		isShowing = false;
		this.remove();
	}

	public Widget asWidget() {
		return popupWidget;
	}

	public void remove() {
		this.popup.removeFromParent();
		this.overlay.removeFromParent();
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

	public boolean isVisible() {
		return isShowing;
	}

	public Button addButton(String text) {
		if (!appriseButtons.isAttached()) {
			popupWidget.add(appriseButtons);
		}
		Button button = new Button(text);
		this.appriseButtons.add(button);
		return button;
	}

	public void setFooterWidget(IsWidget footerWidget) {

		footerWidget.asWidget().getElement().getStyle().setPosition(Position.ABSOLUTE);
		footerWidget.asWidget().getElement().getStyle().setBottom(0, Unit.PX);
		footerWidget.asWidget().getElement().getStyle().setLeft(0, Unit.PX);
		footerWidget.asWidget().getElement().getStyle().setRight(0, Unit.PX);
		footerWidget.asWidget().getElement().getStyle().setMarginBottom(0, Unit.PX);

		popupWidget.add(footerWidget);
	}

}
