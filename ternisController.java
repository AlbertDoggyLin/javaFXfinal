package finalProject;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class ternisController{
    @FXML
    private AnchorPane pane;
    @FXML
    private Label secondCounter;
    public double difficulty;
    private KeyCode currentKeyCode=null;
    private double duration=1;
    private double frequency;
    private int changeCounter=0;
    private int counter=0;
    private int timer=0;
    private Timeline time;
    private Timeline gameTimeline;
    private String[] A=new String[5];
    private int[][][] position=new int[10][20][2];
    private Color[][] color=new Color[10][20];
    private Rectangle[][] element=new Rectangle[10][20];
    private Color bgc;
    private tetris currentTetris;
    ObservableList<Node> children;
    public void Initialize(Scene scene) {
        difficulty=Math.sqrt(difficulty);
        duration=(11-difficulty)*50;
        frequency=(1 + difficulty)*2;
        A[0]="←";A[1]="→";A[2]="⇊";A[3]="↓";A[4]="↺";
        bgc=Color.rgb(0, 31, 62);
        secondCounter.setText("You've waist "+this.timer+
            " second A:"+A[0]+" D:"+A[1]+" Space:"+A[2]+" S:"+A[3]+" W:"+A[4]);
        children=pane.getChildren();
        for(int i=0;i<=20;i++){
            Line line=new Line(50,50+20*i,250,50+20*i);
            line.setStroke(Color.WHITE);
            line.setStrokeWidth(0.1);
            children.add(line);
        }
        for(int i=0;i<=10;i++){
            Line line=new Line(50+20*i,50,50+20*i,450);
            line.setStrokeWidth(0.1);
            line.setStroke(Color.WHITE);
            children.add(line);
        }
        for(int i=0;i<10;i++){
            for(int j=0;j<20;j++){
                position[i][j][0]=50+20*i;
                position[i][j][1]=50+20*j;
                color[i][j]=bgc;
            }
        }
        gameTimeline=new Timeline(new KeyFrame(Duration.millis(1),e->setNextFrame()));
        time=new Timeline(
            new KeyFrame(Duration.millis(1000),e->{
                this.timer++;
                if(this.timer%20==0){
                    frequency*=0.9;
                    duration*=0.9;
                }
                changeCounter++;
                secondCounter.setText("You've waist "+this.timer+
                    " second A:"+A[0]+" D:"+A[1]+" Space:"+A[2]+" S:"+A[3]+" W:"+A[4]);
                if(changeCounter>=frequency){
                    changeCounter=0;
                    Random random=new Random();
                    for(int i=0;i<5;i++){
                        String temp;
                        int t=random.nextInt(5);
                        temp=A[t];
                        A[t]=A[0];
                        A[0]=temp;
                    }
                }
            })
        );
        gameTimeline.setCycleCount(Timeline.INDEFINITE);
        time.setCycleCount(Timeline.INDEFINITE);
        gameTimeline.play();
        time.play();
        scene.setOnKeyPressed(e->currentKeyCode=e.getCode());
        Random random=new Random();
        currentTetris=new tetris(random.nextInt(7));
        for(int[] cube:currentTetris.cubes){
            if(cube[1]<0)continue;
            Rectangle temp=new Rectangle(position[cube[0]][cube[1]][0], position[cube[0]][cube[1]][1], 20, 20);
            temp.setFill(currentTetris.cubeColor);
            children.add(temp);
            element[cube[0]][cube[1]]=temp;
        }
    }
    private void setNextFrame(){
        counter++;
        if(counter>=duration){
            Down();
        }
        if(currentKeyCode!=null)
        switch(currentKeyCode){
            case A:
            case LEFT:
                ternis(0);
                currentKeyCode=null;
                break;
            case D:
            case RIGHT:
                ternis(1);
                currentKeyCode=null;
                break;
            case S:
            case DOWN:
                ternis(3);
                currentKeyCode=null;
                break;
            case W:
            case UP:
                ternis(4);
                currentKeyCode=null;
                break;
            case SPACE:
                currentKeyCode=null;
                ternis(2);
                break;
            default:
                currentKeyCode=null;
                break;
        }
    }
    private void ternis(int cmd) {
        if(A[cmd].equals("←")){
            left();
        }
        else if(A[cmd].equals("→")){
            right();
        }
        else if(A[cmd].equals("⇊")){
            space();
        }
        else if(A[cmd].equals("↓")){
            down();
        }
        else if(A[cmd].equals("↺")){
            rotate();
        }
    }
    private boolean rotate() {
        for(int[] cube:currentTetris.cubes){
            if(cube[1]>=0){
                children.remove(element[cube[0]][cube[1]]);
                color[cube[0]][cube[1]]=bgc;
            }
        }
        tetris checkTetris=new tetris(0);
        for(int i=0;i<4;i++){
            for(int j=0;j<2;j++){
                checkTetris.cubes[i][j]=currentTetris.cubes[i][j];
            }
        }
        checkTetris.cubeColor=currentTetris.cubeColor;
        checkTetris.centerCube=currentTetris.centerCube;
        for(int[] cube:checkTetris.cubes){
            int x=checkTetris.cubes[checkTetris.centerCube][0]+checkTetris.cubes[checkTetris.centerCube][1]-cube[1];
            int y=checkTetris.cubes[checkTetris.centerCube][1]+checkTetris.cubes[checkTetris.centerCube][0]-cube[0];
            cube[0]=x;cube[1]=y;
        }
        boolean check=true;int x=0,y=0;
        for(int[] cube:checkTetris.cubes){
            if(cube[0]<0||cube[0]>9||cube[1]>19){
                check=false;
                break;
            }
            if(cube[1]<0)continue;
            if(color[cube[0]][cube[1]]!=bgc){
                check=false;
                break;
            }
        }
        if(!check)
            for(int i=-1;i<=1;i++){
                for(int j=-1;j<=1;j++){
                    check=true;x=i;y=j;
                    for(int[] cube:checkTetris.cubes){
                        if(cube[0]+i<0||cube[0]+i>9||cube[1]+j>19){
                            check=false;
                            break;
                        }
                        if(cube[1]+j<0)continue;
                        if(color[cube[0]+i][cube[1]+j]!=bgc){
                            check=false;
                            break;
                        }
                    }
                    if(check)break;
                }
                if(check)break;
            }
        if(!check){
            for(int[] cube:currentTetris.cubes){
                if(cube[1]<0)continue;
                Rectangle temp=new Rectangle(position[cube[0]][cube[1]][0], position[cube[0]][cube[1]][1], 20, 20);
                temp.setFill(currentTetris.cubeColor);
                children.add(temp);
                element[cube[0]][cube[1]]=temp;
                color[cube[0]][cube[1]]=currentTetris.cubeColor;
            }
            return false;
        }
        for(int i=0;i<4;i++){
            currentTetris.cubes[i][0]=checkTetris.cubes[i][0]+x;
            currentTetris.cubes[i][1]=checkTetris.cubes[i][1]+y;
        }
        for(int[] cube:currentTetris.cubes){
            if(cube[1]<0)continue;
            Rectangle temp=new Rectangle(position[cube[0]][cube[1]][0], position[cube[0]][cube[1]][1], 20, 20);
            temp.setFill(currentTetris.cubeColor);
            children.add(temp);
            element[cube[0]][cube[1]]=temp;
            color[cube[0]][cube[1]]=currentTetris.cubeColor;
        }
        return true;
    }
    private void space() {
        while(down());
    }
    private boolean right() {
        List<int[]> checkList=new ArrayList<>();
        for(int[] i:currentTetris.cubes){
            boolean check=true;
            for(int[] j:currentTetris.cubes){

                if(i[1]==j[1]&&i[0]+1==j[0]){
                    check=false;
                }
            }
            if(check)checkList.add(i);
        }
        for(int[] checkedCube:checkList){
            if(checkedCube[1]<0 && checkedCube[0]!=9)continue;
            if(checkedCube[0]==9||color[checkedCube[0]+1][checkedCube[1]]!=bgc)
                return false;
        }
        for(int[] cube:currentTetris.cubes){
            if(cube[1]>=0){
                children.remove(element[cube[0]][cube[1]]);
                color[cube[0]][cube[1]]=bgc;
            }
            cube[0]+=1;
        }
        for(int[] cube:currentTetris.cubes){
            if(cube[1]<0)continue;
            Rectangle temp=new Rectangle(position[cube[0]][cube[1]][0], position[cube[0]][cube[1]][1], 20, 20);
            temp.setFill(currentTetris.cubeColor);
            children.add(temp);
            element[cube[0]][cube[1]]=temp;
            color[cube[0]][cube[1]]=currentTetris.cubeColor;
        }
        return true;
    }
    private boolean left() {
        List<int[]> checkList=new ArrayList<>();
        for(int[] i:currentTetris.cubes){
            boolean check=true;
            for(int[] j:currentTetris.cubes){
                if(i[1]==j[1]&&i[0]==j[0]+1){
                    check=false;
                }
            }
            if(check)checkList.add(i);
        }
        for(int[] checkedCube:checkList){
            if(checkedCube[1]<0 && checkedCube[0]!=0)continue;
            if(checkedCube[0]==0||color[checkedCube[0]-1][checkedCube[1]]!=bgc)
                return false;
        }
        for(int[] cube:currentTetris.cubes){
            if(cube[1]>=0){
                children.remove(element[cube[0]][cube[1]]);
                color[cube[0]][cube[1]]=bgc;
            }
            cube[0]-=1;
        }
        for(int[] cube:currentTetris.cubes){
            if(cube[1]<0)continue;
            Rectangle temp=new Rectangle(position[cube[0]][cube[1]][0], position[cube[0]][cube[1]][1], 20, 20);
            temp.setFill(currentTetris.cubeColor);
            children.add(temp);
            color[cube[0]][cube[1]]=currentTetris.cubeColor;
            element[cube[0]][cube[1]]=temp;
        }
        return true;
    }
    private boolean down() {
        List<int[]> checkList=new ArrayList<>();
        for(int[] i:currentTetris.cubes){
            boolean check=true;
            for(int[] j:currentTetris.cubes){
                if(i[0]==j[0]&&i[1]+1==j[1]){
                    check=false;
                }
            }
            if(check)checkList.add(i);
        }
        for(int[] checkedCube:checkList){
            if(checkedCube[1]<0)continue;
            if(checkedCube[1]==19||color[checkedCube[0]][checkedCube[1]+1]!=bgc)
                return false;
        }
        for(int[] cube:currentTetris.cubes){
            if(cube[1]>=0){
                children.remove(element[cube[0]][cube[1]]);
                color[cube[0]][cube[1]]=bgc;
            }
            cube[1]+=1;
        }
        for(int[] cube:currentTetris.cubes){
            if(cube[1]<0)continue;
            Rectangle temp=new Rectangle(position[cube[0]][cube[1]][0], position[cube[0]][cube[1]][1], 20, 20);
            temp.setFill(currentTetris.cubeColor);
            children.add(temp);
            element[cube[0]][cube[1]]=temp;
            color[cube[0]][cube[1]]=currentTetris.cubeColor;
        }
        return true;
    }
    private void Down() {
        counter=0;
        if(!down()){
            boolean check=false;
            for(int[] cube:currentTetris.cubes){
                if(cube[1]<0){
                    time.stop();
                    gameTimeline.stop();
                    check=true;
                }
            }
            if(!check){
                Random random=new Random();
                currentTetris=new tetris(random.nextInt(7));
                for(int[] c:currentTetris.cubes){
                    if(c[1]<0)continue;
                    Rectangle temp=new Rectangle(position[c[0]][c[1]][0], position[c[0]][c[1]][1], 20, 20);
                    temp.setFill(currentTetris.cubeColor);
                    children.add(temp);
                    element[c[0]][c[1]]=temp;
                }
            }
        }
    }
}
