/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pgxp.security;

import java.util.Iterator;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import javax.enterprise.context.ApplicationScoped;
import pgxp.domain.Usuario;

/**
 *
 * @author gladson
 */
@ApplicationScoped
public class StoreToken {

    private static final Map<String, Token> serviceKeysStorage = new ConcurrentHashMap<String, Token>();

    public static boolean verifyToken(String token) {
        if (token == null) {
            return false;
        }
        Token objeto = serviceKeysStorage.get(token);
        if (objeto != null) {
            if (objeto.getTempo() > System.currentTimeMillis()) {
                return serviceKeysStorage.containsKey(token);
            }
        }
        return false;
    }

    public static void removeToken(String token) {
        serviceKeysStorage.remove(token);
    }

    public static Usuario getUsuario(String token) {
        Token objeto = serviceKeysStorage.get(token);
        if (objeto != null) {
            if (objeto.getTempo() > System.currentTimeMillis()) {
                objeto.setTempo(System.currentTimeMillis() + 3600000);
                return objeto.getUsuario();
            } else {
                serviceKeysStorage.remove(token);
            }
        }

        return null;
    }

    public static String insertToken(Usuario idUsu) {
        String chave = UUID.randomUUID().toString();
        Token objeto = new Token();
        objeto.setTempo(System.currentTimeMillis() + 3600000);
        objeto.setUsuario(idUsu);
        serviceKeysStorage.put(chave, objeto);
        return chave;
    }

    public static void limpaToken() {
        Iterator<String> keySetIterator = serviceKeysStorage.keySet().iterator();

        while (keySetIterator.hasNext()) {
            String chave = keySetIterator.next();
            Token objeto = serviceKeysStorage.get(chave);
            if (objeto != null) {
                if (objeto.getTempo() < System.currentTimeMillis()) {
                    removeToken(chave);
                }
            }
        }

    }

}
