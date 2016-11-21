package cn.hankchan.stu.jdk.concurrent.cmpltnsrv;

import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.junit.Test;

public class TestCompletionService {

	@Test
	public void test() {
		// 创建线程池
		ExecutorService executor = Executors.newFixedThreadPool(5);
		CompletionService<String> completionService = new ExecutorCompletionService<>(executor);
		// 最大任务数
		final int TOTAL_TASK = 20;
		try {
			// 向其中丢任务
			for (int i = 0; i < TOTAL_TASK; i++) {
				completionService.submit(new MyTask("Thread:" + i));
			}
			System.out.println(completionService.take().get());
			
			// 检查线程池任务执行结果
			for (int i = 0; i < TOTAL_TASK; i++) {
				Future<String> future = completionService.take();
				System.out.println("completionResult: " + future.get());
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		} finally {
			// 关闭线程池
			executor.shutdown();
		}

	}
}
