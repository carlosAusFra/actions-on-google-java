package ca.sukhsingh.actions.on.google;

/**
 * Created by sinsukhv on 2017-08-28.
 */
public class Util {

    public static boolean isNull(Object obj) {
        if (obj == null) {
            return true;
        }
        return false;
    }

    public static boolean isNullOrEmpty(String string) {
        if (string == null && string.isEmpty()) {
            return true;
        }
        return false;
    }
}
