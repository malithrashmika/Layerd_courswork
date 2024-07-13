package lk.Ijse;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Launcher extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
        stage.setScene(new Scene(root));
//stage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("Login.fxml"))));
stage.setTitle("Login");
stage.show();
    }
}
