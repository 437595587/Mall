package com.ruoyi.seckill.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

@Configuration
public class ThreadPoolTaskConfig {
	@Bean("taskExecutor") // bean的名称，默认为首字母小写的方法名
	public ThreadPoolTaskExecutor asyncExecutor(ThreadPoolConfigProperties properties) {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(properties.getCorePoolSize());
		executor.setMaxPoolSize(properties.getMaxPoolSize());
		executor.setQueueCapacity(properties.getQueueCapacity());
		executor.setKeepAliveSeconds(properties.getKeepAliveTime());
		executor.setThreadNamePrefix(properties.getThreadNamePrefix());

		// 线程池对拒绝任务的处理策略
		executor.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardOldestPolicy());
		// 初始化
		executor.initialize();
		return executor;
	}
}
