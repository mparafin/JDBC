CREATE OR REPLACE PROCEDURE InsertEmployee
(p_first_name IN VARCHAR2,
p_last_name IN VARCHAR2, 
p_email IN VARCHAR2, 
p_phone_number IN VARCHAR2, 
p_hire_date IN DATE, 
p_job_id IN VARCHAR2, 
p_salary IN NUMBER, 
p_commission_pct IN NUMBER, 
p_manager_id IN NUMBER, 
p_department_id IN NUMBER) IS

v_employee_id NUMBER;
v_id_tester NUMBER;
v_min_salary NUMBER;
v_max_salary NUMBER;
v_today DATE;

BEGIN

IF p_last_name IS NULL OR p_email IS NULL or p_hire_date IS NULL OR p_job_id IS NULL THEN
RAISE_APPLICATION_ERROR(-20001, 'Null value of a not nullable field');
END IF;

SELECT employee_id INTO v_id_tester FROM EMPLOYEES WHERE employee_id = p_manager_id;

SELECT department_id INTO v_id_tester FROM DEPARTMENTS WHERE department_id = p_department_id;

SELECT min_salary, max_salary INTO v_min_salary, v_max_salary FROM JOBS WHERE job_id = p_job_id;

IF p_salary < v_min_salary OR p_salary > v_max_salary THEN
RAISE_APPLICATION_ERROR(-20005, 'Incorrect salary');
END IF;

SELECT SYSDATE INTO v_today FROM DUAL;
IF p_hire_date > v_today THEN
RAISE_APPLICATION_ERROR(-20006, 'Future hire date');
END IF;

--testing over
v_employee_id := SEQ_EMPLOYEE_ID.NEXTVAL;
INSERT INTO EMPLOYEES (employee_id, first_name, last_name, email, phone_number, hire_date, job_id, salary, commission_pct, manager_id, department_id)
VALUES (v_employee_id, p_first_name, p_last_name, p_email, p_phone_number, p_hire_date, p_job_id, p_salary, p_commission_pct, p_manager_id, p_department_id);

EXCEPTION
WHEN NO_DATA_FOUND THEN

IF v_id_tester IS NULL THEN
RAISE_APPLICATION_ERROR(-20002, 'Incorrect manager ID');
END IF;

IF v_id_tester = p_manager_id THEN
RAISE_APPLICATION_ERROR(-20003, 'Incorrect department ID');
END IF;

IF v_id_tester = p_department_id THEN
RAISE_APPLICATION_ERROR(-20004, 'Incorrect job ID');
END IF;

END;
