# testplanner
Test management application

Based on https://www.callicoder.com/spring-boot-rest-api-tutorial-with-mysql-jpa-hibernate/


# Curl commands

## Get all testcases

```
curl http://localhost:8080/api/v1/testcases
```

## Create a testcase

```
curl --header "Content-Type: application/json" \
  --request POST \
  --data '{"name":"My first testcase","description":"Desc goes here"}' \
  http://localhost:8080/api/v1/testcases
```

## Get a single testcase

```
curl http://localhost:8080/api/v1/testcases/1
```

## Update a testcase

```
curl --header "Content-Type: application/json" \
  --request PUT \
  --data '{"name":"My first testcase updated","description":"Desc goes here"}' \
  http://localhost:8080/api/v1/testcases/1
```

## Delete a testcase

```
curl --request DELETE http://localhost:8080/api/v1/testcases/1
```