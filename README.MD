### Restaurant voting
Написать RestFul app на hibernate + servlets по патерну MVC: controllers, dao, dto, entity, services
Должно быть 5 таблиц. Минимум 1 many2many relations и 1 one2many relations:

1. СRUD по всем таблицам + getAll
2. Реализовать paging по 2 из таблиц
3. Если у вас таблциы связаны и изменение происходить в одной из таблиц, то изменение должно происходить и в другой
4. Реализовать поиск в каждой таблице по какой-нибудь колонки кроме ID
5. Придумать хэндлинг ошибок.