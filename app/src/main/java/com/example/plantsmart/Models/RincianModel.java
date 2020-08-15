package com.example.plantsmart.Models;

public class RincianModel {
    private float humidityTanah;
    private float humidity;
    private float suhu;
    private String waktu;

    public float getHumidityTanah() {
        return humidityTanah;
    }

    public void setHumidityTanah(float humidityTanah) {
        this.humidityTanah = humidityTanah;
    }

    public float getHumidity() {
        return humidity;
    }

    public void setHumidity(float humidity) {
        this.humidity = humidity;
    }

    public float getSuhu() {
        return suhu;
    }

    public void setSuhu(float suhu) {
        this.suhu = suhu;
    }

    public String getWaktu() {
        return waktu;
    }

    public void setWaktu(String waktu) {
        this.waktu = waktu;
    }
}
