package immobi.tec.immobitec;


import com.stripe.Stripe;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.annotation.PostConstruct;


@SpringBootApplication
@EnableWebMvc
@EnableScheduling


public class ImmobiTecApplication extends SpringBootServletInitializer {

    @PostConstruct
    public void setup(){
        Stripe.apiKey = "sk_test_51Ks89UCXTqtJcSxPSlNzU1YoSmb3jNW4ja2I6xw9nH4vVzQ3u4ACnJQ8sUr5jQODs5ce9OH8Ys7VFnoSkjRv5xDB00frb9D2f3";
    }
    public static void main(String[] args) {
        SpringApplication.run(ImmobiTecApplication.class, args);
    }
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(ImmobiTecApplication.class);
    }
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
