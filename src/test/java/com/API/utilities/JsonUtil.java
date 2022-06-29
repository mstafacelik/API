package com.API.utilities;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;

public class JsonUtil {

    private static ObjectMapper mapper;  // import codehaus.jackson'dan

    static {                            // en once static block calisir
        mapper = new ObjectMapper();
    }

    // <T>, data type'i herhangi bir data type olabilir anlamina gelir.
    // Data type'i belli olamayan yapilarda bu kullanilir

    public static <T> T convertJsonToJava(String json, Class<T> cls) {
        T javaResult = null;
        try {
            javaResult = mapper.readValue(json, cls);
        } catch (IOException e) {
            System.err.println("json datası javaya dönüştürülemedi"); // err --> error mesajlari icin kullanilir
        }
        return javaResult;
    }


}
