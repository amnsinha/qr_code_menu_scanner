package com.hotbot.utils;

import org.apache.commons.codec.binary.Base64;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;
import java.util.StringTokenizer;

public class DataHideUtils {

    public static String getMD5(String otp) {
        String result = null;
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("MD5");
            md.update(otp.getBytes(StandardCharsets.UTF_8));
            result = String.format(Locale.ROOT, "%032x",
                    new BigInteger(1, md.digest()));
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException(e);
        }
        return result;
    }

    public static String timetosec(String time) {
        String result = null;
        if (time != null) {
            String[] strings = time.split(":");
            int min = Integer.parseInt(strings[0]);
            int sec = Integer.parseInt(strings[1]);
            result = String.valueOf(min * 60 + sec);
        }
        return result;
    }

    public static String sectotime(int time) {
        String result = null;
        if (time != 0) {
            int min = time / 60;
            int sec = time % 60;
            result = (min < 10 ? "0" + min : min) + ":"
                    + (sec < 10 ? "0" + sec : sec);
        }
        return result;
    }

    public static String normalizeString(String string) {
        if (string != null) {
            string = string.replace(" ", "_");
            string = string.replace("'", "_");
            string = string.replace(":", "_");
            string = string.replace("/", "_");
            string = string.replace("&", "_");
            string = string.replace("%", "_");
            string = string.replace("$", "_");
            string = string.replace("#", "_");
            string = string.replace(".", "_");
        }
        return string;
    }

    public static String createAuthId(String emailId) {

        String authId = "";
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(
                    "yyyy-MM-dd HH:mm:ss.SSS");
            String format = dateFormat.format(new Date());
            Date parsedDate = dateFormat.parse(format);

            Timestamp timeStamp = new Timestamp(parsedDate.getTime());
            authId = timeStamp.toString() + "/" + emailId;
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return authId;
    }

    public static String encrypt(String string) {
        byte[] byteArray = Base64.encodeBase64(string.getBytes());
        String encodedString = new String(byteArray);
        return encodedString;
    }

    public static String decode(String string) {
        byte[] byteArray = Base64.decodeBase64(string);
        String encodedString = new String(byteArray);
        return encodedString;
    }

    public static String decrypt(String string) {
        String emailId = "";

        byte[] byteArray = Base64.decodeBase64(string.getBytes());
        String decodedString = new String(byteArray);

        StringTokenizer tokenizer = new StringTokenizer(decodedString, "/");
        while (tokenizer.hasMoreElements()) {
            tokenizer.nextToken();
            emailId = tokenizer.nextToken();
        }
        return emailId;
    }

    public static String decodeFromBase64(String string) {
        byte[] byteArray = Base64.decodeBase64(string.getBytes());
        String decodedString = new String(byteArray);
        return decodedString;
    }

    public static long getTimeStampdiffrence(String string) {
        String timeStamp = "";
        long diffSeconds = 0;
        String emailId = "";
        byte[] byteArray = Base64.decodeBase64(string.getBytes());
        String decodedString = new String(byteArray);

        StringTokenizer tokenizer = new StringTokenizer(decodedString, "/");
        while (tokenizer.hasMoreElements()) {
            timeStamp = tokenizer.nextToken();
            emailId = tokenizer.nextToken();
        }
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(
                    "yyyy-MM-dd HH:mm:ss.SSS");
            Date parsedDate = dateFormat.parse(timeStamp);
            Timestamp timestamp3 = new Timestamp(parsedDate.getTime());
            long oldTime = timestamp3.getTime();

            Date date = new Date();
            Timestamp timestamp2 = new Timestamp(date.getTime());
            long newTime = timestamp2.getTime();

            diffSeconds = (newTime - oldTime) / 1000;

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return diffSeconds;
    }

    public static String updateAuthId(String authId) {

        String emailId = decrypt(authId);
        String updatedAuthId = createAuthId(emailId);

        return encrypt(updatedAuthId);
    }

    public static String getUniqueIdEncrypt(String anyString) {
        String token = anyString + ":" + Math.random() + ":" + new Date().getTime() + ":" + Math.random();
        String newToken1 = DataHideUtils.encrypt(token);
        String newToken2 = DataHideUtils.encrypt(newToken1);
        String finalToken = newToken2.replaceAll("=", "XSAD4D");
        return finalToken;
    }

    public static String generateRandomString() {

        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String fullalphabet = alphabet + alphabet.toLowerCase() + "123456789";
        Random random = new Random();
        char code = fullalphabet.charAt(random.nextInt(9));
        return Character.toString(code);

    }

    // function to generate a random string of length n
    public static String getAlphaNumericString() {
        int n = 10;
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";
        StringBuilder sb = new StringBuilder(n);

        for (int i = 0; i < n; i++) {
            int index
                    = (int) (AlphaNumericString.length()
                    * Math.random());
            sb.append(AlphaNumericString
                    .charAt(index));
        }
        return sb.toString();
    }

}
