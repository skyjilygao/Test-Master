package com.skjilygao.design.patterns;

/**
 * 工厂模式
 *
 * @author skyjilygao
 */
public class FactoryPattern {

    public static void main(String[] args) {
        FactoryPattern factoryPattern = new FactoryPattern();
        factoryPattern.factory();
    }

    public void factory() {
        // 矩形
        ShapeFactory factory = new ShapeFactory(ShapeEnums.RECTANGLE);
        factory.getShape().draw();
        // 圆形
        factory = new ShapeFactory(ShapeEnums.CIRCLE);
        factory.getShape().draw();
        // 正方形
        factory = new ShapeFactory(ShapeEnums.SQUARE);
        factory.getShape().draw();
        // 菱形
        factory = new ShapeFactory(ShapeEnums.RHOMBUS);
        factory.getShape().draw();
    }

    /**
     * 定义 shape工厂
     */
    public class ShapeFactory {
        private Shape shape;

        public ShapeFactory(ShapeEnums shape) {
            switch (shape) {
                case RECTANGLE:
                    this.shape = new Rectangle();
                    break;
                case CIRCLE:
                    this.shape = new Circle();
                    break;
                case SQUARE:
                    this.shape = new Square();
                    break;
                case RHOMBUS:
                    this.shape = new Rhombus();
                    break;
                default:
                    throw new IllegalArgumentException("not found match construct method with : " + shape);
            }
        }

        public Shape getShape() {
            return shape;
        }
    }

    /**
     * 定义可使用的类型，所有新的在此增加即可
     */
    public enum ShapeEnums {
        RECTANGLE, CIRCLE, SQUARE, RHOMBUS;
    }

    /**
     * 公共接口
     */
    public interface Shape {
        void draw();
    }

    /**
     * 长方形
     */
    public class Rectangle implements Shape {
        @Override
        public void draw() {
            System.out.println("this is Rectangle::draw method!");
        }
    }

    /**
     * 圆形
     */
    public class Circle implements Shape {
        @Override
        public void draw() {
            System.out.println("this is Circle::draw method!");
        }
    }

    /**
     * 正方形
     */
    public class Square implements Shape {
        @Override
        public void draw() {
            System.out.println("this is Square::draw method!");
        }
    }

    /**
     * 菱形
     */
    public class Rhombus implements Shape {
        @Override
        public void draw() {
            System.out.println("this is Rhombus::draw method!");
        }
    }
}
