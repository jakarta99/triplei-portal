set war=triplei-portal-0.0.1-SNAPSHOT.war
java -jar -Xms512m -Xmx512m -Dfile.encoding=UTF-8 -Dupload.location=C:\Temp -jar target\%war% --spring.jpa.hibernate.ddl-auto=update
goto exit