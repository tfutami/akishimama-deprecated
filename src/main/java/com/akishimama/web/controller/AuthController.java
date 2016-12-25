package com.akishimama.web.controller;

import com.akishimama.domain.*;
import com.akishimama.web.SessionHandler;
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
    private SessionHandler sessionHandler;

    @Autowired
    private SessionRepository sessionRepository;

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

        Session session = sessionHandler.getSession(request);

        Session storedSession = sessionRepository.findOne(session.getSessionId());

        if (storedSession != null) {
            storedSession.setUser(twitterAuth.getUser());
        }
        else {
            session.setUser(twitterAuth.getUser());
            sessionRepository.save(session);
        }

        return "redirect:/";
    }
}
