package com.example.poltekkes.view;


import com.example.poltekkes.menu.model.pertanyaan.DataItem_pertanyaan;

import java.util.List;


/**
 * This class represents the country view interface.
 *
 * @author Jean Carlos (Github: @jeancsanchez)
 * @date 09/03/18.
 * Jesus loves you.
 */
public interface pertanyaan_view {

    void pertanyaan(List<DataItem_pertanyaan> pertanyaan);
    void status(String status,String pesan);


}
