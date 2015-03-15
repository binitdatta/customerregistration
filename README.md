1. Create a folder

2. Copy Vagrantfile

3. Change Vagrantfile

4. vagrant up

5. vagrant ssh

6. sudo yum update -y 

7. mkdir software

6. cd software

7. sudo yum install git -y

8. git clone https://github.com/elizabetht/StudentEnrollmentWithSpring.git

9. Remove the pluginmanagement and plugins elements from the pom.xml

10. Edit the jpaContext.xml under src/main/resources to 

<property name="url" value="jdbc:mysql://10.0.0.13:3306/mkyong" />

11. Run this in local MySQL

start of mysql confusion

GRANT ALL PRIVILEGES ON mykong.* TO 'user'@'%' IDENTIFIED BY 'root';
GRANT ALL PRIVILEGES ON mykong.* TO 'user'@'10.0.0.13' IDENTIFIED BY 'root';
GRANT ALL PRIVILEGES ON mykong.* TO 'user'@'10.0.0.22' IDENTIFIED BY 'root';

sudo yum -y install mysql-server; # for mac

/etc/rc.d/init.d/mysqld start #for linux
 cd /usr/mysql-test ; #for linux
 perl mysql-test-run.pl #for linux

mysql_secure_installation #for linux

http://coolestguidesontheplanet.com/start-stop-mysql-from-the-command-line-terminal-osx-linux/
GRANT ALL PRIVILEGES ON mykong.* TO 'user'@'%' IDENTIFIED BY 'root'l
GRANT ALL PRIVILEGES ON mykong.* TO 'user'@'10.0.0.13' IDENTIFIED BY 'root';
GRANT ALL PRIVILEGES ON mykong.* TO 'user'@'10.0.0.22' IDENTIFIED BY 'root'

https://help.ubuntu.com/community/MysqlPasswordReset
GRANT ALL PRIVILEGES ON mykong.* TO 'root'@'%' IDENTIFIED BY 'root'

USE mysql

   UPDATE user SET Password = PASSWORD('root')
   WHERE Host = 'localhost' AND User = 'root';
   
   UPDATE user SET Password = PASSWORD('root')
   WHERE Host = '%' AND User = 'root';
   
   FLUSH PRIVILEGES;

   restart mysql

http://coolestguidesontheplanet.com/start-stop-mysql-from-the-command-line-terminal-osx-linux/

http://www.server-world.info/en/note?os=CentOS_6&p=mysql

End of mysql confusion


12. sudo rpm -Uvh http://download.fedoraproject.org/pub/epel/6/i386/epel-release-6-8.noarch.rpm

13. sudo yum -y install docker-io

14. sudo yum install java-1.7.0-openjdk-devel -y

15.export JAVA_HOME=/etc/alternatives/java_sdk_1.7.0

16. export PATH=$PATH:$JAVA_HOME/bin

17. wget ftp://mirror.reverse.net/pub/apache/maven/maven-3/3.2.5/binaries/apache-maven-3.2.5-bin.zip

18. sudo yum install unzip -y

19. unzip apache-maven-3.2.5-bin.zip

20. export MAVEN_HOME=/home/vagrant/software/apache-maven-3.2.5

21. export PATH=$PATH:$MAVEN_HOME/bin

23. cd StudentEnrollmentWithSpring

24. mvn clean install

25. vi Dockerfile

PAste the following content

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
RUN sed -i 's/8080/8020/g' /usr/local/tomcat7/conf/server.xml
RUN echo $JAVA_HOME >> /etc/default/tomcat7
RUN java -version
# Download Slashdot homepage
ADD target/StudentEnrollment.war /usr/local/tomcat7/webapps/
ENV CATALINA_HOME /usr/local/tomcat7
ADD run.sh /usr/local/tomcat7/bin/
RUN chmod 755 /usr/local/tomcat7/bin/run.sh
EXPOSE 8020
CMD /usr/local/tomcat7/bin/run.sh


Gold File

