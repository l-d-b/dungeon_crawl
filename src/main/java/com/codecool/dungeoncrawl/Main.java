package com.codecool.dungeoncrawl;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.MapLoader;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.monsters.Monster;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.scene.text.Font;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class Main extends Application {
    String gameOver = "/gameover.txt";
    String map1 = "/map.txt";
    String map2 = "/map_2.txt";
    String map3 = "/map_3.txt";
    GameMap map = MapLoader.loadMap(map1, 100, 5);
    Player player = map.getPlayer();
    Canvas canvas = new Canvas(
            map.getWidth() * Tiles.TILE_WIDTH,
            map.getHeight() * Tiles.TILE_WIDTH);
    GraphicsContext context = canvas.getGraphicsContext2D();

    private int mapLevelCounter;
    int maxPower = 20;
    int currentPlayerHealth = player.getHealth();
    int currentPlayerPower = player.getAttack();
    ArrayList<CellType> playerInvetory = player.getInventory();

    Label healthLabel = new Label();
    Label currentHealthLabel = new Label();
    Label currentPowerLabel = new Label();
    Label inventoryLabel = new Label();
    Label inventory = new Label();
    Label powerLabel = new Label();


    Rectangle healthbar = new Rectangle();
    Rectangle powerbar = new Rectangle();
    Button pickUpButton;


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        GridPane ui = new GridPane();
        ui.setMinWidth(300);
        ui.setVgap(1);
        ui.setPadding(new Insets(50));

        Rectangle background = new Rectangle(100, 100, 200, 20);

        ui.add(background, 0, 11);
        setHealthbar(ui);
        setPowerbar(ui);

        background.setFill(Color.GREY);
        mapLevelCounter = 1;
        setPickUpButton(ui);

        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(canvas);
        borderPane.setRight(ui);

        canvas.setWidth(1600);
        canvas.setHeight(1000);
        setInventoryLabel(ui);

        Scene scene = new Scene(borderPane);
        primaryStage.setScene(scene);
        refresh();
        scene.setOnKeyPressed(this::onKeyPressed);
        primaryStage.setTitle("Dungeon Crawl");
        primaryStage.show();
    }

    private void setHealthbar(GridPane ui) {
        String playerHealth = String.valueOf(player.getHealth());
        healthLabel.setText("Health: ");
        healthLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        healthLabel.setTextFill(Color.BROWN);
        currentHealthLabel.setText(playerHealth);
        ui.add(healthLabel, 0, 0);
        ui.add(currentHealthLabel, 0, 1);
        ui.setHalignment(healthLabel, HPos.CENTER);

        healthLabel.setPadding(new Insets(0, 55, 0, 55));

        healthbar = new Rectangle(100, 100, 200, 20);
        ui.add(healthbar, 0, 11);
        healthbar.setFill(Color.GREEN);
    }

    private void setPowerbar(GridPane ui) {
        String playerPower = String.valueOf(player.getAttack());
        powerLabel.setText("Power: ");
        powerLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        powerLabel.setTextFill(Color.BROWN);
        currentPowerLabel.setText(playerPower);
        ui.add(powerLabel, 0, 12);
        ui.add(currentPowerLabel, 0, 14);
        ui.setHalignment(powerLabel, HPos.CENTER);

        powerbar = new Rectangle(100, 100, 200, 20);
        Rectangle powBackground = new Rectangle(100, 100, 200, 20);

        ui.add(powBackground, 0, 15);
        ui.add(powerbar, 0, 15);
        powBackground.setFill(Color.GREY);

        powerbar.setFill(Color.RED);

    }

    private void setPickUpButton(GridPane ui) {
        pickUpButton = new Button("Pick up");
        pickUpButton.setVisible(false);

        pickUpButton.addEventFilter(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            pickUp();
        });

        pickUpButton.addEventFilter(KeyEvent.KEY_PRESSED, keyEvent -> {
            if (keyEvent.getCode() == KeyCode.ENTER) {
                pickUp();
            }
        });
        ui.add(pickUpButton, 0, 17);
        ui.setHalignment(pickUpButton, HPos.CENTER);

    }

    private void setInventoryLabel(GridPane ui) {
        ui.add(inventoryLabel, 0, 19);
        ui.add(inventory, 0, 21);
        inventoryLabel.setText("Inventory:");
        inventoryLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 22));
        inventoryLabel.setTextFill(Color.BROWN);
        ui.setHalignment(inventoryLabel, HPos.CENTER);
        ui.setHalignment(inventory, HPos.CENTER);
        inventory.setFont(Font.font("Verdana", 16));
    }

    public Cell playerCellCheck(int x, int y) {
        return player.cellCheck(x, y);
    }

    private void setPickUpButtonVisibleTrue() {
        pickUpButton.setVisible(true);
    }

    private void setPickUpButtonVisibleFalse() {
        pickUpButton.setVisible(false);
    }

    private Monster getCurrentMonster(int x, int y) {
        return playerCellCheck(x, y).getMonster();
    }

    private int getCurrentMonsterHealth(int x, int y) {
        return getCurrentMonster(x, y).getHealth();
    }

    private void roundByKeyPressed(int x, int y) {
            if (!playerCellCheck(x, y).isWall() && !playerCellCheck(x, y).isMonster()) {
                if (playerCellCheck(x, y).isItem()) {
                    setPickUpButtonVisibleTrue();
                    player.move(x, y);
                    refresh();


                } else if (playerCellCheck(x, y).isDoorClose() && !playerInvetory.contains(CellType.KEY)) {
                    refresh();


                } else if (playerCellCheck(x, y).isDoorClose() && playerInvetory.contains(CellType.KEY)) {
                    player.move(x, y);
                    mapLevel(this.mapLevelCounter);
                    refresh();

                } else {
                    player.move(x, y);
                    refresh();

                }
            } else if (playerCellCheck(x, y).isMonster()) {
                player.fight(getCurrentMonster(x, y));
                refresh();

                if ((getCurrentMonster(x, y).getName().equals("Boss") && getCurrentMonsterHealth(x, y) <= 0) || (currentPlayerHealth <= 0)) {
                    map = MapLoader.loadMap(gameOver, this.currentPlayerHealth, this.currentPlayerPower);

                } else if (getCurrentMonsterHealth(x, y) <= 0) {
                    playerCellCheck(x, y).setType(CellType.FLOOR);
                    refresh();
                    updateHealth();

                }
            } else {
                refresh();
            }
        }

    private void onKeyPressed(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
            case UP:
                roundByKeyPressed(0, -1);
                refresh();
                break;
            case DOWN:
                roundByKeyPressed(0,1);
                refresh();
                break;
            case LEFT:
                roundByKeyPressed(-1,0);
                refresh();
                break;
            case RIGHT:
                roundByKeyPressed(1,0);
                refresh();
                break;
            case E:
                pickUp();
                refresh();
                break;
        }
    }


    private void refresh() {
        updateHealth();
        updateInventory();
        updatePower();

        context.setFill(Color.BLACK);
        context.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        for (int x = 0; x < 50; x++) {
            for (int y = 0; y < 32; y++) {
                Cell cell = map.getCell(map.getPlayer().getX() - 25 + x, map.getPlayer().getY() - 16 + y);
                if (cell.getActor() != null) {
                    Tiles.drawTile(context, cell.getActor(), x, y);
                } else {
                    Tiles.drawTile(context, cell, x, y);
                }
            }
        }
        currentHealthLabel.setText(String.valueOf(currentPlayerHealth));
        healthbar.setWidth(currentPlayerHealth * 2);
        currentPowerLabel.setText(String.valueOf(currentPlayerPower));
        powerbar.setWidth(currentPlayerPower * 10);
    }

    public void pickUp() {
        int playerCoordX = player.getX();
        int playerCoordY = player.getY();

        CellType itemToPick = map.getCell(playerCoordX, playerCoordY).getType();
        player.pickUpItem(itemToPick);
        setPickUpButtonVisibleFalse();
        refresh();
    }

    public void updateInventory() {
        StringBuilder stringBuilder = new StringBuilder();
        for (CellType item : map.getPlayer().getInventory()) {
            stringBuilder.append(item).append("\n");
            inventory.setText(String.valueOf(stringBuilder));
        }
    }

    public void updateHealth() {
        currentPlayerHealth = player.getHealth();
    }

    public void updatePower() {
        if (currentPlayerPower <= 20) {
            currentPlayerPower = player.getAttack();
        } else {
            currentPlayerPower = maxPower;
        }
    }

    public void mapLevel(int mapLevelCounter) {
        switch (mapLevelCounter) {
            case 1:
                map = MapLoader.loadMap(map2, this.currentPlayerHealth, this.currentPlayerPower);
                this.player = map.getPlayer();
                this.mapLevelCounter = 2;
                break;

            case 2:
                if (playerCellCheck(0, 0).isDoorClose()) {
                    map = MapLoader.loadMap(map3, this.currentPlayerHealth, this.currentPlayerPower);
                    this.player = map.getPlayer();
                    this.mapLevelCounter = 3;
                    break;
                } else if (!playerCellCheck(0, 0).isDoorClose()) {
                    map = MapLoader.loadMap(map1, this.currentPlayerHealth, this.currentPlayerPower);
                    this.player = map.getPlayer();
                    this.mapLevelCounter = 1;
                    break;
                }
            case 3:
                if (!playerCellCheck(0, 0).isDoorClose()) {
                    map = MapLoader.loadMap(map2, this.currentPlayerHealth, this.currentPlayerPower);
                    this.player = map.getPlayer();
                    this.mapLevelCounter = 2;
                    break;
                }
        }
    }
}
