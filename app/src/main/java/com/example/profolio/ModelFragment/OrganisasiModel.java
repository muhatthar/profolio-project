package com.example.profolio.ModelFragment;

public class OrganisasiModel {

    private String namaOrganisasi, jabatanOrganisasi, tahunMulaiOrganisasi, tahunSelesaiOrganisasi, deskripsiOrganisasi;
    private String key;

    public OrganisasiModel() {

    }

    public OrganisasiModel(String namaOrganisasi, String jabatanOrganisasi, String tahunMulaiOrganisasi, String tahunSelesaiOrganisasi, String deskripsiOrganisasi) {
        this.namaOrganisasi = namaOrganisasi;
        this.jabatanOrganisasi = jabatanOrganisasi;
        this.tahunMulaiOrganisasi = tahunMulaiOrganisasi;
        this.tahunSelesaiOrganisasi = tahunSelesaiOrganisasi;
        this.deskripsiOrganisasi = deskripsiOrganisasi;
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

    public String getTahunMulaiOrganisasi() {
        return tahunMulaiOrganisasi;
    }

    public void setTahunMulaiOrganisasi(String tahunMulaiOrganisasi) {
        this.tahunMulaiOrganisasi = tahunMulaiOrganisasi;
    }

    public String getTahunSelesaiOrganisasi() {
        return tahunSelesaiOrganisasi;
    }

    public void setTahunSelesaiOrganisasi(String tahunSelesaiOrganisasi) {
        this.tahunSelesaiOrganisasi = tahunSelesaiOrganisasi;
    }

    public String getDeskripsiOrganisasi() {
        return deskripsiOrganisasi;
    }

    public void setDeskripsiOrganisasi(String deskripsiOrganisasi) {
        this.deskripsiOrganisasi = deskripsiOrganisasi;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}



