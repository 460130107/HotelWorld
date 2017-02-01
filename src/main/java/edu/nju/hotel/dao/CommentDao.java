package edu.nju.hotel.dao;

import edu.nju.hotel.model.Comment;

import java.util.List;

/**
 * Created by zhouxiaofan on 2017/1/22.
 */
public interface CommentDao {
    List<Comment> findAll();
}
