# Spring Boot and AWS S3 Bucket

## Overview

This project demonstrates the integration of Spring Boot with Amazon Web Services (AWS) S3. A model Student was used to simulate a real scenario of a profile picture beign uploaded.

## Technologies

- Spring Boot
- Spring Web
- Spring Data JPA
- PostgreSQL
- Docker
- AWS SDK
- Apache Tika

## Features

- Post Student
- List Students
- Upload files to AWS S3.

## Prerequisites

Before running the project, ensure you have the following prerequisites:

- JDK 17
- Maven
- Docker
- AWS CLI
- AWS account with access to S3 service
- AWS access key and secret key
- AWS S3 bucket created

## Configuration

To configure the project for your AWS S3 account, you need to edit your `.aws/credentials` and `.aws/config` files

```properties
# AWS S3 credentials
[default]
aws_access_key_id = <YOUR_ACCESS_KEY_HERE>
aws_secret_access_key = <YOUR_SECRET_KEY_HERE>
```

```properties
# AWS S3 config
[default]
region = <BUCKET_REGION_HERE>
```

With that configured the only thing left is to put your bucket name in the following line `../domain/StudentService.java`

```
private static final String BUCKET_NAME = "your-bucket-name-here";
```

## Running

- Clone the project
- Execute the docker-compose file
```
docker-compose -f docker-compose.local.yml up
```
- In the root folder run
```
mvn clean install
```
```
mvn spring-boot:run
```

## Testing

You can use any http request tool that you like.

HOST: http://localhost:8080

Endpoints:
- /api/v1/student/post
- /api/v1/student/list
- /api/vi/student/upload/{id}

Using httpie

Post Student:
```
http POST http://localhost:8080/api/v1/student/post name="student" email="student@gmail.com"
```

List Students:
```
http http://localhost:8080/api/v1/student/list
```

Upload img:
```
http -f POST http://localhost:8080/api/v1/student/upload/{student_id} file@image-name.png
```

For uploading the picture your terminal should be in the folder that your image is located, or you can define the path of the file in the request `file@~/images/image-name.png`




