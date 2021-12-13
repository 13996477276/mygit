package com.example.thymeleaf.dao;

import com.example.thymeleaf.pojo.Department;
import com.example.thymeleaf.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class EmplyeeDao {
    private static Map<Integer, Employee> employees = null;
    @Autowired
    private DepartmentDao departmentDao;
    static {
        employees = new HashMap<Integer,Employee>();
        employees.put(1001,new Employee(1001,1,"a29467172@qq.com","aa",new Department(101,"教学部")));
        employees.put(1002,new Employee(1002,1,"b29467172@qq.com","bb",new Department(101,"市场部")));
        employees.put(1003,new Employee(1003,0,"c29467172@qq.com","cc",new Department(101,"教研部")));
        employees.put(1004,new Employee(1004,0,"d29467172@qq.com","dd",new Department(101,"运营部")));
        employees.put(1005,new Employee(1005,1,"e29467172@qq.com","ee",new Department(101,"后勤部")));
    }
    private static Integer initId = 1006;
    public void save(Employee employee){
        if(null==employee.getId()){
            employee.setId(initId++);
        }
        employee.setDepartment(departmentDao.getDepartmentById(employee.getDepartment().getId()));
        employees.put(employee.getId(),employee);
    }
    public Collection<Employee> getAll(){
        return employees.values();
    }
    public Employee getEmployeeById(Integer id){
        return employees.get(id);
    }
    public void delete(Integer id){
        employees.remove(id);
    }
}
