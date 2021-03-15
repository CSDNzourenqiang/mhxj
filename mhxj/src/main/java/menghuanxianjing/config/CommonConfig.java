package menghuanxianjing.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "server")
@PropertySource(value = "classpath:application.properties")
public class CommonConfig {
	private String open_month;
	private String open_year;
	public String getOpen_month() {
		return open_month;
	}
	public void setOpen_month(String open_month) {
		this.open_month = open_month;
	}
	public String getOpen_year() {
		return open_year;
	}
	public void setOpen_year(String open_year) {
		this.open_year = open_year;
	}
	
	
}
