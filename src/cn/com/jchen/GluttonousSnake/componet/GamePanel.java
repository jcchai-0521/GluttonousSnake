package cn.com.jchen.GluttonousSnake.componet;

import cn.com.jchen.GluttonousSnake.data.Food;
import cn.com.jchen.GluttonousSnake.data.PanelDate;
import cn.com.jchen.GluttonousSnake.data.Snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * 游戏画布
 */
public class GamePanel extends JPanel implements KeyListener, ActionListener
{
    /**
     * 贪吃蛇对象
     */
    private Snake snake;

    /**
     * 食物对象
     */
    private Food food;

    /**
     * 游戏是否启动
     */
    private boolean isStart;

    /**
     * 判断是否失败
     */
    private boolean isFail;

    // 定时任务
    Timer timer = new Timer(100, this);

    public GamePanel()
    {
        // 初始化
        init();

        // 设置相关监听事件
        // 获取焦点事件
        this.setFocusable(true);
        // 键盘监听事件
        this.addKeyListener(this);

        // 定时任务启动
        timer.start();
    }

    /**
     * 画组件
     * @param g 画笔
     */
    @Override
    protected void paintComponent(Graphics g)
    {
        System.out.println("重画组件内容");
        // 清屏
        super.paintComponent(g);

        // 画背景
        this.setBackground(Color.WHITE);

        // 界面头部积分信息
        g.setColor(Color.BLACK);
        g.setFont(new Font("微软雅黑",Font.BOLD,16));
        g.drawString("长度 " + snake.snakeLengh, 750, 35);
        g.drawString("分数 " + snake.score, 750, 50);

        // 主主体部分
        g.setColor(Color.BLACK);
        g.fillRect(25, 75, 850, 600);

        // 小蛇
        paintSnake(g);

        // 食物
        PanelDate.food.paintIcon(this, g, food.getFoodX(), food.getFoodY());

        // 游戏提示
        //游戏提示
        if (isStart == false)
        {
            g.setColor(Color.white);
            g.setFont(new Font("微软雅黑", Font.BOLD, 40));
            g.drawString("按下空格开始游戏!", 300, 300);
        }

        // 失败判断
        //失败判断
        if (isFail)
        {
            g.setColor(Color.RED);
            g.setFont(new Font("微软雅黑", Font.BOLD, 40));
            g.drawString("失败, 按下空格重新开始", 200, 300);
        }
    }

    /**
     * 画小蛇
     * @param g
     */
    private void paintSnake(Graphics g)
    {
        // 画头
        ImageIcon snakeHeader = getSnakeHeaderImageIcon(snake.currDirection);
        snakeHeader.paintIcon(this, g, snake.snakeX[0], snake.snakeY[0]);

        // 画身子
        for (int i = 1; i < snake.snakeLengh; i++)
        {
            PanelDate.body.paintIcon(this, g, snake.snakeX[i], snake.snakeY[i]);
        }
    }

    /**
     * 根据方向获取头部图标
     * @param currDirection
     * @return
     */
    private ImageIcon getSnakeHeaderImageIcon(String currDirection)
    {
        if ("LEFT".equals(currDirection))
        {
            return PanelDate.left;
        }

        if ("RIGHT".equals(currDirection))
        {
            return PanelDate.right;
        }

        if ("UP".equals(currDirection))
        {
            return PanelDate.up;
        }

        return PanelDate.down;
    }

    /**
     * 初始化场景
     */
    public void init()
    {
        System.out.println("初始化完成！");
        // 初始化一条蛇
        snake = new Snake();
        // 设置游戏暂停状态
        isStart = false;
        // 设置成功失败状态
        isFail = false;

        // 初始化食物数据
        food = new Food();
    }

    @Override
    public void keyTyped(KeyEvent e)
    {

    }

    /**
     * 键盘监听事件
     * @param e
     */
    @Override
    public void keyPressed(KeyEvent e)
    {
        // 获取键盘按键
        int keyCode = e.getKeyCode();
        System.out.println(keyCode);
        //如果是空格
        if (keyCode == KeyEvent.VK_SPACE)
        {
            //如果游戏失败,从头再来！
            if (isFail)
            {
                isFail = false;
                //重新初始化
                init();
            }
            else
            {
                //否则，暂停游戏
                isStart = !isStart;
            }

            // 将界面重新渲染
            repaint();
        }

        // 上下左右处理
        String nextDirection = "RIGHT";
        //键盘控制走向
        if (keyCode == KeyEvent.VK_LEFT)
        {
            nextDirection = "LEFT";
        }
        else if (keyCode == KeyEvent.VK_RIGHT)
        {
            nextDirection = "RIGHT";
        }
        else if (keyCode == KeyEvent.VK_UP)
        {
            nextDirection = "UP";
        }
        else if (keyCode == KeyEvent.VK_DOWN)
        {
            nextDirection = "DOWN";
        }

        snake.currDirection = nextDirection;
    }

    @Override
    public void keyReleased(KeyEvent e)
    {

    }

    /**
     * 主页面图像生成
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e)
    {
        //如果游戏处于开始状态，并且没有结束，则小蛇可以移动
        if (!isStart || isFail)
        {
            timer.start();
            return;
        }

        // 身体移动，后一个移动到前一个身子的位置即可
        for (int i = snake.snakeLengh - 1; i > 0; i--)
        {
            //除了脑袋都往前移：身体移动
            snake.snakeX[i] = snake.snakeX[i - 1];
            snake.snakeY[i] = snake.snakeY[i - 1];
        }

        // 头部移动
        if ("UP".equals(snake.currDirection))
        {
            // x轴不变，y轴加25
            snake.snakeY[0] -= 25;
            if (snake.snakeY[0] < 75)
            {
                snake.snakeY[0] = 650;
            }
        }
        else if ("DOWN".equals(snake.currDirection))
        {
            // x轴不变，y轴减25
            snake.snakeY[0] += 25;
            if (snake.snakeY[0] > 650)
            {
                snake.snakeY[0] = 75;
            }
        }
        else if ("LEFT".equals(snake.currDirection))
        {
            // y轴不变，x减去25
            snake.snakeX[0] -= 25;
            if (snake.snakeX[0] < 25)
            {
                snake.snakeX[0] = 850;
            }
        }
        else
        {
            // y轴不变，x加上25
            snake.snakeX[0] += 25;
            if (snake.snakeX[0] > 850)
            {
                snake.snakeX[0] = 25;
            }
        }

        // 吃食物
        eatFood(snake, food);

        // 死亡判定，当前只判断小蛇吃到自己
        for (int i = 1; i < snake.snakeLengh; i++)
        {
            //如果头和身体碰撞，那就说明游戏失败
            if (snake.snakeX[i] == snake.snakeX[0] && snake.snakeY[i] == snake.snakeY[0])
            {
                isFail = true;
            }
        }

        // 重新画页面信息
        repaint();

        timer.start();
    }

    /**
     * 吃食物，判定小蛇的头部坐标与食物坐标重合
     * @param snake
     * @param food
     */
    private void eatFood(Snake snake, Food food)
    {
        if (snake.snakeX[0] != food.getFoodX() || snake.snakeY[0] != food.getFoodY())
        {
            return;
        }

        // 小蛇长度加1
        snake.snakeLengh++;

        // 积分增加3分
        snake.score += 3;

        // 重新生成食物
        this.food = new Food();
    }
}
