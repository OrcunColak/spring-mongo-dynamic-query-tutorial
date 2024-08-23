package com.colak.springtutorial.helpers;

public record FilterCondition(String field, FilterOperation operator, Object value) {
}