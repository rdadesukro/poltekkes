//package com.example.poltekkes.menu.menu;
//
//import androidx.appcompat.app.AlertDialog;
//import androidx.appcompat.app.AppCompatActivity;
//
//import cn.pedant.SweetAlert.SweetAlertDialog;
//
//import android.annotation.SuppressLint;
//import android.annotation.TargetApi;
//import android.content.Context;
//import android.content.DialogInterface;
//import android.content.Intent;
//import android.content.SharedPreferences;
//import android.graphics.Color;
//import android.os.Build;
//import android.os.Bundle;
//import android.os.CountDownTimer;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.ImageView;
//import android.widget.RadioButton;
//import android.widget.RadioGroup;
//import android.widget.TextView;
//import android.widget.Toast;
//
//
//import com.example.poltekkes.R;
//import com.example.poltekkes.model.Soal;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Calendar;
//import java.util.Collections;
//import java.util.List;
//import java.util.concurrent.TimeUnit;
//
//public class quiz_satu extends AppCompatActivity {
//    private mapel_1 db;
//    private TextView txtnama, txtno, txttanggal, txtwaktu, txtsoal,txtnis;
//    private ImageView img;
//    private RadioGroup rg;
//    private RadioButton rdA, rdB, rdC,rdD;
//    private List<Soal> listSoal;
//    private CounterClass mCountDownTimer;
//    private int detik = 300000; // --> 10 menit
//    private Button btnPrev, btnNext, btnSelesai;
//    int jawabanYgDiPilih[] = null;
//    int jawabanYgBenar[] = null;
//    boolean cekPertanyaan = false;
//    int urutanPertanyaan = 0;
//    String noSalah = "";
//    private static final String TAG_SUCCESS = "success";
//    private static final String TAG_MESSAGE = "message";
//    private String KEY_ID = "id_profil";
//    TextView nilai;
//
//    private static final String TAG = quiz_satu.class.getSimpleName();
//    SweetAlertDialog pd_new;
//    TextView mapel;
//
//    int total;
//    int success;
//
//    EditText inputUser,inputnis;
//    TextView sts;
//    SharedPreferences sharedpreferences;
//
//    ImageView hapus;
//
//    Boolean session = false;
//
//    String id_guru,id_kelas;
//    String id,nis,nama;
//
//    public final static String TAG_nis = "nis_ambil";
//    public final static String TAG_STATUS = "status";
//    public final static String TAG_GURU = "id_guru";
//    public final static String TAG_NAMA = "nama";
//    public final static String TAG_KELAS = "kelas";
//    public final static String TAG_ID_KELAS = "id_kelas";
//    public static final String my_shared_preferences = "my_shared_preferences";
//
//    public static final String session_status = "session_status";
//    String validasi;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.quiz_satu);
//        db = new mapel_1(this);
//        txtnama = (TextView) findViewById(R.id.textViewNama);
//        txtnis = (TextView) findViewById(R.id.txt_nis);
//        txtno = (TextView) findViewById(R.id.textViewHalaman);
//        txttanggal = (TextView) findViewById(R.id.textViewTanggal);
//       // sts = (TextView) findViewById(R.id.txt_sts);
//        nilai = (TextView) findViewById(R.id.txt_nilai);
//        mapel = (TextView) findViewById(R.id.txt_mapel);
//        txtwaktu = (TextView) findViewById(R.id.textViewWaktu);
//        txtsoal = (TextView) findViewById(R.id.textViewSoal);
//        img = (ImageView) findViewById(R.id.gambarKuis);
//        sharedpreferences = getSharedPreferences(my_shared_preferences, Context.MODE_PRIVATE);
//        session = sharedpreferences.getBoolean(session_status, false);
//        nis = sharedpreferences.getString(TAG_nis, null);
//        nama = sharedpreferences.getString(TAG_NAMA, null);
//        id_guru = sharedpreferences.getString(TAG_GURU, null);
//        id_kelas = sharedpreferences.getString(TAG_ID_KELAS, null);
//
//        Log.i("data_quiz", "onCreate: "+nis+" "+nama+" "+id_guru+" "+id_kelas);
//
//
//        pd_new = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
//        pd_new.getProgressHelper().setBarColor(Color.parseColor("#3395ff"));
//        rg = (RadioGroup) findViewById(R.id.radioGroup1);
//        rdA = (RadioButton) findViewById(R.id.radio0);
//        rdB = (RadioButton) findViewById(R.id.radio1);
//        rdC = (RadioButton) findViewById(R.id.radio2);
//        rdD = (RadioButton) findViewById(R.id.radio3);
//        btnPrev = (Button) findViewById(R.id.buttonPrev);
//        btnNext = (Button) findViewById(R.id.buttonNext);
//        btnSelesai = (Button) findViewById(R.id.buttonSelesai);
//        final Calendar c = Calendar.getInstance();
//        int day = c.get(Calendar.DAY_OF_MONTH);
//        int month = c.get(Calendar.MONTH);
//        int year = c.get(Calendar.YEAR);
//        txttanggal.setText(Integer.toString(day)+"-"+Integer.toString(month+1)+"-"+Integer.toString(year));
//
//        listSoal = new ArrayList<Soal>();
//        listSoal = db.getSoal();
//
//        btnSelesai.setOnClickListener(klikSelesai);
//        btnPrev.setOnClickListener(klikSebelum);
//        btnNext.setOnClickListener(klikBerikut);
//        //new GetSoal().execute();
//        jawabanYgDiPilih = new int[listSoal.size()];
//        Arrays.fill(jawabanYgDiPilih, -2);
//        jawabanYgBenar = new int[listSoal.size()];
//        Arrays.fill(jawabanYgBenar, -1);
//        showInputUser();
//    }
//
//    private void showInputUser() {
//        LayoutInflater mInflater = LayoutInflater.from(this);
//        View v = mInflater.inflate(R.layout.nama_anda, null);
//
//        final AlertDialog dialog = new AlertDialog.Builder(this).create();
//
//        dialog.setView(v);
//        // dialog.setTitle("Ketikkan Nama Anda");
//        dialog.setCancelable(false);
//
//        final Button btnOk = (Button) v.findViewById(R.id.buttonOK);
//        final Button btn_batal = (Button) v.findViewById(R.id.btn_btl);
//
//        inputUser = (EditText) v.findViewById(R.id.editTextNama);
//        inputnis = (EditText) v.findViewById(R.id.editTextnis);
//
//        inputUser.setText(nama);
//        inputnis.setText(nis);
//        btnOk.setOnClickListener(new View.OnClickListener()
//        {
//
//            @Override
//            public void onClick(View v)
//            {
//                if(inputUser.getText().toString().equals("")){
//                    Toast.makeText(getBaseContext(), "Isi dulu", Toast.LENGTH_LONG).show();
//                }else{
//                    txtnama.setText(inputUser.getText().toString());
//                    txtnis.setText(inputnis.getText().toString());
//                    mulaiKuis();
//                    dialog.dismiss();
//                }
//
//            }
//        });
//
//        dialog.show();
//        btn_batal.setOnClickListener(new View.OnClickListener()
//        {
//
//            @Override
//            public void onClick(View v)
//            {
//                startActivity(new Intent(getApplication(),menu_utama.class));
//
//            }
//        });
//
//    }
//
//    private void mulaiKuis() {
//        cler();
//        setUpWaktu();
//        setUpSoal();
//    }
//
//    private void setUpSoal() {
//        Collections.shuffle(listSoal);
//        this.tunjukanPertanyaan(0, cekPertanyaan);
//    }
//
//    private void cler() {
//        mCountDownTimer = new CounterClass(detik, 1000);
//        mCountDownTimer.cancel();
//    }
//    private void setUpWaktu() {
//        mCountDownTimer = new CounterClass(detik, 1000);
//        mCountDownTimer.start();
//    }
//    private void tunjukanPertanyaan(int urutan_soal_soal, boolean cekPertanyaan) {
//        btnSelesai.setEnabled(false);
//        try {
//            rg.clearCheck();
//            Soal soal = new Soal();
//            soal = listSoal.get(urutan_soal_soal);
//            String pertanyaan = soal.getSoal();
//            if (jawabanYgBenar[urutan_soal_soal] == -1) {
//                jawabanYgBenar[urutan_soal_soal] = soal.getJwban();
//            }
//            int gambar = soal.getGambar();
//            txtsoal.setText(pertanyaan.toCharArray(), 0, pertanyaan.length());
//            img.setImageResource(gambar);
//            rg.check(-1);
//            String jwb_a = soal.getPil_a();
//            rdA.setText(jwb_a.toCharArray(), 0,
//                    jwb_a.length());
//            String jwb_b = soal.getPil_b();
//            rdB.setText(jwb_b.toCharArray(), 0,
//                    jwb_b.length());
//            String jwb_c = soal.getPil_c();
//            rdC.setText(jwb_c.toCharArray(), 0,
//                    jwb_c.length());
////            String jwb_d = soal.getPil_d();
////            rdD.setText(jwb_d.toCharArray(), 0,
////                    jwb_d.length());
//
//
//            Log.d("", jawabanYgDiPilih[urutan_soal_soal] + "");
//
//            Log.i("cek_pilihan", "tunjukanPertanyaan: "+jawabanYgDiPilih[urutan_soal_soal]);
//            if (jawabanYgDiPilih[urutan_soal_soal] == 0)
//                rg.check(R.id.radio0);
//            if (jawabanYgDiPilih[urutan_soal_soal] == 1)
//                rg.check(R.id.radio1);
//            if (jawabanYgDiPilih[urutan_soal_soal] == 2)
//                rg.check(R.id.radio2);
//
////            if (jawabanYgDiPilih[urutan_soal_soal] == 3)
////                rg.check(R.id.radio3);
//
//            pasangLabelDanNomorUrut();
//
//            if (validasi.equals("10")){
//                btnNext.setVisibility(View.GONE);
//            }else {
//                btnNext.setVisibility(View.VISIBLE);
//            }
//
//            if (urutan_soal_soal == (listSoal.size() - 1)){
//                btnNext.setEnabled(false);
//                btnSelesai.setEnabled(true);
//            }
//
//            if (urutan_soal_soal == 0)
//                btnPrev.setEnabled(false);
//
//            if (urutan_soal_soal > 0)
//                btnPrev.setEnabled(true);
//
//            if (urutan_soal_soal < (listSoal.size() - 1))
//                btnNext.setEnabled(true);
//
//        } catch (Exception e) {
//            Log.e(this.getClass().toString(), e.getMessage(), e.getCause());
//        }
//    }
//    public class CounterClass extends CountDownTimer {
//        public CounterClass(long millisInFuture, long countDownInterval) {
//            super(millisInFuture, countDownInterval);
//        }
//
//        @Override
//        public void onFinish() {
//            aturJawaban_nya();
//            // hitung berapa yg benar
//            int jumlahJawabanYgBenar = 0;
//            int jawaban=0;
//
//            int i;
//            for ( i = 0; i < jawabanYgBenar.length; i++) {
//                if ((jawabanYgBenar[i] != -1) && (jawabanYgBenar[i] == jawabanYgDiPilih[i]))
//                    jumlahJawabanYgBenar++;
//                jawaban=jawabanYgDiPilih[i];
//                if(jawabanYgBenar[i] != jawabanYgDiPilih[i])
//                    noSalah = noSalah+" " + Integer.toString(i+1);
//            }
//            if(noSalah == ""){
//                noSalah = "Benar semua";
//            }
//            else{
//                noSalah = "No yang salah"+noSalah;
//            }
//            total = jumlahJawabanYgBenar*10;
//            AlertDialog tampilKotakAlert;
//            tampilKotakAlert = new AlertDialog.Builder(quiz_satu.this).create();
//            tampilKotakAlert.setTitle("Nilai");
//            tampilKotakAlert.setMessage("Besdasdasdnar " +jumlahJawabanYgBenar + " dari "
//                    + (listSoal.size() +" soal. "+noSalah+ "Total nialai anda = "+total));
//            // nilai.setText(total);
//            tampilKotakAlert.setButton(AlertDialog.BUTTON_NEUTRAL, "",
//                    new DialogInterface.OnClickListener() {
//
//                        public void onClick(DialogInterface dialog, int which) {
//                            cekPertanyaan = false;
//                            urutanPertanyaan = 0;
//                            noSalah="";
//                            Arrays.fill(jawabanYgDiPilih, -2);
//                            quiz_satu.this.tunjukanPertanyaan(0,
//                                    cekPertanyaan);
//                            nilai.setText(total);
//                        }
//                    });
//
//            tampilKotakAlert.setButton(AlertDialog.BUTTON_NEGATIVE, "Keluar",
//                    new DialogInterface.OnClickListener() {
//
//                        public void onClick(DialogInterface dialog, int which) {
//                            cekPertanyaan = false;
//                            finish();
//                        }
//                    });
//
//            tampilKotakAlert.show();
//        }
//
//        @SuppressLint("NewApi")
//        @TargetApi(Build.VERSION_CODES.GINGERBREAD)
//
//        public void onTick(long millisUntilFinished) {
//            long millis = millisUntilFinished;
//            String hms = String.format(
//                    "%02d:%02d:%02d",
//                    TimeUnit.MILLISECONDS.toHours(millis),
//                    TimeUnit.MILLISECONDS.toMinutes(millis)
//                            - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS
//                            .toHours(millis)),
//                    TimeUnit.MILLISECONDS.toSeconds(millis)
//                            - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS
//                            .toMinutes(millis)));
//            txtwaktu.setText(hms);
//        }
//    }
//
//    private View.OnClickListener klikSelesai = new View.OnClickListener() {
//        public void onClick(View v) {
//            aturJawaban_nya();
//            // hitung berapa yg benar
//            int jumlahJawabanYgBenar = 0;
//            int jawaban=0;
//            // int total= 0;
//            int i;
//            for (i = 0; i < jawabanYgBenar.length; i++) {
//                if ((jawabanYgBenar[i] != -1) && (jawabanYgBenar[i] == jawabanYgDiPilih[i]))
//                    jumlahJawabanYgBenar++;
//                jawaban=jawabanYgDiPilih[i];
//                if(jawabanYgBenar[i] != jawabanYgDiPilih[i])
//                    noSalah = noSalah+" " + Integer.toString(i+1);
//            }
//            if(noSalah == ""){
//                noSalah = "Benar semua";
//            }
//            else{
//                noSalah = "No yang salah"+noSalah;
//            }
//            total = jumlahJawabanYgBenar*10;
//            AlertDialog tampilKotakAlert;
//            tampilKotakAlert = new AlertDialog.Builder(quiz_satu.this).create();
//            tampilKotakAlert.setTitle("Nilai");
//            tampilKotakAlert.setMessage("Benar " +jumlahJawabanYgBenar + " dari "
//                    + (listSoal.size() +" soal. "+noSalah+ " Total nialai anda = "+total));
//            nilai.setText(""+total);
//            tampilKotakAlert.setButton(AlertDialog.BUTTON_NEUTRAL, "Lagi",
//                    new DialogInterface.OnClickListener() {
//
//                        public void onClick(DialogInterface dialog, int which) {
//                            cekPertanyaan = false;
//                            urutanPertanyaan = 0;
//                            noSalah="";
//                            Arrays.fill(jawabanYgDiPilih, -2);
//                            quiz_satu.this.tunjukanPertanyaan(0,
//                                    cekPertanyaan);
//                            txtwaktu.setText("dsdasd");
//
//                            // nilai.setText(total);
//                        }
//                    });
//
//            tampilKotakAlert.setButton(AlertDialog.BUTTON_NEGATIVE, "Simpan",
//                    new DialogInterface.OnClickListener() {
//
//                        public void onClick(DialogInterface dialog, int which) {
//                            //simapan_quiz();
//                            cekPertanyaan = false;
//
//                        }
//                    });
//
//            tampilKotakAlert.show();
//
//        }
//    };
//
//    private void aturJawaban_nya() {
//        if (rdA.isChecked())
//            jawabanYgDiPilih[urutanPertanyaan] = 0;
//        if (rdB.isChecked())
//            jawabanYgDiPilih[urutanPertanyaan] = 1;
//        if (rdC.isChecked())
//            jawabanYgDiPilih[urutanPertanyaan] = 2;
//
//        Log.d("", Arrays.toString(jawabanYgDiPilih));
//        Log.d("", Arrays.toString(jawabanYgBenar));
//
//    }
//
//    private View.OnClickListener klikBerikut = new View.OnClickListener() {
//        public void onClick(View v) {
//            aturJawaban_nya();
//            urutanPertanyaan++;
//            if (urutanPertanyaan >= listSoal.size())
//                urutanPertanyaan = listSoal.size() - 1;
//
//            tunjukanPertanyaan(urutanPertanyaan, cekPertanyaan);
//        }
//    };
//
//    private View.OnClickListener klikSebelum = new View.OnClickListener() {
//        public void onClick(View v) {
//            aturJawaban_nya();
//            urutanPertanyaan--;
//            if (urutanPertanyaan < 0)
//                urutanPertanyaan = 0;
//            tunjukanPertanyaan(urutanPertanyaan, cekPertanyaan);
//        }
//    };
//
//    private void pasangLabelDanNomorUrut() {
//        txtno.setText("Soal ke-" + (urutanPertanyaan + 1) + " dari "
//                + listSoal.size());
//        validasi= String.valueOf(urutanPertanyaan+1);
//    }
//
//
////    private void simapan_quiz() {
////
////            pd_new.setTitle("Simpan Data");
////            pd_new.setContentText("Mohon tunggu sedang memproses...");
////            pd_new.show();
////            pd_new.setCancelable(false);
////
////
////            try {
////                ApiRequest api = Retroserver.getClient().create(ApiRequest.class);
////                Call<BaseResponse> sendbio = api.simpan_quiz(
////                        nis,
////                        nama,
////                        id_guru,
////                        id_kelas,
////                        "1",
////                        String.valueOf(total));
////                sendbio.enqueue(new Callback<BaseResponse>() {
////                    @Override
////                    public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
////                        String value = response.body().getvalue();
////
////                       String message = response.body().getMessage();
////                        Log.i("data_pesan", "onResponse: "+value);
////                        //    Toast.makeText(menu_register_detail_bukan_warga_jambi.this, "Jaringan Error!"+message, Toast.LENGTH_SHORT);
////
////                        //  progress.dismiss();
////                        if (value.equals("1")) {
////                            pd_new.dismissWithAnimation();
////                            Toast.makeText(quiz_satu.this, "berhasil", Toast.LENGTH_SHORT).show();
////                            dialog_berhasil_simpan();
////                        } else {
////                            pd_new.dismiss();
////                        //    Toast.makeText(permohonan_informasi_publik.this, message, Toast.LENGTH_SHORT);
////                            //dialog_register_duplikat();
////                        }
////                    }
////
////                    @Override
////                    public void onFailure(Call<BaseResponse> call, Throwable t) {
////                        // progress.dismiss();
////                        //  Toast.makeText(menu_register_detail_bukan_warga_jambi.this, "Jaringan Error!" + t, Toast.LENGTH_SHORT);
////                    }
////                });
////
////            }catch (Exception e){
////                Log.i("data_error", "simpan_permohonan: "+e);
////
////            }
////
////
//
//
//  //  }
//    void dialog_berhasil_simpan() {
//        SweetAlertDialog pDialog = new SweetAlertDialog(quiz_satu.this, SweetAlertDialog.SUCCESS_TYPE);
//        pDialog.setCancelable(false);
//        pDialog.setTitleText("Simpan Nilai Berhasil");
//        pDialog.setContentText("Lihat Nilai Anda Di Menu Nilai");
//        pDialog.setConfirmText("Ok");
//        pDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
//            @Override
//            public void onClick(SweetAlertDialog sweetAlertDialog) {
////                sweetAlertDialog.dismissWithAnimation();
////                Intent intent = new Intent(quiz_satu.this, menu_nilai.class);
////                startActivity(intent);
////                finish();
//            }
//        });
//        pDialog.setCanceledOnTouchOutside(false);
//        pDialog.show();
//
//    }
//
//}
