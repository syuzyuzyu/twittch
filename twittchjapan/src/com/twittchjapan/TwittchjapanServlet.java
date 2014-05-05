package com.twittchjapan;

import java.io.IOException;
import javax.servlet.http.*;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.RequestToken;

@SuppressWarnings("serial")
public class TwittchjapanServlet extends HttpServlet {
	public static final String consumerKey = "rjg4B5xJCLPHWGqy3IbQ";
	public static final String consumerSecret = "5ctC7P7BzWS6MkU38bDd2tfDuliX2Cx7JlxGnUFVHWc";
	static TwitterFactory twitterFactory = new TwitterFactory();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		
		StringBuffer callbackURL = request.getRequestURL();
		int index = callbackURL.lastIndexOf("/");
		callbackURL.replace(index, callbackURL.length(), "").append("/callback");
		
		String word = request.getParameter("word");
		HttpSession session = request.getSession();	
		session.setAttribute("word", word);

		Twitter twitter = twitterFactory.getInstance();
		twitter.setOAuthConsumer(consumerKey, consumerSecret);
		request.getSession().setAttribute("Twitter", twitter);
		try {
			RequestToken requestToken = twitter.getOAuthRequestToken();
			request.getSession().setAttribute("RequestToken", requestToken);
			response.sendRedirect(requestToken.getAuthenticationURL());
		} catch (TwitterException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}
