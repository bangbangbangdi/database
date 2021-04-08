package com.bangdi.dao;

import com.bangdi.entity.Emp;

public interface EmpDao {
    //插入数据
    public void insert(Emp emp);

    //删除数据
    public void delete(Emp emp);

    //修改数据
    public void updata(Emp emp);

    //查找数据
    public Emp getEmpByEmpno(Integer empno);

    public Emp getEmpByEname(String ename);
}
