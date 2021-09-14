package com.codecool.dungeoncrawl;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.MapLoader;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {
    GameMap map = MapLoader.loadMap();
    Canvas canvas = new Canvas(
            map.getWidth() * Tiles.TILE_WIDTH,
            map.getHeight() * Tiles.TILE_WIDTH);
    GraphicsContext context = canvas.getGraphicsContext2D();
    Label healthLabel = new Label();
    Button pickUpButton;
    Label inventory = new Label();
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        GridPane ui = new GridPane();
        ui.setPrefWidth(200);
        ui.setPadding(new Insets(10));

        ui.add(new Label("Health: "), 0, 0);
        ui.add(healthLabel, 1, 0);

        pickUpButton = new Button("Pick up");
        Scene s = new Scene(pickUpButton, 200,200);
        primaryStage.setScene(s);
        pickUpButton.setVisible(false);
        map.getPlayer();
        pickUpButton.addEventFilter(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            pickUp();
        });

        //primaryStage.show();

        BorderPane borderPane = new BorderPane();

        borderPane.setCenter(canvas);
        borderPane.setRight(ui);
        ui.add(pickUpButton,0,1);

        ui.add(new Label("Inventory:"),0,7);
        ui.add(inventory, 0, 8);
        //System.out.println(map.getPlayer().getInventory());


        Scene scene = new Scene(borderPane);
        primaryStage.setScene(scene);
        refresh();
        scene.setOnKeyPressed(this::onKeyPressed);
        primaryStage.setTitle("Dungeon Crawl");
        primaryStage.show();
    }

    private void onKeyPressed(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
            case UP:
                map.getPlayer().move(0, -1);
                if (map.getCell(map.getPlayer().getX(), map.getPlayer().getY()).isItem()) {
                    pickUpButton.setVisible(true);
                }
                refresh();
                break;

            case DOWN:
                map.getPlayer().move(0, 1);
                if (map.getCell(map.getPlayer().getX(), map.getPlayer().getY()).isItem()) {
                    pickUpButton.setVisible(true);
                }
                refresh();
                break;
            case LEFT:
                map.getPlayer().move(-1, 0);
                if (map.getCell(map.getPlayer().getX(), map.getPlayer().getY()).isItem()) {
                    pickUpButton.setVisible(true);
                }
                refresh();
                break;
            case RIGHT:
                map.getPlayer().move(1,0);
                if (map.getCell(map.getPlayer().getX(), map.getPlayer().getY()).isItem()) {
                    pickUpButton.setVisible(true);
                }
                refresh();
                break;
        }
    }

    private void refresh() {
        context.setFill(Color.BLACK);
        context.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        for (int x = 0; x < map.getWidth(); x++) {
            for (int y = 0; y < map.getHeight(); y++) {
                Cell cell = map.getCell(x, y);
                if (cell.getActor() != null) {
                    Tiles.drawTile(context, cell.getActor(), x, y);
                } else {
                    Tiles.drawTile(context, cell, x, y);
                }
            }
        }
        healthLabel.setText("" + map.getPlayer().getHealth());
    }

    public void pickUp(){
        CellType itemToPick = map.getCell(map.getPlayer().getX(), map.getPlayer().getY()).getType();
        map.getPlayer().pickUpItem(itemToPick, map);
        pickUpButton.setVisible(false);
        refresh();
        updateInventory();

    }

    public void updateInventory(){
        StringBuilder stringBuilder = new StringBuilder();
        for(CellType item: map.getPlayer().getInventory()){
            stringBuilder.append(item).append("\n");
            System.out.println(stringBuilder);
            inventory.setText(String.valueOf(stringBuilder));
        }
    }
}
