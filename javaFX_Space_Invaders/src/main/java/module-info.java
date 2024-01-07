module com.example.javafx_space_invaders {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires com.almasb.fxgl.all;
    requires javafx.media;
    requires java.desktop;

    opens com.example.javafx_space_invaders to javafx.fxml;
    exports com.example.javafx_space_invaders;
    exports com.example.javafx_space_invaders.nepouzite;
    opens com.example.javafx_space_invaders.nepouzite to javafx.fxml;
}