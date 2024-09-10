package com.craigraw.waylandtester;

import javafx.application.Platform;
import javafx.beans.NamedArg;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;

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
    private final String defaultValue;



    /* ************************************************************************
     *
     * Constructors
     *
     **************************************************************************/

    /**
     * Creates a new TextInputDialog without a default value entered into the
     * dialog {@link TextField}.
     */
    public CustomTextInputDialog() {
        this("");
    }

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

        this.defaultValue = defaultValue;

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.add(label, 0, 0);
        grid.add(textField, 1, 0);
        getDialogPane().setContent(grid);

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

