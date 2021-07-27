package com.skjilygao.design.patterns;

import java.util.ArrayList;
import java.util.List;

/**
 * 建造者模式
 *
 * @author skyjilygao
 */
public class BuilderPattern {

    public static void main(String[] args) {
        BuilderPattern builderPattern = new BuilderPattern();
        builderPattern.build();
    }

    private void build() {
        MealBuilder builder = new MealBuilder();
        Meal meal = builder.buildVegMeal();
        System.out.println("veg meal:");
        meal.showItem();
        System.out.println("the meal total cost: " + meal.getCost());

        System.out.println();
        meal = builder.buildChickenMeal();
        System.out.println("chicken meal:");
        meal.showItem();
        System.out.println("the meal total cost: " + meal.getCost());
    }


    /**
     * 产品：所有产品共同特性
     */
    public interface Item {
        String name();

        float price();

        Packing packing();
    }

    /**
     * 包装接口
     */
    public interface Packing {
        String pack();
    }

    /**
     * 杯子包装
     */
    public class Bottle implements Packing {
        @Override
        public String pack() {
            return "bottle";
        }
    }

    /**
     * 纸盒包装
     */
    public class Wrapper implements Packing {
        @Override
        public String pack() {
            return "wrapper";
        }
    }

    /**
     * 汉堡抽象类
     */
    public abstract class Burger implements Item {

        @Override
        public Packing packing() {
            return new Wrapper();
        }
    }

    /**
     * 冷饮抽象类
     */
    public abstract class ColdDrink implements Item {
        @Override
        public Packing packing() {
            return new Bottle();
        }
    }

    /**
     * 素菜汉堡
     */
    public class VegBurger extends Burger {
        @Override
        public String name() {
            return "VegBurger";
        }

        @Override
        public float price() {
            return 3.05F;
        }
    }

    /**
     * 鸡肉汉堡
     */
    public class ChickenBurger extends Burger {
        @Override
        public String name() {
            return "Chicken Burger";
        }

        @Override
        public float price() {
            return 12.56F;
        }
    }

    /**
     * 可口可乐
     */
    public class Coke extends ColdDrink {
        @Override
        public String name() {
            return "coke";
        }

        @Override
        public float price() {
            return 3.6F;
        }
    }

    /**
     * 百事可乐
     */
    public class Pepsi extends ColdDrink {
        @Override
        public String name() {
            return "Pepsi";
        }

        @Override
        public float price() {
            return 3.65F;
        }
    }

    /**
     * 套餐：可以有多个汉堡或多个冷饮，所以直接添加item
     */
    public class Meal {
        private List<Item> items = new ArrayList<>();
        private float cost = 0;

        public void addItem(Item item) {
            items.add(item);
            cost += item.price();
        }

        public float getCost() {
            return cost;
        }

        public void showItem() {
            for (Item item : items) {
                System.out.println(String.format("Item: %s, Packing: %s, Price: %s", item.name(), item.packing().pack(), item.price()));
            }
        }
    }

    /**
     * 套餐构建
     */
    public class MealBuilder {
        public Meal buildVegMeal() {
            Meal meal = new Meal();
            meal.addItem(new VegBurger());
            meal.addItem(new Coke());
            return meal;
        }

        public Meal buildChickenMeal() {
            Meal meal = new Meal();
            meal.addItem(new ChickenBurger());
            meal.addItem(new Pepsi());
            return meal;
        }
    }
}
