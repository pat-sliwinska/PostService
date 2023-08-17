package com.project.poster.shared.common.utils;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum FileExtension {
    JSON(".json");

    private final String extensionName;
}
