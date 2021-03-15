package menghuanxianjing.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
@ConfigurationProperties(prefix = "spring.data.mongodb.gs")
public class MongoTemplateMgr extends AbstractMongoConfig{

	@Primary
	@Override
	public  MongoTemplate getMongoTemplate() throws Exception {
		return new MongoTemplate(mongoDbFactory());
	}
	
	@Override
	public MongoTemplate getMongoTemplate(String host,String dbname) throws Exception {
		return new MongoTemplate(mongoDbFactory(host,dbname));
	}

}
