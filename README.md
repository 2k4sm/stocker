# stocker
 Developing a system to manage stock trading operations, including handling of trader profiles, stock transactions, stock listings, and market staff. This system will facilitate the creation, update, retrieval, and deletion of information about traders, transactions, stocks, and market personnel.

## Setup Guide:

- Clone the repository.
```shell
git clone git@github.com:2k4sm/stocker.git
cd stocker
```

- To use default database config(need to have provided .env file) run :
```shell
export $(cat .env | xargs)
```

- To use other database(use db conn string to obtain needed details) run: 

__*provide appropriate value for < blank > fields*__

```shell
touch .env

# export the variables.
export DB_URL=<url>
export DB_PWD=<user-password>
export DB_UNAME=<user-name>

# Store it in .env(needed for compose to run.)
echo DB_URL=$DB_URL >> .env
echo DB_PWD=$DB_PWD >> .env
echo DB_UNAME=$DB_UNAME >> .env

export $(cat .env | xargs)
```

- Building the jar file run:
```shell
./mvnw clean package
```

- To start the server run:
```shell
java -jar ./target/stocker-0.0.1-SNAPSHOT.jar
```

- Stopping the server.
```shell
press : ctrl + c
```
---

# Schema Design:
![drawSQL-stocker](https://github.com/2k4sm/stocker/assets/101013814/6a234afc-f12b-43ff-b84e-8281b945bd28)

# API Endpoints:
Use the swagger-Ui url to access the api endpoints and interact with the api:
__[swagger-ui](https://api-sm2k4.koyeb.app/swagger-ui/index.html)__

# API is deployed using Dockerfile at koyeb.

-  __Access the api at [api-sm2k4.koyeb.app](https://api-sm2k4.koyeb.app/)__

# Thanks for using stocker.