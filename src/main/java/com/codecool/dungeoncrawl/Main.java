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

public class Main extends Application {
    String gameOver = "/gameover.txt";
    String map1 = "/map.txt";
    String map2 = "/map_2.txt";
    String map3 = "/map_3.txt";
    private int mapLevelCounter;
    GameMap map = MapLoader.loadMap(map1);
    Player player = map.getPlayer();
    Canvas canvas = new Canvas(
            map.getWidth() * Tiles.TILE_WIDTH,
            map.getHeight() * Tiles.TILE_WIDTH);
    GraphicsContext context = canvas.getGraphicsContext2D();
    int maxPower = 20;
    int currentHealth = player.getHealth();
    int currentPower = player.getAttack();
    Label healthLabel = new Label();
    Label currentHealthLabel = new Label();
    Label currentPowerLabel = new Label();
    Label inventoryLabel = new Label();
    Rectangle healthbar = new Rectangle();
    Rectangle powerbar = new Rectangle();
    Button pickUpButton;

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
        Player player = map.getPlayer();
        String playerHealth = String.valueOf(player.getHealth());
        String playerPower = String.valueOf(player.getAttack());
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

        mapLevelCounter = 1;

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


        Scene scene = new Scene(borderPane);
        primaryStage.setScene(scene);
        refresh();
        scene.setOnKeyPressed(this::onKeyPressed);
        primaryStage.setTitle("Dungeon Crawl");
        primaryStage.show();
    }

    private void onKeyPressed(KeyEvent keyEvent) {
        Player player = map.getPlayer();
        switch (keyEvent.getCode()) {
            case UP:
                if(player.cellCheck(0, -1).getType() != CellType.WALL &&
                        !player.cellCheck(0, -1).isMonster()){

                    if(player.cellCheck(0, -1).isItem()) {

                        pickUpButton.setVisible(true);
                        player.move(0, -1);
                        refresh();
                        break;
                    }
                    else if (player.cellCheck(0, -1).getType() == CellType.CLOSED_DOOR &&
                            !player.getInventory().contains(CellType.KEY)) {

                        refresh();
                        break;
                    }
                    else if (player.cellCheck(0, -1).getType() == CellType.CLOSED_DOOR &&
                            player.getInventory().contains(CellType.KEY)) {

                        player.move(0,-1);
                        mapLevel(this.mapLevelCounter);
                        refresh();
                        break;
                    }
                    else{
                        player.move(0, -1);
                        refresh();
                        break;
                    }

                }
                else if(player.cellCheck(0,-1).isMonster()){
                    Monster monster = player.cellCheck(0,-1).getMonster();
                    player.fight(monster);
                    refresh();
                    if ((monster.getName().equals("Boss") && monster.getHealth() <= 0) || (player.getHealth()<=0)){
                        map = MapLoader.loadMap(gameOver);
                        refresh();
                        break;
                    }
                    else if(player.cellCheck(0,-1).getMonster().getHealth()<=0){
                        player.cellCheck(0,-1).setType(CellType.FLOOR);
                        updateHealth();
                        break;
                    }
                }
                else{
                    refresh();
                    break;
                }
            case DOWN:

                if(player.cellCheck(0, 1).getType() != CellType.WALL &&
                        !player.cellCheck(0, 1).isMonster()){

                    if(player.cellCheck(0, 1).isItem()) {

                        pickUpButton.setVisible(true);
                        player.move(0, 1);
                        refresh();
                        break;
                    }
                    else if (player.cellCheck(0, 1).getType() == CellType.CLOSED_DOOR &&
                            !player.getInventory().contains(CellType.KEY)) {

                        refresh();
                        break;
                    }
                    else if (player.cellCheck(0, 1).getType() == CellType.CLOSED_DOOR &&
                            player.getInventory().contains(CellType.KEY)) {

                        player.move(0,1);
                        mapLevel(this.mapLevelCounter);


                        refresh();
                        break;
                    }
                    else{
                        player.move(0, 1);
                        refresh();
                        break;
                    }

                }
                else if(player.cellCheck(0, 1).isMonster()){
                    player.fight(player.cellCheck(0, 1).getMonster());
                    refresh();
                    if (player.getHealth()<=0){
                        map =MapLoader.loadMap(gameOver);
                        refresh();
                        break;
                    }
                    else if(player.cellCheck(0, 1).getMonster().getHealth()<=0){
                        player.cellCheck(0, 1).setType(CellType.FLOOR);
                        break;
                    }
                }
                else {
                    refresh();
                    break;

                }
            case LEFT:

                if(player.cellCheck(-1, 0).getType() != CellType.WALL &&
                        !player.cellCheck(-1, 0).isMonster()){



                    if(player.cellCheck(-1, 0).isItem()) {

                        pickUpButton.setVisible(true);
                        player.move(-1, 0);
                        refresh();
                        break;
                    }
                    else if (player.cellCheck(-1, 0).getType() == CellType.CLOSED_DOOR &&
                            !player.getInventory().contains(CellType.KEY)) {

                        refresh();
                        break;
                    }
                    else if (player.cellCheck(-1, 0).getType() == CellType.CLOSED_DOOR &&
                            player.getInventory().contains(CellType.KEY)) {

                        player.move(-1,0);
                        mapLevel(this.mapLevelCounter);
                        refresh();
                        break;
                    }
                    else{
                        player.move(-1, 0);
                        refresh();
                        break;
                    }
                }
                else if(player.cellCheck(-1, 0).isMonster()){
                    player.fight(player.cellCheck(-1, 0).getMonster());
                    refresh();
                    if (player.getHealth()<=0){
                        map =MapLoader.loadMap(gameOver);
                        refresh();
                        break;
                    }
                    else if(player.cellCheck(-1, 0).getMonster().getHealth()<=0){
                        player.cellCheck(-1, 0).setType(CellType.FLOOR);
                        break;
                    }
                }
                else {
                    refresh();
                    break;

                }
            case RIGHT:

                if(player.cellCheck(1,0).getType() != CellType.WALL &&
                        !player.cellCheck(1,0).isMonster()){

                    if(player.cellCheck(1,0).isItem()) {

                        pickUpButton.setVisible(true);
                        player.move(1,0);
                        refresh();
                        break;
                    }
                    else if (player.cellCheck(1, 0).getType() == CellType.CLOSED_DOOR &&
                            !player.getInventory().contains(CellType.KEY)) {

                        refresh();
                        break;
                    }
                    else if (player.cellCheck(1, 0).getType() == CellType.CLOSED_DOOR &&
                            player.getInventory().contains(CellType.KEY)) {

                        player.move(1,0);
                        mapLevel(this.mapLevelCounter);

                        refresh();
                        break;
                    }
                    else{
                        player.move(1,0);
                        refresh();
                        break;
                    }
                }
                else if(player.cellCheck(1,0).isMonster()){
                    player.fight(player.cellCheck(1,0).getMonster());
                    refresh();
                    if (player.getHealth()<=0){
                        map =MapLoader.loadMap(gameOver);
                        refresh();
                        break;
                    }
                    else if(player.cellCheck(1,0).getMonster().getHealth()<=0){
                        player.cellCheck(1,0).setType(CellType.FLOOR);
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
        map.getPlayer().pickUpItem(itemToPick);
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
        currentHealth = map.getPlayer().getHealth();
    }

    public void updatePower(){
        if (map.getPlayer().getAttack() <= 20) {
            currentPower = map.getPlayer().getAttack();
        } else {
            currentPower = maxPower;
        }
    }

    public void mapLevel(int mapLevelCounter){
        switch (mapLevelCounter){
            case 1:
                map = MapLoader.loadMap(map2);
                this.mapLevelCounter = 2;
                break;

            case 2:
                if(map.getPlayer().cellCheck(0, 0).getType() == CellType.CLOSED_DOOR){
                    map = MapLoader.loadMap(map3);
                    this.mapLevelCounter = 3;
                    break;
                }
                else if(map.getPlayer().cellCheck(0, 0).getType() == CellType.OPENED_DOOR){
                    map = MapLoader.loadMap(map1);
                    this.mapLevelCounter = 1;
                     break;
                }
            case 3:
                if(map.getPlayer().cellCheck(0, 0).getType() == CellType.OPENED_DOOR){
                    map = MapLoader.loadMap(map2);
                    this.mapLevelCounter = 2;
                    break;
                }
        }
    }
}
