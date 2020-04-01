package com.example.study.ifs;

//틀릴수도 있으니깐 강제로 재정의 하라고 인터페이스 설정

import com.example.study.model.network.Header;

//일일히 User, Order 인터페이스를 만들어 주기 귀찮기 때문에 타입을 제네릭으로 관리하는게 편하다.
public interface CrudInterface<Req, Res> {
    Header<Res> create(Header<Req> request);    //todo request object 추가

    Header<Res> read(Long id);

    Header<Res> update(Header<Req> request);

    Header delete(Long id);
}
