package com.skjilygao.test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ThreadLocalTests {
    ThreadLocal<String> threadName;
    public static void main(String[] args) {
        ThreadLocalTests tests = new ThreadLocalTests();
        tests.start();
    }

    private void start(){
        int maxThead = 20;
        CountDownLatch start = new CountDownLatch(1);
        CountDownLatch done = new CountDownLatch(maxThead);
        ThreadPoolExecutor executor = threadPoolExecutor(maxThead);
        List<Future<Integer>> futureList = new ArrayList<>();
        for (int i = 0; i < maxThead; i++) {
            TestCallable2 callable = new TestCallable2(start,done,i);
            futureList.add(executor.submit(callable));
        }
        startAndWaitDone(start, done, executor);
    }

    private void startAndWaitDone(CountDownLatch startSignal, CountDownLatch doneSignal, ThreadPoolExecutor executor) {
        //开始信号
        startSignal.countDown();
        try {
            //等待结束
            doneSignal.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            //关闭线程
            executor.shutdown();
        }
    }
    ThreadPoolExecutor threadPoolExecutor(int corePoolSize) {
        int maximumPoolSize = 3 * corePoolSize / 2;
        ThreadPoolExecutor exec = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, 30L, TimeUnit.SECONDS, new ArrayBlockingQueue(30), new ThreadPoolExecutor.DiscardOldestPolicy());
        return exec;
    }

    private void papreadRun(int index){
        threadName = ThreadLocal.withInitial(()-> "线程="+index);
        run();
    }

    private void run(){
        int m = 50;
        for (int i = 0; i < m; i++) {
            System.out.println(">>>>>>"+threadName.get());
        }
    }
    class TestCallable2 implements Callable<Integer> {
        private CountDownLatch startSignal;
        private CountDownLatch doneSignal;
        private int index;

        public TestCallable2() {
        }

        public TestCallable2(CountDownLatch startSignal, CountDownLatch doneSignal, int index) {
            this.startSignal = startSignal;
            this.doneSignal = doneSignal;
            this.index = index;
        }

        @Override
        public Integer call() throws Exception {
            try {
                startSignal.await();
                papreadRun(index);
            } catch (Throwable e) {
            } finally {
                doneSignal.countDown();
                System.out.println("当前线程完成，剩余线程数：" + doneSignal.getCount());
                return index;
            }
        }
    }

}
