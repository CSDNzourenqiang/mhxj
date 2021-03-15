package menghuanxianjing.mhxj;

import java.io.IOException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.context.ApplicationPidFileWriter;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Hello world!
 *
 */
@ComponentScan("menghuanxianjing.*")
@EnableScheduling
@SpringBootApplication(exclude = {MongoAutoConfiguration.class,MongoDataAutoConfiguration.class})
@EnableMongoRepositories(basePackages = {"menghuanxianjing.mhxj.repository"})
public class App
{
	
    public static void main( String[] args ) throws IOException
    {
    	SpringApplication sa = new SpringApplication(App.class);
        sa.addListeners(new ApplicationPidFileWriter());
        sa.run(args);
    }
}
