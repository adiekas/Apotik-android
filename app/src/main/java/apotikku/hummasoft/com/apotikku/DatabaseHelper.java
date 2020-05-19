//package apotikku.hummasoft.com.apotikku;
//
//import android.content.Context;
//import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteOpenHelper;
//
//public class DatabaseHelper extends SQLiteOpenHelper {
//    // Table Name
//    public static final String TABLE_NAME = "COUNTRIES";
//
//   // Table columns
//    public static final String _ID = "_id";
//    public static final String ID_MASJID = "idmasjid";
//    public static final String SUBJECT = "subject";
//    public static final String DESC = "description";
//
//    public static final String deskripsi = "deskripsi";
//    public static final String luastanah = "luastanah";
//    public static final String statustanah = "statustanah";
//    public static final String luasbangunan = "luasbangunan";
//    public static final String tahunberdiri = "tahunberdiri";
//    public static final String notelepon = "notelepon";
//    public static final String ketarangan = "ketarangan";
//    public static final String fasilitas = "fasilitas";
//    public static String latitude = "latitude";
//    public static String longitude = "longitude";
//    public static final String profil = "porfil";
//
//    //Database information
//    static final String DM_NAME = "JOURNALDEY_COUNTRIES.DB";
//
//    //database version
//    static final int DB_VERSION = 1;
//
//    //Crreating Table Query
//    private static final String CREATE_TABLE = "create table" +TABLE_NAME +"(" +_ID
//            +"INTEGER PRIMARY KEY AUTOINCREMENT,"+SUBJECT+"TEXT NOT NULL, "+
//            IDMASJID+ "TEXT,"+ deskripsi+"TEXT,"+luastanah+"TEXT,"+statustanah+"TEXT,"+
//            ketarangan+"TEXT,"+luasbangunan+"TEXT,"+tahunberdiri+"TEXT,"+notelepon+"TEXT,"+
//            fasilitas+"TEXT,"+latitude+"TEXT,"+longitude+"TEXT,"+profil+"TEXT,"+DESC+"TEXT);";
//
//    public DatabaseHelper(Context context){
//        super(context, DB_NAME, null, DB_VERSION);
//    }
//
//    @Override
//    public void onCreate(SQLiteOpenHelper db){
//        db.execSQL(CREATE_TABLE);
//    }
//
//     @Override
//     public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
//           db.exceSQL("DROP TABLE IF EXISTS"+ TABLE_NAME);
//           onCreate( db );
//     }
//}
