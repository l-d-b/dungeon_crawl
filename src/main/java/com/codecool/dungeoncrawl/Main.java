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
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.scene.text.Font;
import javafx.scene.shape.Rectangle;

import javax.swing.*;

public class Main extends Application {
    GameMap map = MapLoader.loadMap();
    Canvas canvas = new Canvas(
            map.getWidth() * Tiles.TILE_WIDTH,
            map.getHeight() * Tiles.TILE_WIDTH);
    GraphicsContext context = canvas.getGraphicsContext2D();
    int maxPower = 20;
    int maxHealth = 100;
    int currentHealth = map.getPlayer().getHealth();
    int currentPower = map.getPlayer().getAttack();
   // int healthbarWidth = currentHealth*20;
    Label healthLabel = new Label();
 //   Label uzenet = new Label();
    Label currentHealthLabel = new Label();
    Label currentPowerLabel = new Label();
    Label inventoryLabel = new Label();
    Rectangle healthbar = new Rectangle();
    Rectangle powerbar = new Rectangle();
    Button pickUpButton;
    Player player;
    Monster monster;
    Cell cell;

    Label inventory = new Label();
    Label powerLabel = new Label();


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        GridPane ui = new GridPane();
        ui.setMinWidth(300);
        ui.setVgap(1);
        ui.setPadding(new Insets(50));
        String playerHealth = String.valueOf(map.getPlayer().getHealth());
        String playerPower = String.valueOf(map.getPlayer().getAttack());
        healthLabel.setText("Health: ");
        healthLabel.setFont(Font.font ("Verdana", FontWeight.BOLD, 20));
        healthLabel.setTextFill(Color.BROWN);
        currentHealthLabel.setText(playerHealth);
        ui.add(healthLabel, 0, 0);
        ui.add(currentHealthLabel, 0, 1);

        ui.setHalignment(healthLabel, HPos.CENTER);
        healthLabel.setPadding(new Insets(0,55, 0, 55));

        healthbar = new Rectangle(100,100,200,20);
        Rectangle background = new Rectangle(100,100,200,20);

        ui.add(background, 0, 11);
        ui.add(healthbar,0,11);
        background.setFill(Color.GREY);

        healthbar.setFill(Color.GREEN);

     //   ui.add(new Label("Power: "), 0,12);
      //  ui.add(powerLabel,1,12);

        powerLabel.setText("Power: ");
        powerLabel.setFont(Font.font ("Verdana", FontWeight.BOLD, 20));
        powerLabel.setTextFill(Color.BROWN);
        currentPowerLabel.setText(playerPower);
        ui.add(powerLabel, 0, 12);
        ui.add(currentPowerLabel, 0, 14);
        ui.setHalignment(powerLabel, HPos.CENTER);

        powerbar = new Rectangle(100,100,200,20);
        Rectangle powBackground = new Rectangle(100,100,200,20);

        ui.add(powBackground, 0, 15);
        ui.add(powerbar,0,15);
        powBackground.setFill(Color.GREY);

        powerbar.setFill(Color.RED);

        pickUpButton = new Button("Pick up");
        pickUpButton.setVisible(false);

