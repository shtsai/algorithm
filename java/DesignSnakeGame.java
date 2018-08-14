/*
    Design a Snake game that is played on a device with screen size = width x height. Play the game online if you are not familiar with the game.
    The snake is initially positioned at the top left corner (0,0) with length = 1 unit.
    You are given a list of food's positions in row-column order. When a snake eats the food, its length and the game's score both increase by 1.
    Each food appears one by one on the screen. For example, the second food will not appear until the first food was eaten by the snake.
    When a food does appear on the screen, it is guaranteed that it will not appear on a block occupied by the snake.

    Example:
    Given width = 3, height = 2, and food = [[1,2],[0,1]].
    Snake snake = new Snake(width, height, food);

    Initially the snake appears at position (0,0) and the food at (1,2).
    |S| | |
    | | |F|
    snake.move("R"); -> Returns 0

    | |S| |
    | | |F|
    snake.move("D"); -> Returns 0

    | | | |
    | |S|F|
    snake.move("R"); -> Returns 1 (Snake eats the first food and right after that, the second food appears at (0,1) )

    | |F| |
    | |S|S|
    snake.move("U"); -> Returns 1

    | |F|S|
    | | |S|
    snake.move("L"); -> Returns 2 (Snake eats the second food)

    | |S|S|
    | | |S|
    snake.move("U"); -> Returns -1 (Game over because snake collides with border)
 */

/**
 * Your SnakeGame object will be instantiated and called as such:
 * SnakeGame obj = new SnakeGame(width, height, food);
 * int param_1 = obj.move(direction);
 */

// Solution 2: Deque + HashSet
// Quick check for eating body case
// Time: O(1)
// Space: O(n)
// 08/13/2018
class SnakeGame {
    int width;
    int height;
    int[][] food;
    int foodIndex;
    Deque<int[]> body;
    Set<Integer> set;
    /** Initialize your data structure here.
        @param width - screen width
        @param height - screen height
        @param food - A list of food positions
        E.g food = [[1,1], [1,0]] means the first food is positioned at [1,1], the second is at [1,0]. */
    public SnakeGame(int width, int height, int[][] food) {
        this.width = width;
        this.height = height;
        this.food = food;
        this.foodIndex = 0;
        body = new LinkedList<int[]>();
        body.offerFirst(new int[]{0, 0});
        set = new HashSet<Integer>();
        set.add(0);
    }

    /** Moves the snake.
        @param direction - 'U' = Up, 'L' = Left, 'R' = Right, 'D' = Down
        @return The game's score after the move. Return -1 if game over.
        Game over when snake crosses the screen boundary or bites its body. */
    public int move(String direction) {
        int[] cur = body.peekFirst();
        int[] newPos = new int[] {cur[0], cur[1]};
        switch (direction) {
            case "U":
                newPos[0]--;
                break;
            case "L":
                newPos[1]--;
                break;
            case "R":
                newPos[1]++;
                break;
            case "D":
                newPos[0]++;
                break;
        }
        int[] curFood = foodIndex < food.length ? food[foodIndex] : new int[]{-1, -1};
        if (newPos[0] == curFood[0] && newPos[1] == curFood[1]) {   // capture food
            foodIndex++;
        } else {
            int[] remove = body.pollLast();
            set.remove(remove[0] * width + remove[1]);
        }

        if (newPos[0] < 0 || newPos[0] >= height || newPos[1] < 0 || newPos[1] >= width || set.contains(newPos[0] * width + newPos[1])) {
            return -1;
        }

        body.offerFirst(newPos);
        set.add(newPos[0] * width + newPos[1]);
        return body.size() - 1;
    }
}


// Solution 1: Deque
// Time: O(n) - n: length of Snake
// Space: O(n)
// 08/13/2018

class SnakeGame {
    int width;
    int height;
    int[][] food;
    int foodIndex;
    Deque<int[]> body;
    /** Initialize your data structure here.
        @param width - screen width
        @param height - screen height
        @param food - A list of food positions
        E.g food = [[1,1], [1,0]] means the first food is positioned at [1,1], the second is at [1,0]. */
    public SnakeGame(int width, int height, int[][] food) {
        this.width = width;
        this.height = height;
        this.food = food;
        this.foodIndex = 0;
        body = new LinkedList<int[]>();
        body.offerFirst(new int[]{0, 0});
    }

    /** Moves the snake.
        @param direction - 'U' = Up, 'L' = Left, 'R' = Right, 'D' = Down
        @return The game's score after the move. Return -1 if game over.
        Game over when snake crosses the screen boundary or bites its body. */
    public int move(String direction) {
        int[] cur = body.peekFirst();
        int[] newPos = new int[] {cur[0], cur[1]};
        switch (direction) {
            case "U":
                newPos[0]--;
                break;
            case "L":
                newPos[1]--;
                break;
            case "R":
                newPos[1]++;
                break;
            case "D":
                newPos[0]++;
                break;
        }
        if (newPos[0] < 0 || newPos[0] >= height || newPos[1] < 0 || newPos[1] >= width) {
            return -1;
        }
        int[] curFood = foodIndex < food.length ? food[foodIndex] : new int[]{-1, -1};
        if (newPos[0] == curFood[0] && newPos[1] == curFood[1]) {   // capture food
            foodIndex++;
        } else {
            body.pollLast();
        }
        for (int[] b : body) {
            if (newPos[0] == b[0] && newPos[1] == b[1]) {
                return -1;
            }
        }
        body.offerFirst(newPos);
        return body.size() - 1;
    }
}
