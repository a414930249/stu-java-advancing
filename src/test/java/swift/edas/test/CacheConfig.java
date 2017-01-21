package swift.edas.test;

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

@Configuration
@EnableCaching
@org.springframework.context.annotation.PropertySource("classpath:/swift.properties")
public class CacheConfig {

	public static Logger logger = LoggerFactory.getLogger(CacheConfig.class);

	public static final String PREFIX_CACHE = "CACHE.";

	@Autowired
	ConfigurableEnvironment env;

	@Bean
	public CacheManager cacheManager() {
		SimpleCacheManager simpleCacheManager = new SimpleCacheManager();
		List<GuavaCache> caches = new ArrayList<GuavaCache>();
		for(PropertySource<?> propertySource : env.getPropertySources()) {
			if(propertySource instanceof EnumerablePropertySource<?>) {
				EnumerablePropertySource<?> propertites = (EnumerablePropertySource<?>)propertySource;
				for(String key : propertites.getPropertyNames()) {
					if(key.startsWith(PREFIX_CACHE)) {
						String cacheName = key.substring(PREFIX_CACHE.length()).trim();
						if(!Strings.isNullOrEmpty(cacheName)) {
							String cacheSetting = propertites.getProperty(key).toString();
							logger.debug("Initializeing cache: {}={}", cacheName, cacheSetting);
							CacheBuilder<Object, Object> cacheBuilder = Strings.isNullOrEmpty(cacheSetting) ?
								CacheBuilder.newBuilder() :
								CacheBuilder.from(cacheSetting);
							caches.add(new GuavaCache(cacheName, (Cache<Object, Object>)cacheBuilder.build()));
						}
					}
				}
			}
		}
		simpleCacheManager.setCaches(caches);
		return simpleCacheManager;
	}

}
