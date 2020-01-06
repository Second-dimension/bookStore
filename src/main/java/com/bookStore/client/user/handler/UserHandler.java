package com.bookStore.client.user.handler;

import com.bookStore.client.user.service.IUserService;
import com.bookStore.commons.beans.Order;
import com.bookStore.commons.beans.Orderitem;
import com.bookStore.commons.beans.User;
import com.bookStore.utils.ActiveCodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;

@Controller
@RequestMapping("/client/user")
public class UserHandler {

    @Autowired
    private IUserService userService;

    @RequestMapping("/findEmail.do")
    @ResponseBody
    public String findEmail(String email){
        User user = userService.findEmail(email);
        if (user != null){
            return "EXIST";
        }else {
            return "OK";
        }
    }

    @RequestMapping("/findUsername.do")
    @ResponseBody
    public String findUsername(String username){
        User user = userService.findUsername(username);
        if (user != null){
            return "EXIST";
        }else {
            return "OK";
        }
    }

    @RequestMapping("/register.do")
    public String register(User user, String checkCode, HttpSession session, HttpServletRequest request){
        System.out.println(user);
        System.out.println(checkCode+"输入");
        String checkcode_session = (String) session.getAttribute("checkcode_session");
        user.setActiveCode(ActiveCodeUtils.createActiveCode());
        if (checkcode_session.equals(checkCode)) {
            int rows = userService.addUser(user,request);
            if (rows > 0){
                return "/client/registersuccess.jsp";
            }else {
                request.setAttribute("fail","用户注册失败，请重新注册！");
                return "/client/register.jsp";
            }
        }else {
            request.setAttribute("user",user);
            request.setAttribute("check_error","验证码错误，请重新输入！");
            return "/client/register.jsp";
        }
    }

    @RequestMapping("/activeUser.do")
    public String activeUser(String activeCode){
        int row = userService.activeUser(activeCode);
        if (row > 0){
            return "/client/activesuccess.jsp";
        }else {
            return "/client/activeFail.jsp";
        }
    }

    @RequestMapping("/myAccount.do")
    public String myAccount(HttpSession session, HttpServletRequest request){
        User login_user = (User) session.getAttribute("login_user");
        //如果已登录
        if (login_user !=null){
            return "/client/myAccount.jsp";
        }else {
        //如果未登录
        //调用自动登录方法
            login_user = autologin(request);
        //自动登陆成功
            if (login_user != null){
                session.setAttribute("login_user",login_user);
                return "/client/myAccount.jsp";
            }
            return "/client/login.jsp";
        }
    }

    @RequestMapping("/login.do")
    public String login(User user, String remember, String autologin, HttpSession session, HttpServletRequest request, HttpServletResponse response){
        //查找用户名密码是否正确
        User login_user=userService.findUserByLogin(user);
        //如果用户名正确
        if (login_user != null){
            //如果用户已激活
            if (login_user.getState() == 1){
                if ("1".equals(autologin)){
                    addCookie(autologin,user,request,response);
                }else if ("1".equals(remember)){
                    //保存用户名
                    addCookie(autologin,user,request,response);
                }
                session.setAttribute("login_user",login_user);
                return "/index.jsp";
            }else {//用户未激活
                request.setAttribute("error","账号未激活，请激活后使用！");
                return "/client/login.jsp";
            }
        }else {
            //用户名或密码错误
            request.setAttribute("error","用户名或密码错误！");
            return "/client/login.jsp";
        }
    }

    @RequestMapping("/modifyUser.do")
    public String modifyUser(User user, HttpSession session, Model model){
        User login_user = (User) session.getAttribute("login_user");
        //根据用户的id进行修改
        user.setId(login_user.getId());
        int row = userService.modifyUser(user);
        if (row > 0){
            model.addAttribute("error","用户信息修改成功，请重新登陆！");
            return "/client/login.jsp";
        }else {
            model.addAttribute("fail","用户信息修改失败！");
            return "/client/modifyuserinfo.jsp";

        }
    }

    @RequestMapping("/logout.do")
    public String logout(HttpSession session,HttpServletResponse response,HttpServletRequest request){
        session.removeAttribute("login_user");
        Cookie cookie1 = new Cookie("bookstore_username",null);
        cookie1.setMaxAge(0);
        cookie1.setPath(request.getContextPath()+"/");
        response.addCookie(cookie1);
        Cookie cookie2 = new Cookie("bookstore_password",null);
        cookie2.setMaxAge(0);
        cookie2.setPath(request.getContextPath()+"/");
        response.addCookie(cookie2);
        return "/client/login.jsp";
    }

    @RequestMapping("/findOrderByUser.do")
    public String findOrderByUser(User user,HttpSession session,Model model){
        User login_user = (User) session.getAttribute("login_user");
        List<Order> orders = userService.findOrderByUser(login_user.getId());
        model.addAttribute("orders",orders);
        return "/client/orderlist.jsp";
    }

    @RequestMapping("/findOrderById.do")
    public String findOrderById(String id,Model model){
        List<Orderitem> items = userService.findOrderitemById(id);
        model.addAttribute("items", items);
        return "/client/orderInfo.jsp";
    }

    @RequestMapping("/removeOrderById.do")
    public String removeOrderById(String id,String flag){
        userService.removeOrderById(id,flag);
        return "/client/user/findOrderByUser.do";
    }

    private void addCookie(String autologin, User user, HttpServletRequest request, HttpServletResponse response) {
        try {
            Cookie cookie1 = null;

            cookie1 = new Cookie("bookstore_username", URLEncoder.encode(user.getUsername(),"utf-8"));
            cookie1.setMaxAge(60*60*24*3);
            cookie1.setPath(request.getContextPath()+"/");
            response.addCookie(cookie1);
            if ("1".equals(autologin)){
                Cookie cookie2 =new Cookie("bookstore_password",URLEncoder.encode(user.getPassword(),"utf-8"));
                cookie2.setMaxAge(60*60*24*3);
                cookie2.setPath(request.getContextPath()+"/");
                response.addCookie(cookie2);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    private User autologin(HttpServletRequest request) {
        String username = null;
        String password = null;
        User user =new User();
        Cookie[] cookies = request.getCookies();
        try {
             for (Cookie cookie:cookies) {
                 if ("bookstore_username".equals(cookie.getName())) {
                     username = URLDecoder.decode(cookie.getValue(), "utf-8");
                 }
                 if ("bookstore_password".equals(cookie.getName())) {
                     password = URLDecoder.decode(cookie.getValue(), "utf-8");
                 }
             }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        user.setUsername(username);
        user.setPassword(password);
        return userService.findUserByLogin(user);
    }

}