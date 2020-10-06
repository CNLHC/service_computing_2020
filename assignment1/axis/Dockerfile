from tomcat:9.0


WORKDIR /usr/local/tomcat/webapps/
RUN mkdir axis2
COPY axis2.war  ./axis2
RUN cd axis2; jar xvf axis2.war
COPY repository/services/* /usr/local/tomcat/webapps/axis2/WEB-INF/services/

