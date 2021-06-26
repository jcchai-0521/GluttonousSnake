package cn.com.jchen.GluttonousSnake.frame;

import cn.com.jchen.GluttonousSnake.componet.GamePanel;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame
{
    /**
     * 构造方法
     */
    public MainFrame()
    {
        // 初始化相关参数
        init();
    }

    /**
     * 初始化
     */
    public void init()
    {
        setTitle("贪吃蛇，吃吃吃。。。");

        // 设置位置和大小 900和720是通过计算而来，25*25个格子，每个格子是25*25
        setBounds(500, 500, 900, 720 );

        // 设置可见性
        setVisible(true);

        // 设置大小不可变，避免游戏场景变形
        setResizable(false);

        // 设置窗口的关闭事件
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * 游戏启动方法
     */
    public void startGame()
    {
        // 设置游戏主要组件：游戏面板
        setContentPane(new GamePanel());
    }
}
