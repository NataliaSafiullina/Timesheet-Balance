## Репозиторий Timesheet-Balance-Skillbox содержит проект, созданный по техническому заданию:
> Ваша задача состоит в том, чтобы реализовать для программы следующую функциональность, доступную через командную строку:
> 1) program import positions.csv — добавляет в БД указанные в файле positions.csv должности и ставки оплаты труда;
> 2) program import employees.csv — добавляет в БД указанные в файле employees.csv должности и ставки оплаты труда;
> 3) program import timesheet.csv — добавляет в БД указанные в файле timesheet.csv периоды работы сотрудников над задачами;
> 4) program list employee — выводит и перечисляет сотрудников по именам;
> 5) program get [employeeName] — выводит таймшиты сотрудника по его имени;
> 6) program remove [timesheetID] — удаление записи таймшита по идентификатору;
> 7) program report top5longTasks — выводит пять задач, на которые потрачено больше всего времени;
> 8) program report top5costTasks — выводит пять задач, на которые потрачено больше всего денег;
> 9) program report top5employees — выводит пять сотрудников, отработавших наибольшее количество времени за всё время.

>В примерах выше program — имя вашей программы. Оно зависит от того, на каком языке программирования вы будете выполнять данный проект. Пример для Java:\
>java -jar program.jar import positions.csv


## Для запуска программы необходимо:
1. Java Runtime Machine,
2. DBMS MySQL.

## Работа с БД описана в папке for_database_mysql:
1. схема данных, schema.sql
2. DDL для создания триггеров, console_mysql.sql 
3. концептуальная модель БД, Balance_DB_Model.png

## Команды запуска программы.
*Перейдите в папку Balance.*

#### Просмотр содержимого БД:

1. java -jar Balance.main.jar list employee
– программа выводит список сотрудников

2. java -jar Balance.main.jar list position
– программа выводит список должностей

3. java -jar Balance.main.jar list task
– программа выводит список задач

#### Импорт данных в БД:

4. java -jar Balance.main.jar import positions.csv
– программа импортирует данные (список должностей) из файл positions.csv в базу данных Balance.

5. java -jar Balance.main.jar import employees.csv
– программа импортирует данные (список сотрудников) из файл employeess.csv в базу данных Balance.

6. java -jar Balance.main.jar import timesheet.csv
– программа импортирует данные (timesheet) из файл timesheet.csv в базу данных Balance.

#### Манипуляции с данными в БД:

7. java -jar Balance.main.jar get Robert
– выводит таймшиты сотрудника по его имени.

8. java -jar Balance.main.jar remove TimesheetID 
– удаление записи таймшита по идентификатору.

#### Отчеты:

9. java -jar Balance.main.jar report top5longTasks
– выводит пять задач, на которые потрачено больше всего времени.

10. java -jar Balance.main.jar report top5costTasks
– выводит пять задач, на которые потрачено больше всего денег.

11. java -jar Balance.main.jar report top5employees
– выводит пять сотрудников, отработавших наибольшее количество времени.
