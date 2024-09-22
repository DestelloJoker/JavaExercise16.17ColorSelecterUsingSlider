/*Program Name: ColorSelectorUsingSlider.java
 * Authors: Austin P
 * Date last Updated: 9/22/2024
 * Purpose: This program is meant to use javaFX to change the color of a line of text
 * based on the slider value using listeners to help track when the sliders get altered.
 */
package com.example.colorselectorusingsliders;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class HelloApplication extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Create a new text display to display a string
        Text text = new Text("Change one of the sliders to change my color");
        text.setFont(Font.font(30)); // Set font size

        // Create four sliders for Red, Green, Blue, and Opacity
        Slider redSlider = createColorSlider("Red");
        Slider greenSlider = createColorSlider("Green");
        Slider blueSlider = createColorSlider("Blue");
        Slider opacitySlider = createOpacitySlider("Opacity");

        // Set up a listener that updates the color of the text based on slider values from 0 to 255
        ChangeListener<Number> colorChangeListener = (ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
            Color color = Color.rgb(
                    (int) redSlider.getValue(),
                    (int) greenSlider.getValue(),
                    (int) blueSlider.getValue(),
                    opacitySlider.getValue() / 100
            );
            text.setFill(color);
        };

        // Add listeners to the sliders to check if they get altered
        redSlider.valueProperty().addListener(colorChangeListener);
        greenSlider.valueProperty().addListener(colorChangeListener);
        blueSlider.valueProperty().addListener(colorChangeListener);
        opacitySlider.valueProperty().addListener(colorChangeListener);

        // Create a VBox layout and add all the components
        VBox vbox = new VBox(10);
        vbox.getChildren().addAll(
                text,
                new Label("Red"), redSlider,
                new Label("Green"), greenSlider,
                new Label("Blue"), blueSlider,
                new Label("Opacity"), opacitySlider
        );

        // Create a scene and place it in the stage
        Scene scene = new Scene(vbox, 400, 300);
        primaryStage.setTitle("Color Selector Using Sliders");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // The helper method to create color sliders for Red, Green, Blue
    private Slider createColorSlider(String colorName) {
        Slider slider = new Slider(0, 255, 0);
        slider.setOrientation(Orientation.HORIZONTAL);
        slider.setShowTickMarks(true);
        slider.setShowTickLabels(true);
        slider.setMajorTickUnit(50);
        slider.setBlockIncrement(10);
        return slider;
    }

    // The helper method to create an opacity slider
    private Slider createOpacitySlider(String opacityName) {
        Slider slider = new Slider(0, 100, 100);
        slider.setOrientation(Orientation.HORIZONTAL);
        slider.setShowTickMarks(true);
        slider.setShowTickLabels(true);
        slider.setMajorTickUnit(25);
        slider.setBlockIncrement(5);
        return slider;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
