CREATE TABLE APP_WEATHER (ID INTEGER, CITY VARCHAR2(50), UPDATED_TIME VARCHAR2(30), WEATHER VARCHAR2(50), TEMPERATURE VARCHAR2(10), WIND VARCHAR2(10), VALID NUMBER(1));

Insert into APP_WEATHER (ID,CITY,UPDATED_TIME,WEATHER,TEMPERATURE,WIND,VALID) values (0, 'Melbourne', 'Thursday 11:00 AM', 'Mostly Cloudy', '9℃', '32km/h', 1);
Insert into APP_WEATHER (ID,CITY,UPDATED_TIME,WEATHER,TEMPERATURE,WIND,VALID) values (1, 'Sydney', 'Wednesday 10:00 AM', 'Sunny', '12℃', '30km/h', 0);
Insert into APP_WEATHER (ID,CITY,UPDATED_TIME,WEATHER,TEMPERATURE,WIND,VALID) values (2, 'Wolongong', 'Wednesday 09:05 AM', 'Rainy', '6℃', '40km/h', 0);
