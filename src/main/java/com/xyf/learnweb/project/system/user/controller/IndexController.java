package com.xyf.learnweb.project.system.user.controller;

import com.xyf.learnweb.framework.config.CipherResourceRunConfig;
import com.xyf.learnweb.framework.web.controller.BaseController;
import com.xyf.learnweb.project.system.menu.domain.Menu;
import com.xyf.learnweb.project.system.menu.service.IMenuService;
import com.xyf.learnweb.project.system.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * 首页 业务处理
 * 
 * @author Lihui
 */
@Controller
public class IndexController extends BaseController
{
    @Autowired
    private IMenuService menuService;

    @Autowired
    private CipherResourceRunConfig cipherResourceRunConfig;

    // 系统首页
    @GetMapping("/index")
    public String index(ModelMap mmap)
    {
        // 取身份信息
        User user = getSysUser();
        // 根据用户id取出菜单
        List<Menu> menus = menuService.selectMenusByUser(user);
        mmap.put("menus", menus);
        mmap.put("user", user);
        mmap.put("copyrightYear", cipherResourceRunConfig.getCopyrightYear());
        return "index";
    }

    // 切换主题
    @GetMapping("/system/switchSkin")
    public String switchSkin(ModelMap mmap)
    {
        return "skin";
    }

    // 系统介绍
    @GetMapping("/system/main")
    public String main(ModelMap mmap)
    {
        mmap.put("version", cipherResourceRunConfig.getVersion());
        return "main";
    }
}
