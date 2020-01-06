package net.hycollege.message.utils;

public class CheckObjects {
	/**
	 * if object is null return true, else return false
	 * @param objects
	 * @return
	 */
	public static boolean checkObjectNull(Object ...objects ) {
		for (Object object : objects) {
			if(object == null)
				return true;
		}
		return false;
	}
	/**
	 * if object length equal 0 return true, else return false
	 * @param objects
	 * @return
	 */
	public static boolean checkLeng(String ...strings ) {
		for (String string : strings) {
			if(string.length() == 0)
				return true;
		}
		return false;
	}
}
