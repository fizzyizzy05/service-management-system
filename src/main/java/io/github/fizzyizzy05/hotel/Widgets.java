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
        Label rateLbl = new Label(formatPrice(hourlyRate));
        // Styling
        widget.setPadding(new Insets(5, 10, 5, 10));
        nameLbl.setPadding(new Insets(0, 5, 0, 5));
        widget.getChildren().addAll(idLbl, nameLbl, rateLbl);
        // Return this custom widget
        return widget;
    }

    public static String formatPrice(int rate) {
        String outputRate;
        Double doubleRate = Double.valueOf(rate) / 100;
        if (rate >= 100) {
            outputRate = "£" + String.format("%.2f", doubleRate) + " per hour";
        } else if (rate < 100) {
            outputRate = String.format("%.2f", doubleRate) + "p per hour";
        } else {
            outputRate = "Free";
        }
        return outputRate;
    }
}