# Spring-Security-Study
BE Spring Repo - 브랜치에 따라 다른 방식 구현

<br/>

## Version
* Java 17
* Spring Boot 3.3.2

## Branch
* <strong>basic/access+refresh</strong> <br/>
  일반 로그인 / 회원가입 + JWT(access + refresh) <br/>

* <strong>oauth2/access+refresh</strong> <br/>
  oauth2 소셜 로그인 + JWT(access + refresh) <br/>

* <strong>oauth2/access+refresh-v2</strong> - 진행 중 <br/>
  oauth2 소셜 로그인 + JWT(access + refresh) <br/>
  리소스 서버에 인가코드로 access 토큰을 요청하는 부분부터 구현
