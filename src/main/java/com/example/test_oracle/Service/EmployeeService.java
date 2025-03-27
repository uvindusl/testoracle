package com.example.test_oracle.Service;

import com.example.test_oracle.Entity.EmployeeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

@Service
public class EmployeeService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public EmployeeEntity getEmployeeDetails(String employeeId){
        try{
            //calling the pl/sql stored procedure
            return jdbcTemplate.execute((Connection conn)-> {
                CallableStatement cs = conn.prepareCall("{call get_employee_details(?,?,?,?,?,?,?)}");

                //set input parameter
                cs.setString(1, employeeId);

                //register output parameter
                cs.registerOutParameter(2 , Types.VARCHAR);
                cs.registerOutParameter(3, Types.VARCHAR);
                cs.registerOutParameter(4, Types.INTEGER);
                cs.registerOutParameter(5, Types.DATE);
                cs.registerOutParameter(6, Types.VARCHAR);
                cs.registerOutParameter(7, Types.DOUBLE);

                //Execute the stored procedure
                cs.execute();

                //get the output parameter
                String firstName = cs.getString(2);
                String lastName = cs.getString(3);
                int departmentId = cs.getInt(4);
                String joinedDate = cs.getString(5);
                String gender = cs.getString(6);
                double salary = cs.getDouble(7);

                //return the result
                return new EmployeeEntity(firstName,lastName,departmentId,joinedDate,gender,salary);

            });
        }catch (DataAccessException e){
            throw new RuntimeException("Error excuting stored procedure", e);
        }
    }

}