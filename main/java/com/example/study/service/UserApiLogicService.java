package com.example.study.service;

import com.example.study.ifs.CrudInterface;
import com.example.study.model.UserStatus;
import com.example.study.model.entity.User;
import com.example.study.model.network.Header;
import com.example.study.model.network.request.UserApiRequest;
import com.example.study.model.network.response.UserApiResponse;
import com.example.study.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserApiLogicService extends BaseService<UserApiRequest, UserApiResponse, User> {

//    @Autowired
//    private UserRepository userRepository;

    //1. request data 를 가져오기
    //2. user 생성
    //3. 생성된 데이터 -> UserApiResponse return
    @Override
    public Header<UserApiResponse> create(Header<UserApiRequest> request) {

        //1. request data
        UserApiRequest userApiRequest = request.getData();

        //2. User 생성
        User user= User.builder()
                .account(userApiRequest.getAccount())
                .password(userApiRequest.getPassword())
                .status(UserStatus.REGISTERED)
                .phoneNumber(userApiRequest.getPhoneNumber())
                .email(userApiRequest.getEmail())
                .registeredAt(LocalDateTime.now())
                .build();

        User newUser = baseRepository.save(user);

        //3.생성된 데이터 -> userApiResponse return
        //다른함수에서도 쓸수 있으니깐 밖으로 뺀다.
        return Header.OK(response(newUser));
    }

    @Override
    public Header<UserApiResponse> read(Long id) {
        //id -> repository getOne, getById
        return baseRepository.findById(id)
                .map(user -> response(user))
//                .map(userApiResponse -> Header.OK(userApiResponse))
                .map(Header::OK)
                .orElseGet(()->Header.ERROR("데이터 없음"));

//        Optional<User> optional =userRepository.findById(
//        id);
//
//        //user -> userApiResponse return
//        return optional
//                .map(user -> response(user))
//                .orElseGet(()->Header.ERROR("데이터 없음"));
    }

    @Override
    public Header<UserApiResponse> update(Header<UserApiRequest> requset) {
        //1.data 가져오기
        UserApiRequest userApiRequest = requset.getData();
        //2.id -> user 데이터 찾기

        Optional<User> optional= baseRepository.findById(userApiRequest.getId());
        return optional.map(user->{
            //3.data -> update
            //id
            user.setAccount(userApiRequest.getPassword())
                    .setPassword(userApiRequest.getPassword())
                    .setStatus(userApiRequest.getStatus())
                    .setPhoneNumber(userApiRequest.getPhoneNumber())
                    .setEmail(userApiRequest.getEmail())
                    .setRegisteredAt(userApiRequest.getRegisteredAt())
                    .setUnregisteredAt(userApiRequest.getUnregisteredAt());

            return user;

        })
        .map(user -> baseRepository.save(user))//update -> newUser
        .map(updateUser -> response(updateUser))//userApiResponse
        .map(userApiResponse -> Header.OK(userApiResponse))
        .orElseGet(()->Header.ERROR("데이터없음"));
    }

    @Override
    public Header delete(Long id) {
        //1.id -> repository -> user
        Optional<User> optional = baseRepository.findById(id);

        //2.repository -> delete
        return optional.map(user ->{
            baseRepository.delete(user);

            return Header.OK();
        })
        .orElseGet(()->Header.ERROR("데이터 없음"));
    }
    private UserApiResponse response(User user){
        //user -> userApiResponse

        UserApiResponse userApiResponse = UserApiResponse.builder()
                .id(user.getId())
                .account(user.getAccount())
                .password(user.getPassword())// todo 암호화, 길이
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .status(user.getStatus())
                .registeredAt(user.getRegisteredAt())
                .unregisteredAt(user.getUnregisteredAt())
                .build();

        //header + data return

        return userApiResponse;
    }

    public Header<List<UserApiResponse>> search(Pageable pageable) {
        Page<User> users = baseRepository.findAll(pageable);
        List<UserApiResponse> userApiResponseList = users.stream()
                .map(user -> response(user))
                .collect(Collectors.toList());

        //List<UserApiResponse>
        //Header<List<UserApiResponse>>

        return Header.OK(userApiResponseList);
    }
}
