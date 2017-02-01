package edu.nju.hotel.dao.daoImpl;

import edu.nju.hotel.dao.CommentDao;
import edu.nju.hotel.model.Comment;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by zhouxiaofan on 2017/1/22.
 */
@Repository
@Transactional
public class CommentDaoImpl implements CommentDao {
    @PersistenceContext
    private EntityManager em;
    @Override
    public List<Comment> findAll() {
        Query q=em.createQuery("select c from Comment c");
        List<Comment> list=q.getResultList();
        return list;
    }
}
