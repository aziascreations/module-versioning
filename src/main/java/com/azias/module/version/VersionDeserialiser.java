package com.azias.module.version;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

public class VersionDeserialiser implements JsonDeserializer<Version> {

	@Override
	public Version deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
		JsonObject jObject = (JsonObject) json;
		try {
			Version a = new Version(jObject.get("version").getAsString());
			a.setFriendlyName(jObject.get("friendlyName").getAsString());
			return a;
		} catch (IllegalVersionFormatException e) {
			throw new JsonParseException(e);
		}
	}
}
