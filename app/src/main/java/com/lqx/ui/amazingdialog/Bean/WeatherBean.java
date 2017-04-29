package com.lqx.ui.amazingdialog.Bean;

/**
 * Created by NEDUsoftware on 2017/4/29.
 */

public class WeatherBean {


    /**
     * resultcode : 200
     * reason : 查询成功
     * result : {"sk":{"temp":"6","wind_direction":"西北风","wind_strength":"3级","humidity":"90%","time":"15:19"},"today":{"temperature":"3℃~12℃","weather":"小雨转多云","weather_id":{"fa":"07","fb":"01"},"wind":"北风3-4 级","week":"星期四","city":"吉林","date_y":"2017年04月20日","dressing_index":"较冷","dressing_advice":"建议着厚外套加毛衣等服装。年老体弱者宜着大衣、呢外套加羊毛衫。","uv_index":"最弱","comfort_index":"","wash_index":"不宜","travel_index":"较不宜","exercise_index":"较不宜","drying_index":""},"future":{"day_20170420":{"temperature":"3℃~12℃","weather":"小雨转多云","weather_id":{"fa":"07","fb":"01"},"wind":"北风3-4 级","week":"星期四","date":"20170420"},"day_20170421":{"temperature":"5℃~14℃","weather":"晴转多云","weather_id":{"fa":"00","fb":"01"},"wind":"西南风3-4 级","week":"星期五","date":"20170421"},"day_20170422":{"temperature":"8℃~20℃","weather":"晴转小雨","weather_id":{"fa":"00","fb":"07"},"wind":"西南风3-4 级","week":"星期六","date":"20170422"},"day_20170423":{"temperature":"5℃~15℃","weather":"小雨转晴","weather_id":{"fa":"07","fb":"00"},"wind":"微风","week":"星期日","date":"20170423"},"day_20170424":{"temperature":"5℃~16℃","weather":"晴","weather_id":{"fa":"00","fb":"00"},"wind":"微风","week":"星期一","date":"20170424"},"day_20170425":{"temperature":"8℃~20℃","weather":"晴转小雨","weather_id":{"fa":"00","fb":"07"},"wind":"西南风3-4 级","week":"星期二","date":"20170425"},"day_20170426":{"temperature":"5℃~15℃","weather":"小雨转晴","weather_id":{"fa":"07","fb":"00"},"wind":"微风","week":"星期三","date":"20170426"}}}
     * error_code : 0
     */

    private String resultcode;
    private String reason;
    private ResultBean result;
    private int error_code;

    public String getResultcode() {
        return resultcode;
    }

