import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class Main {
    public enum Season{
        A,B,C,D
    }

    public static void main(String[] args) {
        /*
        * 加载类
        * */
        URL[] urls= new URL[0];
        try {
            urls = new URL[]{new URL("file:/" + System.getProperty("user.dir") + "/ravageWeb/")};
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        URLClassLoader classLoader = new URLClassLoader(urls);

    }
}
