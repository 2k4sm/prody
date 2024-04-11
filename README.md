# Prody

## Product service api made using springboot and postgresql.

### Setup Guide.

- Clone the repository and move into it.

  ```bash
  git clone git@github.com:2k4sm/prody.git
  cd prody
  ```

- SET ENV Variables by creating a .env file.

  ```bash
  touch .env

  # export the variables.
  export DB_URL=<url>/prody
  export DB_NAME=prody
  export DB_PWD=<user-password>
  export DB_UNAME=<user-name>

  # Store it in .env(needed for compose to run.)
  echo DB_URL=$DB_URL >> .env
  echo DB_NAME=$DB_NAME >> .env
  echo DB_PWD=$DB_PWD >> .env
  echo DB_UNAME=$DB_UNAME >> .env
  ```

- Start postgres instance.

  ```bash
  docker compose up -d
  ```

- Run the server.

  ```java
  export $(cat .env | xargs)
  ./mvnw clean package
  java -jar ./target/prody-0.0.1-SNAPSHOT.jar
  ```

- Stop postgres instance.

  ```bash
  docker compose down
  ```

## Thanks for using Prody.