    public void setResultcode(String resultcode) {
        this.resultcode = resultcode;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public static class ResultBean {
        /**
         * sk : {"temp":"6","wind_direction":"西北风","wind_strength":"3级","humidity":"90%","time":"15:19"}
         * today : {"temperature":"3℃~12℃","weather":"小雨转多云","weather_id":{"fa":"07","fb":"01"},"wind":"北风3-4 级","week":"星期四","city":"吉林","date_y":"2017年04月20日","dressing_index":"较冷","dressing_advice":"建议着厚外套加毛衣等服装。年老体弱者宜着大衣、呢外套加羊毛衫。","uv_index":"最弱","comfort_index":"","wash_index":"不宜","travel_index":"较不宜","exercise_index":"较不宜","drying_index":""}
         * future : {"day_20170420":{"temperature":"3℃~12℃","weather":"小雨转多云","weather_id":{"fa":"07","fb":"01"},"wind":"北风3-4 级","week":"星期四","date":"20170420"},"day_20170421":{"temperature":"5℃~14℃","weather":"晴转多云","weather_id":{"fa":"00","fb":"01"},"wind":"西南风3-4 级","week":"星期五","date":"20170421"},"day_20170422":{"temperature":"8℃~20℃","weather":"晴转小雨","weather_id":{"fa":"00","fb":"07"},"wind":"西南风3-4 级","week":"星期六","date":"20170422"},"day_20170423":{"temperature":"5℃~15℃","weather":"小雨转晴","weather_id":{"fa":"07","fb":"00"},"wind":"微风","week":"星期日","date":"20170423"},"day_20170424":{"temperature":"5℃~16℃","weather":"晴","weather_id":{"fa":"00","fb":"00"},"wind":"微风","week":"星期一","date":"20170424"},"day_20170425":{"temperature":"8℃~20℃","weather":"晴转小雨","weather_id":{"fa":"00","fb":"07"},"wind":"西南风3-4 级","week":"星期二","date":"20170425"},"day_20170426":{"temperature":"5℃~15℃","weather":"小雨转晴","weather_id":{"fa":"07","fb":"00"},"wind":"微风","week":"星期三","date":"20170426"}}
         */

        private SkBean sk;
        private TodayBean today;
        private FutureBean future;


        public SkBean getSk() {
            return sk;
        }

        public void setSk(SkBean sk) {
            this.sk = sk;
        }

        public TodayBean getToday() {
            return today;
        }

        public void setToday(TodayBean today) {
            this.today = today;
        }

        public FutureBean getFuture() {
            return future;
        }

        public void setFuture(FutureBean future) {
            this.future = future;
        }

        public static class SkBean {
            /**
             * temp : 6
             * wind_direction : 西北风
             * wind_strength : 3级
             * humidity : 90%
             * time : 15:19
             */

            private String temp;
            private String wind_direction;
            private String wind_strength;
            private String humidity;
            private String time;

            @Override
            public String toString() {
                return "当前天气：" +
                        "\n当前时间：" + time+"\n温度：" + temp + "℃\n风向：" + wind_direction  +
                        "\n风力;" + wind_strength + "\n湿度:" + humidity;
            }
            public String getTemp() {
                return temp;
            }

            public void setTemp(String temp) {
                this.temp = temp;
            }

            public String getWind_direction() {
                return wind_direction;
            }

            public void setWind_direction(String wind_direction) {
                this.wind_direction = wind_direction;
            }

            public String getWind_strength() {
                return wind_strength;
            }

            public void setWind_strength(String wind_strength) {
                this.wind_strength = wind_strength;
            }

            public String getHumidity() {
                return humidity;
            }

            public void setHumidity(String humidity) {
                this.humidity = humidity;
            }

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }
        }

        public static class TodayBean {
            /**
             * temperature : 3℃~12℃
             * weather : 小雨转多云
             * weather_id : {"fa":"07","fb":"01"}
             * wind : 北风3-4 级
             * week : 星期四
             * city : 吉林
             * date_y : 2017年04月20日
             * dressing_index : 较冷
             * dressing_advice : 建议着厚外套加毛衣等服装。年老体弱者宜着大衣、呢外套加羊毛衫。
             * uv_index : 最弱
             * comfort_index :
             * wash_index : 不宜
             * travel_index : 较不宜
             * exercise_index : 较不宜
             * drying_index :
             */

            private String temperature;
            private String weather;
            private WeatherIdBean weather_id;
            private String wind;
            private String week;
            private String city;
            private String date_y;
            private String dressing_index;
            private String dressing_advice;
            private String uv_index;
            private String comfort_index;
            private String wash_index;
            private String travel_index;
            private String exercise_index;
            private String drying_index;

            @Override
            public String toString() {
                return "\n\n今日天气：" +
                        "\n温度:" + temperature +
                        "\n天气：" + weather +
                        "\n风力：" + wind +
                        "\n城市：" + city  +
                        "\n日期：" + date_y + "/" +week+
                        "\n穿衣指数：" + dressing_index +
                        "\n穿衣建议：" + dressing_advice +
                        "\n紫外线强度：" + uv_index +
                        "\n洗车指数：" + wash_index +
                        "\n旅游指数：" + travel_index +
                        "\n晨练指数：" + exercise_index ;
            }

            public String getTemperature() {
                return temperature;
            }

            public void setTemperature(String temperature) {
                this.temperature = temperature;
            }

            public String getWeather() {
                return weather;
            }

            public void setWeather(String weather) {
                this.weather = weather;
            }

            public WeatherIdBean getWeather_id() {
                return weather_id;
            }

            public void setWeather_id(WeatherIdBean weather_id) {
                this.weather_id = weather_id;
            }

            public String getWind() {
                return wind;
            }

            public void setWind(String wind) {
                this.wind = wind;
            }

            public String getWeek() {
                return week;
            }

            public void setWeek(String week) {
                this.week = week;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getDate_y() {
                return date_y;
            }

            public void setDate_y(String date_y) {
                this.date_y = date_y;
            }

            public String getDressing_index() {
                return dressing_index;
            }

            public void setDressing_index(String dressing_index) {
                this.dressing_index = dressing_index;
            }

            public String getDressing_advice() {
                return dressing_advice;
            }

            public void setDressing_advice(String dressing_advice) {
                this.dressing_advice = dressing_advice;
            }

            public String getUv_index() {
                return uv_index;
            }

            public void setUv_index(String uv_index) {
                this.uv_index = uv_index;
            }

            public String getComfort_index() {
                return comfort_index;
            }

            public void setComfort_index(String comfort_index) {
                this.comfort_index = comfort_index;
            }

            public String getWash_index() {
                return wash_index;
            }

            public void setWash_index(String wash_index) {
                this.wash_index = wash_index;
            }

            public String getTravel_index() {
                return travel_index;
            }

            public void setTravel_index(String travel_index) {
                this.travel_index = travel_index;
            }

            public String getExercise_index() {
                return exercise_index;
            }

            public void setExercise_index(String exercise_index) {
                this.exercise_index = exercise_index;
            }

            public String getDrying_index() {
                return drying_index;
            }

            public void setDrying_index(String drying_index) {
                this.drying_index = drying_index;
            }

            public static class WeatherIdBean {
                /**
                 * fa : 07
                 * fb : 01
                 */

                private String fa;
                private String fb;

                public String getFa() {
                    return fa;
                }

                public void setFa(String fa) {
                    this.fa = fa;
                }

                public String getFb() {
                    return fb;
                }

                public void setFb(String fb) {
                    this.fb = fb;
                }
            }
        }

        public static class FutureBean {
            /**
             * day_20170420 : {"temperature":"3℃~12℃","weather":"小雨转多云","weather_id":{"fa":"07","fb":"01"},"wind":"北风3-4 级","week":"星期四","date":"20170420"}
             * day_20170421 : {"temperature":"5℃~14℃","weather":"晴转多云","weather_id":{"fa":"00","fb":"01"},"wind":"西南风3-4 级","week":"星期五","date":"20170421"}
             * day_20170422 : {"temperature":"8℃~20℃","weather":"晴转小雨","weather_id":{"fa":"00","fb":"07"},"wind":"西南风3-4 级","week":"星期六","date":"20170422"}
             * day_20170423 : {"temperature":"5℃~15℃","weather":"小雨转晴","weather_id":{"fa":"07","fb":"00"},"wind":"微风","week":"星期日","date":"20170423"}
             * day_20170424 : {"temperature":"5℃~16℃","weather":"晴","weather_id":{"fa":"00","fb":"00"},"wind":"微风","week":"星期一","date":"20170424"}
             * day_20170425 : {"temperature":"8℃~20℃","weather":"晴转小雨","weather_id":{"fa":"00","fb":"07"},"wind":"西南风3-4 级","week":"星期二","date":"20170425"}
             * day_20170426 : {"temperature":"5℃~15℃","weather":"小雨转晴","weather_id":{"fa":"07","fb":"00"},"wind":"微风","week":"星期三","date":"20170426"}
             */

            private Day20170420Bean day_20170420;
            private Day20170421Bean day_20170421;
            private Day20170422Bean day_20170422;
            private Day20170423Bean day_20170423;
            private Day20170424Bean day_20170424;
            private Day20170425Bean day_20170425;
            private Day20170426Bean day_20170426;

            public Day20170420Bean getDay_20170420() {
                return day_20170420;
            }

            public void setDay_20170420(Day20170420Bean day_20170420) {
                this.day_20170420 = day_20170420;
            }

            public Day20170421Bean getDay_20170421() {
                return day_20170421;
            }

            public void setDay_20170421(Day20170421Bean day_20170421) {
                this.day_20170421 = day_20170421;
            }

            public Day20170422Bean getDay_20170422() {
                return day_20170422;
            }

            public void setDay_20170422(Day20170422Bean day_20170422) {
                this.day_20170422 = day_20170422;
            }

            public Day20170423Bean getDay_20170423() {
                return day_20170423;
            }

            public void setDay_20170423(Day20170423Bean day_20170423) {
                this.day_20170423 = day_20170423;
            }

            public Day20170424Bean getDay_20170424() {
                return day_20170424;
            }

            public void setDay_20170424(Day20170424Bean day_20170424) {
                this.day_20170424 = day_20170424;
            }

            public Day20170425Bean getDay_20170425() {
                return day_20170425;
            }

            public void setDay_20170425(Day20170425Bean day_20170425) {
                this.day_20170425 = day_20170425;
            }

            public Day20170426Bean getDay_20170426() {
                return day_20170426;
            }

            public void setDay_20170426(Day20170426Bean day_20170426) {
                this.day_20170426 = day_20170426;
            }

            public static class Day20170420Bean {
                /**
                 * temperature : 3℃~12℃
                 * weather : 小雨转多云
                 * weather_id : {"fa":"07","fb":"01"}
                 * wind : 北风3-4 级
                 * week : 星期四
                 * date : 20170420
                 */

                private String temperature;
                private String weather;
                private WeatherIdBeanX weather_id;
                private String wind;
                private String week;
                private String date;

                public String getTemperature() {
                    return temperature;
                }

                public void setTemperature(String temperature) {
                    this.temperature = temperature;
                }

                public String getWeather() {
                    return weather;
                }

                public void setWeather(String weather) {
                    this.weather = weather;
                }

                public WeatherIdBeanX getWeather_id() {
                    return weather_id;
                }

                public void setWeather_id(WeatherIdBeanX weather_id) {
                    this.weather_id = weather_id;
                }

                public String getWind() {
                    return wind;
                }

                public void setWind(String wind) {
                    this.wind = wind;
                }

                public String getWeek() {
                    return week;
                }

                public void setWeek(String week) {
                    this.week = week;
                }

                public String getDate() {
                    return date;
                }

                public void setDate(String date) {
                    this.date = date;
                }

                public static class WeatherIdBeanX {
                    /**
                     * fa : 07
                     * fb : 01
                     */

                    private String fa;
                    private String fb;

                    public String getFa() {
                        return fa;
                    }

                    public void setFa(String fa) {
                        this.fa = fa;
                    }

                    public String getFb() {
                        return fb;
                    }

                    public void setFb(String fb) {
                        this.fb = fb;
                    }
                }
            }

            public static class Day20170421Bean {
                /**
                 * temperature : 5℃~14℃
                 * weather : 晴转多云
                 * weather_id : {"fa":"00","fb":"01"}
                 * wind : 西南风3-4 级
                 * week : 星期五
                 * date : 20170421
                 */

                private String temperature;
                private String weather;
                private WeatherIdBeanXX weather_id;
                private String wind;
                private String week;
                private String date;

                public String getTemperature() {
                    return temperature;
                }

                public void setTemperature(String temperature) {
                    this.temperature = temperature;
                }

                public String getWeather() {
                    return weather;
                }

                public void setWeather(String weather) {
                    this.weather = weather;
                }

                public WeatherIdBeanXX getWeather_id() {
                    return weather_id;
                }

                public void setWeather_id(WeatherIdBeanXX weather_id) {
                    this.weather_id = weather_id;
                }

                public String getWind() {
                    return wind;
                }

                public void setWind(String wind) {
                    this.wind = wind;
                }

                public String getWeek() {
                    return week;
                }

                public void setWeek(String week) {
                    this.week = week;
                }

                public String getDate() {
                    return date;
                }

                public void setDate(String date) {
                    this.date = date;
                }

                public static class WeatherIdBeanXX {
                    /**
                     * fa : 00
                     * fb : 01
                     */

                    private String fa;
                    private String fb;

                    public String getFa() {
                        return fa;
                    }

                    public void setFa(String fa) {
                        this.fa = fa;
                    }

                    public String getFb() {
                        return fb;
                    }

                    public void setFb(String fb) {
                        this.fb = fb;
                    }
                }
            }

            public static class Day20170422Bean {
                /**
                 * temperature : 8℃~20℃
                 * weather : 晴转小雨
                 * weather_id : {"fa":"00","fb":"07"}
                 * wind : 西南风3-4 级
                 * week : 星期六
                 * date : 20170422
                 */

                private String temperature;
                private String weather;
                private WeatherIdBeanXXX weather_id;
                private String wind;
                private String week;
                private String date;

                public String getTemperature() {
                    return temperature;
                }

                public void setTemperature(String temperature) {
                    this.temperature = temperature;
                }

                public String getWeather() {
                    return weather;
                }

                public void setWeather(String weather) {
                    this.weather = weather;
                }

                public WeatherIdBeanXXX getWeather_id() {
                    return weather_id;
                }

                public void setWeather_id(WeatherIdBeanXXX weather_id) {
                    this.weather_id = weather_id;
                }

                public String getWind() {
                    return wind;
                }

                public void setWind(String wind) {
                    this.wind = wind;
                }

                public String getWeek() {
                    return week;
                }

                public void setWeek(String week) {
                    this.week = week;
                }

                public String getDate() {
                    return date;
                }

                public void setDate(String date) {
                    this.date = date;
                }

                public static class WeatherIdBeanXXX {
                    /**
                     * fa : 00
                     * fb : 07
                     */

                    private String fa;
                    private String fb;

                    public String getFa() {
                        return fa;
                    }

                    public void setFa(String fa) {
                        this.fa = fa;
                    }

                    public String getFb() {
                        return fb;
                    }

                    public void setFb(String fb) {
                        this.fb = fb;
                    }
                }
            }

            public static class Day20170423Bean {
                /**
                 * temperature : 5℃~15℃
                 * weather : 小雨转晴
                 * weather_id : {"fa":"07","fb":"00"}
                 * wind : 微风
                 * week : 星期日
                 * date : 20170423
                 */

                private String temperature;
                private String weather;
                private WeatherIdBeanXXXX weather_id;
                private String wind;
                private String week;
                private String date;

                public String getTemperature() {
                    return temperature;
                }

                public void setTemperature(String temperature) {
                    this.temperature = temperature;
                }

                public String getWeather() {
                    return weather;
                }

                public void setWeather(String weather) {
                    this.weather = weather;
                }

                public WeatherIdBeanXXXX getWeather_id() {
                    return weather_id;
                }

                public void setWeather_id(WeatherIdBeanXXXX weather_id) {
                    this.weather_id = weather_id;
                }

                public String getWind() {
                    return wind;
                }

                public void setWind(String wind) {
                    this.wind = wind;
                }

                public String getWeek() {
                    return week;
                }

                public void setWeek(String week) {
                    this.week = week;
                }

                public String getDate() {
                    return date;
                }

                public void setDate(String date) {
                    this.date = date;
                }

                public static class WeatherIdBeanXXXX {
                    /**
                     * fa : 07
                     * fb : 00
                     */

                    private String fa;
                    private String fb;

                    public String getFa() {
                        return fa;
                    }

                    public void setFa(String fa) {
                        this.fa = fa;
                    }

                    public String getFb() {
                        return fb;
                    }

                    public void setFb(String fb) {
                        this.fb = fb;
                    }
                }
            }

            public static class Day20170424Bean {
                /**
                 * temperature : 5℃~16℃
                 * weather : 晴
                 * weather_id : {"fa":"00","fb":"00"}
                 * wind : 微风
                 * week : 星期一
                 * date : 20170424
                 */

                private String temperature;
                private String weather;
                private WeatherIdBeanXXXXX weather_id;
                private String wind;
                private String week;
                private String date;

                public String getTemperature() {
                    return temperature;
                }

                public void setTemperature(String temperature) {
                    this.temperature = temperature;
                }

                public String getWeather() {
                    return weather;
                }

                public void setWeather(String weather) {
                    this.weather = weather;
                }

                public WeatherIdBeanXXXXX getWeather_id() {
                    return weather_id;
                }

                public void setWeather_id(WeatherIdBeanXXXXX weather_id) {
                    this.weather_id = weather_id;
                }

                public String getWind() {
                    return wind;
                }

                public void setWind(String wind) {
                    this.wind = wind;
                }

                public String getWeek() {
                    return week;
                }

                public void setWeek(String week) {
                    this.week = week;
                }

                public String getDate() {
                    return date;
                }

                public void setDate(String date) {
                    this.date = date;
                }

                public static class WeatherIdBeanXXXXX {
                    /**
                     * fa : 00
                     * fb : 00
                     */

                    private String fa;
                    private String fb;

                    public String getFa() {
                        return fa;
                    }

                    public void setFa(String fa) {
                        this.fa = fa;
                    }

                    public String getFb() {
                        return fb;
                    }

                    public void setFb(String fb) {
                        this.fb = fb;
                    }
                }
            }

            public static class Day20170425Bean {
                /**
                 * temperature : 8℃~20℃
                 * weather : 晴转小雨
                 * weather_id : {"fa":"00","fb":"07"}
                 * wind : 西南风3-4 级
                 * week : 星期二
                 * date : 20170425
                 */

                private String temperature;
                private String weather;
                private WeatherIdBeanXXXXXX weather_id;
                private String wind;
                private String week;
                private String date;

                public String getTemperature() {
                    return temperature;
                }

                public void setTemperature(String temperature) {
                    this.temperature = temperature;
                }

                public String getWeather() {
                    return weather;
                }

                public void setWeather(String weather) {
                    this.weather = weather;
                }

                public WeatherIdBeanXXXXXX getWeather_id() {
                    return weather_id;
                }

                public void setWeather_id(WeatherIdBeanXXXXXX weather_id) {
                    this.weather_id = weather_id;
                }

                public String getWind() {
                    return wind;
                }

                public void setWind(String wind) {
                    this.wind = wind;
                }

                public String getWeek() {
                    return week;
                }

                public void setWeek(String week) {
                    this.week = week;
                }

                public String getDate() {
                    return date;
                }

                public void setDate(String date) {
                    this.date = date;
                }

                public static class WeatherIdBeanXXXXXX {
                    /**
                     * fa : 00
                     * fb : 07
                     */

                    private String fa;
                    private String fb;

                    public String getFa() {
                        return fa;
                    }

                    public void setFa(String fa) {
                        this.fa = fa;
                    }

                    public String getFb() {
                        return fb;
                    }

                    public void setFb(String fb) {
                        this.fb = fb;
                    }
                }
            }

            public static class Day20170426Bean {
                /**
                 * temperature : 5℃~15℃
                 * weather : 小雨转晴
                 * weather_id : {"fa":"07","fb":"00"}
                 * wind : 微风
                 * week : 星期三
                 * date : 20170426
                 */

                private String temperature;
                private String weather;
                private WeatherIdBeanXXXXXXX weather_id;
                private String wind;
                private String week;
                private String date;

                public String getTemperature() {
                    return temperature;
                }

                public void setTemperature(String temperature) {
                    this.temperature = temperature;
                }

                public String getWeather() {
                    return weather;
                }

                public void setWeather(String weather) {
                    this.weather = weather;
                }

                public WeatherIdBeanXXXXXXX getWeather_id() {
                    return weather_id;
                }

                public void setWeather_id(WeatherIdBeanXXXXXXX weather_id) {
                    this.weather_id = weather_id;
                }

                public String getWind() {
                    return wind;
                }

                public void setWind(String wind) {
                    this.wind = wind;
                }

                public String getWeek() {
                    return week;
                }

                public void setWeek(String week) {
                    this.week = week;
                }

                public String getDate() {
                    return date;
                }

                public void setDate(String date) {
                    this.date = date;
                }

                public static class WeatherIdBeanXXXXXXX {
                    /**
                     * fa : 07
                     * fb : 00
                     */

                    private String fa;
                    private String fb;

                    public String getFa() {
                        return fa;
                    }

                    public void setFa(String fa) {
                        this.fa = fa;
                    }

                    public String getFb() {
                        return fb;
                    }

                    public void setFb(String fb) {
                        this.fb = fb;
                    }
                }
            }
        }
    }


}

