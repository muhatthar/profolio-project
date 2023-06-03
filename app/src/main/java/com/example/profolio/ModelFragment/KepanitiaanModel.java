package com.example.profolio.ModelFragment;

public class KepanitiaanModel {
    private String namaKepanitiaan, jabatanKepanitiaan, deskripsiKepanitiaan, tahunKepanitiaan;
    private String key;

    public KepanitiaanModel() {

    }

    public KepanitiaanModel(String namaKepanitiaan, String jabatanKepanitiaan, String deskripsiKepanitiaan, String tahunKepanitiaan) {
        this.namaKepanitiaan = namaKepanitiaan;
        this.jabatanKepanitiaan = jabatanKepanitiaan;
        this.deskripsiKepanitiaan = deskripsiKepanitiaan;
        this.tahunKepanitiaan = tahunKepanitiaan;
    }

    public String getNamaKepanitiaan() {
        return namaKepanitiaan;
    }

    public void setNamaKepanitiaan(String namaKepanitiaan) {
        this.namaKepanitiaan = namaKepanitiaan;
    }

    public String getJabatanKepanitiaan() {
        return jabatanKepanitiaan;
    }

    public void setJabatanKepanitiaan(String jabatanKepanitiaan) {
        this.jabatanKepanitiaan = jabatanKepanitiaan;
    }

    public String getDeskripsiKepanitiaan() {
        return deskripsiKepanitiaan;
    }

    public void setDeskripsiKepanitiaan(String deskripsiKepanitiaan) {
        this.deskripsiKepanitiaan = deskripsiKepanitiaan;
    }

    public String getTahunKepanitiaan() {
        return tahunKepanitiaan;
    }

    public void setTahunKepanitiaan(String tahunKepanitiaan) {
        this.tahunKepanitiaan = tahunKepanitiaan;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}


