package io.github.lvbo.learn.java.concurrent.completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * @author lvbo
 * @version V1.0
 * @date 2019-07-13 07:34
 */
public class CompletableFutureX {

    public void wantToDrinkTea() {
        // 任务 1：洗水壶 -> 烧开水
        CompletableFuture<Void> f1 = CompletableFuture.runAsync(() -> {
           System.out.println("T1:洗水壶");
           sleep(1, TimeUnit.SECONDS);
           System.out.println("T1:烧开水");
        });

        // 任务 2：洗茶壶 -> 洗茶杯 -> 拿茶叶
        CompletableFuture<String> f2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("T2:洗茶壶");
            sleep(1, TimeUnit.SECONDS);
            System.out.println("T2:洗茶杯");
            sleep(1, TimeUnit.SECONDS);
            System.out.println("T2:拿茶叶");
            sleep(1, TimeUnit.SECONDS);

            return "龙井";
        });

        // 任务 3：任务 1 和任务 2 完成后执行：泡茶
        CompletableFuture<String> f3 = f1.thenCombine(f2, (__, tf) -> {
            System.out.println("拿到茶叶：" + tf);
            System.out.println("...泡茶");
            return " 上茶：" + tf;
        });

        System.out.println(f3.join());
    }

    private void sleep(int t, TimeUnit u) {
        try {
            u.sleep(t);
        }catch(InterruptedException e){}
    }

    public static void main(String[] args) {
        CompletableFutureX completableFutureX = new CompletableFutureX();
        completableFutureX.wantToDrinkTea();
    }
}
