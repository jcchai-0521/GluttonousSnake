# GUI编程

## 组件

- 窗口
- 弹窗
- 面板
- 文本框
- 列表框
- 按钮
- 图片
- 监听事件
- 鼠标
- 键盘
- 破解工具

## 布局管理

- 流式布局-FlowLayout
- 表格布局-GridLayout
- 上下左右中布局-BorderLayout
- 绝对定位-null



### 练习1 布局设置

![image-20210620155959604](C:\Users\Admin\AppData\Roaming\Typora\typora-user-images\image-20210620155959604.png)

### code

```java
private static void test2()
    {
        Frame frame = new Frame("布局测试2");
        // 自动填充布局
        frame.setVisible(true);
        frame.setBounds(200, 200, 500, 500);
        frame.setBackground(new Color(42, 42, 231));

        // 设置上下两个面板
        frame.setLayout(new GridLayout(2, 1));

        Panel topPanel = new Panel();
        topPanel.setBackground(new Color(1,2,3));
        topPanel.setLayout(new BorderLayout());
        topPanel.add(new Button("左"), BorderLayout.EAST);
        Panel topCenterPanel = new Panel(new GridLayout(2,1));
        topCenterPanel.add(new Button("嘿嘿"));
        topCenterPanel.add(new Button("哈哈"));
        topPanel.add(topCenterPanel, BorderLayout.CENTER);
        topPanel.add(new Button("右"), BorderLayout.WEST);

        Panel buttomPanel = new Panel();
        buttomPanel.setBackground(new Color(205, 70, 205));
        buttomPanel.setLayout(new BorderLayout());
        buttomPanel.add(new Button("左"), BorderLayout.EAST);
        Panel buttomCenterPanel = new Panel(new GridLayout(2,2));
        for (int i = 0; i < 4; i++)
        {
            buttomCenterPanel.add(new Button("咯咯咯" + i));
        }

        buttomPanel.add(buttomCenterPanel, BorderLayout.CENTER);
        buttomPanel.add(new Button("左"), BorderLayout.WEST);

        frame.add(topPanel);
        frame.add(buttomPanel);

        closeFrame(frame);
    }
```



## 事件监听

frame关闭

按钮点击

多个按钮共享一个实现

![image-20210620161615526](C:\Users\Admin\AppData\Roaming\Typora\typora-user-images\image-20210620161615526.png)

简易计算器



画笔

​	paint

鼠标监听

实现画笔，每次鼠标点击都画一个点

![image-20210620182311764](C:\Users\Admin\AppData\Roaming\Typora\typora-user-images\image-20210620182311764.png)

![image-20210620183959116](C:\Users\Admin\AppData\Roaming\Typora\typora-user-images\image-20210620183959116.png)

```java
package cn.com.jchen.gui.paint;

import cn.com.jchen.gui.base.BaseFrame;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MyPaint extends BaseFrame
{
    /**
     * 鼠标点击的位置集合
     */
    private List<Point> mouseApoint = new ArrayList<>();

    public MyPaint(String title)
    {
        // 设置窗口标题
        super(title);

        this.addMouseListener(new MyMouseListener());
    }

    @Override
    public void loadFrame()
    {
        super.loadFrame();

        setBounds(300, 300, 600, 500);
    }

    /**
     * 重写画笔，在窗口调用repaint的时候回进行调用
     * @param g
     */
    @Override
    public void paint(Graphics g)
    {
        Iterator<Point> iterator = mouseApoint.iterator();
        while (iterator.hasNext())
        {
            Point next = iterator.next();
            g.setColor(Color.BLACK);
            g.fillOval(next.x, next.y, 10, 10);
        }
    }

    public void addPoint(Point point)
    {
        mouseApoint.add(point);
    }

    class MyMouseListener extends MouseAdapter
    {
        @Override
        public void mousePressed(MouseEvent e)
        {
            System.out.println("点了一下");
            // 获取当前的坐标
            MyPaint myPaint = (cn.com.jchen.gui.paint.MyPaint) e.getSource();

            // 添加的鼠标点击集合中
            myPaint.addPoint(new Point(e.getX(), e.getY()));

            // 重新对页面进行渲染处理
            myPaint.repaint();
        }
    }
}

```

