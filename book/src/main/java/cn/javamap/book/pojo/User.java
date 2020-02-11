package cn.javamap.book.pojo;

import java.io.Serializable;

/**
 * 对应数据表 `user` 的学生实体类
 */
public class User implements Serializable {
    private static final long serialVersionUID = -9029167481808271591L;
    private Integer userId;

    private String userName;

    private String userPwd;

    private String userRole;

    private String userEmail;

    private Integer userStatus;

    private Integer deptId;

    private Dept dept;

    public User(Integer userId, String userName, String userPwd, String userRole, String userEmail, Integer userStatus, Integer deptId) {
        this.userId = userId;
        this.userName = userName;
        this.userPwd = userPwd;
        this.userRole = userRole;
        this.userEmail = userEmail;
        this.userStatus = userStatus;
        this.deptId = deptId;
    }

    public User() {
        super();
    }

    public User(String userName) {
        this.userName = userName;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd == null ? null : userPwd.trim();
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole == null ? null : userRole.trim();
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail == null ? null : userEmail.trim();
    }

    public Integer getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(Integer userStatus) {
        this.userStatus = userStatus;
    }

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public Dept getDept() {
        return dept;
    }

    public void setDept(Dept dept) {
        this.dept = dept;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userPwd='" + userPwd + '\'' +
                ", userRole='" + userRole + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", userStatus=" + userStatus +
                ", deptId=" + deptId +
                ", dept=" + dept +
                '}';
    }
}