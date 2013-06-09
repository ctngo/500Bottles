package com._500bottles.dispatch;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com._500bottles.action.RatingsAction;

public class RatingsDispatch extends HttpServlet
{
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{
		String action = request.getParameter("action");
		String wineId = request.getParameter("wineId");
		String rating = request.getParameter("rating");

		if (action == "addRating")
		{
			setRating(Long.parseLong(wineId), Double.parseDouble(rating));
		} else if (action == "removeRating")
		{
			removeRating(Long.parseLong(wineId));
		} else if (action == "getRating")
		{
			getRating(Long.parseLong(wineId));
		}

	}

	private void setRating(long wineId, double rating)
	{
		RatingsAction.setRating(wineId, rating);
	}

	private void removeRating(long wineId)
	{
		RatingsAction.removeRating(wineId);
	}

	private double getRating(long wineId)
	{
		return RatingsAction.getRating(wineId);
	}
}
