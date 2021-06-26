package cn.com.jchen.GluttonousSnake.data;

import java.util.Random;

/**
 * 食物对象
 */
public class Food
{
    /**
     * 食物的横坐标
     */
    private int foodX;

    /**
     * 食物的纵坐标
     */
    private int foodY;

    /**
     * 随机数对象
     */
    Random random = new Random();

    /**
     * 无参构造方法
     */
    public Food()
    {
        // 使用随机数进行生成坐标数据
        foodX = 25 + 25* random.nextInt(34);
        foodY = 75 + 25* random.nextInt(24);
    }

    public int getFoodX()
    {
        return foodX;
    }

    public void setFoodX(int foodX)
    {
        this.foodX = foodX;
    }

    public int getFoodY()
    {
        return foodY;
    }

    public void setFoodY(int foodY)
    {
        this.foodY = foodY;
    }
}
