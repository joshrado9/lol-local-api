package com.rado.app.api;

import org.json.simple.JSONObject;

public class ImageDto
{
	public String full;
	public String group;
	public String sprite;
	public long h;
	public long w;
	public long y;
	public long x;

	public ImageDto(JSONObject image)
	{
		this.full = (String) image.get("full");
		this.group = (String) image.get("group");
		this.sprite = (String) image.get("sprite");
		this.h = (long) image.get("h");
		this.w = (long) image.get("w");
		this.y = (long) image.get("y");
		this.x = (long) image.get("x");
	}

}
