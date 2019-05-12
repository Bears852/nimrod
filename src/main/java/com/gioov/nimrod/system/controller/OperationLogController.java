package com.gioov.nimrod.system.controller;

import com.gioov.nimrod.common.Common;
import com.gioov.nimrod.system.System;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.gioov.nimrod.user.service.UserService.SYSTEM_ADMIN;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2018-02-22
 */
@Controller
@RequestMapping(System.Page.OPERATION_LOG)
public class OperationLogController {

    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('/SYSTEM/OPERATION_LOG/PAGE_ALL')")
    @RequestMapping("/page_all")
    public String pageAll() {
        return Common.trimSlash(System.Page.OPERATION_LOG + "/page_all");
    }

}
