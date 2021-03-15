package menghuanxianjing.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
@ConfigurationProperties(prefix = "spring.data.mongodb.center")
public class CsCenterMongoConfig extends AbstractMongoConfig{

	@Primary

	@Override
	public @Bean(name = "gsCenterMongoTemplate") MongoTemplate getMongoTemplate() throws Exception {
		return new MongoTemplate(mongoDbFactory());
	}

	@Override
	public MongoTemplate getMongoTemplate(String host, String dbname) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
