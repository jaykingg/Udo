# API 시나리오

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

- **경로:** `/api/book/list`
- **메소드:** GET
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
