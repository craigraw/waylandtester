package com.craigraw.waylandtester;

import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import org.controlsfx.control.textfield.CustomPasswordField;
import org.controlsfx.glyphfont.FontAwesome;
import org.controlsfx.glyphfont.Glyph;
import org.controlsfx.validation.ValidationResult;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.decoration.StyleClassValidationDecoration;

public class WalletPasswordDialog extends Dialog<String> {
    private final CustomPasswordField password;
    private final CustomPasswordField passwordConfirm;

    public WalletPasswordDialog() {
        this.password = new CustomPasswordField();
        this.passwordConfirm = new CustomPasswordField();

        final DialogPane dialogPane = getDialogPane();
        dialogPane.getButtonTypes().addAll(ButtonType.CANCEL);

        final VBox content = new VBox(10);
        content.setPrefHeight(100);
        content.getChildren().add(password);
        content.getChildren().add(passwordConfirm);

        dialogPane.setContent(content);
    }
}

