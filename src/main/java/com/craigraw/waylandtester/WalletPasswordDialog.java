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
    private final ButtonType okButtonType;
    private final PasswordRequirement requirement;
    private final CustomPasswordField password;
    private final CustomPasswordField passwordConfirm;
    private final CheckBox backupExisting;
    private final CheckBox changePassword;
    private final CheckBox deleteBackups;
    private boolean addingPassword;

    public WalletPasswordDialog(String walletName, PasswordRequirement requirement) {
        this(walletName, requirement, false);
    }

    public WalletPasswordDialog(String walletName, PasswordRequirement requirement, boolean suggestChangePassword) {
        this.requirement = requirement;
        this.password = new CustomPasswordField();
        this.passwordConfirm = new CustomPasswordField();
        this.backupExisting = new CheckBox("Backup existing wallet first");
        this.changePassword = new CheckBox("Change password");
        this.deleteBackups = new CheckBox("Delete any backups");

        final DialogPane dialogPane = getDialogPane();
        setTitle("Wallet Password" + (walletName != null ? " - " + walletName : ""));
        dialogPane.setHeaderText(walletName != null ? requirement.description.substring(0, requirement.description.length() - 1) + " for " + walletName + ":" : requirement.description);
        //dialogPane.getStylesheets().add(Main.class.getResource("general.css").toExternalForm());
        dialogPane.getButtonTypes().addAll(ButtonType.CANCEL);
        dialogPane.setPrefWidth(380);
        dialogPane.setPrefHeight(260);

        final VBox content = new VBox(10);
        content.setPrefHeight(100);
        content.getChildren().add(password);
        content.getChildren().add(passwordConfirm);

        dialogPane.setContent(content);

        okButtonType = new javafx.scene.control.ButtonType(requirement.okButtonText, ButtonBar.ButtonData.OK_DONE);
        dialogPane.getButtonTypes().addAll(okButtonType);
//        Button okButton = (Button) dialogPane.lookupButton(okButtonType);
//        okButton.setPrefWidth(130);
//        BooleanBinding isInvalid = Bindings.createBooleanBinding(() -> (requirement == PasswordRequirement.LOAD && password.getText().isEmpty()) || (passwordConfirm.isVisible() && !password.getText().equals(passwordConfirm.getText())), password.textProperty(), passwordConfirm.textProperty());
//        okButton.disableProperty().bind(isInvalid);

//        if(requirement != PasswordRequirement.UPDATE_NEW && requirement != PasswordRequirement.UPDATE_CHANGE) {
//            passwordConfirm.setVisible(false);
//            passwordConfirm.setManaged(false);
//        }

        setResultConverter(dialogButton -> dialogButton == okButtonType ? password.getText() : null);
    }

    public boolean isBackupExisting() {
        return !(addingPassword || isChangePassword()) && backupExisting.isSelected();
    }

    public boolean isChangePassword() {
        return changePassword.isSelected();
    }

    public boolean isDeleteBackups() {
        return (addingPassword || isChangePassword()) && deleteBackups.isSelected();
    }

    public enum PasswordRequirement {
        LOAD("Enter the wallet password:", "Unlock"),
        UPDATE_NEW("Add a password to the wallet?\nLeave empty for no password:", "No Password"),
        UPDATE_EMPTY("This wallet has no password.\nAdd a password to the wallet?\nLeave empty for no password:", "No Password"),
        UPDATE_SET("Re-enter the wallet password:", "Verify Password"),
        UPDATE_CHANGE("Enter the new wallet password.\nLeave empty for no password:", "No Password");

        private final String description;
        private final String okButtonText;

        PasswordRequirement(String description, String okButtonText) {
            this.description = description;
            this.okButtonText = okButtonText;
        }
    }
}

