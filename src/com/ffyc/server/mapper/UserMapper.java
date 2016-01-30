package com.ffyc.server.mapper;

import org.apache.ibatis.annotations.Param;
import com.ffyc.server.vo.UserTokenVO;

public interface UserMapper
{
    public void updateUserLocation(@Param("userId") String userId,@Param("lng") double lng,@Param("lat") double lat);

    public UserTokenVO getUserTokenByToken(@Param("token") String token);
    
    public UserTokenVO getUserTokenByUserId(@Param("userId") String userId);

    public void insertUserToken(UserTokenVO userTokenVO);
    
    public void updateUserToken(@Param("userId") String userId,@Param("token") String token);
    
    public void deleteUserToken(@Param("userId") String userId);

}
