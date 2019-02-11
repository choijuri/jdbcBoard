package my.examples.jdbcboard.dto;


import java.util.Date;

public class Board1 {

    private long id;
    private long thread;
    private int depth;
    private long userId;
    private String nickname;
    private String title;
    private String content;
    private Date regdate;
    private int readCount;


    //id, title, content, name, regdate, readCount
    public Board1(){
        regdate = new Date();
    }

    // get boards 용.
    public Board1(Long id, Long userId, String nickname, String title,Date regdate, int readCount, int depth ) {
        this();
        this.id = id;
        this.userId = userId;
        this.nickname = nickname;
        this.title = title;
        this.regdate = regdate;
        this.readCount = readCount;
        this.depth = depth;
    }
    //write 용
    public Board1(Long userId, String nickname, String title, String content) {
        this();
        this.userId = userId;
        this.nickname = nickname;
        this.title = title;
        this.content = content;
    }

    public Board1(Long id, Long userId, String nickname, String title, String content, Date regdate, int readCount) {
        this(userId, nickname, title, content);
        this.id = id;
        this.nickname = nickname;
        this.regdate = regdate;
        this.readCount = readCount;
    }



//    public Board1(long userId, String title, String content){
//        this();
//        this.userId = userId;
//        this.title = title;
//        this.content = content;
//    }
//
//    public Board1(String nickname, String title, String content) {
//        this.nickname = nickname;
//        this.title = title;
//        this.content = content;
//    }
//
//    ////id, title, content, nickname, regdate, readCount
//    public Board1(long id, String title,String content, String nickname ,Date regdate, int readCount) {
//        this();
//        this.id = id;
//        this.content = content;
//        this.nickname = nickname;
//        this.title = title;
//        this.readCount = readCount;
//    }
//
//
//    public Board1(Long id, long userId, String nickname, String title, String content) {
//        this.userId = userId;
//        this.title = title;
//        this.content = content;
//        this.nickname = nickname;
//        this.id = id;
//    }
//
//    public Board1(long id, long thread, int depth, long userId, String nickname, String title, String content, Date regdate, int readCount) {
//        this(id, userId, nickname, title, content);
//        this.thread = thread;
//        this.depth = depth;
//    }


    public long getThread() {
        return thread;
    }

    public void setThread(long thread) {
        this.thread = thread;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String name) {
        this.nickname = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getRegdate() {
        return regdate;
    }

    public void setRegdate(Date regdate) {
        this.regdate = regdate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getReadCount() {
        return readCount;
    }

    public void setReadCount(int readCount) {
        this.readCount = readCount;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }





    @Override
    public String toString() {
        return "Board{" +
                "id=" + id +
                ", name='" + nickname + '\'' +
                ", title='" + title + '\'' +
                ", regdate=" + regdate +
                ", content='" + content + '\'' +
                ", readCount=" + readCount +
                '}';
    }
}
