package com.example.study.service;

import com.example.study.ifs.CrudInterface;
import com.example.study.model.entity.AdminUser;
import com.example.study.model.network.Header;
import com.example.study.model.network.request.AdminUserApiRequest;
import com.example.study.model.network.response.AdminUserApiResponse;
import com.example.study.repository.AdminUserRepository;
import jdk.javadoc.internal.doclets.formats.html.markup.Head;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminUserApiLogicService implements CrudInterface<AdminUserApiRequest, AdminUserApiResponse> {
    @Autowired
    AdminUserRepository adminUserRepository;

    @Override
    public Header<AdminUserApiResponse> create(Header<AdminUserApiRequest> request) {
        AdminUserApiRequest adminUserApiRequest = request.getData();
        AdminUser adminUser= AdminUser.builder()
                .account(adminUserApiRequest.getAccount())
                .password(adminUserApiRequest.getPassword())
                .status(adminUserApiRequest.getStatus())
                .role(adminUserApiRequest.getRole())
                .lastLoginAt(adminUserApiRequest.getLastLoginAt())
                .passwordUpdatedAt(adminUserApiRequest.getPasswordUpdatedAt())
                .registeredAt(adminUserApiRequest.getRegisteredAt())
                .unregisteredAt(adminUserApiRequest.getUnregisteredAt())
                .createdAt(adminUserApiRequest.getCreatedAt())
                .createdBy(adminUserApiRequest.getCreatedBy())
                .updatedAt(adminUserApiRequest.getUpdatedAt())
                .updatedBy(adminUserApiRequest.getUpdatedBy())
                .build();

        AdminUser newAdminUser = adminUserRepository.save(adminUser);

        return response(newAdminUser);
    }

    @Override
    public Header<AdminUserApiResponse> read(Long id) {

        return adminUserRepository.findById(id).
                map(adminUser -> response(adminUser))
                .orElseGet(()->Header.ERROR("데이터 없음"));
    }

    @Override
    public Header<AdminUserApiResponse> update(Header<AdminUserApiRequest> request) {
        AdminUserApiRequest body = request.getData();
        return adminUserRepository.findById(body.getId())
                .map(adminUser -> {
                    adminUser
                            .setAccount(body.getAccount())
                            .setPassword(body.getPassword())
                            .setStatus(body.getStatus())
                            .setRole(body.getRole())
                            .setLastLoginAt(body.getLastLoginAt())
                            .setLoginFailCount(body.getLoginFailCount())
                            .setPasswordUpdatedAt(body.getPasswordUpdatedAt())
                            .setRegisteredAt(body.getRegisteredAt())
                            .setRegisteredAt(body.getRegisteredAt())
                            .setUnregisteredAt(body.getUnregisteredAt())
                            .setCreatedAt(body.getCreatedAt())
                            .setCreatedBy(body.getCreatedBy())
                            .setUpdatedAt(body.getUpdatedAt())
                            .setCreatedBy(body.getUpdatedBy());
                    return adminUser;
                })
                .map(adminUser->adminUserRepository.save(adminUser))
                .map(adminUser -> response(adminUser))
                .orElseGet(()->Header.ERROR("데이터 없음"));
    }

    @Override
    public Header delete(Long id) {
        return adminUserRepository.findById(id)
                .map(adminUser -> {
                    adminUserRepository.delete(adminUser);
                    return Header.OK();
                })
                
                .orElseGet(()->Header.ERROR("데이터 없음"));
    }

    private Header<AdminUserApiResponse> response(AdminUser adminUser){
        AdminUserApiResponse adminUserApiResponse= AdminUserApiResponse.builder()
                .id(adminUser.getId())
                .account(adminUser.getAccount())
                .password(adminUser.getPassword())
                .status(adminUser.getStatus())
                .role(adminUser.getRole())
                .lastLoginAt(adminUser.getLastLoginAt())
                .loginFailCount(adminUser.getLoginFailCount())
                .registeredAt(adminUser.getRegisteredAt())
                .unregisteredAt(adminUser.getUnregisteredAt())
                .createdAt(adminUser.getCreatedAt())
                .createdBy(adminUser.getCreatedBy())
                .updatedAt(adminUser.getUpdatedAt())
                .updatedBy(adminUser.getUpdatedBy())
                .build();

        return Header.OK(adminUserApiResponse);
    }
}
