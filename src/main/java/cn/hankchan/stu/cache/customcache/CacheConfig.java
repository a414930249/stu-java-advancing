package cn.hankchan.stu.cache.customcache;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.guava.GuavaCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.EnumerablePropertySource;
import org.springframework.core.env.PropertySource;

import com.google.common.base.Strings;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

/**
 * 自定义缓存实现 
 * @author ching(代码实现) hankChan(补充注释)
 * @time 10:57:51 - 27 Dec 2016
 * @detail 使用Configuration注解，功能等同于一个Spring的XML配置文件，使用@EnableCaching修饰，支持Spring注解去配置缓存
 * 应用中同时实现了EhCache框架的缓存配置，应用启动时只需要二选一即可，不要同时使用两种缓存实现，具体选择方法如下：
 * 在classpath:config/application.xml中启用如下配置的其中一个，另外一个屏蔽起来即可：
 * <bean class="swift.edas.web.CacheConfig"/>
 * <import resource="classpath:config/application-ehcache.xml"/>
 */
@Configuration
@EnableCaching
@ComponentScan("")
@org.springframework.context.annotation.PropertySource("classpath:/custom-cache.properties")
public class CacheConfig {

	public static Logger logger = LoggerFactory.getLogger(CacheConfig.class);

	public static final String PREFIX_CACHE = "CACHE.";

	/**
	 * 自动注入ConfigurableEnvironment，在Spring容器初始化的时候，会自动实现生成ConfigurableEnvironment的Bean
	 * 该Bean中可用的内容（仅列出已初始化的属性）有：
	 * 1. defaultProfiles=[default=java.lang.Object]
	 * 2. logger=[Jdk14Logger=org.apache.commons.logging.impl.Jdk14Logger]
	 * 3. propertyResolver=[org.springframework.core.env.PropertySourcesPorpertyResolver]：资源文件的解析器
	 * 4. propertySources=[systemProperties, systemEnvironmet]：被Spring管理的资源文件
	 * 		默认的systemProperties存放应用系统的配置，如：应用上下文Context信息、java运行环境的配置信息、jvm相关配置信息等等。
	 * 		默认的systemEnvironment存放系统环境变量相关信息，如：PATH的值等信息。
	 * 如果Spring引用了外部资源文件，那么该外部文件将被propertySources管理。如当前类使用了导入资源文件的注解@PropertySource("classpath:/swift.properties")
	 * 		此时，当Spring初始化的时候，propertySources中的内容将会增加一项：class path resource [swift.properties]
	 */
	@Autowired
	ConfigurableEnvironment env;

	@Bean
	public CacheManager cacheManager() {
		SimpleCacheManager simpleCacheManager = new SimpleCacheManager();
		List<GuavaCache> caches = new ArrayList<GuavaCache>();
		// 通过env获取资源文件
		for(PropertySource<?> propertySource : env.getPropertySources()) {
			if(propertySource instanceof EnumerablePropertySource<?>) {
				EnumerablePropertySource<?> propertites = (EnumerablePropertySource<?>)propertySource;
				// 遍历资源文件，为的是找到加入的外部资源文件
				for(String key : propertites.getPropertyNames()) {
					// 当资源文件中的key值以PREFIX_CACHE开头，说明就是自定义需要的文件
					if(key.startsWith(PREFIX_CACHE)) {
						// 对key值进行处理，获取需要配置为缓存名的信息（如：radar、typhoon、realtime、satellite等）
						String cacheName = key.substring(PREFIX_CACHE.length()).trim();
						if(!Strings.isNullOrEmpty(cacheName)) {  //确保该缓存名不为null不为空字符串
							String cacheSetting = propertites.getProperty(key).toString();
							logger.debug("Initializeing cache: {}={}", cacheName, cacheSetting);
							// 构建缓存
							CacheBuilder<Object, Object> cacheBuilder = Strings.isNullOrEmpty(cacheSetting) ?
								CacheBuilder.newBuilder() :
								CacheBuilder.from(cacheSetting);
							// 将缓存内容加入缓存列表中
							caches.add(new GuavaCache(cacheName, (Cache<Object, Object>)cacheBuilder.build()));
						}
					}
				}
			}
		}
		// 将缓存列表加入自定义缓存对象中并返回
		simpleCacheManager.setCaches(caches);
		return simpleCacheManager;
	}

}
