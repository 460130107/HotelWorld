package edu.nju.hotel.dao.daoImpl;

import edu.nju.hotel.dao.BaseDao;
import edu.nju.hotel.dao.UserDao;
import edu.nju.hotel.model.UserEntity;
import edu.nju.hotel.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
public class UserDaoImpl implements UserDao {
    @Autowired
    BaseDao baseDao;

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private UserRepository up;

    @Override
    public void save(UserEntity user) {
        baseDao.insert(user);
    }

    @Override
    public List<UserEntity> findAll() {
        Query q=em.createQuery("select u from UserEntity u");
        List<UserEntity> list=q.getResultList();
        return list;
    }

    @Override
    public boolean verify(String username, String password) {
        return false;
    }

    @Override
    public UserEntity getUserByName(String username) {
        Query q=em.createQuery("select u from UserEntity u where u.nickname =?1");
        q.setParameter(1,username);
        UserEntity user= (UserEntity) q.getSingleResult();
        return user;
    }

    @Override
    public UserEntity getUserById(int id) {
        Query q=em.createQuery("select u from UserEntity u where u.id =?1");
        q.setParameter(1,id);
        UserEntity user= (UserEntity) q.getSingleResult();
        return user;
    }

    @Override
    public void deleteInBatch() {

    }

    @Override
    public void deleteById(int id) {
        baseDao.delete(UserEntity.class,id);
    }

    @Override
    public void update(UserEntity userEntity) {
        em.merge(userEntity);
    }
}
