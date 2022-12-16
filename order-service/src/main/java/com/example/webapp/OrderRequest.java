package com.example.webapp;

import javax.validation.constraints.NotEmpty;

public record OrderRequest(@NotEmpty String name) {
}
