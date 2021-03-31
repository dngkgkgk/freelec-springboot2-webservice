package com.jojoIdu.book.springboot.domain.posts;

import com.jojoIdu.book.springboot.web.domain.posts.Posts;
import com.jojoIdu.book.springboot.web.domain.posts.PostsRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTest {
    @Autowired
    PostsRepository postsRepositoryTest;

    //Junit 에서 단위테스트가 끝날 때마다 수행되는 메소드를 저장
    //보통은 배포 전 전체 테스트를 수행할 때 테스트간 데이터 침범을 막기 위해 사용합니다
    //여러테스트가 동시에 수행되면 테스트용 데이터베이스인 H2에 데이터가 그대로 남아 있어 다음테스트 실행 시 테스트가 실해할 수 있습니다.
    @After
    public void cleanup(){
        postsRepositoryTest.deleteAll();
    }

    @Test
    public void 게시글저장_불러오기(){
        String title = "테스트 게시글";
        String content = "테스트 내용";
        postsRepositoryTest.save(Posts.builder()
                                .title(title)
                                .content(content)
                                .author("jojo@gmail.com").build());

        List<Posts> postsList = postsRepositoryTest.findAll();
        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
    }

    @Test
    public void BaseTImeEntity_등록(){
        LocalDateTime now = LocalDateTime.of(2019,6,4,0,0,0);
        postsRepositoryTest.save(Posts.builder()
            .title("title")
            .content("content")
            .author("author")
            .build());

        List<Posts> postList = postsRepositoryTest.findAll();

        Posts posts = postList.get(0);

        System.out.println(">>>>>>>> createDate="+posts.getCreatedDate()+", modified " + posts.getModifiedDate());
        assertThat(posts.getCreatedDate()).isAfter(now);
        assertThat(posts.getCreatedDate()).isAfter(now);

        //assertThat
        // assertj라는 테스트 검증 라이브러리의 검증 메서드입니다.
        // 검증하고싶은 대상을 메소드 인자로 받습니다.
        // 메서드 체이닝이 지원되어 isEqualTo와 같이 메소드를 이어서 사용할 수 있습니다.

        //isEqualTo
        // assertj의 동등 비교 메서드입니다.
        // assertThan에 있는 값과 isEqualTo의 값을 비교해서 같을 때만 성공입니다.
    }
}
