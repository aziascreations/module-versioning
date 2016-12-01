package com.azias.module.version;

import java.lang.reflect.Type;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class VersionSerialiser implements JsonSerializer<Version> {
	
	@Override
	public JsonElement serialize(final Version version, final Type typeOfSrc, final JsonSerializationContext context) {
		final JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("version", version.getOriginalVersion());
		jsonObject.addProperty("friendlyName", version.getFriendlyName());
		return jsonObject;
	}
}
