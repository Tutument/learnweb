package com.xyf.learnweb.common.exception.user;

/**
 * 用户不存在异常类
 * 
 * @author Lihui
 */
public class UserNotExistsException extends UserException
{
    private static final long serialVersionUID = 1L;

    public UserNotExistsException()
    {
        super("user.not.exists", null);
    }
}
