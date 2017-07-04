package ru.pearx.lib;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/*
 * Created by mrAppleXZ on 04.07.17 15:33.
 */

/**
 * PearXLib hashing related utils.
 */
public class HashingUtils
{
    /**
     * Gets a hash of "bts" using "name" algorithm.
     * @param name Algorithm name.
     * @param bts Byte array.
     * @return A hash.
     */
    public static String getHash(String name, byte[] bts)
    {
        try
        {
            return PXL.getHexString(MessageDigest.getInstance(name).digest(bts));
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Gets a hash of InputStream using "name" algorithm.
     * @param name Algorithm name.
     * @param str Input Stream.
     * @return A hash.
     */
    public static String getHash(String name, InputStream str)
    {
        try(DigestInputStream dig = new DigestInputStream(str, MessageDigest.getInstance(name)))
        {
            int num;
            do
                num = dig.read();
            while(num != -1);
            return PXL.getHexString(dig.getMessageDigest().digest());
        }
        catch (NoSuchAlgorithmException | IOException e)
        {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Gets a hash of "text" (in UTF-8) using "name" algorithm.
     * @param name Algorithm name.
     * @param text Text that will be converted to UTF-8 byte array.
     * @return A hash.
     */
    public static String getHash(String name, String text)
    {
        return getHash(name, text.getBytes(StandardCharsets.UTF_8));
    }
}
