package com._500bottles.da.external.wine.filter;

import com._500bottles.da.external.wine.Attribute;

public class FilterCategory extends Filter
{
	public FilterCategory()
	{
		_filter = "categories";
	}

	public FilterCategory(Attribute a)
	{
		if (a.getAttribute() != null)
		{
			_filter = "categories";
			_filter = _filter + "(" + a.getAttribute() + ")";
		}
	}

	public void addAttribute(Attribute a)
	{
		if (a.getAttribute() != null)
		{
			if (_filter.indexOf('(') == -1)
				_filter = _filter + "(" + a.getAttribute() + ")";
			else
				_filter = _filter.substring(0, _filter.length() - 1) + "+"
						+ a.getAttribute() + ")";
		}
	}
}
