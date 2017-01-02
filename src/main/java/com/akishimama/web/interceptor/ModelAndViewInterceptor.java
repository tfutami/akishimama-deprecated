package com.akishimama.web.interceptor;

import com.akishimama.web.constraint.ModelAttributeName;
import com.akishimama.web.domain.WebSession;
import com.akishimama.web.filter.WebSessionFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class ModelAndViewInterceptor extends HandlerInterceptorAdapter {
    @Override
    public void postHandle(
            HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
            throws Exception {
        WebSession webSession = (WebSession) request.getAttribute(WebSessionFilter.SESS_ATTR_NAME);
        log.info("path is {}", request.getServletPath());
        log.info("webSession is {}", webSession);
        log.info("webSession user is {}", webSession.getUser());
        modelAndView.addObject(ModelAttributeName.USER, webSession.getUser());
    }

}
