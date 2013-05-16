/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openinfinity.web.controller;

import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Jarno Knihtilä
 */
@Controller
public class LoginController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    @RequestMapping(value = "/userRedirect", method = RequestMethod.GET)
    public String moderatorHome(Model model, HttpServletRequest request) {
        LOGGER.info("Login requested");

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("username", user.getUsername());

        if (isModerator(user)) {
            return "moderator/moderator";
        } else {
            return "user/user";
        }        
    }

    private boolean isModerator(User user) {
        for (GrantedAuthority a : user.getAuthorities()) {
            if (a.getAuthority().equalsIgnoreCase("moderator")) {
                return true;
            }
        }
        return false;
    }
}
