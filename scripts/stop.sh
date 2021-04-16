#!/usr/bin/env bash

# 심볼릭 링크
# 심볼릭 링크는 단순히 원본파일을 가르키도록 링크만 연결 시켜둔 것으로 윈도우에서의 바로가기 파일이라고 생각하시면 됩니다.
# 또한 원본파일만 가리키기만 하고 있으므로 원본파일의 크기와 무관하며 원본파일이 삭제되어 존재하지 않을 경우에 빨간색으로 깜빡 거리면서
# 링크파일의 원본파일이 없다는 것을 알려줍니다.
# 심볼릭 링크가 없으면 일일이 파일경로를 다 외워서 파일을 찾은 후 실행 시켜야 될 것입니다.

#readlink 심볼릭 링크의 원본 파일 확인
# -f 심볼릭 링크를 따라 최종 파일 절대 경로반환 $0 - 아마 현재위치
ABSPATH=$(readlink -f $0)

#ABSDIR=$(dirname $ABSPATH)
# - 현재 stop.sh가 속해 있는 경로를 찾습니다.
# - 하단의 코드와 같이 profile.sh의 경로를 찾기 위해 사용됩니다.
ABSDIR=$(dirname $ABSPATH)

# source ${ABSDIR}/profile.sh
# 자바로 보면 일종의 import 구문입니다.
# 해당코드로 인해 stop.sh에서도 profile.sh의 여러 function을 사용할 수 있게 됩니다.
source ${ABSDIR}/profile.sh

IDLE_PORT=$(find_idle_port)

echo "> $IDLE_PORT에서 현재 구동중인 애플리케이션 pid 확인 "
IDLE_PID=$(lsof -ti tcp:${IDLE_PORT})

if [ -z ${IDLE_PID} ]
then
  echo "> 현재 구동 중인 애플리케이션이 없으므로 종료하지 않습니다."
else
  echo "> kill -15 $IDLE_PID"
  kill -15 ${IDLE_PID}
  sleep 5
fi