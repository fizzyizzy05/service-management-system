package io.github.fizzyizzy05.hotel;

import javafx.scene.layout.HBox;
import javafx.scene.control.Label;

public class Widgets {
    public static HBox serviceWidget(int id, String name, int hourlyRate) {
        HBox widget = new HBox();
        Label idLbl = new Label(Integer.toString(id));
        Label nameLbl = new Label(name);
        Label rateLbl = new Label(Integer.toString(hourlyRate));
        widget.getChildren().addAll(idLbl, nameLbl, rateLbl);
        return widget;
    }
}
