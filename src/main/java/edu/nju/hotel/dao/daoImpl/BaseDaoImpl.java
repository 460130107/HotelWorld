package edu.nju.hotel.dao.daoImpl;

import edu.nju.hotel.dao.BaseDao;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by zhouxiaofan on 2017/1/22.
 */
@Repository
@Transactional
public class BaseDaoImpl implements BaseDao {
    @PersistenceContext
    private EntityManager em;

    @Override
    public void insert(Object obj) {
        em.persist(obj);
        em.flush();
    }

    @Override
    public void delete(Class<?> c, long id) {
        Object obj=load(c,id);
        em.remove(obj);
        em.flush();
    }

    @Override
    public void update(Object obj) {
        em.merge(obj);
    }

    @Override
    public Object load(Class<?> c, long id) {
        return em.find(c,id);
    }
}
