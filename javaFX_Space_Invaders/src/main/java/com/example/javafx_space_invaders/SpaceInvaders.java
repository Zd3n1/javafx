package com.example.javafx_space_invaders;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
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


public class SpaceInvaders extends Application {

    public static final Random RAND = new Random();
    public static final int WIDTH = 800;
    private static final int HEIGHT = 900;
    private static final int PLAYER_SIZE = 60;

    static Image EXPLOSION_IMG = new Image("file:./images/explosion.png");
    static Image EXPLOSION_8_IMG = new Image("file:./images/explosion8bit.png");
    static final int EXPLOSION_W = 128;
    static final int EXPLOSION_ROWS = 3;
    static final int EXPLOSION_COL = 3;
    static final int EXPLOSION_H = 128;
    static final int EXPLOSION_STEPS = 15;

    //static String version;

    //static String version = "8bit/b";
    //static String version = "sw/SW";
    //static String version = "st/ST";
    static String version = "comic/c";

    static Image[] BOMBS_IMG;
    static Image PLAYER_IMG;

    static int maxScore = 0;


    static void loadImages(){
        BOMBS_IMG = new Image[]{
                new Image("file:./images/" + version + "1.png"),
                new Image("file:./images/" + version + "2.png"),
                new Image("file:./images/" + version + "3.png"),
                new Image("file:./images/" + version + "4.png"),
                new Image("file:./images/" + version + "5.png"),
                new Image("file:./images/" + version + "6.png"),
                new Image("file:./images/" + version + "7.png"),
                new Image("file:./images/" + version + "8.png"),
                new Image("file:./images/" + version + "9.png"),
                new Image("file:./images/" + version + "10.png"),

        };
        PLAYER_IMG = new Image("file:./images/" + version + ".png");
    }
//    Image[] BOMBS_IMG = {
//            new Image("file:./images/" + version + "1.png"),
//            new Image("file:./images/" + version + "2.png"),
//            new Image("file:./images/" + version + "3.png"),
//            new Image("file:./images/" + version + "4.png"),
//            new Image("file:./images/" + version + "5.png"),
//            new Image("file:./images/" + version + "6.png"),
//            new Image("file:./images/" + version + "7.png"),
//            new Image("file:./images/" + version + "8.png"),
//            new Image("file:./images/" + version + "9.png"),
//            new Image("file:./images/" + version + "10.png"),
//
//    };

//    static final Image BOMBS_IMG[] = {
//            for (int i = 1; i <= 17; i++) {
//        String filename = "file:./images/comic/" + "c" + i + ".png";
//        BOMBS_IMG[i - 1] = new Image(filename);
//    }

//    String musicFile = "file:./test.mp3";
//
//        Media sound = new Media(musicFile);
//        MediaPlayer mediaPlayer = new MediaPlayer(sound);
//        mediaPlayer.play();






    final int MAX_BOMBS = 10;
    int maxShots = 10;
    boolean gameOver = false;
    GraphicsContext gc;

    Rocket player;
    List<Shot> shots;
    List<Universe> univ;
    List<Bomb> bombs;

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



    //String musicFile = "comic/c.mp3";
    //Media sound = new Media(new File(musicFile).toURI().toString());
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

        //String musicFile = "./test.mp3";

//        File file = new File("file:./test.mp3");
//        File file = new File("./test.mp3");
//        Media sound = new Media(file.toURI().toString());
//        MediaPlayer mediaPlayer = new MediaPlayer(sound);
//        mediaPlayer.play();

        //nevim jak ale totalne mi to zasekalo hru a zacalo se otevirat nekolik instanci najednou

//        try {
//            String musicFilePath = "./test.mp3";
//            File file = new File(musicFilePath);
//
//            if (!file.exists()) {
//                throw new RuntimeException("File not found: " + musicFilePath);
//            }
//
//            Media sound = new Media(file.toURI().toString());
//            MediaPlayer mediaPlayer = new MediaPlayer(sound);
//            mediaPlayer.play();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

//        String musicFile = "test.mp3";
//        Media sound = new Media(new File(musicFile).toURI().toString());
//        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.setVolume(0.09);
        mediaPlayer.play();

        stage.setOnCloseRequest(event -> {
            // Stop the music when the window is closed
            mediaPlayer.stop();
        });
    }

    private void setup() {
        univ = new ArrayList<>();
        shots = new ArrayList<>();
        bombs = new ArrayList<>();
        player = new Rocket(WIDTH / 2, HEIGHT - PLAYER_SIZE, PLAYER_SIZE, PLAYER_IMG);
        score = 0;
        IntStream.range(0, MAX_BOMBS).mapToObj(i -> this.newBomb()).forEach(bombs::add);
    }

    public void run(GraphicsContext gc) {
        gc.setFill(Color.grayRgb(20));
        gc.fillRect(0, 0, WIDTH, HEIGHT);
        gc.setTextAlign(TextAlignment.CENTER);
        gc.setFont(Font.font(20));
        gc.setFill(Color.WHITE);
        gc.fillText("Score: " + score, 60,  20);
        gc.fillText("Shots left: " + (maxShots - shots.size()), 180,  20);
        gc.fillText("Shots max: " + maxShots, 320,  20);
        gc.setTextAlign(TextAlignment.RIGHT);
        gc.fillText("Version: " + version, 780,  20);
        gc.setTextAlign(TextAlignment.CENTER);


        if(gameOver) {
            mediaPlayer.stop();
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

        bombs.stream().peek(Rocket::update).peek(Rocket::draw).forEach(e -> {
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
            for (Bomb bomb : bombs) {
                if(shot.colide(bomb) && !bomb.exploding) {
                    score++;
                    bomb.explode();
                    shot.toRemove = true;
                }
            }
        }

        for (int i = bombs.size() - 1; i >= 0; i--){
            if(bombs.get(i).destroyed)  {
                bombs.set(i, newBomb());
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

        public void draw() {
            if (exploding) {
                if (version.contains("8bit/b")) {
                    gc.drawImage(EXPLOSION_8_IMG, posX, posY, size, size);
                } else {
                    // Draw the regular explosion image
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

    public class Bomb extends Rocket {

        int SPEED = (score/5)+2;

        public Bomb(int posX, int posY, int size, Image image) {
            super(posX, posY, size, image);
        }

        public void update() {
            super.update();
            if(!exploding && !destroyed) posY += SPEED;
            if(posY > HEIGHT) destroyed = true;
        }
    }

    Bomb newBomb() {
        return new Bomb(50 + RAND.nextInt(WIDTH - 100),
                0, PLAYER_SIZE,
                BOMBS_IMG[RAND.nextInt(BOMBS_IMG.length)]);
    }

    int distance(int x1, int y1, int x2, int y2) {
        return (int) Math.sqrt(Math.pow((x1 - x2), 2) + Math.pow((y1 - y2), 2));
    }

    public static void main(String[] args) {
        launch();
    }
}


