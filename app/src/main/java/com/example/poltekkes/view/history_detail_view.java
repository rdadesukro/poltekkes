package com.example.poltekkes.view;



import com.example.poltekkes.model.detail_history.Balita;
import com.example.poltekkes.model.detail_history.Data;
import com.example.poltekkes.model.detail_history.JawabanItem;
import com.example.poltekkes.model.detail_history.Pemeriksa;
import com.example.poltekkes.model.detail_history.Perkembangan;
import com.example.poltekkes.model.detail_history.Pertumbuhan;
import com.example.poltekkes.model.history.DataItem_history;

import java.util.List;


/**
 * This class represents the country view interface.
 *
 * @author Jean Carlos (Github: @jeancsanchez)
 * @date 09/03/18.
 * Jesus loves you.
 */
public interface history_detail_view {

    void history_detail(List<JawabanItem> history_detail);
//     void  data_pemeriksa (String nama,
//                           String nim,
//                           String tgl_pemeriksaan);
     void  data_bayi(String nama_bayi,
                     String tgl_lahir,
                     String bb,
                     String pb,
                     String jk,
                     String alamat,
                     String nama_ibu);
//     void  pertumbuhan(String hasil,
//                       String rekomendasi,
//                       String jadawal);
//     void  perkembangan (String hasil,
//                         String rekomendasi,
//                         String jadwal);

    void pemeriksa(Pemeriksa pemeriksa);
    void pertumbuhan(Pertumbuhan pertumbuhan);
    void  perkembangan(Perkembangan perkembangan);
     void balita (Balita balita);

}
