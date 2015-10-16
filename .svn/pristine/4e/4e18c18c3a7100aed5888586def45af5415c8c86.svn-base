package com.baosteel.qcsh.model;

import java.io.Serializable;
import java.util.List;

/**
 * 商品评价列表数据
 * Created by kuangyong on 15/9/30.
 */
public class QueryAppGoodsCommentBean implements Serializable{

    /**
     * returnMap : {"total":1,"pages":1,"pageSize":1,"list":[{"content":"这件商品不错","id":1,"username":"bg_0000000040","avgScore":4,"appendComment":[{"content":"忘记追加了","id":1,"url":"","comment_time":"2015-09-01 09:00:00"},{"content":"该商品不错","id":2,"url":"","comment_time":"2015-09-29 19:56:30"},{"content":"该商品不错","id":3,"url":"","comment_time":"2015-09-29 19:57:09"},{"content":"该商品不错","id":4,"url":"","comment_time":"2015-09-29 20:01:33"},{"content":"该商品不错","id":5,"url":"","comment_time":"2015-09-30 10:46:27"}],"comment_type":0,"url":"","comment_time":"2015-08-15 08:00:00"}],"pageNum":1}
     * type : 1
     * msg : 成功
     */

    private ReturnMapEntity returnMap;
    private int type;
    private String msg;

    public void setReturnMap(ReturnMapEntity returnMap) {
        this.returnMap = returnMap;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ReturnMapEntity getReturnMap() {
        return returnMap;
    }

    public int getType() {
        return type;
    }

    public String getMsg() {
        return msg;
    }

    public static class ReturnMapEntity {
        /**
         * total : 1
         * pages : 1
         * pageSize : 1
         * list : [{"content":"这件商品不错","id":1,"username":"bg_0000000040","avgScore":4,"appendComment":[{"content":"忘记追加了","id":1,"url":"","comment_time":"2015-09-01 09:00:00"},{"content":"该商品不错","id":2,"url":"","comment_time":"2015-09-29 19:56:30"},{"content":"该商品不错","id":3,"url":"","comment_time":"2015-09-29 19:57:09"},{"content":"该商品不错","id":4,"url":"","comment_time":"2015-09-29 20:01:33"},{"content":"该商品不错","id":5,"url":"","comment_time":"2015-09-30 10:46:27"}],"comment_type":0,"url":"","comment_time":"2015-08-15 08:00:00"}]
         * pageNum : 1
         */

        private int total;                  //总数
        private int pages;                  //总页码
        private int pageSize;               //每页数量
        private int pageNum;                //当前页
        private List<ListEntity> list;

        public boolean hasComments(){
        	if(null != list && list.size() > 0){
        		return true;
        	}
        	
        	return false;
        }
        
        public void setTotal(int total) {
            this.total = total;
        }

        public void setPages(int pages) {
            this.pages = pages;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public void setPageNum(int pageNum) {
            this.pageNum = pageNum;
        }

        public void setList(List<ListEntity> list) {
            this.list = list;
        }

        public int getTotal() {
            return total;
        }

        public int getPages() {
            return pages;
        }

        public int getPageSize() {
            return pageSize;
        }

        public int getPageNum() {
            return pageNum;
        }

        public List<ListEntity> getList() {
            return list;
        }

        public static class ListEntity {
            /**
             * content : 这件商品不错
             * id : 1
             * username : bg_0000000040
             * avgScore : 4
             * appendComment : [{"content":"忘记追加了","id":1,"url":"","comment_time":"2015-09-01 09:00:00"},{"content":"该商品不错","id":2,"url":"","comment_time":"2015-09-29 19:56:30"},{"content":"该商品不错","id":3,"url":"","comment_time":"2015-09-29 19:57:09"},{"content":"该商品不错","id":4,"url":"","comment_time":"2015-09-29 20:01:33"},{"content":"该商品不错","id":5,"url":"","comment_time":"2015-09-30 10:46:27"}]
             * comment_type : 0
             * url :
             * comment_time : 2015-08-15 08:00:00
             */

            private String content;         //评价内容
            private String id;              //评价记录id
            private String username;        //用户名
            private int avgScore;           //总评分
            private String comment_type;    //评价类型(0:实名，1：匿名)
            private String url;             //图片地址
            private String comment_time;    //评价时间
            private List<AppendCommentEntity> appendComment;//追加评论列表

            public void setContent(String content) {
                this.content = content;
            }

            public void setId(String id) {
                this.id = id;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public void setAvgScore(int avgScore) {
                this.avgScore = avgScore;
            }

            public void setComment_type(String comment_type) {
                this.comment_type = comment_type;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public void setComment_time(String comment_time) {
                this.comment_time = comment_time;
            }

            public void setAppendComment(List<AppendCommentEntity> appendComment) {
                this.appendComment = appendComment;
            }

            public String getContent() {
                return content;
            }

            public String getId() {
                return id;
            }

            public String getUsername() {
                return username;
            }

            public int getAvgScore() {
                return avgScore;
            }

            public String getComment_type() {
                return comment_type;
            }

            public String getUrl() {
                return url;
            }

            public String getComment_time() {
                return comment_time;
            }

            public List<AppendCommentEntity> getAppendComment() {
                return appendComment;
            }

            public static class AppendCommentEntity {
                /**
                 * content : 忘记追加了
                 * id : 1
                 * url :
                 * comment_time : 2015-09-01 09:00:00
                 */

                private String content;         //追加内容
                private String id;                 //追加记录id
                private String url;             //追加图片
                private String comment_time;    //追加时间

                public void setContent(String content) {
                    this.content = content;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public void setUrl(String url) {
                    this.url = url;
                }

                public void setComment_time(String comment_time) {
                    this.comment_time = comment_time;
                }

                public String getContent() {
                    return content;
                }

                public String getId() {
                    return id;
                }

                public String getUrl() {
                    return url;
                }

                public String getComment_time() {
                    return comment_time;
                }
            }
        }
    }
}
