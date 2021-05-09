package finalProject;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.stage.Stage;

public class StartSceneController implements Initializable{
    @FXML
    private Button ternisButton;
    @FXML
    private Button mineSweeperButton;
    @FXML
    private Slider difficultSlider;
    private double difficulty;
    private FXMLLoader gameLoader;
    @Override
    public void initialize(URL arg0,ResourceBundle arg1){
        difficultSlider.valueProperty().addListener(e->difficulty=difficultSlider.getValue());
    }
    public void ternisStart(ActionEvent e) throws IOException{
        Stage stage= (Stage)((Node)e.getSource()).getScene().getWindow();
        gameLoader=new FXMLLoader(getClass().getResource("ternis.fxml"));
        Scene ternisScene=new Scene(gameLoader.load(),300,500);
        ternisController controller=gameLoader.getController();
        controller.difficulty=difficulty;
        controller.Initialize(ternisScene);
        stage.setTitle("tetris");
        stage.setScene(ternisScene);
    }
    public void minesweeperStart(ActionEvent e) throws IOException{
        gameLoader=new FXMLLoader(getClass().getResource("minesweeper.fxml"));
        Scene minesweeperScene=new Scene(gameLoader.load(),800,600);
        minesweeperController controller=gameLoader.getController();
        controller.difficulty=difficulty;
        Stage stage= (Stage)((Node)e.getSource()).getScene().getWindow();
        stage.setTitle("minesweeper");
        stage.setScene(minesweeperScene);
    }
}
