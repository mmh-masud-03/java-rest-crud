/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package features.users.dao;

import features.users.dto.UserDto;
import java.util.List;

/**
 *
 * @author macpc3
 */
interface UsersDao {
    List<UserDto> findAll() throws Exception;
    UserDto findById(String id) throws Exception;
    int insert(UserDto user) throws Exception;
    int update(UserDto user) throws Exception;
    int delete(String id) throws Exception;
    
}
