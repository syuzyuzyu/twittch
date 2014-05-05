package com.twittchjapan;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@SuppressWarnings("serial")
public class CallbackServlet extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		HttpSession session = req.getSession();
        Twitter twitter = (Twitter)session.getAttribute("Twitter");
        String verifier = req.getParameter("oauth_verifier");

        String word = (String)session.getAttribute("word");
        AccessToken accessToken = null;
 
        try {
            accessToken = twitter.getOAuthAccessToken((RequestToken)session.getAttribute("RequestToken"), verifier);
        } catch (TwitterException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
 
        if(accessToken != null){
            session.setAttribute("AccessToken", accessToken.getToken());
            session.setAttribute("AccessTokenSecret", accessToken.getTokenSecret());
            session.setAttribute("TwitterObj", twitter);
            if(word != null){
            	resp.sendRedirect("/timeLine?word="+word);           	
            }else{
            	resp.sendRedirect("/timeLine?word=nowplaying");
            }
        }else{
            resp.setContentType("text/plain");
            resp.getWriter().println("AccessToken is null");
        }
	}
}