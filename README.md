# testplanner
Test management application

## References

https://www.softensity.com/blog/crud-api/
https://caslav-nedeljkovic.medium.com/creating-first-spring-boot-restful-api-with-postgresql-29cf14d48a4e
https://spring.io/guides/tutorials/rest/

## cURL commands

### Create a testcase

    curl --request POST \
    --url http://localhost:8888/testplanner/v1/testcases/ \
    --header 'Content-Type: application/json' \
    --data '{ "name": "Testcase 1"}'

### Get a testcase (after creating one or more)

    curl  http://localhost:8888/testplanner/v1/testcases/1

### Update an existing testcase

    curl --request PUT \
    --url http://localhost:8888/testplanner/v1/testcases/1 \
    --header 'Content-Type: application/json' \
    --data '{ "id": 1, "name": "Testcase 1 updated"}'

### Delete a testcase
    curl --request DELETE http://localhost:8888/testplanner/v1/testcases/1

### Get all testcases

    curl http://localhost:8888/testplanner/v1/testcases/