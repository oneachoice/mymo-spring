package oneachoice.mymo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextListener;

@Configuration
public class RequestContextConfig {

    /**
     * RequestContextHolder를 사용하기 위해 등록한 RequestContextListener Bean입니다.
     */
    @Bean
    public RequestContextListener requestContextListener() {
        return new RequestContextListener();
    }
}
