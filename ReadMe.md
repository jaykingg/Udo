해당 문서는 프로젝트에 대한 전반적인 설명이며, 아래와 같은 순서로 작성되어있습니다.

1. 프로젝트 스펙 문서
2. API 스펙 문서
3. 애플리케이션 실행 절차

# 프로젝트 스펙 문서

- Sprint Boot 3, kotlin(openjdk-17)
- Spring Web, Spring Data JPA, Spring Security, JWT
- Validator, Configurator
- H2 in-memory Database

# API 스펙 문서

모든 API 의 도메인은 localhost:8080 입니다.   
예) `localhost:8080/api/account/regsiter`

## 사용자 관련 API

### 사용자 등록

- **경로:** `/api/account/register`
- **메소드:** POST
- **설명:** 사용자는 이름, 이메일, 핸드폰 번호, 비밀번호를 입력하여 시스템에 등록합니다.
  이때, 이메일이 가장 유니크하다고 판단되어 이메일을 사용, jwt token 을 생성할 때 사용합니다.
- **요청 예시:**
    ```json
    {
      "name": "홍길동",
      "email": "hong@example.com",
      "mobileNumber": "010-1234-5678",
      "password": "password123"
    }
    ```
- **응답 예시:** 상태 코드 201 (Created)

### 사용자 로그인

- **경로:** `/api/account/login`
- **메소드:** POST
- **설명:** 등록된 사용자는 이메일과 비밀번호를 사용하여 로그인합니다. 성공적인 로그인 시, JWT 토큰이 반환됩니다.
- **요청 예시:**
    ```json
    {
      "email": "hong@example.com",
      "password": "password123"
    }
    ```
- **응답 예시:**
    ```json
    {
      "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
    }
    ```

## 도서 관련 API
도서 관련 API 는 인증된 사용자만 접근가능합니다.
따라서, 로그인을 통해 발급받은 token을 사용하여 API Call 을 진행합니다.   
Curl 을 사용하실 경우 `-H 'Authorization: Bearer $token'` 을 추가,   
Postman 을 사용하실 경우 Authorization Tab 에 Bearer -> token 값을 입력한 뒤 진행해주세요.
### 도서 위탁

- **경로:** `/api/book/consign`
- **메소드:** POST
- **설명:** 사용자는 도서명, ISBN 코드, 가격을 입력하여 도서를 시스템에 위탁합니다.
- **요청 예시:**
    ```json
    {
      "bookName": "자바의 정석",
      "isbn": "978-89-6626-355-4",
      "price": 30000
    }
    ```
- **응답 예시:** 상태 코드 200 (OK)

### 도서 대여

- **경로:** `/api/book/rent`
- **메소드:** POST
- **설명:** 사용자는 대여하고자 하는 도서의 ID 목록을 입력하여 도서를 대여합니다. 대여된 도서는 스케쥴러에 의해 자동으로 15~20초 후에 반납 처리됩니다.
- **요청 예시:**
    ```json
    {
      "rentBookIds": [1, 2]
    }
    ```
- **응답 예시:** 상태 코드 200 (OK)

### 도서 목록 조회

### 요청

- **경로:** `/api/book/list`
- **메소드:** GET
- **파라미터:**
    - `page`: 조회할 페이지 번호 (옵션)
    - `size`: 20 (기본값)
    - `sortType`: 정렬 방식을 지정합니다. 옵션은 `rentalCount`, `price`, `recent`가 있으며, 기본값은 `rentalCount`입니다.
        - `rentalCount`: 대여 많은 순으로 정렬 (기본값)
        - `price`: 낮은 가격순으로 정렬
        - `recent`: 최근 등록일 순으로 정렬
- **설명:** 사용자는 시스템에 등록된 도서 목록을 조회할 수 있습니다. 이때, 도서의 대여 여부와 대여 횟수, 등록한 유저의 정보도 함께 제공됩니다.
- **응답 예시:**
    ```json
    [
      {
        "id": 1,
        "bookName": "자바의 정석",
        "isbn": "978-89-6626-355-4",
        "price": 30000,
        "registeredAt": "2023-03-01T12:00:00",
        "accountResponse" : {
            "id" : 1,
            "name" : "홍길동"
        },
        "isRented": false,
        "rentalCount": 2
      }
    ]
    ```

# 애플리케이션 실행 절차 문서

### 1. 시스템 요구사항

- Java 버전: Java JDK 17 이상
- Gradle
- 8080 port 사용가능

### 2. 설치 및 실행 절차

Docker 이미지 실행 방법은 Docker 설치 및 환경세팅의 메스가 있고,
빌드 및 자체 이미지 크기를 고려하여 배제하였습니다.
따라서 아래 방법 중 하나를 선택합니다.

1. GitHub 저장소에서 프로젝트를 특정 폴더에 Clone 하거나 첨부된 파일을 압축해제합니다.
2. IDE 를 사용하는 경우
    1. 해당 프로젝트를 연동시킨 뒤 실행합니다. 단, Project SDK 를 jdk17 에 맞춰진 것을 확인해야합니다.
3. jar 파일로 실행하는 경우
    1. 프로젝트 내 최상위 폴더에서 아래 커멘드를 실행합니다.
    ```bash
    ./gradlew build
    ```
    2. /build/lib 폴더로 이동 한 뒤 아래 커멘트를 실행합니다.
    ```bash
    java -jar Udo-0.0.1.jar
    ```
