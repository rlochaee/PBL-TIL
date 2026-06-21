# 📘 Today I Learned

### 1. 오늘 배운 내용
- @RestControllerAdvice를 사용한 전역 예외 처리 구현
- 커스텀 예외 클래스(MemberNotFoundException, AssignmentNotFoundException, DuplicateMemberException) 작성
- ErrorResponse DTO로 에러 응답 형식 통일 {status, message}
- orElse(null) → orElseThrow() 패턴으로 서비스 레이어 리팩토링
- Spring Data JPA 쿼리 메서드 네이밍으로 검색 API 구현 (findByPart, findByTitleContaining)
- @RequestParam으로 파트별 멤버 필터링 (GET /members?part=백엔드)
- 정적 리소스 폴더(src/main/resources/static/)를 통한 프론트엔드 연동

### 2. 핵심 정리 (내 언어로)
@RestControllerAdvice는 여러 컨트롤러에 걸쳐 발생하는 예외를 한 곳에서 통합 처리하는 클래스에 붙이는 어노테이션이다. 이전에는 각 컨트롤러마다 예외 상황을 직접 처리해야 했지만, @RestControllerAdvice를 사용하면 전역에서 발생하는 예외를 하나의 클래스가 담당하기 때문에 코드 중복이 줄고 유지보수가 쉬워진다.

@ExceptionHandler는 특정 예외 클래스를 지정하면, 그 예외가 발생했을 때 해당 메서드가 자동으로 실행되는 원리다. 예를 들어 @ExceptionHandler(MemberNotFoundException.class)라고 선언하면, 어느 컨트롤러에서든 MemberNotFoundException이 던져졌을 때 이 메서드가 잡아서 원하는 HTTP 상태 코드와 메시지를 응답으로 돌려줄 수 있다.

커스텀 예외를 만드는 이유는 예외 상황에 의미를 부여하기 위해서다. RuntimeException만 던지면 어떤 상황에서 발생한 에러인지 코드만 봐서는 알기 어렵다. 반면 MemberNotFoundException, DuplicateMemberException처럼 이름이 명확한 예외를 만들면, 코드를 읽는 사람이 어떤 문제가 발생했는지 바로 파악할 수 있다.

서비스에서 null을 반환하는 방식은 컨트롤러가 null 여부를 직접 확인하고 분기 처리를 해야 한다는 문제가 있다. 반면 예외를 던지는 방식은 서비스 내부에서 문제를 즉시 알리고, 전역 예외 처리기가 이를 받아서 처리하기 때문에 컨트롤러가 훨씬 단순해진다. 또한 RuntimeException을 상속한 예외는 @Transactional이 붙은 메서드 안에서 발생하면 자동으로 롤백되므로 데이터 정합성 측면에서도 안전하다.

Spring Data JPA는 메서드 이름만으로 쿼리를 자동 생성해준다. findByPart(String part)라고 선언하면 WHERE part = ? 쿼리가 되고, findByTitleContaining(String keyword)라고 하면 WHERE title LIKE '%keyword%' 쿼리가 자동으로 만들어진다. SQL을 직접 작성하지 않아도 되는 것이 큰 장점이다.

프론트엔드에서 fetch()를 호출하면, 브라우저가 지정한 URL로 HTTP 요청을 보낸다. 백엔드는 해당 요청을 컨트롤러에서 받아 서비스 → 레포지토리 순서로 처리한 뒤 JSON 형태의 응답을 돌려준다. 프론트엔드는 response.json()으로 데이터를 파싱해 화면에 렌더링한다. Spring Boot는 src/main/resources/static/ 폴더의 파일을 자동으로 서빙하므로, 별도 서버 없이 http://localhost:8080에서 바로 프론트엔드와 백엔드가 연동된다.

HTTP 메서드는 CRUD와 다음과 같이 대응된다. POST는 새 데이터를 생성(Create), GET은 데이터를 조회(Read), PUT은 기존 데이터를 수정(Update), DELETE는 데이터를 삭제(Delete)할 때 사용한다.


### 3. 결과 이미지(스크린샷)
- 멤버 등록
![alt text](<스크린샷 2026-06-21 085709.png>)
![alt text](<스크린샷 2026-06-21 085847.png>)

- 멤버 수정
![alt text](<스크린샷 2026-06-21 090048.png>)

- 멤버 삭제
![alt text](<스크린샷 2026-06-21 090048.png>)
![alt text](<스크린샷 2026-06-21 090112.png>)

- 과제 등록
![alt text](<스크린샷 2026-06-21 090300.png>)

- 과제 조회
![alt text](<스크린샷 2026-06-21 090502.png>)

- 멤버별 과제 조회
![alt text](<스크린샷 2026-06-21 090515.png>)

- 과제 단건 조회
![alt text](<스크린샷 2026-06-21 090527.png>)

- 과제 제목 검색
![alt text](<스크린샷 2026-06-21 090541.png>)

- 과제 수정
![alt text](<스크린샷 2026-06-21 090638.png>)
![alt text](<스크린샷 2026-06-21 090638.png>)

- 과제 삭제
![alt text](<스크린샷 2026-06-21 090701.png>)
![alt text](<스크린샷 2026-06-21 090708.png>)

### 4. 느낀 점
 9주차까지는 예외가 발생하면 서비스에서 null을 반환하고, 컨트롤러에서 if (result == null) 같은 일을 직접 처리했다. 10주차에서는 이 방식을 예외 처리 방식으로 바꾸고, @RestControllerAdvice로 한 곳에서 처리하도록 리팩토링했다. 이렇게 해보니 코드가 눈에 띄게 깔끔해졌고, 컨트롤러가 본래 역할인 요청 수신과 응답 반환에만 집중할 수 있게 된다는 것을 알 수 있었다. 또 프론트엔드를 직접 연동해서 만든 기능이 화면에서 실제로 동작하는 걸 보니, 지금까지 만든 API가 어디에 쓰이는지 체감할 수 있어서 재미있었다.