        pickUpButton.addEventFilter(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            pickUp();
        });

        pickUpButton.addEventFilter(KeyEvent.KEY_PRESSED, keyEvent-> {
            if(keyEvent.getCode() == KeyCode.ENTER){
               pickUp();
           }
        });

        primaryStage.show();

        BorderPane borderPane = new BorderPane();

        borderPane.setCenter(canvas);
        canvas.setWidth(1600);
        canvas.setHeight(1000);
        borderPane.setRight(ui);

        ui.add(pickUpButton,0,17);
        ui.setHalignment(pickUpButton, HPos.CENTER);

        inventoryLabel.setText("Inventory:");
        ui.add(inventoryLabel,0,19);
        ui.add(inventory, 0, 21);
        inventoryLabel.setFont(Font.font ("Verdana", FontWeight.BOLD, 22));
        inventoryLabel.setTextFill(Color.BROWN);
        ui.setHalignment(inventoryLabel, HPos.CENTER);
        ui.setHalignment(inventory, HPos.CENTER);
        inventory.setFont(Font.font ("Verdana", 16));
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

                if(map.getPlayer().cellCheck(0, -1).getType() != CellType.WALL &&

                        map.getPlayer().cellCheck(0, -1).getType() != CellType.GOLEM){
                        !map.getPlayer().cellCheck(0, -1).isMonster()){

                    if(map.getPlayer().cellCheck(0, -1).isItem()) {

                        pickUpButton.setVisible(true);
                        map.getPlayer().move(0, -1);
                        refresh();
                        break;
                    }
                    else if (map.getPlayer().cellCheck(0, -1).getType() == CellType.CLOSED_DOOR &&
                            !map.getPlayer().getInventory().contains(CellType.KEY)) {

                        refresh();
                        break;
                    }
                    else if (map.getPlayer().cellCheck(0, -1).getType() == CellType.CLOSED_DOOR &&
                            map.getPlayer().getInventory().contains(CellType.KEY)) {

                        map.getPlayer().move(0,-1);
                        map.getPlayer().cellCheck(0, 0).setType(CellType.OPENED_DOOR);
                        refresh();
                        break;
                    }

                    else{
                        map.getPlayer().move(0, -1);
                        refresh();
                        break;
                    }

                }

                else if(map.getPlayer().cellCheck(0,-1).getType() == CellType.GOLEM){

                else if(map.getPlayer().cellCheck(0,-1).isMonster()){

                    map.getPlayer().fight(map.getPlayer().cellCheck(0,-1).getMonster());
                    if (map.getPlayer().getHealth()<=0){
                        return;
                    }
                    else if(map.getPlayer().cellCheck(0,-1).getMonster().getHealth()<=0){
                        map.getPlayer().cellCheck(0,-1).setType(CellType.FLOOR);
                        break;
                    }
                }
                else{
                    refresh();
                    break;
                }
            case DOWN:

                if(map.getPlayer().cellCheck(0, 1).getType() != CellType.WALL &&
                        !map.getPlayer().cellCheck(0, 1).isMonster()){

                    if(map.getPlayer().cellCheck(0, 1).isItem()) {

                        pickUpButton.setVisible(true);
                        map.getPlayer().move(0, 1);
                        refresh();
                        break;
                    }
                    else if (map.getPlayer().cellCheck(0, 1).getType() == CellType.CLOSED_DOOR &&
                            !map.getPlayer().getInventory().contains(CellType.KEY)) {

                        refresh();
                        break;
                    }
                    else if (map.getPlayer().cellCheck(0, 1).getType() == CellType.CLOSED_DOOR &&
                            map.getPlayer().getInventory().contains(CellType.KEY)) {

                        map.getPlayer().move(0,1);
                        map.getPlayer().cellCheck(0, 0).setType(CellType.OPENED_DOOR);
                        refresh();
                        break;
                    }
                    else{
                        map.getPlayer().move(0, 1);
                        refresh();
                        break;
                    }

                }
                else if(map.getPlayer().cellCheck(0, 1).isMonster()){
                    map.getPlayer().fight(map.getPlayer().cellCheck(0, 1).getMonster());
                    if (map.getPlayer().getHealth()<=0){
                        return;
                    }
                    else if(map.getPlayer().cellCheck(0, 1).getMonster().getHealth()<=0){
                        map.getPlayer().cellCheck(0, 1).setType(CellType.FLOOR);
                        break;
                    }
                }
                else {
                    refresh();
                    break;

                }
            case LEFT:

                if(map.getPlayer().cellCheck(-1, 0).getType() != CellType.WALL &&
                        !map.getPlayer().cellCheck(-1, 0).isMonster()){



                    if(map.getPlayer().cellCheck(-1, 0).isItem()) {

                        pickUpButton.setVisible(true);
                        map.getPlayer().move(-1, 0);
                        refresh();
                        break;
                    }
                    else if (map.getPlayer().cellCheck(-1, 0).getType() == CellType.CLOSED_DOOR &&
                            !map.getPlayer().getInventory().contains(CellType.KEY)) {

                        refresh();
                        break;
                    }
                    else if (map.getPlayer().cellCheck(-1, 0).getType() == CellType.CLOSED_DOOR &&
                            map.getPlayer().getInventory().contains(CellType.KEY)) {

                        map.getPlayer().move(-1,0);
                        map.getPlayer().cellCheck(0, 0).setType(CellType.OPENED_DOOR);
                        refresh();
                        break;
                    }
                    else{
                        map.getPlayer().move(-1, 0);
                        refresh();
                        break;
                    }
                }
                else if(map.getPlayer().cellCheck(-1, 0).isMonster()){
                    map.getPlayer().fight(map.getPlayer().cellCheck(-1, 0).getMonster());
                    if (map.getPlayer().getHealth()<=0){
                        return;
                    }
                    else if(map.getPlayer().cellCheck(-1, 0).getMonster().getHealth()<=0){
                        map.getPlayer().cellCheck(-1, 0).setType(CellType.FLOOR);
                        break;
                    }
                }
                else {
                    refresh();
                    break;

                }
            case RIGHT:

                if(map.getPlayer().cellCheck(1,0).getType() != CellType.WALL &&
                        !map.getPlayer().cellCheck(1,0).isMonster()){

                    if(map.getPlayer().cellCheck(1,0).isItem()) {

                        pickUpButton.setVisible(true);
                        map.getPlayer().move(1,0);
                        refresh();
                        break;
                    }
                    else if (map.getPlayer().cellCheck(1, 0).getType() == CellType.CLOSED_DOOR &&
                            !map.getPlayer().getInventory().contains(CellType.KEY)) {

                        refresh();
                        break;
                    }
                    else if (map.getPlayer().cellCheck(1, 0).getType() == CellType.CLOSED_DOOR &&
                            map.getPlayer().getInventory().contains(CellType.KEY)) {

                        map.getPlayer().move(1,0);
                        map.getPlayer().cellCheck(0, 0).setType(CellType.OPENED_DOOR);
                        refresh();
                        break;
                    }
                    else{
                        map.getPlayer().move(1,0);
                        refresh();
                        break;
                    }
                }
                else if(map.getPlayer().cellCheck(1,0).isMonster()){
                    map.getPlayer().fight(map.getPlayer().cellCheck(1,0).getMonster());
                    if (map.getPlayer().getHealth()<=0){
                        return;
                    }
                    else if(map.getPlayer().cellCheck(1,0).getMonster().getHealth()<=0){
                        map.getPlayer().cellCheck(1,0).setType(CellType.FLOOR);
                        break;
                    }
                }
                else {
                    refresh();
                    break;
                }
            case E:
                pickUp();
                refresh();
                break;
        }
    }

    private void refresh() {
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
        currentHealthLabel.setText(String.valueOf(map.getPlayer().getHealth()));
        currentHealth = map.getPlayer().getHealth();
        System.out.println(map.getPlayer().getHealth());
        healthbar.setWidth(map.getPlayer().getHealth() * 2);
        currentPowerLabel.setText(String.valueOf(currentPower));
        powerbar.setWidth(currentPower * 10);

        updateHealth();
        updateInventory();
        updatePower();
    }

    public void pickUp(){
        CellType itemToPick = map.getCell(map.getPlayer().getX(), map.getPlayer().getY()).getType();
        map.getPlayer().pickUpItem(itemToPick, map);
        pickUpButton.setVisible(false);
        refresh();
    }

    public void updateInventory(){
        StringBuilder stringBuilder = new StringBuilder();
        for(CellType item: map.getPlayer().getInventory()){
            stringBuilder.append(item).append("\n");
            System.out.println(stringBuilder);
            inventory.setText(String.valueOf(stringBuilder));
        }
    }

    public void updateHealth(){
       // healthLabel.setText(String.valueOf(map.getPlayer().getHealth()));
        currentHealth = map.getPlayer().getHealth();
    }

    public void updatePower(){
   //     currentPowerLabel.setText(String.valueOf(map.getPlayer().getAttack()));
        if (map.getPlayer().getAttack() <= 20) {
            currentPower = map.getPlayer().getAttack();
        } else {
            currentPower = maxPower;
        }
    }

    public void checkingIsWall(){

    }
}
