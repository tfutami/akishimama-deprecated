package com.akishimama.web.controller;

import com.akishimama.web.domain.TwitterAuth;
import com.akishimama.web.domain.User;
import com.akishimama.web.domain.WebSession;
import com.akishimama.web.repository.TwitterAuthRepository;
import com.akishimama.web.repository.UserRepository;
import com.akishimama.web.repository.WebSessionRepository;
import com.akishimama.web.filter.WebSessionFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.TwitterProfile;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(path = "auth")
@Slf4j
public class AuthController {

    @Autowired
    private Twitter twitter;

    @Autowired
    private ConnectionRepository connectionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TwitterAuthRepository twitterAuthRepository;

    @Autowired
    private WebSessionRepository webSessionRepository;

    @Transactional
    @RequestMapping(
            path = "twitter",
            method = RequestMethod.GET
    )
    public String doGet(
            HttpServletRequest request
    ) {
        if (connectionRepository.findPrimaryConnection(Twitter.class) == null) {
            return "redirect:/";
        }

        TwitterProfile profile = twitter.userOperations().getUserProfile();

        TwitterAuth twitterAuth = twitterAuthRepository.findOne(String.valueOf(profile.getId()));

        if (twitterAuth == null) {
            log.info("Accessed by new user. twitter auth info is {}", twitter.userOperations().getUserProfile());

            User user = new User();
            user.setNickname(profile.getName());
            userRepository.save(user);

            twitterAuth = new TwitterAuth();
            twitterAuth.setTwitterId(String.valueOf(profile.getId()));
            twitterAuth.setUser(user);

            twitterAuthRepository.save(twitterAuth);
        } else {
            log.info("we know him. he is {}", twitterAuth.getUser());
        }

        WebSession webSession = (WebSession) request.getAttribute(WebSessionFilter.SESS_ATTR_NAME);
        WebSession storedWebSession = webSessionRepository.findOne(webSession.getSessionId());
        storedWebSession.setUser(twitterAuth.getUser());

        return "redirect:/";
    }
}
