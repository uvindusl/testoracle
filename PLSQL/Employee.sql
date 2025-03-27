CREATE OR REPLACE PROCEDURE get_employee_details (
    p_employee_id IN VARCHAR2, 
    p_first_name OUT VARCHAR2,
    p_last_name OUT VARCHAR2,
    p_department_id OUT NUMBER,
    p_joined_date OUT DATE,
    p_gender OUT VARCHAR2,
    p_salary OUT NUMBER
)
IS
BEGIN
    SELECT 
        FIRSTNAME, 
        LASTNAME, 
        DEPARTMENTID, 
        JOINEDDATE, 
        GENDER, 
        SALARY
    INTO 
        p_first_name, 
        p_last_name, 
        p_department_id, 
        p_joined_date, 
        p_gender, 
        p_salary
    FROM 
        EMPLOYEE
    WHERE 
        EMPLOYEEID = p_employee_id;
END get_employee_details;
