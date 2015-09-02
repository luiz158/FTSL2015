/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pgxp;

import java.lang.reflect.Field;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author gladson
 */
public class Util {

    public static boolean fieldInClass(String field, Class classe) {
        Field[] methods = classe.getDeclaredFields();
        for (Field field1 : methods) {
            if (field1.getName().equalsIgnoreCase(field)) {
                return true;
            }
        }
        return false;
    }

    public static String md5(String senha) {
        String sen = "";
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        BigInteger hash = new BigInteger(1, md.digest(senha.getBytes()));
        sen = hash.toString(16);
        return sen;
    }
}
