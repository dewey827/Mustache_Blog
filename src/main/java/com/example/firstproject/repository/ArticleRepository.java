package com.example.firstproject.repository;

import com.example.firstproject.entity.Article;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

//리파지토리 - 인터페이스

//Article : 관리 대상의 엔티티 클래스 타입
//Long : 관리 대상의 대푯값 엔티티 타입
public interface ArticleRepository extends CrudRepository<Article,Long> {
    @Override
    ArrayList<Article> findAll(); //Iterable -> ArrayList 수정
}
