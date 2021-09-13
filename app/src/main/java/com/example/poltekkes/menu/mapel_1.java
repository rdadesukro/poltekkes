//package com.example.poltekkes.menu;
//
//import android.content.ContentValues;
//import android.content.Context;
//import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteOpenHelper;
//
//
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class mapel_1 extends SQLiteOpenHelper {
//    final static String DB_NAME = "db_1";
//
//    public mapel_1(Context context) {
//        super(context, DB_NAME, null, 1);
//        // TODO Auto-generated constructor stub
//    }
//
//    @Override
//    public void onCreate(SQLiteDatabase db) {
//        String sql = "CREATE TABLE IF NOT EXISTS tbl_soal1(id INTEGER PRIMARY KEY AUTOINCREMENT, soal TEXT, pil_a TEXT, pil_b TEXT, pil_c TEXT, jwban INTEGER, img BLOB)";
//        db.execSQL(sql);
//
//        ContentValues values = new ContentValues();
//        values.put("soal", "Teknik dasar yang wajib pertama kali dipelajari oleh pemula ialah ...");
//        values.put("pil_a", "servis");
//        values.put("pil_b", "passing");
//        values.put("pil_c", "blocking");
//        values.put("jwban", "1");
//        //  values.put("img", R.drawable.sapi);
//        db.insert("tbl_soal1", "soal", values);
//
//        values.put("soal", "Permainan bola voli diciptakan oleh ...");
//        values.put("pil_a", "James A. Naismith");
//        values.put("pil_b", "Hasley. T");
//        values.put("pil_c", "Wiliam G. Morgan");
//        values.put("jwban", "2");
//        //  values.put("img", R.drawable.hidup);
//        db.insert("tbl_soal1", "soal", values);
//
//        values.put("soal", "Permainan bola voli diciptakan pada tahun ...");
//        values.put("pil_a", "1875");
//        values.put("pil_b", "1885");
//        values.put("pil_c", "1895");
//        values.put("jwban", "2");
//        //  values.put("img", R.drawable.iakan);
//        db.insert("tbl_soal1", "soal", values);
//
//        values.put("soal", "Jumlah pemain bola voli dam satu regu adalah ...");
//        values.put("pil_a", "5 orang");
//        values.put("pil_b", "6 orang");
//        values.put("pil_c", "7 orang");
//        values.put("jwban", "1");
//        //  values.put("img", R.drawable.ramb);
//        db.insert("tbl_soal1", "soal", values);
//
//        values.put("soal", "bola voli berasal dari negara ...");
//        values.put("pil_a", "Amerika Serikat");
//        values.put("pil_b", "Inggris");
//        values.put("pil_c", "Prancis");
//        values.put("jwban", "0");
//        // values.put("img", R.drawable.ayam);
//        db.insert("tbl_soal1", "soal", values);
//
//        values.put("soal", "FIVB terbentuk pada tahun ...");
//        values.put("pil_a", "1945");
//        values.put("pil_b", "1946");
//        values.put("pil_c", "1947");
//        values.put("jwban", "2");
//        //  values.put("img", R.drawable.tebu);
//        db.insert("tbl_soal1", "soal", values);
//
//        values.put("soal", "FIVB dibentuk di negara ...");
//        values.put("pil_a", "Amerika serikat");
//        values.put("pil_b", "Inggris");
//        values.put("pil_c", "Prancis");
//        values.put("jwban", "2");
//        //  values.put("img", R.drawable.kelapa);
//        db.insert("tbl_soal1", "soal", values);
//
//        values.put("soal", "Pemain yang bertugas untuk melakukan pukulan agar bola jatuh di daerah lawan disebut ...");
//        values.put("pil_a", "spiker");
//        values.put("pil_b", "toser");
//        values.put("pil_c", "libero");
//        values.put("jwban", "0");
//        //  values.put("img", R.drawable.tikus);
//        db.insert("tbl_soal1", "soal", values);
//
//
//        values.put("soal",  "Set-upper bertugas sebagai...");
//        values.put("pil_a", "memukul bola ke daerah lawan");
//        values.put("pil_b", "menerima serangan dari lawan");
//        values.put("pil_c", "mengumpankan bola pada teman");
//        values.put("jwban", "2");
//        // values.put("img", R.drawable.padi);
//        db.insert("tbl_soal1", "soal", values);
//
//        values.put("soal", "Panjang dan lebar lapangan bola voli adalah ...");
//        values.put("pil_a", "20 m x 11 m");
//        values.put("pil_b", "18 m x  11 m");
//        values.put("pil_c", "18 m x 9 m");
//        values.put("jwban", "2");
//        //  values.put("img", R.drawable.burung);
//        db.insert("tbl_soal1", "soal", values);
//
//        String sql2 = "CREATE TABLE IF NOT EXISTS tbl_gambar(id INTEGER PRIMARY KEY AUTOINCREMENT, nama TEXT, img BLOB)";
//        db.execSQL(sql2);
//
//        ContentValues v = new ContentValues();
//        v.put("nama", "Manfaat hewan");
//        // values.put("img", R.drawable.sapi);
//        db.insert("tbl_gambar1", "nama", v);
//
//        v.put("nama", "Manfaat hewan");
//        //   values.put("img", R.drawable.burung);
//        db.insert("tbl_gambar1", "nama", v);
//
//    }
//
//    public List<Soal> getSoal() {
//        List<Soal> listSoal = new ArrayList<Soal>();
//        String query = "select * from tbl_soal1";
//
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor cursor = db.rawQuery(query, null);
//
//        if (cursor.moveToFirst()) {
//            do {
//                Soal s = new Soal();
//                s.setSoal(cursor.getString(1));
//                s.setPil_a(cursor.getString(2));
//                s.setPil_b(cursor.getString(3));
//                s.setPil_c(cursor.getString(4));
//                s.setJwban(cursor.getInt(5));
//                s.setGambar(cursor.getInt(6));
//                listSoal.add(s);
//            } while (cursor.moveToNext());
//        }
//        return listSoal;
//    }
//
//    @Override
//    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        db.execSQL("DROP TABLE IF EXISTS tbl_soal1");
//        db.execSQL("DROP TABLE IF EXISTS tbl_gambar1");
//    }
//}