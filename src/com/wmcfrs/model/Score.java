package com.wmcfrs.model;

import com.wmcfrs.model.Music;
import com.wmcfrs.model.User;

/**
 * 评分实体类
 */
public class Score  implements java.io.Serializable {


	private static final long serialVersionUID = 1L;
    private Integer id;
    private Music music;
    private User user;
    private Integer point;


    public Score() {
    }

    public Score(Music music, User user, Integer point) {
        this.music = music;
        this.user = user;
        this.point = point;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public Music getMusic() {
        return this.music;
    }
    
    public void setMusic(Music music) {
        this.music = music;
    }

    public User getUser() {
        return this.user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }

    public Integer getPoint() {
        return this.point;
    }
    
    public void setPoint(Integer point) {
        this.point = point;
    }

}