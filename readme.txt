To Run the DavisBase execute the followin command in the terminal at the current downloaded folder. 

>javac DavisBase.java
>java DavisBase

To test the program execute following query at the output terminal:

Supported sql commands and their syntax are as follows : Please Press Enter after each command(s).
You can test all the feature(Insert, Update, Delete, Select, Drop, Create, Show by testing the following commands).

1) gives the list of all tables
	show tables;

2) Create the table.
	create table gdb (id int primary key,name text [not null],age int);
	show tables;

3) Insert the record in the table.
	insert into table (id,name,age) gdb values (1,hearty,42);
	insert into table (id,name,age) gdb values (2,handi,22);

4) Display all/specific attribute with/without where condition the values from table.
	select * from gdb;
	select name,age from gdb;
	select id,age from gdb;
	select * from gdb where id = 1;
	select * from gdb where name = handi;
	select id,name,age from gdb where id = 1;
	select name,age from gdb where name = handi;
	select name from gdb where age > 8;
	select * from gdb where id < 3;

5) Display the meta info from the column table;
	select * from columns;


6) Update
	select * from gdb;
	update gdb set age = 12 where name = hearty;
	select * from gdb;

7) Delete
	select * from gdb;
	delete from table gdb where rowid = 1;
	select * from gdb;

8) Drop
	drop table gdb;
	show tables;