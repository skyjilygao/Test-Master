package com.skjilygao.design.patterns;

/**
 * 桥接模式
 * @author skyjilygao
 */
public class BridgePattern {

public static void main(String[] args) {
    BridgePattern pattern = new BridgePattern();
    pattern.bridge();
}

private void bridge() {
    // 画蓝色圆形
    Circle circle = new Circle(10, 20, 100, new BlueDraw());
    circle.draw();

    // 画红色圆形
    circle = new Circle(10, 20, 200, new RedDraw());
    circle.draw();

    // 画红色矩形
    Rectangle rectangle = new Rectangle(20,30,400, new RedDraw());
    rectangle.draw();
}

/**
 * 定义画图接口
 */
public interface DrawApi{
    void draw(ShapeEnums shapeEnums, int x, int y, int raduis);
}

/**
 * 实现画图接口，画红色
 */
public class RedDraw implements DrawApi{
    @Override
    public void draw(ShapeEnums shapeEnums, int x, int y, int raduis) {
        System.out.println(String.format("draw red %s. [x: %s, y: %s, raduis: %s]", shapeEnums, x, y, raduis));
    }
}

/**
 * 实现画图接口，画蓝色
 */
public class BlueDraw implements DrawApi{
    @Override
    public void draw(ShapeEnums shapeEnums, int x, int y, int raduis) {
        System.out.println(String.format("draw blue %s. [x: %s, y: %s, raduis: %s]", shapeEnums, x, y, raduis));
    }
}

/**
 * 形状抽象类
 */
public abstract class Shape{
    protected DrawApi drawApi;

    protected Shape(DrawApi drawApi) {
        this.drawApi = drawApi;
    }

    /**
     * 虽然这里也可以有具体实现，但为了解耦，各自形状有各自的draw逻辑。所以这里只定义
     */
    protected abstract void draw();
}

/**
 * 实现形状抽象类，定义具体形状
 */
public class Circle extends Shape{
    int x, y, raduis;
    private ShapeEnums shapeEnums = ShapeEnums.CIRCLE;

    public Circle(int x, int y, int raduis, DrawApi drawApi) {
        super(drawApi);
        this.x = x;
        this.y = y;
        this.raduis = raduis;
    }

    @Override
    protected void draw() {
        drawApi.draw(shapeEnums, x, y, raduis);
    }
}
    /**
     * 矩形
     */
public class Rectangle extends Shape{
    int x, y, raduis;
    private ShapeEnums shapeEnums = ShapeEnums.RECTANGLE;

    public Rectangle(int x, int y, int raduis, DrawApi drawApi) {
        super(drawApi);
        this.x = x;
        this.y = y;
        this.raduis = raduis;
    }

    /**
     * 虽然这里也可以有具体实现，但为了解耦，各自形状有各自的draw逻辑。所以这里只定义
     */
    @Override
    protected void draw() {
        drawApi.draw(shapeEnums, x,y, raduis);
    }
}

public enum ShapeEnums{
    CIRCLE, RECTANGLE
    ;
}
}
