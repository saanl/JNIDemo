package myapp.com.jnidemo;

public class HelloCmake {

    static {
        System.loadLibrary("hellondk");
    }

    public native String getString();
    public native String getStringWithParam(String myStr);
    public static native String getStringFromNDK();

    public String get(){
        return getString() + getStringFromNDK();
    }
    public String get(String str){
        return getStringWithParam(str);
    }
}
