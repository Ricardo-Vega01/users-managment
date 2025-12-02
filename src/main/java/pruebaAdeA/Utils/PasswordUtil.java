package pruebaAdeA.Utils;

import java.security.MessageDigest;
import java.util.Base64;

public class PasswordUtil {
    public static String encrypt(String password){
        try{
            // Aplicacion del sha-256
            MessageDigest md = MessageDigest.getInstance("SHA-256");

            // Uso de utf-8 para caracteres especiales
            byte[] hash = md.digest(password.getBytes("UTF-8"));
            return Base64.getEncoder().encodeToString(hash);
        }catch (Exception e){
            throw new RuntimeException("Error al encriptar", e);
        }
    }
}
