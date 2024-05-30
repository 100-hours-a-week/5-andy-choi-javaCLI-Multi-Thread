# 프로젝트 개요



- **콘솔 기반의 주식 시뮬레이션 게임 구현**
- **내가 자산 관리자가 되어 고객의 돈을 관리**
- **상품은 주식, 펀드, 채권**
- **1일 3턴(장 초반,중반,후반) 존재, 각 턴 별로 상품 가격이 바뀜**
- **난이도는 Easy, Normal, Hard**
- **난이도에 따라 상품 상승 확률, 시작 원금, 고객의 인내심이 달라짐**
- **고객의 인내심 ⇒ 현재 자산이 원금의 n%가 될 경우 고객이 돈을 빼 감.**
- **고객이 돈을 빼가면 GAME OVER.**
- **반대로 원금의 5배로 돈을 불리면 WIN.**

<br></br>

# 프로젝트 목표



- **재밌는 주식 시뮬레이션 게임 구현**
- **객체 간 상호작용을 통한 OOP 프로그래밍**
- **다중 사용자에 의해 발생하는 멀티 스레드의 동시성 제어 구현**
<br></br><br></br>
# 동작 과정


<img width="985" alt="Untitled" src="https://github.com/HuttTheJAVA/JavaCLI/assets/92637789/7b64b686-cf99-41e6-b8f0-27907a09865a">

### 상세 설명

1. 사용자가 선택지를 보고 값을 입력
2. Main 클래스는 선택지에 따른 동작을 수행시킴
3. 매수, 매도, 계좌 조회, 상품 상세 조회 등 선택한 동작 발생
4. 선택이 끝날 때 마다 주식 가격 변동(refresh)이 발생 후 주식 시장의 변동을 출력함
5. 사용자의 수익률에 따라 게임 오버, Win, 계속 진행을 시킬지 판단
<br></br><br></br>



### ✅ 시연

- 난이도 선택
- 종목 상세 조회 시연
- 잔액 조회, 계좌 조회 시연
- 종목 조회 (목록)

![몸 풀기 시연 (1)](https://github.com/HuttTheJAVA/JavaCLI/assets/92637789/50bcfcce-6932-43b6-9129-d79543be99f0)


<br></br><br></br>
# 프로그램 구조


<p align="center">
  <img src="https://github.com/HuttTheJAVA/JavaCLI/assets/92637789/a16da19f-9022-4806-b14e-a80b05d04544" alt="이미지 설명" width="200">
</p>
<br></br><br></br>

# 프로그램 설계도


<p align="center">
  <img width="474" alt="Untitled (2)" src="https://github.com/HuttTheJAVA/JavaCLI/assets/92637789/7104b7d1-3451-4621-a8df-b5cebb14c387">
</p>

<p align="center"><strong>클래스 다이어그램</strong></p><br></br><br></br><br></br>

<p align="center">
  <img width="975" alt="Untitled (3)" src="https://github.com/HuttTheJAVA/JavaCLI/assets/92637789/aaa37447-0383-40b3-a74a-abbf8e9a7169">
</p>

<p align="center"><strong>컨트롤러 클래스 다이어그램</strong></p><br></br><br></br>

# 클래스 및 메서드 설명
<br></br>


## 🕹 Main 클래스

- 프로젝트를 관장하며 컨트롤러들을 컨트롤하여 주식시장을 동작시킴
- 선택지 메뉴를 출력하고 사용자의 입력에 따라 해당 이벤트를 발생 시킴
- eventController를 시켜 주식 시장 가격 변동(refresh)을 일으킴

## 🕹 Account 클래스

- 고객의 잔액과 주식 계좌를 관리하는 클래스
- 입,출금, 매수, 매도, 빚 등 고객의 자산과 관련된 모든 행위를 책임 짐
- 고객의 전체 수익률, 계좌 상세 조회 가능

## 🕹 MessageController 클래스

- 시작 배너,모드 설정 , 선택지 메뉴, 게임 오버, 종료 메시지 등 큰 이벤트에 관련 출력문을 출력하는 클래스
- 출력 사이즈가 큰 문자열들을 따로 필드로 가짐

## 🕹 ProductController 클래스

- 종목  초기 생성, 종목 조회 등 종목 관련 처리를 담당한다
- Account, EventController가 참조하는 클래스
- Account는 매수,매도 시 ProductController가 필요
- EventController는 refresh 발생 시 ProductController가 필요 함

## 🕹 EventController 클래스

- 게임 난이도 설정, 주식 시장 변동, 게임 오버 등 주식 시장에서 발생하는 이벤트를 담당
- ProductController의 product_lst를 참조하여 1턴 마다 모든 종목의 가격을 변동시킨다

## 🎟 Stock,Fund,Bond,PreferredStock 클래스

- 모두 Product 클래스를 조상으로 가짐
- 각각 주식, 펀드, 채권, 우선주를 나타내는 클래스
- 공통적으로 이름, 가격, 수량, 등을 가짐
- 종목 리스크, 수수료, 배당금, 이자, CEO 등 여러 필드를 개별적으로 가짐
