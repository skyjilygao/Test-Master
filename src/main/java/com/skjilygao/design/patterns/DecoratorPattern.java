package com.skjilygao.design.patterns;

/**
 * 装饰器模式
 *
 * @author skyjilygao
 */
public class DecoratorPattern {
    public static void main(String[] args) {
        DecoratorPattern pattern = new DecoratorPattern();
        pattern.decorator();
    }

    private void decorator() {
        Shape rect = new Rectangle();
        // 装饰前：没有装饰
        System.out.println("装饰前：");
        rect.draw();

        // 装饰后：
        System.out.println("装饰后：");
        Shape red = new RedShapeDecorator(rect);
        red.draw();
    }

    /**
     * 定义接口
     */
    public interface Shape {
        void draw();
    }

    /**
     * 矩形实现
     */
    public class Rectangle implements Shape {
        @Override
        public void draw() {
            System.out.println("Shape: Rectangle");
        }
    }

    /**
     * 圆形实现
     */
    public class Circle implements Shape {
        @Override
        public void draw() {
            System.out.println("Shape: Circle");
        }
    }

    /**
     * 现在需要额外装饰已有的形状，那么就定义一个装饰抽象类
     */
    public abstract class ShapeDecorator implements Shape {
        private Shape decorator;

        public ShapeDecorator(Shape decorator) {
            this.decorator = decorator;
        }

        @Override
        public void draw() {
            decorator.draw();
        }
    }

    /**
     * 红色装饰实现类
     */
    public class RedShapeDecorator extends ShapeDecorator {
        public RedShapeDecorator(Shape decorator) {
            super(decorator);
        }

        @Override
        public void draw() {
            super.draw();
            setBorder();
        }

        private void setBorder() {
            System.out.println("额外功能：decorator: red color.");
        }
    }

    /**
     * 黑色装饰实现类
     */
    public class BlackShapeDecorator extends ShapeDecorator {
        public BlackShapeDecorator(Shape decorator) {
            super(decorator);
        }

        @Override
        public void draw() {
            super.draw();
            setBorder();
        }

        private void setBorder() {
            System.out.println("decorator: black color.");
        }
    }
}
