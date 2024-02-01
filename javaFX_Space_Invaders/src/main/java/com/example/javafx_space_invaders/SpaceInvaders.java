package com.example.javafx_space_invaders;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;

import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;


/**
 * The SpaceInvaders class represents the main application for the Space Invaders game.
 * It handles game initialization, rendering, and user input.
 */

public class SpaceInvaders extends Application {


    public static final Random RAND = new Random();
    public static final int WIDTH = 800;
    private static final int HEIGHT = 900;
    private static final int PLAYER_SIZE = 60;


    static Image EXPLOSION_IMG = new Image("file:." + File.separator + "images" + File.separator + "explosion.png");
    static Image EXPLOSION_8_IMG = new Image("file:." + File.separator + "images" + File.separator + "explosion8bit.png");
    static final int EXPLOSION_W = 128;
    static final int EXPLOSION_ROWS = 3;
    static final int EXPLOSION_COL = 3;
    static final int EXPLOSION_H = 128;
    static final int EXPLOSION_STEPS = 15;



    /**
     * The version of the game, determining the appearance of game elements.
     */
    //static String version;
    static String version = "comic" + File.separator + "c";
    //static String version = "8bit/b";
    //static String version = "sw/SW";
    //static String version = "st/ST";

    static Image[] ENEMY_IMG;
    static Image PLAYER_IMG;

    static int maxScore = 0;

    static String baseImagePath = "file:." + File.separator + "images" + File.separator;


    static void loadImages(){
        ENEMY_IMG = new Image[]{
                new Image(baseImagePath + version + "1.png"),
                new Image(baseImagePath + version + "2.png"),
                new Image(baseImagePath + version + "3.png"),
                new Image(baseImagePath + version + "4.png"),
                new Image(baseImagePath + version + "5.png"),
                new Image(baseImagePath + version + "6.png"),
                new Image(baseImagePath + version + "7.png"),
                new Image(baseImagePath + version + "8.png"),
                new Image(baseImagePath + version + "9.png"),
                new Image(baseImagePath + version + "10.png")

        };
//        ImageView imageView = new ImageView(new Image("file:." + File.separator + "images" + File.separator + version + ".png"));
//        imageView.setPreserveRatio(true);
//        PLAYER_IMG = imageView.getImage();
        PLAYER_IMG = new Image(baseImagePath + version + ".png");

    }

    /**
     * The maximum number of enemies in the game.
     */
    public static int maxEnemies = 10;
    int maxShots = 10;
    boolean gameOver = false;
    GraphicsContext gc;

    Rocket player;
    List<Shot> shots;
    List<Universe> univ;
    List<Enemy> enemies;

    private double mouseX;
    private double mouseY;
    public int score;
    private int shotsLeft;

    static void setVersion(String newVersion) {
        version = newVersion;
    }

    //String musicFile = "test.mp3";
    String musicFile = "c.mp3";

    String music() {
        String musicFile;

        // wasnt able to get File.separator working here so maybe music selection would be limited to 1 same song on Windows

        switch (version) {
            case "8bit/b":
                musicFile = "b.mp3";
                break;
            case "sw/SW":
                musicFile = "SW.mp3";
                break;
            case "st/ST":
                musicFile = "ST.mp3";
                break;
            default:
                musicFile = "c.mp3";
                break;
        }

        return musicFile;
    }

    Media sound = new Media(new File(music()).toURI().toString());
    MediaPlayer mediaPlayer = new MediaPlayer(sound);

