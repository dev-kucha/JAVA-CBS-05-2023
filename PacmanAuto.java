import java.util.Arrays;

public class PacmanAuto {
    private static String[][] maze;
    private static int[] currentPosition;
    private static String pacman;
    private static int dots;
    private static faceDirection direction;
    private static int[] nextPosition;
    private static final String youWin = "**********You win!!!**********";

    private enum faceDirection {
        LEFT, RIGHT, UP, DOWN;
    }

    public static void main(String[] args) {

        currentPosition = new int[]{18, 1};
        nextPosition = new int[]{17, 1};
        getGameStatics();

        //!!!

        //Напишіть реалізацію свого алгоритму в цьому місці
        while(dots > 0) {
            turnRight();
            if (!isWall()) {
                step();
            } else {
                while(isWall()){
                    turnLeft();
                }  step();
            }
            System.out.println(dots);
        }

      System.out.println(youWin);
    }

    private static void getGameStatics() {
        pacman = "@";
        maze = new String[][]{
                {"*", "*", "*", "*", "*", "*", "*", "*", "*", "*", "*", "*", "*", "*", "*", "*", "*", "*", "*", "*"},
                {"*", "*", ".", ".", ".", ".", ".", "*", ".", ".", ".", "*", "*", "*", "*", ".", ".", ".", "*", "*"},
                {"*", "*", ".", "*", "*", "*", ".", "*", ".", "*", ".", ".", ".", ".", "*", ".", "*", ".", ".", "*"},
                {"*", "*", ".", ".", ".", "*", ".", ".", ".", "*", ".", ".", "*", ".", "*", ".", "*", ".", "*", "*"},
                {"*", ".", ".", "*", ".", "*", "*", "*", "*", "*", "*", ".", "*", ".", ".", ".", "*", ".", ".", "*"},
                {"*", "*", "*", "*", ".", "*", "*", ".", ".", ".", "*", ".", "*", "*", "*", "*", "*", "*", ".", "*"},
                {"*", "*", ".", ".", ".", "*", ".", ".", "*", ".", "*", ".", ".", ".", ".", ".", ".", ".", "*", "*"},
                {"*", ".", ".", "*", "*", "*", "*", ".", "*", ".", "*", "*", "*", "*", "*", "*", "*", "*", "*", "*"},
                {"*", ".", "*", "*", ".", ".", ".", ".", "*", ".", ".", ".", ".", ".", "*", "*", "*", "*", "*", "*"},
                {"*", ".", "*", ".", ".", "*", "*", "*", "*", "*", "*", "*", "*", ".", "*", ".", ".", ".", ".", "*"},
                {"*", ".", ".", ".", "*", ".", ".", ".", "*", ".", ".", ".", "*", ".", "*", ".", "*", "*", ".", "*"},
                {"*", "*", "*", "*", "*", ".", "*", ".", "*", ".", "*", ".", "*", ".", ".", ".", ".", "*", ".", "*"},
                {"*", ".", ".", ".", ".", ".", "*", ".", "*", ".", "*", ".", "*", "*", "*", "*", "*", "*", ".", "*"},
                {"*", ".", "*", "*", "*", "*", "*", ".", "*", ".", "*", ".", ".", ".", "*", "*", "*", "*", ".", "*"},
                {"*", ".", ".", ".", ".", "*", ".", ".", "*", ".", "*", "*", "*", ".", ".", ".", ".", ".", ".", "*"},
                {"*", "*", ".", "*", ".", "*", ".", "*", "*", ".", ".", ".", "*", ".", "*", "*", ".", "*", "*", "*"},
                {"*", ".", ".", "*", ".", "*", ".", ".", "*", "*", "*", ".", "*", ".", "*", "*", ".", ".", ".", "*"},
                {"*", "*", "*", "*", ".", "*", "*", ".", ".", ".", "*", ".", "*", ".", "*", "*", "*", "*", ".", "*"},
                {"*", ".", ".", ".", ".", "*", ".", ".", "*", ".", ".", ".", "*", ".", ".", ".", ".", ".", ".", "*"},
                {"*", "*", "*", "*", "*", "*", "*", "*", "*", "*", "*", "*", "*", "*", "*", "*", "*", "*", "*", "*"},

        };

        maze[currentPosition[0]][currentPosition[1]] = pacman;
        for (String[] strings : maze) {
            for (String string : strings) {
                if (string.equals(".")) {
                    dots++;
                }
            }
        }
        direction = faceDirection.UP;
        run();
    }

    private static void turnRight() {
        if(direction == faceDirection.UP) {
            nextPosition = new int[]{currentPosition[0], currentPosition[1] + 1};
            direction = faceDirection.RIGHT;
        } else if (direction == faceDirection.DOWN) {
            nextPosition = new int[]{currentPosition[0], currentPosition[1] - 1};
            direction = faceDirection.LEFT;
        } else if (direction == faceDirection.LEFT) {
            nextPosition = new int[]{currentPosition[0] - 1, currentPosition[1]};
            direction = faceDirection.UP;
        } else if (direction == faceDirection.RIGHT) {
            nextPosition = new int[]{currentPosition[0] + 1, currentPosition[1]};
            direction = faceDirection.DOWN;
        }

    }

    private static void turnLeft() {
        if(direction == faceDirection.UP) {
            nextPosition = new int[]{currentPosition[0], currentPosition[1] - 1};
            direction = faceDirection.LEFT;
        } else if (direction == faceDirection.DOWN) {
            nextPosition = new int[]{currentPosition[0], currentPosition[1] + 1};
            direction = faceDirection.RIGHT;
        } else if (direction == faceDirection.LEFT) {
            nextPosition = new int[]{currentPosition[0] + 1, currentPosition[1]};
            direction = faceDirection.DOWN;
        } else if (direction == faceDirection.RIGHT) {
            nextPosition = new int[]{currentPosition[0] - 1, currentPosition[1]};
            direction = faceDirection.UP;
        }
    }

    private static void step() {
        maze[currentPosition[0]][currentPosition[1]] = " ";
        if(maze[nextPosition[0]][nextPosition[1]].equals(".")) {
            dots--;
        }
        maze[nextPosition[0]][nextPosition[1]] = pacman;
        currentPosition = nextPosition;
        if(direction == faceDirection.UP) {
            nextPosition = new int[]{currentPosition[0] - 1, currentPosition[1]};
        } else if (direction == faceDirection.DOWN) {
            nextPosition = new int[]{currentPosition[0] + 1, currentPosition[1]};
        } else if (direction == faceDirection.LEFT) {
            nextPosition = new int[]{currentPosition[0], currentPosition[1] - 1};
        } else if (direction == faceDirection.RIGHT) {
            nextPosition = new int[]{currentPosition[0], currentPosition[1] + 1};
        }
        run();
    }

    private static void run() {
        clearScreen();
        for(int i = 0; i < maze.length; i++) {
            for(int j = 0; j < maze[i].length; j++) {
                System.out.print(maze[i][j]);
            }
            System.out.println();
        }
    }

    private static void clearScreen() {
        try{
            String operatingSystem = System.getProperty("os.name");
            if(operatingSystem.contains("Windows")){
                ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "cls");
                Process startProcess = pb.inheritIO().start();
                startProcess.waitFor();
            } else {
                ProcessBuilder pb = new ProcessBuilder("clear");
                Process startProcess = pb.inheritIO().start();
                startProcess.waitFor();
            }
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }

    private static boolean isWall() {
        return maze[nextPosition[0]][nextPosition[1]].equals("*");
    }
}