FROM centos
MAINTAINER binitdatta@gmail.com
RUN yum install git -y
RUN yum install tar -y
RUN yum install unzip -y
RUN yum install wget -y
RUN cd /tmp
RUN wget --no-check-certificate --no-cookies --header "Cookie: oraclelicense=accept-securebackup-cookie" http://download.oracle.com/otn-pub/java/jdk/7u67-b01/jdk-7u67-l
inux-x64.tar.gz
RUN tar -xvf jdk-7u67-linux-x64.tar.gz
RUN mv jdk1.7.0_67 /usr/local/bin
ENV JAVA_HOME=/usr/local/bin/jdk1.7.0_67
ENV PATH=$JAVA_HOME/bin:$PATH
RUN echo $JAVA_HOME
RUN echo $PATH
RUN java -version
# Install tomcat This may have to change
RUN wget http://apache.cs.utah.edu/tomcat/tomcat-7/v7.0.59/bin/apache-tomcat-7.0.59.tar.gz
RUN tar xvf apache-tomcat-7.0.59.tar.gz
RUN mv apache-tomcat-7.0.59 /usr/local/tomcat7
RUN sed -i 's/8080/9030/g' /usr/local/tomcat7/conf/server.xml
RUN echo $JAVA_HOME >> /etc/default/tomcat7
RUN java -version
# Download Slashdot homepage
ADD build/libs/gradle-spring-mvc-web-project-1.0.war /usr/local/tomcat7/webapps/
ENV CATALINA_HOME /usr/local/tomcat7
ADD run.sh /usr/local/tomcat7/bin/
RUN chmod 755 /usr/local/tomcat7/bin/run.sh
EXPOSE 9030
CMD /usr/local/tomcat7/bin/run.sh

26. vi run.sh

Paste the following content

$CATALINA_HOME/bin/catalina.sh start && tail -f $CATALINA_HOME/logs/catalina.out

27. chmod 755 run.sh

28. sudo service docker start


29. sudo docker build –t tomcat7 .

30. Take a break............................

31. sudo docker images

32. sudo docker ps

33.  sudo docker run -d -p 8020:8020 -t tomcat7

31.  sudo docker ps

32. curl http://localhost:8020	     [proves tomctat runing]

http://localhost:9020/gradle-spring-mvc-web-project-1.0/hello

http://localhost:8020/StudentEnrollment


33. Try this from Windows / Mac : http://172.28.128.4:9040/gradle-spring-mvc-web-project-1.0/hello/mkyong

34. sudo docker exec -i -t 7b16bb93cd5a bash 


Gold Dockerfile

FROM centos:6
MAINTAINER binitdatta@gmail.com
sudo yum update -y
RUN yum install tar -y
RUN yum install unzip -y
RUN yum install wget -y
RUN cd /tmp
RUN yum install java-1.7.0-openjdk-devel -y
RUN export JAVA_HOME=/etc/alternatives/java_sdk_1.7.0
RUN export PATH=$PATH:$JAVA_HOME/bin
RUN java -version
# Install tomcat
RUN wget http://apache.cs.utah.edu/tomcat/tomcat-7/v7.0.59/bin/apache-tomcat-7.0.59.tar.gz
RUN tar xzf apache-tomcat-7.0.59.tar.gz
RUN mv apache-tomcat-7.0.59 /usr/local/tomcat7
RUN sed -i 's/8080/9020/g' /usr/local/tomcat7/conf/server.xml
#RUN  echo "JAVA_HOME=/usr/lib/jvm/java-7-oracle" >> /etc/default/tomcat7
RUN echo $JAVA_HOME >> /etc/default/tomcat7
RUN java -version
# Download Slashdot homepage
ADD build/libs/gradle-spring-mvc-web-project-1.0.war /usr/local/tomcat7/webapps/
ENV CATALINA_HOME /usr/local/tomcat7
ADD run.sh /usr/local/tomcat7/bin/
RUN chmod 755 /usr/local/tomcat7/bin/run.sh
EXPOSE 9020
CMD /usr/local/tomcat7/bin/run.sh


ssh-keygen -t rsa -C "binitdatta@gmail.com"

vim ~/.ssh/id_rsa_binit.pub