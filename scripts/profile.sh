#!/usr/bin/env bash

#쉬고 있는 profile찾기 : real1이 사용중이면 real2가 쉬고 있고, 반대면 real1이 쉬고있음

function find_idle_profile()
{
  #curl : 명령어 기반 웹 요청 도구 특히 rest 애플리케이션을 개발 테스트에 유용하게 사용할 수 있다.
  #아래처럼 간단한 스크립트로 웹 서버의 정상 동작 여부를 확인 할 수 있다.
  RESPONSE_CODE=$(curl -s -o /dev/null -w "%{http_code}" http://localhost/profile)
  # RESPONSE_CODE=$(curl -s -o /dev/null -w "%{http_code}" http://localhost/profile)
  # - 현재엔진엑스가 바라보고 있는 스프링 부트가 정상적으로 수행중인지 확인합니다.
  # - 응답값을 HttpStatus로 받습니다.
  # - 정상이면 200, 오류가 발생한다면 400~503 사이로 발생하니 400이상은 모두 예외로 보고 real2를 현재 profile로 사용합니다.

  if [ ${RESPONSE_CODE} ge 400] #response_code가 400보다 크면 40x, 50x 에러 모두 포함

  then
    CURRENT_PROFILE=real2
  else
    CURRENT_PROFILE=$(curl -s http://localhost/profile)
  fi

  if [ ${CURRENT_PROFILE} == real1 ]
  then
    # - 엔진엑스와 연결되지않은 profile입니다.
    # - 스프링 부트 프로젝트를 이 profile로 연결하기 위해 반환합니다.
    IDLE_PROFILE=real2

  else
    IDLE_PROFILE=real1
  fi

  # bash라는 스크립트는 값을 반환하는 기능이 없습니다.
  # 그래서 제일 마지막 줄에 echo로 결과를 출력 후, 클라이언트에서 그 값을 잡아서 ($(find_idle_profile)) 사용합니다.
  # 중간에 echo를 사용해서는 안됩니다.
  echo ${IDLE_PROFILE}

}

#쉬고 있는 profile의 port 찾기
function find_idle_port()
{
  IDLE_PROFILE=$(find_idle_profile)

  if [ ${IDLE_PROFILE} == real ]
  then
    echo "8081"
  else
    echo "8082"
  fi
}