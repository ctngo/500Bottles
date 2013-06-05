package com._500bottles.manager;

import com._500bottles.da.internal.FavoritesDAO;
import com._500bottles.da.internal.WineDAO;
import com._500bottles.exception.da.DAException;
import com._500bottles.object.wine.CustomWine;
import com._500bottles.object.wine.Favorites;
import com._500bottles.object.wine.Wine;
import com._500bottles.object.wine.WineQuery;
import com._500bottles.object.wine.WineQueryResult;

public class WineManager
{
	/**
	 * Gets wine by specified ID
	 * 
	 * @param id
	 *            ID of wine to be returned
	 * @return Wine object of specifed Wine ID
	 */
	public static Wine getWine(long wineId)
	{
		Wine wine = null;

		try
		{
			wine = WineDAO.getWine(wineId);
		} catch (DAException e)
		{
			// TODO:
		}

		return wine;
	}

	/**
	 * Gets and returns a Wine by snoothId
	 * 
	 * @param snoothId
	 *            of the wine to be returned
	 * @return The Wine that will be returned by snoothId
	 */
	static Wine getWineBySnoothId(String snoothId)
	{
		Wine resultWine = null, searchWine = new Wine();
		searchWine.setSnoothId(snoothId);

		try
		{
			resultWine = WineDAO.getWine(searchWine);
			System.out.println("found a snooth");
		} catch (DAException e)
		{
			System.out.println("did not find a snooth");
			return null;
		}

		return resultWine;
	}

	/**
	 * Get the Wine associated with the WineComID
	 * 
	 * @param id
	 *            WineComId of the wine to be returned
	 * @return The wine associated with the WineComId
	 */
	static Wine getWineByWineComId(long wineComId)
	{
		Wine resultWine = null, searchWine = new Wine();
		searchWine.setWinecomId(wineComId);

		try
		{
			resultWine = WineDAO.getWine(searchWine);
			System.out.println("found a winecom");
		} catch (DAException e)
		{
			System.out.println("Did not find winecom");
			return null;
		}

		return resultWine;
	}

	/**
	 * Return WineQueryResult from Specified Wine Query
	 * 
	 * @param query
	 *            The WineQuery to be searched with
	 * @return The WineQueryResult of the WineQuery search
	 */
	public static WineQueryResult searchWine(WineQuery query)

	{
		WineQueryResult result = null;

		try
		{
			result = WineQueryManager.search(query);
		} catch (Exception e)
		{
			// throws InvalidCategory, InvalidSort, InvalidOtherParameters,
			// IOException, ParseException, DAException

			// TODO: fix this mess of shit.
		}

		return result;
	}

	public static void addCustomWine(CustomWine w)
	{
		try
		{
			WineDAO.addWine(w);
		} catch (DAException e)
		{
			// TODO Auto-generated catch block
		}
	}

	public static void editCustomWine(CustomWine w)
	{
		try
		{
			WineDAO.editWine(w);
		} catch (DAException e)
		{
			// TODO Auto-generated catch block
		}
	}

	public static boolean deleteCustomWine(long id)
	{
		Wine w = getWine(id);
		return WineDAO.deleteWine(w);
	}

	/**
	 * Set the rating of the specified Wine using the specified rating
	 * 
	 * @param id
	 *            The ID of the wine that will be set with the specified rating
	 * @param rating
	 *            The rating of the that specified wine will be set to
	 */
	public static void setRating(long id, double rating)
	{
		Wine w = getWine(id);
		w.setRating(rating);
		try
		{
			WineDAO.editWine(w);
		} catch (DAException e)
		{
			// TODO Auto-generated catch block
		}
	}

	/*
	 * public void addTastingNotes(String notes) { }
	 * 
	 * public void editTastingNotes() { }
	 * 
	 * public void deleteTastingNotes() { }
	 */

	/**
	 * Set the specified wine to favorite
	 * 
	 * @param w
	 *            The wine to be set to favorite
	 * @throws DAException
	 */
	public static void setFavorite(Wine w) throws DAException
	{
		Favorites fave = new Favorites();
		fave.setWineId(w.getId());
		long userId = SessionManager.getSessionManager().getLoggedInUser()
				.getUserId();
		FavoritesDAO.addFavorite(userId, fave);

	}

	/**
	 * 
	 * @param id
	 *            - the wine Id
	 * @return boolean, if this wine id is in the favorites
	 * @throws DAException
	 */
	public static boolean isFavorite(long wineId) throws DAException
	{
		Favorites fave = new Favorites();

		fave = FavoritesDAO.getFavorite(wineId);
		if (fave == null)
		{
			return false;
		}

		return true;
	}

}
