package com.allin.common.tool;

import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Date;

import com.allin.common.constant.Constants;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializer;

public final class JsonUtils {
	private static final GsonBuilder gsonBuilder = getGsonBuilder();
	private static final Gson gson = gsonBuilder.create();

	private JsonUtils() {
	}

	/**
	 * Create a Gson
	 * 
	 * @return
	 */
	public static final Gson createGson() {
		return gsonBuilder.create();
	}

	/**
	 * 
	 * @param json
	 * @param clazz
	 *            the class of T
	 * @return an object of type T from the string. Returns null if json is
	 *         null.
	 */
	public static <T> T fromJson(final String json, final Class<T> clazz) {
		return gson.fromJson(json, clazz);
	}

	/**
	 * 
	 * @param json
	 * @param clazz
	 *            the class of T
	 * @return an object of type T from the string. Returns null if json is
	 *         null.
	 */
	public static <T> T fromJson(final String json, final Type typeOfT) {
		return gson.fromJson(json, typeOfT);
	}

	/**
	 * 
	 * @param src
	 *            the object for which Json representation is to be created
	 *            setting for Gson
	 * @return Json representation of src
	 */
	public static String toJson(final Object src) {
		return gson.toJson(src);
	}

	/**
	 * Gets a GsonBuilder instance that has been used to build Gson with various
	 * configuration settings. <br>
	 * 
	 * @return
	 */
	private static final GsonBuilder getGsonBuilder() {
		final GsonBuilder builder = new GsonBuilder();

		/** convert Date to Long */
		final JsonSerializer<Date> dateSerializer = (date, typeOfSrc, context) -> date == null ? null
				: new JsonPrimitive(date.getTime());

		/** convert String to Date */
		final JsonDeserializer<Date> dateDeserializer = (json, typeOfT, context) -> toDate(json);

		/** convert String to Timestamp */
		final JsonDeserializer<Timestamp> timestampDeserializer = (json, typeOfT,
				context) -> new Timestamp(toDate(json).getTime());

		builder.registerTypeAdapter(Date.class, dateSerializer).registerTypeAdapter(Date.class, dateDeserializer)
				.registerTypeAdapter(Timestamp.class, timestampDeserializer)
				.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).serializeNulls();

		return builder;
	}

	private static Date toDate(JsonElement json) {
		final String jsonStr = json.getAsString();

		try {
			return new Date(json.getAsJsonPrimitive().getAsLong());
		} catch (NumberFormatException e) {
		}

		try {
			return DateUtils.toDate(jsonStr, Constants.DATE_FORMAT_PATTRN4);
		} catch (ParseException e) {
		}

		try {
			return DateUtils.toDate(jsonStr, Constants.DATE_FORMAT_PATTRN3);
		} catch (ParseException e) {
		}

		try {
			return DateUtils.toDate(jsonStr, Constants.DATE_FORMAT_PATTRN2);
		} catch (ParseException e) {
		}

		try {

			return DateUtils.toDate(jsonStr, Constants.DATE_FORMAT_PATTRN1);
		} catch (ParseException e) {
		}

		throw new JsonParseException("Fail to parse this JsonElement to Date, JsonElement:" + json);
	}

}
