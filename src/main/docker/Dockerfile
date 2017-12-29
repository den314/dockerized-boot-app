FROM anapsix/alpine-java:8_jdk
VOLUME /tmp
COPY app.jar /app.jar
CMD java -Djava.security.egd=file:/dev/./urandom -jar app.jar