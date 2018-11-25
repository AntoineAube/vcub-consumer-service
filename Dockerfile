FROM tomee:8-jre-7.0.3-plus
MAINTAINER Antoine Aubé (aube.antoine@protonmail.com)

WORKDIR /usr/local/tomee/

COPY ./target/vcs.war ./webapps/.

EXPOSE 8080