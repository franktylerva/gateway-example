package com.example.webapp;

import javax.validation.constraints.NotEmpty;

public record  UserRequest(@NotEmpty String name) {
}
