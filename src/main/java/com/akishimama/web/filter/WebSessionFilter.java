package com.akishimama.web.filter;

import com.akishimama.web.domain.WebSession;
import com.akishimama.web.repository.WebSessionRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class WebSessionFilter extends OncePerRequestFilter {

    private static final String SID_COOKIE_NAME = "sid";

    public static final String SESS_ATTR_NAME = "session";

    public static final List<String> NO_SESSION_PATH_PREFIXES =  Collections.unmodifiableList(Arrays.asList(
            "/assets",
            "/error"
    ));

    @Autowired
    private WebSessionRepository webSessionRepository;

    @Transactional
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (isExcludePath(request.getServletPath())) {
            filterChain.doFilter(request, response);
            return;
        }

        Optional<String> sessionId = getSessionIdByCookie(request);

        WebSession webSession = null;
        if (sessionId.isPresent()) {
            webSession = webSessionRepository.findOne(sessionId.get());
        }

        if (webSession == null) {
            webSession = new WebSession();
            webSession.setSessionId(RandomStringUtils.randomAlphanumeric(16));
            webSessionRepository.save(webSession);
        }

        request.setAttribute(SESS_ATTR_NAME, webSession);

        response.addCookie(createCookie(webSession.getSessionId()));
        filterChain.doFilter(request, response);
    }

    private boolean isExcludePath(String path) {
        for(String pattern : NO_SESSION_PATH_PREFIXES) {
            if (path.startsWith(pattern)) {
                return true;
            }
        }

        return false;
    }

    private Cookie createCookie(String sessionId) {
        Cookie cookie = new Cookie(SID_COOKIE_NAME, sessionId);
        cookie.setPath("/");
        cookie.setMaxAge(60 * 60 * 24 * 365);
        return cookie;
    }

    private Optional<String> getSessionIdByCookie(HttpServletRequest request) {
        for(Cookie cookie : request.getCookies()) {
            if (SID_COOKIE_NAME.equals(cookie.getName())) {
                return Optional.of(cookie.getValue());
            }
        }

        return Optional.empty();
    }
}
