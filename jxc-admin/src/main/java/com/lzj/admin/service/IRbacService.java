package com.lzj.admin.service;

import java.util.List;

public interface IRbacService {

    List<String> findRolesByUserName(String userName);

    List<String> findAuthoritiesByRoleName(List<String> roleNames);
}
