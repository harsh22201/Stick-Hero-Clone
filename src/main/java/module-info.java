module com.javafx.game {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires javafx.graphics;
    // JUnit 5
    requires org.junit.jupiter.api;

    opens com.javafx.game to javafx.fxml;

    exports com.javafx.game;
}
