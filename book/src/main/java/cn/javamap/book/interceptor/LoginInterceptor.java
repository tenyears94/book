package cn.javamap.book.interceptor;

import cn.javamap.book.pojo.User;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor extends HandlerInterceptorAdapter {
    //    设置需要拦截的路径
    public static final String[] LOGIN_URL = {"add", "delete", "update", "admin/user", "borrow"};

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//      获取请求路径
        String url = request.getRequestURI().toLowerCase();
        System.out.println("请求路径---------->" + url + "<----------");
        User user = (User) request.getSession().getAttribute("login_user");
        if (user == null) {
            for (String s : LOGIN_URL) {
                if (url.indexOf(s) >= 0) {
                    System.out.println("路径过滤----->" + url);
                    response.sendRedirect(request.getContextPath() + "/user/login");
                    return false;
                }
            }
        }
        return true;
    }
}
