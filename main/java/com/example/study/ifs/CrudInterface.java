package com.example.study.ifs;

//틀릴수도 있으니깐 강제로 재정의 하라고 인터페이스 설정

import com.example.study.model.network.Header;

public interface CrudInterface {
    Header create();    //todo request object 추가

    Header read(Long id);

    Header update();

    Header delete(Long id);
}
