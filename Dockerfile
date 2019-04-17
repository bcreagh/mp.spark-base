FROM java:9-jre

EXPOSE 4567

ADD target/mpspark-1.0-SNAPSHOT-jar-with-dependencies.jar /mp_spark-base
WORKDIR /mp_spark-base

ENTRYPOINT ["./entrypoint.sh"]