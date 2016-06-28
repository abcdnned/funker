package dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import model.Model;

public interface TaskDao {
    @Select("select * from APP.NEWTABLE")
    List<Model> findAll();
}
