package service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.TaskDao;
import model.Model;
import service.Servicee;

@Service
public class ServiceImp implements Servicee {

    @Autowired
    private TaskDao dao;

    @Override
    public List<Model> findAllTasks() {
        return dao.findAll();
    }


}
