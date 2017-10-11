set war=triplei-portal-0.0.1-SNAPSHOT.war
java -jar -Xms768m -Xmx768m -Xss512m -Dfile.encoding=UTF-8 target\%war% --spring.jpa.hibernate.ddl-auto=update
goto exit