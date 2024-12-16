package com.example.EmployeeApp.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class LogIn {
        private long id;
        private String username;
        private String password;

        public LogIn(String username, String password) {
                this.username = username;
                this.password = password;
        }
}
