package finalProject;

import javafx.scene.paint.Color;

public class tetris {
    public int[][] cubes=new int[4][2];
    public Color cubeColor;
    public int centerCube;
    public tetris(int id){
        switch(id){
            case 0:
                cubes[0][0]=4;
                cubes[0][1]=0;
                cubes[1][0]=5;
                cubes[1][1]=0;
                cubes[2][0]=4;
                cubes[2][1]=-1;
                cubes[3][0]=5;
                cubes[3][1]=-1;
                cubeColor=Color.YELLOW;
                centerCube=1;
                break;
            case 1:
                cubes[0][0]=4;
                cubes[0][1]=0;
                cubes[1][0]=4;
                cubes[1][1]=-1;
                cubes[2][0]=4;
                cubes[2][1]=-2;
                cubes[3][0]=4;
                cubes[3][1]=-3;
                cubeColor=Color.SKYBLUE;
                centerCube=1;
                break;
            case 2:
                cubes[0][0]=4;
                cubes[0][1]=0;
                cubes[1][0]=5;
                cubes[1][1]=0;
                cubes[2][0]=4;
                cubes[2][1]=-1;
                cubes[3][0]=4;
                cubes[3][1]=-2;
                cubeColor=Color.ORANGE;
                centerCube=2;
                break;
            case 3:
                cubes[0][0]=4;
                cubes[0][1]=0;
                cubes[1][0]=5;
                cubes[1][1]=0;
                cubes[2][0]=5;
                cubes[2][1]=-1;
                cubes[3][0]=5;
                cubes[3][1]=-2;
                cubeColor=Color.BLUE;
                centerCube=2;
                break;
            case 4:
                cubes[0][0]=4;
                cubes[0][1]=0;
                cubes[1][0]=5;
                cubes[1][1]=-1;
                cubes[2][0]=4;
                cubes[2][1]=-1;
                cubes[3][0]=3;
                cubes[3][1]=-1;
                cubeColor=Color.PURPLE;
                centerCube=2;
                break;
            case 5:
                cubes[0][0]=4;
                cubes[0][1]=0;
                cubes[1][0]=4;
                cubes[1][1]=-1;
                cubes[2][0]=5;
                cubes[2][1]=-1;
                cubes[3][0]=5;
                cubes[3][1]=-2;
                cubeColor=Color.RED;
                centerCube=1;
                break;
            case 6:
                cubes[0][0]=4;
                cubes[0][1]=0;
                cubes[1][0]=4;
                cubes[1][1]=-1;
                cubes[2][0]=3;
                cubes[2][1]=-1;
                cubes[3][0]=3;
                cubes[3][1]=-2;
                cubeColor=Color.GREEN;
                centerCube=1;
                break;
        }
    }
}
