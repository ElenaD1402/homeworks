package org.elena.test2.dto;

public class UserDto {
    String name;
    String address;

    public UserDto(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public UserDto(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public void print() {
        System.out.printf("%-20s %-50s %n", name, address);
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof UserDto) {
            UserDto userDto = (UserDto) object;
            if (userDto.getName().equals(this.getName())) {
                if (userDto.getAddress() == null & this.getAddress() == null) {
                    return true;
                } else {
                    return userDto.getAddress().equals(this.getAddress());
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
