# ledr_dashboard
This is the dash board to be used by the LEDR team to help view stats

## Running on Mac
To run the app on Mac, go to the base directory of the project in terminal and run the command `mvn clean install -DskipTests=true` followed by `mvn spring-boot:run` This will launch the App on port `7051`.

## Running on Mac (UI only)
To run the UI only on Mac, go to the base directory of the Web App in terminal `/src/main/app` and run the command `npm start` This will launch the UI on port `3000`.

## Using Mock Endpoints
To utilising the Mock Endpoints change `API_ENDPOINT.DASHBOARD` to `API_ENDPOINT.DASHBOARD_MOCK` in `DashboardData.js`

## Setting up OracleDB on Mac

`git clone` the Oracle docker repository <https://github.com/oracle/docker-images>  

Download the _Linux x86-64_ image for _Oracle Database 12c Release 2_ from <http://www.oracle.com/technetwork/database/enterprise-edition/downloads/index.html>

Put the _.zip_ folder in `docker-images/OracleDatabase/SingleInstance/dockerfiles/12.2.0.1`  _(Do not extract the .zip, this is done automatically later.)_

Run the _buildDockerImage.sh_ script as follows to build the Oracle Docker Image:

`./buildDockerImage.sh -v 12.2.0.1 -e`

_(This process may take a while so be patient)_

Once this is complete you can run the following command to start the Oracle Database:

	docker run -p 1521:1521 -p 5500:5500 \
	-e ORACLE_SID=LEDRORACLE \
	-e ORACLE_PWD=Followthel3dr \
	oracle/database:12.2.0.1-ee
