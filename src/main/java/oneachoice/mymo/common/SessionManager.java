package oneachoice.mymo.common;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class SessionManager {

    private SessionManager() {
    }

    public static HttpSession getSession(boolean create) {

        // RequestContextHolder에서 ServletRequestAttributes 얻기
        ServletRequestAttributes servletRequestAttribute = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();

        // Session 가져오기
        return servletRequestAttribute.getRequest().getSession(create);
    }

    public static void setSessionAttribute(String name, Object value) {

        // Session 가져오기
        HttpSession httpSession = getSession(true);

        // 세션 속성에 키, 값 저장
        httpSession.setAttribute(name, value);
    }

    public static Object getSessionAttribute(String name) {

        // Session 가져오기
        HttpSession httpSession = getSession(false);

        if(httpSession == null) {
            throw new RuntimeException("세션이 존재하지 않습니다.");
        }

        return httpSession.getAttribute(name);
    }
}
