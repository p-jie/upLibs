package p_jie.uplibs;

/**
 * Created by jj on 2017/4/1.
 */

public class Bean {

    /**
     * weatherinfo : {"njd":"暂无实况","qy":"970","temp":"20","Radar":"JC_RADAR_AZ9290_JB","time":"17:00","SD":"14%","cityid":"101110101","isRadar":"1","city":"西安","rain":"0","WD":"西南风","WSE":"1","WS":"1级"}
     */

    private WeatherinfoBean weatherinfo;

    public WeatherinfoBean getWeatherinfo() {
        return weatherinfo;
    }

    public void setWeatherinfo(WeatherinfoBean weatherinfo) {
        this.weatherinfo = weatherinfo;
    }

    public static class WeatherinfoBean {
        /**
         * njd : 暂无实况
         * qy : 970
         * temp : 20
         * Radar : JC_RADAR_AZ9290_JB
         * time : 17:00
         * SD : 14%
         * cityid : 101110101
         * isRadar : 1
         * city : 西安
         * rain : 0
         * WD : 西南风
         * WSE : 1
         * WS : 1级
         */

        private String njd;
        private String qy;
        private String temp;
        private String Radar;
        private String time;
        private String SD;
        private String cityid;
        private String isRadar;
        private String city;
        private String rain;
        private String WD;
        private String WSE;
        private String WS;

        public String getNjd() {
            return njd;
        }

        public void setNjd(String njd) {
            this.njd = njd;
        }

        public String getQy() {
            return qy;
        }

        public void setQy(String qy) {
            this.qy = qy;
        }

        public String getTemp() {
            return temp;
        }

        public void setTemp(String temp) {
            this.temp = temp;
        }

        public String getRadar() {
            return Radar;
        }

        public void setRadar(String Radar) {
            this.Radar = Radar;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getSD() {
            return SD;
        }

        public void setSD(String SD) {
            this.SD = SD;
        }

        public String getCityid() {
            return cityid;
        }

        public void setCityid(String cityid) {
            this.cityid = cityid;
        }

        public String getIsRadar() {
            return isRadar;
        }

        public void setIsRadar(String isRadar) {
            this.isRadar = isRadar;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getRain() {
            return rain;
        }

        public void setRain(String rain) {
            this.rain = rain;
        }

        public String getWD() {
            return WD;
        }

        public void setWD(String WD) {
            this.WD = WD;
        }

        public String getWSE() {
            return WSE;
        }

        public void setWSE(String WSE) {
            this.WSE = WSE;
        }

        public String getWS() {
            return WS;
        }

        public void setWS(String WS) {
            this.WS = WS;
        }
    }
}
