FROM java:8-jre

COPY target/devops2-jar.jar /opt

EXPOSE 9999

CMD ["java", "-jar", "/opt/devops2-jar.jar"]
