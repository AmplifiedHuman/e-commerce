# International Bot

## Quick start
### Live site
[internationalbots.herokuapp.com](https://internationalbots.herokuapp.com/)
### For H2-Database (dev profile)
```bash
mvn clean install -Pdev
mvn spring-boot:run 
```
Visit [localhost:8080](http://localhost:8080/)
### Database production instance with Postgres
#### MacOS/Linux
Install [Docker-Compose](https://docs.docker.com/compose/install/), then run
```bash
./run.sh
```
#### Windows
Install [Docker-Compose](https://docs.docker.com/compose/install/) and [Maven](https://mkyong.com/maven/how-to-install-maven-in-windows/), then run
```bash
bash run.sh
```
Visit [localhost:8080](http://localhost:8080/)
