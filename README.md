# Wayland Tester

This is a simple JavaFX test app to show a window z order issue on XWayland.
When certain conditions are present, a dialog which has been shown after the primary stage is displayed behind it.
These conditions appear to be:
1. The dialog is owned by the primary stage (with `initOwner`)
2. The dialog is sufficiently simple to render quickly

## Running

There are two boolean command line arguments. 
The first controls whether the dialog is owned, the second whether to use a VBox instead of a more complex GridPane layout in the dialog.
The issue will only be present when both arguments are true, ie:

```shell
./gradlew run --args="true true"
```

