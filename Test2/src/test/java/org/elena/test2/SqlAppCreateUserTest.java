package org.elena.test2;

import org.elena.test2.dao.SqlApp;
import org.elena.test2.dto.UserDto;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class SqlAppCreateUserTest {

    @Test
    public void testCreateUserWithoutAddress() {
        SqlApp sqlApp = new SqlApp();
        UserDto userDto1 = new UserDto("Kevin Moore");
        sqlApp.createUser(userDto1);
        List<UserDto> usersDtoList = Reader.getUsers();
        boolean isCreated = false;
        for (UserDto userDto : usersDtoList) {
            if (userDto.equals(userDto1)) {
                isCreated = true;
                break;
            }
        }
        Assert.assertTrue(isCreated, "User " + userDto1.getName() + " is not created");
    }

    @Test
    public void testCreateUserWithAddress() {
        SqlApp sqlApp = new SqlApp();
        UserDto userDto1 = new UserDto("James Russell", "201 Shawnee Ave, Kansas City, KS 66105, USA");
        sqlApp.createUser(userDto1);
        List<UserDto> usersDtoList = Reader.getUsers();
        boolean isCreated = false;
        for (UserDto userDto : usersDtoList) {
            if (userDto.equals(userDto1)) {
                isCreated = true;
                break;
            }
        }
        Assert.assertTrue(isCreated, "User " + userDto1.getName() + " with address " + userDto1.getAddress() + " is not created");
    }
}
