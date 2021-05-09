package finalProject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class Main extends Application{
    private int closeCounter=0;
    private boolean canclose=true;
    public static void main(String[] args){
        System.out.println("project launch");
        launch(args);
    }
    @Override
    public void start(Stage m_stage)throws Exception{
        Parent root= FXMLLoader.load(getClass().getResource("StartScene.fxml"));
        m_stage.setTitle("Start manu");
        m_stage.setResizable(false);
        m_stage.setScene(new Scene(root, 600, 400));
        m_stage.show();
        m_stage.setOnCloseRequest(event->{
            event.consume();
            joke(m_stage);
        });
    }
    public void joke(Stage stage){
        Alert alert;
        switch(closeCounter) {
            case 0:
                alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Plz don't go");
                alert.setHeaderText("Are you sure you want to quit?");
                alert.setResizable(false);
                alert.setContentText("sure to quit");
                if(alert.showAndWait().get()==ButtonType.CANCEL)return;
                alert.setAlertType(AlertType.ERROR);
                alert.setHeaderText("Just a few more steps");
                alert.setContentText("Far way to go");
                alert.show();
                closeCounter++;
                break;
            case 1:
                alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("really?");
                alert.setHeaderText("Plz don't go");
                alert.setResizable(false);
                alert.setContentText("Something good might happend if you click cancel");
                if(alert.showAndWait().get()==ButtonType.CANCEL){
                    closeCounter++;
                    return;
                }
                alert.setAlertType(AlertType.ERROR);
                alert.setHeaderText("That's the wrong choise");
                alert.setContentText("I'd never lie");
                alert.show();
                closeCounter=0;
                break;
            case 2:
                alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("really?");
                alert.setHeaderText("Some thing might happend if you click cancel?");
                alert.setResizable(false);
                alert.setContentText("Plz don't go");
                if(alert.showAndWait().get()==ButtonType.CANCEL){
                    closeCounter++;
                    return;
                }
                alert.setAlertType(AlertType.ERROR);
                alert.setHeaderText("That's a bad choise");
                alert.setContentText("Be certain");
                alert.show();
                closeCounter=0;
                break;
            case 3:
                alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("really?");
                alert.setHeaderText("Some thing might happend if you click cancel?");
                alert.setResizable(false);
                alert.setContentText("Plz don't go");
                if(alert.showAndWait().get()==ButtonType.CANCEL){
                    closeCounter++;
                    return;
                }
                alert.setAlertType(AlertType.ERROR);
                alert.setHeaderText("What a shame");
                alert.setContentText("You almost got there");
                alert.show();
                closeCounter=0;
                break;
            case 4:
                alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("really?");
                alert.setHeaderText("Are you sure you want to quit?");
                alert.setResizable(false);
                alert.setContentText("Plz don't go");
                if(alert.showAndWait().get()==ButtonType.OK){
                    closeCounter++;
                    return;
                }
                alert.setAlertType(AlertType.ERROR);
                alert.setHeaderText("Thank you");
                alert.setContentText("You're so nice not to quit");
                alert.show();
                closeCounter=0;
                break;
            case 5:
                alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("really?");
                alert.setHeaderText("Are you sure you want to quit?");
                alert.setResizable(false);
                alert.setContentText("Click cancel to quit");
                if(alert.showAndWait().get()==ButtonType.OK){
                    closeCounter=0;
                    return;
                }
                alert.setAlertType(AlertType.ERROR);
                alert.setHeaderText("I never lie, try more times");
                alert.setContentText("Almost there");
                alert.show();
                closeCounter++;
                break;
            case 6:
                alert=new Alert(AlertType.CONFIRMATION, "",ButtonType.OK, ButtonType.CLOSE);
                canclose=true;
                alert.setTitle("seriously?");
                alert.setHeaderText("Don't go plzzzz?");
                alert.setResizable(false);
                alert.setContentText("Don't close this window, this will immediately close the game");
                ButtonType answer=alert.showAndWait().get();
                if(answer==ButtonType.OK){
                    canclose=false;
                    closeCounter=0;
                }
                if(canclose){stage.close();return;}
                alert.setAlertType(AlertType.INFORMATION);
                alert.setHeaderText("What a shame");
                alert.setContentText("Start it over again");
                alert.showAndWait();
        }
        
    }
}
