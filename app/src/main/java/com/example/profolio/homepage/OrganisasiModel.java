package com.example.profolio.homepage;

public class OrganisasiModel {

    private String namaOrganisasi, jabatanOrganisasi, periodeOrganisasi, deskripsi;
    private String key;

    public OrganisasiModel(String namaOrganisasi, String jabatanOrganisasi, String periodeOrganisasi, String deskripsi) {
        this.namaOrganisasi = namaOrganisasi;
        this.jabatanOrganisasi = jabatanOrganisasi;
        this.periodeOrganisasi = periodeOrganisasi;
        this.deskripsi = deskripsi;
    }

    public String getNamaOrganisasi() {
        return namaOrganisasi;
    }

    public void setNamaOrganisasi(String namaOrganisasi) {
        this.namaOrganisasi = namaOrganisasi;
    }

    public String getJabatanOrganisasi() {
        return jabatanOrganisasi;
    }

    public void setJabatanOrganisasi(String jabatanOrganisasi) {
        this.jabatanOrganisasi = jabatanOrganisasi;
    }

    public String getPeriodeOrganisasi() {
        return periodeOrganisasi;
    }

    public void setPeriodeOrganisasi(String periodeOrganisasi) {
        this.periodeOrganisasi = periodeOrganisasi;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
