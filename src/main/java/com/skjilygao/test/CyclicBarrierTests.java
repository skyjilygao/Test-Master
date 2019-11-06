package com.skjilygao.test;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * CyclicBarrier test
 * CyclicBarrier，一个同步辅助类
 * <p> 在API中是这么介绍的：
 * 它允许一组线程互相等待，直到到达某个公共屏障点 (common barrier point)。在涉及一组固定大小的线程的程序中，这些线程必须不时地互相等待，此时 CyclicBarrier 很有用。因为该 barrier 在释放等待线程后可以重用，所以称它为循环 的 barrier。
 * <p> 通俗点讲就是：让一组线程到达一个屏障时被阻塞，直到最后一个线程到达屏障时，屏障才会开门，所有被屏障拦截的线程才会继续干活。
 * 参考： https://blog.csdn.net/chenssy/article/details/70160595
 * @author skyjilygao
 * @since 20191106
 */
public class CyclicBarrierTests {
    private static CyclicBarrier cyclicBarrier;

    public static void main(String[] args) {
        int thCount = 5;
        cyclicBarrier = new CyclicBarrier(thCount, ()-> System.out.println("全部就位"));
        for (int i = 0; i < thCount; i++) {
            new CyclicBarrierThread().start();
        }
    }
    static class CyclicBarrierThread extends Thread{
        @Override
        public void run() {
            try {
                System.out.println(Thread.currentThread().getName() + " 已就位...");
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }

        }
    }
}
