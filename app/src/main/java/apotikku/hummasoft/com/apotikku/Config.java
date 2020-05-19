package apotikku.hummasoft.com.apotikku;

public class Config {

    public static final String BASE_URL = "http://192.168.43.179/"; //Your Local IP Address or Localhost (http://10.0.2.2.)
    public static final String BASE_URL_APOTIK = BASE_URL +"Apotik/";
    public static final String BASE_URL_KAJIAN = BASE_URL +"Apotik/getdatapotik.php/";
    public static final String BASE_URL_GAMBAR = BASE_URL +"Apotik/apotikgambar/";
    public static final String API_URL = BASE_URL+"Apotik";

    public static final String API_LOGIN = API_URL+"/login.php";
    public static final String API_REGISTER  = API_URL+"/register.php";
}