窗口监听

窗口关闭

窗口隐藏

键盘监听

## Swing

### 与AWT的区别

### JFrame

获取容器

JFrame.getContentPane()，内容都是在容器中

### JDialog

![image-20210620211454795](C:\Users\Admin\AppData\Roaming\Typora\typora-user-images\image-20210620211454795.png)

默认有关闭事件，无须添加

### JLabel

#### 图片标签

![image-20210620212023967](C:\Users\Admin\AppData\Roaming\Typora\typora-user-images\image-20210620212023967.png)

![image-20210620212409006](C:\Users\Admin\AppData\Roaming\Typora\typora-user-images\image-20210620212409006.png)

### 面板

JPanel

JScroll

![image-20210620212922714](C:\Users\Admin\AppData\Roaming\Typora\typora-user-images\image-20210620212922714.png)

超过一定内容后展示滚动条

### 按钮

![image-20210620213244733](C:\Users\Admin\AppData\Roaming\Typora\typora-user-images\image-20210620213244733.png)

#### 单选-JRadioButton

![image-20210620213453341](C:\Users\Admin\AppData\Roaming\Typora\typora-user-images\image-20210620213453341.png)

#### 多选-JCheckBox

![image-20210620214133247](C:\Users\Admin\AppData\Roaming\Typora\typora-user-images\image-20210620214133247.png)

### 列表

下拉框-JComboBox

![image-20210620214342122](C:\Users\Admin\AppData\Roaming\Typora\typora-user-images\image-20210620214342122.png)

列表框-JList

![image-20210620214516833](C:\Users\Admin\AppData\Roaming\Typora\typora-user-images\image-20210620214516833.png)

### 文本框

文本框-JTextField

![image-20210620214805645](C:\Users\Admin\AppData\Roaming\Typora\typora-user-images\image-20210620214805645.png)

密码框-JPasswordField

![image-20210620214854173](C:\Users\Admin\AppData\Roaming\Typora\typora-user-images\image-20210620214854173.png)

文本域

![image-20210620214922980](C:\Users\Admin\AppData\Roaming\Typora\typora-user-images\image-20210620214922980.png)



## 综合应用-贪吃蛇

### 相关概念

```
帧：如果时间片足够小，那么一秒30帧的效果对于人的眼睛来说就是动画。
每个帧都可以看做是一个图片
```

### 涉及到的技术

- 键盘监听
- 定时器



![image-20210620215549680](C:\Users\Admin\AppData\Roaming\Typora\typora-user-images\image-20210620215549680.png)

![image-20210620220001101](C:\Users\Admin\AppData\Roaming\Typora\typora-user-images\image-20210620220001101.png)

![image-20210620220051739](C:\Users\Admin\AppData\Roaming\Typora\typora-user-images\image-20210620220051739.png)



![image-20210620220630085](C:\Users\Admin\AppData\Roaming\Typora\typora-user-images\image-20210620220630085.png)

![image-20210620220755409](C:\Users\Admin\AppData\Roaming\Typora\typora-user-images\image-20210620220755409.png)



![image-20210620220836949](C:\Users\Admin\AppData\Roaming\Typora\typora-user-images\image-20210620220836949.png)

![image-20210620221308414](C:\Users\Admin\AppData\Roaming\Typora\typora-user-images\image-20210620221308414.png)

![image-20210620221618061](C:\Users\Admin\AppData\Roaming\Typora\typora-user-images\image-20210620221618061.png)

![image-20210620221645441](C:\Users\Admin\AppData\Roaming\Typora\typora-user-images\image-20210620221645441.png)