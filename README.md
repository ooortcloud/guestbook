## 학습 목표
이번 장에서는 다음의 내용들을 학습한다.
- 프로젝트의 계층별 구조와 객체들의 구성
- QueryDSL을 이용하여 동적으로 검색 조건 처리
- entity 객체와 DTO 구분하기
- 화면에서의 페이징 처리하는 방법

## 화면 설계서(와이어 프레임)
화면 설계서는 화면의 URL와 전달되는 파라미터 등을 설계 단계에서 미리 결정하고, DB 설계에 필요한 column들을 미리 파악하는데 도움을 준다. 이는 구현 단계에서 실수할 가능성을 크게 줄여준다는 장점이 있다.

여기서는 단순 텍스트로만 표현하도록 하겠다.
1. 목록 화면: 전체 목록을 페이징 처리해서 조회할 수 있으며, 제목/내용/작성자 항목으로 검색과 페이징 처리를 가능하게 한다.
2. 등록 화면: 새로운 글을 등록할 수 있으며, 등록 처리 후 다시 목록 화면으로 이동한다.
3. 조회 화면: 목록 화면에서 특정 글을 선택하면 자동으로 조회 화면으로 이동한다. 조회 화면에서는 '수정/삭제 화면'으로 버튼을 클릭해서 이동할 수 있다.
4. 수정/삭제 화면: 수정 화면에서 삭제가 가능하며, 삭제 후 목록 화면으로 이동한다. 글을 수정한 이후에는 다시 조회 화면으로 이동해서 수정 내용을 확인할 수 있다.

| 기능  | URL                 | GET/POST | 세부 기능       | Redirect URL    |
| --- | ------------------- | -------- | ----------- | --------------- |
| 목록  | /guestbook/list     | GET      | 목록/페이징/검색   |                 |
| 등록  | /guestbook/register | GET      | 입력 화면       |                 |
| 등록  | /guestbook/register | POST     | 등록 처리       | /guestbook/list |
| 조회  | /guestbook/read     | GET      | 조회 화면       |                 |
| 수정  | /guestbook/modify   | GET      | 수정/삭제 기능 화면 |                 |
| 수정  | /guestbook/modify   | POST     | 수정 처리       | /guestbook/read |
| 삭제  | /guestbook/remove   | POST     | 삭제 처리       | /guestbook/list |

## 계층 구조
텍스트로 간단하게 표현하겠다.
- 브라우저에서 들어오는 request는 GuestbookController 객체로 처리한다.
- 브라우저에서 전달되는 request는 GuestbookController에서 DTO 형태로 처리한다.
- GuestbookController는 GuestbookService 타입을 주입받는 구조로 만든다.
- GuestbookRepository는 Spring Data JPA를 이용해서 구성하고, GuestbookServiceImpl 클래스에 주입해서 사용한다.
- GuestbookRepository는 엔티티 객체를 이용하므로, 중간에 Service 계층에서는 DTO와 엔티티 객체 간 변환을 처리한다.
- Thymeleaf를 이용해서 레이아웃 템플릿을 활용해 화면을 출력한다.

