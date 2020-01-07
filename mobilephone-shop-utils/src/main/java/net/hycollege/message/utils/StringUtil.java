package net.hycollege.message.utils;

public class StringUtil {
	public static String contactString(String... strings) {
		StringBuilder builder = new StringBuilder();
		for (String string : strings) {
			builder.append(string);
		}
		return builder.toString();
	}
}
