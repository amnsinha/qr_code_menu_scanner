package com.hotbot.model.customerapplication;

import com.hotbot.model.AbstractDocument;

public class HeaderSettings {
    private String headerName;
    private String brandNameColor;
    private String headerIcon;
    private String headerColor;
    private boolean roundIcon;

    public String getBrandNameColor() {
        return brandNameColor;
    }

    public void setBrandNameColor(String brandNameColor) {
        this.brandNameColor = brandNameColor;
    }

    public String getHeaderName() {
        return headerName;
    }

    public void setHeaderName(String headerName) {
        this.headerName = headerName;
    }

    public String getHeaderIcon() {
        return headerIcon;
    }

    public void setHeaderIcon(String headerIcon) {
        this.headerIcon = headerIcon;
    }

    public String getHeaderColor() {
        return headerColor;
    }

    public void setHeaderColor(String headerColor) {
        this.headerColor = headerColor;
    }

    public boolean isRoundIcon() {
        return roundIcon;
    }

    public void setRoundIcon(boolean roundIcon) {
        this.roundIcon = roundIcon;
    }
}
