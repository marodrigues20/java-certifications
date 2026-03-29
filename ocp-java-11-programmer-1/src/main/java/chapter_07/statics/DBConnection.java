package chapter_07.statics;


/**
 * ✅ Uso clássico: inicializar constantes que precisam de lógica — como você fazia no Tomcat/JBoss no deploy!
 */
public class DBConnection {
    private static final String URL;

    static {
        String env = System.getenv("ENV");
        URL = env != null ? "prod-db" : "dev-db";
        System.out.println("Classe carregada! URL = " + URL);
    }

    public static String getURL() {
        return URL;
    }
}

