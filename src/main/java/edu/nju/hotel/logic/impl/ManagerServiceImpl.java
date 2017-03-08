package edu.nju.hotel.logic.impl;

import edu.nju.hotel.data.model.Admin;
import edu.nju.hotel.data.repository.ManagerRepository;
import edu.nju.hotel.data.util.VerifyResult;
import edu.nju.hotel.logic.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhouxiaofan on 2017/3/7.
 */
@Service
public class ManagerServiceImpl implements ManagerService {
    @Autowired
    private ManagerRepository managerRepository;

    @Override
    public VerifyResult verifyLogin(String name, String password) {
        List<Admin> adminList=managerRepository.getAdmin(name);

        if( adminList.size()==0){
            return VerifyResult.NOTEXIST;
        }
        else if(password.equals(adminList.get(0).getPsw())){
            return VerifyResult.SUCCESS;
        }
        return VerifyResult.INCORRECT;
    }

}
