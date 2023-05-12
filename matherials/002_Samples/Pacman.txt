import java.util.Scanner;

public class Pacman {
    private static String[][] maze;
    private static int[] currentPosition;
    private static int[] nextPosition;
    private static String pacman;
    private static int dots;
    private static final String enterDirection = "Enter the direction letter and quantity of steps (for example: r4): ";
    private static final String wrongInput = "Wrong action input! Try again";
    private static final String youWin = "**********You win!!!**********";

    public static void main(String[] args) {

        currentPosition = new int[]{18, 1};
        getGameStatics();

        String strCommand;
        Scanner reader = new Scanner(System.in);
        System.out.print(enterDirection);
        while (!(strCommand = reader.nextLine()).equals("exit")) {
            int steps;
            switch (strCommand.substring(0, 1)) {

                case "l":
                    try {
                        steps = Integer.parseInt(strCommand.substring(1));
                        moveLeft(maze, currentPosition, steps);
                        System.out.print(enterDirection);
                    } catch (NumberFormatException e) {
                        System.out.println(wrongInput);
                        System.out.println();
                        System.out.print(enterDirection);
                    }
                    break;

                case "r":
                    try {
                        steps = Integer.parseInt(strCommand.substring(1));
                        moveRight(steps);
                        System.out.print(enterDirection);
                    } catch (NumberFormatException e) {
                        System.out.println(wrongInput);
                        System.out.println();
                        System.out.print(enterDirection);
                    }
                    break;

                case "u":
                    try {
                        steps = Integer.parseInt(strCommand.substring(1));
                        moveUp(maze, currentPosition, steps);
                        System.out.print(enterDirection);
                    } catch (NumberFormatException e) {
                        System.out.println(wrongInput);
                        System.out.println();
                        System.out.print(enterDirection);
                    }
                    break;

                case "d":
                    try {
                        steps = Integer.parseInt(strCommand.substring(1));
                        moveDown(maze, currentPosition, steps);
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
                {"*", ".", ".", "*", ".", "*", ".", ".", "*", "*", "*", ".", "*", ".", ".", ".", ".", ".", ".", "*"},
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
        run(maze);
    }

    private static void moveRight(int steps) {
        for (int i = 0; i < steps && i < maze[currentPosition[0]].length; i++) {
            nextPosition = new int[]{currentPosition[0], currentPosition[1] + 1};
            if(maze[nextPosition[0]][nextPosition[1]].equals("*")) {
                break;
            } else {
                maze[currentPosition[0]][currentPosition[1]] = " ";
                if(maze[nextPosition[0]][nextPosition[1]].equals(".")) {
                    dots--;
                }
                maze[nextPosition[0]][nextPosition[1]] = pacman;
                currentPosition = nextPosition;
                run(maze);
                if(dots == 0) {
                    clearScreen();
                    System.out.println(youWin);
                    System.exit(1);
                }
            }
        }
    }

    private static void moveLeft(String[][] maze, int[] position, int steps) {
        for (int i = 0; i < steps && i < maze[currentPosition[0]].length; i++) {
            nextPosition = new int[]{currentPosition[0], currentPosition[1] - 1};
            if(maze[nextPosition[0]][nextPosition[1]].equals("*")) {
                break;
            } else {
                maze[currentPosition[0]][currentPosition[1]] = " ";
                if(maze[nextPosition[0]][nextPosition[1]].equals(".")) {
                    dots--;
                }
                maze[nextPosition[0]][nextPosition[1]] = pacman;
                currentPosition = nextPosition;
                run(maze);
                if(dots == 0) {
                    clearScreen();
                    System.out.println(youWin);
                    System.exit(1);
                }
            }
        }
    }

    private static void moveUp(String[][] maze, int[] position, int steps) {
        for (int i = 0; i < steps && i < maze[currentPosition[0]].length; i++) {
            nextPosition = new int[]{currentPosition[0] - 1, currentPosition[1]};
            if(maze[nextPosition[0]][nextPosition[1]].equals("*")) {
                break;
            } else {
                maze[currentPosition[0]][currentPosition[1]] = " ";
                if(maze[nextPosition[0]][nextPosition[1]].equals(".")) {
                    dots--;
                }
                maze[nextPosition[0]][nextPosition[1]] = pacman;
                currentPosition = nextPosition;
                run(maze);
                if(dots == 0) {
                    clearScreen();
                    System.out.println(youWin);
                    System.exit(1);
                }
            }
        }
    }

    private static void moveDown(String[][] maze, int[] position, int steps) {
        for (int i = 0; i < steps && i < maze[currentPosition[0]].length; i++) {
            nextPosition = new int[]{currentPosition[0] + 1, currentPosition[1]};
            if(maze[nextPosition[0]][nextPosition[1]].equals("*")) {
                break;
            } else {
                maze[currentPosition[0]][currentPosition[1]] = " ";
                if(maze[nextPosition[0]][nextPosition[1]].equals(".")) {
                    dots--;
                }
                maze[nextPosition[0]][nextPosition[1]] = pacman;
                currentPosition = nextPosition;
                run(maze);
                if(dots == 0) {
                    clearScreen();
                    System.out.println(youWin);
                    System.exit(1);
                }
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
