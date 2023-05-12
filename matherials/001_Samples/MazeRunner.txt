import java.util.Scanner;

public class MazeRunner {
    private static String[][] maze;
    private static int[] startPosition;
    private static int[] exitPosition;
    private static String pacman;
    private static final String enterDirection = "Enter the direction (for example: left): ";
    private static final String enterQuantitySteps = "Enter the quantity of steps: ";
    private static final String wrongInput = "Wrong action input! Try again";
    private static final String youWin = "**********You win!!!**********";

    public static void main(String[] args) {
        startPosition = new int[]{8, 1};
        exitPosition = new int[]{0, 8};
        getGameStatics(startPosition);

        String strCommand;
        Scanner reader = new Scanner(System.in);
        System.out.print(enterDirection);
        while (!(strCommand = reader.nextLine()).equals("exit")) {
            int steps;
            switch (strCommand) {

                case "left":
                    System.out.print(enterQuantitySteps);
                    try {
                        steps = Integer.parseInt(reader.nextLine());
                        moveLeft(maze, startPosition, steps);
                        System.out.print(enterDirection);
                    } catch (NumberFormatException e) {
                        System.out.println(wrongInput);
                        System.out.println();
                        System.out.print(enterDirection);
                    }
                    break;

                case "right":
                    System.out.print(enterQuantitySteps);
                    try {
                        steps = Integer.parseInt(reader.nextLine());
                        moveRight(maze, startPosition, steps);
                        System.out.print(enterDirection);
                    } catch (NumberFormatException e) {
                        System.out.println(wrongInput);
                        System.out.println();
                        System.out.print(enterDirection);
                    }
                    break;

                case "up":
                    System.out.print(enterQuantitySteps);
                    try {
                        steps = Integer.parseInt(reader.nextLine());
                        moveUp(maze, startPosition, steps);
                        System.out.print(enterDirection);
                    } catch (NumberFormatException e) {
                        System.out.println(wrongInput);
                        System.out.println();
                        System.out.print(enterDirection);
                    }
                    break;

                case "down":
                    System.out.print(enterQuantitySteps);
                    try {
                        steps = Integer.parseInt(reader.nextLine());
                        moveDown(maze, startPosition, steps);
                        System.out.print(enterDirection);
                    } catch (NumberFormatException e) {
                        System.out.println(wrongInput);
                        System.out.println();
                        System.out.print(enterDirection);
                    }
                    break;

                default:
                    System.out.println(wrongInput);
                    System.out.println();
                    System.out.print(enterDirection);
                    break;
            }
        }
        reader.close();
    }

    private static void getGameStatics(int[] startPosition) {
        pacman = "C";
        maze = new String[][]{
                {"*", "*", "*", "*", "*", "*", "*", "*", ".", "*"},
                {"*", ".", "*", ".", ".", ".", ".", ".", ".", "*"},
                {"*", ".", ".", ".", "*", ".", "*", "*", "*", "*"},
                {"*", ".", "*", "*", "*", ".", ".", ".", ".", "*"},
                {"*", ".", "*", ".", ".", ".", "*", "*", ".", "*"},
                {"*", "*", "*", ".", "*", ".", "*", "*", "*", "*"},
                {"*", ".", ".", ".", "*", ".", ".", ".", ".", "*"},
                {"*", "*", "*", "*", "*", "*", "*", "*", ".", "*"},
                {"*", ".", ".", ".", ".", ".", ".", ".", ".", "*"},
                {"*", "*", "*", "*", "*", "*", "*", "*", "*", "*"}
        };
        maze[startPosition[0]][startPosition[1]] = pacman;
        run(maze);
    }

    private static void moveRight(String[][] maze, int[] position, int steps) {
        String nextPosition = "";
        for (int i = 1; i <= steps && i < maze[position[0]].length; i++) {
            try {
                nextPosition = maze[position[0]][position[1] + i];
            } catch (IndexOutOfBoundsException e) {
                clearScreen();
                System.out.println(youWin);
                System.exit(1);
            }
            if(nextPosition.equals("*")) {
                break;
            } else {
                maze[position[0]][position[1] + i - 1] = ".";
                maze[position[0]][position[1] + i] = pacman;
                startPosition = new int[]{position[0], position[1] + i};
                run(maze);
            }
        }
    }

    private static void moveLeft(String[][] maze, int[] position, int steps) {
        String nextPosition = "";
        for (int i = 1; i <= steps && i < maze[position[0]].length; i++) {
            try {
                nextPosition = maze[position[0]][position[1] - i];
            } catch (IndexOutOfBoundsException e) {
                clearScreen();
                System.out.println(youWin);
                System.exit(1);
            }
            if(nextPosition.equals("*")) {
                break;
            } else {
                maze[position[0]][position[1] - i + 1] = ".";
                maze[position[0]][position[1] - i] = pacman;
                startPosition = new int[]{position[0], position[1] - i};
                run(maze);
            }
        }
    }

    private static void moveUp(String[][] maze, int[] position, int steps) {
        String nextPosition = "";
        for (int i = 1; i <= steps && i < maze.length; i++) {
            try {
                nextPosition = maze[position[0] - i][position[1]];
            } catch (IndexOutOfBoundsException e) {
                clearScreen();
                System.out.println(youWin);
                System.exit(1);
            }
            if(nextPosition.equals("*")) {
                break;
            } else {
                maze[position[0] - i +1][position[1]] = ".";
                maze[position[0] - i][position[1]] = pacman;
                startPosition = new int[]{position[0] - i, position[1]};
                run(maze);
            }
        }
    }

    private static void moveDown(String[][] maze, int[] position, int steps) {
        String nextPosition = "";
        for (int i = 1; i <= steps && i < maze.length; i++) {
            try {
                nextPosition = maze[position[0] + i][position[1]];
            } catch (IndexOutOfBoundsException e) {
                clearScreen();
                System.out.println(youWin);
                System.exit(1);
            }
            if(nextPosition.equals("*")) {
                break;
            } else {
                maze[position[0] + i - 1][position[1]] = ".";
                maze[position[0] + i][position[1]] = pacman;
                startPosition = new int[]{position[0] + 1, position[1]};
                run(maze);
            }
        }
    }

    private static void run(String[][] maze) {
        clearScreen();
        for(int i = 0; i < maze.length; i++) {
            for(int j = 0; j < maze[i].length; j++) {
                System.out.print(maze[i][j]);
            }
            System.out.println();
        }
        System.out.println();
        System.out.println("To finish game type: \"exit\'");
        System.out.println();
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
}