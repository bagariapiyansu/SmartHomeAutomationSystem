import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.scene.paint.Color;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;

class RoomControl {
    // Light properties
    private String roomName;
    private boolean isLightOn;
    private String brightness;

    // Light UI components
    private Button lightToggleButton;
    private ComboBox<String> brightnessComboBox;
    private Label lightStateLabel;

    // Fan properties
    private boolean isFanOn;
    private String fanSpeed;

    // Fan UI components
    private Button fanToggleButton;
    private ComboBox<String> fanSpeedComboBox;
    private Label fanStateLabel;

    // Layout containers
    private VBox roomBox;
    private VBox lightBox;
    private VBox fanBox;

    // Constructor
    public RoomControl(String roomName) {
        this.roomName = roomName;
        this.isLightOn = false;
        this.brightness = "None";
        this.isFanOn = false;
        this.fanSpeed = "None";

        // Initialize Light UI components
        lightToggleButton = new Button("Turn Light On");
        brightnessComboBox = new ComboBox<>();
        brightnessComboBox.getItems().addAll("Low", "Medium", "High");
        brightnessComboBox.setDisable(true); // Initially disabled
        lightStateLabel = new Label("Light is Off");

        // Initialize Fan UI components
        fanToggleButton = new Button("Turn Fan On");
        fanSpeedComboBox = new ComboBox<>();
        fanSpeedComboBox.getItems().addAll("Low", "Medium", "High");
        fanSpeedComboBox.setDisable(true); // Initially disabled
        fanStateLabel = new Label("Fan is Off");

        // Set up event handlers for Light controls
        lightToggleButton.setOnAction(e -> toggleLight());
        brightnessComboBox.setOnAction(e -> setBrightness(brightnessComboBox.getValue()));

        // Set up event handlers for Fan controls
        fanToggleButton.setOnAction(e -> toggleFan());
        fanSpeedComboBox.setOnAction(e -> setFanSpeed(fanSpeedComboBox.getValue()));

        // Arrange Light controls vertically
        lightBox = new VBox(10);
        lightBox.getChildren().addAll(lightToggleButton, brightnessComboBox, lightStateLabel);

        // Arrange Fan controls vertically
        fanBox = new VBox(10);
        fanBox.getChildren().addAll(fanToggleButton, fanSpeedComboBox, fanStateLabel);

        // Arrange all components vertically within the roomBox
        roomBox = new VBox(20);
        roomBox.setPadding(new Insets(10));
        roomBox.setStyle("-fx-border-color: black; -fx-border-width: 1px;");
        Label roomLabel = new Label(roomName);
        roomLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 16px;");
        roomBox.getChildren().addAll(roomLabel, lightBox, fanBox);

        // Set fixed size to prevent resizing
        roomBox.setPrefWidth(250);
        roomBox.setPrefHeight(250);
    }

    // Toggles the light on or off and updates the UI accordingly.
    private void toggleLight() {
        isLightOn = !isLightOn;
        if (isLightOn) {
            lightToggleButton.setText("Turn Light Off");
            brightnessComboBox.setDisable(false);
            brightnessComboBox.getSelectionModel().selectFirst(); // Default to Low
            brightness = brightnessComboBox.getValue();
            lightStateLabel.setText("Light is On, Brightness: " + brightness);
            applyButtonStyle(lightToggleButton, "green");
        } else {
            lightToggleButton.setText("Turn Light On");
            brightnessComboBox.setDisable(true);
            brightness = "None"; // Reset brightness when turned off
            lightStateLabel.setText("Light is Off");
            applyButtonStyle(lightToggleButton, "red");
        }
    }

    // Sets the brightness level of the light and updates the UI accordingly.
    private void setBrightness(String level) {
        if (isLightOn) {
            brightness = level;
            lightStateLabel.setText("Light is On, Brightness: " + brightness);
        }
    }

    // Toggles the fan on or off and updates the UI accordingly.
    private void toggleFan() {
        isFanOn = !isFanOn;
        if (isFanOn) {
            fanToggleButton.setText("Turn Fan Off");
            fanSpeedComboBox.setDisable(false);
            fanSpeedComboBox.getSelectionModel().selectFirst(); // Default to Low
            fanSpeed = fanSpeedComboBox.getValue();
            fanStateLabel.setText("Fan is On, Speed: " + fanSpeed);
            applyButtonStyle(fanToggleButton, "green");
        } else {
            fanToggleButton.setText("Turn Fan On");
            fanSpeedComboBox.setDisable(true);
            fanSpeed = "None"; // Reset fan speed when turned off
            fanStateLabel.setText("Fan is Off");
            applyButtonStyle(fanToggleButton, "red");
        }
    }

    private void setFanSpeed(String speed) {
        if (isFanOn) {
            fanSpeed = speed;
            fanStateLabel.setText("Fan is On, Speed: " + fanSpeed);
        }
    }

    private void applyButtonStyle(Button button, String color) {
        String colorCode = "";
        switch (color.toLowerCase()) {
            case "green":
                colorCode = "#00FF00"; // Bright Green
                break;
            case "red":
                colorCode = "#FF0000"; // Bright Red
                break;
            default:
                colorCode = "";
        }

        if (!colorCode.isEmpty()) {
            button.setBackground(
                    new Background(new BackgroundFill(Color.web(colorCode), CornerRadii.EMPTY, Insets.EMPTY)));
            button.setTextFill(Color.BLACK); // Keep text color black
        } else {
            button.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
            button.setTextFill(Color.BLACK);
        }
    }

    public VBox getRoomBox() {
        return roomBox;
    }

    public void resetRoom() {
        // Turn off light if it's on
        if (isLightOn) {
            toggleLight();
        }

        // Turn off fan if it's on
        if (isFanOn) {
            toggleFan();
        }

        // Reset button backgrounds to default
        lightToggleButton
                .setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
        fanToggleButton
                .setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
    }
}

public class SmartHomeAutomationSystem extends Application {
    private RoomControl livingRoom;
    private RoomControl bedroom;
    private RoomControl kitchen;

    public void start(Stage primaryStage) {
        primaryStage.setTitle("Smart Home Automation System");

        // Initialize RoomControl instances for each room
        livingRoom = new RoomControl("Living Room");
        bedroom = new RoomControl("Bedroom");
        kitchen = new RoomControl("Kitchen");

        // Create Master Switch
        Button masterSwitch = new Button("Master Switch - Turn All Off");
        masterSwitch.setOnAction(e -> masterTurnOff());

        // Arrange Master Switch in an HBox
        HBox masterSwitchBox = new HBox();
        masterSwitchBox.setPadding(new Insets(20, 0, 20, 0));
        masterSwitchBox.setSpacing(10);
        masterSwitchBox.setStyle("-fx-alignment: center;");
        masterSwitchBox.getChildren().add(masterSwitch);

        // Arrange rooms horizontally
        HBox roomsBox = new HBox(20);
        roomsBox.setPadding(new Insets(0, 20, 20, 20));
        roomsBox.getChildren().addAll(livingRoom.getRoomBox(), bedroom.getRoomBox(), kitchen.getRoomBox());

        // Combine Master Switch and Rooms into a VBox for overall layout
        VBox mainLayout = new VBox();
        mainLayout.getChildren().addAll(masterSwitchBox, roomsBox);

        // Set up the scene
        Scene scene = new Scene(mainLayout, 800, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void masterTurnOff() {
        livingRoom.resetRoom();
        bedroom.resetRoom();
        kitchen.resetRoom();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
