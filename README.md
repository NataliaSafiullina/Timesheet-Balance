����������� Timesheet-Balance-Skillbox �������� ������,
��������� �� ������������ �������:

���� ������ ������� � ���, ����� ����������� ��� ��������� ���������
����������������, ��������� ����� ��������� ������:
1) program import positions.csv � ��������� � �� ��������� � ����� positions.csv
��������� � ������ ������ �����;
2) program import employees.csv � ��������� � �� ��������� � ����� employees.csv
��������� � ������ ������ �����;
3) program import timesheet.csv � ��������� � �� ��������� � ����� timesheet.csv
������� ������ ����������� ��� ��������;
4) program list employee � ������� � ����������� ����������� �� ������;
5) program get [employeeName] � ������� �������� ���������� �� ��� �����;
6) program remove [timesheetID] � �������� ������ �������� �� ��������������;
7) program report top5longTasks � ������� ���� �����, �� ������� ��������� ������
����� �������;
8) program report top5costTasks � ������� ���� �����, �� ������� ��������� ������
����� �����;
9) program report top5employees � ������� ���� �����������, ������������
���������� ���������� ������� �� �� �����.
� �������� ���� program � ��� ����� ���������. ��� ������� �� ����, �� ����� �����
���������������� �� ������ ��������� ������ ������. ������ ��� Java:
java -jar program.jar import positions.csv


��� ������� ��������� ����������:
� Java Runtime Machine,
� DBMS MySQL.

������ � �� ������� � ����� for_database_mysql:
� ����� ������, schema.sql
� DDL ��� �������� ���������, console_mysql.sql 
� �������������� ������ ��, Balance_DB_Model.png

������� ������� ���������.
��������� � ����� Balance.

�������� ����������� ��:

1. java -jar Balance.main.jar list employee
� ��������� ������� ������ �����������

2. java -jar Balance.main.jar list position
� ��������� ������� ������ ����������

3. java -jar Balance.main.jar list task
� ��������� ������� ������ �����

������ ������ � ��:

4. java -jar Balance.main.jar import positions.csv
� ��������� ����������� ������ (������ ����������) �� ���� positions.csv � ���� ������ Balance.

5. java -jar Balance.main.jar import employees.csv
� ��������� ����������� ������ (������ �����������) �� ���� employeess.csv � ���� ������ Balance.

6. java -jar Balance.main.jar import timesheet.csv
� ��������� ����������� ������ (timesheet) �� ���� timesheet.csv � ���� ������ Balance.

����������� � ������� � ��:

7. java -jar Balance.main.jar get Robert
� ������� �������� ���������� �� ��� �����.

8. java -jar Balance.main.jar remove TimesheetID 
� �������� ������ �������� �� ��������������.

������:

9. java -jar Balance.main.jar report top5longTasks
� ������� ���� �����, �� ������� ��������� ������ ����� �������.

10. java -jar Balance.main.jar report top5costTasks
� ������� ���� �����, �� ������� ��������� ������ ����� �����.

11. java -jar Balance.main.jar report top5employees
� ������� ���� �����������, ������������ ���������� ���������� �������.
