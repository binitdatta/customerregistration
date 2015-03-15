FROM centos:6
MAINTAINER binitdatta@gmail.com
RUN yum install tar -y
RUN yum install unzip -y
RUN yum install wget -y
RUN yum install java-1.7.0-openjdk-devel -y
ENV JAVA_HOME=/etc/alternatives/java_sdk_1.7.0
RUN export PATH=$PATH:$JAVA_HOME/bin
RUN java -version
# Install tomcat
RUN wget http://apache.cs.utah.edu/tomcat/tomcat-7/v7.0.59/bin/apache-tomcat-7.0.59.tar.gz
RUN tar xzf apache-tomcat-7.0.59.tar.gz
RUN mv apache-tomcat-7.0.59 /usr/local/tomcat7
RUN sed -i 's/8080/8016/g' /usr/local/tomcat7/conf/server.xml
RUN echo $JAVA_HOME >> /etc/default/tomcat7
RUN java -version
# Download Slashdot homepage
ADD target/CustomerRegistration.war /usr/local/tomcat7/webapps/
ENV CATALINA_HOME /usr/local/tomcat7
ADD run.sh /usr/local/tomcat7/bin/
RUN chmod 755 /usr/local/tomcat7/bin/run.sh
EXPOSE 8016
CMD /usr/local/tomcat7/bin/run.sh