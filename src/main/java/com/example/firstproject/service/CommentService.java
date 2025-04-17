package com.example.firstproject.service;

import com.example.firstproject.dto.CommentDto;
import com.example.firstproject.entity.Article;
import com.example.firstproject.entity.Comment;
import com.example.firstproject.repository.ArticleRepository;
import com.example.firstproject.repository.CommentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class CommentService {
    //댓글 객체 주입
    @Autowired
    private CommentRepository commentRepository;

    //게시글 객체 주입
    @Autowired
    private ArticleRepository articleRepository;




    //댓글 조회 메서드 comments
    public List<CommentDto> comments(Long articleId) {
        /*// 1. 댓글 조회
        List<Comment> comments = commentRepository.findByArticleId(articleId);

        // 2. 엔티티 -> DTO 변환
        List<CommentDto> dtos = new ArrayList<CommentDto>();

        for (int i = 0; i < comments.size(); i++) {
            Comment c = comments.get(i);
            CommentDto dto = CommentDto.createCommentDto(c);
            dtos.add(dto);
        }*/

        // 3. 결과 반환
        return commentRepository.findByArticleId(articleId)
                .stream()
                .map(comment -> CommentDto.createCommentDto(comment))
                .collect(Collectors.toList());
    }




    //댓글 생성 메서드 create
    @Transactional
    public CommentDto create(Long articleId, CommentDto dto){
        //1.게시글 조회, 예외 발생
        Article article = articleRepository.findById(articleId)
                .orElseThrow(()->new
                        IllegalArgumentException("댓글 생성 실패!" + "대상 게시글 없습니다."));

        //2. 댓글 엔티티 생성
        Comment comment = Comment.createComment(dto,article);

        //3. 댓글 엔티티를 DB에 저장
        Comment created = commentRepository.save(comment);

        //4. DTO로 변환 -> 반환
        return CommentDto.createCommentDto(created);
    }


    //댓글 수정 메서드 update
    @Transactional
    public CommentDto update(Long id, CommentDto dto) {
        // 1. 댓글 조회 및 예외 발생
        Comment target = commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("댓글 수정 실패!" +
                        "대상 댓글이 없습니다."));
        // 2. 댓글 수정
        target.patch(dto);

        // 3. DB로 갱신
        Comment updated = commentRepository.save(target);

        // 4. 댓글 엔티티를 DTO로 변환 및 반환
        return CommentDto.createCommentDto(updated);
    }




    //댓글 삭제 메서드 delete
    @Transactional
    public CommentDto delete(Long id) {
        // 1. 댓글 조회 및 예외 발생
        Comment target = commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("댓글 삭제 실패! " +
                        "대상이 없습니다."));

        // 2. 댓글 삭제
        commentRepository.delete(target);

        // 3. 삭제 댓글을 DTO로 변환 및 반환
        return CommentDto.createCommentDto(target);
    }


}
