package com.mizore.gwt.apprise.client;

import com.google.gwt.dom.client.Style.Unit;

public class SizePreference {

    private int leftBorder;
    private int popupWidth;
    private Unit unitLeft;
    private Unit unitWidth;

    public SizePreference(int leftBorder, Unit unitLeft, int popupWidth, Unit unitWidth) {
        this.leftBorder = leftBorder;
        this.popupWidth = popupWidth;
        this.unitLeft = unitLeft;
        this.unitWidth = unitWidth;
    }

    public int getLeftBorder() {
        return leftBorder;
    }

    public int getPopupWidth() {
        return popupWidth;
    }

    public void setLeftBorder(int leftBorder) {
        this.leftBorder = leftBorder;
    }

    public void setPopupWidth(int popupWidth) {
        this.popupWidth = popupWidth;
    }

    public Unit getUnitLeft() {
        return unitLeft;
    }

    public Unit getUnitWidth() {
        return unitWidth;
    }

    public void setUnitLeft(Unit unitLeft) {
        this.unitLeft = unitLeft;
    }

    public void setUnitWidth(Unit unitWidth) {
        this.unitWidth = unitWidth;
    }
}
