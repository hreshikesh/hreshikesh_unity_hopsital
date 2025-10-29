package com.xworkz.hospital.audit;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.AuditorAware;

import javax.servlet.http.HttpSession;
import java.util.Optional;
@Slf4j
public class AuditorAwareImpl implements AuditorAware<String> {


    private final HttpSession httpSession;


    public AuditorAwareImpl(HttpSession httpSession) {
        this.httpSession = httpSession;
    }

    @Override
    public Optional<String> getCurrentAuditor() {
        String adminName = (String) httpSession.getAttribute("adminName");
        String userName=(String) httpSession.getAttribute("userName");
        if(adminName!=null) {
            log.info(adminName);
            return Optional.of(adminName+"-ADMIN");
        }else if(userName!=null){
            return  Optional.of(userName+"-USER");
        }
        return Optional.empty();
    }
}
