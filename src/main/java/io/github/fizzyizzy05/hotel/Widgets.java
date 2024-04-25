package io.github.fizzyizzy05.hotel;

import javafx.geometry.Insets;
import javafx.scene.layout.HBox;
import javafx.scene.control.Label;

public class Widgets {
    public static HBox serviceWidget(int id, String name, int hourlyRate) {
        // Content
        HBox widget = new HBox();
        Label idLbl = new Label(Integer.toString(id));
        Label nameLbl = new Label(name);
        Label rateLbl = new Label(Integer.toString(hourlyRate));
        // Styling
        widget.setPadding(new Insets(5, 10, 5, 10));
        nameLbl.setPadding(new Insets(0, 5, 0, 5));
        widget.getChildren().addAll(idLbl, nameLbl, rateLbl);
        // Return this custom widget
        return widget;
    }
}
