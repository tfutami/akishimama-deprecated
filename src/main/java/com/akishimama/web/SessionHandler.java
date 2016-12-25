package com.akishimama.web;

import com.akishimama.domain.Session;
import com.akishimama.domain.SessionRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Slf4j
@Component
public class SessionHandler extends OncePerRequestFilter {

    private static final String SID_COOKIE_NAME = "sid";

    private static final String SESS_ATTR_NAME = "session";

    @Autowired
    private SessionRepository sessionRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (request.getServletPath().startsWith("/assets/")) {
            filterChain.doFilter(request, response);
            return;
        }

        Optional<String> sessionId = getSessionIdByCookie(request);

        Session session = null;
        if (sessionId.isPresent()) {
            session = sessionRepository.findOne(sessionId.get());
        }

        if (session == null) {
            session = new Session();
            session.setSessionId(RandomStringUtils.randomAlphanumeric(12));
            sessionRepository.save(session);
        }

        request.setAttribute(SESS_ATTR_NAME, session);

        response.addCookie(createCookie(session.getSessionId()));
        filterChain.doFilter(request, response);
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

    public Session getSession(HttpServletRequest request) {
        Session session = (Session) request.getAttribute(SESS_ATTR_NAME);

        if (session == null) {
            throw new IllegalArgumentException(
                    "HttpServletRequest has no session attribute.");
        }

        return session;
    }

}
