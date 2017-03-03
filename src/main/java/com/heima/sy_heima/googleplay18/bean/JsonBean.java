package com.heima.sy_heima.googleplay18.bean;

import java.util.List;

/**
 * 作者: old样
 * 描述:
 * 上海传智播客android黑马程序员
 */

public class JsonBean {

    /**
     * code : 1
     * message : success
     * result : {"items":[{"detail":"","href":"https://www.oschina.net/news/81098/gitosc-recruitment-2017","id":81098,"img":"https://static.oschina.net/uploads/cooperation/75410/google-beta-natural-language-api_2f605332-a297-4020-8989-bf8910e2b604.jpg","name":"2017 开源中国码云招聘","pubDate":"2017-01-19 14:17:33","type":6},{"detail":"","href":"https://my.oschina.net/gitosc/blog/836829","id":836829,"img":"https://static.oschina.net/uploads/cooperation/75323/ubuntu-forum-black-sql_f56538b5-299c-4b53-b3c7-aba7fc72e6c7.jpg","name":"码云周刊 | 首个完整版开源机器人项目","pubDate":"2017-02-13 11:04:52","type":3},{"detail":"","href":"http://www.oschina.net/news/81863/how-to-learn-web-programming-in-2017","id":81863,"img":"https://static.oschina.net/uploads/cooperation/78083/chrome55-save-at-least-35-percent-memory_2e440e4d-b3cb-40eb-82e9-dc9f6b44af94.jpg","name":"2017 年你应该这样学习 Web 编程","pubDate":"2017-02-13 11:08:43","type":6},{"detail":"","href":"https://www.oschina.net/question/2886655_2230954","id":2230954,"img":"https://static.oschina.net/uploads/cooperation/77929/top-income-programming-languages-2016_4bd2fc0a-eed2-4fd2-9e5a-168417bf28f5.png","name":"高手问答 | MySQL 主从复制的那些事","pubDate":"2017-02-13 11:11:42","type":2},{"detail":"","href":"http://www.oschina.net/news/81873/2017-february-yuanchuanghui","id":81873,"img":"https://static.oschina.net/uploads/cooperation/78455/intellij-idea-2016-3-public-preview_d90db147-55b1-4f9b-b915-e6fb0dee07e2.jpg","name":"厦门、福州源创会开始报名啦！","pubDate":"2017-02-13 11:14:12","type":6}],"nextPageToken":"61AF0C190D6BD629","prevPageToken":"3EA621243546C8A5","requestCount":5,"responseCount":5,"totalResults":5}
     * time : 2017-02-15 17:10:20
     */

    private int code;
    private String     message;
    private ResultBean result;
    private String     time;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public static class ResultBean {
        /**
         * items : [{"detail":"","href":"https://www.oschina.net/news/81098/gitosc-recruitment-2017","id":81098,"img":"https://static.oschina.net/uploads/cooperation/75410/google-beta-natural-language-api_2f605332-a297-4020-8989-bf8910e2b604.jpg","name":"2017 开源中国码云招聘","pubDate":"2017-01-19 14:17:33","type":6},{"detail":"","href":"https://my.oschina.net/gitosc/blog/836829","id":836829,"img":"https://static.oschina.net/uploads/cooperation/75323/ubuntu-forum-black-sql_f56538b5-299c-4b53-b3c7-aba7fc72e6c7.jpg","name":"码云周刊 | 首个完整版开源机器人项目","pubDate":"2017-02-13 11:04:52","type":3},{"detail":"","href":"http://www.oschina.net/news/81863/how-to-learn-web-programming-in-2017","id":81863,"img":"https://static.oschina.net/uploads/cooperation/78083/chrome55-save-at-least-35-percent-memory_2e440e4d-b3cb-40eb-82e9-dc9f6b44af94.jpg","name":"2017 年你应该这样学习 Web 编程","pubDate":"2017-02-13 11:08:43","type":6},{"detail":"","href":"https://www.oschina.net/question/2886655_2230954","id":2230954,"img":"https://static.oschina.net/uploads/cooperation/77929/top-income-programming-languages-2016_4bd2fc0a-eed2-4fd2-9e5a-168417bf28f5.png","name":"高手问答 | MySQL 主从复制的那些事","pubDate":"2017-02-13 11:11:42","type":2},{"detail":"","href":"http://www.oschina.net/news/81873/2017-february-yuanchuanghui","id":81873,"img":"https://static.oschina.net/uploads/cooperation/78455/intellij-idea-2016-3-public-preview_d90db147-55b1-4f9b-b915-e6fb0dee07e2.jpg","name":"厦门、福州源创会开始报名啦！","pubDate":"2017-02-13 11:14:12","type":6}]
         * nextPageToken : 61AF0C190D6BD629
         * prevPageToken : 3EA621243546C8A5
         * requestCount : 5
         * responseCount : 5
         * totalResults : 5
         */

        private String nextPageToken;
        private String          prevPageToken;
        private int             requestCount;
        private int             responseCount;
        private int             totalResults;
        private List<ItemsBean> items;

        public String getNextPageToken() {
            return nextPageToken;
        }

        public void setNextPageToken(String nextPageToken) {
            this.nextPageToken = nextPageToken;
        }

        public String getPrevPageToken() {
            return prevPageToken;
        }

        public void setPrevPageToken(String prevPageToken) {
            this.prevPageToken = prevPageToken;
        }

        public int getRequestCount() {
            return requestCount;
        }

        public void setRequestCount(int requestCount) {
            this.requestCount = requestCount;
        }

        public int getResponseCount() {
            return responseCount;
        }

        public void setResponseCount(int responseCount) {
            this.responseCount = responseCount;
        }

        public int getTotalResults() {
            return totalResults;
        }

        public void setTotalResults(int totalResults) {
            this.totalResults = totalResults;
        }

        public List<ItemsBean> getItems() {
            return items;
        }

        public void setItems(List<ItemsBean> items) {
            this.items = items;
        }

        public static class ItemsBean {
            /**
             * detail :
             * href : https://www.oschina.net/news/81098/gitosc-recruitment-2017
             * id : 81098
             * img : https://static.oschina.net/uploads/cooperation/75410/google-beta-natural-language-api_2f605332-a297-4020-8989-bf8910e2b604.jpg
             * name : 2017 开源中国码云招聘
             * pubDate : 2017-01-19 14:17:33
             * type : 6
             */

            private String detail;
            private String href;
            private int    id;
            private String img;
            private String name;
            private String pubDate;
            private int    type;

            public String getDetail() {
                return detail;
            }

            public void setDetail(String detail) {
                this.detail = detail;
            }

            public String getHref() {
                return href;
            }

            public void setHref(String href) {
                this.href = href;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getPubDate() {
                return pubDate;
            }

            public void setPubDate(String pubDate) {
                this.pubDate = pubDate;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }
        }
    }
}
