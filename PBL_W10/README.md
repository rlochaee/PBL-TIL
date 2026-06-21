# 🦁 멋쟁이사자 멤버 관리 시스템

멋쟁이사자처럼 10주차 PBL 프로젝트입니다.  
Spring Boot + JPA + MySQL 기반의 멤버 및 과제 관리 REST API 서버로, 전역 예외 처리와 프론트엔드 연동을 포함합니다.

---

## 🛠 기술 스택

| 기술 | 버전 |
|------|------|
| Java | 21 |
| Spring Boot | 4.0.6 |
| Spring Data JPA | - |
| MySQL | 8.x |
| Lombok | - |
| Gradle | - |

---

## ▶ 실행 방법

### 1. MySQL DB 생성

```sql
CREATE DATABASE likelion_pbl;
```

### 2. application.properties 설정 확인

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/likelion_pbl?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=create
```

### 3. 프로젝트 실행

```bash
./gradlew bootRun
```

### 4. 브라우저 접속

```
http://localhost:8080
```

---

## 📡 API 목록

### Member API

| 메서드 | URI | 설명 |
|--------|-----|------|
| POST | `/members/lions` | 아기사자 등록 |
| POST | `/members/staffs` | 운영진 등록 |
| GET | `/members` | 전체 멤버 조회 |
| GET | `/members?part={part}` | 파트별 멤버 필터링 |
| GET | `/members/{id}` | ID로 단일 멤버 조회 |
| PUT | `/members/lions/{id}` | 아기사자 정보 수정 |
| PUT | `/members/staffs/{id}` | 운영진 정보 수정 |
| DELETE | `/members/{id}` | 멤버 삭제 |

### Assignment API

| 메서드 | URI | 설명 |
|--------|-----|------|
| POST | `/members/{memberId}/assignments` | 과제 등록 |
| GET | `/assignments` | 전체 과제 조회 |
| GET | `/assignments/{id}` | ID로 단일 과제 조회 |
| GET | `/assignments/search?keyword={keyword}` | 과제 제목 검색 |
| GET | `/members/{memberId}/assignments` | 멤버별 과제 조회 |
| PUT | `/assignments/{id}` | 과제 수정 |
| DELETE | `/assignments/{id}` | 과제 삭제 |

### 에러 응답 형식

모든 에러는 아래 형식으로 통일하여 반환합니다.

```json
{
  "status": 404,
  "message": "해당 멤버를 찾을 수 없습니다. id: 999"
}
```

| 상황 | 상태 코드 |
|------|-----------|
| 존재하지 않는 멤버 조회/수정/삭제 | 404 Not Found |
| 존재하지 않는 과제 조회/수정/삭제 | 404 Not Found |
| 중복된 이름으로 멤버 등록 | 409 Conflict |

---

## 📁 프로젝트 구조

```
src/main/java/com/likelion/pbl_w10/
├── member/                        # 멤버 도메인
│   ├── controller/                # HTTP 요청 처리
│   ├── service/                   # 비즈니스 로직
│   ├── repository/                # DB 접근 (JPA)
│   ├── domain/                    # 엔티티 (Member, RoleType)
│   └── dto/                       # 요청/응답 DTO
├── assignment/                    # 과제 도메인
│   ├── controller/
│   ├── service/
│   ├── repository/
│   ├── domain/
│   └── dto/
└── global/                        # 공통 설정
    ├── exception/                 # 커스텀 예외 + 전역 예외 처리기
    │   ├── GlobalExceptionHandler.java
    │   ├── MemberNotFoundException.java
    │   ├── AssignmentNotFoundException.java
    │   └── DuplicateMemberException.java
    └── dto/
        └── ErrorResponse.java     # 공통 에러 응답 DTO

src/main/resources/
├── static/                        # 프론트엔드 정적 파일
│   ├── index.html
│   ├── css/style.css
│   └── js/
│       ├── member.js
│       └── assignment.js
└── application.properties
```
