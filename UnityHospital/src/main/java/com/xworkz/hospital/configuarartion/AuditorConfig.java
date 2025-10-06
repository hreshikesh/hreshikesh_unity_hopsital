package com.xworkz.hospital.configuarartion;
import com.xworkz.hospital.audit.AuditorAwareImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import javax.servlet.http.HttpSession;

@Configuration
@ComponentScan(basePackages = "com.xworkz.hospital")
@EnableJpaAuditing
public class AuditorConfig {

    @Bean
    public AuditorAware<String> auditorProvider(HttpSession session) {
        return new AuditorAwareImpl(session);
    }
}
