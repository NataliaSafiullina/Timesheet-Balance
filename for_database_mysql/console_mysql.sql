-- mysql --

-- �������� �������, ������� ����� ����������� �� �������� ������� �� �������
-- ���������. ������� ������ ������� ��������� ������, �������� ���������
-- ��������� ������ � �������� ������ � ������� �������.
mysql> delimiter //
mysql>
mysql> CREATE TRIGGER ins_history_on_del_timesheet BEFORE DELETE ON timesheet FOR EACH ROW
    -> BEGIN
    -> INSERT INTO timesheet_history (timesheet_id,employee_name,task_name,start_time,finish_time)
    -> SELECT
    -> TS.timesheet_id,
    -> (select EMP.employee_name from employees EMP where EMP.employee_id=TS.employee_id limit 1),
    -> (select T.task_name from tasks T where T.task_id=TS.task_id limit 1),
    -> TS.start_time,
    -> TS.finish_time
    -> FROM timesheet TS
    -> WHERE TS.timesheet_id=OLD.timesheet_id;
    -> END
    -> //
Query OK, 0 rows affected (0.03 sec)
mysql> delimiter ;

-- �������� �������� ins_history_on_del_timesheet
-- �������� ���� �� � tomesheet ������ � ID=6
mysql> select * from timesheet where timesheet_id = 6;
+--------------+-------------+---------+---------------------+---------------------+
| timesheet_id | employee_id | task_id | start_time          | finish_time         |
+--------------+-------------+---------+---------------------+---------------------+
|            6 |           2 |       2 | 2023-01-07 19:00:00 | 2023-01-07 20:00:00 |
+--------------+-------------+---------+---------------------+---------------------+
1 row in set (0.00 sec)

-- �������� ������� timesheet_history - ��� ������.
mysql> select * from timesheet_history;
Empty set (0.00 sec)

-- ������� ������ � ID=6 �� ������� timesheet
mysql> delete from timesheet where timesheet_id = 6;
Query OK, 1 row affected (0.03 sec)

-- �������� ������� timesheet_history - � ��� ��������� �������� ������.
mysql> select * from timesheet_history;
+--------------+---------------+------------+---------------------+---------------------+
| timesheet_id | employee_name | task_name  | start_time          | finish_time         |
+--------------+---------------+------------+---------------------+---------------------+
|            6 | Natalia       | Report1209 | 2023-01-07 19:00:00 | 2023-01-07 20:00:00 |
+--------------+---------------+------------+---------------------+---------------------+
1 row in set (0.00 sec)


-- ��� ����������� ����������� ��������� ����� ��������� �������� ������� �
-- schema.sql BEFORE INSERT/UPDATE, ������� ����� ���������, �� ������������
-- �� �������� ���������� (��������� ����� �������� ������ ��� ����� ������� �
-- ������ ������ �������).     
mysql> delimiter //
mysql>
mysql> create trigger insert_timesheet before insert on timesheet for each row begin 
    -> declare rowcount INT;
    -> select count(1) into rowcount 
    -> from timesheet TS 
    -> where TS.employee_id = NEW.employee_id 
    -> and TS.start_time<=NEW.finish_time 
    -> and TS.finish_time>=NEW.start_time;  
    -> if rowcount > 0 then SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Employee can not execute a few tasks at the same time.'; 
    -> end if; 
    -> end
    -> //
Query OK, 0 rows affected (0.03 sec)

mysql> create trigger update_timesheet before update on timesheet for each row begin 
    -> declare rowcount INT; 
    -> select count(1) into rowcount
    -> from timesheet TS 
    -> where TS.employee_id = NEW.employee_id 
    -> and TS.start_time <= NEW.finish_time 
    -> and TS.finish_time>=NEW.start_time;  
    -> if rowcount > 0 then SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Employee can not execute a few tasks at the same time.'; 
    -> end if; 
    -> end
    -> //
Query OK, 0 rows affected (0.03 sec)
mysql> delimiter ;


-- ��� ������ ������ �������� ����� ������ ������ ��� ������� ������ ����
-- ������ ������� ��������� ������ ��� ���. � ������ ����������� ������������
-- ������ ��������� ������ ������������ � � �������� �������������� � ��������.
mysql> delimiter //
mysql> create trigger update_timesheet_2 before update on timesheet for each row  
    -> begin  
    -> if NEW.start_time > NEW.finish_time 
    -> then SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Finish time can not be less then start time.';  
    -> end if;  
    -> end
    -> //
Query OK, 0 rows affected (0.03 sec)
mysql> delimiter ;

-- �������� �������� update_timesheet_2
mysql> insert into timesheet set employee_id=1, task_id=1, start_time='2023-01-07 13:00:00', finish_time='2023-01-07 19:00:00';
ERROR 1644 (45000): Employee can not execute a few tasks at the same time.
mysql>
mysql> insert into timesheet set employee_id=1, task_id=1, start_time='2023-01-07 19:00:00', finish_time='2023-01-07 13:00:00';
ERROR 1644 (45000): Finish time can not be less then start time.
mysql>


-- �������� ��������, ��� � �� ��� ����� ��� ���������. � ������ �������� ������
-- �� ������� ����� ����� ������ ���� �������.
-- �.�. ������� �� �������� ������ � timesheet, ������� ������ � ������� tasks, ���� ������ ��� ��������� � ����� �������.

mysql> DELIMITER //
mysql>
mysql> CREATE TRIGGER del_task_on_del_timesheet AFTER DELETE ON timesheet FOR EACH ROW BEGIN 
    -> DECLARE rowcount INT; 
    -> SELECT COUNT(1) INTO rowcount 
    -> FROM timesheet ts 
    -> WHERE ts.task_id = OLD.task_id;  
    -> IF rowcount < 1 THEN DELETE FROM tasks t WHERE t.task_id = OLD.task_id; 
    -> END IF; 
    -> END;
    -> //
Query OK, 0 rows affected (0.44 sec)
mysql> DELIMITER ;
