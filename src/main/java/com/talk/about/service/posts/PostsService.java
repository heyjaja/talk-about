package com.talk.about.service.posts;

import com.talk.about.domain.posts.Posts;
import com.talk.about.domain.posts.PostsRepository;
import com.talk.about.web.dto.PostsResponseDto;
import com.talk.about.web.dto.PostsSavsRequestDto;
import com.talk.about.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    // 글쓰기
    @Transactional
    public Long save(PostsSavsRequestDto requestDto) {

        return postsRepository.save(requestDto.toEntity()).getId();
    }

    // 글수정
    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {

        Posts posts = postsRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    // 글조회
    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 게시글이 없습니다. id="+id));

        return new PostsResponseDto(entity);
    }
}
