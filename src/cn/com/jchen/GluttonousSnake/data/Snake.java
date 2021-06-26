package cn.com.jchen.GluttonousSnake.data;

/**
 * 贪吃蛇对象
 * 组成：集合
 */
public class Snake
{
    /**
     * 初始化
     */
    public Snake()
    {
        // 初始蛇身长度
        snakeLengh = 3;

        // 初始化蛇头方向
        currDirection = "RIGHT";

        // 初始化蛇身
        snakeX[0] = 100; snakeY[0] = 100;
        snakeX[1] = 75; snakeY[1] = 100;
        snakeX[2] = 50; snakeY[2] = 100;

        // 初始分数
        score = 0;
    }

    /**
     * 蛇身长度默认3（一个头，两个身子）
     */
    public int snakeLengh;

    /**
     * 当前方向
     */
    public String currDirection;

    /**
     * 蛇身的横坐标
     */
    public int[] snakeX = new int[625];

    /**
     * 蛇身的纵坐标
     */
    public int[] snakeY = new int[625];

    /**
     * 分数
     */
    public int score;
}