    //start
    public void start(Stage stage) throws Exception {
        Canvas canvas = new Canvas(WIDTH, HEIGHT);
        loadImages();
        gc = canvas.getGraphicsContext2D();
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(100), e -> run(gc)));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
        canvas.setCursor(Cursor.MOVE);
        canvas.setOnMouseMoved(e -> {
            mouseX = e.getX();
            mouseY = e.getY();
        });
        canvas.setOnMouseClicked(e -> {
            if(shots.size() < maxShots) shots.add(player.shoot());
            if(gameOver) {
                gameOver = false;
                setup();
            }
        });
        setup();
        stage.setScene(new Scene(new StackPane(canvas)));
        stage.setTitle("Space Invaders");
        stage.show();

        mediaPlayer.setVolume(0.09);
        mediaPlayer.play();

        stage.setOnCloseRequest(event -> {
            mediaPlayer.stop();
        });
    }

    /**
     * Initializes the game state, creating instances of the player, enemies, shots, and universe.
     */
    private void setup() {
        univ = new ArrayList<>();
        shots = new ArrayList<>();
        enemies = new ArrayList<>();
        player = new Rocket(WIDTH / 2, HEIGHT - PLAYER_SIZE, PLAYER_SIZE, PLAYER_IMG);
        score = 0;
        maxEnemies = 10;

        // delay
        Timeline enemySpawner = new Timeline();
        for (int i = 0; i < maxEnemies; i++) {
            KeyFrame keyFrame = new KeyFrame(Duration.seconds(i), event -> {
                Enemy newEnemy = this.newEnemy();
                enemies.add(newEnemy);
            });
            enemySpawner.getKeyFrames().add(keyFrame);
        }

        enemySpawner.play();
    }


    public void run(GraphicsContext gc) {
        gc.setFill(Color.grayRgb(20));
        gc.fillRect(0, 0, WIDTH, HEIGHT);
        gc.setTextAlign(TextAlignment.CENTER);
        gc.setFont(Font.font(20));
        gc.setFill(Color.YELLOW);
        gc.fillText("Score: " + score, 60,  20);
        gc.fillText("Shots left: " + (maxShots - shots.size()), 180,  20);
        gc.fillText("Shots max: " + maxShots, 320,  20);
        gc.fillText("Enemies: " + maxEnemies, 460,  20);
        gc.setTextAlign(TextAlignment.RIGHT);
        gc.fillText("Version: " + version, 780,  20);
        gc.setTextAlign(TextAlignment.CENTER);


        if(gameOver) {
            gc.setFont(Font.font(35));
            gc.setFill(Color.YELLOW);
            gc.fillText("Game Over \n" +
                    " Your score: " + score + " \n" +
                    " Max score: " + maxScore + " \n" +
                    "  Click to play again", WIDTH / 2, HEIGHT /2.5);
            maxShots = 10;
//            if (score > maxScore){
//                maxScore = score;
//            }
            maxScore = (score > maxScore) ? score : maxScore;

            return;
        }
        univ.forEach(Universe::draw);

        player.update();
        player.draw();
        player.posX = (int) mouseX;

        enemies.stream().peek(Rocket::update).peek(Rocket::draw).forEach(e -> {
            if(player.colide(e) && !player.exploding) {
                player.explode();
            }
        });


        for (int i = shots.size() - 1; i >=0 ; i--) {
            Shot shot = shots.get(i);
            if(shot.posY < 0 || shot.toRemove)  {
                shots.remove(i);
                continue;
            }
            shot.update();
            shot.draw();
            for (Enemy enemy : enemies) {
                if(shot.colide(enemy) && !enemy.exploding) {
                    score++;
                    enemy.explode();
                    shot.toRemove = true;
                }
            }
        }

        for (int i = enemies.size() - 1; i >= 0; i--){
            if(enemies.get(i).destroyed)  {
                enemies.set(i, newEnemy());
            }
        }

        gameOver = player.destroyed;
        if(RAND.nextInt(10) > 2) {
            univ.add(new Universe(this));
        }
        for (int i = 0; i < univ.size(); i++) {
            if(univ.get(i).posY > HEIGHT)
                univ.remove(i);
        }
    }

    public class Rocket {
        int posX, posY, size;
        boolean exploding, destroyed;
        Image img;
        int explosionStep = 0;

        public Rocket(int posX, int posY, int size,  Image image) {
            this.posX = posX;
            this.posY = posY;
            this.size = size;
            img = image;
        }

        public Shot shoot() {
            return new Shot(SpaceInvaders.this, posX + size / 2 - Shot.size / 2, posY - Shot.size);
        }

        public void update() {
            if(exploding) explosionStep++;
            destroyed = explosionStep > EXPLOSION_STEPS;
        }

        /**
         * Selecting and applying right explosion effect based on game version
         */

        public void draw() {
            if (exploding) {
                if (version.contains("8bit/b")) {
                    gc.drawImage(EXPLOSION_8_IMG, posX, posY, size, size);
                } else {
                    gc.drawImage(EXPLOSION_IMG, explosionStep % EXPLOSION_COL * EXPLOSION_W,
                            (explosionStep / EXPLOSION_ROWS) * EXPLOSION_H + 1,
                            EXPLOSION_W, EXPLOSION_H,
                            posX, posY, size, size);
                }
            } else {
                gc.drawImage(img, posX, posY, size, size);
            }
        }


        public boolean colide(Rocket other) {
            int d = distance(this.posX + size / 2, this.posY + size /2,
                    other.posX + other.size / 2, other.posY + other.size / 2);
            return d < other.size / 2 + this.size / 2 ;
        }

        public void explode() {
            exploding = true;
            explosionStep = -1;
        }
    }

    /**
     * The Enemy class represents a enemy in the Space Invaders game, extending the Rocket class.
     * It includes methods for updating the enemies position based on speed.
     */

    public class Enemy extends Rocket {

        int SPEED = (score/5)+2;
        // number of ships at the start

        public Enemy(int posX, int posY, int size, Image image) {
            super(posX, posY, size, image);
        }

        public void update() {
            super.update();
            if(!exploding && !destroyed) posY += SPEED;
            if(posY > HEIGHT) destroyed = true;
        }
    }

    /**
     * Creates a new instance of the Enemy class with random attributes.
     *
     * @return A new Enemy instance.
     */
    Enemy newEnemy() {
        return new Enemy(50 + RAND.nextInt(WIDTH - 100),
                0, PLAYER_SIZE,
                ENEMY_IMG[RAND.nextInt(ENEMY_IMG.length)]);
    }

    int distance(int x1, int y1, int x2, int y2) {
        return (int) Math.sqrt(Math.pow((x1 - x2), 2) + Math.pow((y1 - y2), 2));
    }

    public static void main(String[] args) {
        launch();
    }
}


