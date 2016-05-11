# ilibrary
Web library

### Backend:
Web library is developed with Spring MVC.
Authorization is based on Spring Security.
JdbcTemplate is used for access to data.
Data base is h2 base with liquibase initialization.
###### Embedded base only for tests. There is in-memory h2 data base, which init from create.sql. So, you can't change data, which inserted from .sql file, because url is "jdbc:h2:mem:lib" - READ-ONLY! But "DB_CLOSE_DELAY=-1" allows you to select, insert, update, delete new data and to select or update read-only data while program is running.

### Frontend:
Html + css + JQuery 2.2.3


