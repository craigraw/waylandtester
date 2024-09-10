package com.craigraw.waylandtester;

import javafx.application.Platform;
import javafx.beans.NamedArg;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

/**
 * A dialog that shows a text input control to the user.
 *
 * @see Dialog
 * @since JavaFX 8u40
 */
public class CustomTextInputDialog extends Dialog<String> {

    /* ************************************************************************
     *
     * Fields
     *
     **************************************************************************/

    private final Label label;
    private final TextField textField;



    /**
     * Creates a new TextInputDialog with the default value entered into the
     * dialog {@link TextField}.
     * @param defaultValue the default value entered into the dialog
     */
    public CustomTextInputDialog(@NamedArg("defaultValue") String defaultValue) {
        final DialogPane dialogPane = getDialogPane();

        // -- textfield
        this.textField = new TextField(defaultValue);

        // -- label
        label = new Label(dialogPane.getContentText());
        label.textProperty().bind(dialogPane.contentTextProperty());

        final VBox content = new VBox(10);
        content.setPrefHeight(100);
        content.getChildren().add(textField);
        content.getChildren().add(label);

        getDialogPane().setContent(content);

        setTitle("Confirm");
        dialogPane.setHeaderText("Header Text");
        dialogPane.getStyleClass().add("text-input-dialog");
        dialogPane.getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        setResultConverter((dialogButton) -> {
            ButtonData data = dialogButton == null ? null : dialogButton.getButtonData();
            return data == ButtonData.OK_DONE ? textField.getText() : null;
        });
    }
}

