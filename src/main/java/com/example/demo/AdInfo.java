package com.example.demo;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class AdInfo {
    private AdType type;
    private Date shownDate;
    private Boolean isClicked;

    public AdInfo() {
    }

    public AdInfo(AdType type) {
        this.type = type;
    }

    public AdInfo(AdType type, Date shownDate, boolean isClicked) {
        this.type = type;
        this.shownDate = shownDate;
        this.isClicked = isClicked;
    }

    @Override
    public String toString() {
        return "AdInfo{" + type + ", shown at " + shownDate.toString() + ", clicked:" + (isClicked ? "✔" : "❌") + "}";
    }
}
